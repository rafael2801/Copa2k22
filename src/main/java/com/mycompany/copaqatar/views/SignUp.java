package com.mycompany.copaqatar.views;

import com.mycompany.copaqatar.ConnectionFactory;
import com.mycompany.copaqatar.models.User;
import com.mycompany.copaqatar.service.AuthService;

import javax.swing.*;
import java.awt.*;

public class SignUp {
    private JPanel container;
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton cadastrarButton;
    JFrame frame;
    private final Color mainColor = new Color(105, 4, 34);

    ConnectionFactory connectionFactory = new ConnectionFactory();
    private AuthService authService = new AuthService(connectionFactory.obtemConexao());

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

        this.cadastrarButton.addActionListener(evt -> {
            String name = this.nameField.getText();
            String email = this.emailField.getText();
            char[] pf = this.passwordField.getPassword();

            String password = new String(pf);
            authService.signUp(name, email, password);

            this.frame.setVisible(false);
            new Login(new User()).makeFrame();
        });
    }
}
