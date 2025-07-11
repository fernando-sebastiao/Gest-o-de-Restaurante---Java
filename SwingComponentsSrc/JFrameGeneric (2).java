/************************************************************************
 file.: JFrameGeneric.java                                                  
 Autor.: Osvaldo Manuel Ramos                                         
 Data.: 17-dez-2005                                                   
 Num.: 2817                                                           
 Objectivo.: Criacao de Janela                            
 Descricao.:                                
************************************************************************/


package SwingComponents;
import javax.swing.*;

public abstract class JFrameGeneric extends JFrame implements DebugInterface
{
	protected JFrameGeneric() { }
	
	protected JFrameGeneric(String title) { super(title);}
	
	public void debug()
	{
		JOptionPane.showMessageDialog(null, toString());
	}
}
