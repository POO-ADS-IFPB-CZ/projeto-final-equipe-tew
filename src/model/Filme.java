package model;

public class Filme extends Conteudo {
    private int duracao;
    private String diretor;

    public Filme(String titulo, int anoLancamento, String genero, int duracao, String diretor) {
        super(titulo, anoLancamento, genero);
        this.duracao = duracao;
        this.diretor = diretor;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    @Override
    public void exibirDetalhes() {
        super.exibirDetalhes();
        System.out.println("Duração: " + duracao + " minutos");
        System.out.println("Diretor: " + diretor);
    }
}