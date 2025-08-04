/*------------------------------------
Tema: Gestão de um Restaurante
Nome: Fernando Afonso Sebastiao
Numero: 34422
Ficheiro: ReservaModelo.java
Data: 10.07.2025
--------------------------------------*/

import javax.swing.*;
import SwingComponents.*;
import Calendario.*;
import java.io.*;

public class ReservaFile extends ObjectsFile {

	public ReservaFile() {
		super("ReservaFile.dat", new ReservaModelo());
	}

	// Salvar nova reserva
	public void salvarDados(ReservaModelo modelo) {
		try {
			stream.seek(stream.length());
			modelo.write(stream);
			incrementarProximoCodigo();
			JOptionPane.showMessageDialog(null, "Reserva salva com sucesso!");
		} catch (IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Falha ao salvar nova reserva.");
		}
	}

	// Alterar dados de uma reserva
	public void alterarDados(ReservaModelo modeloNovo) {
		ReservaModelo modeloAtual = new ReservaModelo();
		try {
			stream.seek(4);
			for (int i = 0; i < getNregistos(); ++i) {
				modeloAtual.read(stream);
				if (modeloAtual.getId() == modeloNovo.getId()) {
					stream.seek(4 + i * modeloNovo.sizeof());
					modeloNovo.write(stream);
					JOptionPane.showMessageDialog(null, "Dados da reserva alterados com sucesso!");
					return;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Eliminar logicamente uma reserva
	public void eliminarDados(ReservaModelo modeloNovo) {
		modeloNovo.setStatus(false);
		alterarDados(modeloNovo);
		JOptionPane.showMessageDialog(null, "Reserva eliminada com sucesso!");
	}

	// Listar todas as reservas
	public static void listarReservas() {
		ReservaFile ficheiro = new ReservaFile();
		ReservaModelo modelo = new ReservaModelo();
		String output = "Listagem de Reservas:\n\n";

		try {
			ficheiro.stream.seek(4);
			for (int i = 0; i < ficheiro.getNregistos(); ++i) {
				modelo.read(ficheiro.stream);
				if (modelo.getStatus()) {
					output += "-----------------------------\n";
					output += modelo.toString() + "\n";
				}
			}
			JTextArea area = new JTextArea(40, 60);
			area.setText(output);
			area.setEditable(false);
			JOptionPane.showMessageDialog(null, new JScrollPane(area), "Reservas", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Buscar reserva por cliente
	public static ReservaModelo getReservaPorCliente(String clienteProcurado) {
		ReservaFile ficheiro = new ReservaFile();
		ReservaModelo modelo = new ReservaModelo();
		try {
			ficheiro.stream.seek(4);
			for (int i = 0; i < ficheiro.getNregistos(); ++i) {
				modelo.read(ficheiro.stream);
				if (modelo.getNomeCliente().equalsIgnoreCase(clienteProcurado) && modelo.getStatus()) {
					return modelo;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	// Pesquisar e exibir reserva por cliente
	public static void pesquisarReservaPorCliente(String clienteProcurado) {
		ReservaModelo modelo = getReservaPorCliente(clienteProcurado);
		if (modelo != null && modelo.getStatus()) {
			JOptionPane.showMessageDialog(null, modelo.toString(),
				"Dados da Reserva", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Reserva não encontrada ou inativa.");
		}
	}

	public static ReservaModelo getReservaPorData(String dataProcurada) {
    ReservaFile ficheiro = new ReservaFile();
    ReservaModelo modelo = new ReservaModelo();
    try {
        ficheiro.stream.seek(4);
        for (int i = 0; i < ficheiro.getNregistos(); ++i) {
            modelo.read(ficheiro.stream);
            if (modelo.getDataReserva().equals(dataProcurada) && modelo.getStatus()) {
                return modelo;
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return null;
}


	// Obter todos os nomes de clientes com reserva
	public static StringVector getAllClientesReservas() {
		ReservaFile ficheiro = new ReservaFile();
		ReservaModelo modelo = new ReservaModelo();
		StringVector vector = new StringVector();
		try {
			ficheiro.stream.seek(4);
			for (int i = 0; i < ficheiro.getNregistos(); ++i) {
				modelo.read(ficheiro.stream);
				if (modelo.getStatus()) {
					vector.add(modelo.getNomeCliente());
				}
			}
			vector.sort();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return vector;
	}
}
