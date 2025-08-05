import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class FrmDefesa extends JFrame {
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private VendaFile vendaFile;  // Aqui, uso VendaFile para ler vendas

    private JTextField pesquisarTF;
    private JButton pesquisarJB;

    public FrmDefesa() {
        super("Lista de Defesas");
        setLayout(new BorderLayout(10, 10));

        vendaFile = new VendaFile();  // Instancia o arquivo de vendas

        // Painel para pesquisa
        JPanel painelPesquisa = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pesquisarTF = new JTextField(20);
        pesquisarJB = new JButton("Pesquisar");
        painelPesquisa.add(new JLabel("Pesquisar Defesa:"));
        painelPesquisa.add(pesquisarTF);
        painelPesquisa.add(pesquisarJB);

        add(painelPesquisa, BorderLayout.NORTH);

        // Definindo colunas da tabela
        String[] colunas = {
            "ID", "Cliente", "Produto", "Quantidade",
            "Pagamento", "Funcionário", "Data", "Valor Total"
        };

        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Desativa edição das células
            }
        };

        tabela = new JTable(modeloTabela);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createTitledBorder("Defesas Registradas"));

        add(scroll, BorderLayout.CENTER);

        carregarDadosTabela();

        // Ação do botão pesquisar
        pesquisarJB.addActionListener(e -> pesquisar());

        setSize(900, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void carregarDadosTabela() {
        modeloTabela.setRowCount(0); // Limpa tabela

        ArrayList<VendaModelo> lista = vendaFile.listarDados();  // Usando método do VendaFile
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

    private void pesquisar() {
        String termo = pesquisarTF.getText().trim().toLowerCase();
        if (termo.isEmpty()) {
            carregarDadosTabela();
            return;
        }

        modeloTabela.setRowCount(0); // Limpa tabela

        ArrayList<VendaModelo> lista = vendaFile.listarDados();
        for (VendaModelo vm : lista) {
            if (vm.getNomeCliente().toLowerCase().contains(termo)) {
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
    }
}
