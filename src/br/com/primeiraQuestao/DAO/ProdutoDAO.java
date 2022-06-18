package br.com.primeiraQuestao.DAO;

import br.com.primeiraQuestao.modelo.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private final Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvarLista(List<Produto> produtos) {
        try {
            String sql = "INSERT INTO produtos (nome, descricao, quantidade, preco) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                for(Produto produto : produtos) {
                    salva(produto, pstm);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void salvar(Produto produto) {
        try {
            String sql = "INSERT INTO produtos_a_cadastrar (nome, descricao, quantidade, preco) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                salva(produto, pstm);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void salva(Produto produto, PreparedStatement pstm) {
       try {
           pstm.setString(1, produto.getNome());
           pstm.setString(2, produto.getDescricao());
           pstm.setInt(3, produto.getQuantidade());
           pstm.setDouble(4, produto.getPreco());

           pstm.execute();

           try (ResultSet rst = pstm.getGeneratedKeys()) {
               while (rst.next()) {
                   produto.setId(rst.getInt(1));
               }
           }
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }


    public List<Produto> listarCadastrar() {
        try {
            List<Produto> produtos = new ArrayList<>();
            String sql = "SELECT id, nome, descricao, quantidade, preco FROM produtos_a_cadastrar LIMIT 3";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                trasformarResultSetEmProduto(produtos, pstm);
            }
            return produtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Produto> listaCadastrado() {
        try {
            List<Produto> produtos = new ArrayList<>();
            String sql = "SELECT id, nome, descricao, quantidade, preco FROM produtos";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                trasformarResultSetEmProduto(produtos, pstm);
            }
            return produtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletarACadastrar(List<Produto> produtos) {
        try {
            try (PreparedStatement stm = connection.prepareStatement("DELETE FROM produtos_a_cadastrar WHERE id = ?")) {
                for (Produto produto : produtos) {
                    stm.setInt(1, produto.getId());
                    stm.execute();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletarCadastrado(Produto produto) {
        try {
            try (PreparedStatement stm = connection.prepareStatement("DELETE FROM produtos WHERE id = ?")) {

                stm.setInt(1, produto.getId());
                stm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterar(String nome, String descricao, Integer quantidade, Double preco, Integer id) {
        try {
            try (PreparedStatement stm = connection
                    .prepareStatement("UPDATE produtos P SET P.nome = ?, P.descricao = ?, P.quantidade = ?, P.preco = ? WHERE id = ?")) {
                stm.setString(1, nome);
                stm.setString(2, descricao);
                stm.setInt(3, quantidade);
                stm.setDouble(4, preco);
                stm.setInt(5, id);
                stm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void trasformarResultSetEmProduto(List<Produto> produtos, PreparedStatement pstm) {
        try {
            try (ResultSet rst = pstm.getResultSet()) {
                while (rst.next()) {
                    Produto produto = new Produto(rst.getInt(1), rst.getString(2),
                            rst.getString(3), rst.getInt(4), rst.getDouble(5));

                    produtos.add(produto);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void reset(List<Produto> produtos) {

        try {
            String sql = "INSERT INTO produtos_a_cadastrar (nome, descricao, quantidade, preco) VALUES (?, ?, ?, ?)";

            for (Produto produto : produtos) {
                try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    salva(produto, pstm);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        try {
            try (PreparedStatement stm = connection.prepareStatement("TRUNCATE TABLE produtos")) {
                stm.execute();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
