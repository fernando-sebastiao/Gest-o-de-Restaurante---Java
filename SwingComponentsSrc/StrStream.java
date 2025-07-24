package SwingComponents;
import java.io.*;


public abstract class StrStream
{
	/*
	public boolean isEqualsIgnoreCase(String s1, String s2)
	{
		
	}
	*/

	public static void Write_String( RandomAccessFile f, String name, int nchars )
      throws IOException
   {
      StringBuffer buf = null;

      if ( name != null )
         buf = new StringBuffer( name );
      else
         buf = new StringBuffer( nchars );

      buf.setLength( nchars );
      f.writeChars( buf.toString() );
   }

   public static String Read_String(  RandomAccessFile f, int nchars )
      throws IOException
   {
      char name[] = new char[ nchars ], temp;

      for ( int i = 0; i < name.length; i++ )
      {
         temp = f.readChar();
         name[ i ] = temp;
      }
      return new String( name ).replace( '\0', ' ' );
   }
   
  public static void Write_String( ObjectOutputStream f, String name )
      throws IOException
   {
      StringBuffer buf = null;

      if ( name != null )
         buf = new StringBuffer( name );
      else
         buf = new StringBuffer( 30 );

      buf.setLength( 30 );
      f.writeChars( buf.toString() );
      f.flush();
   }

   public static String Read_String( ObjectInputStream f )
      throws IOException
   {
      char name[] = new char[ 30 ], temp;

      for ( int i = 0; i < name.length; i++ )
      {
         temp = f.readChar();
         name[ i ] = temp;
      }
      return new String( name ).replace( '\0', ' ' );
   }

/*  public static void writeString( ObjectOutputStream f, String name )
      throws IOException
   {
      StringBuffer buf = null;

      if ( name != null )
         buf = new StringBuffer( name );
      else
         buf = new StringBuffer( 30 );

      buf.setLength( 30 );
      f.writeChars( buf.toString() );
   }
   public static String readString( ObjectInputStream f )
      throws IOException
   {
      char name[] = new char[ 30 ], temp;

      for ( int i = 0; i < name.length; i++ )
      {
         temp = f.readChar();
         name[ i ] = temp;
      }
      return new String( name ).replace( '\0', ' ' );
   }
*/
}