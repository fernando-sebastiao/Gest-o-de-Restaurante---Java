package SwingComponents;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyData
{
	private int dia, mes, ano;
	
	private static String meses[] = 
  	{
    	"Janeiro","Fevereiro","Março","Abril","Maio","Junho",
    	"Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"
	};
	
	public MyData()
	{
		dia = mes = ano = 0;
	}
	
	public MyData(String data)
	{
		char separator = '-';
		int pos1Separator, pos2Separator;
		String diaS, mesS, anoS;  
		//dd/mm/aaaa
//Debug.debug("0: MyData(String data)\t" + "data = " +data);		
		pos1Separator = data.indexOf(separator);
//Debug.debug("00: MyData(String data)\t" + "pos1Separator = " +pos1Separator);
		diaS = data.substring(0, pos1Separator);
//Debug.debug("1: MyData(String data)\t" + "diaS = " + diaS);		
		pos2Separator = data.indexOf(separator, pos1Separator + 1);
//Debug.debug("2: MyData(String data)\t" + "pos2Separator = " +pos2Separator);
		mesS = data.substring(pos1Separator + 1, pos2Separator);
//Debug.debug("3: MyData(String data)\t" + "mesS = " + mesS);	
		anoS = data.substring(pos2Separator + 1);
//Debug.debug("4: MyData(String data)\t" + "anoS = " + anoS);	
		
		dia = Integer.parseInt(diaS);
		mes = getMes(mesS);
		ano = Integer.parseInt(anoS);
	}
	public int getMes(String mesS)
	{		
		for (int i = 0; i < meses.length; i++)
		{
			if (mesS.compareTo(meses[i].substring(0, 3)) == 0)
				return i + 1;
		}
		return -1;
	}
	public String toString()
	{
		return "" + dia + '/' + mes + '/' + ano;
	}
	
	public void debug()
	{
		JOptionPane.showMessageDialog(null, toString());
	}
}