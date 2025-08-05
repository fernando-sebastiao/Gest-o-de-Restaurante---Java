import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class EditarReserva extends JFrame {
    PainelCentro centro;
    PainelSul sul;

    public EditarReserva() {
        super("Pesquisar para Editar Reserva");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel implements ActionListener {
        JRadioButton pesquisarPorClienteJRB, pesquisarPorIDJRB;
        ButtonGroup grupo;
        JTextField clienteJTF, idJTF;

        public PainelCentro() {
            setLayout(new GridLayout(3, 2, 10, 10));

            grupo = new ButtonGroup();

            pesquisarPorClienteJRB = new JRadioButton("Pesquisar Por Nome do Cliente", true);
            pesquisarPorIDJRB = new JRadioButton("Pesquisar Por ID da Reserva");

            grupo.add(pesquisarPorClienteJRB);
            grupo.add(pesquisarPorIDJRB);

            clienteJTF = new JTextField();
            idJTF = new JTextField();
            idJTF.setEnabled(false); // inicia desabilitado

            add(pesquisarPorClienteJRB);
            add(pesquisarPorIDJRB);

            add(new JLabel("Nome do Cliente:"));
            add(clienteJTF);

            add(new JLabel("ID da Reserva:"));
            add(idJTF);

            pesquisarPorClienteJRB.addActionListener(this);
            pesquisarPorIDJRB.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (pesquisarPorClienteJRB.isSelected()) {
                clienteJTF.setEnabled(true);
                idJTF.setEnabled(false);
                idJTF.setText("");
            } else {
                clienteJTF.setEnabled(false);
                clienteJTF.setText("");
                idJTF.setEnabled(true);
            }
        }

        public boolean isPesquisaPorCliente() {
            return pesquisarPorClienteJRB.isSelected();
        }

        public String getClienteProcurado() {
            return clienteJTF.getText().trim();
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
                ReservaModelo modelo = null;

                if (centro.isPesquisaPorCliente()) {
                    String cliente = centro.getClienteProcurado();
                    if (cliente.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, digite o nome do cliente.");
                        return;
                    }
                    modelo = ReservaFile.getReservaPorCliente(cliente);
                } else {
                    String idStr = centro.getIDProcurado();
                    if (idStr.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, digite o ID da reserva.");
                        return;
                    }
                    try {
                        int id = Integer.parseInt(idStr);
                        modelo = ReservaFile.getReservaPorId(id);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "ID inválido. Digite um número inteiro.");
                        return;
                    }
                }

                if (modelo != null) {
                    new ReservaVisao(true, modelo);
                } else {
                    JOptionPane.showMessageDialog(null, "Reserva não encontrada ou inativa.");
                }
            } else {
                dispose();
            }
        }
    }
}
