package br.com.primeiraQuestao.factory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost/produto?useTimezone=true&serverTimezone=UTC";
            String user = "root";
            String senha = "12345678";

            return DriverManager.getConnection(url, user, senha);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
