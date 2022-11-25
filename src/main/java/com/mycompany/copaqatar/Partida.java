/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.copaqatar;

/**
 *
 * @author supor
 */
public class Partida {
    private int id;
    private Equipe equipeA;
    private Equipe equipeB;
    // iniciei os placares com -1 para saber que a partida não aconteceu, se não aparece 0
    private int placarEquipeA = -1;
    private int placarEquipeB = -1;
    
    public Partida(Equipe equipeA, Equipe equipeB){
        this.equipeA = equipeA;
        this.equipeB = equipeB;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public void simularPartida(){
        this.placarEquipeA = (int)(Math.random() * 4);
        this.placarEquipeB = (int)(Math.random() * 4);
    }
    
    public String getPartida(){
        return this.placarEquipeA + " " + this.equipeA.getNome() + " vs " + this.equipeB.getNome() + " " + this.placarEquipeB;
    }
}
