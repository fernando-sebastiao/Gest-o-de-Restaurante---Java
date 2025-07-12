/*------------------------------------
Tema: Gestão de uma Barbearia
Nome: Enio Manuel
Numero: 2817
Ficheiro: PagamentoVisao.java
Data: 11.07.2025
--------------------------------------*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;
import java.io.*;

public class PagamentoVisao extends JFrame
{
    private PainelCentro centro;
    private PainelSul sul;
    private boolean editar;

    public PagamentoVisao(boolean alterar, PagamentoModelo modelo)
    {

        super("Cadastro dos Pagamentos");

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
        private JTextField idJTF, valorJTF, dataPagamentoJTF;
        private JComboBox metodoPagamentoJCB;
        private String[] arrayMetodo = {"Dinheiro", "Cartao", "Transferencia"}; 
        private JTextFieldData txtData; 
        private PagamentoFile file;

        public PainelCentro()
        {
            setLayout(new GridLayout(4, 2));
            file = new PagamentoFile();

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setFocusable(false);

            // 2º linha
            add(new JLabel("Valor"));
            add(valorJTF = new JTextField());

            // 3º linha
            add(new JLabel("Data de Pagamento"));
            JPanel painelData = new JPanel( new GridLayout(1, 1) );
			txtData = new JTextFieldData("Data ?");
			painelData.add( txtData.getDTestField());
			painelData.add( txtData.getDButton());
			add(painelData);

            // 4º linha
            add(new JLabel("Metodo de Pagamento"));
            add(metodoPagamentoJCB = new JComboBox(arrayMetodo));
        }

        // construtor com parametros
        public PainelCentro(PagamentoModelo modelo)
        {
            setLayout(new GridLayout(4, 2));
            file = new PagamentoFile();

            // 1º linha
            add(new JLabel("Id"));
            add(idJTF = new JTextField());
            idJTF.setText("000" + file.getProximoCodigo());
            idJTF.setText("" +modelo.getId());
            idJTF.setFocusable(false);

            // 2º linha
            add(new JLabel("Valor"));
            add(valorJTF = new JTextField());
            valorJTF.setText("" +modelo.getValor());

            // 3º linha
            add(new JLabel("Data de Pagamento"));
            JPanel painelData = new JPanel( new GridLayout(1, 1) );
			txtData = new JTextFieldData("Data ?");
			painelData.add( txtData.getDTestField());
			painelData.add( txtData.getDButton());
			add(painelData);
            txtData.getDTestField().setText(modelo.getDataPagamento());

            // 4º linha
            add(new JLabel("Metodo de Pagamento"));
            add(metodoPagamentoJCB = new JComboBox(arrayMetodo));
            metodoPagamentoJCB.setSelectedItem(modelo.getMetodoPagamento());
        }
 
        // getters
        public int getId()
        {
            return Integer.parseInt(idJTF.getText().trim());
        }

        public float getValor()
        {
            return Float.parseFloat(valorJTF.getText().trim());
        }

        public String getDataPagamento()
        {
            return txtData.getDTestField().getText();
        }

        public String getMetodoPagamento()
        {
            return String.valueOf(metodoPagamentoJCB.getSelectedItem());
        }
        
        // metodos setters
        public void setId(int id)
        {
            idJTF.setText("" +id);
        }

        public void setValor(float valor)
        {
            valorJTF.setText("" + valor);
        }

        public void setDataPagamento(String data)
        {
            txtData.getDTestField().setText(data);
        }

        public void setMetodoPagamento(String metodoPagamento)
        {
           metodoPagamentoJCB.setSelectedItem(metodoPagamento);
        }
        
        // metodo salvar
        public void salvar()
        {
            PagamentoModelo modelo = new PagamentoModelo(
            getId(),
            getValor(),
            getDataPagamento(),
            getMetodoPagamento(),
            true);

            JOptionPane.showMessageDialog(null, modelo.toString());
            modelo.salvar();
            dispose();
        }

        // alterar
        public void alterar()
        {
            PagamentoModelo modelo = new PagamentoModelo(
            getId(),
            getValor(),
            getDataPagamento(),
            getMetodoPagamento(),
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
        new PagamentoVisao(false, new PagamentoModelo());
    }
}