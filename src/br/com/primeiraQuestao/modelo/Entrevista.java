package br.com.primeiraQuestao.modelo;

import br.com.primeiraQuestao.DAO.ProdutoDAO;
import br.com.primeiraQuestao.factory.ConnectionFactory;

import java.util.Scanner;

public class Entrevista {
    private int opcao;
    private Scanner entrada;

    public Entrevista() {
        ProdutoDAO produtoDAO = new ProdutoDAO(ConnectionFactory.getConnection());
        System.out.println("Informe o que deseja fazer:\n" +
                "1 - Cadastrar 3 produtos.\n" +
                "2 - Atualizar o primeiro produto cadastrado.\n" +
                "3 - Excluir o segundo produto cadastrado.\n" +
                "0 - Sair.");
        entrada = new Scanner(System.in);

        opcao = Integer.parseInt(entrada.nextLine());

        switch (opcao) {
            case 0 :
                produtoDAO.reset(produtoDAO.listaCadastrado());
            break;

            case 1 :
                System.out.println(produtoDAO.listarCadastrar());
                System.out.println("\nDeseja cadastrar esses itens? " +
                        "\n1 - Sim!" +
                        "\n2 - Não!");
                int opcao2 = Integer.parseInt(entrada.nextLine());

                switch (opcao2) {
                    case 1 :
                        produtoDAO.salvarLista(produtoDAO.listarCadastrar());
                        produtoDAO.deletarACadastrar(produtoDAO.listarCadastrar());
                        System.out.println("Produtos cadastrados com sucesso!!");

                    case 2 :
                        new Entrevista();

                }

            case 2 :
                int primeiroProduto = produtoDAO.listaCadastrado().size() - 3;
                System.out.println("Deseja atualizar o produto: \n" + produtoDAO.listaCadastrado().get(primeiroProduto) +
                        "\n1 - Sim!" +
                        "\n2 - Não!");
                int opcao3 = Integer.parseInt(entrada.nextLine());

                switch (opcao3) {
                    case 1 :
                        System.out.println("Digite o novo nome: ");
                        String novoNome = entrada.nextLine();

                        System.out.println("Digite a nova descrição: ");
                        String novaDescricao = entrada.nextLine();

                        System.out.println("Digite a nova quantidade: ");
                        int novaQuantidade = Integer.parseInt(entrada.nextLine());

                        System.out.println("Digite o novo preço: ");
                        double novoPreco = Double.parseDouble(entrada.nextLine());


                        produtoDAO.alterar(novoNome, novaDescricao, novaQuantidade,
                                novoPreco, produtoDAO.listaCadastrado().get(primeiroProduto).getId());
                        System.out.println("Produto atualizado com sucesso!!");

                    case 2 :
                        new Entrevista();

                }

            case 3 :
                int segundoProduto = produtoDAO.listaCadastrado().size() - 2;
                System.out.println("Deseja excluir o item: \n" +
                        produtoDAO.listaCadastrado().get(segundoProduto) +
                        "\n1 - Sim!" +
                        "\n2 - Não!");
                int opcao4 = Integer.parseInt(entrada.nextLine());

                switch (opcao4) {
                    case 1 :
                        produtoDAO.salvar(produtoDAO.listaCadastrado().get(segundoProduto));
                        produtoDAO.deletarCadastrado(produtoDAO.listaCadastrado().get(segundoProduto));
                        System.out.println("Produto deletado com sucesso!!");

                    case 2 :
                        new Entrevista();

                }

        }

        entrada.close();
    }
}
