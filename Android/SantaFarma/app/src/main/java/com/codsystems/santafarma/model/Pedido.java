package com.codsystems.santafarma.model;

import androidx.annotation.NonNull;

import com.codsystems.santafarma.config.ConfigFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Pedido {
String nomeCliente;
String nomeProduto;
String precoProduto;
String precoTotal;
ArrayList<String> endereco = new ArrayList<>();


  public  void salvarPedido(Pedido ped) {
      FirebaseFirestore db = FirebaseFirestore.getInstance();
      Map<String, Object> pedido = new HashMap<>();
      pedido.put("nomeProduto", ped.getNomeProduto());
      pedido.put("precoProduto",ped.getPrecoProduto());

    db.collection("Clientes").document(ped.getIdCliente())
            .collection("Cesta")
              .document()
     .set(pedido)
              .addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {

        }
    });
}
public void setPedido(){}

    public String getIdCliente() {
        FirebaseAuth auth = ConfigFirebase.getFirebaseAutenticacao();
        String idCliente = auth.getCurrentUser().getUid().toString();
        return idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public ArrayList<String> getEndereco() {
        return endereco;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(String precoProduto) {
        this.precoProduto = precoProduto;
    }

    @NonNull
    @Override
    public String toString() {
        return nomeProduto;
    }

    public String getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(String precoTotal) {
        this.precoTotal = precoTotal;
    }
}
