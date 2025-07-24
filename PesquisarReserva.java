/*------------------------------------
Tema: Gestão de uma Barbearia
Nome: Enio Manuel
Número: 2817
Ficheiro: PesquisarReserva.java
Data: 10.07.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

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

    class PainelCentro extends JPanel {
        JComboBox clientesJCB;

        public PainelCentro() {
            setLayout(new GridLayout(1, 2, 10, 10));

            add(new JLabel("Escolha o Cliente:"));
            add(clientesJCB = new JComboBox(ReservaFile.getAllClientesReservas()));
        }

        public String getClienteProcurado() {
            return String.valueOf(clientesJCB.getSelectedItem());
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
                ReservaModelo modelo = ReservaFile.getReservaPorCliente(centro.getClienteProcurado());

                if (modelo != null && modelo.getStatus()) {
                    JOptionPane.showMessageDialog(null, modelo.toString(), "Reserva Encontrada", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Reserva não encontrada ou inativa.");
                }
            } else {
                dispose();
            }
        }
    }
}
