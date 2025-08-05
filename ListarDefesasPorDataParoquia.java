/*------------------------------------
Tema: Gestão de uma Diocese
Nome: Adolfo Cabeia
Número: 31671
Ficheiro: ListarDefesasPorDataParoquia.java
Data: 04.08.2025
--------------------------------------*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.UIManager.*;
import SwingComponents.*;
import Calendario.*;

public class ListarDefesasPorDataParoquia extends JFrame {
    JTextField paroquiaTF;
    JTextFieldData campoData;
    JButton listarJB, cancelarJB;

    public ListarDefesasPorDataParoquia() {
        super("Listar Defesas por Paróquia e Data");

        setLayout(new BorderLayout());

        // Painel Centro
        JPanel centro = new JPanel(new GridLayout(2, 2, 10, 10));
        centro.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        centro.add(new JLabel("Nome da Paróquia:"));
        centro.add(paroquiaTF = new JTextField());

        centro.add(new JLabel("Data de Fundação:"));
        JPanel painelData = new JPanel(new BorderLayout());
        campoData = new JTextFieldData("Data?");
        painelData.add(campoData.getDTestField(), BorderLayout.CENTER);
        painelData.add(campoData.getDButton(), BorderLayout.EAST);
        centro.add(painelData);

        // Painel Sul
        JPanel sul = new JPanel();
        sul.add(listarJB = new JButton("Listar", new ImageIcon("icons/list32.png")));
        sul.add(cancelarJB = new JButton("Cancelar"));

        add(centro, BorderLayout.CENTER);
        add(sul, BorderLayout.SOUTH);

        listarJB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String paroquia = paroquiaTF.getText().trim();
                String data = campoData.getDTestField().getText().trim();

                if (paroquia.isEmpty() || data.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                DefesaFile.listarPorParoquiaEData(paroquia, data);
            }
        });

        cancelarJB.addActionListener(e -> dispose());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
