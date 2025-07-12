/*------------------------------------
Tema: Gestão de uma Barbearia
Nome: Enio Manuel
Numero: 2817
Ficheiro: PagamentoModelo.java
Data: 11.07.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class PagamentoModelo implements RegistGeneric
{
    private int id;
    private float valor;
    private StringBufferModelo metodoPagamento;
    private DataModelo dataPagamento;
    private boolean status;

    public PagamentoModelo()
    {
        id = 0;
        valor = 0;
        dataPagamento = new DataModelo();
        metodoPagamento = new StringBufferModelo("", 20);
        status = false;
    }

    public PagamentoModelo(int id, float valor, String dataPagamento, String metodoPagamento, 
    boolean status)
    {
        this.id = id;
        this.valor = valor;
        this.dataPagamento = new DataModelo(dataPagamento);
        this.metodoPagamento = new StringBufferModelo(metodoPagamento, 20);
        this.status = status;
    }

    // metodos getters
    public int getId()
    {
        return id;
    }

    public float getValor()
    {
        return valor;
    }

    public String getDataPagamento()
    {
        return dataPagamento.toString();
    }

    public String getMetodoPagamento()
    {
        return metodoPagamento.toStringEliminatingSpaces();
    }

    public boolean getStatus()
    {
        return status;
    }
    
    // metodos setters
    public void setId(int id)
    {
        this.id = id;
    }

    public void setValor(float valor)
    {
        this.valor = valor;
    }

    public void setDataPagamento(String dataPagamento)
    {
        this.dataPagamento = new DataModelo(dataPagamento);
    }

    public void setMetodoPagamento(String metodoPagamento)
    {
        this.metodoPagamento = new StringBufferModelo(metodoPagamento, 20);
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    // metodo toString
    public String toString()
    {
        String dados = "Dados do Pagamento \n\n";

        dados += "Id: " + getId() + "\n";
        dados += "Valor: " + getValor() + "\n";
        dados += "Data Pagamento: " + getDataPagamento() + "\n";
        dados += "Metodo Pagamento: " + getMetodoPagamento() + "\n";
        dados += "Estado: " + getStatus() + "\n";

        return dados;
    }

    // calcular o tamanho dos bytes
    public long sizeof()
    {
        try
        {
            return 20 * 2 + 4 + 12 + 4 + 1;
        }
        catch(Exception ex)
        {
            return 0;
        }
    }

    // metodo write
    public void write(RandomAccessFile stream)
    {
        try
        {
            stream.writeInt(id);
            stream.writeFloat(valor);            
            dataPagamento.write(stream);
            metodoPagamento.write(stream);
            stream.writeBoolean(status);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao escrever no Ficheiro");
        }
    }

    // metodo read
    public void read(RandomAccessFile stream)
    {
        try
        {
            id = stream.readInt();
            valor = stream.readFloat();
            dataPagamento.read(stream);
            metodoPagamento.read(stream);
            status = stream.readBoolean();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao ler no ficheiro");
        }
    }

    public void salvar()
    {
        PagamentoFile file = new PagamentoFile();
        file.salvarDados(this);
    }

    public void salvarDados()
    {
        PagamentoFile file = new PagamentoFile();
        file.alterarDados(this);
    }
}