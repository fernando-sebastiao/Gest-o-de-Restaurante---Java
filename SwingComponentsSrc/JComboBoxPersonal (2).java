package SwingComponents;import javax.swing.JComboBox;
import java.util.*;
import java.awt.*;

public class JComboBoxPersonal extends JComboBox
{
	public JComboBoxPersonal(JComboBox j)
	{
		super(j.getModel());
	}
	public JComboBoxPersonal(Vector v)
	{
		super(v);
	}
	public JComboBoxPersonal() 	{ }
	public void insert (String item)
	{
		ArrayList list = new ArrayList();
		int sz = this.getItemCount();
		String st;
		for (int i = 0; i < sz; i++)
		{
			st = (String) this.getItemAt(i);
			if (st.trim().equals("") == false)
				list.add(st);
		}
		list.add(item);
		Collections.sort(list);
		this.removeAllItems();
		sz = list.size();
		for (int i = 0; i < sz; i++)
			this.addItem(list.get(i));
        this.requestFocus();
	}
	public Vector getItems()
	{
		Vector list = new Vector();
		int sz = this.getItemCount();
		String st;
		for (int i = 0; i < sz; i++)
		{
			st = ((String) this.getItemAt(i)).trim();
			if (st.equals("") == false)
				list.add(st);
		}
		return list;
	}
	
	public void changeModel(String[] items)
	{
		removeAllItems();
		int sz =  items.length;
        for (int i = 0; i < sz; i++)
           addItem(items[i]);
        requestFocus();
        setBackground(Color.white);
	}
	public void changeModel(Vector items)
	{
		removeAllItems();
		int sz =  items.size();
        for (int i = 0; i < sz; i++)
           addItem(items.elementAt(i));
        requestFocus();
        setBackground(Color.white);
	}
}
