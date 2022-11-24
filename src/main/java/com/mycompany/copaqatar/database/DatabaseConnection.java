package com.mycompany.copaqatar.database;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
public class DatabaseConnection {
    private static DatabaseConnection instance;
    public DatabaseConnection() {

    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

        // Método que estabele a conexão com o banco de dados
        public Connection getConnection() throws
                ClassNotFoundException, InstantiationException,
                IllegalAccessException, SQLException {

            String userName = "root";
            String password = "Sp_964269849";
            String url = "jdbc:mysql://localhost/copa?useTimezone=true&serverTimezone=UTC";
            String driver = "com.mysql.cj.jdbc.Driver";
            // Registra o driver do banco de dados
            Class.forName(driver);

            // Estabele a conexão passando url, usuário e senha
            Connection conn = DriverManager.getConnection(url, userName, password);

            return conn;
        }




}
