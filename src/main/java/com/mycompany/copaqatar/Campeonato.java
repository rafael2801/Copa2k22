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
    private ConnectionFactory factory = new ConnectionFactory();
    private String nome;
    private Partida[] partidas = new Partida[48];
    public Equipe[] equipes = new Equipe[32];
    public Grupo[] grupos = new Grupo[8];
    
    public Campeonato(String nome){
        // buscar atributos no BD
        // this.nome = nome;
    }
    
    public Equipe[] gerarEquipesOficiais() throws SQLException{
        this.equipes[0] = new Equipe("Qatar");
        this.equipes[1] = new Equipe("Equador");
        this.equipes[2] = new Equipe("Senegal");
        this.equipes[3] = new Equipe("Holanda");
        this.equipes[4] = new Equipe("Inglaterra");
        this.equipes[5] = new Equipe("Irã");
        this.equipes[6] = new Equipe("Estados Unidos");
        this.equipes[7] = new Equipe("País de Gales");
        this.equipes[8] = new Equipe("Argentina");
        this.equipes[9] = new Equipe("Arábia Saudita");
        this.equipes[10] = new Equipe("México");
        this.equipes[11] = new Equipe("Polônia");
        this.equipes[12] = new Equipe("França");
        this.equipes[13] = new Equipe("Austrália");
        this.equipes[14] = new Equipe("Dinamarca");
        this.equipes[15] = new Equipe("Tunísia");
        this.equipes[16] = new Equipe("Espanha");
        this.equipes[17] = new Equipe("Costa Rica");
        this.equipes[18] = new Equipe("Alemanha");
        this.equipes[19] = new Equipe("Japão");
        this.equipes[20] = new Equipe("Bélgica");
        this.equipes[21] = new Equipe("Canada");
        this.equipes[22] = new Equipe("Marrocos");
        this.equipes[23] = new Equipe("Croácia");
        this.equipes[24] = new Equipe("Brasil");
        this.equipes[25] = new Equipe("Sérvia");
        this.equipes[26] = new Equipe("Suiça");
        this.equipes[27] = new Equipe("Camarões");
        this.equipes[28] = new Equipe("Portugal");
        this.equipes[29] = new Equipe("Gana");
        this.equipes[30] = new Equipe("Uruguai");
        this.equipes[31] = new Equipe("Coréia do Sul");
        return equipes;
    }
    
    public Equipe[] carregarEquipes(){
        String sql = "SELECT * FROM equipes";
        // tentando conexão com o BD para executar o comando sql
        try(Connection conn = factory.obtemConexao()){
            PreparedStatement ps = conn.prepareStatement(sql);
            try(ResultSet rs = ps.executeQuery()){
                // definindo comando sql para inserir novo registro na tabela equipes com o nome definido por uma variável no lugar de ?
                Equipe[] equipes = new Equipe[32];
                int i = 0;
                while (rs.next()){
                    String nome = rs.getString("nome");
                    equipes[i] = new Equipe(nome);
                    i += 1;
                }
                return this.equipes = equipes;
                //return rs;
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public Grupo[] gerarGruposOficiais(Equipe[] equipes){
        Grupo[] grupos = new Grupo[8];
        char[] nomesGrupos = new char[8];
        nomesGrupos[0] = 'A';
        nomesGrupos[1] = 'B';
        nomesGrupos[2] = 'C';
        nomesGrupos[3] = 'D';
        nomesGrupos[4] = 'E';
        nomesGrupos[5] = 'F';
        nomesGrupos[6] = 'G';
        nomesGrupos[7] = 'H';
        int i = 0;
        for(int x = 0; x < 8; x++){
                Grupo grupo = new Grupo(nomesGrupos[x], equipes[i], equipes[i+1], equipes[i+2], equipes[i+3]);
                grupos[x] = grupo;
            i += 4;
            }
        return this.grupos = grupos;
    } 
}
