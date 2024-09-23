package view;

import model.Usuario;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TelaFavoritos extends JDialog {
    private JPanel contentPane;
    private JButton buttonAdicionar;
    private JButton buttonRemover;
    private JButton cancelarButton;
    private JList list1;

    public TelaFavoritos(Usuario usuario) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonAdicionar);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                carregarFavoritos();
                pack();// Carregar os conteúdos quando a janela for aberta
            }
            public void windowActivated(WindowEvent e) {
                carregarFavoritos();
                pack();
            }
        });

    }

    public void carregarFavoritos(){

    }
}
