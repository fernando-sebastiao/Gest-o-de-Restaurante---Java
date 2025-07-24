package Calendario;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.text.*;

public class Data extends JFrame implements ActionListener
{
	private JComboBoxMes mes;
	private JButton calendario, aplicar, cancelar;
	private JLabel labelMes, labelAno;
	private DiasMes dia;
	private JSpinner ano;
  
  private int mesSeleccionado /*0..11*/, 
              diaSeleccionado /*1..31*/,
              anoSeleccionado;
	
	private JTextField data;
  
	public Data() { }
	public Data(JTextField data)
	{
		super("Data");
		try 
    {
	     UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception exc) 
		{
	    	System.err.println("Error loading L&F: " + exc);
		}
		this.data = data;	 
    generateCurrentData();

		dia = new DiasMes(this);	
			
		labelMes = new JLabel("Mes");
		labelAno = new JLabel("Ano");

		generateAno();
    generateMes();

    generateUserInterface();
	}
  
  public void generateUserInterface()
  {
    Container painel = this.getContentPane();
		painel.setBackground(Color.white);
		painel.setLayout(new BorderLayout());
	
		JPanel painelNorte = new JPanel();
		painelNorte.setLayout(new FlowLayout());
		painelNorte.setBackground(Color.white);
		painelNorte.add(labelMes);
		painelNorte.add(mes);
		painelNorte.add(labelAno);
		painelNorte.add(ano);
		
		JPanel painelCentro = new JPanel();
    JPanel painelDia = new JPanel();
    painelDia.add(dia);
		painelCentro.setBackground(Color.white);
		painelCentro.add(painelDia, BorderLayout.CENTER);
		
		JPanel painelSul = new JPanel();
		painelSul.setBackground(Color.white);
    painelSul.setLayout(new GridLayout(2, 1));
    
    JPanel painelCalendario = new JPanel();
		painelCalendario.setLayout(new FlowLayout()); 
		//painelCalendario.setBackgroud(getBackground());
    painelCalendario.setBackground(Color.white);
		calendario = new JButton("Ver Calendario");
		calendario.setBackground(Color.white);
    calendario.addActionListener(this);
    painelCalendario.add(calendario);
    painelSul.add(painelCalendario);
    
    JPanel painelAplicarCancelar = new JPanel();
		painelAplicarCancelar.setLayout(new FlowLayout()); 
    painelAplicarCancelar.setBackground(Color.white);
		aplicar = new JButton("Aplicar");
		aplicar.setBackground(Color.white);
		aplicar.addActionListener(this);
		cancelar = new JButton("Cancelar");
		cancelar.setBackground(Color.white);
		cancelar.addActionListener(this);		
    
    painelAplicarCancelar.add(aplicar);
    painelAplicarCancelar.add(cancelar);
		painelSul.add(painelAplicarCancelar);
    
		painel.add(painelNorte, BorderLayout.NORTH);
		painel.add(painelCentro, BorderLayout.CENTER);
		painel.add(painelSul, BorderLayout.SOUTH);

		setSize(230, 260);
		setBackground(Color.gray);
		setResizable(false);
		show();
	}
  
  public int getAnoSeleccionado() 
    { return anoSeleccionado; }
  public int getMesSeleccionado() /*0..11*/
    { return mesSeleccionado; }
  public int getDiaSeleccionado() /*1..31*/
    { return diaSeleccionado; }
  
  public void generateCurrentData()
  {
    Calendar rightNow = Calendar.getInstance();
    anoSeleccionado = rightNow.get(Calendar.YEAR);
    mesSeleccionado = rightNow.get(Calendar.MONTH); /*0..11*/
    diaSeleccionado = rightNow.get(Calendar.DAY_OF_MONTH);
  }

  public void generateAno()
  {
    ano = new JSpinnerAno( anoSeleccionado );

    ano.addChangeListener
		(
			new ChangeListener()
			{
				public void stateChanged(ChangeEvent e)
				{
          updateStateAno();
					geraDias();
				}
			}
		);
  }

  public void generateMes()
  {  
		mes = new JComboBoxMes(mesSeleccionado);
		mes.addActionListener(this);
  }

  public void geraDias()
  {
    dia.geraDias(mesSeleccionado, anoSeleccionado);
  }
	
	public void actualizaJTextField()
	{
		String dt = "" + diaSeleccionado + "-" 
					+ mes.getMesSeleccionadoString().substring(0, 3) + "-"
					+ anoSeleccionado;
		data.setText(dt);
	}
	public void setDiaSeleccionado(int d)
  {
    dia.setDiaSeleccionado(d);
    diaSeleccionado = d;
  }
  public void updateStateMes()
	{
    mesSeleccionado = mes.getMesSeleccionado();
  }
  public void updateStateAno()
	{
		anoSeleccionado = ((SpinnerNumberModel)ano.getModel()).getNumber().intValue();
  }
	public void updateState()
	{
    updateStateMes();
    updateStateAno();
    diaSeleccionado = dia.getDiaSeleccionado();
	}
	
  public void actionPerformed(ActionEvent e)
  {
    int dias = 0;
    if (e.getSource() == calendario)
      new Calendario();
		else if( e.getSource() == aplicar)
    {
      updateState();
      actualizaJTextField();
      dispose();
    }
    else if( e.getSource() == cancelar)
      dispose();
    else if ( e.getSource() == mes)
    {
      updateStateMes();
			geraDias();
    }
  }
}

