import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;

public class EliminarCliente extends JFrame {

    PainelCentro centro;
    PainelSul sul;

    public EliminarCliente() {
        super("Pesquisar para Eliminar Cliente");

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

            add(new JLabel("Digite o Nome do Cliente:"));
            nomeField = new JTextField();
            add(nomeField);

            add(new JLabel("Ou insira o ID do Cliente:"));
            idField = new JTextField();
            add(idField);

            // Ativa/desativa o campo ID conforme preenchimento do nome
            nomeField.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent e) {
                    if (!nomeField.getText().trim().isEmpty()) {
                        idField.setEnabled(false);
                    } else {
                        idField.setEnabled(true);
                    }
                }
            });

            // Ativa/desativa o campo nome conforme preenchimento do ID
            idField.addKeyListener(new KeyAdapter() {
                public void keyReleased(KeyEvent e) {
                    if (!idField.getText().trim().isEmpty()) {
                        nomeField.setEnabled(false);
                    } else {
                        nomeField.setEnabled(true);
                    }
                }
            });
        }

        public String getNomeProcurado() {
            return nomeField.getText().trim();
        }

        public String getIdDigitado() {
            return idField.getText().trim();
        }
    }

    class PainelSul extends JPanel implements ActionListener {
        JButton eliminarJB, cancelarJB;

        public PainelSul() {
            add(eliminarJB = new JButton("Eliminar", new ImageIcon("image/delete32.png")));
            add(cancelarJB = new JButton("Cancelar", new ImageIcon("image/cancel.png")));

            eliminarJB.addActionListener(this);
            cancelarJB.addActionListener(this);
        }

        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == eliminarJB) {
                ClienteModelo modelo = null;

                String idTexto = centro.getIdDigitado();
                if (!idTexto.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idTexto);
                        modelo = ClienteFile.getClientePorId(id);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "ID inválido. Insira apenas números.");
                        return;
                    }
                } else {
                    modelo = ClienteFile.getClientePorNome(centro.getNomeProcurado());
                }

                if (modelo != null && modelo.getStatus()) {
                    JOptionPane.showMessageDialog(null, modelo.toString());

                    int opcao = JOptionPane.showConfirmDialog(null,
                            "Tem certeza que deseja eliminar este cliente?",
                            "Confirmação", JOptionPane.YES_NO_OPTION);

                    if (opcao == JOptionPane.YES_OPTION) {
                        new ClienteFile().eliminarDados(modelo);
                    } else {
                        JOptionPane.showMessageDialog(null, "Operação cancelada pelo utilizador.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado ou já eliminado.");
                }
            } else {
                dispose();
            }
        }
    }
}
