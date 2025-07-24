/*------------------------------------
Tema: Gestão de uma Barbearia
Nome: Enio Manuel
Número: 2817
Ficheiro: EliminarReserva.java
Data: 10.07.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class EliminarReserva extends JFrame {

    PainelCentro centro;
    PainelSul sul;

    public EliminarReserva() {
        super("Pesquisar para Eliminar Reserva");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel implements ActionListener {
        JComboBox clientesJCB;
        JRadioButton pesquisarPorClienteJRB;
        ButtonGroup group;

        public PainelCentro() {
            setLayout(new GridLayout(2, 2, 10, 10));

            group = new ButtonGroup();

            add(pesquisarPorClienteJRB = new JRadioButton("Pesquisar por Cliente", true));
            group.add(pesquisarPorClienteJRB);

            add(new JLabel("Escolha o Cliente"));
            add(clientesJCB = new JComboBox(ReservaFile.getAllClientesReservas()));

            pesquisarPorClienteJRB.addActionListener(this);
        }

        public void actionPerformed(ActionEvent evt) {
            // Nada necessário aqui, pois só há um modo de pesquisa
        }

        public String getClienteProcurado() {
            return String.valueOf(clientesJCB.getSelectedItem());
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
                ReservaModelo modelo = ReservaFile.getReservaPorCliente(centro.getClienteProcurado());

                if (modelo != null && modelo.getStatus()) {
                    JOptionPane.showMessageDialog(null, modelo.toString());

                    int opcao = JOptionPane.showConfirmDialog(
                        null,
                        "Tem certeza que deseja eliminar esta reserva?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION
                    );

                    if (opcao == JOptionPane.YES_OPTION) {
                        new ReservaFile().eliminarDados(modelo);
                    } else {
                        JOptionPane.showMessageDialog(null, "Operação cancelada pelo utilizador.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Reserva não encontrada ou já eliminada.");
                }
            } else {
                dispose();
            }
        }
    }
}
