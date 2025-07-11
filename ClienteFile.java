/*------------------------------------
Tema: Gestão de uma Barbearia
Nome: Enio Manuel
Numero: 2817
Ficheiro: ClienteVisao.java
Data: 11.07.2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

class ClienteFile extends ObjectsFile
{
    public ClienteFile()
    {
        super("ClienteFile.dat", new ClienteModelo());
    }

    public void salvarDados(ClienteModelo modelo)
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

    public void alterarDados(ClienteModelo modelo_novo)
	{
		ClienteModelo modelo_antigo = new ClienteModelo();
		
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

    public void eliminarDados(ClienteModelo modelo_novo)
	{
		ClienteModelo modelo_antigo = new ClienteModelo();
		
		try
		{
			stream.seek(4);
			
			for(int i = 0; i < getNregistos(); ++i)
			{
				modelo_antigo.read( stream );
				
				if (i == 0 && modelo_antigo.getId() == (modelo_novo.getId()))
				{
					stream.seek(4); 
					modelo_novo.write( stream );
					JOptionPane.showMessageDialog(null, "Dados eliminados com sucesso!");
					return;
				}	
				else
				{
					if ((modelo_antigo.getId() + 1) == (modelo_novo.getId()))
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

    public static void listarClientes()
    {
        ClienteFile file = new ClienteFile();
        ClienteModelo modelo = new ClienteModelo();
        String dados = "Listagem dos Dados do Cliente:\n\n";

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

	public static StringVector getAllNames()
    {
        ClienteFile file = new ClienteFile();
        ClienteModelo modelo = new ClienteModelo();
        StringVector vetor = new StringVector();

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getStatus() == true)
                    vetor.add(modelo.getNome());
            }
            
            vetor.sort();    
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return vetor;
    }

	public static int pesquisarPorId(int idProcurado)
    {
        ClienteFile file = new ClienteFile();
        ClienteModelo modelo = new ClienteModelo();

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

    public static void pesquisarPorNome(String nomeProcurado)
    {
        ClienteFile file = new ClienteFile();
        ClienteModelo modelo = new ClienteModelo();

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if((modelo.getNome().equalsIgnoreCase(nomeProcurado)) && (modelo.getStatus() == true))
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    return;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Erro, nome nao encontrado", 
                    "File Not Found", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    // metodo de pesquisa para edicao
    public static ClienteModelo getPesquisaPorId(int idProcurado)
    {
        ClienteFile file = new ClienteFile();
        ClienteModelo modelo = new ClienteModelo();

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if((modelo.getId() == idProcurado) && (modelo.getStatus() == true))
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

    // getPesquisarPorNome
    public static ClienteModelo getNomeProcurado(String nomeProcurado)
    {
        ClienteFile file = new ClienteFile();
        ClienteModelo modelo = new ClienteModelo();

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if((modelo.getNome().equalsIgnoreCase(nomeProcurado)) && (modelo.getStatus() == true))
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    return modelo;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Erro, nome nao encontrado", 
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