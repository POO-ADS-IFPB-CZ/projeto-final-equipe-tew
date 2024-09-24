package view;

import dao.ConteudoDao;
import model.Conteudo;
import model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAdicionarAosFavoritos extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textTitulo;
    private Usuario usuario;
    private ConteudoDao dao;

    public TelaAdicionarAosFavoritos(Usuario usuario) {
        this.usuario = usuario;
        dao = new ConteudoDao();

        setContentPane(contentPane);
        setModal(true);
        setLocation(600, 300);
        getRootPane().setDefaultButton(buttonOK);
        setTitle("Adicionar Favorito");


        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = textTitulo.getText();
                Conteudo conteudoParaFavoritar = dao.carregarConteudos()
                        .stream()
                        .filter(c -> c.getTitulo().equalsIgnoreCase(titulo))
                        .findFirst()
                        .orElse(null);

                if (conteudoParaFavoritar != null) {
                    usuario.adicionarAosFavoritos(conteudoParaFavoritar);
                    dao.salvarUsuarios();
                    JOptionPane.showMessageDialog(TelaAdicionarAosFavoritos.this, "Adicionado com sucesso!");
                }
                else {
                    JOptionPane.showMessageDialog(TelaAdicionarAosFavoritos.this, "Conteúdo não encontrado!");
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
}
