/*------------------------------------
Tema: Gestão de um Video Clube
Nome: Adolfo Cabeia
Numero: 31671
Ficheiro: EliminarVenda.java
Data: 14.07.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class EliminarVenda extends JFrame {

    PainelCentro centro;
    PainelSul sul;

    public EliminarVenda() {
        super("Pesquisar para Eliminar Venda");

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

            add(pesquisarPorNomeJRB = new JRadioButton("Pesquisar por Cliente", true));
            add(pesquisarPorIdJRB = new JRadioButton("Pesquisar por ID da Venda", false));
            group.add(pesquisarPorNomeJRB);
            group.add(pesquisarPorIdJRB);

            add(new JLabel("Escolha o Nome do Cliente"));
            add(nomesClientesJCB = new JComboBox(VendaFile.getAllClientes()));

            add(new JLabel("Digite o ID da Venda"));
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
                return -1; // valor inválido
            }
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
                VendaModelo modelo = null;

                if (centro.getTipoPesquisa() == 1) {
                    // Pesquisa por nome do cliente
                    String nome = centro.getNomeCliente();
                    StringVector nomes = VendaFile.getAllClientes();
                    for (int i = 0; i < nomes.size(); i++) {
                        VendaModelo v = VendaFile.getVendaPorId(i + 1); // ids supostos sequenciais
                        if (v != null && v.getNomeCliente().equalsIgnoreCase(nome)) {
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
                    JOptionPane.showMessageDialog(null, modelo.toString());

                    int opcao = JOptionPane.showConfirmDialog(null,
                            "Tem certeza que deseja eliminar esta venda?",
                            "Confirmação", JOptionPane.YES_NO_OPTION);

                    if (opcao == JOptionPane.YES_OPTION) {
                        new VendaFile().eliminarDados(modelo);
                    } else {
                        JOptionPane.showMessageDialog(null, "Operação cancelada pelo utilizador.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Venda não encontrada ou já eliminada.");
                }
            } else {
                dispose();
            }
        }
    }
}
