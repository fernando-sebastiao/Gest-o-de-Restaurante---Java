package SwingComponents;
import java.util.*;
import javax.swing.*;


public class SubFormJTextFieldComboBox extends SubFormJTextField
{
	protected Vector combos;
	
	public SubFormJTextFieldComboBox()
  	{
  		combos = new Vector();
  	}
  	
  	public void clear()
  	{
  		super.clear();
  		int sz = combos.size();
		/*
  		for (int i = 0; i < sz; i++)
  			((JComboBox) combos.elementAt(i)).setSelectedIndex(0);
		*/
  	}
  	
	public void addComboBox(JComboBox jb)
		{ combos.add(jb); }
}

