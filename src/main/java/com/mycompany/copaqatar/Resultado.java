/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.copaqatar;

/**
 *
 * @author supor
 */
public class Resultado {
    private Partida partida;
    private int placarEquipeA = -1;
    private int placarEquipeB = -1;

    public Resultado(Partida p) {
        this.partida = p;
        this.placarEquipeA = (int)(Math.random() * 4);
        this.placarEquipeB = (int)(Math.random() * 4);
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }
    
    public int getPlacarEquipeA() {
        return placarEquipeA;
    }

    public void setPlacarEquipeA(int placarEquipeA) {
        this.placarEquipeA = placarEquipeA;
    }

    public int getPlacarEquipeB() {
        return placarEquipeB;
    }

    public void setPlacarEquipeB(int placarEquipeB) {
        this.placarEquipeB = placarEquipeB;
    }
    
}
