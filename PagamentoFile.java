/*------------------------------------
Tema: Gestão de uma Barbearia
Nome: Enio Manuel
Numero: 2817
Ficheiro: PagamentoFile.java
Data: 11.07.2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class PagamentoFile extends ObjectsFile
{
    public PagamentoFile()
    {
        super("PagamentoFile.dat", new PagamentoModelo());
    }  

    public void salvarDados(PagamentoModelo modelo)
    {
        try
        {
            // colocar o file pointer no final do ficheiro
            stream.seek(stream.length());

            // escrever no modelo
            modelo.write(stream);

            incrementarProximoCodigo();
            JOptionPane.showMessageDialog(null,  "Dados Salvos com Sucessso");
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Falha ao Salvar os Dados");
        }
    }

    public void alterarDados(PagamentoModelo modelo_novo)
	{
		PagamentoModelo modelo_antigo = new PagamentoModelo();
		
		try
		{
			stream.seek(4);
			
			for(int i = 0; i < getNregistos(); ++i)
			{
				modelo_antigo.read( stream );
				
				if (i == 0 && modelo_antigo.getId() == modelo_novo.getId())
				{
					stream.seek(4); 
					modelo_novo.write( stream );
					JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!");
					return;
				}	
				else
				{
					if (modelo_antigo.getId() + 1 == modelo_novo.getId())
					{
						modelo_novo.write( stream);
						return;
					}
							
				}			
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

    public void eliminarDados(PagamentoModelo modelo_novo)
	{
		PagamentoModelo modelo_antigo = new PagamentoModelo();
		
		try
		{
			stream.seek(4);
			
			for(int i = 0; i < getNregistos(); ++i)
			{
				modelo_antigo.read( stream );
				
				if (i == 0 && modelo_antigo.getId() == modelo_novo.getId())
				{
					stream.seek(4); 
					modelo_novo.write( stream );
					JOptionPane.showMessageDialog(null, "Dados eliminados com sucesso!");
					return;
				}	
				else
				{
					if (modelo_antigo.getId() + 1 == modelo_novo.getId())
					{
						modelo_novo.write(stream);
						return;
					}							
				}			
			}			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

    public static void listarPagamentos()
    {
        PagamentoFile file = new PagamentoFile();
        PagamentoModelo modelo = new PagamentoModelo();
        String dados = "Listagem dos Dados do Pagamento:\n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getStatus() == true)
                {
                    dados += "==============================\n";
                    dados += modelo.toString() + "\n\n";
                }
            }

            JTextArea area = new JTextArea(40 , 60);
            area.setText(dados);
            area.setFocusable(false);
            JOptionPane.showMessageDialog(null, new JScrollPane(area),
            "Gestao de Barbearia", JOptionPane.INFORMATION_MESSAGE);
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }
    }

    public static int pesquisarPorId(int idProcurado)
    {
        PagamentoFile file = new PagamentoFile();
        PagamentoModelo modelo = new PagamentoModelo();

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getId() == idProcurado && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    return 0;
                }
            }
               JOptionPane.showMessageDialog(null, "Erro, id nao encontrado", 
                    "File Not Found", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return idProcurado;
    }

    public static float pesquisarPorValor(float valorProcurado)
    {
        PagamentoFile file = new PagamentoFile();
        PagamentoModelo modelo = new PagamentoModelo();

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getValor() == valorProcurado && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    return 0;
                }
            }
               JOptionPane.showMessageDialog(null, "Erro, valor nao encontrado", 
                    "File Not Found", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return valorProcurado;
    }

    // metodos para a edicao
    public static PagamentoModelo getPesquisaPorId(int idProcurado)
    {
        PagamentoFile file = new PagamentoFile();
        PagamentoModelo modelo = new PagamentoModelo();

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getId() == idProcurado && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    return modelo;
                }
            }
               JOptionPane.showMessageDialog(null, "Erro, id nao encontrado", 
                    "File Not Found", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return modelo;
    }

    public static PagamentoModelo getPesquisaPorValor(float valorProcurado)
    {
        PagamentoFile file = new PagamentoFile();
        PagamentoModelo modelo = new PagamentoModelo();

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getValor() == valorProcurado && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    return modelo;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Erro, valor nao encontrado", 
                    "File Not Found", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return modelo;
    }

}   