package com.mycompany.copaqatar.views;

import com.mycompany.copaqatar.database.DatabaseConnection;
import com.mycompany.copaqatar.models.User;
import com.mycompany.copaqatar.service.AuthService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Login {
    private JTextField eMailTextField;
    private JButton entrarButton;
    private JPanel container;
    private JTextField inputEmail;
    private JTextField inputPassword;
    private JPasswordField passwordField1;

    private  JFrame frame;

    public Login(User user) {
        this.entrarButton.addActionListener(e -> {
            String email = this.inputEmail.getText();
            String password = this.inputPassword.getText();
            System.out.println(email + " === " + password);

            DatabaseConnection connection = DatabaseConnection.getInstance();
            try {
                AuthService userService = new AuthService(connection.getConnection());
                User userSigned = userService.signIn(email, password);
                user.setLogged(userSigned.getLogged());
                user.setSuper(userSigned.getLogged());
                user.setUser_name(userSigned.getUser_name());

                if(user.getLogged()) {
                    this.frame.setVisible(false);
//                    new Home();
                }

            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (InstantiationException ex) {
                throw new RuntimeException(ex);
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
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
    }

    public void closeFrame () {
        this.frame.setVisible(false);
    }

    private ActionListener signIn () {

        ActionListener listener = e -> {
            String email = this.eMailTextField.getText();
            String password = this.passwordField1.getText();

            DatabaseConnection connection = DatabaseConnection.getInstance();
            try {
                AuthService userService = new AuthService(connection.getConnection());
                userService.signIn(email, password);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (InstantiationException ex) {
                throw new RuntimeException(ex);
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        };

        return listener;
    }

}

