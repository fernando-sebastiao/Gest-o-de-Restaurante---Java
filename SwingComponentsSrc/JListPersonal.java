package SwingComponents;
import javax.swing.*;
import java.util.*;
import java.util.*;



public class JListPersonal extends JList
{
	public JListPersonal(JList j)
	{
		super(j.getModel());
	}
	
	public JListPersonal(Vector v)
	{
		super(v);
	}
	
	public JListPersonal() 	{ }
	
	public void insert (String item)
	{
		DefaultListModel newModel = new DefaultListModel();
		ListModel listModel = getModel();
		int sz = listModel.getSize();
		String st = null;
		
		for (int i = 0; i < sz; i++)
		{
			st = ((String) listModel.getElementAt(i)).trim();
			if (st.equals("") == false)
				newModel.addElement(st);
		}
		
		newModel.addElement(item);
		setModel(newModel);
		sort();
        this.requestFocus();
	}
	
	public void insert(Object[] items)
	{
		DefaultListModel newModel = new DefaultListModel();
		ListModel listModel = getModel();
		int sz = listModel.getSize();
		String st = null;
		for (int i = 0; i < sz; i++)
		{
			st = ((String) listModel.getElementAt(i)).trim();
			if (st.equals("") == false)
				newModel.addElement(st);
		}
		
		sz = items.length;
		for (int i = 0; i < sz; i++)
		{
			st = ((String) items[i]).trim();
			if (st.equals("") == false)
				newModel.addElement(st);
		}
		
		setModel(newModel);
		sort();
        this.requestFocus();
	}
	
	public void sort()
	{
		ListModel listModel = getModel();
		java.util.Vector list = new java.util.Vector();
		int sz = listModel.getSize();
		String st = null;
		for (int i = 0; i < sz; i++)
		{
			st = ((String) listModel.getElementAt(i)).trim();
			if (st.equals("") == false)
				list.add(st);
		}
		Collections.sort(list);
		DefaultListModel newModel = new DefaultListModel();
		for (int i = 0; i < sz; i++)
		{
			st = ((String) list.elementAt(i)).trim();
			if (st.equals("") == false)
				newModel.addElement(st);
		}
		setModel(newModel);
	}
	
	public StringVector getItems()
	{
		ListModel listModel = getModel();
		StringVector list = new StringVector();
		int sz = listModel.getSize();
		String st = null;
		for (int i = 0; i < sz; i++)
		{
			st = ((String) listModel.getElementAt(i)).trim();
			if (st.equals("") == false)
				list.add(st);
		}
		return list;
	}
	
	public void changeModel(String[] items)
	{
		DefaultListModel newModel = new DefaultListModel();
		ListModel listModel = getModel();
		int sz = listModel.getSize();
		String st = null;
        for (int i = 0; i < sz; i++)
		{
			st = ((String) listModel.getElementAt(i)).trim();
			if (st.equals("") == false)
				newModel.addElement(st);
		}
		
		setModel(newModel);
		sort();
        requestFocus();
	}
			
	public void changeModel(Vector items)
	{
		int sz =  items.size();
		DefaultListModel newModel = new DefaultListModel();

		String st = null;
        for (int i = 0; i < sz; i++)
		{
			st = ((String) items.elementAt(i)).trim();
			if (st.equals("") == false)
				newModel.addElement(st);
		}
		setModel(newModel);
		sort();
        requestFocus();
	}
	
	public String toString()
	{
		String msg = "{ ";
		ListModel listModel = getModel();

		int sz = listModel.getSize();
		String st = null;
		for (int i = 0; i < sz; i++)
		{
			st = ((String) listModel.getElementAt(i)).trim();
			if (st.equals("") == false)
			{
				msg += i != 0 ? ", " : "";
				msg += st;
			}
		}
		msg += " }";
		return msg;
	}
	
	public void debug()
	{
		JOptionPane.showMessageDialog(null, toString());
	}
}
