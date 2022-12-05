package com.mycompany.copaqatar.views;

import com.mycompany.copaqatar.models.Group;
import com.mycompany.copaqatar.models.User;
import com.mycompany.copaqatar.service.GameService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class Home {
    private JPanel container;
    private JButton headerTitle;
    private JButton btn_simulate;
    private JButton btn_delete_all;
    private JLabel btn_logout;
    private JPanel content;
    private JList teamList;
    private JList groupList;
    private JLabel gruposTitle;
    private JPanel header;
    private JLabel G;
    private JScrollPane groupScroll;

    private  JFrame frame;

    private User user;
    GameService gameService = new GameService();

    private final String[] mockTeams = {
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

    private final Color mainColor = new Color(105, 4, 34);


    public Home (User user) {
        this.user = user;
    }

    public Home () {
    }

    public static void main(String[] args) {
        Home home = new Home();
        home.makeFrame();
    }

    public void makeFrame () {
        frame = new JFrame();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int width = (int) screenSize.getWidth();
        int frameWidth = (width * 75) / 100;
        int frameHeight = (screenSize.height * 75) / 100;

        frame.setContentPane(this.container);
        frame.setTitle("Copa 2k22!");
        frame.setVisible(true);
        frame.setSize(frameWidth, frameHeight);

        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.makeActions();
        this.fixComponents();
        this.populateComponents();

    }

    public void makeActions() {

        this.btn_delete_all.addActionListener(evt -> {
            gameService.deleteAll();
            String[] listOfNull = {};
            this.groupList.setListData(listOfNull);

            this.teamList.setListData(listOfNull);

            this.frame.setVisible(false);
            new Home().makeFrame();
        });

        this.btn_simulate.addActionListener(evt -> {
            ArrayList<Group> groups = gameService.getAllGroups();
            for (Group g : groups) {
                gameService.simulateGroupsStep(g, "Fase de grupos");
            }
//            gameService.play();
            new GroupsStep().makeFrame();
            this.frame.setVisible(false);
        });

        this.btn_logout.addMouseListener(this.clickListener());
    }

    private void populateComponents () {

        this.btn_simulate.setBackground(mainColor);
        this.btn_simulate.setOpaque(true);

        String[] teams = gameService.getTeams();
        String[] countries = gameService.getGroups();

        this.groupList.setListData(countries);

        this.teamList.setListData(teams);

    }

    private void fixComponents () {

        //lista de grupos
        this.groupList.setFixedCellWidth(50);
        this.groupList.setFixedCellHeight(50);

        //lista de times
        this.teamList.setFixedCellWidth(50);
        this.teamList.setFixedCellHeight(20);
        this.btn_delete_all.setVisible(false);


        if(this.user != null && this.user.getSuper()) {
        } else {
            this.btn_simulate.setVisible(false);
        }

    }

    private MouseAdapter clickListener () {
        JFrame frame = this.frame;
        return new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                // you can open a new frame here as
                // i have assumed you have declared "frame" as instance variable
                frame.setVisible(false);

            }
        };
    }
}
