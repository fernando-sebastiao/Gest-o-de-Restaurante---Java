import javax.swing.*;
import java.awt.*;

public class SobreAutor extends JDialog {

    public SobreAutor(JFrame parent) {
        super(parent, "Sobre o Autor", true);
        setSize(600, 900);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setText(
            "Autor: Fernando Afonso Sebastião\n" +
            "Data de Nascimento: 10 de Maio de 2005\n\n" +
            "Desenvolvedor Backend utilizando:\n" +
            "- NestJS\n" +
            "- Node.js\n" +
            "- Spring Boot\n" +
            "- Express\n" +
            "- Fastify\n" +
            "- Prisma\n" +
            "- MySQL\n" +
            "- PostgreSQL\n\n" +
            "Formação:\n" +
            "- Técnico Médio de Gestão de Sistemas Informáticos pelo Instituto Politécnico Industrial de Luanda - Makarenco\n" +
            "- Atualmente cursando o 1º ano de Engenharia Informática na Universidade Católica de Angola"
        );
        textArea.setMargin(new Insets(10, 10, 10, 10));
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));

        add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton btnFechar = new JButton("Fechar");
        btnFechar.addActionListener(e -> dispose());
        add(btnFechar, BorderLayout.SOUTH);

        setVisible(true);
    }
}
