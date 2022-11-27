package com.mycompany.copaqatar.views;

import com.mycompany.copaqatar.Equipe;
import com.mycompany.copaqatar.service.GameService;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class RegisterTeams {

    private JFrame frame;
    private JPanel container;
    private JPanel header;
    private JButton copa2k22Button;
    private JPanel content;
    private JTextField textTeam;
    private JButton adicionarButton;
    private JButton usarTimesOficiaisDaButton;
    private JScrollPane teamScroll;
    private JButton cadastrarGruposButton;
    private JLabel totalTeam;

    private List<String> teams = new ArrayList<>();
    private GameService gameService = new GameService();

    public static void main(String[] args) {
        RegisterTeams r = new RegisterTeams();
        r.makeFrame();
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
//        this.populateComponents();

    }

    public void makeActions() {
        this.adicionarButton.addActionListener(e -> {
            this.verifyQtd();
            String teamName = this.textTeam.getText();
            teams.add(teamName);
            gameService.saveTeam(teamName);
            JList<String> list = new JList<>(teams.toArray(new String[0]));
            this.teamScroll.setViewportView(list);
            this.textTeam.setText("");
            this.totalTeam.setText("Total de times: " + teams.size());
        });
    }

    private void verifyQtd () {
        if(teams.size() == 2) {
            JOptionPane.showMessageDialog(null, "Numero maximo de equipes cadastradas!", "Sistema",  JOptionPane.INFORMATION_MESSAGE);
            this.frame.setVisible(false);
            //
        }
    }

    private void fixComponents () {
//        JList<String> list = new JList<>(s);
//        this.teamScroll.setViewportView();

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
