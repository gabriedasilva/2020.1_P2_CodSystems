package com.codsystems.santafarma.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.codsystems.santafarma.R;
import com.codsystems.santafarma.activity.CategoriasActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ProdutoFragment extends Fragment {


    public ProdutoFragment() {
        // Required empty public constructor
    }
private ListView opcoes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String idProd = ProdutosFragment.uidProd;
        View view =  inflater.inflate(R.layout.fragment_produto, container, false);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Produtos")
                .whereEqualTo("nome","Simeticona")
                .get()

        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                   @Override
                                   public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                       if(task.isSuccessful()){
                                           ArrayList<Object> categoria = new ArrayList<>();
                                           for(QueryDocumentSnapshot document :task.getResult()){
                                               System.out.println("OBJETOS RETORNADOS:"+document.getData());
                                           }
                                       }
                                   }
                               }
        );




        System.out.println();



        return view;
    }
}
