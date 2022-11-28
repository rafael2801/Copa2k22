package com.mycompany.copaqatar.service;

import com.mycompany.copaqatar.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class GameService {

    private DAO dao = new DAO();
    private Campeonato campeonato = new Campeonato();

    public GameService() {
    }

    public void saveTeam (String name)  {
        try {
            dao.salvarEquipe(new Equipe(name));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> gameResult () {
        ArrayList<String> response = new ArrayList<>();
        campeonato.setResultados();
        Resultado[] res = campeonato.getResultados();

        for (Resultado result : res) {
            System.out.println(result);
            String gameString = "" + result.getPartida().getEquipeA().getNome() + " " + result.getPlacarEquipeA() + " X " + result.getPlacarEquipeB() + " " + result.getPartida().getEquipeB().getNome();
            response.add(gameString);
        }

        return response;
    }

    public String[] getTeams () {

        ArrayList<String> arr = new ArrayList<>();

        for(Equipe equipe : dao.carregarEquipes()){
            arr.add(equipe.getNome());
        }

        return arr.toArray(new String[0]);
    }

    public String[] getGroups () {
        Grupo[] groups = dao.carregarGrupos();

        ArrayList<String> arr = new ArrayList<>();

        if (groups == null) return arr.toArray(new String[0]);

        for (Grupo grupo : groups) {
            arr.add("Grupo " + grupo.getNome());
        }

        return arr.toArray(new String[0]);
    }
    public void sortGroups () {
        dao.createGroups(); //cria 8 grupos
        campeonato.setEquipes();
        campeonato.setGrupos();

        campeonato.cadastrarGruposOficiais();

    }

    public void setOficialTeams () {
        dao.setOficialTeams();

        dao.createGroups(); //cria 8 grupos
        campeonato.setEquipes();
        campeonato.setGrupos();
        campeonato.cadastrarGruposOficiais();
    }

    public void deleteAll () {
        dao.deleteAll();
    }

    public void play () {

        try {
            campeonato.setGrupos();
            campeonato.setEquipes();
            campeonato.setClassifificacao();

            campeonato.cadastrarGruposOficiais();
            campeonato.cadastrarPartidasGrupo();
            campeonato.setPartidas();
            campeonato.simularResultadosGrupo();
            campeonato.setResultados();

        } catch (SQLException e) {
            System.out.println("aaaaaaaaa: " + e);
            throw new RuntimeException(e);
        }
    }
}
