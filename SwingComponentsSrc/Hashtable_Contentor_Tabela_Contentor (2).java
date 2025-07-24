/******************************************************************************

Nome: Felisberto Pinto Delgado
Nº 1735
Data: 7 de Outubro de 2003
Objetivo : 
******************************************************************************/
package SwingComponents;


import java.util.*;

public class Hashtable_Contentor_Tabela_Contentor extends Hashtable
{
  public Hashtable_Contentor_Tabela_Contentor()
  {
    super(100);
  }

  public Vector_Tabelas getStream(String File)
  {
    return (Vector_Tabelas) get(File);
  }
}