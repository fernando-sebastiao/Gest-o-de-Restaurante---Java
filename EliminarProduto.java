/*------------------------------------
Tema: Gestão de uma Barbearia
Nome: Adolfo Cabeia
Número: 31671
Ficheiro: EliminarProduto.java
Data: 10.07.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

    class PainelCentro extends JPanel {
        JTextField nomeProdutoTF;
        JTextField idProdutoTF;

        public PainelCentro() {
            setLayout(new GridLayout(4, 1, 10, 10));

            add(new JLabel("Digite o ID do Produto:"));
            add(idProdutoTF = new JTextField(20));

            add(new JLabel("Ou digite o Nome do Produto:"));
            add(nomeProdutoTF = new JTextField(20));
        }

        public String getProdutoNome() {
            return nomeProdutoTF.getText().trim();
        }

        public String getProdutoId() {
            return idProdutoTF.getText().trim();
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
                String idText = centro.getProdutoId();
                String nome = centro.getProdutoNome();

                ProdutoModelo modelo = null;

                if (!idText.isEmpty()) {
                    try {
                        int id = Integer.parseInt(idText);
                        modelo = ProdutoFile.getProdutoPorId(id);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "ID inválido. Digite apenas números.");
                        return;
                    }
                } else if (!nome.isEmpty()) {
                    modelo = ProdutoFile.getProdutoPorNome(nome);
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, digite o ID ou o nome do produto.");
                    return;
                }

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
