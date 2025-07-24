/*------------------------------------
Tema: Gestão de um Restaurante
Nome: Fernando Afonso Sebastiao
Numero: 34422
Ficheiro: EditarVenda.java
Data: 14.07.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class EditarVenda extends JFrame {
    PainelCentro centro;
    PainelSul sul;

    public EditarVenda() {
        super("Pesquisar para Editar Venda");

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
            setLayout(new GridLayout(3, 2));

            group = new ButtonGroup();

            add(pesquisarPorNomeJRB = new JRadioButton("Pesquisar Por Nome do Cliente", true));
            add(pesquisarPorIdJRB = new JRadioButton("Pesquisar Por ID da Venda", false));
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
            add(pesquisarJB = new JButton("Pesquisar", new ImageIcon("icons/search32.png")));
            add(cancelarJB = new JButton("Cancelar"));

            pesquisarJB.addActionListener(this);
            cancelarJB.addActionListener(this);
        }

        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == pesquisarJB) {
                VendaModelo modelo = null;

                if (centro.getTipoPesquisa() == 1) {
                    // Pesquisa por nome do cliente
                    String nome = centro.getNomeCliente();
                    StringVector nomes = VendaFile.getAllClientes();
                    for (int i = 0; i < nomes.size(); i++) {
                        VendaModelo v = VendaFile.getVendaPorId(i + 1);
                        if (v != null && v.getNomeCliente().equalsIgnoreCase(nome) && v.getStatus()) {
                            modelo = v;
                            break;
                        }
                    }
                } else {
                    int id = centro.getIdVenda();
                    if (id > 0) {
                        modelo = VendaFile.getVendaPorId(id);
                    }
                }

                if (modelo != null && modelo.getStatus()) {
                    new VendaVisao(true, modelo); // abre a tela com dados preenchidos
                } else {
                    JOptionPane.showMessageDialog(null, "Venda não encontrada ou inativa.");
                }
            } else {
                dispose();
            }
        }
    }
}
