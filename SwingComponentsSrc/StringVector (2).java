package SwingComponents;

import java.util.*;

public class StringVector extends Vector
{
	
	public StringVector() { }
	
	public StringVector(StringVector v) { super(v); }
	
	public StringVector remove(String item , Vector list )
	{
		StringVector newList = new StringVector(this);
		return newList.removeInside(item, list);
		
	}
	
	public StringVector remove(Vector list )
	{
		StringVector newList = new StringVector(this);
		return newList.removeInside(list);
		
	}
	
	public StringVector removeInside(Vector list )
	{
		int sz = list.size();
		String st = null;
		for (int i = 0; i < sz; i++)
		{
			st = (String) list.elementAt(i);
			if (st.trim().equals("") != true)
				remove(st);
		}
		return this;
	}
	
	public StringVector removeInside(String item , Vector list )
	{
		if (item.trim().equals("") != true)
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
			String s1 = (String) o1;
			String s2 = (String) o2;
			return s1.compareToIgnoreCase(s2);
		}
	}
	
}
