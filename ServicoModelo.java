/*------------------------------------
Tema: Gestão de uma Barbearia
Nome: Enio Manuel
Numero: 2817
Ficheiro: ServicoModelo.java
Data: 10.07.2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class ServicoModelo implements RegistGeneric
{
    private int id, duracaoMinutos;
    private float preco;
    private StringBufferModelo nomeServico, descricao;
    private boolean status;
    
    public ServicoModelo()
    {
        id = 0;
        nomeServico = new StringBufferModelo("", 30);
        preco = 0;
        duracaoMinutos = 0;
        descricao = new StringBufferModelo("", 20);
        status = false;
    }

    public ServicoModelo(int id, String nomeServico, float preco, int duracaoMinutos, String descricao, 
    boolean status)
    {
        this.id = id;
        this.nomeServico = new StringBufferModelo(nomeServico, 30);
        this.preco = preco;
        this.duracaoMinutos = duracaoMinutos;
        this.descricao = new StringBufferModelo(descricao, 20);
        this.status = status;
    }

    // metodos getters
    public int getId()
    {
        return id;
    }

    public String getNomeServico()
    {
        return nomeServico.toStringEliminatingSpaces();
    }

    public float getPreco()
    {
        return preco;
    }

    public int getDuracao()
    {
        return duracaoMinutos;
    }

    public String getDescricao()
    {
        return descricao.toStringEliminatingSpaces();
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

    public void setNomeServico(String nomeServico)
    {
        this.nomeServico = new StringBufferModelo(nomeServico, 30);
    }

    public void setPreco(float preco)
    {
        this.preco = preco;
    }

    public void setDuracao(int duracaoMinutos)
    {
        this.duracaoMinutos = duracaoMinutos;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = new StringBufferModelo(descricao, 20);
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }
 
    // metodo toString
    public String toString()
    {
        String dados = "Dados do Servico Modelo: \n\n";

        dados += "Id: " + getId() + "\n";
        dados += "Nome do Servico: " + getNomeServico() + "\n";
        dados += "Preco: " + getPreco() + "\n";
        dados += "Duracao em Minutos: " + getDuracao() + "\n";
        dados += "Descricao: " + getDescricao() + "\n";
        dados += "Status: " + getStatus() + "\n";

        return dados;
    }

    // calcular o tamanho dos bytes
    public long sizeof()
    {
        try
        {
            return 50 * 2 + 4 + 4 + 4 + 1;
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
            nomeServico.write(stream);
            stream.writeFloat(preco);
            stream.writeInt(duracaoMinutos);
            descricao.write(stream);
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
            nomeServico.read(stream);
            preco = stream.readFloat();
            duracaoMinutos = stream.readInt();
            descricao.read(stream);
            status = stream.readBoolean();
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao ler no ficheiro");
        }
    }

    // metodo salvar
    public void salvar()
    {
        ServicoFile file = new ServicoFile();
        file.salvarDados(this);
    }

    // metodo alterar
    public void salvarDados()
    {
        ServicoFile file = new ServicoFile();
        file.alterarDados(this);
    }
}