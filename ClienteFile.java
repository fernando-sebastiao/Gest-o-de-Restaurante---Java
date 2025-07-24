/*------------------------------------
Tema: Gestão de um Restaurante
Nome: Fernando Afonso Sebastiao
Numero: 34422
Ficheiro: ClienteFile.java
Data: 26.06.2025
--------------------------------------*/
import javax.swing.*;
import SwingComponents.*;
import Calendario.*;
import java.io.*;

public class ClienteFile extends ObjectsFile {

	public ClienteFile() {
		super("ClienteFile.dat", new ClienteModelo());
	}

	// SALVAR NOVO CLIENTE
	public void salvarDados(ClienteModelo modelo) {
		try {
			stream.seek(stream.length());
			modelo.write(stream);
			incrementarProximoCodigo();
			JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso!");
		} catch (IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao salvar cliente.");
		}
	}

	// ALTERAR CLIENTE EXISTENTE
	public void alterarDados(ClienteModelo modeloNovo) {
		ClienteModelo modeloAtual = new ClienteModelo();
		try {
			stream.seek(4);
			for (int i = 0; i < getNregistos(); ++i) {
				modeloAtual.read(stream);
				if (modeloAtual.getId() == modeloNovo.getId()) {
					stream.seek(4 + i * modeloNovo.sizeof());
					modeloNovo.write(stream);
					JOptionPane.showMessageDialog(null, "Dados do cliente alterados com sucesso!");
					return;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao alterar dados do cliente.");
		}
	}

	// ELIMINAR CLIENTE (LÓGICO)
	public void eliminarDados(ClienteModelo modelo) {
		modelo.setStatus(false);
		alterarDados(modelo);
		JOptionPane.showMessageDialog(null, "Cliente eliminado com sucesso!");
	}

	// LISTAR TODOS OS CLIENTES
	public static void listarClientes() {
		ClienteFile ficheiro = new ClienteFile();
		ClienteModelo modelo = new ClienteModelo();
		String output = "Listagem de Clientes:\n\n";

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
			JOptionPane.showMessageDialog(null, new JScrollPane(area),
				"Clientes", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao listar clientes.");
		}
	}

	// BUSCAR CLIENTE PELO ID
	public static ClienteModelo getClientePorId(int idProcurado) {
		ClienteFile ficheiro = new ClienteFile();
		ClienteModelo modelo = new ClienteModelo();
		try {
			ficheiro.stream.seek(4);
			for (int i = 0; i < ficheiro.getNregistos(); ++i) {
				modelo.read(ficheiro.stream);
				if (modelo.getId() == idProcurado && modelo.getStatus()) {
					return modelo;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	// BUSCAR CLIENTE PELO NOME
	public static ClienteModelo getClientePorNome(String nomeProcurado) {
		ClienteFile ficheiro = new ClienteFile();
		ClienteModelo modelo = new ClienteModelo();
		try {
			ficheiro.stream.seek(4);
			for (int i = 0; i < ficheiro.getNregistos(); ++i) {
				modelo.read(ficheiro.stream);
				if (modelo.getNome().equalsIgnoreCase(nomeProcurado) && modelo.getStatus()) {
					return modelo;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	// OBTER TODOS OS NOMES DE CLIENTES
	public static StringVector getAllNomesClientes() {
		StringVector vector = new StringVector();
		ClienteFile ficheiro = new ClienteFile();
		ClienteModelo modelo = new ClienteModelo();

		try {
			ficheiro.stream.seek(4);
			for (int i = 0; i < ficheiro.getNregistos(); ++i) {
				modelo.read(ficheiro.stream);
				if (modelo.getStatus()) {
					vector.add(modelo.getNome());
				}
			}
			vector.sort();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return vector;
	}
}
