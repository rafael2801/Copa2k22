package com.mycompany.copaqatar.views;

import com.mycompany.copaqatar.service.GameService;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameResult {
    private JPanel container;
    private JPanel header;
    private JPanel content;
    private JButton resultadosButton;
    private JButton copa2k22Button;
    private JScrollPane resultScroll;
    public JFrame frame;

    GameService gameService = new GameService();


    public static void main(String[] args) {
        new GameResult().makeFrame();
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


        this.makeActions();
        this.fixComponents();

    }

    public void makeActions() {
    }

    private void fixComponents () {

        ArrayList<String> games = new ArrayList<String>();

        if(gameService.gameResult().size() > 0) {
            games = gameService.gameResult();
        }

        JList<String> list = new JList<>(games.toArray(new String[0]));
        list.setFixedCellHeight(50);
        this.resultScroll.setViewportView(list);
    }
}
