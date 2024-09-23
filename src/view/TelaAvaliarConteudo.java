package view;

import dao.ConteudoDao;
import model.Usuario;
import model.Conteudo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAvaliarConteudo extends JDialog {
    private JPanel contentPane;
    private JTextField nomeConteudoField;
    private JSpinner notaSpinner;
    private JButton buttonSalvar;
    private Usuario usuario;
    private ConteudoDao dao;
    private Conteudo conteudo;
    public TelaAvaliarConteudo(Usuario usuario) {
        this.usuario = usuario;
        dao = new ConteudoDao();

        setContentPane(contentPane);
        setModal(true);

        SpinnerNumberModel model = new SpinnerNumberModel(0.0, 0.0, 5.0, 0.1);
        notaSpinner.setModel(model);
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(notaSpinner, "0.0");
        notaSpinner.setEditor(editor);

        buttonSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeConteudo = nomeConteudoField.getText();
                double nota = (Double) notaSpinner.getValue();

                Conteudo conteudoParaAvaliar = dao.carregarConteudos()
                        .stream()
                        .filter(c -> c.getTitulo().equalsIgnoreCase(nomeConteudo))
                        .findFirst()
                        .orElse(null);


                if (conteudoParaAvaliar != null) {
                    if(usuario.getConteudosVistos().containsKey(conteudoParaAvaliar)){
                        int resposta = JOptionPane.showConfirmDialog(contentPane,
                                "Você já avaliou este conteúdo. Deseja mudar a nota?",
                                "Confirmar alteração",
                                JOptionPane.YES_NO_OPTION);

                        if (resposta == JOptionPane.YES_OPTION) {
                            usuario.editarNotaConteudo(conteudoParaAvaliar, nota);
                            JOptionPane.showMessageDialog(contentPane, "Nota atualizada com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(contentPane, "Avaliação não alterada.");
                        }
                    }
                    else {
                        usuario.avaliarConteudo(conteudoParaAvaliar, nota);
                        JOptionPane.showMessageDialog(contentPane, "Avaliação salva com sucesso!");
                    }
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Conteúdo não encontrado.");
                }

                dispose();
            }
        });
    }
}
