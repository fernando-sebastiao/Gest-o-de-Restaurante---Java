/****************************************************/
/*Autor:Elyvaldo Agostinho                          */
/*Numero:2911					    */
/*Curso:Engenharia Informatica			    */
/*Data: 17/12/2005				    */
/*Objectivo:Criacao do Menu do Programa             */
/****************************************************/

package SwingComponents;


import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;


public class MoradaModelo extends ObjectSerializableGeneric
{
	protected int codigoProvincia, codigoMunicipio, codigoComuna;	
	
	public MoradaModelo()
	{
		codigoProvincia = 0;
		codigoMunicipio = 0;
		codigoComuna = 0;
	}
	
	public void setCodigoProvincia(int codigoProvincia)
		{ this.codigoProvincia = codigoProvincia; }
		
	public void setCodigoMunicipio(int codigoMunicipio)
		{ this.codigoMunicipio = codigoMunicipio; }
		
	public void setCodigoComuna(int codigoComuna)
		{ this.codigoComuna = codigoComuna; }
	/*	
	public long sizeof()
		{ return 12; }
	*/	
	public String toString()
	{
		Contentor_Tabela2 streamProvincias = Contentor_Tabela2.createStream(Defs.fileProvincias);
		Contentor_Tabela3_2 streamMunicipios = Contentor_Tabela3_2.createStream3_2(Defs.fileMunicipios);
		Contentor_Tabela3_3 streamComunas = Contentor_Tabela3_3.createStream3_3(Defs.fileComunas);
		
		String msg = "\n       Provincia: " + streamProvincias.get_Designacao(codigoProvincia).trim() +  
		"\n       Municipio: " + streamMunicipios.get_Designacao(codigoMunicipio).trim() +
		"\n       Comuna: " + streamComunas.get_Designacao(codigoComuna).trim();

		return msg;
	}
	
	public int getProvincia()
	{
		return codigoProvincia;
	}
	
	public int getMunicipio()
	{		
		return codigoMunicipio;
	}
	
	public int getComuna()
	{		
		return codigoComuna;
	}
	
	public String getProvinciaDesig()
	{ 	
		int Cod = getProvincia();		
		Contentor_Tabela2 streamProvincias = Contentor_Tabela2.createStream(Defs.fileProvincias);
		
		return streamProvincias.get_Designacao(Cod).trim();	 
	}
		
	public String getMunicipioDesig()
	{
		Contentor_Tabela3_2 streamMunicipios = Contentor_Tabela3_2.createStream3_2(Defs.fileMunicipios);		
		int Cod = getMunicipio();
		streamMunicipios.get_Designacao(codigoMunicipio).trim();
		
		 return streamMunicipios.get_Designacao(Cod).trim();	 
	}
	
	public String getComunaDesig()
	{		
		Contentor_Tabela3_3 streamComunas = Contentor_Tabela3_3.createStream3_3(Defs.fileComunas);
		int Cod = getComuna();		
		streamComunas.get_Designacao(codigoComuna).trim();
		
		 return streamComunas.get_Designacao(Cod).trim();	 
	}
	
	public void write(RandomAccessFile stream)
	{
		try
		{
			stream.writeInt(codigoProvincia);
			stream.writeInt(codigoMunicipio);
			stream.writeInt(codigoComuna);
		}
		catch (IOException ex)
		{
			String msg = "falha na escrita da morada no ficheiro " ;
			JOptionPane.showMessageDialog(null, msg);
			System.exit(1);
		}
	}
	
	public void read(RandomAccessFile stream)
	{
		try
		{
			codigoProvincia = stream.readInt();
			codigoMunicipio = stream.readInt();
			codigoComuna = stream.readInt();
		}
		catch (IOException ex)
		{
			String msg = "falha na leitura da morada no ficheiro " ;
			JOptionPane.showMessageDialog(null, msg);
			System.exit(1);
		}
	}
		

}
	
