/*------------------------------------
Tema: Gestão de um Restaurante
Nome: Fernando Afonso Sebastiao
Numero: 34422
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
    private JMenu clienteMenu, reservaMenu,produtoMenu,vendaMenu,tabelaMenu, listagemMenu,ajudaMenu;
    private JMenuItem novoClienteItem, editarClienteItem, eliminarClienteItem, sairItem, listagemClienteItem, pesquisarClienteItem;
    private JMenuItem novaReservaItem, editarReservaItem, eliminarReservaItem, listarReservasItem, pesquisarReservaItem;
    private JMenuItem novoProdutoItem, editarProdutoItem, eliminarProdutoItem, listarProdutosItem, pesquisarProdutoItem;
    private JMenuItem novaVendaItem, editarVendaItem, eliminarVendaItem, listarVendasItem, pesquisarVendaItem;
    private JMenuItem nacionalidadeItem, provinciaItem, municipioItem, comunaItem, metodoPagamentoItem, nomeClienteItem, nomeProdutoItem, nomeFuncionarioItem, nomeMesaItem;

    public MenuPrincipal(String user)
    {
        super("Menu Principal | Operador " + user);

        instanciarObjetos();

        setJMenuBar(menuBar);

        //pack();
        setSize(900, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void instanciarObjetos()
    {
        // instanciando o MenuBar
        menuBar = new JMenuBar();

        // instanciando os Menus
        menuBar.add(clienteMenu = new JMenu("Clientes"));
        clienteMenu.setIcon(new ImageIcon("image/cliente.png"));
        clienteMenu.setMnemonic('C');

        menuBar.add(reservaMenu = new JMenu("Reservas"));
        reservaMenu.setIcon(new ImageIcon("image/reserva.png"));
        reservaMenu.setMnemonic('R');

        menuBar.add(produtoMenu = new JMenu("Produtos"));
        produtoMenu.setIcon(new ImageIcon("image/adicionar-produto.png"));
        produtoMenu.setMnemonic('P');

        menuBar.add(vendaMenu = new JMenu("Vendas"));
        vendaMenu.setIcon(new ImageIcon("image/mao.png"));
        vendaMenu.setMnemonic('V');

        menuBar.add(listagemMenu= new JMenu("Listagens"));
        listagemMenu.setIcon(new ImageIcon("image/listagens.png"));
        listagemMenu.setMnemonic('L');
        
        menuBar.add(tabelaMenu = new JMenu("Tabelas"));
        tabelaMenu.setIcon(new ImageIcon("image/tabela.png"));
        tabelaMenu.setMnemonic('T');
        
        menuBar.add(ajudaMenu = new JMenu("Ajuda"));
        ajudaMenu.setIcon(new ImageIcon("image/help.png"));
        ajudaMenu.setMnemonic('A');

        // instanciando os elementos do menuCliente
        clienteMenu.add(novoClienteItem = new JMenuItem("Novo Cliente", new ImageIcon("image/novo24.png")));
        novoClienteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        clienteMenu.add(editarClienteItem = new JMenuItem("Editar Cliente", new ImageIcon("image/edit24.png")));
        clienteMenu.add(eliminarClienteItem = new JMenuItem("Eliminar CLiente", new ImageIcon("image/delete24.png")));
        clienteMenu.addSeparator();
        clienteMenu.add(pesquisarClienteItem = new JMenuItem("Pesquisar Cliente", new ImageIcon("image/all/search24.png")));
        clienteMenu.addSeparator();
        clienteMenu.add(sairItem = new JMenuItem("Sair", new ImageIcon("image/poder.png")));

        //Reserva
        reservaMenu.add(novaReservaItem = new JMenuItem("Nova Reserva", new ImageIcon("image/novo24.png")));
        novaReservaItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        reservaMenu.add(editarReservaItem = new JMenuItem("Editar Reservas", new ImageIcon("image/edit24.png")));
        reservaMenu.add(eliminarReservaItem = new JMenuItem("Eliminar Reserva", new ImageIcon("image/delete24.png")));
        reservaMenu.add(pesquisarReservaItem = new JMenuItem("Pesquisar Reserva", new ImageIcon("image/all/search24.png")));

        //ProdutoMenu
        produtoMenu.add(novoProdutoItem = new JMenuItem("Nova Produto", new ImageIcon("image/novo24.png")));
        novoProdutoItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        produtoMenu.add(editarProdutoItem = new JMenuItem("Editar Produto", new ImageIcon("image/edit24.png")));
        produtoMenu.add(eliminarProdutoItem = new JMenuItem("Eliminar Produto", new ImageIcon("image/delete24.png")));
        produtoMenu.add(pesquisarProdutoItem = new JMenuItem("Pesquisar Produto", new ImageIcon("image/all/search24.png")));

        //VendaMenu
        vendaMenu.add(novaVendaItem = new JMenuItem("Nova Venda", new ImageIcon("image/novo24.png")));
        novaVendaItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
        vendaMenu.add(editarVendaItem = new JMenuItem("Editar Venda", new ImageIcon("image/edit24.png")));
        vendaMenu.add(eliminarVendaItem = new JMenuItem("Eliminar Venda", new ImageIcon("image/delete24.png")));
        vendaMenu.add(pesquisarVendaItem = new JMenuItem("Pesquisar Venda", new ImageIcon("image/all/search24.png")));

        //ListagensMenu
        listagemMenu.add(listagemClienteItem = new JMenuItem("Listar Cliente", new ImageIcon("image/listagens-all.png")));
        listagemMenu.add(listarReservasItem = new JMenuItem("Listar Reservas", new ImageIcon("image/listagens-all.png")));
        listagemMenu.add(listarProdutosItem = new JMenuItem("Listar Produto", new ImageIcon("image/listagens-all.png")));
        listagemMenu.add(listarVendasItem = new JMenuItem("Listar Vendas", new ImageIcon("image/listagens-all.png")));

        // instanciando os elementos do tabelaMenu
        tabelaMenu.add(nomeClienteItem = new JMenuItem("Nome do Cliente"));
        tabelaMenu.add(nomeProdutoItem = new JMenuItem("Nome do Produto"));
        tabelaMenu.add(nacionalidadeItem = new JMenuItem("Nacionalidades"));
        tabelaMenu.add(provinciaItem = new JMenuItem("Provincias"));
        tabelaMenu.add(municipioItem = new JMenuItem("Municipios"));
        tabelaMenu.add(comunaItem = new JMenuItem("Comunas"));
        tabelaMenu.add(metodoPagamentoItem = new JMenuItem("Metodos de Pagamento"));
        tabelaMenu.add(nomeMesaItem = new JMenuItem("Nome Da Mesa"));
        tabelaMenu.add(nomeFuncionarioItem = new JMenuItem("Nome Do Funcionário"));

        // adicionar evento no cliente
        novoClienteItem.addActionListener(this);
        editarClienteItem.addActionListener(this);
        eliminarClienteItem.addActionListener(this);
        sairItem.addActionListener(this);
        listagemClienteItem.addActionListener(this);
        pesquisarClienteItem.addActionListener(this);

        //RESERVA
        novaReservaItem.addActionListener(this);
        editarReservaItem.addActionListener(this);
        eliminarReservaItem.addActionListener(this);
        pesquisarReservaItem.addActionListener(this);
        listarReservasItem.addActionListener(this);

        //PRODUTO
        novoProdutoItem.addActionListener(this);
        listarProdutosItem.addActionListener(this);
        editarProdutoItem.addActionListener(this);
        eliminarProdutoItem.addActionListener(this);
        pesquisarProdutoItem.addActionListener(this);

        //Venda
        novaVendaItem.addActionListener(this);
        listarVendasItem.addActionListener(this);
        editarVendaItem.addActionListener(this);
        eliminarVendaItem.addActionListener(this);
        pesquisarVendaItem.addActionListener(this);
        
        // adicionando eventos no elementos da tabela
        nomeClienteItem.addActionListener(this);
        nomeProdutoItem.addActionListener(this);
        nacionalidadeItem.addActionListener(this);
        provinciaItem.addActionListener(this);
        municipioItem.addActionListener(this);
        comunaItem.addActionListener(this);
        metodoPagamentoItem.addActionListener(this);
        nomeFuncionarioItem.addActionListener(this);
        nomeMesaItem.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event)
    {
        if(event.getSource() == novoClienteItem)
            new ClienteVisao(false, new ClienteModelo());
        else if(event.getSource() == listagemClienteItem)
            ClienteFile.listarClientes();
        else if(event.getSource() == pesquisarClienteItem)
            new PesquisarCliente();
        else if(event.getSource() == editarClienteItem)
            new EditarCliente();
        else if(event.getSource() == eliminarClienteItem)
            new EliminarCliente();

        //RESERVA
        if(event.getSource() == novaReservaItem)
            new ReservaVisao(false, new ReservaModelo());
        else if(event.getSource() == listarReservasItem)
            ReservaFile.listarReservas();
        else if(event.getSource() == editarReservaItem)
            new EditarReserva(); 
        else if(event.getSource() == eliminarReservaItem)
            new EliminarReserva();
        else if(event.getSource() == pesquisarReservaItem)
            new PesquisarReserva();

        //PRODUTOS
        if(event.getSource() == novoProdutoItem)
            new ProdutoVisao(false, new ProdutoModelo());
        else if(event.getSource() == listarProdutosItem)
            ProdutoFile.listarProdutos(); 
        else if(event.getSource() == eliminarProdutoItem)
            new EliminarProduto();
        else if(event.getSource() == pesquisarProdutoItem)
            new PesquisarProduto();
        else if(event.getSource() == editarProdutoItem)
            new EditarProduto();  

        //VENDAS
          if(event.getSource() == novaVendaItem)
            new VendaVisao(false, new VendaModelo());
        else if(event.getSource() == listarVendasItem)
            VendaFile.listarVendas(); 
        else if(event.getSource() == eliminarVendaItem)
            new EliminarVenda();
        else if(event.getSource() == pesquisarVendaItem)
            new PesquisarVenda();
        else if(event.getSource() == editarVendaItem)
            new EditarVenda();

        //TABELAS               
        else if(event.getSource() == nomeFuncionarioItem)
            Tabela2.editarNovosItems("NomeFuncionario.tab", "Nova Mesa");    
        else if(event.getSource() == nomeMesaItem)
            Tabela2.editarNovosItems("NomeMesa.tab", "Nova Mesa");    
        else if(event.getSource() == nomeClienteItem)
            Tabela2.editarNovosItems("NomeCLiente.tab", "Novo Cliente");
        else if(event.getSource() == nomeProdutoItem)
            Tabela2.editarNovosItems("NomeProduto.tab", "Novo Cliente"); 
        else if(event.getSource() == nacionalidadeItem)
            Tabela2.editarNovosItems("Nacionalidades.tab", "Nova Nacionalidade");
        else if(event.getSource() == metodoPagamentoItem)
            Tabela2.editarNovosItems("MetodoPagamento.tab", "Novo Metodo de Pagamento");
        else if(event.getSource() == provinciaItem)
            Tabela2.editarNovosItems("Provincias.tab", "Nova Provincia");
        else if(event.getSource() == municipioItem)
            Tabela3_2.editarNovosItems("Provincias.tab", "Municipios.tab", "Provincia", "Municipio", 
            "Novo Municipio");
        else if(event.getSource() == comunaItem)
            Tabela3_3.editarNovosItems("Provincias.tab", "Municipios.tab", "Comunas.tab", 
            "Provincia", "Municipio", "Comuna", "Nova Comuna");
    }

    public static void main(String[] args)
    {
        Vector_Tabelas.inic();
        new MenuPrincipal("");
    }
}