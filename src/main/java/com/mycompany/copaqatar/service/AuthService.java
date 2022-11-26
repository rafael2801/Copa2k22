package com.mycompany.copaqatar.service;

import com.mycompany.copaqatar.models.User;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AuthService {

    Connection connection = null;

    public AuthService (Connection conn) {
        this.connection = conn;
    }

    public User signIn (String email, String password) {
        User user = new User();
        user.setLogged(false);

        try {
            Statement statement;

            statement = connection.createStatement();

            ResultSet response = statement.executeQuery( "SELECT * FROM user");

            response.next();

            if(response.getString("email").equals(email) && response.getString("user_password").equals(password)) {
                JOptionPane.showMessageDialog(null, "Login efetuado com sucesso!");
                user = new User(response.getString("user_name"),response.getBoolean("is_super"));
                user.setLogged(true);

            } else {
                JOptionPane.showMessageDialog(null, "Email ou senha invalidos");
            }

            return user ;

        } catch (Exception e) {
            System.out.println("err in sigin, err: " + e);
            return user ;
        }

    }

}
