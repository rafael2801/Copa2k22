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
public class Campeonato {
    private int id;
    private String nome;
    private String sede;
    private int ano;
    private Partida[] partidas = new Partida[48];
    private Resultado[] resultados = new Resultado[48];
    private Equipe[] equipes = new Equipe[32];
    private Classificacao[] classifificacao = new Classificacao[32];
    private Grupo[] grupos = new Grupo[8];
    DAO dao = new DAO();

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
        
        //c.getEquipes()[0].setNome("Corinthians");

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

    public void setPartidas() {
        this.partidas = this.dao.carregarPartidas();
    }

    public Equipe[] getEquipes() {
        return equipes;
    }
    public Classificacao[] getClassifificacao() {
        return classifificacao;
    }

    public void setClassifificacao() {
        this.classifificacao = dao.carregarClassificacao();
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
    
    public String[] listarEquipesGrupo(Grupo g) throws SQLException{
        String[] strings = new String[4];
        int i = 0;
        for(Equipe e : this.dao.carregarEquipesGrupo(g.getId())){
            strings[i] = e.getNome();
            i++;
        }
        return strings;
    }

    public void setEquipes() {
        this.equipes = dao.carregarEquipes();
    }

    public Grupo[] getGrupos() {
        return grupos;
    }

    public void setGrupos()  {
        this.grupos = this.dao.carregarGrupos();
    }
    public Resultado[] getResultados() {
        return this.resultados;
    }

    public void setResultados() {
        this.resultados = this.dao.carregarResultados();
    }

    public void cadastrarEquipe(String nome) throws SQLException{
        Equipe e = new Equipe(nome);
        dao.salvarEquipe(e);
    }
    public Equipe[] cadastrarEquipesOficiais() throws SQLException{
        return this.equipes = this.dao.carregarEquipesOficiais();
    }
    public void cadastrarGruposOficiais() {
        int e = 0;
        try {
            for(Grupo g : this.grupos){
                for(int i = 0; i < 4; i++){
                    Classificacao c = new Classificacao();
                    c.setEquipe(this.equipes[e]);
                    c.setGrupo(g);
                    this.dao.salvarClassificacao(c);
                    e++;
                }
            }
        } catch (SQLException ex) {
            System.out.println("err on save classification: " + ex);
        }

    }
    
    public Partida[] cadastrarPartidasGrupo() throws SQLException{
        // um laço de repetição com indíce de 0 a 2 para gerar as três rodadas
        Partida[] p = new Partida[6];
        for(int i = 1; i < 8; i++){
            Equipe[] e = dao.carregarEquipesGrupo(i);
            System.out.println("========== " + e.length);
            System.out.println("========== " + e[0].getNome() + " ==== " + e[1].getNome());
            //Resultado[] resultados = new Resultado[6];
            p[0] = new Partida(e[0], e[1]);
            this.dao.salvarPartida(p[0]);
            //resultados[0] = new Resultado(partidas[0]);
            //this.dao.salvarResultado(resultados[0]);
            p[1] = new Partida(e[2], e[3]);
            this.dao.salvarPartida(p[1]);
            //resultados[1] = new Resultado(partidas[1]);
            p[2] = new Partida(e[0], e[2]);
            this.dao.salvarPartida(p[2]);
            //resultados[2] = new Resultado(partidas[2]);
            //this.dao.salvarResultado(resultados[2]);
            p[3] = new Partida(e[3], e[1]);
            this.dao.salvarPartida(p[3]);
            //resultados[3] = new Resultado(partidas[3]);
            //this.dao.salvarResultado(resultados[3]);
            p[4] = new Partida(e[3], e[0]);
            this.dao.salvarPartida(p[4]);
            //resultados[4] = new Resultado(partidas[4]);
            //this.dao.salvarResultado(resultados[4]);
            p[5] = new Partida(e[1], e[2]);
            this.dao.salvarPartida(p[5]);
            //resultados[5] = new Resultado(partidas[5]);
            //this.dao.salvarResultado(resultados[5]);
        }
        return this.partidas = p;
    }
    public Resultado[] simularResultadosGrupo() throws SQLException {
        // um laço de repetição com indíce de 0 a 2 para gerar as três rodadas
        Resultado[] r = new Resultado[6];

        for(int i = 0; i < 8; i++){
            // Equipe[] e = dao.carregarEquipesGrupo(i);
            r[0] = new Resultado(this.partidas[0]);
            this.dao.salvarResultado(r[0]);
            r[1] = new Resultado(this.partidas[1]);
            this.dao.salvarResultado(r[1]);
            r[2] = new Resultado(this.partidas[2]);
            this.dao.salvarResultado(r[2]);
            r[3] = new Resultado(this.partidas[3]);
            this.dao.salvarResultado(r[3]);
            r[4] = new Resultado(this.partidas[4]);
            this.dao.salvarResultado(r[4]);
            r[5] = new Resultado(this.partidas[5]);
            this.dao.salvarResultado(r[5]);
        }
        return this.resultados = r;
    }
}
