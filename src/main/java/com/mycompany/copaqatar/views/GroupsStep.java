package com.mycompany.copaqatar.views;

import com.mycompany.copaqatar.models.Group;
import com.mycompany.copaqatar.models.Time;
import com.mycompany.copaqatar.service.GameService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GroupsStep {
    private JPanel container;
    private JPanel header;
    private JPanel content;
    private JButton btn_group_name;
    private JButton pontosButton;
    private JScrollPane scrollGames;
    private JScrollPane scrollPoints;
    private JButton btn_next_group;

    JFrame frame;

    GameService gs = new GameService();
    ArrayList<Group> updatedGroups = new ArrayList<>();

    private int groupIndex = 1;

    public static void main(String[] args) {
        new GroupsStep().makeFrame();
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

        ArrayList<Group> groups = gs.getAllGroups();

        this.updatedGroups = gs.calculateGroupsStepWinner(groups);

        this.populateComponents();
        this.makeActions();
    }

    private void makeActions () {
        this.btn_next_group.addActionListener(evt -> {
            if(this.groupIndex == 8) {
                this.frame.setVisible(false);
                gs.simulateOctavesStep(this.updatedGroups);
                new OctavesResult().makeFrame();
            } else {
                this.groupIndex++;
                this.populateComponents();
                if(this.groupIndex == 8) this.btn_next_group.setText("Ver Oitavas");
            }

        });
    }
    private void populateComponents() {
        ArrayList<String> games = gs.getGame(groupIndex);

        JList<String> list = new JList<>(games.toArray(new String[0]));
        list.setFixedCellWidth(50);
        list.setFixedCellHeight(50);
        this.scrollGames.setViewportView(list);

        ArrayList<String> gamePoints = new ArrayList<>();
        for (Group g : this.updatedGroups) {
            if((groupIndex) == g.getId()){
                this.btn_group_name.setText(g.getNome());
                for (Time time : g.getTimes()) {
                    gamePoints.add(time.getNome() + " fez " + time.getPontos() + " pontos! ");
                }
            }


            JList<String> listPoints = new JList<>(gamePoints.toArray(new String[0]));
            listPoints.setFixedCellWidth(50);
            listPoints.setFixedCellHeight(50);
            this.scrollPoints.setViewportView(listPoints);

        }

    }
}
