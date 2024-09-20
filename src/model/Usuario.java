package model;

import java.io.Serializable;
import java.util.*;

public class Usuario implements Serializable {
    private String nome;
    private String email;
    private String senha;
    private List<Conteudo> favoritos;
    private Map<Conteudo, Double> conteudosVistos;

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

    public void avaliarConteudo(Conteudo conteudo, double nota) {
        if (conteudosVistos.containsKey(conteudo)) {
            System.out.println("Você já avaliou este conteúdo. Deseja editar a nota? (S/N)");
            Scanner scanner = new Scanner(System.in);
            String resposta = scanner.nextLine();

            if (resposta.equalsIgnoreCase("S")) {
                editarNotaConteudo(conteudo, nota);
            }
            else {
                System.out.println("Avaliação não alterada.");
            }
        }
        else {
            conteudosVistos.put(conteudo, (double) nota);
            System.out.println("Conteúdo avaliado com sucesso!");
        }
    }

    public void exibirFavoritos() {
        System.out.println("Favoritos de " + nome + ":");
        for (Conteudo conteudo : favoritos) {
            conteudo.exibirDetalhes();
        }
    }

    public void exibirConteudosVistos() {
        System.out.println("Conteúdos vistos por " + nome + ":");
        for (Map.Entry<Conteudo, Double> entry : conteudosVistos.entrySet()) {
            Conteudo conteudo = entry.getKey();
            double nota = entry.getValue();
            System.out.println("Título: " + conteudo.getTitulo() + " | Nota: " + nota);
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Conteudo> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Conteudo> favoritos) {
        this.favoritos = favoritos;
    }

    public Map<Conteudo, Double> getConteudosVistos() {
        return conteudosVistos;
    }

    public void setConteudosVistos(Map<Conteudo, Double> conteudosVistos) {
        this.conteudosVistos = conteudosVistos;
    }

    public void removerDosFavoritos(Conteudo conteudo) {
        if (favoritos.contains(conteudo)) {
            favoritos.remove(conteudo);
            System.out.println(conteudo.getTitulo() + " foi removido dos favoritos.");
        } else {
            System.out.println(conteudo.getTitulo() + " não está na lista de favoritos.");
        }
    }

    public void editarNotaConteudo(Conteudo conteudo, double novaNota) {
        if (conteudosVistos.containsKey(conteudo)) {
            conteudosVistos.put(conteudo, novaNota);
            System.out.println("Nota atualizada com sucesso para " + conteudo.getTitulo() + " com nota " + novaNota);
        } else {
            System.out.println("Você não avaliou este conteúdo.");
        }
    }

}
