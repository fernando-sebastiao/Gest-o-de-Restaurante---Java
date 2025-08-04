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

    class PainelCentro extends JPanel {
        JTextField nomeClienteJTF, idVendaJTF;

        public PainelCentro() {
            setLayout(new GridLayout(2, 2, 10, 10));

            add(new JLabel("Digite o Nome do Cliente:"));
            nomeClienteJTF = new JTextField();
            add(nomeClienteJTF);

            add(new JLabel("Digite o ID da Venda:"));
            idVendaJTF = new JTextField();
            add(idVendaJTF);
        }

        public String getNomeCliente() {
            return nomeClienteJTF.getText().trim();
        }

        public String getIdVendaTexto() {
            return idVendaJTF.getText().trim();
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
                VendaFile vendaFile = new VendaFile();

                String nome = centro.getNomeCliente();
                String idTexto = centro.getIdVendaTexto();

                if (!idTexto.isEmpty()) {
                    // tenta eliminar pelo ID
                    int id;
                    try {
                        id = Integer.parseInt(idTexto);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "ID inválido.");
                        return;
                    }
                    modelo = VendaFile.getVendaPorId(id);

                    if (modelo != null && modelo.getStatus()) {
                        int opcao = JOptionPane.showConfirmDialog(null,
                                "Venda encontrada:\n" + modelo.toString() +
                                "\nTem certeza que deseja eliminar esta venda?",
                                "Confirmação", JOptionPane.YES_NO_OPTION);
                        if (opcao == JOptionPane.YES_OPTION) {
                            vendaFile.eliminarDados(modelo);
                        } else {
                            JOptionPane.showMessageDialog(null, "Operação cancelada pelo utilizador.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Venda não encontrada ou já eliminada.");
                    }
                } else if (!nome.isEmpty()) {
                    // elimina pelo nome
                    modelo = VendaFile.getVendaPorNome(nome);

                    if (modelo != null && modelo.getStatus()) {
                        int opcao = JOptionPane.showConfirmDialog(null,
                                "Venda encontrada:\n" + modelo.toString() +
                                "\nTem certeza que deseja eliminar esta venda?",
                                "Confirmação", JOptionPane.YES_NO_OPTION);
                        if (opcao == JOptionPane.YES_OPTION) {
                            vendaFile.eliminarDadosPorNome(nome);
                        } else {
                            JOptionPane.showMessageDialog(null, "Operação cancelada pelo utilizador.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Venda não encontrada ou já eliminada.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, informe o Nome do Cliente ou o ID da Venda para eliminar.");
                }
            } else if (evt.getSource() == cancelarJB) {
                dispose();
            }
        }
    }
}
