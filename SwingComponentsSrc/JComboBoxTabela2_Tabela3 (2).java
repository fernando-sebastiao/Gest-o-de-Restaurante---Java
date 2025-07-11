package SwingComponents;
/******************************************************************************

Nome: Felisberto Pinto Delgado
N� 1735
Data: 7 de Outubro de 2003
Objetivo : 
******************************************************************************/


import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;

public class JComboBoxTabela2_Tabela3
{
  protected JComboBoxPersonal comboBoxPai, comboBoxFilho;
  protected int codigoPaiSeleccionado, codigoFilhoSeleccionado;
  protected String fileFather, fileSun, paiSeleccionado, filhoSeleccionado;
  protected Contentor_Tabela2 Tabela_Pais;
  protected Contentor_Tabela3_2 Tabela_Filhos;
  
  public JComboBoxTabela2_Tabela3(String fileFather, String fileSun)
  {
  	this.fileFather = fileFather;
  	this.fileSun = fileSun;
//JOptionPane.showMessageDialog(null, "0: JComboBoxTabela2_Tabela3()");

   comboBoxPai = UInterfaceBox.createJComboBoxsTabela2(fileFather);
   setStatePai();

//JOptionPane.showMessageDialog(null, "Tabela_Pais =  " + Tabela_Pais.debug());
//JOptionPane.showMessageDialog(null, "codigoPaiSeleccionado =  " + codigoPaiSeleccionado);

   comboBoxFilho = new JComboBoxPersonal(new JComboBox( get_Strings_Filhos(codigoPaiSeleccionado)));
   setStateSun();
 	
	
//JOptionPane.showMessageDialog(null, "1: JComboBoxTabela2_Tabela3()");
// 888888888888888
	setEventManipulators2_3();
  }
  
  public void setStatePai()
  {
   paiSeleccionado = (String) comboBoxPai.getItemAt(0);
   Tabela_Pais =  Contentor_Tabela2.createStream(fileFather);
   codigoPaiSeleccionado = Tabela_Pais.get_Codigo(paiSeleccionado);
  }
  
  public void setStateSun()
  {
    filhoSeleccionado = (String) comboBoxFilho.getItemAt(0);
   	Tabela_Filhos =  Contentor_Tabela3_2.createStream3_2(fileSun);
   	codigoFilhoSeleccionado = Tabela_Filhos.get_Codigo(codigoPaiSeleccionado, filhoSeleccionado);
  }
  public void updateStatePai()
  {
   paiSeleccionado = (String) comboBoxPai.getSelectedItem();
   Tabela_Pais =  Contentor_Tabela2.createStream(fileFather);
   codigoPaiSeleccionado = Tabela_Pais.get_Codigo(paiSeleccionado);
  }
  public void updateStateSun()
  {
    filhoSeleccionado = (String) comboBoxFilho.getSelectedItem();
   	Tabela_Filhos =  Contentor_Tabela3_2.createStream3_2(fileSun);
   	codigoFilhoSeleccionado = Tabela_Filhos.get_Codigo(codigoPaiSeleccionado, filhoSeleccionado);
  }

      
  public int getCodigoPaiSeleccionado()
  {
  	return codigoPaiSeleccionado;
  }
  
  public String getPaiSeleccionado()
  {
  	return paiSeleccionado;
  }

  public int getCodigoFilhoSeleccionado()
  {
  	return codigoFilhoSeleccionado;
  }
  
  public String getFilhoSeleccionado()
  {
  	return filhoSeleccionado;
  }	

  public JComboBoxPersonal getComboBoxFather()
  {
    return comboBoxPai;
  }

  public JComboBoxPersonal getComboBoxSun()
  {
    return comboBoxFilho;
  }

  public  String[] get_Strings_Filhos(int codPai)
  {
     Tabela_Filhos =  Contentor_Tabela3_2.createStream3_2(fileSun);
     Tabela_Filhos.Sort();
//JOptionPane.showMessageDialog(null, "Tabela_Filhos =  " + Tabela_Filhos.toString());
     String[] filhos =  Tabela_Filhos.get_Designacoes(codPai);
//JOptionPane.showMessageDialog(null, "000: JComboBoxTabela2_Tabela3.get_Strings_Filhos(int codPai)");
// String msg = "Filhos: ";
// for (int i = 0; i < filhos.length; i++)
//    msg += filhos[i] +"\n";
//JOptionPane.showMessageDialog(null, msg);
     return filhos != null ? filhos : New_String.Gera_Vector_Strings_Nulo(2);
  }
 
  public void setEventManipulators2_3()
  {
  	comboBoxPai.addItemListener( new ItemListener()
    {
       public void itemStateChanged(ItemEvent e)
       {
       	 if (e.getStateChange() != ItemEvent.SELECTED)
      		return;
// JOptionPane.showMessageDialog(null, "000: JComboBoxTabela2_Tabela3()");
         
         updateStatePai();

       	 comboBoxFilho.changeModel(get_Strings_Filhos(codigoPaiSeleccionado));
        
         setStateSun();
           
      }
    });
    
    comboBoxFilho.addItemListener( new ItemListener()
    {
       public void itemStateChanged(ItemEvent e)
       {
       	 if (e.getStateChange() != ItemEvent.SELECTED)
      		return;
 //JOptionPane.showMessageDialog(null, "000: JComboBoxTabela2_Tabela3()");
         
         updateStateSun();
         
      }
    });
  }
}