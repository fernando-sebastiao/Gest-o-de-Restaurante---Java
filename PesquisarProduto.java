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

public class PesquisarProduto extends JFrame {
    PainelCentro centro;
    PainelSul sul;

    public PesquisarProduto() {
        super("Pesquisar Produto");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel {
        JComboBox produtosJCB;

        public PainelCentro() {
            setLayout(new GridLayout(1, 2, 10, 10));

            add(new JLabel("Escolha o Produto:"));
            add(produtosJCB = new JComboBox(ProdutoFile.getAllProdutos()));
        }

        public String getProdutoProcurado() {
            return String.valueOf(produtosJCB.getSelectedItem());
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
                ProdutoModelo modelo = ProdutoFile.getProdutoPorNome(centro.getProdutoProcurado());

                if (modelo != null && modelo.getStatus()) {
                    JOptionPane.showMessageDialog(null, modelo.toString(), "Produto Encontrado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Produto não encontrado ou já eliminado.");
                }
            } else {
                dispose();
            }
        }
    }
}
