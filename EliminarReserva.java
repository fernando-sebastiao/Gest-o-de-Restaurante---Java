import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import SwingComponents.*;
import Calendario.*;
import javax.swing.UIManager.*;

public class EliminarReserva extends JFrame {

    PainelCentro centro;
    PainelSul sul;

    public EliminarReserva() {
        super("Pesquisar para Eliminar Reserva");

        getContentPane().add(centro = new PainelCentro(), BorderLayout.CENTER);
        getContentPane().add(sul = new PainelSul(), BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    class PainelCentro extends JPanel {
        JTextField nomeClienteJTF;
        JTextField idReservaJTF;

        public PainelCentro() {
            setLayout(new GridLayout(2, 2, 10, 10));

            add(new JLabel("Digite o Nome do Cliente:"));
            nomeClienteJTF = new JTextField();
            add(nomeClienteJTF);

            add(new JLabel("Digite o ID da Reserva:"));
            idReservaJTF = new JTextField();
            add(idReservaJTF);
        }

        public String getNomeCliente() {
            return nomeClienteJTF.getText().trim();
        }

        public int getIdReserva() {
            try {
                return Integer.parseInt(idReservaJTF.getText().trim());
            } catch (NumberFormatException e) {
                return -1; // ID inválido
            }
        }
    }

    class PainelSul extends JPanel implements ActionListener {
        JButton eliminarJB, cancelarJB;

        public PainelSul() {
            add(eliminarJB = new JButton("Eliminar", new ImageIcon("image/delete32.png")));
            add(cancelarJB = new JButton("Cancelar", new ImageIcon("image/cancel.png")));

            eliminarJB.addActionListener(this);
            cancelarJB.addActionListener(this);
        }

        public void actionPerformed(ActionEvent evt) {
            if (evt.getSource() == eliminarJB) {
                ReservaModelo modelo = null;

                // Tentar buscar por ID primeiro, se válido
                int id = centro.getIdReserva();
                if (id > 0) {
                    modelo = ReservaFile.getReservaPorId(id);
                }

                // Se não encontrou por ID, tenta buscar pelo nome
                if (modelo == null) {
                    String nome = centro.getNomeCliente();
                    if (!nome.isEmpty()) {
                        modelo = ReservaFile.getReservaPorCliente(nome);
                    }
                }

                if (modelo != null && modelo.getStatus()) {
                    JOptionPane.showMessageDialog(null, modelo.toString());

                    int opcao = JOptionPane.showConfirmDialog(
                        null,
                        "Tem certeza que deseja eliminar esta reserva?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION
                    );

                    if (opcao == JOptionPane.YES_OPTION) {
                        new ReservaFile().eliminarDados(modelo);
                    } else {
                        JOptionPane.showMessageDialog(null, "Operação cancelada pelo utilizador.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Reserva não encontrada ou já eliminada.");
                }
            } else {
                dispose();
            }
        }
    }
}
