package SwingComponents;

import javax.swing.*;
import java.awt.event.*;
//import java.util.*;
import java.awt.*;

public class DataPanel extends JPanel implements ItemListener {

  private static final int CBX_ANO = 0, CBX_MES = CBX_ANO + 1, CBX_DIA = CBX_MES + 1;
  private JComboBox[] combosData;
  private Data data;

  public DataPanel() {
    this(new Data());
  }

  public DataPanel(Data data)
  {
    this.data = data;

    createGUI();
  }

  public void createGUI()
  {
    combosData = new JComboBox[3];

    setLayout(new FlowLayout());

    Integer anos[] = new Integer[200];

    // montar anos
    for (int i = 0; i < 200; i++)
      anos[i] = new Integer(i+1900);

    // montar dias
    Integer dias[] = new Integer[data.diasMes()];

    for (int i = 0; i < data.diasMes(); i++)
      dias[i] = new Integer(i+1);

    for (int i = 0; i < 3; i++)
    {
      switch(i)
      {
          case(CBX_DIA): combosData[i] = new JComboBox(dias);
        combosData[i].setSelectedItem(new Integer(data.getDia()));
        break;
          case(CBX_MES): combosData[i] = new JComboBox(Data.meses);
            combosData[i].setSelectedItem(Data.meses[data.getMes()-1]);
            break;
          case(CBX_ANO): combosData[i] = new JComboBox(anos);
            combosData[i].setSelectedItem(new Integer(data.getAno()));
            break;
          default: break;
      }

      combosData[i].setMaximumRowCount(3);
      combosData[i].addItemListener(this);
      add(combosData[i]);
    }
  }

  public void setData(Data dt)
  {
    //System.out.println("SetData:DataPanel");
    //data = dt;
    //System.out.println("Ano(data): " + data.getAno());
    //System.out.println("Ano(dt): " + dt.getAno());
    combosData[CBX_ANO].setSelectedItem(new Integer(dt.getAno()));
    //System.out.println("Mes(data): " + data.getMes());
    //System.out.println("Mes(dt): " + dt.getMes());
    combosData[CBX_MES].setSelectedIndex(dt.getMes()-1);
    //System.out.println("Dia(data): " + data.getDia());
    //System.out.println("Dia(dt): " + dt.getDia());
    combosData[CBX_DIA].setSelectedIndex(dt.getDia()-1);
    data = dt;
  }

  public Data getData()
  {
    return data;
  }

  public void reset()
  {
    System.out.println("beginReset:DataPanel");
    setData(new Data());
    System.out.println("endReset:DataPanel");
  }

  public void itemStateChanged(ItemEvent event)
  {
    if (event.getStateChange() == ItemEvent.SELECTED)
    {
      if (event.getSource() == combosData[CBX_MES]) {

        //JOptionPane.showMessageDialog(null, "MES", "",
          //                            JOptionPane.PLAIN_MESSAGE);
        int mes = combosData[CBX_MES].getSelectedIndex() + 1;
        data.setMes(mes);

        // montar dias
        combosData[CBX_DIA].removeAllItems();

        for (int i = 0; i < data.diasMes(); i++)
          combosData[CBX_DIA].addItem(new Integer(i+1));

        combosData[CBX_DIA].setSelectedIndex(0);
      }
      else if (event.getSource() == combosData[CBX_ANO]) {

        //JOptionPane.showMessageDialog( null, "ANO", "", JOptionPane.PLAIN_MESSAGE);

        int ano = Integer.parseInt(combosData[CBX_ANO].getSelectedItem().toString());
        data.setAno(ano);

        combosData[CBX_MES].setSelectedIndex(0);
      }
      else if (event.getSource() == combosData[CBX_DIA]) {

        //JOptionPane.showMessageDialog( null, "DIA", "", JOptionPane.PLAIN_MESSAGE);

        int dia = combosData[CBX_DIA].getSelectedIndex() + 1;
        data.setDia(dia);
      }
    }
  }
}
