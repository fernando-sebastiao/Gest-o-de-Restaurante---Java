/*------------------------------------
Tema: Gestão de um Restaurante
Nome: Fernando Afonso Sebastião
Número: 34422
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
        super("Tela de Boas-Vindas");

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
            areaPrincipal.setText(
                "Bem-vindo ao Sistema de Gestão de um Restaurante.\n\n" +
                "\tTema: Gestão de um Restaurante\n\n" +
                "Este projeto tem o objetivo de gerir informações relacionadas ao atendimento de clientes,\n" +
                "permitindo o cadastro de clientes, marcação de horários, controle de pagamentos e muito mais.\n\n" +
                "Este projeto foi desenvolvido no âmbito da cadeira de Fundamentos de Programação 2,\n" +
                "no curso de Engenharia Informática da UCAN. É de uso exclusivo aos Recursos Humanos.\n\n" +
                "Este sistema foi desenvolvido para facilitar o controlo e gestão da informação sobre os clientes do restaurante,\n" +
                "permitindo localizar os dados de forma concisa, rápida e segura.\n\n" +
                "Este projeto foi desenvolvido por Fernando Afonso Sebastião, estudante do 1º ano, ID: 34422.\n\n" +
                "Se concorda com os termos e condições, clique em 'Concordo' para continuar."
            );

            add(concordarJCB = new JCheckBox("Concordo com os termos acima"));

            concordarJCB.addActionListener(this);
        }

        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == concordarJCB)
                sul.ativarBotao(concordarJCB.isSelected());
        }
    }

    class PainelSul extends JPanel implements ActionListener
    {
        JButton entrarJB, sairJB;

        public PainelSul()
        {
            add(entrarJB = new JButton("Entrar", new ImageIcon("image/next24.png")));
            add(sairJB = new JButton("Sair", new ImageIcon("image/logout24.png")));

            ativarBotao(false);

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
                new LoginVisao(); // Chama a tela de login
            }
            else
            {
                dispose(); // Fecha a tela se clicar em sair
            }
        }
    }

    public static void main(String[] args)
    {
        Vector_Tabelas.inic(); // Inicializa estruturas se necessário
        new ApresentacaoVisao();       
    }
}
