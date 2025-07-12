/*------------------------------------
Tema: Gestão de uma Barbearia
Nome: Enio Manuel
Numero: 2817
Ficheiro:EditarAgendamento.java
Data: 12.07.2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class EditarAgendamento extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;
    
    public EditarAgendamento()
    {
        super("Pesquisas do Agendamento para Edicao");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel implements ActionListener
    {

        private JTextField  idJTF, dataJTF;
        private JRadioButton pesquisarPorId, pesquisarPorData;
        private ButtonGroup grupo;
    
        public PainelCentro()
        {
            setLayout(new GridLayout(3 , 2));
            
            grupo = new ButtonGroup();

            add(pesquisarPorId = new JRadioButton("Pesquisa Por Id"));
            add(pesquisarPorData = new JRadioButton("Pesquisa Por Data"));

            grupo.add(pesquisarPorId);
            grupo.add(pesquisarPorData);
            
            add(new JLabel("Digite o Id Procurado"));
            add(idJTF = new JTextField());
            idJTF.setEnabled(false);
            
            add(new JLabel("Digite a Data Procurada"));
            add(dataJTF = new JTextField());
            dataJTF.setEnabled(false);
            
            pesquisarPorId.addActionListener(this);
            pesquisarPorData.addActionListener(this);
        }

        public int getIdProcurado() 
        {
            return Integer.parseInt(idJTF.getText().trim());
        }

        public String getDataProcurada()
        {
            return dataJTF.getText().trim();
        }

        public int getTipoPesquisa()
        {
            if(pesquisarPorId.isSelected())
                return 1;
            else 
                return 2;
        }

        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == pesquisarPorId)
            {
                idJTF.setEnabled(true);
                dataJTF.setEnabled(false);
            }
            else if(event.getSource() == pesquisarPorData)
            {
                idJTF.setEnabled(false);
                dataJTF.setEnabled(true);
            }
        }
    }

    class PainelSul extends JPanel implements ActionListener
    {
        private JButton pesquisarJB, cancelarJB;

        public PainelSul()
        {
            add(pesquisarJB = new JButton("Pesquisar", new ImageIcon("image/search32.PNG")));
            add(cancelarJB = new JButton("Cancelar", new ImageIcon("image/cancel24.PNG")));

            pesquisarJB.addActionListener(this);
            cancelarJB.addActionListener(this);
        }

        public void actionPerformed(ActionEvent event)
        {
            AgendamentoModelo modelo;
            if(event.getSource() == pesquisarJB)
            {    
                if(centro.getTipoPesquisa() == 1)
                {
                   modelo = AgendamentoFile.getPesquisaPorId(centro.getIdProcurado());
                   new AgendamentoVisao(true, modelo);
                }
                else if(centro.getTipoPesquisa() == 2)
                {
                    modelo = AgendamentoFile.getPesquisaPorData(centro.getDataProcurada());
                    new AgendamentoVisao(true, modelo);
                }
            }
            else 
                dispose();
        }
    }
}
