/*------------------------------------
Tema: Gestão de um Restaurante
Nome: Fernando Afonso Sebastiao
Numero: 34422
Ficheiro: ApresentacaoVisao.java
Data: 11.07.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;
import SwingComponents.*;
import Calendario.*;

public class ApresentacaoVisao extends JFrame 
{
    
    private PainelCentro centro;
    private PainelSul sul;

    public ApresentacaoVisao()
    {
        super("Tela de Boas Vindas");

        JPanel painelNorte = new JPanel();
        JLabel lbImagem = new JLabel(new ImageIcon("image/descarregar (1).jpg"));
        painelNorte.add(lbImagem);
        getContentPane().add(painelNorte , BorderLayout.NORTH);
        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);
        
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel implements ActionListener
    {
            JTextArea areaPrincipal;
            JCheckBox concordarJCB;
            public PainelCentro()
            {
                setLayout(new GridLayout(2, 1));
                
                add(new JScrollPane(areaPrincipal = new JTextArea(80 , 60)));
                areaPrincipal.setFocusable(false);
                areaPrincipal.setText("Bem Vindo ao Sistema de Gestao da Barbearia.\n" +
                "\tTema: Gestao de Barbearia\n" +
                "Este projeto tem o objetivo de gerir informacoes relacionadas ao atendimento de clientes\n" +
                "permitindo o cadastro de clientes, marcacao de horarios, controle de pagamentos ." +
                "Este projeto foi desenvolvido no ambito da cadeira de Fundamentos de Programacao 2,\n" +
                "no Curso de Engenharia Informatica na UCAN. E de uso exclusivo aos Recursos Humanos.\n" +
                "Este projeto foi desenvolvido para facilitar o controlo e gestão da informação sobre os clientes da barbearia,\n" +
                "permitindo localizar os dados de forma concisa e segura.\n" +
                "Este projeto foi desenvolvido por Enio Manuel, estudante do 1º ano, ID: 2817.\n" +
                "Se concorda com os termos e condições clique em Concordar para Continuar");


                add(concordarJCB = new JCheckBox("Concordo com os termos a seguir"));

                concordarJCB.addActionListener(this);
            }

            public void actionPerformed(ActionEvent event)
            {
                if(event.getSource() == concordarJCB)
                    if(concordarJCB.isSelected())
                        sul.ativarBotao(true);
                    else
                        sul.ativarBotao(false);
            }
    }

    
    class PainelSul extends JPanel implements ActionListener
    {
        JButton entrarJB, sairJB;
        public PainelSul()
        {
            add(entrarJB = new JButton("Entrar", new ImageIcon("image/next24.png")));
            add(sairJB = new JButton("Sair", new ImageIcon("image/logout24.png")));

            ativarBotao( false );

            entrarJB.addActionListener(this);
            sairJB.addActionListener(this);
        }

        public void ativarBotao(boolean status)
        {
            entrarJB.setEnabled(status);
        }

        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == entrarJB)
            {
                dispose();
                new LoginVisao();
            }
            else
                dispose();
        }
    }

    public static void main(String[] args)
    {
        Vector_Tabelas.inic();
        new ApresentacaoVisao();       
    }
}