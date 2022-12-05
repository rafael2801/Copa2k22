package com.mycompany.copaqatar;

import com.mycompany.copaqatar.models.Group;
import com.mycompany.copaqatar.models.Time;
import com.mycompany.copaqatar.models.User;
import com.mycompany.copaqatar.service.GameService;
import com.mycompany.copaqatar.views.Home;
import com.mycompany.copaqatar.views.Login;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {

        User user = new User();

        new Login(user).makeFrame();

        String[] octaveTeams = {"Qatar",
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
                "Tunísia",};

//        System.out.println(times.stream().map(String::valueOf).toArray(String[]::new));

//        for (int i = 1; i < 5; i++) {
//            for (int j = 0; j < 4; j++) {
//                System.out.println(octaveTeams[((i - 1) * 4) + j] + " aaa : " + (i + 10));
//
//            }
//        }
    }

        String[] octaveTeams = {"Qatar",
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
                "Tunísia",};
//
//        for (int i = 1; i < 5; i++) {
//            for (int j = 0; j < 4; j++) {
//                System.out.println(octaveTeams[((i - 1) * 4) + j] + " com o grupo: " + (i + 10));
//            }
//        }
//    }

//        Time timeA = new Time("Brasil");
//        Time timeB = new Time("Suiça");
//        Time timeC = new Time("Servia");
//        Time timeD = new Time("Camaroes");
//
//        ArrayList<Time> times = new ArrayList<>();
//
//        times.add(timeA);
//        times.add(timeB);
//        times.add(timeC);
//        times.add(timeD);
//
//        Group group = new Group();
//        group.setTimes(times);
//        group.setId(1);
//
//        GameService gs = new GameService();
//
////        gs.createDefaultGroups();
////        gs.createDefaultTeams();
//
//        ArrayList<Group> groups = gs.getAllGroups();
//
//        ArrayList<Group> updatedGroups = gs.calculateGroupsStepWinner(groups);
//
//        for (Group g : updatedGroups) {
//            System.out.println(g.getNome());
//            for (Time time : g.getTimes()){
//                System.out.println(time.getNome() + " fez " + time.getPontos() + " pontos! ");
//            }
//
//            System.out.println("Next group ===================== ");
//        }
//
//    }

}
