package com.codsystems.santafarma.fragment;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.codsystems.santafarma.R;
import com.codsystems.santafarma.model.Produto;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Objects;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProdutosFragment extends Fragment {
    ArrayList<Produto> listaDeProdutos = new ArrayList<>();
ListView produtosList;
Spinner categoriaSpinner;
static String uidProd;
boolean b = false;
    int cout;
    public ProdutosFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


       View view = inflater.inflate(R.layout.fragment_produtos, container, false);
       categoriaSpinner = view.findViewById(R.id.spinnerCategoria);
       produtosList = view.findViewById(R.id.listaProdutos);
       spinnerSetItemCatego();
       categoriaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               String s = categoriaSpinner.getSelectedItem().toString();
               System.out.println("-----------"+s);
               cout =0;
               setClasseSpinner(s);
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });

       produtosList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

         if(cout == 0) {
             cout = cout +1;;
             String s = (String) produtosList.getItemAtPosition(position);
             consultaClasse(s);
             System.out.println("STRING");
         }else {
             Produto p = (Produto)produtosList.getItemAtPosition(position);
             System.out.println("OBJETO");
         }
           }

       });

        return view;
    }

    void gerarAdapterString(String[] classes){
        listaDeProdutos.clear();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                classes  );
        adapter.notifyDataSetChanged();
        produtosList.setAdapter(adapter);
    }

    public void setClasseSpinner(String classe){

        if(classe.equals("Beleza")){
            String[] classes = new String[]{
"Tinturas","Maos e Pes"
            };
            gerarAdapterString(classes);
        }else if(classe.equals("Dermocosméticos")){
            String[] classes = new String[]{
"Rosto","Corpo"
            };
            gerarAdapterString(classes);
        }else if(classe.equals("Medicamentos")){
            String[] classes = new String[]{
"Eticos","Genericos","Similares"
            };
            gerarAdapterString(classes);
        }else if(classe.equals("Mamãe e Bebê")){
            String[] classes = new String[]{
"Fraldas","Para o Bebe","Para Mamae"
            };
            gerarAdapterString(classes);
        }else if(classe.equals("Vitaminas/Suplementos/Naturais")){
            String[] classes = new String[]{
"Vitaminas","Suplementos","Naturais"
            };
            gerarAdapterString(classes);
        }else if(classe.equals("Primeiros Socorros/Dor")){
            String[] classes = new String[]{
"Primeiros Socorros","Dor"
            };
            gerarAdapterString(classes);
        }else if(classe.equals("Higiene Pessoal")){
            String[] classes = new String[]{
"Higiene Bucal","Desodorantes","Diversos"
            };
            gerarAdapterString(classes);
        }
    }
    public void spinnerSetItemCatego(){
        String[] spinnerItem = new String[] {"Escolha Uma Categoria",
                "Beleza","Dermocosméticos" ,"Medicamentos","Mamãe e Bebê",
                "Vitaminas/Suplementos/Naturais","Primeiros Socorros/Dor",
                "Higiene Pessoal"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_spinner_item,
                spinnerItem
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriaSpinner.setAdapter(adapter);
    }

    public void consultaClasse(String classe){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Produtos")
                .whereEqualTo("classe",classe)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            b = true;
                            ArrayAdapter<Produto> adapter = new ArrayAdapter<>(
                                    getContext(),
                                    android.R.layout.simple_list_item_1,
                                    android.R.id.text1,
                                    listaDeProdutos  );
                            for (QueryDocumentSnapshot document :task.getResult()){
                                Produto p = new Produto();
                                p.setNome(document.get("nome").toString());
                                p.setCategoria(document.get("categoria").toString());
                                p.setClasse(document.get("classe").toString());
                                p.setPreco(document.get("preco").toString());
                                p.setDesc(document.get("descricao").toString());
                                p.setQtdeDisp(document.get("disp").toString());
                                p.setIdProduto(document.getId());
                                System.out.println(document.getData());
                                listaDeProdutos.add(p);
                                adapter.notifyDataSetChanged();
                            }
                            produtosList.setAdapter(adapter);

                        }

                    }
                });
    }
    public void consultaGeral(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Produtos")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){

                            ArrayAdapter<Produto> adapter = new ArrayAdapter<>(
                                    getContext(),
                                    android.R.layout.simple_list_item_1,
                                    android.R.id.text1,
                                    listaDeProdutos  );
                            for (QueryDocumentSnapshot document :task.getResult()){
                                Produto p = new Produto();
                                p.setNome(document.get("nome").toString());
                                p.setCategoria(document.get("categoria").toString());
                                p.setClasse(document.get("classe").toString());
                                p.setPreco(document.get("preco").toString());
                                p.setDesc(document.get("descricao").toString());
                                p.setQtdeDisp(document.get("disp").toString());
                                p.setIdProduto(document.getId());
                                System.out.println(document.getData());
                                listaDeProdutos.add(p);
                                adapter.notifyDataSetChanged();
                            }



                            produtosList.setAdapter(adapter);
                        }

                    }
                });

    }
}
