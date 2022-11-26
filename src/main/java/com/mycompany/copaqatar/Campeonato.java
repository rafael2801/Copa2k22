/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.copaqatar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author supor
 */
public class Campeonato {
    private int id;
    private String nome;
    private String sede;
    private int ano;
    private Partida[] partidas = new Partida[48];
    private Equipe[] equipes = new Equipe[32];
    private Grupo[] grupos = new Grupo[8];
    private DAO dao = new DAO();
    
    public Campeonato(){
//        DAO d = new DAO();
//        d.getCampeonato();
        // buscar atributos no BD
        // this.nome = nome;
    }
    
    public Campeonato(int id) throws SQLException{
        Campeonato c = dao.carregarCampeonato(id);
        this.nome = c.getNome();
        this.sede = c.getSede();
        this.ano = c.getAno();
        this.cadastrarEquipesOficiais();
        this.setGrupos();
        this.cadastrarGruposOficiais();
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
    
    public Partida[] getPartidas() {
        return partidas;
    }

    public void setPartidas(Partida[] partidas) {
        this.partidas = partidas;
    }

    public Equipe[] getEquipes() {
        return equipes;
    }
    
    public String[] listarEquipes(){
        String[] strings = new String[32];
        int i = 0;
        for(Equipe e : this.getEquipes()){
            strings[i] = e.getNome();
            i++;
        }
        return strings;
    }

    public void setEquipes(Equipe[] equipes) {
        this.equipes = equipes;
    }

    public Grupo[] getGrupos() {
        return grupos;
    }

    public void setGrupos() throws SQLException {
        this.grupos = this.dao.carregarGrupos();
    }
  
    public Equipe[] cadastrarEquipesOficiais() throws SQLException{
        return this.equipes = this.dao.carregarEquipesOficiais();
    }
    public void cadastrarGruposOficiais() throws SQLException{
        int e = 0;
        for(Grupo g : this.grupos){
            for(int i = 0; i < 4; i++){
                Classificacao c = new Classificacao();
                c.setEquipe(this.equipes[e]);
                c.setGrupo(g);
                this.dao.salvarClassificacao(c);
                e++;
            }
        }
    } 
}
