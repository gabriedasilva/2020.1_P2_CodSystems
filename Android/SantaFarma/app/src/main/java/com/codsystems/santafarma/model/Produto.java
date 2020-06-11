package com.codsystems.santafarma.model;

public class Produto {
    private String idProduto;
    private String nome;
    private String desc;
	private String categoria;
	private String qtdeDisp;
    private String classe;
    private String preco;


    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getQtdeDisp() {
        return qtdeDisp;
    }

    public void setQtdeDisp(String qtdeDisp) {
        this.qtdeDisp = qtdeDisp;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
}
