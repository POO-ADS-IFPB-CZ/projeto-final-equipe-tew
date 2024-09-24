package view;

import dao.ConteudoDao;
import model.Conteudo;
import model.Filme;
import model.Serie;
import model.Usuario;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class TelaConteudos extends JDialog {
    private JPanel contentPane;
    private JButton buttonCadastrarConteudo;
    private JButton buttonAvaliarConteudo;
    private JButton buttonFavoritos;
    private JList<String> listConteudos; // Exibe a lista de conteúdos
    private JButton suaContaButton;
    private Usuario usuario;
    private ConteudoDao dao;

    public TelaConteudos(Usuario usuario) {
        this.usuario = usuario;
        dao = new ConteudoDao();
        setContentPane(contentPane);
        setModal(true);
        setTitle("Conteúdos disponíveis");

        // Ações dos botões
        buttonCadastrarConteudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre a tela de cadastro
                TelaCadastroConteudo telaCadastro = new TelaCadastroConteudo(usuario);
                telaCadastro.pack();
                telaCadastro.setVisible(true);
            }
        });

        buttonAvaliarConteudo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre a tela de avaliação
                TelaAvaliarConteudo telaAvaliar = new TelaAvaliarConteudo(usuario);
                telaAvaliar.pack();
                telaAvaliar.setVisible(true);
            }
        });
        suaContaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre a tela de avaliação
                TelaUsuario telaUsuario = new TelaUsuario(usuario);
                telaUsuario.pack();
                telaUsuario.setVisible(true);
            }
        });

        buttonFavoritos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre a tela de favoritos
                TelaFavoritos telaFavoritos = new TelaFavoritos(usuario);
                telaFavoritos.pack();
                telaFavoritos.setVisible(true);
            }
        });

        // Adicionar um WindowListener para carregar os conteúdos ao abrir a janela
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                carregarConteudos();
                pack();// Carregar os conteúdos quando a janela for aberta
            }
            public void windowActivated(WindowEvent e) {
                carregarConteudos();
                pack();
            }
        });
    }

    private void carregarConteudos() {
        List<Conteudo> conteudos = dao.carregarConteudos();

        DefaultListModel<String> model = new DefaultListModel<>();

        for (Conteudo conteudo : conteudos) {
            if (conteudo instanceof Filme) {
                Filme filme = (Filme) conteudo;
                String formatado = String.format("%s - %d, %s, %d min, Diretor: %s",
                        filme.getTitulo(),
                        filme.getAnoLancamento(),
                        filme.getGenero(),
                        filme.getDuracao(),
                        filme.getDiretor());
                model.addElement(formatado);
            } else if (conteudo instanceof Serie) {
                Serie serie = (Serie) conteudo;
                String formatado = String.format("%s - %d, %s, %d Temporadas, %d Episódios",
                        serie.getTitulo(),
                        serie.getAnoLancamento(),
                        serie.getGenero(),
                        serie.getNumeroTemporadas(),
                        serie.getNumeroEpisodios());
                model.addElement(formatado);
            }
        }

        listConteudos.setModel(model); // Atualiza a JList com o conteúdo formatado
    }

}
