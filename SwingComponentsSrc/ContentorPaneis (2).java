package SwingComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ContentorPaneis extends JFrame
{
  private static String names[] = { "Next", "Back", "Save", "Clear" };
  private static int NEXT = 0, BACK = NEXT + 1, SAVE = BACK + 1, CLEAR = SAVE + 1;

  private JTabbedPane contentor;
  private JButton botoes[];
  
  public ContentorPaneis()
  {
    contentor = new JTabbedPane();
    Container c = getContentPane();
    c.add(contentor, BorderLayout.CENTER);
    botoes = UInterfaceBox.createJButtons(names);
    botoes[NEXT].setEnabled(false);
    botoes[BACK].setEnabled(false);
    JPanel botoesPainel = new JPanel();
    botoesPainel.setLayout(new FlowLayout());
    for (int i = 0; i < names.length; i++)
    {
          botoesPainel.add(botoes[i]);
          botoes[i].addActionListener(
            new ActionListener()
            {
              public void actionPerformed(ActionEvent ev)
              {
                int activePanel = contentor.getSelectedIndex();
                int nt = contentor.getTabCount();

                if (ev.getSource() ==  botoes[NEXT])
                {
                  int proximo = (activePanel + 1) % nt;
                  contentor.setSelectedIndex(proximo);
                  activePanel = proximo;
                }
                else if (ev.getSource() ==  botoes[BACK])
                {
                  int previo = activePanel == 0 ? nt - 1 : activePanel - 1;
                  contentor.setSelectedIndex(previo);
                  activePanel = previo;
                }
                /*
                else if (ev.getSource() ==  botoes[SAVE])
                {
                  SaveClearInterface p = (SaveClearInterface) contentor.getSelectedComponent();
                  p.save();
                }
                */
                else if (ev.getSource() ==  botoes[CLEAR])
                {
                  SaveClearInterface p = (SaveClearInterface) contentor.getSelectedComponent();
                  p.clear();
                }
              }
            }
          );
    }
    c.add(botoesPainel, BorderLayout.SOUTH);
  }
  public void enableButtons()
  {
    botoes[NEXT].setEnabled(true);
    botoes[BACK].setEnabled(true);
  }
  public void add(SubFormSaveClearInterface p, String st)
  {
    enableButtons();
   // tabbedPane.addTab( "Tab One", null, panel1, "First Panel" );
    contentor.addTab( st, null, p, null );
  }
  public static void main(String args[])
  {
    ContentorPaneis p = new ContentorPaneis();
    p.setSize(800, 600);
    p.setVisible(true);
    p.show();
  }
}