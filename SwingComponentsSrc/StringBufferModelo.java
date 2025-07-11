/************************************************************************/
/* file.: ObjectSerializableGeneric.java                                */                 
/* Autor.: Osvaldo Manuel Ramos                                         */
/* Data.: 17-dez-2005                                                   */
/* Num.: 2817                                                           */
/* Objectivo.: Criacao de Janela                            		*/
/* Descricao.:                                				*/
/************************************************************************/


package SwingComponents;
import javax.swing.*;
import java.io.*;


public class StringBufferModelo extends ObjectSerializableGeneric
{
	private StringBuffer buffer;

	public StringBufferModelo(String name, int size)
	{
		buffer = null;
		
		int nl = name != null ? name.length() : 0;
		if ( name != null )
         	buffer = new StringBuffer( name );
		else
         	buffer = new StringBuffer( size );

      	buffer.setLength( size );

		for (int i = nl; i < size; i++ )
			buffer.setCharAt(i, ' ');
	}
	
	public StringBuffer getBuffer()
	{
		return buffer;
	}
	
	public StringBufferModelo(int size)
	{
		buffer = new StringBuffer( size );
		buffer.setLength( size );
		
		for (int i = 0; i < size; i++ )
			buffer.setCharAt(i, ' ');
	}
	
	public long sizeof()
	{
		return 2 * buffer.length();
	}
	
	public String toString()
	{
		return buffer.toString(); 
	}
	
	public String toStringEliminatingSpaces()
	{
		StringBuffer newBuffer = new StringBuffer(new String(buffer));
		int nl = newBuffer.length();
		
		char ch = 0;
		boolean change = true;
		for (int i = nl - 1; change && i >= 0; i--)
		{
			ch = newBuffer.charAt(i);
			if (Character.isWhitespace(ch) == true)
				newBuffer.setCharAt(i, '\0');
			else
				change = false;
		}
		return new String (newBuffer).trim();
	}
	public void write(RandomAccessFile stream)
	{
		try
		{
			stream.writeChars( buffer.toString() );
		}
		catch (IOException ex)
		{
			String msg = "falha na escrita de uma frase no ficheiro " ;
			JOptionPane.showMessageDialog(null, msg);
			System.exit(1);
		}
	}
	
	public void read(RandomAccessFile stream)
	{
		try
		{
			char name[] = new char[ buffer.length() ], temp;

			for ( int i = 0; i < name.length; i++ )
			{
				 temp = stream.readChar();
				 name[ i ] = temp;
			}
			buffer = new StringBuffer(new String( name ).replace( '\0', ' ' ));
			buffer.setLength(name.length);

		}
		catch (IOException ex)
		{
			String msg = "falha na leitura de uma frase no ficheiro " ;
			JOptionPane.showMessageDialog(null, msg);
			System.exit(1);
		}
	}
	
}
