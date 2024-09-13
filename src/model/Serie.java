package model;

public class Serie extends Conteudo {
    private int numeroTemporadas;
    private int numeroEpisodios;

    public Serie(String titulo, int anoLancamento, String genero, int numeroTemporadas, int numeroEpisodios) {
        super(titulo, anoLancamento, genero);
        this.numeroTemporadas = numeroTemporadas;
        this.numeroEpisodios = numeroEpisodios;
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
        System.out.println("Número de Temporadas: " + numeroTemporadas);
        System.out.println("Número de Episódios: " + numeroEpisodios);
    }
}