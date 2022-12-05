package com.mycompany.copaqatar.models;

import java.util.ArrayList;

public class Group {
    private int id;
    private String nome;
    private ArrayList<Time> times = new ArrayList<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void addTeam (Time time) {
        this.times.add(time);
    }

    public ArrayList<Time> getTimes() {
        return times;
    }

    public Time getTime(int index) {
        return times.get(index);
    }

    public void setTimes(ArrayList<Time> times) {
        this.times = times;
    }

}
