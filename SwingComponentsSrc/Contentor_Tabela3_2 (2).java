package SwingComponents;/******************************************************************************

Nome: Felisberto Pinto Delgado
N� 1735
Data: 7 de Outubro de 2003
Objetivo : 
******************************************************************************/


import java.io.*;
import javax.swing.*;
import java.util.*;

public class Contentor_Tabela3_2  extends Vector_Tabelas
{
//  public static boolean flag = false;
  
  public Contentor_Tabela3_2(String file)
  {
    super(file);
  }

  //Metodo q recebe o codigo e retorna a designacao
  public String get_Designacao(int Codigo)
  {
      Vector str = new Vector();
      int sz = size();
      for(int i = 0; i < sz; i++)
      {
        Tabela3_2 tb = (Tabela3_2)elementAt( i );
        if ( tb.get_Codigo() == Codigo )
          return tb.get_Designacao();
      }
      return "";
  }

  //*********
  public String[] get_Designacoes(int CodigoPai)
  {
      Vector str = new Vector();
      int sz = size();
      for (int i = 0; i < sz; i++)
      {
        Tabela3_2 tb = (Tabela3_2)elementAt( i );
        if ( tb.get_Codigo_Pai() == CodigoPai )
          str.addElement(tb.get_Designacao());
      }
      return New_String.get_Strings(str);
  }

  //Metodo q recebe a designacao e retorna o codigo
  public int get_Codigo(String Desig)
  {
          New_String str = new New_String();
          for ( int i = 0; i < size(); i++ )
          {
              if (  ((Tabela3_2)elementAt(i)).get_Designacao().equalsIgnoreCase( Desig.trim() ) == true )
                return ((Tabela3_2)elementAt(i)).get_Codigo();
          }
          return  0;
  }

  public int get_Codigo(int CodigoPai, String Desig)
  {
/*if (flag)
Debug.debug("0: public int get_Codigo(int CodigoPai, String Desig)");*/
      Vector str = new Vector();
      int sz = size();
      for (int i = 0; i < sz; i++)
      {
        Tabela3_2 tb = (Tabela3_2)elementAt( i );
        if ( (tb.get_Codigo_Pai() == CodigoPai) && 
            (tb.get_Designacao().equalsIgnoreCase(Desig.trim()) == true))
          return tb.get_Codigo();
      }
      return 0;
  }
  

  //Metodo q recebe a designacao e retorna o codigo
  public static int get_Codigo(String Desig, String Filename)
  {
    return Contentor_Tabela3_2.createStream3_2(Filename).get_Codigo(Desig);
  }

   //***********
  public void Sort()
  {
    Collections.sort(this , new Tabela3_CompareDesignacao());
  }
   //**********
  private class Tabela3_CompareDesignacao implements Comparator
  {
     public int compare(Object o1, Object o2)
     {
         return (((Tabela3_2)o1).get_Designacao()).compareToIgnoreCase(
           ((Tabela3_2)o2).get_Designacao());
     }
  }

  public int Search(String munic)
  {
     return Collections.binarySearch(this, new String(munic)
        , new Municipio_Search());
  }

  private class Municipio_Search implements Comparator
  {
     public int compare(Object o, Object key)
     {
       return ((Tabela3_2)o).get_Designacao().
                  compareToIgnoreCase(((String)key).trim());
     }
  }

  public Tabela3_2 createNewTabela3_2(int CodProv, String name)
  {
     Tabela3_2 t = new Tabela3_2(CodProv, name);
     t.set_Codigo(Obter_Proximo_Codigo());
     return t;
  }

  public static Contentor_Tabela3_2 createStream3_2(String Filename)
  {
    /*
      verificar se existir canal no Contentor
      Se existir
        retorna canal
      senao
        retorna um novo
    */
    Contentor_Tabela3_2 tb = (Contentor_Tabela3_2) StreamsTabelas.get(Filename);
    if (tb != null)
      return tb;
    tb = new Contentor_Tabela3_2(Filename);
    StreamsTabelas.put(Filename, tb);
    tb.Teste_File();
    tb.Read_File();
    return tb;
  }
}
