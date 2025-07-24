package SwingComponents;
import javax.swing.*;
import java.util.*;

public class New_String
{
  String desg;

  public New_String()
  {
    desg = "";
  }
  
  public static String generateStringFixedlength(String name )
  {
      final int SZ = 30;
      StringBuffer buf = null;
      
      int nl = name.length();
      
      buf = (name != null) ? new StringBuffer( name ) : new StringBuffer( SZ );
      buf.setLength( SZ );
      for (int i = nl; i < SZ; i++ )
      	buf.setCharAt(i, ' ');
      	
      return buf.toString();
  }
   
  public static String[] Ordenar_Strings( String b[] )
  {
     for ( int pass = 1; pass < b.length; pass++ ) // passes
       for ( int i = 0; i < b.length - 1; i++ ) // one pass
         if ( b[ i ].compareTo( b[ i + 1 ]) > 0 )        // one comparison
           swap( b, i, i + 1 );
     return b;           // one swap
  }
  // swap two elements of an array
  public  static void swap( String c[], int first, int second )
  {
     String hold;  // temporary holding area for swap
     hold = c[ first ];
   	 c[ first ] = c[ second ];
   	 c[ second ] = hold;
   }

  public static boolean isNullString(String str)
  {
      int i = str.length() - 1;
      for ( ; i >= 0 && ((str.charAt( i ) == ' ') || (str.charAt( i ) == '\0')) ; i--)
          ;
      return i == -1;
  }

   public static String[] get_Strings(Vector vt)
   {
     int sz = vt.size();
     if (sz == 0)
         return null;
     String[] str = new String[sz];
     for (int i = 0; i < sz; i++)
       str[i] = ((String) vt.elementAt(i)).trim();
     return str;
   }
   
   public static String debug(String[] vt)
   {
     int sz = vt.length;
     String msg = "";
     if (sz == 0)
         return msg;
     for (int i = 0; i < sz; i++)
     	msg += i == 0 ? vt[i] : ", " + vt[i];
     return msg;
   }

   public static String[] Gera_Vector_Strings_Nulo(int sz)
   {
     String[] str = new String[sz];
     for (int i = 0; i < sz; i++)
       str[i] = "           ";
     return str;
   }
}