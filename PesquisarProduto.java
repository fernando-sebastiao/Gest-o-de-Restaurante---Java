/*------------------------------------
Tema: Gestão de um Restaurante
Nome: Fernando Afonso Sebastiao
Numero: 34422
Ficheiro: PesquisarProduto.java
Data: 04.08.2025
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
        private JTextField nomeProdutoTF;

        public PainelCentro() {
            setLayout(new GridLayout(1, 2, 10, 10));

            add(new JLabel("Nome do Produto:"));
            nomeProdutoTF = new JTextField(20);
            add(nomeProdutoTF);
        }

        public String getProdutoProcurado() {
            return nomeProdutoTF.getText().trim();
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
                String nome = centro.getProdutoProcurado();
                ProdutoModelo modelo = ProdutoFile.getProdutoPorNome(nome);

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
