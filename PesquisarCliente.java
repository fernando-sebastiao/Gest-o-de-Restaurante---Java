import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;
import Calendario.*;

public class PesquisarCliente extends JFrame {
    PainelCentro centro;
    PainelSul sul;

    public PesquisarCliente() {
        super("Pesquisar Cliente");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel {
        JTextField nomeField;
        JTextField idField;

        public PainelCentro() {
            setLayout(new GridLayout(3, 2, 10, 10));

                add(new JLabel("Digite o Nome:"));
                nomeField = new JTextField();
                add(nomeField);

            add(new JLabel("Ou digite o ID:"));
            idField = new JTextField();
            add(idField);
        }

        public String getNomeProcurado() {
            return nomeField.getText().trim();
        }

        public String getIdProcurado() {
            return idField.getText().trim();
        }
    }

    class PainelSul extends JPanel implements ActionListener {
        JButton pesquisarJB, cancelarJB;

        public PainelSul() {
            add(pesquisarJB = new JButton("Pesquisar", new ImageIcon("image/search32.png")));
            add(cancelarJB = new JButton("Cancelar", new ImageIcon("image/cancel24.png")));

            pesquisarJB.addActionListener(this);
            cancelarJB.addActionListener(this);
        }

        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == pesquisarJB) {
                ClienteModelo modelo = null;

                String idTexto = centro.getIdProcurado();
                if (!idTexto.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idTexto);
                        modelo = ClienteFile.getClientePorId(id);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "ID inválido.");
                        return;
                    }
                } else {
                    String nome = centro.getNomeProcurado();
                    modelo = ClienteFile.getClientePorNome(nome);
                }

                if (modelo != null && modelo.getStatus()) {
                    JOptionPane.showMessageDialog(null, modelo.toString(), "Cliente Encontrado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado ou inativo.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                dispose();
            }
        }
    }
}
