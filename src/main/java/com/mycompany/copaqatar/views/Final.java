package com.mycompany.copaqatar.views;

import com.mycompany.copaqatar.service.GameService;

import javax.swing.*;
import java.awt.*;

public class Final {

    JFrame frame;
    GameService gs = new GameService();
    int groupIndex = 11;
    private JPanel container;
    private JButton copa2k22Button;
    private JPanel header;
    private JButton finalist;
    private JButton btn_logout;

    public static void main(String[] args) {
        new Final().makeFrame();
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

        this.populateComponents();
        this.makeActions();

    }

    private void populateComponents() {
        this.finalist.setText(gs.getWinner());
    }
    private void makeActions () {
        this.btn_logout.addActionListener(evt -> {
            this.frame.setVisible(false);
            gs.deleteAll();
        });
    }
}
