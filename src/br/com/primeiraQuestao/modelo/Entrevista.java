package br.com.primeiraQuestao.modelo;

import br.com.primeiraQuestao.DAO.ProdutoDAO;
import br.com.primeiraQuestao.factory.ConnectionFactory;

import java.util.Scanner;

public class Entrevista {
    private int opcao;
    private Scanner entrada;

    private ProdutoDAO produtoDAO;

    public Entrevista() {
        produtoDAO = new ProdutoDAO(ConnectionFactory.getConnection());
        produtoDAO.deletarListaCadastrado();
        produtoDAO.deletarACadastrarTodos();
        produtoDAO.reset();
    }

    public ProdutoDAO getProdutoDAO() {
        return produtoDAO;
    }

    public void setProdutoDAO(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    public void menuPrincipal(){
        try {
            System.out.println("Informe o que deseja fazer:\n" +
                    "1 - Cadastrar 3 produtos.\n" +
                    "2 - Atualizar o primeiro produto cadastrado.\n" +
                    "3 - Excluir o segundo produto cadastrado.\n" +
                    "0 - Sair.");
            entrada = new Scanner(System.in);
            opcao = Integer.parseInt(entrada.nextLine());

            switch (opcao) {
                case 1:
                    cadastraProduto();
                    break;
                case 2:
                    atualizaProduto();
                    break;
                case 3:
                    excluiProduto();
                    break;
                case 0:
                    terminaPrograma();
                    break;
                default:
                    System.out.println("Não existe essa opção!!\n" +
                            "DIGITE UMA OPÇÃO VALIDA!!");
                    menuPrincipal();
            }
        } catch (NumberFormatException e) {
            System.out.println("Não existe essa opção!!\n" +
                    "DIGITE UMA OPÇÃO VALIDA!!");

            menuPrincipal();
        } finally {
            if (entrada != null) {
                entrada.close();
            }
        }

        //entrada.close();
    }

    private void terminaPrograma() {
        System.out.println("\nPrograma finalizado com sucesso!");
    }

    private void excluiProduto() {
        try {
            produtoDAO.listaCadastrado();
            int segundoProduto = produtoDAO.listaCadastrado().size() - 2;
            Produto produto = produtoDAO.getListaProdutosCadastrados().get(segundoProduto);
            System.out.println("Deseja excluir o item: \n" +
                    produto.getNome() +
                    "\n1 - Sim!" +
                    "\n2 - Não!");

            try {
                menuExcluir(produto);
            } catch (NumberFormatException e) {
                System.out.println("Essa opção nao existe!!\n" +
                        "DIGITE UMA OPÇÃO VALIDA!!");

                menuExcluir(produto);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println();
        }
    }

    private void menuExcluir(Produto produto) {
        int opcao4 = Integer.parseInt(entrada.nextLine());

        switch (opcao4) {
            case 1:
                produtoDAO.salvarProduto(produto);
                produtoDAO.deletarCadastrado(produto);
                System.out.println("Produto deletado com sucesso!!");

            case 2:
                System.out.println();
                menuPrincipal();

        }
    }

    private void atualizaProduto() {
        Produto produto = null;
        try {
            produtoDAO.listaCadastrado();
            int primeiroProduto = produtoDAO.getListaProdutosCadastrados().size() - 3;
            produto = produtoDAO.getListaProdutosCadastrados().get(primeiroProduto);
            System.out.println("Deseja atualizar o produto: \n" + produto.getNome() +
                    "\n1 - Sim!" +
                    "\n2 - Não!");
            menuAtualizar(produto);

        } catch (NumberFormatException e) {
            System.out.println("Essa opção nao existe!!\n" +
                    "DIGITE UMA OPÇÃO VALIDA!!");

            menuAtualizar(produto);
        }
    }

    private void menuAtualizar(Produto produto) {
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
                        novoPreco, produto.getId());
                System.out.println("Produto atualizado com sucesso!!");

            case 2:
                System.out.println();
                menuPrincipal();

        }
    }

    private void cadastraProduto() {
        try {
            produtoDAO.listaraCadastrar();
            if (produtoDAO.getListaProdutosaCadastrar().size() == 0) {
                System.out.println("\nLista de produtos vazias, não tem mais produtos a listar");
                menuPrincipal();
            } else {
                System.out.println(produtoDAO.getListaProdutosaCadastrar());
                System.out.println("\nDeseja cadastrar esses itens? " +
                        "\n1 - Sim!" +
                        "\n2 - Não!");

                menuCadastro();
            }
        } catch (NumberFormatException e) {
            System.out.println("Essa opção nao existe!!\n" +
                    "DIGITE UMA OPÇÃO VALIDA!!");

            menuCadastro();
        }
    }

    private void menuCadastro() {
        int opcao2 = Integer.parseInt(entrada.nextLine());

        switch (opcao2) {
            case 1: //Sim
                produtoDAO.salvarLista();
                produtoDAO.deletarACadastrar(produtoDAO.getListaProdutosaCadastrar());
                produtoDAO.limpaLista();
                System.out.println("Produtos cadastrados com sucesso!!");

            case 2: //Não
                System.out.println();
                menuPrincipal();

        }
    }
}
