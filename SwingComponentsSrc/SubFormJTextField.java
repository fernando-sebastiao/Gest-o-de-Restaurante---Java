package SwingComponents;
import java.util.*;
import javax.swing.*;

public class SubFormJTextField extends SubFormClearInterface
{
	protected Vector fields;
	
	public SubFormJTextField()
  	{
  		fields = new Vector();
  	}	
  	
  	public void createJTextFields(int n, int ncl)
  	{
    	for (int i = 0; i < n; i++)
      		fields.add(new JTextField(ncl));
  	}
	
  	public void clear()
  	{
  		int sz = fields.size();
  		for (int i = 0; i < sz; i++)
  			((JTextField) fields.elementAt(i)).setText("");
  	}
	
	public JTextField createInvisibleJTextField()
	{
		JTextField jt = new JTextField();
		jt.setVisible(false);
		return jt;
	}
}


