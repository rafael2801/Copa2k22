package com.mycompany.copaqatar.database;

import com.mycompany.copaqatar.models.User;

import java.sql.*;

public class UserService {

    Connection connection = null;

    public UserService (Connection conn) {
        this.connection = conn;
    }

    public User signIn (String email, String password) {
        this.setupAuth();
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

    public void setupAuth () {
        String sql = "CREATE TABLE IF NOT EXISTS user (\n" +
                "\tid int primary key not null auto_increment,\n" +
                "    user_name VARCHAR(100) not null,\n" +
                "    email VARCHAR(150) not null,\n" +
                "    user_password VARCHAR(150) not null,\n" +
                "    is_super boolean not null\n" +
                " )";

        try {
            try {
                PreparedStatement ps = connection.prepareStatement(sql);

                ps.execute();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }catch(Exception e){
            System.out.println("err on delete all " + e);
        }

    }
}
