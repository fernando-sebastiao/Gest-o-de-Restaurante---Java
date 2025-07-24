/******************************************************************************

Nome: Felisberto Pinto Delgado
N� 1735
Data: 7 de Outubro de 2003
Objetivo : 
******************************************************************************/
package SwingComponents;

import java.io.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;



public class Contentor_Tabela2 extends Vector_Tabelas
{
  public Contentor_Tabela2(String file)
  {
     super( file );
  }
  
  //Metodo q recebe o codigo e retorna a designacao
  public String get_Designacao(int Cod)
  {
          for( int i = 0; i < size(); i++ )
             if(((Tabela2)elementAt(i)).get_Codigo() == Cod )
               return ((Tabela2)elementAt(i)).get_Designacao();
          return "";
  }

  //Metodo q recebe a designacao e retorna o codigo
  public int get_Codigo(String Desig)
  {
          //New_String str = new New_String();
          for ( int i = 0; i < size(); i++ )
          {
               if ((((Tabela2)elementAt(i)).get_Designacao()).equalsIgnoreCase( Desig.trim() ) == true )
                return ((Tabela2)elementAt(i)).get_Codigo();
          }
          return  0;
  }

  //Metodo q recebe a designacao e retorna o codigo
  public static int get_Codigo(String Desig, String Filename)
  {
    return Contentor_Tabela2.createStream(Filename).get_Codigo(Desig);
  }

  public void Sort()
  {
    Collections.sort(this , new Tabela2_CompareDesignacao());
  }
  //*******
  private class Tabela2_CompareDesignacao implements Comparator
  {
    public int compare(Object o1, Object o2)
    {
        return (((Tabela2)o1).get_Designacao()).compareToIgnoreCase(
                              ((Tabela2)o2).get_Designacao());
    }
  }

  public int Search(String obj)
  {
    return Collections.binarySearch(this, new String(obj),
      new Tabela2_Search());
  }

  private class Tabela2_Search implements Comparator
  {
    public int compare(Object o, Object key)
    {
      return ((Tabela2)o).get_Designacao().
        compareToIgnoreCase(((String)key).trim());
    }
  }

  public Tabela2 createNewTabela(String name)
  {
    Tabela2 t = new Tabela2(name);
    t.set_Codigo(Obter_Proximo_Codigo());
    return t;
  }
  
  public static Contentor_Tabela2 createStream(String Filename)
  {
    Contentor_Tabela2 tb = (Contentor_Tabela2) StreamsTabelas.get(Filename);
    if (tb != null)
      return tb;
    tb = new Contentor_Tabela2(Filename);
    StreamsTabelas.put(Filename, tb);
    tb.Teste_File();
    tb.Read_File();
    return tb;
  }
}