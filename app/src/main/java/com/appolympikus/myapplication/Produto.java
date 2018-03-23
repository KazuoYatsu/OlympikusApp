package com.appolympikus.myapplication;

/**
 * Created by Arthur on 23/03/18.
 */

public class Produto {

    private String Titulo_ref;
    private String Categoria;
    private String Descricao;
    private int Thumbnail;

    public Produto() {


    }

    public Produto(String titulo_ref, String categoria, String descricao, int thumbnail) {
        Titulo_ref = titulo_ref;
        Categoria = categoria;
        Descricao = descricao;
        Thumbnail = thumbnail;
    }

    public String getTitulo_ref() {
        return Titulo_ref;
    }

    public String getCategoria() {
        return Categoria;
    }

    public String getDescricao() {
        return Descricao;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setTitulo_ref(String titulo_ref) {
        Titulo_ref = titulo_ref;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}
