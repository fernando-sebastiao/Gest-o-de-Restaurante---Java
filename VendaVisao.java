import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.UIManager.*;
import javax.swing.table.DefaultTableModel;
import SwingComponents.*;
import Calendario.*;
import java.util.ArrayList;

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

        private JTable tabela;
        private DefaultTableModel modeloTabela;
        private JButton btnEditar, btnEliminar;

        public PainelCentro() {
            setLayout(new BorderLayout(10, 10));

            vendaFile = new VendaFile();

            // Painel do formulário
            JPanel painelForm = new JPanel(new GridLayout(5, 4, 10, 5));

            painelForm.add(new JLabel("ID:"));
            painelForm.add(idJTF = new JTextField("000" + vendaFile.getProximoCodigo()));
            idJTF.setFocusable(false);

            painelForm.add(new JLabel("Cliente:"));
            painelForm.add(nomeClienteJCB = UInterfaceBox.createJComboBoxsTabela2("NomeCLiente.tab"));

            painelForm.add(new JLabel("Produto:"));
            painelForm.add(nomeProdutoJCB = UInterfaceBox.createJComboBoxsTabela2("NomeProduto.tab"));

            painelForm.add(new JLabel("Quantidade:"));
            painelForm.add(quantidadeJTF = new JTextField());

            painelForm.add(new JLabel("Forma de Pagamento:"));
            painelForm.add(formaPagamentoJCB = UInterfaceBox.createJComboBoxsTabela2("MetodoPagamento.tab"));

            painelForm.add(new JLabel("Funcionário:"));
            painelForm.add(funcionarioJCB = UInterfaceBox.createJComboBoxsTabela2("NomeFuncionario.tab"));

            painelForm.add(new JLabel("Data da Venda:"));
            JPanel painelData = new JPanel(new GridLayout(1, 1));
            txtData = new JTextFieldData("Data?");
            painelData.add(txtData.getDTestField());
            painelData.add(txtData.getDButton());
            painelForm.add(painelData);

            painelForm.add(new JLabel("Valor Total:"));
            painelForm.add(valorTotalJTF = new JTextField());

            add(painelForm, BorderLayout.NORTH);

            // Tabela
            String[] colunas = { "ID", "Cliente", "Produto", "Quantidade", "Pagamento", "Funcionário", "Data", "Valor Total" };
            modeloTabela = new DefaultTableModel(colunas, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // Desabilita edição direta
                }
            };
            tabela = new JTable(modeloTabela);
            tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            JScrollPane scroll = new JScrollPane(tabela);
            scroll.setBorder(BorderFactory.createTitledBorder("Lista de Vendas"));
            add(scroll, BorderLayout.CENTER);

            // Painel dos botões editar e eliminar
            JPanel painelBotoes = new JPanel();
            btnEditar = new JButton("Editar");
            btnEliminar = new JButton("Eliminar");
            painelBotoes.add(btnEditar);
            painelBotoes.add(btnEliminar);
            add(painelBotoes, BorderLayout.SOUTH);

            carregarDadosTabela();

            // Ações dos botões
            btnEditar.addActionListener(e -> editarVenda());
            btnEliminar.addActionListener(e -> eliminarVenda());
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

        public int getId() { return Integer.parseInt(idJTF.getText().trim().replaceFirst("^0+", "")); }
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
            modelo.salvar();
            JOptionPane.showMessageDialog(null, "Venda salva com sucesso!");
            carregarDadosTabela();
            dispose();
        }

        public void alterar() {
            VendaModelo modelo = new VendaModelo(
                getId(), getNomeCliente(), getNomeProduto(), getQuantidade(),
                getFormaPagamento(), getFuncionario(), getDataVenda(), getValorTotal()
            );
            modelo.salvarDados();
            JOptionPane.showMessageDialog(null, "Venda alterada com sucesso!");
            carregarDadosTabela();
            dispose();
        }

        private void carregarDadosTabela() {
            modeloTabela.setRowCount(0);
            ArrayList<VendaModelo> lista = vendaFile.listarDados();
            for (VendaModelo vm : lista) {
                Object[] linha = {
                    vm.getId(),
                    vm.getNomeCliente(),
                    vm.getNomeProduto(),
                    vm.getQuantidade(),
                    vm.getFormaPagamento(),
                    vm.getFuncionario(),
                    vm.getDataVenda(),
                    vm.getValorTotal()
                };
                modeloTabela.addRow(linha);
            }
        }

        private void editarVenda() {
            int linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(null, "Selecione uma venda para editar.");
                return;
            }

            int idVenda = (int) modeloTabela.getValueAt(linhaSelecionada, 0);
            VendaModelo vm = VendaFile.getVendaPorId(idVenda);
            if (vm == null) {
                JOptionPane.showMessageDialog(null, "Venda não encontrada.");
                return;
            }
            // Abre nova janela de edição
            new VendaVisao(true, vm);
            dispose();
        }

        private void eliminarVenda() {
            int linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(null, "Selecione uma venda para eliminar.");
                return;
            }

            int idVenda = (int) modeloTabela.getValueAt(linhaSelecionada, 0);
            VendaModelo vm = VendaFile.getVendaPorId(idVenda);
            if (vm == null) {
                JOptionPane.showMessageDialog(null, "Venda não encontrada.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(null, "Confirma eliminar esta venda?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                VendaFile vf = new VendaFile();
                vf.eliminarDados(vm);
                carregarDadosTabela();
            }
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
        } catch (Exception e) {
            // ignorar exceções
        }
    }
}
