package com.codsystems.santafarma.model;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class BuscasCloud {
 static String prodCategoria,prodClasse;
    List<Produto> produto = new ArrayList<>();
    List<Produto> filtro = new ArrayList<>();


    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public void buscaCategoClasse(String categoria,String classe){
        db.collection("Produtos")
                .whereEqualTo("categoria",categoria)
                .whereEqualTo("classe",classe)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
    for(QueryDocumentSnapshot document:task.getResult()){
        System.out.println("=============================="+document.getData());
    }
                        }
                    }
                });
    }

    public void buscaGeral(){
        db.collection("Produtos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for(QueryDocumentSnapshot doc :task.getResult()){
                                System.out.println("PRODUTOS CADASTRADOS:");
                            }
                        }
                    }
                });
    }

/*
   ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(
                                    getContext(),
                                    android.R.layout.simple_list_item_1,
                                    android.R.id.text1,
                                    listaDeProdutos
                            );

                             Produto p = new Produto();
                            p.setNome(document.get("nome").toString());
                            p.setCategoria(document.get("categoria").toString());
                            p.setClasse(document.get("classe").toString());
                            p.setPreco(document.get("preco").toString());
                            p.setDesc(document.get("descricao").toString());
                            p.setQtdeDisp(document.get("disp").toString());
                            p.setIdProduto(document.getId());
* */


}
