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
public class Grupo {
    private int id;
    private char nome;
    private Equipe[] equipes = new Equipe[4];
    private Partida[] partidas = new Partida[48];

    public int getId() {
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void setNome(String nome){
        this.nome = nome.charAt(0);
    }
    
    public char getNome(){
        return this.nome;
    }
    
    
    
    // public Partida[] gerarPartidas(){
    
}
