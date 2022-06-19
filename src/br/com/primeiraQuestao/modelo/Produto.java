package br.com.primeiraQuestao.modelo;

import java.util.Scanner;

public class Produto {

    private int id;
    private String nome;
    private String descricao;
    private int quantidade;
    private double preco;


    public Produto(int id, String nome, String decricao, int quantidade, double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao= decricao;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return "\nProduto: " + this.nome
                + "\nDescrição: " + this.descricao
                + "\nQuantidade: " + this.quantidade
                + "\nPreço: " + this.preco
                + "\n-----------------------------------------------\n";
    }
}
