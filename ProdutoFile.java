/*------------------------------------
Tema: Gestão de uma Barbearia
Nome: Adolfo Cabeia
Numero: 31671
Ficheiro: ProdutoFile.java
Data: 26.06.2025
--------------------------------------*/

import javax.swing.*;
import SwingComponents.*;
import java.io.*;

public class ProdutoFile extends ObjectsFile {

	public ProdutoFile() {
		super("ProdutoFile.dat", new ProdutoModelo());
	}

	// Salvar novo produto
	public void salvarDados(ProdutoModelo modelo) {
		try {
			stream.seek(stream.length());
			modelo.write(stream);
			incrementarProximoCodigo();
			JOptionPane.showMessageDialog(null, "Produto salvo com sucesso!");
		} catch (IOException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "Falha ao salvar o produto.");
		}
	}

	// Alterar dados de um produto
	public void alterarDados(ProdutoModelo modeloNovo) {
		ProdutoModelo modeloAtual = new ProdutoModelo();
		try {
			stream.seek(4);
			for (int i = 0; i < getNregistos(); ++i) {
				modeloAtual.read(stream);
				if (modeloAtual.getId() == modeloNovo.getId()) {
					stream.seek(4 + i * modeloNovo.sizeof());
					modeloNovo.write(stream);
					JOptionPane.showMessageDialog(null, "Dados do produto alterados com sucesso!");
					return;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Eliminar logicamente um produto
	public void eliminarDados(ProdutoModelo modeloNovo) {
		modeloNovo.setStatus(false);
		alterarDados(modeloNovo);
		JOptionPane.showMessageDialog(null, "Produto eliminado com sucesso!");
	}

	// Listar todos os produtos
	public static void listarProdutos() {
		ProdutoFile ficheiro = new ProdutoFile();
		ProdutoModelo modelo = new ProdutoModelo();
		String output = "Listagem de Produtos:\n\n";

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
			JOptionPane.showMessageDialog(null, new JScrollPane(area), "Produtos", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Buscar produto por nome
	public static ProdutoModelo getProdutoPorNome(String nomeProcurado) {
		ProdutoFile ficheiro = new ProdutoFile();
		ProdutoModelo modelo = new ProdutoModelo();
		try {
			ficheiro.stream.seek(4);
			for (int i = 0; i < ficheiro.getNregistos(); ++i) {
				modelo.read(ficheiro.stream);
				if (modelo.getNomeProduto().equalsIgnoreCase(nomeProcurado) && modelo.getStatus()) {
					return modelo;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	// Pesquisar e exibir produto por nome
	public static void pesquisarProdutoPorNome(String nomeProcurado) {
		ProdutoModelo modelo = getProdutoPorNome(nomeProcurado);
		if (modelo != null && modelo.getStatus()) {
			JOptionPane.showMessageDialog(null, modelo.toString(),
				"Dados do Produto", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Produto não encontrado ou inativo.");
		}
	}

	// Obter todos os nomes de produtos ativos
	public static StringVector getAllProdutos() {
		ProdutoFile ficheiro = new ProdutoFile();
		ProdutoModelo modelo = new ProdutoModelo();
		StringVector vector = new StringVector();
		try {
			ficheiro.stream.seek(4);
			for (int i = 0; i < ficheiro.getNregistos(); ++i) {
				modelo.read(ficheiro.stream);
				if (modelo.getStatus()) {
					vector.add(modelo.getNomeProduto());
				}
			}
			vector.sort();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return vector;
	}
}
