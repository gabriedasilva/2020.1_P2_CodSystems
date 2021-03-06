package com.codsystems.santafarma.model;

import androidx.annotation.Nullable;

import com.codsystems.santafarma.config.ConfigFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Usuario {

    private String uid;
    private String cidade,bairro,rua,numero,complemento,pto_ref;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private ArrayList<String> endereco = new ArrayList<>();

    public Usuario() {
    }



    public void updateUsuario(Usuario u){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();

        user.put("idCliente",u.getUid());
        user.put("nome", u.getNome());
        user.put("endereco", u.getEndereco());
        user.put("telefone", u.getTelefone());

        db.collection("Clientes")
                .document(u.getUid())
                .set(user)
        ;
    }
    public void salvarCloud(Usuario u){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();

        user.put("idCliente",u.getUid());
        user.put("nome", u.getNome());
        user.put("endereco", u.getEndereco());
        user.put("telefone", u.getTelefone());
        user.put("email",u.getEmail());



// Add a new document with a generated ID
        db.collection("Clientes")
                .document(u.getUid())
                .set(user)
        ;
    }
    public void setEndereco(Usuario u) {
        endereco.add(u.getCidade());
        endereco.add(u.getBairro());
        endereco.add(u.getRua());
        endereco.add(u.getNumero());
        endereco.add(u.getComplemento());
        endereco.add(u.getPto_ref());
    }
    public ArrayList<String> getEndereco() {
        return endereco;
    }
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }









    // INICIO ENDERECO
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getPto_ref() {
        return pto_ref;
    }

    public void setPto_ref(String pto_ref) {
        this.pto_ref = pto_ref;
    }
// F I M  E N D E R E C O
}

