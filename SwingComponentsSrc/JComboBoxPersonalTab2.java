package SwingComponents;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;

public class JComboBoxPersonalTab2 extends JComboBoxPersonal
{
	protected int codigoSeleccionado;
	protected String file, itemSeleccionado;
	protected Contentor_Tabela2 stream;
	
	public JComboBoxPersonalTab2(JComboBox j, String file)
	{
		super(j);
		
		this.file = file;
		stream = Contentor_Tabela2.createStream(file);
		setState();
		setEventManipulator();
	}
	
	public int getCodigoSeleccionado()
		{ return codigoSeleccionado; }
	
	public String getItemSeleccionado()
		{ return itemSeleccionado; }
		
	public void setState()
	{
		itemSeleccionado = (String) getItemAt(0);
		stream =  Contentor_Tabela2.createStream(file);
		codigoSeleccionado = stream.get_Codigo(itemSeleccionado);
	}
	
	public void setEventManipulator()
	{
		addItemListener( new ItemListener()
		    {
		       public void itemStateChanged(ItemEvent e)
		       {
			 if (e.getStateChange() != ItemEvent.SELECTED)
				return;
			 
			 updateState();			   
		      }
		    });
		    
		    
	}
	
	public void updateState()
	{
		itemSeleccionado = (String) getSelectedItem();
		stream =  Contentor_Tabela2.createStream(file);
		codigoSeleccionado = stream.get_Codigo(itemSeleccionado);
	}
	
	  
}
