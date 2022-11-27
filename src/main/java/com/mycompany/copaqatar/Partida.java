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
    
    
    public Partida(Equipe equipeA, Equipe equipeB){
        this.equipeA = equipeA;
        this.equipeB = equipeB;
    }
    
    public Partida(int id, Equipe equipeA, Equipe equipeB){
        this.id = id;
        this.equipeA = equipeA;
        this.equipeB = equipeB;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public Equipe getEquipeA() {
        return equipeA;
    }

    public void setEquipeA(Equipe equipeA) {
        this.equipeA = equipeA;
    }

    public Equipe getEquipeB() {
        return equipeB;
    }

    public void setEquipeB(Equipe equipeB) {
        this.equipeB = equipeB;
    }
    
    
    
    public void getPartida(){
        // return this.placarEquipeA + " " + this.equipeA.getNome() + " vs " + this.equipeB.getNome() + " " + this.placarEquipeB;
    }
}
