/*------------------------------------
Tema: Gestão de um Restaurante
Nome: Fernando Afonso Sebastiao
Numero: 34422
Ficheiro: ClienteModelo.java
Data: 10.07.2025
--------------------------------------*/

import javax.swing.*;
import java.io.*;
import Calendario.*;
import SwingComponents.*;

public class ReservaModelo implements RegistGeneric {
    private int id, numPessoas;
    private StringBufferModelo nomeCliente, mesa, horario, funcionario;
    private DataModelo dataReserva;
    private boolean status;

    public ReservaModelo() {
        id = 0;
        numPessoas = 0;
        nomeCliente = new StringBufferModelo("", 50);
        mesa = new StringBufferModelo("", 20);
        horario = new StringBufferModelo("", 15);
        funcionario = new StringBufferModelo("", 30);
        dataReserva = new DataModelo();
        status = false;
    }

    public ReservaModelo(int id, int numPessoas, String nomeCliente, String mesa,
                         String horario, String funcionario, String dataReserva, boolean status) {
        this.id = id;
        this.numPessoas = numPessoas;
        this.nomeCliente = new StringBufferModelo(nomeCliente, 50);
        this.mesa = new StringBufferModelo(mesa, 20);
        this.horario = new StringBufferModelo(horario, 15);
        this.funcionario = new StringBufferModelo(funcionario, 30);
        this.dataReserva = new DataModelo(dataReserva);
        this.status = status;
    }

    // Getters
    public int getId() { return id; }
    public int getNumPessoas() { return numPessoas; }
    public String getNomeCliente() { return nomeCliente.toStringEliminatingSpaces(); }
    public String getMesa() { return mesa.toStringEliminatingSpaces(); }
    public String getHorario() { return horario.toStringEliminatingSpaces(); }
    public String getFuncionario() { return funcionario.toStringEliminatingSpaces(); }
    public String getDataReserva() { return dataReserva.toString(); }
    public boolean getStatus() { return status; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setNumPessoas(int numPessoas) { this.numPessoas = numPessoas; }
    public void setNomeCliente(String nome) { this.nomeCliente = new StringBufferModelo(nome, 50); }
    public void setMesa(String mesa) { this.mesa = new StringBufferModelo(mesa, 20); }
    public void setHorario(String horario) { this.horario = new StringBufferModelo(horario, 15); }
    public void setFuncionario(String funcionario) { this.funcionario = new StringBufferModelo(funcionario, 30); }
    public void setDataReserva(String data) { this.dataReserva = new DataModelo(data); }
    public void setStatus(boolean status) { this.status = status; }

    // toString
    public String toString() {
        String str = "=== DADOS DA RESERVA ===\n\n";
        str += "ID: " + getId() + "\n";
        str += "Nome do Cliente: " + getNomeCliente() + "\n";
        str += "Número de Pessoas: " + getNumPessoas() + "\n";
        str += "Mesa: " + getMesa() + "\n";
        str += "Horário: " + getHorario() + "\n";
        str += "Funcionário: " + getFuncionario() + "\n";
        str += "Data da Reserva: " + getDataReserva() + "\n";
        str += "Status: " + (getStatus() ? "Ativo" : "Inativo") + "\n";
        return str;
    }

    // Tamanho do registro
    public long sizeof() {
        return 4 + 4 + 50*2 + 20*2 + 15*2 + 30*2 + 12 + 1;
    }

    // Escrever no ficheiro
    public void write(RandomAccessFile file) {
        try {
            file.writeInt(id);
            file.writeInt(numPessoas);
            nomeCliente.write(file);
            mesa.write(file);
            horario.write(file);
            funcionario.write(file);
            dataReserva.write(file);
            file.writeBoolean(status);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao escrever reserva: " + e.getMessage());
        }
    }

    // Ler do ficheiro
    public void read(RandomAccessFile file) {
        try {
            id = file.readInt();
            numPessoas = file.readInt();
            nomeCliente.read(file);
            mesa.read(file);
            horario.read(file);
            funcionario.read(file);
            dataReserva = new DataModelo();
            dataReserva.read(file);
            status = file.readBoolean();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao ler reserva: " + e.getMessage());
        }
    }

    // Salvar reserva
    public void salvar() {
        ReservaFile file = new ReservaFile();
        file.salvarDados(this);
    }

    // Alterar reserva
    public void salvarDados() {
        ReservaFile file = new ReservaFile();
        file.alterarDados(this);
    }
}
