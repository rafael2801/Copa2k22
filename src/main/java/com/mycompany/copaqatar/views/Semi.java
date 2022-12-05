package com.mycompany.copaqatar.views;

import com.mycompany.copaqatar.models.Group;
import com.mycompany.copaqatar.models.Time;
import com.mycompany.copaqatar.service.GameService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Semi {
    private JPanel container;
    private JButton copa2k22Button;
    private JButton btn_finalist_two;
    private JButton btn_finalist_one;
    private JButton btn_simulate_final;

    JFrame frame;
    GameService gs = new GameService();
    int groupIndex = 11;

    ArrayList<Time> times = new ArrayList<>();

    public static void main(String[] args) {
        new Semi().makeFrame();
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

         this.times = gs.getAllFinalists();

        this.populateComponents();
        this.makeActions();

    }

    private void populateComponents() {
        this.btn_finalist_one.setText(times.get(0).getNome());
        this.btn_finalist_two.setText(times.get(1).getNome());
    }
    private void makeActions () {
        this.btn_simulate_final.addActionListener(evt -> {
            gs.simulateFinal(times);
            this.frame.setVisible(false);
            new Final().makeFrame();
        });
    }
}
