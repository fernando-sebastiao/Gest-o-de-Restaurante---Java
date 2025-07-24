/*------------------------------------
Tema: Gestão de um Restaurante
Nome: Fernando Afonso Sebastiao
Numero: 34422
Ficheiro: EliminarFuncionario.java
Data: 11.07.2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class EliminarFuncionario extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;
    
    public EliminarFuncionario()
    {
        super("Pesquisas do Funcionario para Eliminar");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel implements ActionListener
    {

        private JTextField  idJTF, especialidadeJTF;
        private JRadioButton pesquisarPorId, pesquisarPorEspecialidade;
        private ButtonGroup grupo;
    
        public PainelCentro()
        {
            setLayout(new GridLayout(3 , 2));
            
            grupo = new ButtonGroup();

            add(pesquisarPorId = new JRadioButton("Pesquisa Por Id"));
            add(pesquisarPorEspecialidade = new JRadioButton("Pesquisa Por Especialidade"));

            grupo.add(pesquisarPorId);
            grupo.add(pesquisarPorEspecialidade);
            
            add(new JLabel("Digite o Id Procurado"));
            add(idJTF = new JTextField());
            idJTF.setEnabled(false);
            
            add(new JLabel("Digite a Especialidade Procurada"));
            add(especialidadeJTF = new JTextField());
            especialidadeJTF.setEnabled(false);
            
            pesquisarPorId.addActionListener(this);
            pesquisarPorEspecialidade.addActionListener(this);
        }

        public int getIdProcurado() 
        {
            return Integer.parseInt(idJTF.getText().trim());
        }

        public String getEspecialidadeProcurada()
        {
            return especialidadeJTF.getText().trim();
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
                especialidadeJTF.setEnabled(false);
            }
            else if(event.getSource() == pesquisarPorEspecialidade)
            {
                idJTF.setEnabled(false);
                especialidadeJTF.setEnabled(true);
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
            if(event.getSource() == pesquisarJB)
            {    
                FuncionarioModelo modelo;
                if(centro.getTipoPesquisa() == 1)
                {
                    modelo = FuncionarioFile.getPesquisaPorId(centro.getIdProcurado());
                    
                    int opcao = JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja eliminar esse dado?");

                    if(opcao == JOptionPane.YES_OPTION)
                    {
                        // eliminar dados
                        modelo.setStatus(false);

                        new FuncionarioFile().eliminarDados(modelo);
                        dispose();
                    }
                    else    
                        JOptionPane.showMessageDialog(null, "Operacao Interrompida por ordem do operador");
                }
                    
                else if(centro.getTipoPesquisa() == 2)
                {    
                    modelo = FuncionarioFile.getEspecialidade(centro.getEspecialidadeProcurada());

                    int opcao = JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja eliminar esse dado?");

                    if(opcao == JOptionPane.YES_OPTION)
                    {
                        // eliminar dados
                        modelo.setStatus(false);

                        new FuncionarioFile().eliminarDados(modelo);
                        dispose();
                    }
                    else    
                        JOptionPane.showMessageDialog(null, "Operacao Interrompida por ordem do operador");
                }
            }
            else 
                dispose();
        }
    }
}
