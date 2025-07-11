package SwingComponents;

import java.io.*;
import javax.swing.*;
import java.util.*;

public class DataModelo extends ObjectSerializableGeneric
{
	int dia, mes, ano;
	
	private static final int DIA = 0, MES = DIA + 1, ANO = MES + 1;
	
	public static String meses[] = 
	{
    "Janeiro","Fevereiro","Mar�o","Abril","Maio","Junho",
    "Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"
	};
	
	public DataModelo()
	{
		dia = mes = 1;
		ano = 2005;
	}
	
	public DataModelo(String data)
	{
		StringTokenizer st = new StringTokenizer(data, "/-");
		for (int i = 0; st.hasMoreTokens() && i < 3; i++) 
		{
			try
			{
				switch (i)
				{
					case DIA:
						dia = Integer.parseInt(st.nextToken());
						break;
					case MES:
						geraMes(st.nextToken());
						break;
					case ANO:
						ano = Integer.parseInt(st.nextToken());
						break;
				}
			}
			catch (NumberFormatException ex)
			{
			}
		}
	}
	
	private void geraMes(String mes)
	{
		for (int i = 0; i < meses.length; i++)
		{
			if (mes.equals(meses[i].substring(0, 3)) == true)
			{
				this.mes = i + 1;
				return;
			}
		}
		return;
	}
	
	public int getDia()
		{ return dia; }
		
	public int getMes()
		{ return mes; }
		
	public int getAno()
		{ return ano; }
		
	public String toString()
	{
		return "" + dia + "/" +  mes + "/" +  ano; 
	}
	
	public void write(RandomAccessFile stream)
	{
		try
		{
			stream.writeInt(dia);
			stream.writeInt(mes);
			stream.writeInt(ano);
		}
		catch (IOException ex)
		{
			String msg = "falha na escrita da data no ficheiro " ;
			JOptionPane.showMessageDialog(null, msg);
			System.exit(1);
		}
	}
	
	public void read(RandomAccessFile stream)
	{
		try
		{
			dia = stream.readInt();
			mes = stream.readInt();
			ano = stream.readInt();
		}
		catch (IOException ex)
		{
			String msg = "falha na leitura da data no ficheiro " ;
			JOptionPane.showMessageDialog(null, msg);
			System.exit(1);
		}
	}
		

}
