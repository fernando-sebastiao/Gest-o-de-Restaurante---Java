import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class EditarCliente extends JFrame {
    PainelCentro centro;
    PainelSul sul;

    public EditarCliente() {
        super("Pesquisar para Editar Cliente");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel implements ActionListener {
        JRadioButton pesquisarPorNomeJRB, pesquisarPorIDJRB;
        ButtonGroup group;
        JTextField nomeJTF, idJTF;

        public PainelCentro() {
            setLayout(new GridLayout(3, 2, 10, 10));

            group = new ButtonGroup();

            pesquisarPorNomeJRB = new JRadioButton("Pesquisar Por Nome", true);
            pesquisarPorIDJRB = new JRadioButton("Pesquisar Por ID");

            group.add(pesquisarPorNomeJRB);
            group.add(pesquisarPorIDJRB);

            nomeJTF = new JTextField();
            idJTF = new JTextField();
            idJTF.setEnabled(false); // inicialmente desabilitado

            add(pesquisarPorNomeJRB);
            add(pesquisarPorIDJRB);

            add(new JLabel("Nome do Cliente:"));
            add(nomeJTF);

            add(new JLabel("ID do Cliente:"));
            add(idJTF);

            pesquisarPorNomeJRB.addActionListener(this);
            pesquisarPorIDJRB.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (pesquisarPorNomeJRB.isSelected()) {
                nomeJTF.setEnabled(true);
                idJTF.setEnabled(false);
                idJTF.setText("");
            } else {
                nomeJTF.setEnabled(false);
                nomeJTF.setText("");
                idJTF.setEnabled(true);
            }
        }

        public boolean isPesquisaPorNome() {
            return pesquisarPorNomeJRB.isSelected();
        }

        public String getNomeProcurado() {
            return nomeJTF.getText().trim();
        }

        public String getIDProcurado() {
            return idJTF.getText().trim();
        }
    }

    class PainelSul extends JPanel implements ActionListener {
        JButton pesquisarJB, cancelarJB;

        public PainelSul() {
            add(pesquisarJB = new JButton("Pesquisar", new ImageIcon("icons/search32.png")));
            add(cancelarJB = new JButton("Cancelar"));

            pesquisarJB.addActionListener(this);
            cancelarJB.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == pesquisarJB) {
                ClienteModelo modelo = null;

                if (centro.isPesquisaPorNome()) {
                    String nome = centro.getNomeProcurado();
                    if (nome.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, digite o nome do cliente.");
                        return;
                    }
                    modelo = ClienteFile.getClientePorNome(nome);
                } else {
                    String idStr = centro.getIDProcurado();
                    if (idStr.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, digite o ID do cliente.");
                        return;
                    }
                    try {
                        int id = Integer.parseInt(idStr);
                        modelo = ClienteFile.getClientePorId(id);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "ID inválido. Digite um número inteiro.");
                        return;
                    }
                }

                if (modelo != null) {
                    new ClienteVisao(true, modelo);
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
                }
            } else {
                dispose();
            }
        }
    }
}
