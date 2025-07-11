/*------------------------------------
Tema: Gestão de uma Barbearia
Nome: Enio Manuel
Numero: 2817
Ficheiro: MenuPrincipal.java
Data: 10.07.2025
--------------------------------------*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;
import SwingComponents.*;
import Calendario.*;

public class MenuPrincipal extends JFrame implements ActionListener
{
    private JMenuBar menuBar;
    private JMenu clienteMenu, funcionarioMenu, servicoMenu, pagamentoMenu, agendamentoMenu;
    private JMenu tabelaMenu, listagemMenu, ajudaMenu;
    private JMenuItem novoClienteItem, editarClienteItem, eliminarClienteItem, sairItem, listagemClienteItem,
    pesquisarClienteItem;
    private JMenuItem novoFuncionarioItem, editarFuncionarioItem, eliminarFuncionarioItem, listagemFuncionarioItem,
    pesquisarFuncionarioItem;
    private JMenuItem novoServicoItem, editarServicoItem, eliminarServicoItem, listagemServicoItem,
    pesqusarServicoItem;
    private JMenuItem novoPagamentoItem, editarPagamentoItem, eliminarPagamentoItem, listagemPagamentoItem,
    pesquisarPagamentoItem;
    private JMenuItem novoAgendamentoItem, editarAgendamentoItem, eliminarAgendamentoItem, listagemAgendamentoItem,
    pesquisarAgendamentoItem;
    private JMenuItem nacionalidadeItem, provinciaItem, municipioItem, comunaItem, metodoPagamentoItem,
    tipoDeServicoItem, especialidadeItem;

    public MenuPrincipal()
    {
        super("Menu Principal");

        instanciarObjetos();

        setJMenuBar(menuBar);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void instanciarObjetos()
    {
        // instanciando o MenuBar
        menuBar = new JMenuBar();

        // instanciando os Menus
        menuBar.add(clienteMenu = new JMenu("Clientes"));
        clienteMenu.setMnemonic('C');
        menuBar.add(funcionarioMenu = new JMenu("Funcionarios"));
        funcionarioMenu.setMnemonic('F');
        menuBar.add(servicoMenu = new JMenu("Servicos"));
        servicoMenu.setMnemonic('S');
        menuBar.add(pagamentoMenu = new JMenu("Pagamentos"));
        pagamentoMenu.setMnemonic('P');
        menuBar.add(agendamentoMenu = new JMenu("Agendamentos"));
        agendamentoMenu.setMnemonic('A');
        menuBar.add(listagemMenu = new JMenu("Listagem/Pesquisas"));
        listagemMenu.setMnemonic('L'); 
        menuBar.add(tabelaMenu = new JMenu("Tabelas"));
        tabelaMenu.setMnemonic('T');
        menuBar.add(ajudaMenu = new JMenu("Ajuda"));
        ajudaMenu.setMnemonic('A');

        // instanciando os elementos do menuCliente
        clienteMenu.add(novoClienteItem = new JMenuItem("Novo Cliente"));
        novoClienteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        clienteMenu.add(editarClienteItem = new JMenuItem("Editar Cliente"));
        clienteMenu.add(eliminarClienteItem = new JMenuItem("Eliminar CLiente"));
        clienteMenu.addSeparator();
        clienteMenu.add(sairItem = new JMenuItem("Sair"));

        // instanciando os elementos do funcionarioMenu
        funcionarioMenu.add(novoFuncionarioItem = new JMenuItem("Novo Funcionario"));
        novoFuncionarioItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
        funcionarioMenu.add(editarFuncionarioItem = new JMenuItem("Editar Funcionario"));
        funcionarioMenu.add(eliminarFuncionarioItem = new JMenuItem("Eliminar Funcionario"));

        // instanciando os elementos do servicoMenu
        servicoMenu.add(novoServicoItem = new JMenuItem("Novo Servico"));
        novoServicoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        servicoMenu.add(editarServicoItem = new JMenuItem("Editar Servico"));
        servicoMenu.add(eliminarServicoItem = new JMenuItem("Eliminar Servico"));

        // instanciando os elementos do pagamentoMenu
        pagamentoMenu.add(novoPagamentoItem = new JMenuItem("Novo Pagamento"));
        novoPagamentoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        pagamentoMenu.add(editarPagamentoItem = new JMenuItem("Editar Pagamento"));
        pagamentoMenu.add(eliminarPagamentoItem = new JMenuItem("EliminarPagamento"));

        // instanciando os elementos agendamento
        agendamentoMenu.add(novoAgendamentoItem = new JMenuItem("Novo Agendamento"));
        novoAgendamentoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        agendamentoMenu.add(editarAgendamentoItem = new JMenuItem("Editar Agendamento"));
        agendamentoMenu.add(eliminarAgendamentoItem = new JMenuItem("Eliminar Agendamento"));

        // instanciando os elementos do tabelaMenu
        tabelaMenu.add(nacionalidadeItem = new JMenuItem("Nacionalidades"));
        tabelaMenu.add(provinciaItem = new JMenuItem("Provincias"));
        tabelaMenu.add(municipioItem = new JMenuItem("Municipios"));
        tabelaMenu.add(comunaItem = new JMenuItem("Comunas"));
        tabelaMenu.add(tipoDeServicoItem = new JMenuItem("Tipo de Servico"));
        tabelaMenu.add(especialidadeItem = new JMenuItem("Especialidades"));
        tabelaMenu.add(metodoPagamentoItem = new JMenuItem("Metodos de Pagamento"));

        // instanciando os elementos da listagem
        listagemMenu.add(listagemClienteItem = new JMenuItem("Listar CLientes"));
        listagemMenu.add(pesquisarClienteItem = new JMenuItem("Pesquisa de Clientes"));
        listagemMenu.addSeparator();
        listagemMenu.add(listagemFuncionarioItem = new JMenuItem("Listar Funcionarios"));
        listagemMenu.add(pesquisarFuncionarioItem = new JMenuItem("Pesquisa de Funcionarios"));
        listagemMenu.addSeparator();
        listagemMenu.add(listagemServicoItem = new JMenuItem("Listar Servicos"));
        listagemMenu.add(pesqusarServicoItem = new JMenuItem("Pesquisa de Servicos"));
        listagemMenu.addSeparator();
        listagemMenu.add(listagemPagamentoItem = new JMenuItem("Listagem de Pagamentos"));
        listagemMenu.add(pesquisarPagamentoItem = new JMenuItem("Pesquisa de Pagamentos"));
        listagemMenu.addSeparator();
        listagemMenu.add(listagemAgendamentoItem = new JMenuItem("Listagem dos Agendamentos"));
        listagemMenu.add(pesquisarAgendamentoItem = new JMenuItem("Pesquisa de Agendamentos"));

        // adicionar evento no cliente
        novoClienteItem.addActionListener(this);
        editarClienteItem.addActionListener(this);
        eliminarClienteItem.addActionListener(this);
        sairItem.addActionListener(this);
        listagemClienteItem.addActionListener(this);
        pesquisarClienteItem.addActionListener(this);

        // adicionar evento no funcionario
        novoFuncionarioItem.addActionListener(this);
        editarFuncionarioItem.addActionListener(this);
        eliminarFuncionarioItem.addActionListener(this);
        listagemFuncionarioItem.addActionListener(this);
        pesquisarFuncionarioItem.addActionListener(this);

        // adicionar evento no servico
        novoServicoItem.addActionListener(this);
        editarServicoItem.addActionListener(this);
        eliminarServicoItem.addActionListener(this);
        listagemServicoItem.addActionListener(this);
        pesqusarServicoItem.addActionListener(this);

        // adicionar evento no  pagamento
        novoPagamentoItem.addActionListener(this);
        editarPagamentoItem.addActionListener(this);
        eliminarPagamentoItem.addActionListener(this);
        listagemPagamentoItem.addActionListener(this);
        pesquisarPagamentoItem.addActionListener(this);

        // adicionar evento no agendamento
        novoAgendamentoItem.addActionListener(this);
        editarPagamentoItem.addActionListener(this);
        eliminarPagamentoItem.addActionListener(this);
        listagemPagamentoItem.addActionListener(this);
        pesquisarPagamentoItem.addActionListener(this);
        
        // adicionando eventos no elementos da tabela
        nacionalidadeItem.addActionListener(this);
        provinciaItem.addActionListener(this);
        municipioItem.addActionListener(this);
        comunaItem.addActionListener(this);
        tipoDeServicoItem.addActionListener(this);
        especialidadeItem.addActionListener(this);
        metodoPagamentoItem.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == novoClienteItem)
            new ClienteVisao();
        else if(event.getSource() == listagemClienteItem)
            ClienteFile.listarClientes();
        else if(event.getSource() == nacionalidadeItem)
            Tabela2.editarNovosItems("Nacionalidades.tab", "Nova Nacionalidade");
        else if(event.getSource() == metodoPagamentoItem)
            Tabela2.editarNovosItems("MetodoPagamento.tab", "Novo Metodo de Pagamento");
        else if(event.getSource() == especialidadeItem)
            Tabela2.editarNovosItems("Especialidades.tab", "Nova Especialidade");
        else if(event.getSource() == provinciaItem)
            Tabela2.editarNovosItems("Provincias.tab", "Nova Provincia");
        else if(event.getSource() == municipioItem)
            Tabela3_2.editarNovosItems("Provincias.tab", "Municipios.tab", "Provincia", "Municipio", 
            "Novo Municipio");
        else if(event.getSource() == comunaItem)
            Tabela3_3.editarNovosItems("Provincias.tab", "Municipios.tab", "Comunas.tab", 
            "Provincia", "Municipio", "Comuna", "Nova Comuna");
        else
            dispose();
    }

    public static void main(String[] args)
    {
        Vector_Tabelas.inic();
        new MenuPrincipal();
    }
}