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

        // Exibe as informações do usuário nos labels
        labelNome.setText("Nome: " + usuario.getNome());
        labelEmail.setText("Email: " + usuario.getEmail());

        // Adiciona evento para carregar os conteúdos vistos ao abrir a janela
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                // Chama o método para obter a lista de conteúdos vistos
                List<String> conteudosVistos = usuario.exibirConteudosVistos();

                // Define o modelo da JList com os conteúdos vistos
                list1.setListData(conteudosVistos.toArray(new String[0]));
                pack(); // Ajusta novamente o tamanho da janela
            }
        });

        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Define o comportamento de fechar
        pack(); // Ajusta o tamanho da janela de acordo com o conteúdo
        setVisible(true); // Exibe a janela
    }
}