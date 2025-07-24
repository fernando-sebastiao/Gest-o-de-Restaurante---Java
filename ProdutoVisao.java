/*------------------------------------
Tema: Gestão de um Restaurante
Nome: Fernando Afonso Sebastiao
Numero: 34422
Ficheiro: ClienteModelo.java
Data: 10.07.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.UIManager.*;
import SwingComponents.*;

public class ProdutoVisao extends JFrame {
    private PainelCentro centro;
    private PainelSul sul;
    private boolean editar;

    public ProdutoVisao(boolean alterar, ProdutoModelo modelo) {
        super("Cadastro de Produto");

        editar = alterar;
        definirTema();

        JPanel painelNorte = new JPanel();
        getContentPane().add(painelNorte, BorderLayout.NORTH);

        if (!alterar)
            getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        else
            getContentPane().add(centro = new PainelCentro(modelo), BorderLayout.CENTER);

        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel {
        private JTextField idJTF, precoJTF;
        private JComboBox nomeJCB;
        private ProdutoFile produtoFile;

        public PainelCentro() {
            setLayout(new GridLayout(3, 2, 10, 10));
            produtoFile = new ProdutoFile();

            add(new JLabel("ID:"));
            idJTF = new JTextField("000" + produtoFile.getProximoCodigo());
            idJTF.setFocusable(false);
            add(idJTF);

            add(new JLabel("Nome do Produto:"));
            add(nomeJCB = UInterfaceBox.createJComboBoxsTabela2("NomeProduto.tab"));

            add(new JLabel("Preço (Kz):"));
            precoJTF = new JTextField();
            add(precoJTF);
        }

        public PainelCentro(ProdutoModelo modelo) {
            this();
            idJTF.setText("000" + modelo.getId());
            nomeJCB.setSelectedItem(modelo.getNomeProduto());
            precoJTF.setText(String.valueOf(modelo.getPreco()));
        }

        public int getId() { return Integer.parseInt(idJTF.getText().trim()); }
        public String getNomeProduto() { return String.valueOf(nomeJCB.getSelectedItem()); }
        public float getPreco() { return Float.parseFloat(precoJTF.getText().trim()); }

        public void salvar() {
            ProdutoModelo modelo = new ProdutoModelo(
                getId(),
                getNomeProduto(),
                getPreco(),
                true
            );
            JOptionPane.showMessageDialog(null, modelo.toString());
            modelo.salvar();
            dispose();
        }

        public void alterar() {
            ProdutoModelo modelo = new ProdutoModelo(
                getId(),
                getNomeProduto(),
                getPreco(),
                true
            );
            JOptionPane.showMessageDialog(null, modelo.toString());
            modelo.salvarDados();
            dispose();
        }
    }

    class PainelSul extends JPanel implements ActionListener {
        private JButton btnSalvar, btnCancelar;

        public PainelSul() {
            btnSalvar = new JButton("Salvar", new ImageIcon("image/save24.png"));
            btnCancelar = new JButton("Cancelar", new ImageIcon("image/cancel24.png"));

            btnSalvar.setBackground(Color.GREEN);
            btnSalvar.setForeground(Color.WHITE);

            btnCancelar.setBackground(Color.RED);
            btnCancelar.setForeground(Color.WHITE);

            add(btnSalvar);
            add(btnCancelar);

            btnSalvar.addActionListener(this);
            btnCancelar.addActionListener(this);
        }

        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == btnSalvar)
                if (editar)
                    centro.alterar();
                else
                    centro.salvar();
            else
                dispose();
        }
    }

    public void definirTema() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
