package model;

import java.io.Serializable;
import java.util.*;

public class Usuario implements Serializable {
    private String nome;
    private String email;
    private String senha;
    private List<Conteudo> favoritos;
    private Map<Conteudo, Integer> conteudosVistos;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.favoritos = new ArrayList<>();
        this.conteudosVistos = new HashMap<>();
    }

    public String getEmail() {
        return email;
    }

    public boolean verificarSenha(String senha) {
        return this.senha.equals(senha);
    }

    public void adicionarAosFavoritos(Conteudo conteudo) {
        if (!favoritos.contains(conteudo)) {
            favoritos.add(conteudo);
            System.out.println(conteudo.getTitulo() + " foi adicionado aos favoritos.");
        } else {
            System.out.println(conteudo.getTitulo() + " já está nos favoritos.");
        }
    }

    public void avaliarConteudo(Conteudo conteudo, int nota) {
        conteudosVistos.put(conteudo, nota);
        conteudo.calcularMediaAvaliacao(nota);
        System.out.println("Você avaliou " + conteudo.getTitulo() + " com nota " + nota);
    }

    public void exibirFavoritos() {
        System.out.println("Favoritos de " + nome + ":");
        for (Conteudo conteudo : favoritos) {
            conteudo.exibirDetalhes();
        }
    }

    public void exibirConteudosVistos() {
        System.out.println("Conteúdos vistos por " + nome + ":");
        for (Map.Entry<Conteudo, Integer> entry : conteudosVistos.entrySet()) {
            Conteudo conteudo = entry.getKey();
            int nota = entry.getValue();
            System.out.println("Título: " + conteudo.getTitulo() + " | Nota: " + nota);
        }
    }
}
