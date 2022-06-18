package br.com.segundaQuestao.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost/filme?useTimezone=true&serverTimezone=UTC";
            String user = "root";
            String senha = "12345678";

            return DriverManager.getConnection(url, user, senha);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
