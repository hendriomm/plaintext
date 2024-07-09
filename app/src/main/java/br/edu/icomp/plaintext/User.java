package br.edu.icomp.plaintext;

import android.media.Image;

public class User {
    private int id;
    private String uuid;
    private String nome;
    private String email;
    private String senha;
    private Image picture;


    public User(int id, String uuid, String nome, String email, String senha, Image picture) {
        this.id = id;
        this.uuid = uuid;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public Image getPicture() {
        return picture;
    }

    public void setPicture(Image picture) {
        this.picture = picture;
    }
}
