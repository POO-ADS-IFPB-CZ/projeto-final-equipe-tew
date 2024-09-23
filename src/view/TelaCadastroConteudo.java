package view;

import dao.ConteudoDao;
import model.Conteudo;
import model.Filme;
import model.Serie;
import model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroConteudo extends JDialog {
    private Usuario usuario;
    private ConteudoDao dao;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JRadioButton filmeRadioButton;
    private JRadioButton serieRadioButton;
    private JTextField textTitulo;
    private JTextField textLancamento;
    private JTextField textDiretor;
    private JTextField textDuracao;
    private JTextField textTemporadas;
    private JTextField textEpisodios;
    private JLabel labelTitulo;
    private JLabel labelLancamento;
    private JLabel labelDiretor;
    private JLabel labelDuracao;
    private JLabel labelTemporadas;
    private JLabel labelEpisodios;
    private JPanel panel1;

    public TelaCadastroConteudo(Usuario usuario) {
        setTitle("Cadastrar Novo Conteúdo");
        this.usuario = usuario;
        dao = new ConteudoDao();
        setContentPane(contentPane);
        setModal(true);
        pack();
        getRootPane().setDefaultButton(buttonOK);

        ocultarCampos();

        // Define a ação para os botões de "Filme" e "Série"
        filmeRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exibirCamposFilme();
                pack();
            }
        });

        serieRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exibirCamposSerie();
                pack();
            }
        });
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = textTitulo.getText();
                int lancamento = Integer.parseInt(textLancamento.getText());

                if(filmeRadioButton.isSelected()){
                    String genero = "Filme";
                    String diretor = textDiretor.getText();
                    int duracao = Integer.parseInt(textDuracao.getText());

                    Filme novoFilme = new Filme(titulo, lancamento, genero, duracao, diretor);
                    dao.adicionarConteudo(novoFilme);
                } else if(serieRadioButton.isSelected()){
                    String genero = "Serie";
                    int temporadas = Integer.parseInt(textTemporadas.getText());
                    int episodios = Integer.parseInt(textEpisodios.getText());

                    Serie novaSerie = new Serie(titulo, lancamento, genero, temporadas, episodios);
                    dao.adicionarConteudo(novaSerie);
                }

                dispose();
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }


    private void exibirCamposFilme() {
        // Exibe campos específicos para "Filme"
        labelDiretor.setVisible(true);
        textDiretor.setVisible(true);
        labelDuracao.setVisible(true);
        textDuracao.setVisible(true);

        // Oculta campos de "Série"
        labelTemporadas.setVisible(false);
        textTemporadas.setVisible(false);
        labelEpisodios.setVisible(false);
        textEpisodios.setVisible(false);
    }

    private void exibirCamposSerie() {
        // Exibe campos específicos para "Série"
        labelTemporadas.setVisible(true);
        textTemporadas.setVisible(true);
        labelEpisodios.setVisible(true);
        textEpisodios.setVisible(true);

        // Oculta campos de "Filme"
        labelDiretor.setVisible(false);
        textDiretor.setVisible(false);
        labelDuracao.setVisible(false);
        textDuracao.setVisible(false);
    }

    private void ocultarCampos() {
        // Oculta todos os campos de filme e série inicialmente
        labelDiretor.setVisible(false);
        textDiretor.setVisible(false);
        labelDuracao.setVisible(false);
        textDuracao.setVisible(false);
        labelTemporadas.setVisible(false);
        textTemporadas.setVisible(false);
        labelEpisodios.setVisible(false);
        textEpisodios.setVisible(false);
    }

}
