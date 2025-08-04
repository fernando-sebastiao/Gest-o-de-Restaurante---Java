
import javax.swing.*;
import SwingComponents.*;
import Calendario.*;
import java.io.*;

public class VendaFile extends ObjectsFile {

	public VendaFile() {
		super("VendaFile.dat", new VendaModelo());
	}

	public void salvarDados(VendaModelo modelo) {
		try {
			stream.seek(stream.length());
			modelo.write(stream);
			incrementarProximoCodigo();
			JOptionPane.showMessageDialog(null, "Venda registrada com sucesso!");
		} catch (IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro ao salvar nova venda.");
		}
	}

	public void alterarDados(VendaModelo modeloNovo) {
		VendaModelo modeloAtual = new VendaModelo();
		try {
			stream.seek(4);
			for (int i = 0; i < getNregistos(); ++i) {
				modeloAtual.read(stream);
				if (modeloAtual.getId() == modeloNovo.getId()) {
					stream.seek(4 + i * modeloNovo.sizeof());
					modeloNovo.write(stream);
					JOptionPane.showMessageDialog(null, "Venda alterada com sucesso!");
					return;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void eliminarDados(VendaModelo modeloNovo) {
		modeloNovo.setStatus(false);
		alterarDados(modeloNovo);
		JOptionPane.showMessageDialog(null, "Venda eliminada com sucesso!");
	}

	public static void listarVendas() {
		VendaFile ficheiro = new VendaFile();
		VendaModelo modelo = new VendaModelo();
		String output = "Listagem de Vendas:\n\n";

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
					"Vendas", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static VendaModelo getVendaPorNome(String nomeProcurado) {
    VendaFile ficheiro = new VendaFile();
    VendaModelo modelo = new VendaModelo();
    try {
        ficheiro.stream.seek(4);
        for (int i = 0; i < ficheiro.getNregistos(); ++i) {
            modelo.read(ficheiro.stream);
            if (modelo.getStatus() && modelo.getNomeCliente().equalsIgnoreCase(nomeProcurado)) {
                return modelo;
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return null;
}


	public static VendaModelo getVendaPorId(int idProcurado) {
		VendaFile ficheiro = new VendaFile();
		VendaModelo modelo = new VendaModelo();
		try {
			ficheiro.stream.seek(4);
			for (int i = 0; i < ficheiro.getNregistos(); ++i) {
				modelo.read(ficheiro.stream);
				if (modelo.getId() == idProcurado && modelo.getStatus())
					return modelo;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static void pesquisarVendaPorId(int idProcurado) {
		VendaModelo modelo = getVendaPorId(idProcurado);
		if (modelo != null && modelo.getStatus()) {
			JOptionPane.showMessageDialog(null, modelo.toString(),
					"Dados da Venda", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Venda não encontrada ou inativa.");
		}
	}

	public static StringVector getAllClientes() {
		VendaFile ficheiro = new VendaFile();
		VendaModelo modelo = new VendaModelo();
		StringVector vector = new StringVector();
		try {
			ficheiro.stream.seek(4);
			for (int i = 0; i < ficheiro.getNregistos(); ++i) {
				modelo.read(ficheiro.stream);
				if (modelo.getStatus())
					vector.add(modelo.getNomeCliente());
			}
			vector.sort();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return vector;
	}
}
