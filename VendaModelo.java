
import javax.swing.*;
import java.io.*;
import Calendario.*;
import SwingComponents.*;

public class VendaModelo implements RegistGeneric {
    private int id, quantidade;
    private float valorTotal;
    private StringBufferModelo nomeCliente, nomeProduto, formaPagamento, funcionario;
    private DataModelo dataVenda;
    private boolean status;

    public VendaModelo() {
        id = 0;
        quantidade = 0;
        valorTotal = 0.0f;
        nomeCliente = new StringBufferModelo("", 50);
        nomeProduto = new StringBufferModelo("", 50);
        formaPagamento = new StringBufferModelo("", 30);
        funcionario = new StringBufferModelo("", 50);
        dataVenda = new DataModelo();
        status = false;
    }

    public VendaModelo(int id, String nomeCliente, String nomeProduto, int quantidade,
                       String formaPagamento, String funcionario, String dataVenda, float valorTotal) {
        this.id = id;
        this.nomeCliente = new StringBufferModelo(nomeCliente, 50);
        this.nomeProduto = new StringBufferModelo(nomeProduto, 50);
        this.quantidade = quantidade;
        this.formaPagamento = new StringBufferModelo(formaPagamento, 30);
        this.funcionario = new StringBufferModelo(funcionario, 50);
        this.dataVenda = new DataModelo(dataVenda);
        this.valorTotal = valorTotal;
        this.status = true;
    }

    // Getters
    public int getId() { return id; }
    public String getNomeCliente() { return nomeCliente.toStringEliminatingSpaces(); }
    public String getNomeProduto() { return nomeProduto.toStringEliminatingSpaces(); }
    public int getQuantidade() { return quantidade; }
    public String getFormaPagamento() { return formaPagamento.toStringEliminatingSpaces(); }
    public String getFuncionario() { return funcionario.toStringEliminatingSpaces(); }
    public String getDataVenda() { return dataVenda.toString(); }
    public float getValorTotal() { return valorTotal; }
    public boolean getStatus() { return status; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setNomeCliente(String nome) { this.nomeCliente = new StringBufferModelo(nome, 50); }
    public void setNomeProduto(String produto) { this.nomeProduto = new StringBufferModelo(produto, 50); }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public void setFormaPagamento(String forma) { this.formaPagamento = new StringBufferModelo(forma, 30); }
    public void setFuncionario(String funcionario) { this.funcionario = new StringBufferModelo(funcionario, 50); }
    public void setDataVenda(String data) { this.dataVenda = new DataModelo(data); }
    public void setValorTotal(float valor) { this.valorTotal = valor; }
    public void setStatus(boolean status) { this.status = status; }

    // toString
    public String toString() {
        String str = "=== DADOS DA VENDA ===\n\n";
        str += "ID: " + getId() + "\n";
        str += "Cliente: " + getNomeCliente() + "\n";
        str += "Produto: " + getNomeProduto() + "\n";
        str += "Quantidade: " + getQuantidade() + "\n";
        str += "Forma de Pagamento: " + getFormaPagamento() + "\n";
        str += "Funcionário: " + getFuncionario() + "\n";
        str += "Data da Venda: " + getDataVenda() + "\n";
        str += "Valor Total: " + getValorTotal() + " KZ\n";
        str += "Status: " + getStatus() + "\n";
        return str;
    }

    // Tamanho do registo
    public long sizeof() {
        return 4 + 50*2 + 50*2 + 4 + 30*2 + 50*2 + 12 + 4 + 1;
    }

    // Escrever no ficheiro binário
    public void write(RandomAccessFile file) {
        try {
            file.writeInt(id);
            nomeCliente.write(file);
            nomeProduto.write(file);
            file.writeInt(quantidade);
            formaPagamento.write(file);
            funcionario.write(file);
            dataVenda.write(file);
            file.writeFloat(valorTotal);
            file.writeBoolean(status);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao escrever venda: " + e.getMessage());
        }
    }

    // Ler do ficheiro binário
    public void read(RandomAccessFile file) {
        try {
            id = file.readInt();
            nomeCliente.read(file);
            nomeProduto.read(file);
            quantidade = file.readInt();
            formaPagamento.read(file);
            funcionario.read(file);
            dataVenda.read(file);
            valorTotal = file.readFloat();
            status = file.readBoolean();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler venda: " + e.getMessage());
        }
    }

    public void salvar() {
        VendaFile file = new VendaFile();
        file.salvarDados(this);
    }

    public void salvarDados() {
        VendaFile file = new VendaFile();
        file.alterarDados(this);
    }
}
