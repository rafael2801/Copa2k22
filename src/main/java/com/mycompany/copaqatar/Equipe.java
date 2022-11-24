/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.copaqatar;

import com.mycompany.copaqatar.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author supor
 */
public class Equipe {
    private String nome;
    public DatabaseConnection factory = new DatabaseConnection();
    
    public Equipe(String nome) throws SQLException{
        this.nome = nome;
        // this.salvarEquipe();
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return this.nome;
    }
    public void salvarEquipe() throws SQLException{
        // definindo comando sql para inserir novo registro na tabela equipes com o nome definido por uma variável no lugar de ?
        String sql = "INSERT INTO equipes (nome) VALUES (?)";
        // tentando conexão com o BD para executar o comando sql
        try(Connection conn = factory.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            // informando a váriavel que irá substituir o ? do comando sql
            ps.setString(1, this.getNome());
            ps.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public Equipe[] carregarEquipes(){
        Equipe[] equipes = new Equipe[32];
        String sql = "SELECT * FROM equipes";
        // tentando conexão com o BD para executar o comando sql
        try(Connection conn = factory.getConnection()){
            PreparedStatement ps = conn.prepareStatement(sql);
            try(ResultSet rs = ps.executeQuery()){
                int i = 0;
                while (rs.next()){
                    String nome = rs.getString("nome");
                    equipes[i] = new Equipe(nome);
                    i += 1;
                }
                return equipes;
                //return rs;
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
