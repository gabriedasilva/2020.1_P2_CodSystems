package com.codsystems.santafarma.model;

import androidx.annotation.NonNull;

public class Oferta {
    String nomeProd;
    String precoOfer;
    String descricaoPromo;
    String idProduto;

    public String getIdProduto() {
        return idProduto;
    }
    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProd() {
        return nomeProd;
    }

    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }

    public String getPreco() {
        return precoOfer;
    }

    public void setPreco(String precoOfer) {
        this.precoOfer = precoOfer;
    }

    public String getDescricao() {
        return descricaoPromo;

    }

    public void setDescricao(String precoAntigo) {
        this.descricaoPromo = precoAntigo;
    }
    @NonNull
    @Override
    public String toString() {
        return nomeProd;

    }
}
