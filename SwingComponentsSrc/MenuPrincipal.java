package SwingComponents;
//Title:        Your Product Name
//Version:
//Copyright:    Copyright (c) 1999
//Author:       Isabel Cafe
//Company:      UCAN
//Description:  Your description


import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.*;

public class MenuPrincipal extends JFrame
{

 // private  Editar_Provincia provinciaTab;
  private JMenuItem comprasItem[], ComprVendItem[], listVendComprItem[], carrosItem,
          listCarrosItem,  consultPessoaItem, consultCarrosItem[], ComprasSubItem1, ComprasSubItem2,
          ComprasSubItem3,PessoasSubItem[], menuSubTabelaItem[], ferramentItem[], sairItem;
 // Private ;
    //private GestaoFicheiros fich ;

  public MenuPrincipal()
  {
    super("REGISTO DE PROPRIEDADE DE VEICULOS");

    //fich = new GestaoFicheiros( );

    JMenuBar menuBar = new JMenuBar();
    setJMenuBar(menuBar);

    JMenu menuFicheiros, menuCompra, menuComprVend, menuConsultas, menuTabelas,
    menuAplicacao, menuCompras,menuPessoa,consultPessoaItem, consultComprItem,
    consultCarros,PessoasItem[], menuCarros, menuTabelaItem[], menuFormatacao,
    formatItem[], menuFerramentas;

    //Configura o menu Ficheiro
     menuFicheiros = new JMenu("Ficheiros");
     menuFicheiros.setMnemonic('F');
     menuBar.add(menuFicheiros);

     //Configura o sub-menu Compras de Ficheiro
     menuCompra = new JMenu("Compra");
     menuCompra.setMnemonic('o');
     menuFicheiros.add(menuCompra);
     
     //Configura os items do Compra
     String StrComprasItem[] = {"Efectuar Compra","Listar Fichas","Alterar","Eliminar"};
     char strComprasMenem[] = {'E','L','A', 'l'};

     comprasItem = new JMenuItem [StrComprasItem.length];
     for(int i = 0; i < StrComprasItem.length; i++)
     {
        comprasItem[i] = new JMenuItem(StrComprasItem[i]);
        comprasItem[i].setMnemonic(strComprasMenem[i]);
        menuCompra.add(comprasItem[i]) ;
     }

     //Configura o sub-menu Comprador/Vendedor
     menuComprVend = new JMenu("Comprador/Vendedor");
     menuComprVend.setMnemonic('V');
     menuFicheiros.add(menuComprVend);

     //Configura os items do Comprador/Vendedor
     String strComprVendItem[] = {"Listar Compradores","Listar Vendedores"};
     char strComprVendMenem[] = {'a','e'};

     ComprVendItem = new JMenuItem[strComprVendItem.length];
     for(int i = 0; i < strComprVendItem.length; i++)
     {
        ComprVendItem[i] = new JMenuItem(strComprVendItem[i]);
        ComprVendItem[i].setMnemonic(strComprVendMenem[i]);
        menuComprVend.add(ComprVendItem[i]) ;
     }

     //Configura o sub-menu Carros
     menuCarros = new JMenu("Carros");
     menuCarros.setMnemonic('r');
     menuFicheiros.add(menuCarros);

     //Configura os items do Carros
     carrosItem = new JMenuItem("Listar Carros Vendidos");
     menuCarros.setMnemonic('L');
     menuCarros.add(carrosItem);


     //Configura o menu Consultas
     menuConsultas = new JMenu("Consultas");
     menuConsultas.setMnemonic('C');
     menuBar.add(menuConsultas);

      //Configura o Item Compras do Consultas
     consultComprItem = new JMenu("Compras");
     consultComprItem.setMnemonic('O');
     menuConsultas.add(consultComprItem);

     ComprasSubItem1 = new JMenuItem("Listar Carros Vendidos");
     ComprasSubItem1.setMnemonic('V');
     consultComprItem.add(ComprasSubItem1) ;

     ComprasSubItem2 = new JMenuItem("Por de Data Venda");
     ComprasSubItem2.setMnemonic('D');
     consultComprItem.add(ComprasSubItem2);

     ComprasSubItem3 = new JMenuItem("Pela Importância da Compra");
     ComprasSubItem3.setMnemonic('I');
     consultComprItem.add(ComprasSubItem3);

      //Configura o Item Pessoa do Consultas
     consultPessoaItem = new JMenu("Pessoas");
     consultPessoaItem.setMnemonic('P');
     menuConsultas.add(consultPessoaItem);

     String strPessoasItem[] = {"Compradores","Vendedores"};
     char strPessoasMenem[] = {'a','e'};

     String strPessoasSubItem[] = {"Por Nome","Por Data Compra/Venda ","Por Marca do Carro"};
     char strSubPessoasMenem[] = {'N','D', 'M'};


     PessoasItem = new JMenu[strComprVendItem.length];
     PessoasSubItem = new JMenuItem[strPessoasSubItem.length];

     for(int i = 0; i < strComprVendItem.length; i++)
     {
        PessoasItem[i] = new JMenu(strComprVendItem[i]);
        PessoasItem[i].setMnemonic(strComprVendMenem[i]);
        consultPessoaItem.add(PessoasItem[i]) ;

        for(int j = 0; j < strPessoasSubItem.length; j++)
        {
          PessoasSubItem[j] = new JMenuItem(strPessoasSubItem[j]);
          PessoasSubItem[j].setMnemonic(strSubPessoasMenem[j]);
          PessoasItem[i].add(PessoasSubItem[j]) ;
        }
     }

     //Configura o Item Carros do Consultas
     consultCarros = new JMenu("Carros");
     consultCarros.setMnemonic('a');
     menuConsultas.add(consultCarros);

     //Configura os items do Carro
     String strCarroItem[] = {"Por Nome do Comprador","Por Data de Compra/Venda ","Por Marca do Carro"};
     char strCarroMenem[] = {'N','D', 'M'};

     consultCarrosItem = new JMenu[strCarroItem.length];
     for(int i = 0; i < strCarroItem.length; i++)
     {
        consultCarrosItem[i] = new JMenu(strCarroItem[i]);
        consultCarrosItem[i].setMnemonic(strCarroMenem[i]);
        consultCarros.add(consultCarrosItem[i]) ;
     }

     //Configura o Menu Tabelas
     menuTabelas = new JMenu("Tabelas");
     menuTabelas.setMnemonic('T');
     menuBar.add(menuTabelas);

     //Configura os items do Tabelas
     String strTabItem[] = {"Marcas de Carros","Cores","Profissao", "Provincias", "Municipio", "Comuna"};
     char strTabMenem[] = {'a','C', 'P', 'r', 'M', 'o'};

     String strSubTabItem[] = {"Editar ","Listar "};
     char strSubTabMenem[] = {'E','L'};

     menuTabelaItem = new JMenu[strTabItem.length];
     menuSubTabelaItem = new JMenuItem[strSubTabItem.length];
      
     for(int i = 0; i < strTabItem.length; i++)
     {
        menuTabelaItem[i] = new JMenu(strTabItem[i]);
        menuTabelaItem[i].setMnemonic(strTabMenem[i]);
        menuTabelas.add(menuTabelaItem[i]) ;

        for(int j = 0; j < strSubTabItem.length; j++)
        {
          menuSubTabelaItem[j] = new JMenuItem(strSubTabItem[j]);
          menuSubTabelaItem[j].setMnemonic(strSubTabMenem[j]);
          menuTabelaItem[i].add(menuSubTabelaItem[j]) ;
        }
     }

     //Configura o Formatacao
     menuFormatacao = new JMenu("Formatacao");
     menuFormatacao.setMnemonic('F');
     menuBar.add(menuFormatacao);

      //Configura os items do Formatacao
     String strFormatItem[] = {"Fonte","Background","Apparencia da janela"};
     char strFormatMenem[] = {'o','B', 'A'};

     formatItem = new JMenu[strFormatItem.length];
     for(int i = 0; i < strFormatItem.length; i++)
     {
        formatItem[i] = new JMenu(strFormatItem[i]);
        formatItem[i].setMnemonic(strFormatMenem[i]);
        menuFormatacao.add(formatItem[i]) ;
     }

     //Configura o Ferramentas
     menuFerramentas = new JMenu("Ferramentas");
     menuFerramentas.setMnemonic('e');
     menuBar.add(menuFerramentas);

     //Configura os Itens do Ferramentas
     String strFerramentItem[] = {"Notepad","Calculadora"};
     char strFerramentMenem[] = {'N','C'};

     ferramentItem = new JMenuItem[strFerramentItem.length];
     for(int i = 0; i < strFerramentItem.length; i++)
     {
        ferramentItem[i] = new JMenuItem(strFerramentItem[i]);
        ferramentItem[i].setMnemonic(strFerramentMenem[i]);
        menuFerramentas.add(ferramentItem[i]) ;
     }

     //Configura o Formatacao
     menuAplicacao = new JMenu("Aplicacao");
     menuAplicacao.setMnemonic('A');
     menuBar.add(menuAplicacao);

  Icon Carro = new ImageIcon("Carro.gif");
  JLabel imagem = new JLabel(Carro);
 getContentPane().add(imagem);



    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

 //setSize(400,250);
  setSize(800,650);
  setVisible(true);
  }

 

   public static void main(String[] args)
 {
        new MenuPrincipal( );
 }

}


     
/*
 //************************************************************************************
    

     menuConsultContas = new JMenu("Consultas");
     menuConsultContas.setMnemonic('C');





     menuConsultDebitos = new JMenu("Consultas");
     menuConsultDebitos.setMnemonic('C');
     menuClientes = new JMenu("Clientes");
     menuClientes .setMnemonic('C');
     menuFicheiros.add(menuClientes);
     menuConsultClientes =  new JMenu("Consultas");
     menuConsultClientes.setMnemonic('C');
     menuContas = new JMenu("Contas");
     menuContas.setMnemonic('o');
     menuFicheiros.add(menuContas);
     menuTabelas = new JMenu("Tabelas");
     menuTabelas.setMnemonic('T');
     menuBar.add(menuTabelas);
     menuAplicacao = new JMenu("Aplicacao");
     menuAplicacao.setMnemonic('A');
     menuBar.add(menuAplicacao);
     menuContas.add(menuConsultContas);
     menuDebitos.add(menuConsultDebitos);
     menuClientes.add( menuConsultClientes);



   //Configura o item de menu para efectuar debitos
   efectDebitItem = new JMenuItem("Efectuar");
   efectDebitItem.setEnabled(true);

   //Exibe dialogo para efectuar debitos quando o usuario seleciona Debitos
     efectDebitItem.addActionListener(
        new ActionListener()
        {
          public void actionPerformed( ActionEvent event)
          {

          }
        }
     );
  /*
  //Configura o item de menu para consultar debitos
   consultDebitItem = new JMenuItem("Consultar");
   consultDebitItem.setEnabled(true);

   //Exibe dialogo para consultar debitos quando o usuario seleciona Debitos
     consultDebitItem.addActionListener(
        new ActionListener()
        {
          public void actionPerformed( ActionEvent event)
          {
            new InterfDebito("Efectuar Novos Debitos");
          }
        }
     ); */
       /*
     //Configura os items do menu para consultar debitos
     String consultsDebit[] = {"Por debitante","Pela importancia","por nº do balcão","Por data "};
                           

     consultDebitosItems = new JMenuItem[consultsDebit.length];
     for(int i = 0; i < consultsDebit.length; i++)
     {
        consultDebitosItems[i] = new JMenuItem(consultsDebit[i]);
        menuConsultDebitos.add(consultDebitosItems[i]) ;
     }

     //Configura o item de menu para consultar debitos
   efectDebitItem = new JMenuItem("Efectuar Debito");
   efectDebitItem.setEnabled(true);

   //Exibe dialogo para consultar debitos quando o usuario seleciona Debitos
     efectDebitItem.addActionListener(
        new ActionListener()
        {
          public void actionPerformed( ActionEvent event)
          {
             dispose();
             //if()| se nao existe o ficheiro
                  fich.createFile();
             //senao
                  new DebitosCash("Efectuar Novos Debitos");
            
          }
        }
     );

    //Configura o item de menu para criar clientes
   criarClienteItem = new JMenuItem("Criar");
   criarClienteItem.setEnabled(true);

   //Exibe dialogo para criar clientes quando o usuario seleciona Debitos
     criarClienteItem.addActionListener(
        new ActionListener()
        {
          public void actionPerformed( ActionEvent event)
          {
              dispose();
             new InterfCliente("Inserir Novos Clientes");
          }
        }
     );


      //Configura os items do menu para consultar debitos
     String consultsClients[] = {"Por nome","Por data de nascimento",
                           "Por naturalidade"};

     consultClienteItems = new JMenuItem[consultsClients.length];
     for(int i = 0; i < consultsClients.length; i++)
     {
        consultClienteItems[i] = new JMenuItem(consultsClients[i]);
        menuConsultClientes.add(consultClienteItems[i]) ;
     }

     //Configura o item de menu para inserir clientes
   //consultClienteItem = new JMenuItem("Consultar");
   //consultClienteItem.setEnabled(true);

   //Exibe dialogo para inserir clientes quando o usuario seleciona Debitos
     consultClienteItems[0].addActionListener(
        new ActionListener()
        {
          public void actionPerformed( ActionEvent event)
          {
             new InterfConsult("consulta");
          }
        }
     );

   //Configura o item de menu para criar contas
   criarContasItem = new JMenuItem("Criar");
   criarContasItem.setEnabled(true);

   //Exibe dialogo para criar contas quando o usuario seleciona Debitos
     criarContasItem.addActionListener(
        new ActionListener()
        {
          public void actionPerformed( ActionEvent event)
          {
              dispose();
             new InterfConta("Criar Novas Contas");
          }
        }
     );

  //Configura o item de menu para consultar contas
   consultContasItem = new JMenuItem("Consultar");
   consultContasItem.setEnabled(true); 

   //Exibe dialogo para consultar contas quando o usuario seleciona Debitos
     consultContasItem.addActionListener(
        new ActionListener()
        {
          public void actionPerformed( ActionEvent event)
          {
             new InterfConsult("Consulta");
          }
        }
     ); */

   /*

   //Configura o item de menu para consultar contas
   provinciasItem = new JMenuItem("Provincias");
   provinciasItem.setEnabled(true);

   //Exibe dialogo para consultar contas quando o usuario seleciona Debitos
   provinciasItem.addActionListener(
        new ActionListener()
        {
          public void actionPerformed( ActionEvent event)
          {
            new Editar_Tabela2("Provincias.dat",0);//provinciaTab.setVisible(true);
          }
        }
     );

   //Configura o item de menu para consultar contas
   bancosItem = new JMenuItem("Bancos");
   bancosItem.setEnabled(true);

   //Exibe dialogo para consultar contas quando o usuario seleciona Debitos
       bancosItem.addActionListener(
        new ActionListener()
        {
          public void actionPerformed( ActionEvent event)
          {
             new Editar_Tabela2("Bancos.dat",0);
          }
        }
     );

     //Anexa itens de menu ao menu file
   menuFicheiros.add(sairItem) ;

   //Configura o item de menu para sair
   sairItem = new JMenuItem("Sair");
   sairItem.setEnabled(true);

   //Sai da aplicacao quando o usuario seleciona Sair
     sairItem.addActionListener(
        new ActionListener()
        {
          public void actionPerformed( ActionEvent event)
          {
           System.exit(0);
          }
        }
     );
   menuDebitos.add(efectDebitItem);
   menuClientes.add(criarClienteItem) ;
   menuContas.add(criarContasItem);
   menuTabelas.add(provinciasItem);
   menuTabelas.add(bancosItem);

   //configura a janela
  setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

  /*Icon logoTipo = new ImageIcon("LogBCI.gif");
  JLabel imagem = new JLabel("",logoTipo, SwingConstants.CENTER );
  getContentPane().add(imagem);*/
   /*
  setSize(800,650);
  setVisible(true);
  }

   public static void main(String[] args)
 {
        new MenuPrincipal( );
 }

}
  */


