package Calendario;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


  public class Dia extends JPanel 
  {
    int value;
    private DiasMes dm;
    static public final Color selectedColor = Color.magenta;
    
    public Dia(int dia, Data data, DiasMes dm)
    { 
      this.value = dia;
      this.dm = dm;
            
      this.addMouseListener(
        new MouseAdapter()
        {
          public void mouseClicked(MouseEvent e)
          {
            // put code here
            select();
          }
        }
      );
    }
    public int getValue()
      { return value; }
    public void select()
    {
      if (value == 0)
        return;
      dm.resetSelectedDay();
      dm.select(this);
      setBackground(selectedColor);
    }
    public void actualiza(int v)
    {
      this.value = v;
      this.repaint();
    }
    public Dimension getPreferredSize()
    {
      return new Dimension(17, 17);
    }
    public Dimension getMinimumSize()
    {
      return getPreferredSize();
    }
    public void paintComponent(Graphics g)
    {
      super.paintComponent(g);
      
      if (value != 0)
        g.drawString(String.valueOf(this.value), value > 9 ? 3 : 6, 12);
    }
  }