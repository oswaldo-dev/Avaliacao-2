package br.com.segundaQuestao.DAO;

import br.com.primeiraQuestao.modelo.Produto;
import br.com.segundaQuestao.modelo.Filme;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmeDAO {
    private final Connection connection;

    public FilmeDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Filme> listar(int pagina, int numero) {
        try {
            List<Filme> filmes = new ArrayList<>();
            if(pagina == 1) {

                String sql = "SELECT id, nome, descricao, ano FROM filmes WHERE id <= ?";

                try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                    pstm.setInt(1, numero);
                    pstm.execute();

                    trasformarResultSetEmProduto(filmes, pstm);
                }
                return filmes;
            } else if (pagina == 2) {
                String sql = "SELECT id, nome, descricao, ano FROM filmes2 WHERE id <= ?";

                try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                    pstm.setInt(1, (numero));
                    pstm.execute();

                    trasformarResultSetEmProduto(filmes, pstm);
                }
                return filmes;
            }
            return filmes;
//            List<Filme> filmes = new ArrayList<>();
//            String sql = "SELECT id, nome, descricao, ano FROM filmes";
//
//            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
//                pstm.execute();
//
//                trasformarResultSetEmProduto(filmes, pstm);
//            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void trasformarResultSetEmProduto(List<Filme> filmes, PreparedStatement pstm) {
        try {
            try (ResultSet rst = pstm.getResultSet()) {
                while (rst.next()) {
                    Filme filme = new Filme(rst.getInt(1), rst.getString(2),
                            rst.getString(3), rst.getInt(4));

                    filmes.add(filme);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
