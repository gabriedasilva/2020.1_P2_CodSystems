package com.codsystems.santafarma.model;

import com.codsystems.santafarma.config.ConfigFirebase;
import com.google.firebase.database.DatabaseReference;

public class Usuario {

    private String uid;
    private String endereco;
    private String nome;
    private String email;
    private String senha;
    private String telefone;

    public Usuario() {
    }


    public void salvarUsuario(){
        DatabaseReference firebaseRef = ConfigFirebase.getFirebaseDatabase();
        DatabaseReference usuario = firebaseRef.child("usuarios").child(getUid());
        usuario.setValue(this);
        System.out.println("FUNCIONOU"+usuario);
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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
}

