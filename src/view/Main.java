package view;

import dao.ConteudoDao;
import model.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConteudoDao conteudoDAO = new ConteudoDao();

        Usuario usuario = null;
        while (usuario == null) {
            System.out.print("Digite seu e-mail: ");
            String email = scanner.nextLine();
            System.out.print("Digite sua senha: ");
            String senha = scanner.nextLine();

            usuario = conteudoDAO.autenticarOuCadastrarUsuario(email, senha);
            if (usuario == null) {
                System.out.println("E-mail ou senha incorretos. Tente novamente.");
            }
        }

        int opcao;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Cadastrar Filme");
            System.out.println("2. Cadastrar Série");
            System.out.println("3. Avaliar Conteúdo");
            System.out.println("4. Adicionar aos Favoritos");
            System.out.println("5. Exibir Favoritos");
            System.out.println("6. Exibir Conteúdos Vistos");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Título do Filme: ");
                    String tituloFilme = scanner.nextLine();
                    System.out.print("Ano de Lançamento: ");
                    int anoFilme = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Gênero: ");
                    String generoFilme = scanner.nextLine();
                    System.out.print("Duração (minutos): ");
                    int duracao = scanner.nextInt();

                    Filme filme = new Filme(tituloFilme, anoFilme, generoFilme, duracao);
                    conteudoDAO.adicionarConteudo(filme);
                    System.out.println("Filme cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.print("Título da Série: ");
                    String tituloSerie = scanner.nextLine();
                    System.out.print("Ano de Lançamento: ");
                    int anoSerie = scanner.nextInt();
                    scanner.nextLine();  // Consumir a quebra de linha
                    System.out.print("Gênero: ");
                    String generoSerie = scanner.nextLine();
                    System.out.print("Número de Temporadas: ");
                    int temporadas = scanner.nextInt();
                    System.out.print("Número de Episódios: ");
                    int episodios = scanner.nextInt();

                    Serie serie = new Serie(tituloSerie, anoSerie, generoSerie, temporadas, episodios);
                    conteudoDAO.adicionarConteudo(serie);
                    System.out.println("Série cadastrada com sucesso!");
                    break;

                case 3:
                    System.out.print("Digite o título do conteúdo a ser avaliado: ");
                    String tituloAvaliacao = scanner.nextLine();
                    Conteudo conteudoParaAvaliar = conteudoDAO.carregarConteudos()
                            .stream()
                            .filter(c -> c.getTitulo().equalsIgnoreCase(tituloAvaliacao))
                            .findFirst()
                            .orElse(null);

                    if (conteudoParaAvaliar != null) {
                        System.out.print("Digite a nota (1-5): ");
                        int nota = scanner.nextInt();
                        usuario.avaliarConteudo(conteudoParaAvaliar, nota);
                        conteudoDAO.salvarUsuarios();
                    } else {
                        System.out.println("Conteúdo não encontrado!");
                    }
                    break;

                case 4:
                    System.out.print("Digite o título do conteúdo a ser adicionado aos favoritos: ");
                    String tituloFavorito = scanner.nextLine();
                    Conteudo conteudoParaFavoritar = conteudoDAO.carregarConteudos()
                            .stream()
                            .filter(c -> c.getTitulo().equalsIgnoreCase(tituloFavorito))
                            .findFirst()
                            .orElse(null);

                    if (conteudoParaFavoritar != null) {
                        usuario.adicionarAosFavoritos(conteudoParaFavoritar);
                        conteudoDAO.salvarUsuarios();
                    } else {
                        System.out.println("Conteúdo não encontrado!");
                    }
                    break;

                case 5:
                    usuario.exibirFavoritos();
                    break;

                case 6:
                    usuario.exibirConteudosVistos();
                    break;

                case 7:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 7);

        scanner.close();
    }
}
