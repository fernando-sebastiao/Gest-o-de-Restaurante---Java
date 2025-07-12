/*------------------------------------
Tema: Gestão de uma Barbearia
Nome: Enio Manuel
Numero: 2817
Ficheiro: AgendamentoModelo.java
Data: 11.07.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class AgendamentoModelo implements RegistGeneric
{
    private int id;
    private StringBufferModelo horaAgendamento,estadoAgendamento;
    private DataModelo dataAgendamento;
    private boolean status;

    public AgendamentoModelo()
    {
        id = 0;
        dataAgendamento = new DataModelo();
        horaAgendamento = new StringBufferModelo("", 20);
        estadoAgendamento = new StringBufferModelo("", 20);
        status = false;
    }

    public AgendamentoModelo(int id, String dataAgendamento, String horaAgendamento, String estadoAgendamento, 
    boolean status)
    {
        this.id = id;
        this.dataAgendamento = new DataModelo(dataAgendamento);
        this.horaAgendamento = new StringBufferModelo(horaAgendamento, 20);
        this.estadoAgendamento = new StringBufferModelo(estadoAgendamento, 20);
        this.status = status;
    }

    // metodos getters
    public int getId()
    {
        return id;
    }

    public String getDataAgenda()
    {
        return dataAgendamento.toString();
    }

    public String getHorario()
    {
        return horaAgendamento.toStringEliminatingSpaces();
    }

    public String getEstado()
    {
        return estadoAgendamento.toStringEliminatingSpaces();
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

    public void setDataAgenda(String dataAgendamento)
    {
        this.dataAgendamento = new DataModelo(dataAgendamento);
    }

    public void setHorario(String horaAgendamento)
    {
        this.horaAgendamento = new StringBufferModelo(horaAgendamento, 20);
    }

    public void setEstado(String estadoAgendamento)
    {
        this.estadoAgendamento = new StringBufferModelo(estadoAgendamento,20);
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
        dados += "Data Agendamento: " + getDataAgenda() + "\n";
        dados += "Horario Agendamento: " + getHorario() + "\n";
        dados += "Estado: " + getEstado() + "\n";
        dados += "Status: " + getStatus() + "\n";

        return dados;
    }

    // calcular o tamanho dos bytes
    public long sizeof()
    {
        try
        {
            return 20 * 2 + 4 + 12 + 1;
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
            dataAgendamento.write(stream);
            horaAgendamento.write(stream);
            estadoAgendamento.write(stream);
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
            dataAgendamento.read(stream);
            horaAgendamento.read(stream);
            estadoAgendamento.read(stream);
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
        AgendamentoFile file = new AgendamentoFile();
        file.salvarDados(this);
    }

    public void salvarDados()
    {
        AgendamentoFile file = new AgendamentoFile();
        file.alterarDados(this);
    }
}