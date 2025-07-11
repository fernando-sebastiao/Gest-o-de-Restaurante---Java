package RSComponents;
/***********************************
Nome: Osvaldo Manuel Ramos
N. 2817
Data: 7 de Outubro de 2006
************************************/

import java.io.*;


public interface RegistGeneric extends Serializable
{
	public long sizeof() throws IOException;
	public String toString();
	public void read(RandomAccessFile stream);
	public void write(RandomAccessFile stream);
}

