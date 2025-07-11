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

public class JComboBoxTabela3_Tabela3 extends JComboBoxTabela2_Tabela3
{
  protected int codigoNetoSeleccionado;
  protected String fileNeto, netoSeleccionado;
  protected JComboBoxPersonal  comboBoxNeto;
  protected Contentor_Tabela3_3 Tabela_Netos;
  
  public JComboBoxTabela3_Tabela3(String fileFather, String fileSun, String fileNeto)
  {
  	super(fileFather, fileSun);
  	this.fileNeto = fileNeto;
   //codigoFilhoSeleccionado = Tabela_Filhos.get_Codigo(codigoPaiSeleccionado, filhoSeleccionado);
//JOptionPane.showMessageDialog(null, "3: JComboBoxTabela3_Tabela3()\nCodigoFilhoSelecionado: " + codigoFilhoSeleccionado);
   comboBoxNeto = new JComboBoxPersonal(new JComboBox( get_Strings_Netos(codigoFilhoSeleccionado)));
   setStateNeto();
//   comboBoxs = UInterfaceBox.joinJComboBoxs(comboBoxs, comboBoxNeto);
//JOptionPane.showMessageDialog(null, "4: JComboBoxTabela3_Tabela3()");
	setEventManipulators3_3();
  }
  
  public void setStateNeto()
  {
 	netoSeleccionado = (String) comboBoxNeto.getItemAt(0);
         
    Tabela_Netos =  Contentor_Tabela3_3.createStream3_3(fileNeto);
    codigoNetoSeleccionado = Tabela_Netos.get_Codigo(codigoFilhoSeleccionado, netoSeleccionado);
  }
  
  public void updateStateNeto()
  {
  	netoSeleccionado = (String)comboBoxNeto.getSelectedItem();
    Tabela_Netos =  Contentor_Tabela3_3.createStream3_3(fileNeto);
    codigoNetoSeleccionado = Tabela_Netos.get_Codigo(codigoFilhoSeleccionado, netoSeleccionado);
  }

  public JComboBoxPersonal getComboBoxNeto()
  {
    return comboBoxNeto;
  }
  public int getCodigoNetoSeleccionado()
  {
  	return codigoNetoSeleccionado;
  }
  
  public String getNetoSeleccionado()
  {
  	return netoSeleccionado;
  }
  
  public  String[] get_Strings_Netos(int codFilho)
  {
     Tabela_Netos =  Contentor_Tabela3_3.createStream3_3(fileNeto);
//JOptionPane.showMessageDialog(null, "1: get_Strings_Netos()\ncodFilho: " + codFilho);
//JOptionPane.showMessageDialog(null, "2: get_Strings_Netos()\nTabela_Netos: " + Tabela_Netos.toString());
     Tabela_Netos.Sort();
//JOptionPane.showMessageDialog(null, "3: get_Strings_Netos()\nTabela_Netos: " + Tabela_Netos.toString());
     String[] netos =  Tabela_Netos.get_Designacoes(codFilho);
/*if (netos != null)
JOptionPane.showMessageDialog(null, "4: get_Strings_Netos()\nnetos: " + New_String.debug(netos));*/
     return netos != null ? netos :  New_String.Gera_Vector_Strings_Nulo(2);
  }
  
  public void setEventManipulators3_3()
  {
  	//super.setEventManipulators();
  	
  	comboBoxFilho.addItemListener( new ItemListener()
    {
       public void itemStateChanged(ItemEvent e)
      {
      	if (e.getStateChange() != ItemEvent.SELECTED)
      		return;
//JOptionPane.showMessageDialog(null, "000: JComboBoxTabela3_Tabela3.itemStateChanged(ItemEvent e)");
        updateStateSun();
/*JOptionPane.showMessageDialog(null, "000: filhoSeleccionado: " + filhoSeleccionado
										+ "\nCodigoFilhoSelecionado = " + codigoFilhoSeleccionado
										+ "\nPaiSeleccionada = (" + codigoPaiSeleccionado
										+ ", " + PaiSeleccionada +")");*/
        
        comboBoxNeto.changeModel(get_Strings_Netos(codigoFilhoSeleccionado));
        setStateNeto();
      }
    });
    comboBoxNeto.addItemListener( new ItemListener()
    {
       public void itemStateChanged(ItemEvent e)
       {
       	 if (e.getStateChange() != ItemEvent.SELECTED)
      		return;

         updateStateNeto();
         
      }
    });

  }
}