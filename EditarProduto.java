/*------------------------------------
Tema: Gestão de um Restaurante
Nome: Fernando Afonso Sebastiao
Numero: 34422
Ficheiro: EditarProduto.java
Data: 10.07.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;

public class EditarProduto extends JFrame {
    PainelCentro centro;
    PainelSul sul;

    public EditarProduto() {
        super("Pesquisar para Editar Produto");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel implements ActionListener {
        JComboBox produtosJCB;
        JRadioButton pesquisarPorNomeJRB;
        ButtonGroup grupo;

        public PainelCentro() {
            setLayout(new GridLayout(2, 2, 10, 10));

            grupo = new ButtonGroup();

            add(pesquisarPorNomeJRB = new JRadioButton("Pesquisar Por Nome do Produto", true));
            grupo.add(pesquisarPorNomeJRB);

            add(new JLabel("Escolha o Produto:"));
            add(produtosJCB = new JComboBox(ProdutoFile.getAllProdutos()));

            pesquisarPorNomeJRB.addActionListener(this);
        }

        public void actionPerformed(ActionEvent e) {
            // Apenas um tipo de pesquisa (por nome do produto), nenhuma ação necessária aqui
        }

        public String getProdutoProcurado() {
            return String.valueOf(produtosJCB.getSelectedItem());
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
                ProdutoModelo modelo = ProdutoFile.getProdutoPorNome(centro.getProdutoProcurado());

                if (modelo != null) {
                    new ProdutoVisao(true, modelo);
                } else {
                    JOptionPane.showMessageDialog(null, "Produto não encontrado ou inativo.");
                }
            } else {
                dispose();
            }
        }
    }
}
