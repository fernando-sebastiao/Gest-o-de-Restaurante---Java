/*------------------------------------
Tema: Gestão de uma Barbearia
Nome: Adolfo Cabeia
Número: 31671
Ficheiro: EliminarProduto.java
Data: 10.07.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;

public class EliminarProduto extends JFrame {

    PainelCentro centro;
    PainelSul sul;

    public EliminarProduto() {
        super("Pesquisar para Eliminar Produto");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel implements ActionListener {
        JComboBox produtosJCB;
        JRadioButton pesquisarPorNomeJRB;
        ButtonGroup group;

        public PainelCentro() {
            setLayout(new GridLayout(2, 2, 10, 10));

            group = new ButtonGroup();

            add(pesquisarPorNomeJRB = new JRadioButton("Pesquisar por Nome do Produto", true));
            group.add(pesquisarPorNomeJRB);

            add(new JLabel("Escolha o Produto"));
            add(produtosJCB = new JComboBox(ProdutoFile.getAllProdutos()));

            pesquisarPorNomeJRB.addActionListener(this);
        }

        public void actionPerformed(ActionEvent evt) {
            // Apenas uma forma de pesquisa, nada a fazer aqui
        }

        public String getProdutoProcurado() {
            return String.valueOf(produtosJCB.getSelectedItem());
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
                ProdutoModelo modelo = ProdutoFile.getProdutoPorNome(centro.getProdutoProcurado());

                if (modelo != null && modelo.getStatus()) {
                    JOptionPane.showMessageDialog(null, modelo.toString());

                    int opcao = JOptionPane.showConfirmDialog(
                        null,
                        "Tem certeza que deseja eliminar este produto?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION
                    );

                    if (opcao == JOptionPane.YES_OPTION) {
                        new ProdutoFile().eliminarDados(modelo);
                    } else {
                        JOptionPane.showMessageDialog(null, "Operação cancelada pelo utilizador.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Produto não encontrado ou já eliminado.");
                }
            } else {
                dispose();
            }
        }
    }
}
