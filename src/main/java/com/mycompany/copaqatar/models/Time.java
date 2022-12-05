package com.mycompany.copaqatar.models;

public class Time {
    private int id;

    private int pontos = 0;

    public int getId() {
        return id;
    }

    public Time(String nome) {
        this.nome = nome;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public void addPontos(int pontos) {
        this.pontos += pontos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private String nome;
}
