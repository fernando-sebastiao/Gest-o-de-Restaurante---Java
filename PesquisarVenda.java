/*------------------------------------
Tema: Gestão de um Restaurante
Nome: Fernando Afonso Sebastiao
Numero: 34422
Ficheiro: ClienteModelo.java
Data: 10.07.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class PesquisarVenda extends JFrame {
    PainelCentro centro;
    PainelSul sul;

    public PesquisarVenda() {
        super("Pesquisar Venda");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel implements ActionListener {
        JComboBox nomesClientesJCB;
        JTextField idVendaJTF;
        JRadioButton pesquisarPorNomeJRB, pesquisarPorIdJRB;
        ButtonGroup group;

        public PainelCentro() {
            setLayout(new GridLayout(3, 2, 10, 10));

            group = new ButtonGroup();

            add(pesquisarPorNomeJRB = new JRadioButton("Pesquisar por Nome do Cliente", true));
            add(pesquisarPorIdJRB = new JRadioButton("Pesquisar por ID da Venda", false));
            group.add(pesquisarPorNomeJRB);
            group.add(pesquisarPorIdJRB);

            add(new JLabel("Escolha o Nome do Cliente:"));
            add(nomesClientesJCB = new JComboBox(VendaFile.getAllClientes()));

            add(new JLabel("Digite o ID da Venda:"));
            add(idVendaJTF = new JTextField());
            idVendaJTF.setEnabled(false);

            pesquisarPorNomeJRB.addActionListener(this);
            pesquisarPorIdJRB.addActionListener(this);
        }

        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == pesquisarPorNomeJRB) {
                nomesClientesJCB.setEnabled(true);
                idVendaJTF.setEnabled(false);
            } else {
                nomesClientesJCB.setEnabled(false);
                idVendaJTF.setEnabled(true);
            }
        }

        public int getTipoPesquisa() {
            return pesquisarPorNomeJRB.isSelected() ? 1 : 2;
        }

        public String getNomeCliente() {
            return String.valueOf(nomesClientesJCB.getSelectedItem());
        }

        public int getIdVenda() {
            try {
                return Integer.parseInt(idVendaJTF.getText().trim());
            } catch (NumberFormatException e) {
                return -1;
            }
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
                if (centro.getTipoPesquisa() == 1) {
                    String nome = centro.getNomeCliente();
                    StringVector nomes = VendaFile.getAllClientes();
                    for (int i = 0; i < nomes.size(); i++) {
                        VendaModelo v = VendaFile.getVendaPorId(i + 1);
                        if (v != null && v.getNomeCliente().equalsIgnoreCase(nome) && v.getStatus()) {
                            JOptionPane.showMessageDialog(null, v.toString(), "Venda Encontrada", JOptionPane.INFORMATION_MESSAGE);
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Venda não encontrada para este cliente.");
                } else {
                    int id = centro.getIdVenda();
                    if (id > 0) {
                        VendaModelo modelo = VendaFile.getVendaPorId(id);
                        if (modelo != null && modelo.getStatus()) {
                            JOptionPane.showMessageDialog(null, modelo.toString(), "Venda Encontrada", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Venda não encontrada ou inativa.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "ID inválido.");
                    }
                }
            } else {
                dispose();
            }
        }
    }
}
