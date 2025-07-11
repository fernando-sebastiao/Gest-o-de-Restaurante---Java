package SwingComponents;/******************************************************************************

Nome: Felisberto Pinto Delgado
Nº 1735
Data: 7 de Outubro de 2003
Objetivo : 
******************************************************************************/


import java.io.*;
import javax.swing.*;
import java.util.*;

public class Contentor_Tabela3_3  extends Contentor_Tabela3_2
{
  public Contentor_Tabela3_3(String file)
  {
    super(file);
  }


    //*********

  //Metodo q recebe a designacao e retorna o codigo
  public static int get_Codigo(String Desig, String Filename)
  {
    return Contentor_Tabela3_3.createStream3_3(Filename).get_Codigo(Desig);
  }

   //***********
  public void Sort()
  {
    Collections.sort(this, new Tabela3_CompareDesignacao());
  }
   //**********
  private class Tabela3_CompareDesignacao implements Comparator
  {
     public int compare(Object o1, Object o2)
     {
         return (((Tabela3_3)o1).get_Designacao()).compareToIgnoreCase(
           ((Tabela3_3)o2).get_Designacao());
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
       return ((Tabela3_3)o).get_Designacao().
                  compareToIgnoreCase(((String)key).trim());
     }
  }

  public Tabela3_3 createNewTabela3_3(int CodProv, String name)
  {
     Tabela3_3 t = new Tabela3_3(CodProv, name);
     t.set_Codigo(Obter_Proximo_Codigo());
     return t;
  }

  public static Contentor_Tabela3_3 createStream3_3(String Filename) // ????
  {
    /*
      verificar se existir canal no Contentor
      Se existir
        retorna canal
      senao
        retorna um novo
    */
    Contentor_Tabela3_3 tb = (Contentor_Tabela3_3) StreamsTabelas.get(Filename);
    if (tb != null)
      return tb;
    tb = new Contentor_Tabela3_3(Filename);
    StreamsTabelas.put(Filename, tb);
    tb.Teste_File();
    tb.Read_File();
    return tb;
  }
}
