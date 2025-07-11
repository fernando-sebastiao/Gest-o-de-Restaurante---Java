package RSComponents;
/***********************************
Nome: Osvaldo Manuel Ramos
N. 2817
Data: 7 de Outubro de 2006
************************************/

import javax.swing.*;
import java.io.*;

public abstract class ObjectSerializableGeneric implements DebugInterface, RegistGeneric
{
	
	protected ObjectSerializableGeneric() { }
	
	public void debug()
	{
		JOptionPane.showMessageDialog(null, toString());
	}
	
	public long sizeof() throws IOException
	{
		return Sizeof.sizeof(this);
	}
}
