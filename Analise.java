/*------------------------------------
Tema: Gestão de uma Barbearia
Nome: Enio Manuel
Numero: 2817
Ficheiro: Analise.java
Data: 21.06.2025
--------------------------------------*/
/*
1. Objectivo
Este projecto tem o objectivo de gerir os serviços prestados numa barbearia, 
incluindo o registo de clientes, agendamento de atendimentos, gestão de funcionários 
e serviços oferecidos.

2. Visao [Interfaces Gráficas]
- ApresentacaoVisao
- LoginVisao
- MenuPrincipal
- ClienteVisao
- FuncionarioVisao
- ServicoVisao
- AgendamentoVisao
- PagamentoVisao

3. Entidades Fortes e Seus Atributos (Modelo)
- ClienteModelo
	int id
	String nome
	String contacto
	String genero
	String nacionalidade
	String provincia
	String municipio
	String comuna
	boolean status
	
- FuncionarioModelo
	int id
	String nome
	String especialidade
	String contacto
	String genero
	String provincia
	String municipio
	String comuna
	boolean status

- ServicoModelo
	int id
	String nomeServico
	double preco
	int duracaoMinutos
	String descricao
	boolean status

- AgendamentoModelo
	int id
	ClienteModelo cliente
	FuncionarioModelo funcionario
	ServicoModelo servico
	String dataAgendamento
	String horaAgendamento
	String estado // Agendado, Concluído, Cancelado
	boolean status

- PagamentoModelo
	int id
	AgendamentoModelo agendamento
	String dataPagamento
	double valorPago
	String metodoPagamento // Dinheiro, Cartão, Transferência

4. Ficheiros
- ClienteFile.dat
- FuncionarioFile.dat
- ServicoFile.dat
- AgendamentoFile.dat
- PagamentoFile.dat

5. Tabelas de Apoio (Auxiliares) = Entidades Fracas
- Nacionalidades.tab
- Provincias.tab
- Municipios.tab
- Comunas.tab
- MetodoPagamento.tab
- Especialidades.tab
- TiposServico.tab

6. Listagens e Pesquisas
- Listagem geral de Clientes
- Listagem geral de Agendamentos
- Pesquisa de Cliente por Nome
- Pesquisa de Cliente por Id
- Pesquisa de Agendamentos por Data

7. Diversos
7.1 - Implementação: Java Swing
7.2 - IDE: Notepad++
*/