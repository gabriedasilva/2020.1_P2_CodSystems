package com.codsystems.santafarma.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.codsystems.santafarma.R;
import com.codsystems.santafarma.config.ConfigFirebase;
import com.codsystems.santafarma.fragment.PromocaoFragment;
import com.codsystems.santafarma.model.Oferta;
import com.codsystems.santafarma.model.Pedido;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class OfertaActivity extends AppCompatActivity {
   public static String nomePROMOCAO;
String nomeProd,descProd,precoProd;
   TextView nomePromo,precoPromo,descPromo;
   Button salvarPedPromo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println(nomePROMOCAO);
        setContentView(R.layout.activity_oferta);
        salvarPedPromo = findViewById(R.id.gotoProdutoBtn);
        nomePromo = findViewById(R.id.promo_nome);
        precoPromo = findViewById(R.id.promo_preco);
        descPromo = findViewById(R.id.promo_descricao);
       buscaProdutoPromo(nomePROMOCAO);
       salvarPedPromo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               salvarPedido();
           }
       });

    }
    public void setCampo(){
        nomePromo.setText(nomeProd);
        precoPromo.setText(precoProd);
        descPromo.setText(descProd);
    }
    public void buscaProdutoPromo(String nome){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Promocoes").whereEqualTo("produtos",nome).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e != null) {
                }else{
                    for(QueryDocumentSnapshot doc : queryDocumentSnapshots){
                        nomeProd = doc.getData().get("produtos").toString();
               descProd = doc.getData().get("descricao").toString();
                  precoProd = doc.getData().get("valor").toString();

                    }
                    setCampo();

                }
            }
        });
    }

    public  void salvarPedido() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> pedido = new HashMap<>();
        pedido.put("nomeProduto", nomeProd);
        pedido.put("precoProduto",precoProd);
        FirebaseAuth auth = ConfigFirebase.getFirebaseAutenticacao();
        String uid = auth.getCurrentUser().getUid();
        db.collection("Clientes").document(uid)
                .collection("Cesta")
                .document()
                .set(pedido)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(OfertaActivity.this, "Pedido adicionado a Cesta", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}
