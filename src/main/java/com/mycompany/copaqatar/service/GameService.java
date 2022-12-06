package com.mycompany.copaqatar.service;

import com.mycompany.copaqatar.*;
import com.mycompany.copaqatar.models.Game;
import com.mycompany.copaqatar.models.Group;
import com.mycompany.copaqatar.models.Time;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GameService {

    private DAO dao = new DAO();
    private Campeonato campeonato = new Campeonato();
    private final ConnectionFactory factory = new ConnectionFactory();


    public String getWinner () {
        String winner = "";
        try (Connection connection = factory.obtemConexao()) {
            String sql = "select * from games where fase = 'Final'";
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int placarA = rs.getInt("placar_a");
                int placarB = rs.getInt("placar_b");
                String timeA = rs.getString("time_a");
                String timeB = rs.getString("time_b");

                if(placarA == placarB) {
                    JOptionPane.showMessageDialog(null, timeA + " empatou com " + timeB);
                    JOptionPane.showMessageDialog(null, "Vamos aos penaltis!");
                    int[] points = this.desempate();

                    if(points[0] > points[1]) {
                        JOptionPane.showMessageDialog(null, timeA + " ganhou!");
                        winner = timeA ;
                    } else {
                        JOptionPane.showMessageDialog(null, timeB + " ganhou!");
                        winner =  timeB;
                    }
                } else if (placarA > placarB) {
                    winner =  timeA;
                } else {
                    winner =  timeB;
                }

            }
            return winner;
        } catch (SQLException e) {
            System.out.println("AAAAA ERRO NO FINAL PQQQQQ " + e);
            throw new RuntimeException(e);
        }
    }
    public GameService() {
    }

    public void saveTeam (String name)  {
        try (Connection connection = factory.obtemConexao()) {
//            dao.salvarEquipe(new Equipe(name));
            String sql = "INSERT INTO times (nome) VALUES (?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, name);
            ps.execute();

        } catch (SQLException e) {
            System.out.println("Err on create team: " + e);
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

        ArrayList<Group> groups = this.getAllGroups();

        ArrayList<String> arr = new ArrayList<>();

        for(Group g : groups){
            for (Time t : g.getTimes()) {
                arr.add(t.getNome() + "  |  " + g.getNome());
            }
        }

        return arr.toArray(new String[0]);
    }

    public String[] getGroups () {

        ArrayList<Group> groups = this.getAllGroups();
        ArrayList<String> arr = new ArrayList<>();

        for (Group group : groups) {
            arr.add(group.getNome());
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
        dao.setOficialTeams(); //salva todos os times da copa

        dao.createGroups(); //cria 8 grupos
        campeonato.setEquipes();
        campeonato.setGrupos();
        campeonato.cadastrarGruposOficiais();
    }

    public void deleteAll () {
        try (Connection connection = factory.obtemConexao()) {
            String sqlDeleteGames = "delete from games limit 1000";
            PreparedStatement deleteGamesPs = connection.prepareStatement(sqlDeleteGames);

            deleteGamesPs.execute();

            String sqlDeleteTimes = "delete from times limit 1000";
            PreparedStatement deleteTimesPs = connection.prepareStatement(sqlDeleteTimes);

            deleteTimesPs.execute();

            String sqlDeleteGrupos = "delete from grupos limit 1000";
            PreparedStatement deleteGruposPs = connection.prepareStatement(sqlDeleteGrupos);

            deleteGruposPs.execute();
        } catch (SQLException e) {
            System.out.println("Err on delete all: " + e);
            throw new RuntimeException(e);
        }
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

    public void saveGame (Game game) {
        try (Connection connection = factory.obtemConexao()) {
            String sql = "insert into games (fase, time_a, time_b, placar_a, placar_b, group_id) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            // informando a váriavel que irá substituir o ? do comando sql
            ps.setString(1, game.getFase());
            ps.setString(2, game.getTimeA());
            ps.setString(3, game.getTimeB());
            ps.setInt(4, game.getPlacarTimeA());
            ps.setInt(5, game.getPlacarTimeB());
            ps.setInt(6, game.getGroup_id());
            ps.execute();

        } catch (SQLException e) {
            System.out.println("Err on save game: " + e);
            throw new RuntimeException(e);
        }
    }

    public void simulateGroupsStep (Group group, String step) {
        for (int i = 0; i < group.getTimes().size(); i++) {
            for (int j = i + 1; j < group.getTimes().size(); j++) {
                Game game = new Game();
                game.setFase(step);
                game.setTimeA(group.getTimes().get(i).getNome()); // time A name
                game.setTimeB(group.getTimes().get(j).getNome()); // time B name
                game.setPlacarTimeA(this.getRandomNumber());
                game.setPlacarTimeB(this.getRandomNumber());
                game.setGroup_id(group.getId());

                this.saveGame(game);

            }
        }
    }

    public void simulateOctavesStep (ArrayList<Group> groups) {

        ArrayList<Time> times = new ArrayList<>();

        for (Group g : groups) {
            Time firstClassified = new Time("null");
            Time secondClassified = new Time("null");

            for (Time t : g.getTimes()) { //pegando primeiro classificado do grupo!
                if(t.getPontos() > firstClassified.getPontos()) {
                    firstClassified.setPontos(t.getPontos());
                    firstClassified.setNome(t.getNome());
                    firstClassified.setId(t.getId());
                }
            }

            for (Time t : g.getTimes()) {//pegando  classificado do grupo!
                if(t.getPontos() > secondClassified.getPontos() && t.getId() != firstClassified.getId()) {
                    secondClassified.setPontos(t.getPontos());
                    secondClassified.setNome(t.getNome());
                    secondClassified.setId(t.getId());
                }
            }

            times.add(firstClassified);
            times.add(secondClassified);

        }

        ArrayList<Group> sGroups = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Group newGroup = new Group();

            for (int j = 0; j < 4; j++) {
            newGroup.addTeam(times.get((i*4) + j));
            }

            sGroups.add(newGroup);
        }

        this.createOctavesGroups();

        this.createOctavesTeams(times);

        ArrayList<Group> octaveGroups = this.getOctaveGroups();

        for (Group octaveGroup : octaveGroups) {

            Game game = new Game();
            game.setFase("Oitavas");
            game.setTimeA(octaveGroup.getTimes().get(0).getNome()); // time A name
            game.setTimeB(octaveGroup.getTimes().get(2).getNome()); // time B name
            game.setPlacarTimeA(this.getRandomNumber());
            game.setPlacarTimeB(this.getRandomNumber());
            game.setGroup_id(octaveGroup.getId());

            this.saveGame(game);

            Game secondGame = new Game();
            secondGame.setFase("Oitavas");
            secondGame.setTimeA(octaveGroup.getTimes().get(1).getNome()); // time A name
            secondGame.setTimeB(octaveGroup.getTimes().get(3).getNome()); // time B name
            secondGame.setPlacarTimeA(this.getRandomNumber());
            secondGame.setPlacarTimeB(this.getRandomNumber());
            secondGame.setGroup_id(octaveGroup.getId());

            this.saveGame(secondGame);

//            for (int i = 0; i < octaveGroup.getTimes().size(); i++) {
//                for (int j = i + 1; j < octaveGroup.getTimes().size(); j++) {
//                    Game game = new Game();
//                    game.setFase("Oitavas");
//                    game.setTimeA(octaveGroup.getTimes().get(i).getNome()); // time A name
//                    game.setTimeB(octaveGroup.getTimes().get(j).getNome()); // time B name
//                    game.setPlacarTimeA(this.getRandomNumber());
//                    game.setPlacarTimeB(this.getRandomNumber());
//                    game.setGroup_id(octaveGroup.getId());
//
//                    this.saveGame(game);
//
//                }
//            }
        }

    }


    public void simulateQuartasStep (ArrayList<Group> groups) {

        ArrayList<Time> times = new ArrayList<>();

        for (Group g : groups) {
            Time firstClassified = new Time("null");
            Time secondClassified = new Time("null");

            for (Time t : g.getTimes()) { //pegando primeiro classificado do grupo!
                if(t.getPontos() > firstClassified.getPontos()) {
                    firstClassified.setPontos(t.getPontos());
                    firstClassified.setNome(t.getNome());
                    firstClassified.setId(t.getId());
                }
            }

            for (Time t : g.getTimes()) {//pegando  classificado do grupo!
                if(t.getPontos() > secondClassified.getPontos() && t.getId() != firstClassified.getId()) {
                    secondClassified.setPontos(t.getPontos());
                    secondClassified.setNome(t.getNome());
                    secondClassified.setId(t.getId());
                }
            }

            times.add(firstClassified);
            times.add(secondClassified);

        }

        this.createQuartasGroups();

        this.createQuartasTeams(times);

        ArrayList<Group> quartasGroups = this.getQuartasGroups();

        for (Group group : quartasGroups) {
            for (int i = 0; i < group.getTimes().size(); i++) {
                for (int j = i + 1; j < group.getTimes().size(); j++) {
                    Game game = new Game();
                    game.setFase("Quartas");
                    game.setTimeA(group.getTimes().get(i).getNome()); // time A name
                    game.setTimeB(group.getTimes().get(j).getNome()); // time B name
                    game.setPlacarTimeA(this.getRandomNumber());
                    game.setPlacarTimeB(this.getRandomNumber());
                    game.setGroup_id(group.getId());

                    this.saveGame(game);

                }
            }
        }

    }

    public void simulateSemi (ArrayList<Group> groups) {
        ArrayList<Time> classifieds = new ArrayList<>();
        try (Connection connection = factory.obtemConexao()) {

            String sql = "select * from games where fase like '%Quartas%'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<String> games = new ArrayList<>();
            while (rs.next()) {
                int pontosTimeB = rs.getInt("placar_b");
                int pontosTimeA = rs.getInt("placar_a");
                String timeA = rs.getString("time_a");
                String timeB = rs.getString("time_b");


                if(pontosTimeA == pontosTimeB) {
                    JOptionPane.showMessageDialog(null, timeA + " empatou com " + timeB);
                    JOptionPane.showMessageDialog(null, "Vamos aos penaltis!");
                    int[] points = this.desempate();

                    if(points[0] > points[1]) {
                        Time classified = new Time(timeA);
                        classified.setPontos(pontosTimeA);
                        classifieds.add(classified);
                        JOptionPane.showMessageDialog(null, timeA + " ganhou!");
                    } else {
                        Time classified = new Time(timeB);
                        classified.setPontos(pontosTimeB);
                        classifieds.add(classified);
                        JOptionPane.showMessageDialog(null, timeB + " ganhou!");
                    }


                } else if (pontosTimeA > pontosTimeB) {
                    Time classified = new Time(timeA);
                    classified.setPontos(pontosTimeA);
                    classifieds.add(classified);
                } else if (pontosTimeB > pontosTimeA) {
                    Time classified = new Time(timeB);
                    classified.setPontos(pontosTimeB);
                    classifieds.add(classified);
                }
                String gameResult = rs.getString("time_A") + " " + rs.getInt("placar_a") + " X " + rs.getInt("placar_b") + " " + rs.getString("time_b");
                games.add(gameResult);
            }

            this.makeSemiGames(classifieds);

//            return games;

        } catch (SQLException e) {
            System.out.println("Err on calculate octave points: " + e);
            throw new RuntimeException(e);
        }
    }

    public void makeSemiGames (ArrayList<Time> classifieds) {
        Game game = new Game();
        game.setFase("Semi");
        game.setTimeA(classifieds.get(0).getNome()); // time A name
        game.setTimeB(classifieds.get(1).getNome()); // time B name
        game.setPlacarTimeA(this.getRandomNumber());
        game.setPlacarTimeB(this.getRandomNumber());
        game.setGroup_id(1);
        this.saveGame(game);


        Game secondGame = new Game();
        secondGame.setFase("Semi");
        secondGame.setTimeA(classifieds.get(2).getNome()); // time A name
        secondGame.setTimeB(classifieds.get(3).getNome()); // time B name
        secondGame.setPlacarTimeA(this.getRandomNumber());
        secondGame.setPlacarTimeB(this.getRandomNumber());
        secondGame.setGroup_id(1);


        this.saveGame(secondGame);

    }

    public ArrayList<Time> getAllFinalists () {

        ArrayList<Time> finalists = new ArrayList<>();

        try (Connection connection = factory.obtemConexao()) {

            String sql = "select * from games where fase = 'Semi'";

            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Time finalist = new Time(rs.getString("time_a"));
                finalists.add(finalist);
            }

            return finalists;

        } catch (SQLException e) {
            System.out.println("Err on get finalists: " + e);
            throw new RuntimeException(e);
        }
    }

    public void simulateFinal (ArrayList<Time> finalists) {
        try  {
            Game game = new Game();
            game.setFase("Final");
            game.setTimeA(finalists.get(0).getNome()); // time A name
            game.setTimeB(finalists.get(1).getNome()); // time B name
            int placarA = this.getRandomNumber();
            int placarB = this.getRandomNumber();
            if(placarA == placarB) {
                placarA = this.getRandomNumber();
                placarB = this.getRandomNumber();

                if (placarA == placarB) placarB = (placarA - 1) < 0 ? 0 : (placarA - 1);
            }
            game.setPlacarTimeA(placarA);
            game.setPlacarTimeB(placarB);
            game.setGroup_id(1);
            this.saveGame(game);

        } catch (Exception e) {
            System.out.println("Err on simulate finalists: " + e);
            throw new RuntimeException(e);
        }
    }
    public int[] desempate () {
        int[] numbers = {this.getRandomNumber(), this.getRandomNumber()};
        return numbers;
    }
    public void saveQuartasGame (Time timeA, Time timeB) {
        Game game = new Game();
        game.setFase("Quartas");
        game.setTimeA(timeA.getNome()); // time A name
        game.setTimeB(timeB.getNome()); // time B name
        game.setPlacarTimeA(this.getRandomNumber());
        game.setPlacarTimeB(this.getRandomNumber());
        game.setGroup_id(1);

        this.saveGame(game);
    }
    private Group createOctavesGroup () {

        try (Connection connection = factory.obtemConexao()){
            String createSql = "insert into grupos (nome) values (?)";
            PreparedStatement createPs = connection.prepareStatement(createSql);

            createPs.setString(1, "Quartas");
            createPs.execute();

            String getSql = "select * from grupos where nome = 'Oitavas'";
            PreparedStatement getPs = connection.prepareStatement(getSql);

            ResultSet rs = getPs.executeQuery();
            Group octavesGroup = new Group();

            while (rs.next()) {
                octavesGroup.setId(rs.getInt("id"));
                octavesGroup.setNome(rs.getString("nome"));

            }

            return octavesGroup;

        } catch (SQLException e) {
            System.out.println("Err on create octaves group: " + e);
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Group> getAllGroups () {
        try (Connection connection = factory.obtemConexao()){
            String sql = "SELECT * from grupos;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            ArrayList<Group> groups = new ArrayList<>();

            while (rs.next()){
                Group newGroup = new Group();
                newGroup.setId(rs.getInt("id"));
                newGroup.setNome(rs.getString("nome"));

                String teamsSql = "select * from times t inner join grupos g on t.grupos_id = g.id where grupos_id = " + newGroup.getId();

                PreparedStatement teamPS = connection.prepareStatement(teamsSql);

                ResultSet rsTeam = teamPS.executeQuery();

                while (rsTeam.next()) {
                    Time newTeam = new Time(rsTeam.getString("nome"));
                    newTeam.setId(rsTeam.getInt("id"));

                    newGroup.addTeam(newTeam);
                }

                groups.add(newGroup);
            }

            return groups;
        } catch (SQLException e) {
            System.out.println("Err on getAll groups: " + e);
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Group> getAllOctaveGroups () {
        try (Connection connection = factory.obtemConexao()){
            String sql = "SELECT * from grupos where nome like '%Oitavas%'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            ArrayList<Group> groups = new ArrayList<>();

            while (rs.next()){
                Group newGroup = new Group();
                newGroup.setId(rs.getInt("id"));
                newGroup.setNome(rs.getString("nome"));

                String teamsSql = "select * from times t inner join grupos g on t.grupos_id = g.id where grupos_id = " + newGroup.getId();

                PreparedStatement teamPS = connection.prepareStatement(teamsSql);

                ResultSet rsTeam = teamPS.executeQuery();

                while (rsTeam.next()) {
                    Time newTeam = new Time(rsTeam.getString("nome"));
                    newTeam.setId(rsTeam.getInt("id"));

                    newGroup.addTeam(newTeam);
                }

                groups.add(newGroup);
            }

            return groups;
        } catch (SQLException e) {
            System.out.println("Err on getAll groups: " + e);
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Group> getAllQuartasGroups () {
        try (Connection connection = factory.obtemConexao()){
            String sql = "SELECT * from grupos where nome like '%Quartas%'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            ArrayList<Group> groups = new ArrayList<>();

            while (rs.next()){
                Group newGroup = new Group();
                newGroup.setId(rs.getInt("id"));
                newGroup.setNome(rs.getString("nome"));

                String teamsSql = "select * from times t inner join grupos g on t.grupos_id = g.id where grupos_id = " + newGroup.getId();

                PreparedStatement teamPS = connection.prepareStatement(teamsSql);

                ResultSet rsTeam = teamPS.executeQuery();

                while (rsTeam.next()) {
                    Time newTeam = new Time(rsTeam.getString("nome"));
                    newTeam.setId(rsTeam.getInt("id"));

                    newGroup.addTeam(newTeam);
                }

                groups.add(newGroup);
            }

            return groups;
        } catch (SQLException e) {
            System.out.println("Err on getAll groups: " + e);
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Group> calculateGroupsStepWinner (ArrayList<Group> groups) {
        try (Connection connection = factory.obtemConexao()) {
                for (Group group : groups) {
                    String sql = "select * from games where group_id = " + group.getId();
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        for (Time time : group.getTimes()) {
                            if(time.getNome().equals(rs.getString("time_A"))) {
                                time.addPontos(rs.getInt("placar_a"));
                            } else if (time.getNome().equals(rs.getString("time_b"))) {
                                time.addPontos(rs.getInt("placar_b"));
                            }
                        }
                    }
                }

                return groups;
        } catch (SQLException e) {
            System.out.println("Err on calculate points: " + e);
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Group> calculateOctavesWinner (ArrayList<Group> groups) {
        try (Connection connection = factory.obtemConexao()) {
            for (Group group : groups) {
                String sql = "select * from games where group_id = " + group.getId();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    for (Time time : group.getTimes()) {
                        if(time.getNome().equals(rs.getString("time_A"))) {
                            time.addPontos(rs.getInt("placar_a"));
                        } else if (time.getNome().equals(rs.getString("time_b"))) {
                            time.addPontos(rs.getInt("placar_b"));
                        }
                    }
                }
            }

            return groups;
        } catch (SQLException e) {
            System.out.println("Err on calculate points: " + e);
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getGame (int index) {
        try (Connection connection = factory.obtemConexao()) {

            String sql = "select * from games where group_id = " + index;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<String> games = new ArrayList<>();
            while (rs.next()) {
                String gameResult = rs.getString("time_A") + " " + rs.getInt("placar_a") + " X " + rs.getInt("placar_b") + " " + rs.getString("time_b");
                games.add(gameResult);
            }

            return games;
        } catch (SQLException e) {
            System.out.println("Err on calculate points: " + e);
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getOctaveGame (int index) {
        try (Connection connection = factory.obtemConexao()) {

            String sql = "select * from games where fase like '%Oitavas%' and group_id = " + index;
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<String> games = new ArrayList<>();
            while (rs.next()) {
                String gameResult = rs.getString("time_A") + " " + rs.getInt("placar_a") + " X " + rs.getInt("placar_b") + " " + rs.getString("time_b");
                games.add(gameResult);
            }

            return games;
        } catch (SQLException e) {
            System.out.println("Err on calculate octave points: " + e);
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getQuartasGame () {
        try (Connection connection = factory.obtemConexao()) {

            String sql = "select * from games where fase like '%Quartas%'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ArrayList<String> games = new ArrayList<>();
            while (rs.next()) {
                String gameResult = rs.getString("time_A") + " " + rs.getInt("placar_a") + " X " + rs.getInt("placar_b") + " " + rs.getString("time_b");
                games.add(gameResult);
            }

            return games;
        } catch (SQLException e) {
            System.out.println("Err on calculate octave points: " + e);
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getOctaveGames () {
        try (Connection connection = factory.obtemConexao()) {
            String sql = "select * from games where fase = 'Oitavas'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            Group group = new Group();
            while (rs.next()) {
                group.setId(rs.getInt("id"));
                group.setNome(rs.getString("nome"));
            }

            return this.getGame(group.getId());
        } catch (SQLException e) {
            System.out.println("Err on get octaves group: " + e);
            throw new RuntimeException(e);
        }
    }
    public void createDefaultGroups () {
        String[] abc = {"A", "B", "C", "D", "E", "F", "G", "H"};

        try (Connection connection = factory.obtemConexao()) {
            for (String a : abc){
                String sql = "insert into grupos (nome) values (?)";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, "Grupo " + a);
                ps.execute();
            }
        } catch (SQLException e) {
            System.out.println("Err on create default teams: " + e);
            throw new RuntimeException(e);
        }
    }
    public void createDefaultTeams () {
        String[] mockTeams = {
                "Qatar",
                "Equador",
                "Senegal",
                "Holanda",
                "Inglaterra",
                "Irã",
                "Estados Unidos",
                "País de Gales",
                "Argentina",
                "Arábia Saudita",
                "México",
                "Polônia",
                "França",
                "Austrália",
                "Dinamarca",
                "Tunísia",
                "Espanha",
                "Costa Rica",
                "Alemanha",
                "Japão",
                "Bélgica",
                "Canada",
                "Marrocos",
                "Croácia",
                "Brasil",
                "Sérvia",
                "Suiça",
                "Camarões",
                "Portugal",
                "Gana",
                "Uruguai",
                "Coréia do Sul"
        };

        try(Connection conn = factory.obtemConexao()){

            for (int i = 1; i < 9; i++) {
                for (int j = 0; j < 4; j++) {
                    String sql = "INSERT INTO times (nome, grupos_id) VALUES (?, ?)";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, mockTeams[((i - 1) * 4) + j]);
                    ps.setInt(2, i);
                    ps.execute();
                }
            }

        }catch(Exception e){
            System.out.println("err on create default teams: " + e);
        }
    }

    public void createOctavesTeams (ArrayList<Time> octaveTeams) {

        try(Connection conn = factory.obtemConexao()){

            for (int i = 1; i < 5; i++) {
                for (int j = 0; j < 4; j++) {
                    String sql = "INSERT INTO times (nome, grupos_id) VALUES (?, ?)";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, octaveTeams.get(((i - 1) * 4) + j).getNome());
                    ps.setInt(2, (i + 10));
                    ps.execute();
                }
            }

        }catch(Exception e){
            System.out.println("err on create octaves teams 222222: " + e);
        }
    }

    public void createQuartasTeams (ArrayList<Time> quartasTeams) {

        try(Connection conn = factory.obtemConexao()){

            for (int i = 1; i < 5; i++) {
                for (int j = 0; j < 2; j++) {
                    String sql = "INSERT INTO times (nome, grupos_id) VALUES (?, ?)";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, quartasTeams.get(((i - 1) * 2) + j).getNome());
                    ps.setInt(2, (i + 20));
                    ps.execute();
                }
            }

        }catch(Exception e){
            System.out.println("err on create quartas teams 222222: " + e);
        }
    }

    public void createOctavesGroups () {
        String[] abc = {"A", "B", "C", "D"};

        try(Connection conn = factory.obtemConexao()){

            for (String str : abc) {

            }

            for (int i = 1; i < 5; i++) {
                    String sql = "INSERT INTO grupos (id, nome) VALUES (?, ?)";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setInt(1, (i + 10));
                    ps.setString(2, "Oitavas " + abc[(i-1)]);
                    ps.execute();
            }

        }catch(Exception e){
            System.out.println("err on create octaves groups 11111: " + e);
        }
    }

    public void createQuartasGroups () {
        String[] abc = {"A", "B", "C", "D"};

        try(Connection conn = factory.obtemConexao()){

            for (int i = 1; i < 5; i++) {
                String sql = "INSERT INTO grupos (id, nome) VALUES (?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, (i + 20));
                ps.setString(2, "Quartas " + abc[(i-1)]);
                ps.execute();
            }

        }catch(Exception e){
            System.out.println("err on create quartas groups 11111: " + e);
        }
    }
    public ArrayList<Group> getOctaveGroups () {
        ArrayList<Group> arr = new ArrayList<>();

        try(Connection conn = factory.obtemConexao()){

            for (int i = 1; i < 5; i++) {

                String sql = "select * from grupos where id = " + (i + 10);
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                Group newGroup = new Group();
                while (rs.next()) {
                    newGroup.setNome(rs.getString("nome"));
                    newGroup.setId(rs.getInt("id"));

                    String teamSql = "select t.nome as nome , t.id as id from grupos g inner join times t on t.grupos_id = g.id where grupos_id = " + rs.getString("id") ;
                    PreparedStatement teamPs = conn.prepareStatement(teamSql);
                    ResultSet rsTeam = teamPs.executeQuery();

                    while (rsTeam.next()) {
                        Time newTime = new Time(rsTeam.getString("nome"));
                        newTime.setId(rsTeam.getInt("id"));
                        newGroup.addTeam(newTime);
                    }
                }

                arr.add(newGroup);
            }

            return arr;

        }catch(Exception e){
            System.out.println("err on create octaves groups 2233r523452345: " + e);
        }
        return arr;
    }

    public ArrayList<Group> getQuartasGroups () {
        ArrayList<Group> arr = new ArrayList<>();

        try(Connection conn = factory.obtemConexao()){

            for (int i = 1; i < 5; i++) {

                String sql = "select * from grupos where id = " + (i + 20);
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                Group newGroup = new Group();
                while (rs.next()) {
                    newGroup.setNome(rs.getString("nome"));
                    newGroup.setId(rs.getInt("id"));

                    String teamSql = "select t.nome as nome , t.id as id from grupos g inner join times t on t.grupos_id = g.id where grupos_id = " + rs.getString("id") ;
                    PreparedStatement teamPs = conn.prepareStatement(teamSql);
                    ResultSet rsTeam = teamPs.executeQuery();

                    while (rsTeam.next()) {
                        Time newTime = new Time(rsTeam.getString("nome"));
                        newTime.setId(rsTeam.getInt("id"));
                        newGroup.addTeam(newTime);
                    }
                }

                arr.add(newGroup);
            }

            return arr;

        }catch(Exception e){
            System.out.println("err on create quartas groups 2233r523452345: " + e);
        }
        return arr;
    }

    public void createTeams (ArrayList<Time> teams) {

        try ( Connection connection = factory.obtemConexao()) {

            for (int i = 1; i < 9; i++) {
                for (int j = 0; j < 4; j++) {
                    String sql = "INSERT INTO times (nome, grupos_id) VALUES (?, ?)";
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setString(1, teams.get(((i - 1) * 4) + j).getNome());
                    ps.setInt(2, i);
                    ps.execute();
                }
            }

        } catch (SQLException e) {
            System.out.println("Err on create my teams: " + e);
            throw new RuntimeException(e);
        }

    }
    private int getRandomNumber () {
        return (int)(Math.random() * 4);
    }
}
