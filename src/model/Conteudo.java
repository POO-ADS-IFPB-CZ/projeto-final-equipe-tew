package model;

import java.io.Serializable;

public abstract class Conteudo implements Serializable {
    protected String titulo;
    protected int anoLancamento;
    protected String genero;
    protected double mediaAvaliacao;
    protected int totalAvaliacoes;
    protected int somaAvaliacoes;

    public Conteudo(String titulo, int anoLancamento, String genero) {
        this.titulo = titulo;
        this.anoLancamento = anoLancamento;
        this.genero = genero;
        this.mediaAvaliacao = 0.0;
        this.totalAvaliacoes = 0;
        this.somaAvaliacoes = 0;
    }

    public abstract void calcularMediaAvaliacao(int novaNota);

    public String getTitulo() {
        return titulo;
    }

    public void exibirDetalhes() {
        System.out.println("Título: " + titulo);
        System.out.println("Ano de Lançamento: " + anoLancamento);
        System.out.println("Gênero: " + genero);
        System.out.println("Média de Avaliação: " + mediaAvaliacao);
    }
}
