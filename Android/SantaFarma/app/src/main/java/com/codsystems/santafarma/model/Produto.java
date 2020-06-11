package com.codsystems.santafarma.model;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Produto {
    private String nome;
    private String desc;
	private String categoria;
	private int qtdeDisp;
    private String classe;
    private Double preco;

    public String getNome() {
        return nome;
    }

    public String getDesc() {
        return desc;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getQtdeDisp() {
        return qtdeDisp;
    }

    public String getClasse() {
        return classe;
    }

    public Double getPreco() {
        return preco;
    }

}
