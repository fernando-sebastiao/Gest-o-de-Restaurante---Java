/*------------------------------------
Tema: Gestão de um Restaurante
Nome: Fernando Afonso Sebastiao
Numero: 34422
Ficheiro: MenuPrincipal.java
Data: 10.07.2025
--------------------------------------*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class LoginVisao extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;

    public LoginVisao()
    {
        super("Tela de Login");

        JPanel painelNorte = new JPanel();

        ImageIcon iconOriginal = new ImageIcon("image/restaurante.png");
        Image imagemRedimensionada = iconOriginal.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        ImageIcon iconRedimensionado = new ImageIcon(imagemRedimensionada);

        JLabel lbImagem = new JLabel(iconRedimensionado);

        painelNorte.add(lbImagem);
        getContentPane().add(painelNorte, BorderLayout.NORTH);

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);
        
        //pack();
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel 
    {
        private JTextField userJTF;
        private JPasswordField passwordJTF;
        private String correctUser = "34422", correctPassword = "ucan";

        public PainelCentro()
        {
            setLayout(new GridLayout(2,2));
            add(new JLabel("Username: "));
            add(userJTF = new JTextField());

            add(new JLabel("Password: "));
            add(passwordJTF = new JPasswordField());

        }

        public String getUser()
        {
            return userJTF.getText().trim();
        }

        public String getPassword()
        {
            return passwordJTF.getText().trim();
        }

        public boolean loginValido()
        {
            if(getUser().equals(correctUser) && getPassword().equals(correctPassword))
                return true;

            return false;
        }

        public void realizarLogin()
        {
            if (loginValido()) {
                String user = getUser();
                dispose();
                new MenuPrincipal(user);
            } else {
                JOptionPane.showMessageDialog(null, "Login Invalido, tente novamente!",
                        "Invalid Login", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class PainelSul extends JPanel implements ActionListener
    {
        JButton entrarJB, sairJB;
        public PainelSul()
        {
            add(entrarJB = new JButton("Entrar", new ImageIcon("image/next24.png")));
            add(sairJB = new JButton("Sair", new ImageIcon("image/logout24.png")));


            entrarJB.addActionListener(this);
            sairJB.addActionListener(this);
        }

        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource() == entrarJB)
            {
                if(centro.loginValido())
                {
                    String user = centro.getUser();

                    dispose();

                    new MenuPrincipal(user);
                }
                else
                
                    JOptionPane.showMessageDialog(null, "Login Invalido, tente novamente!", 
							"Invalid Login", JOptionPane.ERROR_MESSAGE);
                
            }
            else
                dispose();
        }
    }
     public static void main(String[] args)
    {
        Vector_Tabelas.inic();
        new LoginVisao();
    }
}