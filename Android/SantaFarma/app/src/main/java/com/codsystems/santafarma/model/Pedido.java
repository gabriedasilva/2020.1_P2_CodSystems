package com.codsystems.santafarma.model;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pedido {
    String idCliente;
String nomeCliente;
ArrayList<Produto> produtos = new ArrayList<>();
ArrayList<String> endereco = new ArrayList<>();

    void salvarPedido(Pedido ped){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        final Map<String, Object> pedido = new HashMap<>();
        pedido.put("endereco",ped.getEndereco());
        pedido.put("produtos",ped.getProdutos());
        pedido.put("nome",ped.getNomeCliente());
        pedido.put("idCliente",ped.getIdCliente());

        db.collection("Cesta")
                .document(ped.getIdCliente())
                .set(pedido)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        System.out.println(pedido);
                    }
                });

    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public ArrayList<String> getEndereco() {
        return endereco;
    }

    public void setEndereco(ArrayList<String> endereco) {
        this.endereco = endereco;
    }
}
