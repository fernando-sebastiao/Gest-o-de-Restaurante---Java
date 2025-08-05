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
        JRadioButton pesquisarPorNomeJRB, pesquisarPorIdJRB;
        ButtonGroup grupo;
        JTextField nomeProdutoJTF, idProdutoJTF;

        public PainelCentro() {
            setLayout(new GridLayout(3, 2, 10, 10));

            grupo = new ButtonGroup();

            pesquisarPorNomeJRB = new JRadioButton("Pesquisar por Nome do Produto", true);
            pesquisarPorIdJRB = new JRadioButton("Pesquisar por ID do Produto");

            grupo.add(pesquisarPorNomeJRB);
            grupo.add(pesquisarPorIdJRB);

            nomeProdutoJTF = new JTextField();
            idProdutoJTF = new JTextField();
            idProdutoJTF.setEnabled(false); // Desabilitado inicialmente

            add(pesquisarPorNomeJRB);
            add(pesquisarPorIdJRB);

            add(new JLabel("Nome do Produto:"));
            add(nomeProdutoJTF);

            add(new JLabel("ID do Produto:"));
            add(idProdutoJTF);

            // Escuta para habilitar/desabilitar os campos conforme rádio selecionado
            pesquisarPorNomeJRB.addActionListener(this);
            pesquisarPorIdJRB.addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (pesquisarPorNomeJRB.isSelected()) {
                nomeProdutoJTF.setEnabled(true);
                idProdutoJTF.setEnabled(false);
                idProdutoJTF.setText("");
            } else {
                nomeProdutoJTF.setEnabled(false);
                nomeProdutoJTF.setText("");
                idProdutoJTF.setEnabled(true);
            }
        }

        public String getNomeProduto() {
            return nomeProdutoJTF.getText().trim();
        }

        public int getIdProduto() {
            try {
                return Integer.parseInt(idProdutoJTF.getText().trim());
            } catch (NumberFormatException e) {
                return -1; // Indica id inválido
            }
        }

        public boolean isPesquisaPorNome() {
            return pesquisarPorNomeJRB.isSelected();
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

        @Override
        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == pesquisarJB) {
                ProdutoModelo modelo = null;

                if (centro.isPesquisaPorNome()) {
                    String nome = centro.getNomeProduto();
                    if (nome.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, digite o nome do produto.");
                        return;
                    }
                    modelo = ProdutoFile.getProdutoPorNome(nome);
                } else {
                    int id = centro.getIdProduto();
                    if (id == -1) {
                        JOptionPane.showMessageDialog(null, "Por favor, digite um ID válido.");
                        return;
                    }
                    modelo = ProdutoFile.getProdutoPorId(id);
                }

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
