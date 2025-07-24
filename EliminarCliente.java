/*------------------------------------
Tema: Gestão de um Restaurante
Nome: Fernando Afonso Sebastião
Numero: 34422
Ficheiro: EliminarCliente.java
Data: 26.06.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

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
        JComboBox nomesJCB;

        public PainelCentro() {
            setLayout(new GridLayout(2, 2, 10, 10));

            add(new JLabel("Escolha o Nome do Cliente"));
            nomesJCB = new JComboBox(ClienteFile.getAllNomesClientes());
            add(nomesJCB);
        }

        public String getNomeProcurado() {
            return String.valueOf(nomesJCB.getSelectedItem());
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
                ClienteModelo modelo = ClienteFile.getClientePorNome(centro.getNomeProcurado());

                if (modelo != null && modelo.getStatus()) {
                    JOptionPane.showMessageDialog(null, modelo.toString());

                    int opcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja eliminar este cliente?", "Confirmação", JOptionPane.YES_NO_OPTION);

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
