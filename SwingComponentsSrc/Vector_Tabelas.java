/******************************************************************************

Nome: Felisberto Pinto Delgado
Nº 1735
Data: 7 de Outubro de 2003
Objetivo : 
******************************************************************************/
package SwingComponents;




import java.io.*;
import javax.swing.*;
import java.util.*;

public class Vector_Tabelas extends Vector implements Serializable
{
  protected String FileName;
  protected int ProximoCodigo = 1;
  public static Hashtable_Contentor_Tabela_Contentor StreamsTabelas;

  public Vector_Tabelas(String file)
  {
    FileName = file;
    ProximoCodigo = 1;
  }

  public int Obter_Proximo_Codigo()
  {
     	if ( ProximoCodigo != 1 )
				return ProximoCodigo;
			Read_File();
			return ProximoCodigo;
  }

  public void Gerar_Proximo_Codigo()
  {   ProximoCodigo++;   }

  public  void Read_File()
  {
      ObjectInputStream str = Open_Read_File();
      Read_File(str);
      Close_Input_File(str);
  }

  public void Read_File(ObjectInputStream str)
  {
      try
      {
        Read(str);
        str.close();
      }
      catch(IOException io)
      {
        Close_Exit_Input_File(str);
      }
  }

  public ObjectInputStream Open_Read_File()
  {
      ObjectInputStream str;
         // Open the file
         try
         {
            str  = new ObjectInputStream( new FileInputStream( FileName ) );
            return str;
         }
         catch ( IOException e )
         {
            JOptionPane.showMessageDialog( null,
               "Ficheiro não existe",
               "Nome do ficheiro invalido",
               JOptionPane.ERROR_MESSAGE );
         }
    return null;
  }

   public void Close_Input_File(ObjectInputStream str)
   {
      try
      {
         str.close();
      }
      catch( IOException ex )
      {
         JOptionPane.showMessageDialog( null,
            "Erro ao fechar o ficheiro",
            "Erro", JOptionPane.ERROR_MESSAGE );
         System.exit( 1 );
      }
   }

   public void Close_Exit_Input_File(ObjectInputStream str)
   {
      try
      {
         str.close();
         System.exit( 0 );
      }
      catch( IOException ex )
      {
         JOptionPane.showMessageDialog( null,
            "Erro ao fechar o ficheiro",
            "Erro", JOptionPane.ERROR_MESSAGE );
         System.exit( 1 );
      }
   }

  /* public void Write_File()
   {
      ObjectOutputStream str = Open_Write_File(true);
      try
      {
        Write(str);
        str.flush();
        str.close();
      }
      catch(IOException io)
      {
        Close_Exit_Output_File(str);
      }
   }     */
   public void Teste_File()
   {
      File Stream = new File(FileName);
      if (!((Stream.exists() == true)&&(Stream.isFile() == true)))
      {
        ObjectOutputStream str = Open_Write_File(true);
        try
        {
          Write(str);
          str.flush();
          str.close();
        }
        catch(IOException io)
        {
          Close_Exit_Output_File(str);
        }
      }
   }

   public ObjectOutputStream Open_Write_File(boolean app)
   {
      ObjectOutputStream str;
         // Open the file
         try
         {
            str  = new ObjectOutputStream( new FileOutputStream( FileName, app ) );
            return str;
         }
         catch ( IOException e )
         {
            JOptionPane.showMessageDialog( null,
               "Ficheiro não existe",
               "Nome do ficheiro invalido",
               JOptionPane.ERROR_MESSAGE );
         }
    return null;
   }

   public void close_Output_File(ObjectOutputStream str)
   {
      try
      {
         str.close();
      }
      catch( IOException ex )
      {
         JOptionPane.showMessageDialog( null,
            "Erro ao fechar o ficheiro",
            "Erro", JOptionPane.ERROR_MESSAGE );
         System.exit( 1 );
      }
   }

   public void Close_Exit_Output_File(ObjectOutputStream str)
   {
      try
      {
         str.close();
         System.exit( 0 );
      }
      catch( IOException ex )
      {
         JOptionPane.showMessageDialog( null,
            "Erro ao fechar o ficheiro",
            "Erro", JOptionPane.ERROR_MESSAGE );
         System.exit( 1 );
      }
   }

   public void Actualizar_Ficheiro()
   {
      ObjectOutputStream str = Open_Write_File(false);
      try
      {
         Write(str);
         str.flush();
         str.close();
      }
      catch ( IOException e )
      {
         JOptionPane.showMessageDialog( null,
            "Nao foi possivel actualizar Ficheiro " + FileName,
            "Falha na actualização do ficheiro",
            JOptionPane.ERROR_MESSAGE );
      }
   }

   public String toString()
   {
      String str = "";

      int sz = size();
      for (int i = 0; i < sz; i++)
        str += (i != 0) ?"\n" + elementAt(i).toString() : elementAt(i).toString();
      return str;
   }

   public void Write(ObjectOutputStream str) throws IOException
   {
       StrStream.Write_String( str, FileName );
       str.writeInt( ProximoCodigo );
       str.flush();

       int sz = size();
       str.writeInt( sz );
       str.flush();
       for (int i = 0; i < sz; i++)
       	str.writeObject(elementAt(i));
        //((Tabela2)elementAt(i)).Write(str);
   }

   public void Read(ObjectInputStream str) throws IOException
   {
       String FileName2 = StrStream.Read_String( str );

       if ( FileName2.startsWith(FileName) == false)
       {
          JOptionPane.showMessageDialog( null,
               "O Nome registado do ficheiro " + FileName2 + "(" + FileName2.length() + ")"+ " Nao coincide com o nome proposto " + FileName + "(" +FileName.length() + ")",
               "Erro no nome dos ficheiros",
               JOptionPane.ERROR_MESSAGE );
          System.exit(1);
       }
       ProximoCodigo = str.readInt();

       int sz = str.readInt();
       if (sz != 0)
          Read_Vector_Tabelas(sz, str);
   }

   public void Read_Vector_Tabelas(int sz, ObjectInputStream str) 
   {
   	   try
       {
          for (int i = 0; i < sz; i++)
          {
           Tabela2 tb = (Tabela2)str.readObject();
           add(tb);
          }
       }
       catch ( OptionalDataException e )
       {
          
       }
       catch ( Exception e )
       {
              JOptionPane.showMessageDialog( null,
                 "Falha na leitura dos registos",
                 "Falha na leitura dos registos",
                 JOptionPane.ERROR_MESSAGE );
       } 
   }
  
   public static void inic()
   {
    StreamsTabelas = new Hashtable_Contentor_Tabela_Contentor();
   }
   
   
   public String[] get_Designacoes()
   {
      Vector str = new Vector();
      int sz = size();
      for (int i = 0; i < sz; i++)
      {
        Tabela2 tb = (Tabela2)elementAt( i );
        str.addElement(tb.get_Designacao());
      }
      return New_String.get_Strings(str);
   }
  


	
}
