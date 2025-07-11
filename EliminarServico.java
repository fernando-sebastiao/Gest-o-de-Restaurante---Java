/*------------------------------------
Tema: Gestão de uma Barbearia
Nome: Enio Manuel
Numero: 2817
Ficheiro: EliminarServico.java
Data: 11.07.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class EliminarServico extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;
    
    public EliminarServico()
    {
        super("Pesquisas do Servico para Eliminar");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel implements ActionListener
    {

        private JTextField  duracaoMinutosJTF, nomeServicoJTF;
        private JRadioButton pesquisarPorDuracao, pesquisarPorNomeDoServico;
        private ButtonGroup grupo;
    
        public PainelCentro()
        {
            setLayout(new GridLayout(3 , 2));
            
            grupo = new ButtonGroup();

            add(pesquisarPorDuracao = new JRadioButton("Pesquisa Por Duracao de Tempo"));
            add(pesquisarPorNomeDoServico = new JRadioButton("Pesquisa Por Nome do Servico"));

            grupo.add(pesquisarPorDuracao);
            grupo.add(pesquisarPorNomeDoServico);
            
            add(new JLabel("Digite o tempo de Minutos Procurado"));
            add(duracaoMinutosJTF = new JTextField());
            duracaoMinutosJTF.setEnabled(false);
            
            add(new JLabel("Digite o Nome do Servico Procurado"));
            add(nomeServicoJTF = new JTextField());
            nomeServicoJTF.setEnabled(false);
            
            pesquisarPorDuracao.addActionListener(this);
            pesquisarPorNomeDoServico.addActionListener(this);
        }

        public int getDuracaoProcurada() 
        {
            return Integer.parseInt(duracaoMinutosJTF.getText().trim());
        }

        public String getNomeServicoProcurado()
        {
            return nomeServicoJTF.getText().trim();
        }

        public int getTipoPesquisa()
        {
            if(pesquisarPorDuracao.isSelected())
                return 1;
            else 
                return 2;
        }

        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == pesquisarPorDuracao)
            {
                duracaoMinutosJTF.setEnabled(true);
                nomeServicoJTF.setEnabled(false);
            }
            else if(event.getSource() == pesquisarPorNomeDoServico)
            {
                duracaoMinutosJTF.setEnabled(false);
                nomeServicoJTF.setEnabled(true);
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
            ServicoModelo modelo;
            if(event.getSource() == pesquisarJB)
            {    
                if(centro.getTipoPesquisa() == 1)
                {
                    modelo = ServicoFile.getPesquisaPorDuracao(centro.getDuracaoProcurada());
                    
                    JOptionPane.showMessageDialog(null, modelo.toString());

                    int opcao = JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja eliminar esse dado?");

                    if(opcao == JOptionPane.YES_OPTION)
                    {
                        // eliminar dados
                        modelo.setStatus(false);

                        new ServicoFile().eliminarDados(modelo);
                        dispose();
                    }
                    else    
                        JOptionPane.showMessageDialog(null, "Operacao Interrompida por ordem do operador");
                }
                else if(centro.getTipoPesquisa() == 2)
                {    
                    modelo = ServicoFile.getNomeServicoProcurado(centro.getNomeServicoProcurado());
                    
                    int opcao = JOptionPane.showConfirmDialog(null, "Tem a certeza que deseja eliminar esse dado?");

                    if(opcao == JOptionPane.YES_OPTION)
                    {
                        // eliminar dados
                        modelo.setStatus(false);

                        new ServicoFile().eliminarDados(modelo);
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
