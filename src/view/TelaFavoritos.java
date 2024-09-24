package view;

import model.Conteudo;
import model.Filme;
import model.Serie;
import model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class TelaFavoritos extends JDialog {
    private  Usuario usuario;
    private JPanel contentPane;
    private JButton buttonAdicionar;
    private JButton buttonRemover;
    private JButton cancelarButton;
    private JList<String> listFavoritos;

    public TelaFavoritos(Usuario usuario) {
        this.usuario = usuario;
        setContentPane(contentPane);
        setModal(true);
        setLocation(600, 300);
        setTitle("Favoritos");
        getRootPane().setDefaultButton(buttonAdicionar);



        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                carregarFavoritos();
                pack();// Carregar lista de favoritos quando a janela for aberta
            }
            public void windowActivated(WindowEvent e) {
                carregarFavoritos();
                pack();
            }
        });

        buttonAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaAdicionarAosFavoritos telaAddFavorito = new TelaAdicionarAosFavoritos(usuario);
                telaAddFavorito.pack();
                telaAddFavorito.setVisible(true);
            }
        });
        buttonRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaRemoverFavorito telaRemoverFavorito = new TelaRemoverFavorito(usuario);
                telaRemoverFavorito.pack();
                telaRemoverFavorito.setVisible(true);
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public void carregarFavoritos(){
        List<Conteudo> favoritosList = usuario.getFavoritos();

        DefaultListModel<String> model = new DefaultListModel<>();

        for (Conteudo conteudo : favoritosList) {
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

        listFavoritos.setModel(model);


    }
}
