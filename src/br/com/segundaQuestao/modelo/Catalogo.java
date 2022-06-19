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
        } catch (NumberFormatException e) {
            System.out.println("Digite um número válido!!");

            System.out.println("Informe a quantidade de filmes que você quer ver no catalogo: ");
            this.qtdeFilmes = Integer.parseInt(entrada.nextLine());
        }

        try {
            System.out.println("Informe a página do catalogo (1 ou 2): ");
            this.paginas = Integer.parseInt(entrada.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Digite um número válido!!");

            System.out.println("Informe a página do catalogo (1 ou 2): ");
            this.paginas = Integer.parseInt(entrada.nextLine());
        }


        System.out.println(filmeDAO.listar(paginas, qtdeFilmes));



        entrada.close();


    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public int getQtdeFilmes() {
        return qtdeFilmes;
    }

    public void setQtdeFilmes(int qtdeFilmes) {
        this.qtdeFilmes = qtdeFilmes;
    }
}
