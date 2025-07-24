/*------------------------------------
Tema: Gestão de um Restaurante
Nome: Fernando Afonso Sebastiao
Numero: 34422
Ficheiro: ClienteModelo.java
Data: 10.07.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class PesquisarCliente extends JFrame {
    PainelCentro centro;
    PainelSul sul;

    public PesquisarCliente() {
        super("Pesquisar Cliente");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel {
        JComboBox nomesJCB;

        public PainelCentro() {
            setLayout(new GridLayout(2, 2, 10, 10));

            add(new JLabel("Escolha o Nome:"));
            nomesJCB = new JComboBox(ClienteFile.getAllNomesClientes());
            add(nomesJCB);
        }

        public String getNomeProcurado() {
            return String.valueOf(nomesJCB.getSelectedItem());
        }
    }

    class PainelSul extends JPanel implements ActionListener {
        JButton pesquisarJB, cancelarJB;

        public PainelSul() {
            add(pesquisarJB = new JButton("Pesquisar", new ImageIcon("image/search32.png")));
            add(cancelarJB = new JButton("Cancelar", new ImageIcon("image/cancel24.png")));

            pesquisarJB.addActionListener(this);
            cancelarJB.addActionListener(this);
        }

        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == pesquisarJB) {
                String nome = centro.getNomeProcurado();
                ClienteModelo modelo = ClienteFile.getClientePorNome(nome);
                
                if (modelo != null && modelo.getStatus()) {
                    JOptionPane.showMessageDialog(null, modelo.toString(), "Cliente Encontrado", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado ou inativo.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                dispose();
            }
        }
    }
}
