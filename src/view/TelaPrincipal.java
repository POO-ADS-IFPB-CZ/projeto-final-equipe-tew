package view;

import dao.ConteudoDao;
import model.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JDialog {
    private JPanel contentPane;
    private JButton loginButton;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField nameField;
    private JLabel labelNome;
    private Usuario usuarioLogado;
    private ConteudoDao dao;

    public TelaPrincipal() {
        dao = new ConteudoDao();
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(loginButton);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Login");
        labelNome.setVisible(false);
        nameField.setVisible(false);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onLogin();
            }
        });
    }

    private void onLogin() {
        String email = emailField.getText();
        String senha = new String(passwordField.getPassword());
        String nome = nameField.getText();

        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(TelaPrincipal.this, "Email e senha devem ser preenchidos!");
            return;
        }

        usuarioLogado = dao.autenticarOuCadastrarUsuario(email, senha, nome);

        if (usuarioLogado == null) {
            if (dao.buscarUsuario(email) == null) {
                JOptionPane.showMessageDialog(TelaPrincipal.this, "Nome é obrigatório para novos cadastros!");
                labelNome.setVisible(true);
                nameField.setVisible(true);
                pack();
            } else {
                JOptionPane.showMessageDialog(TelaPrincipal.this, "Email ou senha incorretos! Tente novamente.");
            }
        } else {
            JOptionPane.showMessageDialog(TelaPrincipal.this, "Login realizado com sucesso!");

            // Abrir a tela de conteúdos
            TelaConteudos telaConteudos = new TelaConteudos(usuarioLogado);
            telaConteudos.pack();
            telaConteudos.setVisible(true);

            dispose();
        }
    }
    public static void main(String[] args) {
        TelaPrincipal dialog = new TelaPrincipal();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}

