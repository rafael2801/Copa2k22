/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.copaqatar;

/**
 *
 * @author supor
 */
public class Grupo {
    private char nome;
    private Equipe[] equipes = new Equipe[4];
    private Partida[] partidas = new Partida[48];
    
    public Grupo(char x, Equipe e1, Equipe e2, Equipe e3, Equipe e4){
        this.nome = x;
        this.equipes[0] = e1;
        this.equipes[1] = e2;
        this.equipes[2] = e3;
        this.equipes[3] = e4;
    }
    
    public String getNome(){
        String texto = "Grupo ";
        return texto.concat(String.valueOf(this.nome));
    }
    
    public void getGrupo(){
        System.out.println(this.getNome());
        for(Equipe equipe : this.equipes){
            System.out.println(equipe.getNome());
        }
        for(Partida partida: this.partidas){
            System.out.println(partida.getPartida());
        }
    }
    
    // public Partida[] gerarPartidas(){
    public void gerarPartidasGrupo(){
        // um laço de repetição com indíce de 0 a 2 para gerar as três rodadas
        Partida[] partidas = new Partida[6];
        partidas[0] = new Partida(equipes[0], equipes[1]);
        partidas[0].simularPartida();
        partidas[1] = new Partida(equipes[2], equipes[3]);
        partidas[1].simularPartida();
        partidas[2] = new Partida(equipes[0], equipes[2]);
        partidas[2].simularPartida();
        partidas[3] = new Partida(equipes[3], equipes[1]);
        partidas[3].simularPartida();
        partidas[4] = new Partida(equipes[3], equipes[0]);
        partidas[4].simularPartida();
        partidas[5] = new Partida(equipes[1], equipes[2]);
        partidas[5].simularPartida();
        this.partidas = partidas;
    }
}
