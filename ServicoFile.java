/*------------------------------------
Tema: Gestão de uma Barbearia
Nome: Enio Manuel
Numero: 2817
Ficheiro: ServicoFile.java
Data: 11.07.2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

class ServicoFile extends ObjectsFile
{
    public ServicoFile()
    {
        super("ServicoFile.dat", new ServicoModelo());
    }

    public void salvarDados(ServicoModelo modelo)
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

    public void alterarDados(ServicoModelo modelo_novo)
	{
		ServicoModelo modelo_antigo = new ServicoModelo();
		
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

    public void eliminarDados(ServicoModelo modelo_novo)
	{
		ServicoModelo modelo_antigo = new ServicoModelo();
		
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

    public static void listarServicos()
    {
        ServicoFile file = new ServicoFile();
        ServicoModelo modelo = new ServicoModelo();
        String dados = "Listagem dos Dados do Servico:\n\n";

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
        ServicoFile file = new ServicoFile();
        ServicoModelo modelo = new ServicoModelo();
        StringVector vetor = new StringVector();

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getStatus() == true)
                    vetor.add(modelo.getNomeServico());
            }
            
            vetor.sort();    
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return vetor;
    }

	public static int pesquisarPorDuracaoMinutos(int duracaoProcurado)
    {
        ServicoFile file = new ServicoFile();
        ServicoModelo modelo = new ServicoModelo();

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if(modelo.getDuracao() == duracaoProcurado && modelo.getStatus() == true)
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    return 0;
                }
            }
               JOptionPane.showMessageDialog(null, "Erro, duracao em minutos nao encontrado", 
                    "File Not Found", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return duracaoProcurado;
    }

    public static void pesquisarPorNomeServico(String nomeSevicoProcurado)
    {
        ServicoFile file = new ServicoFile();
        ServicoModelo modelo = new ServicoModelo();

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if((modelo.getNomeServico().equalsIgnoreCase(nomeSevicoProcurado)) && (modelo.getStatus() == true))
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    return;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Erro, nome do servico nao encontrado", 
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
    public static ServicoModelo getPesquisaPorDuracao(int duracaoProcurado)
    {
        ServicoFile file = new ServicoFile();
        ServicoModelo modelo = new ServicoModelo();

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if((modelo.getDuracao() == duracaoProcurado) && (modelo.getStatus() == true))
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    return modelo;
                }
            }
               JOptionPane.showMessageDialog(null, "Erro, duraacao em minutos nao encontrado", 
                    "File Not Found", JOptionPane.ERROR_MESSAGE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return modelo;
    }

    // getPesquisarPorNome
    public static ServicoModelo getNomeServicoProcurado(String nomeServicoProcurado)
    {
        ServicoFile file = new ServicoFile();
        ServicoModelo modelo = new ServicoModelo();

        String dados = "Listagem de Dados do Ficheiro \n\n";

        try
        {
            file.stream.seek(4);

            for(int i = 0; i < file.getNregistos(); i++)
            {
                modelo.read(file.stream);

                if((modelo.getNomeServico().equalsIgnoreCase(nomeServicoProcurado)) && (modelo.getStatus() == true))
                {
                    JOptionPane.showMessageDialog(null, modelo.toString());
                    return modelo;
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Erro, nome do servico nao encontrado", 
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