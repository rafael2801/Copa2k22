package com.mycompany.copaqatar.views;

import com.mycompany.copaqatar.models.User;

import javax.swing.*;
import java.awt.*;

public class HomeWithoutTeams {
    private JPanel container;
    private JButton title;
    private JButton btn_register_teams;

    private  JFrame frame;

    User user;

    private final Color mainColor = new Color(105, 4, 34);


    public HomeWithoutTeams (User user) {
        this.user = user;
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

        this.fixComponents();
        this.makeActions();
    }

    private void makeActions () {
        this.btn_register_teams.addActionListener(evt -> {
            this.frame.setVisible(false);
            new RegisterTeams().makeFrame();
        });
    }

    private void fixComponents () {
        this.title.setBackground(mainColor);
        this.title.setOpaque(true);

        this.btn_register_teams.setBackground(mainColor);
        this.btn_register_teams.setOpaque(true);

        if(user.getSuper().equals(false)) {
            this.btn_register_teams.setVisible(false);
        }
    }
}
