package view;

import javax.swing.*;

public class TelaPrincipal extends JFrame {
    private JPanel contentPane;
    private JButton buttonEntar;
    private JButton buttonCancel;
    private JTextField textEmail;
    private JTextField textSenha;
    private JButton cadastrarButton;

    public TelaPrincipal() {
        setContentPane(contentPane);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Avaliações");
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(buttonEntar);
    }

    public static void main(String[] args) {
        TelaPrincipal dialog = new TelaPrincipal();
        dialog.pack();
        dialog.setVisible(true);
    }
}
