/*------------------------------------
Tema: Gestão de um Restaurante
Nome: Fernando Afonso Sebastiao
Numero: 34422
Ficheiro: EditarCliente.java
Data: 10.07.2025
--------------------------------------*/

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
        JComboBox nomesJCB;
        /* JTextField bilheteJTF; */
        JRadioButton pesquisarPorNomeJRB, pesquisarPorBIJRB;
        ButtonGroup group;

        public PainelCentro() {
            setLayout(new GridLayout(3, 2, 10, 10));

            group = new ButtonGroup();

            add(pesquisarPorNomeJRB = new JRadioButton("Pesquisar Por Nome", true));
            /* add(pesquisarPorBIJRB = new JRadioButton("Pesquisar Por BI", false)); */
            group.add(pesquisarPorNomeJRB);
            /* group.add(pesquisarPorBIJRB); */

            add(new JLabel("Escolha o Nome Procurado"));
            nomesJCB = new JComboBox(ClienteFile.getAllNomesClientes());
            add(nomesJCB);

            /* add(new JLabel("Digite o número do BI"));
            bilheteJTF = new JTextField();
            bilheteJTF.setEnabled(false);
            add(bilheteJTF); */

            pesquisarPorNomeJRB.addActionListener(this);
            /* pesquisarPorBIJRB.addActionListener(this); */
        }

        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == pesquisarPorNomeJRB) {
                nomesJCB.setEnabled(true);
                /* bilheteJTF.setEnabled(false); */
            } else {
                nomesJCB.setEnabled(false);
                /* bilheteJTF.setEnabled(true); */
            }
        }

        public int getTipoPesquisa() {
            return pesquisarPorNomeJRB.isSelected() ? 1 : 2;
        }

        public String getNomeProcurado() {
            return String.valueOf(nomesJCB.getSelectedItem());
        }

        /* public String getBIProcurado() {
            return bilheteJTF.getText().trim();
        } */
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
                ClienteModelo modelo = null;

                if (centro.getTipoPesquisa() == 1) {
                    modelo = ClienteFile.getClientePorNome(centro.getNomeProcurado());
                }/*  else {
                    modelo = ClienteFile.getClientePorBI(centro.getBIProcurado());
                } */

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
