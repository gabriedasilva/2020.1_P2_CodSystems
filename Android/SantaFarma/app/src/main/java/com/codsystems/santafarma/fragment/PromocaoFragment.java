package com.codsystems.santafarma.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.codsystems.santafarma.R;
import com.codsystems.santafarma.activity.MainActivity;
import com.codsystems.santafarma.activity.OfertaActivity;
import com.codsystems.santafarma.model.Oferta;
import com.codsystems.santafarma.model.Pedido;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PromocaoFragment extends Fragment {
int count;
    public PromocaoFragment() {
        // Required empty public constructor
    }

    ListView listaPromo;
    String nomeProd;
    ArrayList<Oferta> listaDeProdutos = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

                count = 0;
        View view = inflater.inflate(R.layout.fragment_promocao, container, false);
        listaPromo = view.findViewById(R.id.listaPromocao);
        buscarOfertas();
        listaPromo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                count =0;
                if(count == 0){
       nomeProd =  listaPromo.getItemAtPosition(position).toString();
                    System.out.println(nomeProd);
                    OfertaActivity.nomePROMOCAO = nomeProd;
            abrirPerfilPromocao();
                    System.out.println("------------------------"+nomeProd);
                }else{
                    System.out.println("ERRO");
                }
            }
        });

        return view;
    }
    public void abrirPerfilPromocao(){
        Intent intent = new Intent(getActivity(),OfertaActivity.class);
        getActivity().startActivity(intent);
    }



    public void setAdapter(){
        ArrayAdapter<Oferta> adapter = new ArrayAdapter<Oferta>(
                getContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                listaDeProdutos
        );
        adapter.notifyDataSetChanged();
        listaPromo.setAdapter(adapter);
    }







    public void buscarOfertas(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Promocoes").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot querySnapshot, @Nullable FirebaseFirestoreException e) {
                if(e != null){

                }else{
                    String nomeP,precoO,desc;
                    for(QueryDocumentSnapshot doc : querySnapshot){
                        Oferta of = new Oferta();
                        of.setNomeProd(doc.getData().get("produtos").toString());
                        of.setDescricao(doc.getData().get("descricao").toString());
                        of.setPreco(doc.getData().get("valor").toString());


                        listaDeProdutos.add(of);

                        System.out.println(">>>>" +doc.getData().get("produtos").toString());
                    }
           setAdapter();
                }
            }
        });
    }
}
