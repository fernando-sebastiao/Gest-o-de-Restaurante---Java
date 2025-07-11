/*------------------------------------
Tema: Gestão de uma Barbearia
Nome: Enio Manuel
Numero: 2817
Ficheiro: ServicoVisao.java
Data: 11.07.2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class ServicoVisao extends JFrame 
{
    private PainelCentro centro;
    private PainelSul sul;
    private boolean editar;

    public ServicoVisao(boolean alterar, ServicoModelo modelo)
    {
        super("Servico Visao");
    
        editar = alterar;

        definirTema();
        if(!alterar)
        {
            	getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        }
        else
            getContentPane().add(centro = new PainelCentro(modelo), BorderLayout.CENTER);
         getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        setSize(400, 300);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    class PainelCentro extends JPanel
    {
        private JTextField idJTF, nomeServicoJTF, precoJTF, duracaoMinutosJTF, descricaoJTF;
        private ServicoFile file;

        public PainelCentro()
        {
            setLayout(new GridLayout(5, 2));
            file = new ServicoFile();

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setFocusable(false);

            // 2º linha
            add(new JLabel("Nome do Servico"));
            add(nomeServicoJTF = new JTextField());

            // 3º linha 
            add(new JLabel("Preco"));
            add(precoJTF = new JTextField());

            // 4º linha
            add(new JLabel("Duracao em Minutos"));
            add(duracaoMinutosJTF = new JTextField());

            // 5º linha
            add(new JLabel("Descricao"));
            add(descricaoJTF = new JTextField());
        }

        public PainelCentro(ServicoModelo modelo)
        {
            setLayout(new GridLayout(5, 2));
            file = new ServicoFile();

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setText("" + modelo.getId());
            idJTF.setFocusable(false);

            // 2º linha
            add(new JLabel("Nome do Servico"));
            add(nomeServicoJTF = new JTextField());
            nomeServicoJTF.setText(modelo.getNomeServico());

            // 3º linha
            add(new JLabel("Preco"));
            add(precoJTF = new JTextField());
            precoJTF.setText("" + modelo.getPreco());

            // 4º linha 
            add(new JLabel("Duracao em Minutos"));
            add(duracaoMinutosJTF = new JTextField());
            duracaoMinutosJTF.setText("" + modelo.getDuracao());

            // 5º linha
            add(new JLabel("Descricao"));
            add(descricaoJTF = new JTextField());
            descricaoJTF.setText(modelo.getDescricao());
        }

        // metodos getters
        public int getId()
        {
            return Integer.parseInt(idJTF.getText().trim());
        }

        public String getNomeServico()
        {
            return nomeServicoJTF.getText().trim();
        }

        public float getPreco()
        {
            return Float.parseFloat(precoJTF.getText().trim());
        }

        public int getDuracao()
        {
            return Integer.parseInt(duracaoMinutosJTF.getText().trim());
        }

        public String getDescricao()
        {
            return descricaoJTF.getText().trim();
        }

        // metodos setters
        public void setId(int id)
        {
            idJTF.setText("" + id);
        }

        public void setNomeServico(String nomeServico)
        {
            nomeServicoJTF.setText(nomeServico);
        }

        public void setPreco(float preco)
        {
            precoJTF.setText("" + preco);
        }

        public void setDuracao(int duracao)
        {
            duracaoMinutosJTF.setText("" + duracao);
        }

        public void setDescricao(String descricao)
        {
            descricaoJTF.setText(descricao);
        }

        // metodo salvar
        public void salvar()
        {
            ServicoModelo modelo = new ServicoModelo(
            getId(),
            getNomeServico(),
            getPreco(), 
            getDuracao(),
            getDescricao(),
            true);

            JOptionPane.showMessageDialog(null, modelo.toString());
            modelo.salvar();
        }

        // metodo alterar
        public void alterar()
        {
            ServicoModelo modelo = new ServicoModelo(
            getId(),
            getNomeServico(),
            getPreco(), 
            getDuracao(),
            getDescricao(),
            true);

            JOptionPane.showMessageDialog(null, modelo.toString());
            modelo.salvarDados();
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
        new ServicoVisao(false, new ServicoModelo());
    }
}