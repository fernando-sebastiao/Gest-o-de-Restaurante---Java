package Calendario;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;



public class DiasMes extends JPanel 
{
  private int diaSeleccionado;
  private int mesSeleccionado; // 0..11
  private int anoSeleccionado;
  private int selectionStart, selectionEnd;
  private Data data;
  private Dia selectedJPanelDay;
  TableBody diasDoMes;

  private Color backgroundColor;
  
  private static int[] totalDias = 
    {
      31, 28, 31, 30, 31, 30,
      31, 31, 30, 31, 30, 31
    };
  private static String[] weekDays = 
    {
      "S", "T", "Q", "Q", "S", "S", "D"
    };
  private static int[] weekDaysEn =
  	{
  		Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, 
  		Calendar.THURSDAY, Calendar.FRIDAY, Calendar.SATURDAY, Calendar.SUNDAY
  	};

  public DiasMes(Data data)
  {
      this.data = data;
      getCurrentData();
      backgroundColor = this.getBackground();
      
      setLayout(new BorderLayout());
      //generate table head;
      TableHeader th = new TableHeader();
      add(th, BorderLayout.NORTH);

      //generate table body;
      diasDoMes = new TableBody(this);
      add(diasDoMes, BorderLayout.CENTER);
  }
 
  public void select(Dia d)
  {
    selectedJPanelDay = d;
    data.setDiaSeleccionado(d.getValue());
  }
  public void resetSelectedDay()
  {
    if (selectedJPanelDay != null)
      selectedJPanelDay.setBackground(this.getBackground());
  }
  public int getAnoSeleccionado() 
    { return anoSeleccionado; }
  public int getMesSeleccionado() /*0..11*/
    { return mesSeleccionado; }
  public int getDiaSeleccionado() /*1..31*/
    { return diaSeleccionado; }
  public void getCurrentData()
  {
      diaSeleccionado = data.getDiaSeleccionado(); // 1..31
      mesSeleccionado = data.getMesSeleccionado(); // 0..11
      anoSeleccionado = data.getAnoSeleccionado();    
  }    
  public void setDiaSeleccionado(int d)
  {
    diaSeleccionado = d;
  }
  public void geraDias(int mes, int ano)
  {
    mesSeleccionado = mes;
    anoSeleccionado = ano;
    diasDoMes.actualiza();
  }
  public boolean ehBissexto()
  { 
      return (anoSeleccionado % 4 == 0 && anoSeleccionado % 100 != 0) || 
            (anoSeleccionado % 400 == 0); 
  }

  private class TableHeader extends JPanel
  {
    public TableHeader()
    {
      setLayout(new GridLayout(1, 7));
      JLabel jl;
      for (int i = 0; i < 7; i++)
      {
        jl = new JLabel(weekDays[i], SwingConstants.CENTER);
        add(jl);
      }
      this.setBackground(Color.lightGray);  
    }
  }  // fim da classe TableHeader
  
  private class TableBody extends JPanel
  {
    private int nrows = 6, ncols = 7;
    public TableBody(DiasMes dm)
    {
      int day;
      Dia d;
      
      setLayout(new GridLayout(nrows, ncols));
      int delayedDay = generateDelayedDay();
      
      for (int i = 0, k = 0; i < nrows; i++)
        for (int j = 0; j < ncols; j++, k++)
        {
          day = k + 1 + delayedDay;

          if (day < 0 || day > 31)
            day = 0;
          d = new Dia( day, data, dm);
          add(d);
          if (day == 1)
          {
            d.setBackground(Dia.selectedColor);
            selectedJPanelDay = d;
          }
        }
    }
    public int calculaDiaSemana(int d)
    {
//JOptionPane.showMessageDialog(null, "Dia: " + d);
    	for (int i = 0; i < 7; i++)
    		if (d == weekDaysEn[i])
    			return i;
    	return -1;
	}
    public int generateFirstDayWeek()
    {
    	Calendar cl = Calendar.getInstance();
    	cl.set(anoSeleccionado, mesSeleccionado, 1);
    	return calculaDiaSemana(cl.get(Calendar.DAY_OF_WEEK)); // 0..6	
	}
    public int generateDelayedDay()
    {
    	int firstDay = generateFirstDayWeek();
//JOptionPane.showMessageDialog(null, "firstDay: " + firstDay);
    	return -1 * firstDay;
	}
    public void actualiza()
    {
      Dia d;
      int valor, dias = totalDias[mesSeleccionado];
      
    	if (ehBissexto() && mesSeleccionado == 1)
        dias = 29;
	  
	  	int delayedDay = generateDelayedDay();
//JOptionPane.showMessageDialog(null, "delayedDay: " + delayedDay);	 
      for (int day = 0, total =  nrows * ncols; day < total; day++)
      {
        d = (Dia) getComponent(day);
        valor = day + 1 + delayedDay;
        
        d.actualiza(valor <= 0 || valor > dias ? 0 : valor);
        if (valor == diaSeleccionado)
           d.select();          
      }
    }
  } // fim da classe TableBody
}