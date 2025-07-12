/*------------------------------------
Tema: Gestão de uma Barbearia
Nome: Enio Manuel
Numero: 2817
Ficheiro: AgendamentoVisao.java
Data: 11.07.2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class AgendamentoVisao extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;
    private boolean editar;

    public AgendamentoVisao(boolean alterar, AgendamentoModelo modelo)
    {

        super("Agendamento Visao");

        editar = alterar;

        definirTema();
        if(!alterar)
        {
            	getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        }
        else
            getContentPane().add(centro = new PainelCentro(modelo), BorderLayout.CENTER);
         getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        setSize(400, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel
    {
        private JTextField idJTF, dataAgendaJTF;
        private JComboBox horaAgendamentoJCB, estadoJCB;
        private String[] arrayEstado = {"Agendado", "Concluido", "Cancelado"};
        private String[] arrayHorario = {"7:10 as 9:00", "9:30 as 10:15", "10:25 as 11:30", "11:50 as 12:40",
        "13:00 as 14:10", "14:40 as 16:00", "16:30 as 18:00"}; 
        private JTextFieldData txtData; 
        private AgendamentoFile file;

        public PainelCentro()
        {
            setLayout(new GridLayout(4, 2));
            file = new AgendamentoFile();

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setFocusable(false);

            // 2º linha
            add(new JLabel("Data a Agendar"));
            JPanel painelData = new JPanel( new GridLayout(1, 1) );
			txtData = new JTextFieldData("Data ?");
			painelData.add( txtData.getDTestField());
			painelData.add( txtData.getDButton());
			add(painelData);

            // 3º linha
            add(new JLabel("Horario"));
            add(horaAgendamentoJCB = new JComboBox(arrayHorario));

            // 4º linha
            add(new JLabel("Estado do Agendamento"));
            add(estadoJCB = new JComboBox(arrayEstado));
        }

        // construtor com parametros
        public PainelCentro(AgendamentoModelo modelo)
        {
            setLayout(new GridLayout(4, 2));
            file = new AgendamentoFile();

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setText("" +modelo.getId());
            idJTF.setFocusable(false);

            // 2º linha
            add(new JLabel("Data a Agendar"));
            JPanel painelData = new JPanel( new GridLayout(1, 1) );
			txtData = new JTextFieldData("Data ?");
			painelData.add( txtData.getDTestField());
			painelData.add( txtData.getDButton());
			add(painelData);
            txtData.getDTestField().setText(modelo.getDataAgenda());

            // 3º linha
            add(new JLabel("Horario"));
            add(horaAgendamentoJCB = new JComboBox(arrayHorario));
            horaAgendamentoJCB.setSelectedItem(modelo.getHorario());

            // 4º linha
            add(new JLabel("Estado do Agendamento"));
            add(estadoJCB = new JComboBox(arrayEstado));
            estadoJCB.setSelectedItem(modelo.getEstado());
        }
 
        // getters
        public int getId()
        {
            return Integer.parseInt(idJTF.getText().trim());
        }

        public String getDataAgenda()
        {
            return txtData.getDTestField().getText();
        }

        public String getHorario()
        {
            return String.valueOf(horaAgendamentoJCB.getSelectedItem());
        }
        
        public String getEstado()
        {
            return String.valueOf(estadoJCB.getSelectedItem());
        }

        // metodos setters
        public void setId(int id)
        {
            idJTF.setText("" +id);
        }

        public void setDataAgenda(String data)
        {
            txtData.getDTestField().setText(data);
        }

        public void setHorario(String horario)
        {
           horaAgendamentoJCB.setSelectedItem(horario);
        }

        public void setEstado(String estado)
        {
            estadoJCB.setSelectedItem(estado);
        }
    
        // metodo salvar
        public void salvar()
        {
            AgendamentoModelo modelo = new AgendamentoModelo(
            getId(),
            getDataAgenda(),
            getHorario(),
            getEstado(),
            true);

            JOptionPane.showMessageDialog(null, modelo.toString());
            modelo.salvar();
            dispose();
        }

        // alterar
        public void alterar()
        {
            AgendamentoModelo modelo = new AgendamentoModelo(
            getId(),
            getDataAgenda(),
            getHorario(),
            getEstado(),
            true);

            JOptionPane.showMessageDialog(null, modelo.toString());
            modelo.salvarDados();
            dispose();
        }
    }

    class PainelSul extends JPanel implements ActionListener
    {
        private JButton salvarJBT, cancelarJBT;
        
        public PainelSul()
        {
           setLayout(new FlowLayout());

            add(salvarJBT = new JButton("Salvar", new ImageIcon("image/save24.png")));
            add(cancelarJBT = new JButton("Cancelar", new ImageIcon("image/cancel24.png")));

            salvarJBT.setBackground(Color.GREEN);
            cancelarJBT.setBackground(Color.RED);

            salvarJBT.setForeground(Color.WHITE);
            cancelarJBT.setForeground(Color.WHITE);
            
            salvarJBT.addActionListener(this);
            cancelarJBT.addActionListener(this);
        }

        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == salvarJBT)
            {
                if(editar)
                    centro.alterar();
                else    
                    centro.salvar();
            }
            else
                dispose();
        }
    }

    public void definirTema() 
    {
        try 
        {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
                {
                    if ("Nimbus".equals(info.getName())) 
                    {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) 
        {
        }
    }

    public static void main(String[] args)
    {
        Vector_Tabelas.inic();
        new AgendamentoVisao(false, new AgendamentoModelo());
    }
}