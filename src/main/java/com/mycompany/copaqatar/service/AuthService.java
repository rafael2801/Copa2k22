package com.mycompany.copaqatar.service;

import com.mycompany.copaqatar.DAO;
import com.mycompany.copaqatar.models.User;

import javax.swing.*;
import java.sql.*;

public class AuthService {

    Connection connection = null;
    DAO dao = new DAO();

    public AuthService (Connection conn) {
        this.connection = conn;
    }

    public User signIn (String email, String password) {
        User user = new User();
        user.setLogged(false);

        try {

            String sql = "SELECT * FROM user where email = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();

                if( rs.getString("email").equals(email) && rs.getString("user_password").equals(password) ) {
                    user = new User(rs.getString("user_name"), rs.getBoolean("is_super"));
                    user.setLogged(true);
                    JOptionPane.showMessageDialog(null, "Login efetuado com sucesso!");

                } else {
                    JOptionPane.showMessageDialog(null, "Email ou senha invalidos");
                }
            }

            System.out.println("user" + user.getSuper());

            return user;

        } catch (Exception e) {
            System.out.println("err in sigin, err: " + e);
            return user ;
        }

    }

    public Boolean hasTeams (){
        int number = 0;
        try {
            number = dao.quantidadeEquipesCadastradas();

        } catch (SQLException e) {
            number = 0;
        }
        return ( number > 0);
    }

    public void signUp (String name, String email, String password) {
        dao.signUp(name, email, password);
    }

}