/*------------------------------------
Tema: Gestão de um Restaurante
Nome: Fernando Afonso Sebastiao
Numero: 34422
Ficheiro: EditarReserva.java
Data: 10.07.2025
--------------------------------------*/

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
        JComboBox clientesJCB;
        JRadioButton pesquisarPorClienteJRB;
        ButtonGroup grupo;

        public PainelCentro() {
            setLayout(new GridLayout(2, 2, 10, 10));

            grupo = new ButtonGroup();

            add(pesquisarPorClienteJRB = new JRadioButton("Pesquisar Por Cliente", true));
            grupo.add(pesquisarPorClienteJRB);

            add(new JLabel("Escolha o Cliente:"));
            add(clientesJCB = new JComboBox(ReservaFile.getAllClientesReservas()));

            pesquisarPorClienteJRB.addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            // Somente um tipo de pesquisa (por cliente), nada a alterar
        }

        public String getClienteProcurado() {
            return String.valueOf(clientesJCB.getSelectedItem());
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

        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == pesquisarJB) {
                ReservaModelo modelo = ReservaFile.getReservaPorCliente(centro.getClienteProcurado());

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
