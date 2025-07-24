package SwingComponents;
/******************************************************************************
Nome: Felisberto Pinto Delgado
N� 1735
Data: 7 de Outubro de 2003
Objetivo : 
******************************************************************************/




import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Tabela2  implements Serializable
{
  protected int codigo;
  protected String designacao;

  public Tabela2()
  {
    codigo = 0;
    designacao = New_String.generateStringFixedlength("");
  }

  public Tabela2(String Desig )
  {
    codigo = 0;
    set_Designacao( Desig );
  }

  public void set_Designacao( String Desig )
  {   designacao = New_String.generateStringFixedlength(Desig);   }

  public void set_Codigo( int Cod )
  {  codigo = Cod;   }

  public String get_Designacao()
  {   return designacao.trim();   }

  public int get_Codigo()
  {  return codigo;   }

  public String toString()
  {  return  "(" + codigo + ", " + designacao + ")";  }

/*
  public void Write( ObjectOutputStream stream )
   throws IOException
  {
    stream.writeInt( codigo );
    stream.flush();
    StrStream.Write_String( stream, designacao );
  }
  public void Read( ObjectInputStream stream )
   throws IOException
  {
    codigo = stream.readInt();
    designacao = StrStream.Read_String( stream );
  }
*/


  public int comparateTo( Object o )
  {   
  	int cp = designacao.compareToIgnoreCase(((Tabela2)o).designacao);
  	return cp == 0 ? codigo - ((Tabela2)o).codigo : cp;
  }
  
  
  public static void editarNovosItems(String file, String NovaDesignacao)
  {
	new Editar_Tabela2(file, NovaDesignacao);	
  }
  

  
  private static class Editar_Tabela2 extends JFrame
  {
  private JLabel label;
  private JTextField field;
  private JPanel painelEsquerdo, painelCentro, painelDireito;
  private JButton BtTasks[];
  private static final String btNames[] = {"Salvar", "Cancelar", "Ok"};
  private static final int SAVE = 0, CANCELAR = 1, OK = 2;
  private ObjectOutputStream output;
  private Contentor_Tabela2 contentor;
  protected JComboBoxPersonal cb;

  public Editar_Tabela2(String file, String NovaDesignacao)
   {
    super("Editar " + NovaDesignacao);
    contentor =  Contentor_Tabela2.createStream(file);
            
    Container container = getContentPane();
	container.setLayout(new GridLayout(1,3)); 
	
	// construcao do Painel esquerdo
	//painelEsquerdo = new JPanel();
	//painelEsquerdo.setLayout(new FlowLayout());
	Box box = Box.createVerticalBox();
	box.add(new JLabel("Items Existentes", SwingConstants.LEFT));
	cb = UInterfaceBox.createJComboBoxsTabela2(file);
	box.add(cb);
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

        setSize(600,100);
        this.setResizable(false);
        show();
     }

     public void Save_Data()
      {
         Tabela2 record;
         String designacao = get_Field_Value();
         //Se o campo provincia n�o for nulo e a provincia nao existir ainda
        if ( !designacao.equals("") && contentor.Search(designacao) < 0 )
          {
            record = (Tabela2)contentor.createNewTabela(designacao);
            contentor.add(record);
 //JOptionPane.showMessageDialog(null, "record = " + record.toString());
            contentor.Gerar_Proximo_Codigo();
            contentor.Actualizar_Ficheiro();
            Clear_Field();
            cb.insert(record.get_Designacao());
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