package br.com.segundaQuestao.modelo;

public class Filme {
    private int id;
    private String nome;
    private String descricao;
    private int ano;

    public Filme(int id, String nome, String descricao, int ano) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.ano = ano;
    }

    @Override
    public String toString() {
        return "\nFilme: " + this.nome + "\nDescrição: " + this.descricao + "\nAno: " + this.ano +
                "\n\n--------------------------------------------------------------";
    }
}
