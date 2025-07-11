package SwingComponents;

import java.util.*;

public class StringBufferModeloVector extends Vector
{
	
	public StringBufferModeloVector() { }
	
	public StringBufferModeloVector(StringBufferModeloVector v) { super(v); }
	
	public StringBufferModeloVector remove(StringBufferModelo item , Vector list )
	{
		StringBufferModeloVector newList = new StringBufferModeloVector(this);
		return newList.removeInside(item, list);
		
	}
	
	public StringBufferModeloVector remove(Vector list )
	{
		StringBufferModeloVector newList = new StringBufferModeloVector(this);
		return newList.removeInside(list);
		
	}
	
	public StringBufferModeloVector removeInside(Vector list )
	{
		int sz = list.size();
		StringBufferModelo st = null;
		for (int i = 0; i < sz; i++)
		{
			st = (StringBufferModelo) list.elementAt(i);
			if (new String(st.getBuffer()).trim().equals("") != true)
				remove(st);
		}
		return this;
	}
	
	public StringBufferModeloVector removeInside(StringBufferModelo item , Vector list )
	{
		if (new String(item.getBuffer()).trim().equals("") != true)
			remove(item);
		removeInside(list );
		return this;
	}
	
	public void sort()
	{
		Collections.sort(this , new CompareStrings());
	}

	private class CompareStrings implements Comparator
	{
		public int compare(Object o1, Object o2)
		{
			StringBufferModelo s1 = (StringBufferModelo) o1;
			StringBufferModelo s2 = (StringBufferModelo) o2;
			return new String(s1.getBuffer()).trim().compareToIgnoreCase(new String(s2.getBuffer()).trim());
		}
	}
	
}
