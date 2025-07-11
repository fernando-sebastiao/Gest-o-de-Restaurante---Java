package SwingComponents;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class Tabela3_2 extends Tabela2
{
  private int codPai;

  public Tabela3_2()
  {
    codPai = 0;
  }

  public Tabela3_2(int CodProv, String designacao)
  {
  	super(designacao);
    codPai = CodProv;
  }

  public void set_Codigo_Pai( int codPai )
  {  this.codPai = codPai; }

  public int get_Codigo_Pai()
  {   return codPai;  }

  public String toString()
  {
      {  return  "(" + codPai + ", " + codigo + ", " + designacao + ")";  }
  }

/*
  public void Write(ObjectOutputStream str) throws IOException
  {
    str.writeInt(codPai);
    str.flush();
    str.writeInt(codigo);
    str.flush();
    StrStream.Write_String(str, designacao);
  }

  public void Read(ObjectInputStream str) throws IOException
  {
    codPai = str.readInt();
    codigo = str.readInt();
    designacao = StrStream.Read_String( str );
  }
*/

  public int comparateTo( Object o )
  {   
  	int cp = codPai - ((Tabela3_2)o).codPai;
  	return cp == 0 ? super.comparateTo(o) : cp;
  }
  
  public static void editarNovosItems(String fileFather, String fileSun, 
  					String labelFather, String labelSun, String NovaDesignacao)
  {
	new Editar_Tabela3_2(fileFather, fileSun, labelFather, labelSun, NovaDesignacao);	
  }
  

  private static class Editar_Tabela3_2 extends JFrame
  {
  private JLabel label;
  private JTextField field;
  private JPanel painelEsquerdo, painelCentro, painelDireito;
  private JButton BtTasks[];
  private static final String btNames[] = {"Salvar", "Cancelar", "Ok"};
  private static final int SAVE = 0, CANCELAR = 1, OK = 2;
  private ObjectOutputStream output;
  protected String fileFather, fileSun;
  protected JComboBoxTabela2_Tabela3 cb;
  //private Contentor_Tabela3_2 contentor;

  public Editar_Tabela3_2(String fileFather, String fileSun, 
  					String labelFather, String labelSun, String NovaDesignacao)
   {
    super("Editar " + NovaDesignacao);
               
    this.fileFather = fileFather;
  	this.fileSun = fileSun;
  	
    Container container = getContentPane();
	container.setLayout(new GridLayout(1,3)); 
	
	// construcao do Painel esquerdo
	//painelEsquerdo = new JPanel();
	//painelEsquerdo.setLayout(new FlowLayout());
	Box box = Box.createVerticalBox();
	box.add(new JLabel(labelFather));
	cb = new JComboBoxTabela2_Tabela3(fileFather, fileSun);
	//cb.setEventManipulators();
	box.add(cb.getComboBoxFather());
	
	box.add(new JLabel(labelSun));
	box.add(cb.getComboBoxSun());
		
	container.add(box);
	
	// construcao do Painel central
	painelCentro = new JPanel();
	painelCentro.setLayout(new FlowLayout());
	//box = Box.createVerticalBox();
	label = new JLabel(NovaDesignacao);
    label.setHorizontalAlignment(SwingConstants.CENTER);
    painelCentro.add(label);
    field = new JTextField(15);
	painelCentro.add(field);
	container.add(painelCentro);
	
 	// construcao do Painel direito
	painelDireito = new JPanel();
	painelDireito.setLayout(new GridLayout(3,1));
	BtTasks = UInterfaceBox.createJButtons(btNames);
	//Box box = Box.createVerticalBox();
	for (int i = 0; i < BtTasks.length; i++)
		painelDireito.add(BtTasks[i]);
	container.add(painelDireito);
	

    BtTasks[SAVE].addActionListener(
          new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
            {

              Save_Data();

            }
          }
        );

    BtTasks[CANCELAR].addActionListener(
          new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
            {
              Clear_Field();
            }
          }
        );

    BtTasks[OK].addActionListener(
          new ActionListener()
          {
            public void actionPerformed( ActionEvent e )
            {
              dispose();
            }
          }
        );

     addWindowListener(
         new WindowAdapter()
         {
            public void windowClosing( WindowEvent e )
            {
               if ( output != null )
               {
                  dispose();
               }
               else
                 dispose();
            }
         }
         );

        setSize(900,150);
        this.setResizable(false);
        show();
     }

     public void Save_Data()
      {
         Tabela3_2 record;
         String designacao = get_Field_Value();
         Contentor_Tabela3_2 contentor =  Contentor_Tabela3_2.createStream3_2(fileSun);
         //Se o campo provincia não for nulo e a provincia nao existir ainda
        if ( !designacao.equals("") && contentor.Search(designacao) < 0 )
          {
            record = (Tabela3_2)contentor.createNewTabela3_2(cb.getCodigoPaiSeleccionado(), designacao);
 JOptionPane.showMessageDialog(null, "record = " + record.toString());
            contentor.add(record);
            contentor.Gerar_Proximo_Codigo();
            contentor.Actualizar_Ficheiro();
            Clear_Field();
            cb.getComboBoxSun().insert(record.get_Designacao());
          }
        else
           JOptionPane.showMessageDialog(null,
            "Edite o nome da objecto ou \n" +
            "Verifique se o nome do objecto" +
            " ja existe", "Error", JOptionPane.ERROR_MESSAGE);
  	   }

  	public String get_Field_Value()
  	 {   return field.getText();  }

  	public void Clear_Field()
  	 {   field.setText("");  }
  }

}

