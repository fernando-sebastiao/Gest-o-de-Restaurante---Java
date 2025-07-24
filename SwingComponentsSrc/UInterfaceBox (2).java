package RSComponents;
/***********************************
Nome: Osvaldo Manuel Ramos
N. 2817
Data: 7 de Outubro de 2006
************************************/

import javax.swing.*;
import java.util.*;


public abstract class UInterfaceBox
{
  public UInterfaceBox(String[] names)
  {
  }

  public static JLabel[] createJLabels(String[] names)
  {
    int n = names.length;
    JLabel[] Labels = new JLabel[n];
    for (int i = 0; i < n; i++)
      Labels[i] = new JLabel(names[i]);
    return Labels;
  }

  public static JTextField[] createJTextFields(int n, int ncl)
  {
    JTextField[] Fields = new JTextField[n];
    for (int i = 0; i < n; i++)
      Fields[i] = new JTextField(ncl);
    return Fields;
  }

  public static JButton[] createJButtons(String[] names)
  {
    int n = names.length;
    JButton[] Buttons = new JButton[n];
    for (int i = 0; i < n; i++)
      Buttons[i] = new JButton(names[i]);
    return Buttons;
  }

  public static JComboBox[] joinJComboBoxs(JComboBox c, JComboBox[] c2)
  {
   int sz = c2.length;
   JComboBox[] ComboBoxs = new JComboBox[sz + 1];
   ComboBoxs[0] = c;
   for (int i = 1; i <= sz; i++)
       ComboBoxs[i] = c2[i - 1];
   return ComboBoxs;
  }

  /*public static JComboBoxPersonal[] joinJComboBoxs(JComboBoxPersonal[] c1, JComboBoxPersonal c)
  {
   int  i = 0, sz1 = c1.length;

   JComboBoxPersonal[] ComboBoxs = new JComboBoxPersonal[sz1 + 1];
   for ( ; i < sz1; i++)
       ComboBoxs[i] = c1[i];
   ComboBoxs[i] = c;
   return ComboBoxs;
  }
  
  public static JComboBox[] joinJComboBoxs(JComboBox[] c1, JComboBox c)
  {
   int  i = 0, sz1 = c1.length;

   JComboBox[] ComboBoxs = new JComboBox[sz1 + 1];
   for ( ; i < sz1; i++)
       ComboBoxs[i] = c1[i];
   ComboBoxs[i] = c;
   return ComboBoxs;
  }

  public static JComboBox[] joinJComboBoxs(JComboBox[] c1, JComboBox[] c2)
  {
   int  i = 0, sz1 = c1.length;
   int sz2 = c2.length;
   JComboBox[] ComboBoxs = new JComboBox[sz1 + sz2];
   for ( ; i < sz1; i++)
       ComboBoxs[i] = c1[i];
   for (int j = 0; j < sz2; i++, j++)
       ComboBoxs[i] = c2[j];
   return ComboBoxs;
  }

  public static JComboBox[] createJComboBoxs(Vector names)
  {
    int sz = names.size();
    JComboBox[] ComboBoxs = new JComboBox[sz];
    for (int i = 0; i < sz; i++)
      ComboBoxs[i] = new JComboBox((String[])(names.elementAt(i)));
    return ComboBoxs;
  }

  public static JComboBoxPersonal createJComboBoxsTabela2(String Filename)
  {
   Contentor_Tabela2 str =  Contentor_Tabela2.createStream(Filename);
//JOptionPane.showMessageDialog(null, "Provincias: " + str.debug());
   str.Sort();
   String [] items = str.get_Designacoes();
   
   return new JComboBoxPersonal(new JComboBox(items != null ? items :  New_String.Gera_Vector_Strings_Nulo(2)));
  }
  
  public static JComboBoxPersonalTab2 createJComboBoxPersonalTab2(String filename)
  {
   Contentor_Tabela2 str =  Contentor_Tabela2.createStream(filename);
//JOptionPane.showMessageDialog(null, "Provincias: " + str.debug());
   str.Sort();
   String [] items = str.get_Designacoes();
   
   return new JComboBoxPersonalTab2(new JComboBox(items != null ? items :  New_String.Gera_Vector_Strings_Nulo(2)), filename);
  }*/
}
