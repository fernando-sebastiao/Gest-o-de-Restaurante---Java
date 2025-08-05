/*------------------------------------
Tema: Gestão de um Video Clube
Nome: Adolfo Cabeia
Numero: 31671
Ficheiro: DefesaVisao.java
Data: 26.06.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.UIManager.*;
import SwingComponents.*;
import Calendario.*;

public class DefesaVisao extends JFrame {
    private PainelCentro centro;
    private PainelSul sul;
    private boolean editar;

    public DefesaVisao(boolean alterar, DefesaModelo modelo) {
        super("Cadastro de Novo Cliente");

        editar = alterar;
        definirTema();

        JPanel painelNorte = new JPanel();
        /* JLabel lbImagem = new JLabel(new ImageIcon("image/topo.jpg")); */
        /* painelNorte.add(lbImagem); */
        getContentPane().add(painelNorte, BorderLayout.NORTH);

        if (!alterar)
            getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        else
            getContentPane().add(centro = new PainelCentro(modelo), BorderLayout.CENTER);

        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel {
        private JTextField idJTF, telefoneJTF, enderecoJTF, biJTF;
        private JComboBoxPersonal nomeJCB, generoJCB;
        private JComboBoxPersonal provinciasJCB, municipiosJCB, comunasJCB, dioceseJCB, paroquiaJCB, dataFundacaoJCB;
		private JComboBoxTabela3_Tabela3 provMunCom, dioParoData;
        private JTextFieldData txtData;
        private DefesaFile DefesaFile;

        public PainelCentro() {
            setLayout(new GridLayout(13, 4, 10, 5));
            provMunCom = new JComboBoxTabela3_Tabela3("Provincia.tab", "Municipio.tab", "Comuna.tab");
            dioParoData = new JComboBoxTabela3_Tabela3("Diocese.tab", "Paroquia.tab", "DataFundacao.tab");
            DefesaFile = new DefesaFile();

            add(new JLabel("ID:"));
            add(idJTF = new JTextField("000" + DefesaFile.getProximoCodigo()));
            idJTF.setFocusable(false);

            add(new JLabel("Nome:"));
            add(nomeJCB = UInterfaceBox.createJComboBoxsTabela2("NomeCliente.tab"));

            add(new JLabel("Gênero:"));
            add(generoJCB = UInterfaceBox.createJComboBoxsTabela2("GeneroCliente.tab"));

            add(new JLabel("Endereço:"));
            add(enderecoJTF = new JTextField());

            add( new JLabel("Provincia") );
			add( provinciasJCB = provMunCom.getComboBoxFather() );
			add( new JLabel("Municipio") );
			add( municipiosJCB = provMunCom.getComboBoxSun() );
			
			add( new JLabel("Comuna") );
			add( comunasJCB = provMunCom.getComboBoxNeto() );

            add(new JLabel("Telefone:"));
            add(telefoneJTF = new JTextField());

            add(new JLabel("Bilhete de Identidade:"));
            add(biJTF = new JTextField());

            add(new JLabel("Diocese"));
            add(dioceseJCB = dioParoData.getComboBoxFather());

            add(new JLabel("Paróquia"));
            add(paroquiaJCB = dioParoData.getComboBoxSun());

            add(new JLabel("Data da Fundação"));
            add(dataFundacaoJCB = dioParoData.getComboBoxNeto());

            add(new JLabel("Data de Nascimento:"));
            JPanel painelData = new JPanel(new GridLayout(1, 1));
            txtData = new JTextFieldData("Data?");
            painelData.add(txtData.getDTestField());
            painelData.add(txtData.getDButton());
            add(painelData);
        }

        public PainelCentro(DefesaModelo modelo) {
            this();
            idJTF.setText("000" + modelo.getId());
            nomeJCB.setSelectedItem(modelo.getNome());
            generoJCB.setSelectedItem(modelo.getGenero());
            provinciasJCB.setSelectedItem(modelo.getProvincia());
            provinciasJCB.setSelectedItem(modelo.getProvincia());
            comunasJCB.setSelectedItem(modelo.getComuna());
            enderecoJTF.setText(modelo.getEndereco());
            telefoneJTF.setText(String.valueOf(modelo.getTelefone()));
            dioceseJCB.setSelectedItem(modelo.getDiocese());
            paroquiaJCB.setSelectedItem(modelo.getParoquia());
            dataFundacaoJCB.setSelectedItem(modelo.getDataFundacao());
            biJTF.setText(modelo.getBilheteIdentidade());
            txtData.getDTestField().setText(modelo.getDataNascimento());
        }

        public int getId() { return Integer.parseInt(idJTF.getText().trim()); }
        public String getNome() { return String.valueOf(nomeJCB.getSelectedItem()); }
        public String getGenero() { return String.valueOf(generoJCB.getSelectedItem()); }
        public String getEndereco() { return enderecoJTF.getText().trim(); }
        public String getProvincia()
		{
			return String.valueOf( provinciasJCB.getSelectedItem() );
		}
		public String getMunicipio()
		{
			return String.valueOf(municipiosJCB.getSelectedItem() );
		}
		public String getComuna()
		{
			return String.valueOf( comunasJCB.getSelectedItem() );
		}

        public String getTelefone() { return telefoneJTF.getText().trim(); }
         public String getDiocese()
		{
			return String.valueOf( dioceseJCB.getSelectedItem() );
		}

        public String getParoquia()
		{
			return String.valueOf( paroquiaJCB.getSelectedItem() );
		}

        public String getDataFundacao()
		{
			return String.valueOf( dataFundacaoJCB.getSelectedItem() );
		}
        public String getBI() { return biJTF.getText().trim(); }
        public String getDataNascimento() { return txtData.getDTestField().getText(); }

        public void salvar() {
            DefesaModelo modelo = new DefesaModelo(getId(), getNome(), getGenero(), getEndereco(), getProvincia(), getMunicipio(), getComuna(),getBI(), getDataNascimento(), Integer.parseInt(getTelefone()), getDiocese(), getParoquia(), getDataFundacao());
            JOptionPane.showMessageDialog(null, modelo.toString());
            modelo.salvar();
            dispose();
        }
    }

    class PainelSul extends JPanel implements ActionListener {
        private JButton btnSalvar, btnCancelar;

        public PainelSul() {
            btnSalvar = new JButton("Salvar", new ImageIcon("image/save24.png"));
            btnCancelar = new JButton("Cancelar", new ImageIcon("image/cancel24.png"));

            add(btnSalvar);
            add(btnCancelar);

            btnSalvar.addActionListener(this);
            btnCancelar.addActionListener(this);
        }

        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == btnSalvar)
                centro.salvar();
            else
                dispose();
        }
    }

    public void definirTema() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {}
    }
}

