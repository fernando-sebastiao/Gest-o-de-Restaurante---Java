package Calendario;

import javax.swing.JComboBox;

public class JComboBoxMes extends JComboBox 
{
	private static String meses[] = 
  {
    "Janeiro","Fevereiro","Mar�o","Abril","Maio","Junho",
    "Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"
  };
  
  public JComboBoxMes(int mesSeleccionado)
  {
		super(meses);

    setSelectedIndex(mesSeleccionado);
  }
  public String getMesSeleccionadoString()
    { return meses[this.getMesSeleccionado()]; }
  public int getMesSeleccionado()
    { return this.getSelectedIndex(); }
}