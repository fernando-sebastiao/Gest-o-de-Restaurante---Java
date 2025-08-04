import javax.swing.*;
import java.awt.*;

public class SobreSistema extends JDialog {
    public SobreSistema(JFrame parent) {
        super(parent, "Sobre o Sistema", true);
        setSize(600, 900);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JTextArea texto = new JTextArea(
            "Sistema de Gestão de Restaurante - Versão 1.0\n\n" +
            "Este sistema foi desenvolvido para auxiliar a gestão de um restaurante, " +
            "permitindo o cadastro e controle de Clientes, Reservas, Produtos e Vendas.\n\n" +
            "Funcionalidades principais:\n" +
            "- Cadastrar e listar clientes\n" +
            "- Gerir reservas de mesas\n" +
            "- Adicionar e editar produtos\n" +
            "- Registrar vendas com cálculo de valor total\n\n" +
            "O sistema utiliza Java com interface gráfica Swing e armazena os dados em arquivos binários locais."
        );

        texto.setWrapStyleWord(true);
        texto.setLineWrap(true);
        texto.setEditable(false);
        texto.setBackground(getBackground());
        texto.setFont(new Font("Arial", Font.PLAIN, 14));
        add(new JScrollPane(texto), BorderLayout.CENTER);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnFechar);
        add(painelBotoes, BorderLayout.SOUTH);

        setVisible(true);
    }
}
