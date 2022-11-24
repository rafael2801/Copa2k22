package com.mycompany.copaqatar.database;

import com.mycompany.copaqatar.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserService {

    Connection connection = null;

    public UserService (Connection conn) {
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
                System.out.println("logado");
                user = new User(response.getString("user_name"),response.getBoolean("is_super"));
                user.setLogged(true);

            }

            return user ;

        } catch (Exception e) {
            System.out.println("err in sigin, err: " + e);
            return user ;
        }

    }
}
