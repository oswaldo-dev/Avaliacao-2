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
        try {
            opcao = Integer.parseInt(entrada.nextLine());

            switch (opcao) {
                case 1:
                    cadastraProduto(produtoDAO);

                case 2:
                    atualizaProduto(produtoDAO);

                case 3:
                    excluiProduto(produtoDAO);

                case 0:
                    terminaPrograma(produtoDAO);
            }
        } catch (NumberFormatException e) {
            System.out.println("Não existe essa opção!!\n" +
                    "DIGITE UMA OPÇÃO VALIDA!!");

            opcao = Integer.parseInt(entrada.nextLine());

            switch (opcao) {
                case 1:
                    cadastraProduto(produtoDAO);

                case 2:
                    atualizaProduto(produtoDAO);

                case 3:
                    excluiProduto(produtoDAO);

                case 0:
                    terminaPrograma(produtoDAO);
            }
        }

        entrada.close();
    }

    private void terminaPrograma(ProdutoDAO produtoDAO) {
        System.out.println("\nPrograma finalizado com sucesso!");
        produtoDAO.salvarListaACadastrar(produtoDAO.listaCadastrado());
        produtoDAO.deletarListaCadastrado(produtoDAO.listaCadastrado());
    }

    private void excluiProduto(ProdutoDAO produtoDAO) {
        try {
            int segundoProduto = produtoDAO.listaCadastrado().size() - 2;
            System.out.println("Deseja excluir o item: \n" +
                    produtoDAO.listaCadastrado().get(segundoProduto) +
                    "\n1 - Sim!" +
                    "\n2 - Não!");

            try {
                menuExcluir(produtoDAO, segundoProduto);
            } catch (NumberFormatException e) {
                System.out.println("Essa opção nao existe!!\n" +
                        "DIGITE UMA OPÇÃO VALIDA!!");

                menuExcluir(produtoDAO, segundoProduto);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println();
        }
    }

    private void menuExcluir(ProdutoDAO produtoDAO, int segundoProduto) {
        int opcao4 = Integer.parseInt(entrada.nextLine());

        switch (opcao4) {
            case 1:
                produtoDAO.salvarProduto(produtoDAO.listaCadastrado().get(segundoProduto));
                produtoDAO.deletarCadastrado(produtoDAO.listaCadastrado().get(segundoProduto));
                System.out.println("Produto deletado com sucesso!!");

            case 2:
                System.out.println();
                new Entrevista();

        }
    }

    private void atualizaProduto(ProdutoDAO produtoDAO) {
        try {
            int primeiroProduto = produtoDAO.listaCadastrado().size() - 3;
            System.out.println("Deseja atualizar o produto: \n" + produtoDAO.listaCadastrado().get(primeiroProduto) +
                    "\n1 - Sim!" +
                    "\n2 - Não!");

            try {
                menuAtualizar(produtoDAO, primeiroProduto);
            } catch (NumberFormatException e) {
                System.out.println("Essa opção nao existe!!\n" +
                        "DIGITE UMA OPÇÃO VALIDA!!");

                menuAtualizar(produtoDAO, primeiroProduto);
            }
        }catch (IndexOutOfBoundsException e) {
            System.out.println();
        }
    }

    private void menuAtualizar(ProdutoDAO produtoDAO, int primeiroProduto) {
        int opcao3 = Integer.parseInt(entrada.nextLine());

        switch (opcao3) {
            case 1:
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

            case 2:
                System.out.println();
                new Entrevista();

        }
    }

    private void cadastraProduto(ProdutoDAO produtoDAO) {

        System.out.println(produtoDAO.listarCadastrar());
        System.out.println("\nDeseja cadastrar esses itens? " +
                "\n1 - Sim!" +
                "\n2 - Não!");
        try {
            menuCadastro(produtoDAO);
        } catch (NumberFormatException e) {
            System.out.println("Essa opção nao existe!!\n" +
                    "DIGITE UMA OPÇÃO VALIDA!!");

            menuCadastro(produtoDAO);
        }
    }

    private void menuCadastro(ProdutoDAO produtoDAO) {
        int opcao2 = Integer.parseInt(entrada.nextLine());

        switch (opcao2) {
            case 1:
                produtoDAO.salvarLista(produtoDAO.listarCadastrar());
                produtoDAO.deletarACadastrar(produtoDAO.listarCadastrar());
                System.out.println("Produtos cadastrados com sucesso!!");

            case 2:
                System.out.println();
                new Entrevista();

        }
    }
}
