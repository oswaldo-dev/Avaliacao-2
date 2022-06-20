package br.com.primeiraQuestao.DAO;

import br.com.primeiraQuestao.modelo.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private final Connection connection;

    List<Produto> listaProdutosaCadastrar = new ArrayList<>();

    List<Produto> listaProdutosCadastrados = new ArrayList<>();

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Produto> getListaProdutosaCadastrar() {
        return listaProdutosaCadastrar;
    }

    public void setListaProdutosaCadastrar(List<Produto> listaProdutosaCadastrar) {
        this.listaProdutosaCadastrar = listaProdutosaCadastrar;
    }

    public List<Produto> getListaProdutosCadastrados() {
        return listaProdutosCadastrados;
    }

    public void setListaProdutosCadastrados(List<Produto> listaProdutosCadastrados) {
        this.listaProdutosCadastrados = listaProdutosCadastrados;
    }

    public void salvarLista() {
        try {
            String sql = "INSERT INTO produtos (nome, descricao, quantidade, preco) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                for(Produto produto : listaProdutosaCadastrar) {
                    salva(produto, pstm);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void salvarListaACadastrar() {
        try {
            String sql = "INSERT INTO produtos_a_cadastrar (nome, descricao, quantidade, preco) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                for (Produto produto : listaProdutosaCadastrar) {
                    salva(produto, pstm);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void salvarProduto(Produto produto) {
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

       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }


    public List<Produto> listaraCadastrar() {
        try {

            String sql = "SELECT id, nome, descricao, quantidade, preco FROM produtos_a_cadastrar LIMIT 3";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                trasformarResultSetEmProduto(pstm, listaProdutosaCadastrar);
            }
            return listaProdutosaCadastrar;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Produto> listaCadastrado() {
        try {
           listaProdutosCadastrados.clear();
           String sql = "SELECT id, nome, descricao, quantidade, preco FROM produtos";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();
                trasformarResultSetEmProduto(pstm, listaProdutosCadastrados);
            }
            return listaProdutosCadastrados;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletarACadastrar(List<Produto> listaProdutoLocal) {
        try {
            try (PreparedStatement stm = connection.prepareStatement("DELETE FROM produtos_a_cadastrar WHERE id = ?")) {
                for (Produto produto : listaProdutoLocal) {
                    stm.setInt(1, produto.getId());
                    stm.execute();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletarACadastrarTodos() {
        try {
            try (PreparedStatement stm = connection.prepareStatement("DELETE FROM produtos_a_cadastrar where 1 = 1")) {
                stm.execute();
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

    public void deletarListaCadastrado() {
        try {
            try (PreparedStatement stm = connection.prepareStatement("DELETE FROM produtos where 1 = 1 ")) {
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

    private void trasformarResultSetEmProduto(PreparedStatement pstm, List<Produto> produtos) {
        try {
            try (ResultSet rst = pstm.getResultSet()) {
                produtos.clear();
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

    public void limpaLista(){
        listaProdutosaCadastrar.clear();
    }

    public void reset() {

        try {
            incluirProdutoaCadastrar("Grand Theft Auto V", "Grand Theft Auto V é um jogo eletrônico de ação-aventura desenvolvido pela Rockstar North e publicado pela Rockstar Games.", 10, 150.00);
            incluirProdutoaCadastrar("The Witcher 3: Wild Hunt", "The Witcher 3: Wild Hunt é um jogo eletrônico de RPG de ação em mundo aberto desenvolvido pela CD Projekt RED e lançado no dia 19 de maio de 2015 para as plataformas Microsoft Windows, PlayStation 4, Xbox One e em outubro de 2019 para o Nintendo Switch, sendo o terceiro título da série de jogos The Witcher.", 10, 39.99);
            incluirProdutoaCadastrar("God of War", "God of War é um jogo eletrônico de ação-aventura desenvolvido pela Santa Monica Studio e publicado pela Sony Interactive Entertainment. Foi lançado em 20 de abril de 2018 para PlayStation 4 e em 14 de janeiro de 2022 para Microsoft Windows.", 10, 99.50);
            incluirProdutoaCadastrar("Minecraft", "Minecraft é um jogo eletrônico sandbox de sobrevivência criado pelo desenvolvedor sueco Markus \\\"Notch\\\" Persson e posteriormente desenvolvido e publicado pela Mojang Studios, cuja propriedade intelectual foi obtida pela Microsoft em 2014.", 10, 7.49);
            incluirProdutoaCadastrar("Batman: Arkham City", "Batman: Arkham City é um jogo eletrônico de Ação-Aventura e Stealth, baseado na série de quadrinhos Batman da DC Comics. O jogo é distribuído para: PlayStation 3, Xbox 360 e Microsoft Windows. Foi desenvolvido pela Rocksteady Studios e foi publicado pela Warner Bros. Interactive Entertainment e DC Entertainment.", 10, 19.99);
            incluirProdutoaCadastrar("Doom", "Doom é um jogo de computador lançado em 1994 pela id Software e um dos títulos que geraram o gênero tiro em primeira pessoa.", 10, 19.99);
            incluirProdutoaCadastrar("The Last of Us", "The Last of Us', 'The Last of Us é um jogo eletrônico de ação-aventura e sobrevivência desenvolvido pela Naughty Dog e publicado pela Sony Computer Entertainment. Ele foi lançado exclusivamente para PlayStation 3 em 14 de junho de 2013", 10, 99.50);
            incluirProdutoaCadastrar("Final Fantasy VII", "Final Fantasy VII é um jogo eletrônico de RPG desenvolvido e publicado pela SquareSoft. É o sétimo título principal da série Final Fantasy e foi lançado originalmente para PlayStation em 1997 e depois também para Microsoft Windows no ano seguinte.", 10, 11.99);
            incluirProdutoaCadastrar("The Legend of Zelda", "The Legend of Zelda: Breath of the Wild é um jogo eletrônico de ação-aventura desenvolvido pela Nintendo Entertainment Planning & Development e publicado pela Nintendo. É o décimo nono título da série The Legend of Zelda e foi lançado mundialmente para Wii U e Nintendo Switch em 3 de março de 2017.", 10, 347.70);
            incluirProdutoaCadastrar("Red Dead Redemption 2", "Red Dead Redemption 2 é um jogo eletrônico de ação-aventura desenvolvido e publicado pela Rockstar Games. É o terceiro título da série Red Dead e uma prequela de Red Dead Redemption, tendo sido lançado em outubro de 2018 para PlayStation 4 e Xbox One e em novembro de 2019 para Microsoft Windows e Google Stadia.", 10, 149.97);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    public void incluirProdutoaCadastrar(String nome, String descricao, int quantidade, double preco){
        try {
            Produto produto = new Produto();
            produto.setNome(nome);
            produto.setDescricao(descricao);
            produto.setQuantidade(quantidade);
            produto.setPreco(preco);

            String sql = "INSERT INTO produtos_a_cadastrar (nome, descricao, quantidade, preco) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                salva(produto, pstm);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
