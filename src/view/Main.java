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
            System.out.println("1. Avaliar Conteúdo");
            System.out.println("2. Adicionar aos Favoritos");
            System.out.println("3. Exibir Favoritos");
            System.out.println("4. Exibir Conteúdos Vistos");
            System.out.println("5. Pesquisar ou Cadastrar Conteúdo");
            System.out.println("6. Exibir Todos os Conteúdos Cadastrados");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
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
                        scanner.nextLine();
                        usuario.avaliarConteudo(conteudoParaAvaliar, nota);
                        conteudoDAO.salvarUsuarios();
                    }
                    else {
                        System.out.println("Conteúdo não encontrado!");
                    }
                    break;

                case 2:
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
                    }
                    else {
                        System.out.println("Conteúdo não encontrado!");
                    }
                    break;

                case 3:
                    usuario.exibirFavoritos();
                    break;

                case 4:
                    usuario.exibirConteudosVistos();
                    break;

                case 5:
                    System.out.print("Digite o título do conteúdo a ser pesquisado: ");
                    String tituloPesquisa = scanner.nextLine();
                    Conteudo conteudoPesquisado = conteudoDAO.carregarConteudos()
                            .stream()
                            .filter(c -> c.getTitulo().equalsIgnoreCase(tituloPesquisa))
                            .findFirst()
                            .orElse(null);

                    if (conteudoPesquisado != null) {
                        System.out.println("Conteúdo encontrado!");
                        conteudoPesquisado.exibirDetalhes();
                    }
                    else {
                        System.out.println("Conteúdo não encontrado! Deseja cadastrá-lo? (S/N)");
                        String opcaoCadastro = scanner.nextLine();
                        if (opcaoCadastro.equalsIgnoreCase("S")) {
                            System.out.println("Escolha o tipo de conteúdo para cadastrar:");
                            System.out.println("1. Filme");
                            System.out.println("2. Série");
                            int tipoConteudo = scanner.nextInt();
                            scanner.nextLine();

                            if (tipoConteudo == 1) {
                                System.out.print("Ano de Lançamento: ");
                                int anoFilmeNovo = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Gênero: ");
                                String generoFilmeNovo = scanner.nextLine();
                                System.out.print("Duração (minutos): ");
                                int duracaoFilmeNovo = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Diretor: ");
                                String diretorFilmeNovo = scanner.nextLine();

                                Filme filmeNovo = new Filme(tituloPesquisa, anoFilmeNovo, generoFilmeNovo, duracaoFilmeNovo, diretorFilmeNovo);
                                conteudoDAO.adicionarConteudo(filmeNovo);
                                System.out.println("Filme cadastrado com sucesso!");
                            }
                            else if (tipoConteudo == 2) {
                                System.out.print("Ano de Lançamento: ");
                                int anoSerieNova = scanner.nextInt();
                                scanner.nextLine();
                                System.out.print("Gênero: ");
                                String generoSerieNova = scanner.nextLine();
                                System.out.print("Número de Temporadas: ");
                                int temporadasSerieNova = scanner.nextInt();
                                System.out.print("Número de Episódios: ");
                                int episodiosSerieNova = scanner.nextInt();
                                scanner.nextLine();

                                Serie serieNova = new Serie(tituloPesquisa, anoSerieNova, generoSerieNova, temporadasSerieNova, episodiosSerieNova);
                                conteudoDAO.adicionarConteudo(serieNova);
                                System.out.println("Série cadastrada com sucesso!");
                            }
                            else {
                                System.out.println("Tipo de conteúdo inválido.");
                            }
                        }
                    }
                    break;

                case 6:
                    System.out.println("Todos os conteúdos cadastrados:");
                    conteudoDAO.carregarConteudos().forEach(Conteudo::exibirDetalhes);
                    break;

                case 7:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        }
        while (opcao != 7);

        scanner.close();
    }
}
