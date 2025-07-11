package RSComponents;
/***********************************
Nome: Osvaldo Manuel Ramos
N. 2817
Data: 7 de Outubro de 2006
************************************/

import java.io.*;
import javax.swing.*;
import java.util.*;
import SwingComponents.*;

public class ObjectsFile extends ObjectGeneric
{
	protected int proximoCodigo = 1;
	protected String filename;
	protected RandomAccessFile stream;
	protected RegistGeneric registo;

	// cria um ficheiro para leitura e escrita automaticamente
	public ObjectsFile(String filename, RegistGeneric registo)
	{
		this.filename = filename;
		this.registo = registo;
		try
		{
			// cria um ficheiro de nome filename para rw
			stream = new RandomAccessFile(filename, "rw");
		}
		catch (FileNotFoundException ex)
		{
		ex.printStackTrace();
			String msg = "Nao foi possivel criar o ficheiro " +  filename ;
			JOptionPane.showMessageDialog(null, msg);
			System.exit(1);
		}		
		try
		{
			if (proximoCodigo == 1)
			{
				if (stream.length() == 0)
					stream.writeInt(proximoCodigo);
				else
					proximoCodigo = stream.readInt();
			}
				
		}
		catch (IOException ex)
		{
		ex.printStackTrace();
			String msg = "Nao foi possivel escrever no ficheiro " +  filename 
									+ " o proximo codigo";
			JOptionPane.showMessageDialog(null, msg);
			System.exit(1);
		}
	} // fim do construtor da class ObjectsFile
	
	public RandomAccessFile getStream()
		{ return stream; }
		
	protected void finalize()
	{
		close();
	}
	
	public int getProximoCodigo()
	{
		return proximoCodigo;
	}
	
	public void incrementarProximoCodigo()
	{
		proximoCodigo++;
		
		long currentPosition;
		
		try
		{
			currentPosition = stream.getFilePointer();
			stream.seek(0);
			stream.writeInt(proximoCodigo);
			stream.seek(currentPosition);
		}
		catch (IOException ex)
		{
		ex.printStackTrace();
			String msg = "falhou tentaiva de posicionar no principio  do ficheiro " 
							+  filename ;
			JOptionPane.showMessageDialog(null, msg);
			System.exit(1);
		}	
	}
	

	public static void inic(String filename, RegistGeneric registo)
	{
		ObjectsFile str = new ObjectsFile(filename, registo); 	
		str = null;
		System.gc();
	}

	public void close()
	{
		try
		{
			stream.close(); 
			stream = null;
		}
		catch (IOException ex)
		{
		ex.printStackTrace();
			String msg = "Nao foi possivel fechar o ficheiro " + filename;
			JOptionPane.showMessageDialog(null, msg);
			System.exit(1);
		}	
	}
	
	public long getNregistos()
	{		
		long size = 0;
		long nregs = 0;
		
		try
		{
			size = registo.sizeof();
			nregs = (stream.length() - 4) / size;		
		}
		catch (IOException ex)
		{
		ex.printStackTrace();
			String msg2 = "falhou tentativa de determinar o tamanho  do ficheiro " 
							+  filename ;
			JOptionPane.showMessageDialog(null, msg2);
			System.exit(1);
		}
		return nregs;
	}	
	
	public String toString()
	{
		String msg = "{ ";
		long currentPosition = 0;
		try
		{
			currentPosition = stream.getFilePointer();
			stream.seek(0);
		}
		catch (IOException ex)
		{
		ex.printStackTrace();
			String msg2 = "falhou tentaiva de posicionar no principio  do ficheiro " 
							+  filename ;
			JOptionPane.showMessageDialog(null, msg2);
			System.exit(1);
		}
		try
		{
			proximoCodigo = stream.readInt();
			msg += "proximoCodigo: " + proximoCodigo + "\n\n";
			
			long nregs = getNregistos();
		//Debug.debug("0: ObjectsFile.toString()\tnregs: " + nregs + "\tproximoCodigo: " + proximoCodigo);
			for (long i = 0; i < nregs; i++)
			{
				registo.read(stream);
				msg += "\nRegisto[" + i + "]: " + registo.toString() + "\n";	
			}
			stream.seek(currentPosition);
		}
		catch (IOException ex)
		{
		ex.printStackTrace();
			String msg3 = "falhou tentaiva de escrever no principio  do ficheiro " 
							+  filename ;
			JOptionPane.showMessageDialog(null, msg3);
			System.exit(1);
		}
		return msg + " }";
	}
  
}

