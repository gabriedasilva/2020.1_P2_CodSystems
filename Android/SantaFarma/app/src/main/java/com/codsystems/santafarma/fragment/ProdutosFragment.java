package com.codsystems.santafarma.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.codsystems.santafarma.R;
import com.codsystems.santafarma.activity.CadastroActivity;
import com.codsystems.santafarma.activity.MainActivity;
import com.codsystems.santafarma.model.Produto;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProdutosFragment extends Fragment {
    private TextView textContato;

    public ProdutosFragment() {
        // Required empty public constructor
    }

    private ListView list_produtos;
    private String[] produtos = {

    };
    Button btn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_produtos, container, false);
consultaCole();

        return view;
    }
String nome,preco,desc,categoria,dispon,classe;
    public void consultaCole() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Produtos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Produto> produtos = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                System.out.println(document.getId());
Produto p = new Produto();
p.setNome(document.get("nome").toString());
p.setCategoria(document.get("categoria").toString());
p.setClasse(document.get("classe").toString());
p.setPreco(document.get("preco").toString());
p.setDesc(document.get("descricao").toString());
p.setQtdeDisp(document.get("disp").toString());
p.setIdProduto(document.getId());
                produtos.add(p);
                                System.out.println(
                                        p.getNome()
                                );
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }


}
