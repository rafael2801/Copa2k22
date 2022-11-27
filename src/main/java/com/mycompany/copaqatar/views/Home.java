package com.mycompany.copaqatar.views;

import com.mycompany.copaqatar.models.User;

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


    public static void main(String[] args) {
        Home home = new Home();
        home.makeFrame();

//        JPanel panel = new JPanel(new BorderLayout());
//        List<String> myList = new ArrayList<>(10);
//        for (int index = 0; index < 20; index++) {
//            myList.add("List Item " + index);
//        }
//        final JList<String> list = new JList<String>(myList.toArray(new String[myList.size()]));
//        JScrollPane scrollPane = new JScrollPane();
//        scrollPane.setViewportView(list);
//        list.setLayoutOrientation(JList.VERTICAL);
//        panel.add(scrollPane);
//        JFrame frame = new JFrame("Demo");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.add(panel);
//        frame.setSize(500, 250);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
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
    }

    private void populateComponents () {

        this.btn_simulate.setBackground(mainColor);
        this.btn_simulate.setOpaque(true);
        String[] countries = {"Grupo A", "Grupo B", "Grupo C", "Grupo D", "Grupo E", "Grupo F", "Grupo G", "Grupo H"};
        this.groupList.setListData(countries);
        this.teamList.setListData(mockTeams);

    }

    private void fixComponents () {
//        JScrollPane scrollPane = new JScrollPane();
//        scrollPane.setViewportView(list);
//        list.setLayoutOrientation(JList.VERTICAL);
        //lista de grupos
        this.groupList.setFixedCellWidth(50);
        this.groupList.setFixedCellHeight(50);

        //lista de times
        this.teamList.setFixedCellWidth(50);
        this.teamList.setFixedCellHeight(20);

    }

    private MouseAdapter clickListener () {
        return new MouseAdapter()
        {
            public void mouseClicked(MouseEvent e)
            {
                // you can open a new frame here as
                // i have assumed you have declared "frame" as instance variable
                frame = new JFrame("new frame");
                frame.setVisible(true);

            }
        };
    }
}
