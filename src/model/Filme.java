package model;

public class Filme extends Conteudo {
    private int duracao;

    public Filme(String titulo, int anoLancamento, String genero, int duracao) {
        super(titulo, anoLancamento, genero);
        this.duracao = duracao;
    }

    @Override
    public void calcularMediaAvaliacao(int novaNota) {
        somaAvaliacoes += novaNota;
        totalAvaliacoes++;
        mediaAvaliacao = (double) somaAvaliacoes / totalAvaliacoes;
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Duração: " + duracao + " minutos");
    }
}