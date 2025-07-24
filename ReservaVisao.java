/*------------------------------------
Tema: Gestão de um Restaurante
Nome: Fernando Afonso Sebastiao
Numero: 34422
Ficheiro: ClienteModelo.java
Data: 10.07.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.UIManager.*;
import SwingComponents.*;
import Calendario.*;

public class ReservaVisao extends JFrame {
    private PainelCentro centro;
    private PainelSul sul;
    private boolean editar;

    public ReservaVisao(boolean alterar, ReservaModelo modelo) {
        super("Cadastro de Reserva");

        editar = alterar;
        definirTema();

        JPanel painelNorte = new JPanel();
        /* JLabel lbImagem = new JLabel(new ImageIcon("image/topo.jpg"));
        painelNorte.add(lbImagem); */
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
        private JTextField idJTF, numPessoasJTF, horarioJTF;
        private JComboBoxPersonal nomeClienteJCB, mesaJCB, funcionarioJCB;
        private JTextFieldData txtData;
        private ReservaFile reservaFile;

        public PainelCentro() {
            setLayout(new GridLayout(7, 2, 10, 5));
            reservaFile = new ReservaFile();

            add(new JLabel("ID:"));
            idJTF = new JTextField("000" + reservaFile.getProximoCodigo());
            idJTF.setFocusable(false);
            add(idJTF);

            add(new JLabel("Nome do Cliente:"));
            nomeClienteJCB = UInterfaceBox.createJComboBoxsTabela2("NomeCLiente.tab");
            add(nomeClienteJCB);

            add(new JLabel("Mesa:"));
            mesaJCB = UInterfaceBox.createJComboBoxsTabela2("NomeMesa.tab");
            add(mesaJCB);

            add(new JLabel("Número de Pessoas:"));
            numPessoasJTF = new JTextField();
            add(numPessoasJTF);

            add(new JLabel("Horário:"));
            horarioJTF = new JTextField();
            add(horarioJTF);

            add(new JLabel("Funcionário:"));
            funcionarioJCB = UInterfaceBox.createJComboBoxsTabela2("NomeFuncionario.tab");
            add(funcionarioJCB);

            add(new JLabel("Data da Reserva:"));
            JPanel painelData = new JPanel(new GridLayout(1, 1));
            txtData = new JTextFieldData("Data?");
            painelData.add(txtData.getDTestField());
            painelData.add(txtData.getDButton());
            add(painelData);
        }

        public PainelCentro(ReservaModelo modelo) {
            this();
            idJTF.setText("000" + modelo.getId());
            nomeClienteJCB.setSelectedItem(modelo.getNomeCliente());
            mesaJCB.setSelectedItem(modelo.getMesa());
            numPessoasJTF.setText(String.valueOf(modelo.getNumPessoas()));
            horarioJTF.setText(modelo.getHorario());
            funcionarioJCB.setSelectedItem(modelo.getFuncionario());
            txtData.getDTestField().setText(modelo.getDataReserva());
        }

        public int getId() { return Integer.parseInt(idJTF.getText().trim()); }
        public String getNomeCliente() { return String.valueOf(nomeClienteJCB.getSelectedItem()); }
        public String getMesa() { return String.valueOf(mesaJCB.getSelectedItem()); }
        public int getNumPessoas() { return Integer.parseInt(numPessoasJTF.getText().trim()); }
        public String getHorario() { return horarioJTF.getText().trim(); }
        public String getFuncionario() { return String.valueOf(funcionarioJCB.getSelectedItem()); }
        public String getDataReserva() { return txtData.getDTestField().getText(); }

        public void salvar() {
            ReservaModelo modelo = new ReservaModelo(
                getId(),
                getNumPessoas(),
                getNomeCliente(),
                getMesa(),
                getHorario(),
                getFuncionario(),
                getDataReserva(),
                true
            );
            JOptionPane.showMessageDialog(null, modelo.toString());
            modelo.salvar();
            dispose();
        }

        public void alterar() {
            ReservaModelo modelo = new ReservaModelo(
                getId(),
                getNumPessoas(),
                getNomeCliente(),
                getMesa(),
                getHorario(),
                getFuncionario(),
                getDataReserva(),
                true
            );
            JOptionPane.showMessageDialog(null, modelo.toString());
            modelo.salvarDados();
            dispose();
        }
    }

    class PainelSul extends JPanel implements ActionListener {
        private JButton btnSalvar, btnCancelar;

        public PainelSul() {
            btnSalvar = new JButton("Salvar", new ImageIcon("image/save24.png"));
            btnCancelar = new JButton("Cancelar", new ImageIcon("image/cancel24.png"));

            btnSalvar.setBackground(Color.GREEN);
            btnSalvar.setForeground(Color.WHITE);

            btnCancelar.setBackground(Color.RED);
            btnCancelar.setForeground(Color.WHITE);

            add(btnSalvar);
            add(btnCancelar);

            btnSalvar.addActionListener(this);
            btnCancelar.addActionListener(this);
        }

        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == btnSalvar)
                if (editar)
                    centro.alterar();
                else
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
