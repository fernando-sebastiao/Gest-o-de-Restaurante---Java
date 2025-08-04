import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;

public class PesquisarReserva extends JFrame {
    PainelCentro centro;
    PainelSul sul;

    public PesquisarReserva() {
        super("Pesquisar Reserva");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel implements ActionListener {
        JTextField nomeClienteJTF;
        JTextFieldData dataJTF;
        JRadioButton pesquisarPorNomeJRB, pesquisarPorDataJRB;
        ButtonGroup group;

        public PainelCentro() {
            setLayout(new GridLayout(3, 2, 10, 10));

            group = new ButtonGroup();

            pesquisarPorNomeJRB = new JRadioButton("Pesquisar por Nome do Cliente", true);
            pesquisarPorDataJRB = new JRadioButton("Pesquisar por Data da Reserva", false);

            group.add(pesquisarPorNomeJRB);
            group.add(pesquisarPorDataJRB);

            add(pesquisarPorNomeJRB);
            add(pesquisarPorDataJRB);

            add(new JLabel("Digite o Nome do Cliente:"));
            nomeClienteJTF = new JTextField();
            add(nomeClienteJTF);

            add(new JLabel("Escolha a Data da Reserva:"));
            dataJTF = new JTextFieldData("dd/mm/yyyy");
            add(dataJTF.getDTestField());
            add(dataJTF.getDButton());

            // Inicialmente, campo data desabilitado
            dataJTF.getDTestField().setEnabled(false);
            dataJTF.getDButton().setEnabled(false);

            pesquisarPorNomeJRB.addActionListener(this);
            pesquisarPorDataJRB.addActionListener(this);
        }

        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == pesquisarPorNomeJRB) {
                nomeClienteJTF.setEnabled(true);
                dataJTF.getDTestField().setEnabled(false);
                dataJTF.getDButton().setEnabled(false);
            } else if (evt.getSource() == pesquisarPorDataJRB) {
                nomeClienteJTF.setEnabled(false);
                dataJTF.getDTestField().setEnabled(true);
                dataJTF.getDButton().setEnabled(true);
            }
        }

        public int getTipoPesquisa() {
            return pesquisarPorNomeJRB.isSelected() ? 1 : 2;
        }

        public String getClienteProcurado() {
            return nomeClienteJTF.getText().trim();
        }

        public String getDataProcurada() {
            return dataJTF.getDTestField().getText().trim();
        }
    }

    class PainelSul extends JPanel implements ActionListener {
        JButton pesquisarJB, cancelarJB;

        public PainelSul() {
            pesquisarJB = new JButton("Pesquisar", new ImageIcon("image/search32.png"));
            cancelarJB = new JButton("Cancelar", new ImageIcon("image/cancel24.png"));

            add(pesquisarJB);
            add(cancelarJB);

            pesquisarJB.addActionListener(this);
            cancelarJB.addActionListener(this);
        }

        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == pesquisarJB) {
                if (centro.getTipoPesquisa() == 1) {
                    // Pesquisar por nome do cliente
                    String cliente = centro.getClienteProcurado();
                    if (cliente.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, digite o nome do cliente.");
                        return;
                    }
                    ReservaModelo modelo = ReservaFile.getReservaPorCliente(cliente);
                    if (modelo != null && modelo.getStatus()) {
                        JOptionPane.showMessageDialog(null, modelo.toString(), "Reserva Encontrada", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Reserva não encontrada ou inativa para este cliente.");
                    }
                } else {
                    // Pesquisar por data da reserva
                    String data = centro.getDataProcurada();
                    if (data.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, informe uma data válida.");
                        return;
                    }
                    ReservaModelo modelo = ReservaFile.getReservaPorData(data);
                    if (modelo != null && modelo.getStatus()) {
                        JOptionPane.showMessageDialog(null, modelo.toString(), "Reserva Encontrada", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Reserva não encontrada ou inativa para esta data.");
                    }
                }
            } else {
                dispose();
            }
        }
    }
}
