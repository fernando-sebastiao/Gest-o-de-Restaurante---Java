import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class FrmDefesa extends JFrame {
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private VendaFile vendaFile;

    private JTextField pesquisarTF;
    private JButton pesquisarJB;

    public FrmDefesa() {
        super("Lista de Defesas");
        setLayout(new BorderLayout(10, 10));

        vendaFile = new VendaFile();

        // Painel de pesquisa
        JPanel painelPesquisa = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pesquisarTF = new JTextField(20);
        pesquisarJB = new JButton("Pesquisar (Excluir Data)");

        painelPesquisa.add(new JLabel("Digite a Data a Ser Excluída:"));
        painelPesquisa.add(pesquisarTF);
        painelPesquisa.add(pesquisarJB);

        add(painelPesquisa, BorderLayout.NORTH);

        // Colunas da tabela
        String[] colunas = {
            "ID", "Cliente", "Produto", "Quantidade",
            "Pagamento", "Funcionário", "Data", "Valor Total"
        };

        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabela = new JTable(modeloTabela);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createTitledBorder("Defesas Registradas"));

        add(scroll, BorderLayout.CENTER);

        carregarDadosTabela(); // Carrega todos inicialmente

        // Ação do botão
        pesquisarJB.addActionListener(e -> filtrarPorDataDiferente());

        setSize(900, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    // Carrega todos os dados
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

    // Filtra os registros com data diferente
    private void filtrarPorDataDiferente() {
        String dataExcluida = pesquisarTF.getText().trim().toLowerCase();
        if (dataExcluida.isEmpty()) {
            carregarDadosTabela();
            return;
        }

        modeloTabela.setRowCount(0);

        ArrayList<VendaModelo> lista = vendaFile.listarDados();
        for (VendaModelo vm : lista) {
            if (!vm.getDataVenda().toLowerCase().equals(dataExcluida)) {
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
