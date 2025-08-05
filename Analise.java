/*------------------------------------
Tema: Gestão de um Restaurante
Nome: Fernando Afonso Sebastião
Número: 34422
Ficheiro: Analise.java
Data: 18.01.2025
--------------------------------------*/

/*
1. Objectivo
Este projeto tem como objetivo gerir as operações de um restaurante,
incluindo o cadastro e pesquisa de clientes, controle de produtos,
processamento de vendas e reservas de mesas.

2. Visão [Menus do Sistema / Interfaces]
- MenuPrincipal
- ClienteVisao
- ReservaVisao
- ProdutoVisao
- VendaVisao

3. Entidades Fortes e Seus Atributos (Modelo)
- ClienteModelo
	int id
	String nome
	String bi
	String genero
	int telefone

- ReservaModelo
	int id
	int numPessoas
	String nomeCliente
	String mesa
	String horario
	String funcionario
	DATE dataReserva

- ProdutoModelo
	int id
	String nomeProduto
	float preco

- VendaModelo
	int id
	String nomecliente
	String nomeProduto
	int quantidade
	String formaPagamento
	String funcionario
	DATE dataVenda
	float valorTotal

4. Ficheiros de Dados
- CLIENTES.DAT
- RESERVAS.DAT
- PRODUTOS.DAT
- VENDAS.DAT

5. Tabelas de Apoio (Auxiliares) - Entidades Fracas
- NomeMesa.tab
- NomeProduto.tab
- Nacionalidade.tab
- Provincia.tab
- Municipio.tab
- Comuna.tab
- MetodoPagamento.tab
- NomeFuncionário

6. Listagens e Pesquisas
- Listagem geral de Clientes
- Listagem cliente pelo id do cliente
- Pesquisa de Cliente por Nome
- Listagem geral de Produtos
- Pesquisa de Produto por Nome
- Listagem geral de Vendas
- Pesquisa de Venda por Data
- Listagem geral de Reservas

7. Diversos
7.1 - Implementação: Linguagem C (ou Java, conforme usado)
7.2 - IDE/Editor: NotePad++
7.3 - Compilação: GCC (ou javac se for Java)
*/
