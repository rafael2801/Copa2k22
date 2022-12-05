package com.mycompany.copaqatar.views;

import com.mycompany.copaqatar.models.Group;
import com.mycompany.copaqatar.models.Time;
import com.mycompany.copaqatar.service.GameService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Quartas {

    JFrame frame;
    GameService gs = new GameService();
    int groupIndex = 11;

    ArrayList<Group> updatedGroups = new ArrayList<>();
    private JPanel container;
    private JButton copa2k22Button;
    private JButton btn_groups;
    private JButton btn_points;
    private JButton btn_next;
    private JScrollPane scrollGames;
    private JScrollPane scrollPoints;

    public static void main(String[] args) {
        new Quartas().makeFrame();
    }

    public void makeFrame() {

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

        ArrayList<Group> groups = gs.getAllQuartasGroups();
        this.updatedGroups = gs.calculateGroupsStepWinner(groups);

        this.populateComponents();
        this.makeActions();

    }

    private void populateComponents() {
        ArrayList<String> games = gs.getQuartasGame();

        JList<String> list = new JList<>(games.toArray(new String[0]));
        list.setFixedCellWidth(50);
        list.setFixedCellHeight(50);
        this.scrollGames.setViewportView(list);

        ArrayList<String> gamePoints = new ArrayList<>();
        for (Group g : this.updatedGroups) {
                for (Time time : g.getTimes()) {
                    gamePoints.add(time.getNome() + " fez " + time.getPontos() + " pontos! ");
            }


            JList<String> listPoints = new JList<>(gamePoints.toArray(new String[0]));
            listPoints.setFixedCellWidth(50);
            listPoints.setFixedCellHeight(50);
            this.scrollPoints.setViewportView(listPoints);
        }


    }
    private void makeActions () {
        this.btn_next.addActionListener(evt -> {
            gs.simulateSemi(this.updatedGroups);
            this.frame.setVisible(false);
            new Semi().makeFrame();
        });
    }
}
