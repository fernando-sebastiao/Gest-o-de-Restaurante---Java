/*------------------------------------
Tema: Gestão de um Restaurante
Nome: Fernando Afonso Sebastiao
Numero: 34422
Ficheiro: VendaVisao.java
Data: 10.07.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.UIManager.*;
import SwingComponents.*;
import Calendario.*;

public class VendaVisao extends JFrame {
    private PainelCentro centro;
    private PainelSul sul;
    private boolean editar;

    public VendaVisao(boolean alterar, VendaModelo modelo) {
        super("Cadastro de Nova Venda");

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
        private JTextField idJTF, quantidadeJTF, valorTotalJTF;
        private JComboBoxPersonal nomeClienteJCB, nomeProdutoJCB, formaPagamentoJCB, funcionarioJCB;
        private JTextFieldData txtData;
        private VendaFile vendaFile;

        public PainelCentro() {
            setLayout(new GridLayout(5, 4, 10, 5));
            vendaFile = new VendaFile();

            add(new JLabel("ID:"));
            add(idJTF = new JTextField("000" + vendaFile.getProximoCodigo()));
            idJTF.setFocusable(false);

            add(new JLabel("Cliente:"));
            add(nomeClienteJCB = UInterfaceBox.createJComboBoxsTabela2("NomeCLiente.tab"));

            add(new JLabel("Produto:"));
            add(nomeProdutoJCB = UInterfaceBox.createJComboBoxsTabela2("NomeProduto.tab"));

            add(new JLabel("Quantidade:"));
            add(quantidadeJTF = new JTextField());

            add(new JLabel("Forma de Pagamento:"));
            add(formaPagamentoJCB = UInterfaceBox.createJComboBoxsTabela2("MetodoPagamento.tab"));

            add(new JLabel("Funcionário:"));
            add(funcionarioJCB = UInterfaceBox.createJComboBoxsTabela2("NomeFuncionario.tab"));

            add(new JLabel("Data da Venda:"));
            JPanel painelData = new JPanel(new GridLayout(1, 1));
            txtData = new JTextFieldData("Data?");
            painelData.add(txtData.getDTestField());
            painelData.add(txtData.getDButton());
            add(painelData);

            add(new JLabel("Valor Total:"));
            add(valorTotalJTF = new JTextField());
        }

        public PainelCentro(VendaModelo modelo) {
            this();
            idJTF.setText("000" + modelo.getId());
            nomeClienteJCB.setSelectedItem(modelo.getNomeCliente());
            nomeProdutoJCB.setSelectedItem(modelo.getNomeProduto());
            quantidadeJTF.setText(String.valueOf(modelo.getQuantidade()));
            formaPagamentoJCB.setSelectedItem(modelo.getFormaPagamento());
            funcionarioJCB.setSelectedItem(modelo.getFuncionario());
            txtData.getDTestField().setText(modelo.getDataVenda());
            valorTotalJTF.setText(String.valueOf(modelo.getValorTotal()));
        }

        public int getId() { return Integer.parseInt(idJTF.getText().trim()); }
        public String getNomeCliente() { return String.valueOf(nomeClienteJCB.getSelectedItem()); }
        public String getNomeProduto() { return String.valueOf(nomeProdutoJCB.getSelectedItem()); }
        public int getQuantidade() { return Integer.parseInt(quantidadeJTF.getText().trim()); }
        public String getFormaPagamento() { return String.valueOf(formaPagamentoJCB.getSelectedItem()); }
        public String getFuncionario() { return String.valueOf(funcionarioJCB.getSelectedItem()); }
        public String getDataVenda() { return txtData.getDTestField().getText(); }
        public float getValorTotal() { return Float.parseFloat(valorTotalJTF.getText().trim()); }

        public void salvar() {
            VendaModelo modelo = new VendaModelo(
                getId(), getNomeCliente(), getNomeProduto(), getQuantidade(),
                getFormaPagamento(), getFuncionario(), getDataVenda(), getValorTotal()
            );
            JOptionPane.showMessageDialog(null, modelo.toString());
            modelo.salvar();
            dispose();
        }

        public void alterar() {
            VendaModelo modelo = new VendaModelo(
                getId(), getNomeCliente(), getNomeProduto(), getQuantidade(),
                getFormaPagamento(), getFuncionario(), getDataVenda(), getValorTotal()
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
        } catch (Exception e) {}
    }
}
