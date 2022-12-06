package com.mycompany.copaqatar.views;

import com.mycompany.copaqatar.ConnectionFactory;
import com.mycompany.copaqatar.components.ImagePanel;
import com.mycompany.copaqatar.models.User;
import com.mycompany.copaqatar.service.AuthService;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Login {
    private JTextField eMailTextField;
    private JButton entrarButton;
    private JPanel container;
    private JTextField inputEmail;
    private JTextField inputPassword;
    private JPasswordField passwordField1;

    private  JFrame frame;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton cadastrarButton;
    private JPanel imageContainer;

    private final Color mainColor = new Color(105, 4, 34);

    public Login(User user) {
        this.entrarButton.addActionListener(e -> {
            String email = this.emailField.getText();
            char[] pf = this.passwordField.getPassword();
            String password = new String(pf);

            ConnectionFactory connection = new ConnectionFactory();
            AuthService userService = new AuthService(connection.obtemConexao());
            User userSigned = userService.signIn(email, password);
            user.setLogged(userSigned.getLogged());
            user.setSuper(userSigned.getSuper());
            user.setUser_name(userSigned.getUser_name());

            if(user.getLogged()) {
                this.frame.setVisible(false);
                if(userService.hasTeams()) {
                    new Home(user).makeFrame();
                } else {
                    new HomeWithoutTeams(user).makeFrame();
                }

            }

        });
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

        this.cadastrarButton.setBackground(mainColor);
        this.entrarButton.setBackground(mainColor);

        this.cadastrarButton.addActionListener(evt -> {
            this.frame.setVisible(false);
            new SignUp().makeFrame();
        });

        this.fixComponents();
    }

    private void fixComponents () {
//            this.imageContainer = new ImagePanel();
    }

}


