package br.com.segundaQuestao.modelo;

import br.com.segundaQuestao.DAO.FilmeDAO;
import br.com.segundaQuestao.factory.ConnectionFactory;

import java.util.Scanner;

public class Catalogo {

    private int qtdeFilmes;
    private int paginas;

    Scanner entrada;


    public Catalogo() {
        FilmeDAO filmeDAO = new FilmeDAO(ConnectionFactory.getConnection());
        entrada = new Scanner(System.in);

        try {
            System.out.println("Informe a quantidade de filmes que você quer ver no catalogo: ");
            this.qtdeFilmes = Integer.parseInt(entrada.nextLine());
            if (this.qtdeFilmes > 10) {
                System.out.println("No momento só temos 10 filmes por página!!");
            } else {
                System.out.println("Informe a página do catalogo (1 ou 2): ");
                this.paginas = Integer.parseInt(entrada.nextLine());
                if (this.paginas > 2) {
                    System.out.println("No momento só temos 2 páginas");
                } else {
                    System.out.println(filmeDAO.listar(paginas, qtdeFilmes));
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Digite um número válido!!");
        }
        entrada.close();
    }
}
