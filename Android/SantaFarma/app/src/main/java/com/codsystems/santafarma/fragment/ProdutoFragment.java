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
import com.codsystems.santafarma.model.Produto;
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
Produto p = ProdutosFragment.prodtela;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_produto, container, false);
        System.out.println(""+p.getNome());
        return view;

    }


}
