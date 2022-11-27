package com.mycompany.copaqatar.views;

import javax.swing.*;
import java.awt.*;

public class HomeWithoutTeams {
    private JPanel container;
    private JButton title;
    private JButton btn_register_teams;

    private  JFrame frame;

    private final Color mainColor = new Color(105, 4, 34);


    public static void main(String[] args) {
        HomeWithoutTeams h = new HomeWithoutTeams();
        h.makeFrame();
    }

    private void makeFrame () {
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

        this.fixComponents();
    }

    private void fixComponents () {
        this.title.setBackground(mainColor);
        this.title.setOpaque(true);

        this.btn_register_teams.setBackground(mainColor);
        this.btn_register_teams.setOpaque(true);
    }
}
