package SwingComponents;
import java.io.*;

/**
 * <p>Title: ROF</p>
 * <p>Description: Random Object Files</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: Goncar Software</p>
 * @author C�sar J. G. Cardoso
 * @version 1.0
 */

 
public class Sizeof implements Serializable {
        public static long sizeof(RegistGeneric out) throws IOException 
		{
			File f = new File(Defs.tmpFile);
			
			if (f.exists())
				f.delete();
			
			RandomAccessFile baos = new RandomAccessFile(Defs.tmpFile, "rw");
			out.write(baos);
			long sz = baos.length();
			baos.close();
			(new File(Defs.tmpFile)).delete();
			return sz;
		}
}