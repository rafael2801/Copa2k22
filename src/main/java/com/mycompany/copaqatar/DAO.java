/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.copaqatar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author supor
 */
public class DAO {
    private final ConnectionFactory factory = new ConnectionFactory();
    
    // requisições de Campeonato
    public Campeonato carregarCampeonato(int id) throws SQLException{
        String sql = "SELECT * FROM campeonatos WHERE id = ?";
        try(Connection conn = factory.obtemConexao()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                //System.out.print(rs.getString("nome"));
                while(rs.next()){
                    Campeonato c = new Campeonato();
                    c.setId(rs.getInt("id"));
                    c.setNome(rs.getString("nome"));
                    c.setSede(rs.getString("sede"));
                    c.setAno(rs.getInt("ano"));
                    return c;
                }
            }
        }catch(Exception e){
            // return false;
        }
        return null;
    }
    
    // requisições de Grupo
    public void salvarGrupo(Grupo grupo) throws SQLException{
        
    }
    
    public Grupo carregarGrupo(int grupoId) throws SQLException{
        String sql = "SELECT * FROM grupos WHERE id = ?";
        try(Connection conn = factory.obtemConexao()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, grupoId);
            try(ResultSet rs = ps.executeQuery()){
                //System.out.print(rs.getString("nome"));
                while(rs.next()){
                    Grupo g = new Grupo();
                    g.setId(rs.getInt("id"));
                    g.setNome(rs.getString("nome"));
                    return g;
                }
            }
        }catch(Exception e){
            // return false;
        }
        return null;
    }
    
    public Grupo[] carregarGrupos(){
        Grupo[] grupos = new Grupo[8];
        String sql = "SELECT * FROM grupos";
        try(Connection conn = factory.obtemConexao()){
            PreparedStatement ps = conn.prepareStatement(sql);
            try(ResultSet rs = ps.executeQuery()){
                int i = 0;
                while (rs.next()){
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    grupos[i] = new Grupo();
                    grupos[i].setId(id);
                    grupos[i].setNome(nome);
                    i += 1;
                }
                return grupos;
            }
        }catch(Exception e){
            return null;
        }
    }
    
    // requisições de Classificacao
    public void salvarClassificacao(Classificacao c) throws SQLException{
        String sql = "INSERT INTO classificacao (equipe_id, grupo_id, pontos, vitorias, empates, derrotas, gols_pro, gols_contra) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try(Connection conn = factory.obtemConexao()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, c.getEquipe().getId());
            ps.setInt(2, c.getGrupo().getId());
            ps.setInt(3, c.getPontos());
            ps.setInt(4, c.getVitorias());
            ps.setInt(5, c.getEmpates());
            ps.setInt(6, c.getDerrotas());
            ps.setInt(7, c.getGolsPro());
            ps.setInt(8, c.getGolsContra());
            ps.execute();
        }
    }
    
    public Classificacao carregarClassificacaoEquipe(Equipe equipe) throws SQLException{
        String sql = "SELECT * FROM classificacao WHERE equipe_id = ?";
        try(Connection conn = factory.obtemConexao()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, equipe.getId());
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    Classificacao c = new Classificacao();
                    c.setId(rs.getInt("id"));
                    c.setEquipe(this.carregarEquipe(rs.getInt("equipe_id")));
                    c.setGrupo(this.carregarGrupo(rs.getInt("grupo_id")));
                    // c.setAno(rs.getInt("ano"));
                    return c;
                }
            }
        }catch(Exception e){
            // return false;
        }
        return null;
    }
    
    // requisições de Equipe
    public void salvarEquipe(Equipe equipe) throws SQLException{
        String sql = "INSERT INTO equipes (nome) VALUES (?)";
        // tentando conexão com o BD para executar o comando sql
        try(Connection conn = factory.obtemConexao()){
            PreparedStatement ps = conn.prepareStatement(sql);
            // informando a váriavel que irá substituir o ? do comando sql
            ps.setString(1, equipe.getNome());
            ps.execute();
        }catch(Exception e){
        }
    }
    public Equipe carregarEquipe(int equipeId) throws SQLException{
        String sql = "SELECT * FROM equipes WHERE id = ?";
        try(Connection conn = factory.obtemConexao()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, equipeId);
            try(ResultSet rs = ps.executeQuery()){
                //System.out.print(rs.getString("nome"));
                while(rs.next()){
                    Equipe e = new Equipe(rs.getInt("id"), rs.getString("nome"));
//                    e.setId(rs.getInt("id"));
//                    e.setNome(rs.getString("nome"));
                    return e;
                }
            }
        }catch(Exception e){
            // return false;
        }
        return null;
    }
    
    public Equipe[] carregarEquipesGrupo(int grupoId) throws SQLException{
        Equipe[] equipes = new Equipe[4];
        String sql = "SELECT equipe_id FROM classificacao WHERE grupo_id = ?";
        try(Connection conn = factory.obtemConexao()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, grupoId);
            try(ResultSet rs = ps.executeQuery()){
                int i = 0;
                while(rs.next()){
                    Equipe e = this.carregarEquipe(rs.getInt("equipe_id"));
                    equipes[i] = e;
                    i++;
                }
                return equipes;
            }
        }
    }
    
    public boolean editarEquipe(int equipeId){
        String sql = "UPDATE equipes SET nome = ? WERE id = ?";
        try(Connection conn = factory.obtemConexao()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, equipeId);
            return ps.execute();
        }catch(Exception e){
            return false;
        }
    }
    public boolean excluirEquipe(int equipeId){
        String sql = "DELETE FROM equipes WERE id = ?";
        try(Connection conn = factory.obtemConexao()){
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, equipeId);
            return ps.execute();
        }catch(Exception e){
            return false;
        }
    }
    public Equipe[] carregarEquipesOficiais(){
        Equipe[] equipes = new Equipe[32];
        String sql = "SELECT * FROM equipes_oficiais";
        // tentando conexão com o BD para executar o comando sql
        try(Connection conn = factory.obtemConexao()){
            PreparedStatement ps = conn.prepareStatement(sql);
            try(ResultSet rs = ps.executeQuery()){
                int i = 0;
                while (rs.next()){
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    equipes[i] = new Equipe(id, nome);
                    //equipes[i].setId(id);
                    //equipes[i].setNome(nome);
                    i += 1;
                }
                return equipes;
            }
        }catch(Exception e){
            return null;
        }
    }
    
    // requisições Classificacao
    public Classificacao[] carregarClassificacao(){
        Classificacao[] cLista = new Classificacao[32];
        String sql = "SELECT * FROM classificacao";
        try(Connection conn = factory.obtemConexao()){
            PreparedStatement ps = conn.prepareStatement(sql);
            try(ResultSet rs = ps.executeQuery()){
                int i = 0;
                while (rs.next()){
                    Classificacao c = new Classificacao();
                    c.setId(rs.getInt("id"));
                    c.setEquipe(this.carregarEquipe(rs.getInt("equipe_id")));
                    c.setGrupo(this.carregarGrupo(rs.getInt("grupo_id")));
                    c.setPontos(rs.getInt("pontos"));
                    c.setVitorias(rs.getInt("vitorias"));
                    c.setEmpates(rs.getInt("empates"));
                    c.setDerrotas(rs.getInt("derrotas"));
                    c.setGolsPro(rs.getInt("gols_pro"));
                    c.setGolsContra(rs.getInt("gols_contra"));
                    cLista[i] = c;
                    //equipes[i].setId(id);
                    //equipes[i].setNome(nome);
                    i += 1;
                }
                return cLista;
            }
        }catch(Exception e){
            return null;
        }
    }
}
