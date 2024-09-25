package view;

import model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class TelaUsuario extends JDialog {
    private JPanel contentPane;
    private JList<String> list1;  // JList para exibir os conteúdos vistos
    private JLabel labelNome;
    private JLabel labelEmail;

    public TelaUsuario(Usuario usuario) {
        setContentPane(contentPane);
        setModal(true);
        setLocation(600, 300);

        labelNome.setText("Nome: " + usuario.getNome());
        labelEmail.setText("Email: " + usuario.getEmail());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                List<String> conteudosVistos = usuario.exibirConteudosVistos();
                list1.setListData(conteudosVistos.toArray(new String[0]));
                pack();
            }
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }
}