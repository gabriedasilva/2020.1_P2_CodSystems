package com.codsystems.santafarma.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.codsystems.santafarma.R;
import com.codsystems.santafarma.activity.MainActivity;
import com.codsystems.santafarma.config.ConfigFirebase;
import com.codsystems.santafarma.model.Pedido;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.math.BigDecimal;
import java.text.Format;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class PedidosFragment extends Fragment {
    String idCliente;
    ListView pedidosList;
    double precoTotalPedido;
    String nomeProduto,precoProd;
    TextView txTotal;
    String total,nomeUsuario,telefone;
    ArrayList<String>enderecoUsuario;
            ArrayList<String>prodLIST;

    Button btnenviarPed;

    FirebaseAuth auth = ConfigFirebase.getFirebaseAutenticacao();
    ArrayList<Pedido> listaPedido = new ArrayList<>();
    public PedidosFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        buscaPedidoNomes();
        View view =inflater.inflate(R.layout.fragment_pedidos, container, false);
        idCliente = auth.getCurrentUser().getUid().toString();
        pedidosList = view.findViewById(R.id.pedidosList);
        buscarPedidos(idCliente);
        txTotal = view.findViewById(R.id.precoTotal);
        System.out.println("USE"+idCliente);
        btnenviarPed = view.findViewById(R.id.buttonEnviarPedido);
        btnenviarPed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enviarPedidoCloud();

            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    public void buscaPedidoNomes(){
       String uid = auth.getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Clientes").document(uid).collection("Cesta")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        if(e !=null){

                        }else{
                            ArrayList<String> nome = new ArrayList<>();
                            for (QueryDocumentSnapshot doc : queryDocumentSnapshots){
                                if(doc.get("nomeProduto") !=null){
                                    nome.add(doc.getString("nomeProduto"));
                                }
                            }
                            System.out.println("NOME DOS PRODUTOS:::::"+nome);
                            prodLIST = nome;
                        }


                    }
                });

    }


    public void buscarPedidos(String idCliente){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Clientes").document(idCliente).collection("Cesta")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            System.out.println(task);
                            ArrayAdapter<Pedido> adapter = new ArrayAdapter<>(
                                    getActivity(),
                                    android.R.layout.simple_list_item_1,
                                    android.R.id.text1,
                                    listaPedido );
                            for (QueryDocumentSnapshot document :task.getResult()){
                                Pedido p = new Pedido();
                                p.setNomeProduto((String) document.get("nomeProduto"));
                                p.setPrecoProduto((String) document.get("precoProduto"));
                                String s = p.getPrecoProduto().replaceAll(",",".");
                                precoTotalPedido = precoTotalPedido + Double.parseDouble(s);
                                System.out.println(p.getNomeProduto());
                                System.out.println(p.getPrecoProduto());
                                listaPedido.add(p);
                                adapter.notifyDataSetChanged();
                            }
                            total = String.valueOf(precoTotalPedido).replace(".",",");
                            BigDecimal b1 = new BigDecimal(precoTotalPedido);
                            buscaUsuario();
                            String s = String.valueOf(b1.setScale(2,2));
                           total = s.replace(".",",");
                            txTotal.setText("RS "+s);
                            System.out.println("PRECO TOTAL:"+s);
                            pedidosList.deferNotifyDataSetChanged();
                            pedidosList.setAdapter(adapter);
                        }

                    }
                });

    }

    public void limparTudo(){

        ArrayAdapter<Pedido> adapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                listaPedido );
        listaPedido.clear();

        adapter.notifyDataSetChanged();
        txTotal.setText("0,00");
    }
    public void buscaUsuario(){
        FirebaseAuth auth = ConfigFirebase.getFirebaseAutenticacao();
        String uid = auth.getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Clientes").document(uid).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>(){
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            Pedido p = new Pedido();

                            String nomeCiente = ((String) task.getResult().getData().get("nome"));
                               ArrayList<String> enderecoUsuario = ((ArrayList<String>) task.getResult().getData().get("endereco"));
                                String tel = (String) task.getResult().getData().get("telefone");
setTelefone(tel);
setEnderecoUsuario(enderecoUsuario);
setNomeUsuario(nomeCiente);
                    }

                }
    });

    }
    public void enviarPedidoCloud(){

        final FirebaseAuth auth = ConfigFirebase.getFirebaseAutenticacao();
        final String uid = auth.getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> pedidoC = new HashMap<>();
        pedidoC.put("total",total);
        pedidoC.put("produtos",prodLIST);
        pedidoC.put("idCliente",uid);
        pedidoC.put("nomeCliente",getNomeUsuario());
        pedidoC.put("telefone",getTelefone());
        pedidoC.put("endereco",getEnderecoUsuario());
        pedidoC.put("situacao","0");
        db.collection("Pedidos").document()
                .set(pedidoC)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getActivity(), "Pedido enviado com Sucesso! Agradecemos pela Preferência.", Toast.LENGTH_SHORT).show();
                        apagarPedidos();


                    }
                });


    }
public void apagarPedidos(){
    FirebaseAuth auth = ConfigFirebase.getFirebaseAutenticacao();
    final String uid = auth.getCurrentUser().getUid();
    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    db.collection("Clientes").document(uid).collection("Cesta").addSnapshotListener(new EventListener<QuerySnapshot>() {
        @Override
        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
            if(e != null) {
            }else{
             for (QueryDocumentSnapshot doc :queryDocumentSnapshots){
                 String s = doc.getId();
                 System.out.println("ID DO P E D  I D O"+s);
                 db.collection("Clientes").document(uid).collection("Cesta").document(s).delete();
             }
                buscarPedidos(uid);
             limparTudo();
            }
        }
    });

    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public ArrayList<String> getEnderecoUsuario() {
        return enderecoUsuario;
    }

    public void setEnderecoUsuario(ArrayList<String> enderecoUsuario) {
        this.enderecoUsuario = enderecoUsuario;
    }
}
