/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.copaqatar;

import java.sql.SQLException;

/**
 *
 * @author supor
 */
public class Equipe {
    private int id;
    private String nome;
    public Equipe(String nome){
        this.nome = nome;
    }
    
    public Equipe(int id, String nome){
        this.setId(id);
        this.setNome(nome);
    }
    public int getId() {
        return id;
    }

    public Equipe() {
    }

    public void setId(int id){
        this.id = id;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }



}