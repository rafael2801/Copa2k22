package com.mycompany.copaqatar.service;

import com.mycompany.copaqatar.DAO;
import com.mycompany.copaqatar.Equipe;

import java.sql.SQLException;
import java.util.ArrayList;

public class GameService {

    private DAO dao = new DAO();

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

        response.add("Brasil 2 X 0 Sérvia");
        response.add("Brasil 2 X 0 Sérvia");
        response.add("Brasil 2 X 0 Sérvia");
        response.add("Brasil 2 X 0 Sérvia");
        response.add("Brasil 2 X 0 Sérvia");


        return response;
    }
}
