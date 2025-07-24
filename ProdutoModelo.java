/*------------------------------------
Tema: Gestão de um Restaurante
Nome: Fernando Afonso Sebastiao
Numero: 34422
Ficheiro: ClienteModelo.java
Data: 10.07.2025
--------------------------------------*/

import javax.swing.*;
import java.io.*;
import SwingComponents.*;

public class ProdutoModelo implements RegistGeneric {
    private int id;
    private StringBufferModelo nomeProduto;
    private float preco;
    private boolean status;

    public ProdutoModelo() {
        id = 0;
        nomeProduto = new StringBufferModelo("", 50);
        preco = 0.0f;
        status = false;
    }

    public ProdutoModelo(int id, String nomeProduto, float preco, boolean status) {
        this.id = id;
        this.nomeProduto = new StringBufferModelo(nomeProduto, 50);
        this.preco = preco;
        this.status = status;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNomeProduto() {
        return nomeProduto.toStringEliminatingSpaces();
    }

    public float getPreco() {
        return preco;
    }

    public boolean getStatus() {
        return status;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = new StringBufferModelo(nomeProduto, 50);
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    // toString
    public String toString() {
        String dados = "=== DADOS DO PRODUTO ===\n\n";
        dados += "ID: " + getId() + "\n";
        dados += "Nome do Produto: " + getNomeProduto() + "\n";
        dados += "Preço: " + getPreco() + " Kz\n";
        dados += "Status: " + (getStatus() ? "Ativo" : "Inativo") + "\n";
        return dados;
    }

    // Tamanho do registo
    public long sizeof() {
        return 4 + 50 * 2 + 4 + 1; // id (4), nomeProduto (50*2), preco (4), status (1)
    }

    // Escrever no ficheiro
    public void write(RandomAccessFile stream) {
        try {
            stream.writeInt(id);
            nomeProduto.write(stream);
            stream.writeFloat(preco);
            stream.writeBoolean(status);
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao escrever no ficheiro!");
        }
    }

    // Ler do ficheiro
    public void read(RandomAccessFile stream) {
        try {
            id = stream.readInt();
            nomeProduto.read(stream);
            preco = stream.readFloat();
            status = stream.readBoolean();
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao ler do ficheiro!");
        }
    }

    // Salvar dados
    public void salvar() {
        ProdutoFile file = new ProdutoFile();
        file.salvarDados(this);
    }

    // Alterar dados
    public void salvarDados() {
        ProdutoFile file = new ProdutoFile();
        file.alterarDados(this);
    }
}
