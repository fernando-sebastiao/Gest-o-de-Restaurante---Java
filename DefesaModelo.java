import java.io.Serializable;

public class DefesaModelo implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String nomeCliente;
    private String nomeProduto;
    private int quantidade;
    private String formaPagamento;
    private String funcionario;
    private String dataVenda;
    private float valorTotal;

    public DefesaModelo(int id, String nomeCliente, String nomeProduto, int quantidade,
                        String formaPagamento, String funcionario, String dataVenda, float valorTotal) {
        this.id = id;
        this.nomeCliente = nomeCliente;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.formaPagamento = formaPagamento;
        this.funcionario = funcionario;
        this.dataVenda = dataVenda;
        this.valorTotal = valorTotal;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNomeCliente() { return nomeCliente; }
    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }

    public String getNomeProduto() { return nomeProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public String getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(String formaPagamento) { this.formaPagamento = formaPagamento; }

    public String getFuncionario() { return funcionario; }
    public void setFuncionario(String funcionario) { this.funcionario = funcionario; }

    public String getDataVenda() { return dataVenda; }
    public void setDataVenda(String dataVenda) { this.dataVenda = dataVenda; }

    public float getValorTotal() { return valorTotal; }
    public void setValorTotal(float valorTotal) { this.valorTotal = valorTotal; }

    // Métodos para salvar/alterar podem ser adicionados se quiser
    // Por enquanto só encapsulamos os dados
}
