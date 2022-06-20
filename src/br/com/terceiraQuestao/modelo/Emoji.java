package br.com.terceiraQuestao.modelo;

import java.util.Scanner;

public class Emoji {
    private final String divertido = ":-)";
    private final String chateado = ":-(";
    Scanner entrada;

    public Emoji() {
        System.out.println("Fale uma frase com emojis e a maquina adivinhara como você está: ");
        entrada = new Scanner(System.in);
        String resposta = entrada.nextLine();

        String humor = verificaHumor(verificaPessoaFeliz(resposta), verificaPessoaChateada(resposta));
        System.out.println(humor);
    }

    public int verificaPessoaChateada(String rspTriste) {
        int contador;
        int vezesChateado = 0;

        contador = rspTriste.indexOf(this.chateado);

        while (contador != -1) {
            vezesChateado++;
            rspTriste = rspTriste.substring(contador + 3);
            contador = rspTriste.indexOf(this.chateado);
        }
        return vezesChateado;
    }

    public int verificaPessoaFeliz(String rspFeliz) {
        int contador;
        int vezesDivertido = 0;

        contador = rspFeliz.indexOf(this.divertido);

        while (contador != -1) {
            vezesDivertido++;
            rspFeliz = rspFeliz.substring(contador + 3);
            contador = rspFeliz.indexOf(this.divertido);
        }
        return vezesDivertido;
    }

    public String verificaHumor(int feliz, int triste) {
        if (feliz > triste) {
            return "divertido";
        }
        else if (feliz < triste) {
            return "chateado";
        }
        return "neutro";
    }
}
