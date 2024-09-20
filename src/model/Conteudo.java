package model;

import java.io.Serializable;
import java.util.Objects;

public abstract class Conteudo implements Serializable {
    protected String titulo;
    protected int anoLancamento;
    protected String genero;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Conteudo(String titulo, int anoLancamento, String genero) {
        this.titulo = titulo;
        this.anoLancamento = anoLancamento;
        this.genero = genero;
    }

    public String getTitulo() {
        return titulo;
    }

    public void exibirDetalhes() {
        System.out.println("Título: " + titulo);
        System.out.println("Ano de Lançamento: " + anoLancamento);
        System.out.println("Gênero: " + genero);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Conteudo conteudo = (Conteudo) obj;
        return anoLancamento == conteudo.anoLancamento &&
                Objects.equals(titulo, conteudo.titulo) &&
                Objects.equals(genero, conteudo.genero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, anoLancamento, genero);
    }

}
