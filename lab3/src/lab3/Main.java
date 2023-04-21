package lab3;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Main {
	//menu interativo que eu acabei descobrindo que era o lab 4
	public static Seguradora criaSeguradora(Scanner entrada) {
		System.out.println("Otimo! qual o nome da sua seguradora?");
		String nomeSeguradora = entrada.nextLine();
		nomeSeguradora = nomeSeguradora.replace("\n","");
		System.out.println("E qual o endereco dela?");
		String enderecoSeguradora = entrada.nextLine();
		enderecoSeguradora = enderecoSeguradora.replace("\n","");
		System.out.println("E o telefone?");
		String telefoneSeguradora = entrada.nextLine();
		telefoneSeguradora = telefoneSeguradora.replace("\n","");
		System.out.println("Por ultimo, qual o email dela?");
		String emailSeguradora = entrada.nextLine();
		emailSeguradora = emailSeguradora.replace("\n","");
		Seguradora seguradora = new Seguradora(nomeSeguradora, telefoneSeguradora, emailSeguradora, enderecoSeguradora);
		return seguradora;
	}
	
	public static Veiculo criaVeiculo(Scanner entrada) {
		System.out.println("Qual a placa do veiculo?");
		String placa = entrada.nextLine();
		placa = placa.replace("\n","");
		System.out.println("Qual o modelo dele?");
		String modelo = entrada.nextLine();
		modelo = modelo.replace("\n","");
		System.out.println("Qual a marca dele?");
		String marca = entrada.nextLine();
		marca = marca.replace("\n","");
		System.out.println("Em que ano ele foi fabricado?");
		int anoFabricacao = entrada.nextInt();
		entrada.nextLine();// come \n
		Veiculo veiculo = new Veiculo(placa, marca, modelo, anoFabricacao);
		return veiculo;
	}
	
	
	public static ClientePJ criaClientePJ(Seguradora seguradora, Scanner entrada) throws Exception{
		System.out.println("Qual o nome do seu cliente?");
		String nomeCliente = entrada.nextLine();
		nomeCliente = nomeCliente.replace("\n","");
		System.out.println("Qual o endereco do seu cliente?");
		String enderecoCliente = entrada.nextLine();
		enderecoCliente = enderecoCliente.replace("\n","");
		System.out.println("Qual o CNPJ dele?");
		String CNPJCliente = entrada.nextLine();
		CNPJCliente = CNPJCliente.replace("\n","");
		if(!ClientePJ.validaCNPJ(CNPJCliente)) {
			System.out.println("Cliente com CNPJ invalido! tente cadastrar outro cliente");
			return null;
		}
		System.out.println("Quando a empresa foi fundada? (dd/MM/yyyy)");
		String dataFundacaoS = entrada.nextLine();
		dataFundacaoS = dataFundacaoS.replace("\n","");
		SimpleDateFormat dataformat = new SimpleDateFormat("dd/MM/yyyy");
		Date dataFundacao = dataformat.parse(dataFundacaoS);
		ClientePJ cliente = new ClientePJ (CNPJCliente, dataFundacao, nomeCliente, enderecoCliente);
		int cadastro;
		do {
		System.out.println("Para cadastrar um novo veiculo para seu cliente, aperte '1', se estiver satisfeito, aperte '0'");
		cadastro = entrada.nextInt();
		entrada.nextLine();
		if(cadastro == 1) {
			Veiculo veiculo = criaVeiculo(entrada);
			if(cliente instanceof ClientePJ) {
				cliente = (ClientePJ) cliente;
				cliente.adicionaVeiculo(veiculo);
			}
		}
		}while(cadastro != 0);
		if(seguradora.cadastrarCliente(cliente)){
			System.out.println("Seu cliente foi cadastrado na seguradora com sucesso");
			return cliente;
		}
		return cliente;
		}
	
	
	
	public static Veiculo encontraCarro(Cliente cliente, String placa) {
		Veiculo veiculo = null;
		for(Veiculo valor: cliente.getMapaVeiculos().values()) {
			if(valor.getPlaca().equals(placa))
				veiculo = valor;
		}
		return veiculo;
	}
	
	public static void criaSinistro(Seguradora seguradora, Scanner entrada) throws Exception{
		boolean flag = false;
		System.out.println("Quando ocorreu o sinistro?");
		String data = entrada.nextLine();
		data = data.replace("\n","");
		System.out.println("Onde ocorreu o sinistro?");
		String endereco = entrada.nextLine();
		endereco = endereco.replace("\n","");
		System.out.println("Se o sinistro ocorreu com um cliente ja cadastrado na seguradora, aperte 1\n, se nao, aperte 2 para cadastra-lo");
		int prox = entrada.nextInt();
		entrada.nextLine();
		Cliente cliente = null;
		Veiculo veiculo = null;
		if(prox == 1) {
			flag = true;
			System.out.println("Insira o cpf/cnpj do cliente para localiza-lo");
			String id = entrada.nextLine();
			id = id.replace("\n", "");
			System.out.println("Insira a placa do veiculo para localiza-lo");
			String placa = entrada.nextLine();
			placa = placa.replace("\n", "");
			for(Cliente value: seguradora.getMapaClientes().values()) {
				if(value instanceof ClientePF) {
					ClientePF valuecast = (ClientePF) value;
					if(valuecast.getCPF().equals(id.replaceAll("[^\\d]",""))) {
						cliente = valuecast;
						veiculo = encontraCarro(valuecast, placa);
					}
				}
				else if(value instanceof ClientePJ) {
					ClientePJ valuecast = (ClientePJ) value;
					if(valuecast.getCNPJ().equals(id.replaceAll("[^\\d]",""))) {
						cliente = valuecast;
						veiculo = encontraCarro(valuecast, placa);
					}
				}
			}
		}
		else if(prox == 2) {
			flag = true;
			System.out.println("Aperte '3' para cliente pessoa fisica e '4' para cliente pessoa juridica");
			int next = entrada.nextInt();
			entrada.nextLine();
			System.out.println("Insira a placa do veiculo que voce cadastrara no cliente com o qual ocorreu o sinistro");
			String novaplaca = entrada.nextLine();
			novaplaca = novaplaca.replace("\n", "");
			if(next == 3) {
				cliente = criaClientePF(seguradora, entrada);
				while(cliente == null) {
					cliente = criaClientePF(seguradora, entrada);
				}
			}
			else if(next == 4) {
				cliente = criaClientePJ(seguradora,entrada);
				while(cliente == null) {
					cliente = criaClientePJ(seguradora,entrada);
				}
		}
			veiculo = encontraCarro(cliente, novaplaca);
		}
	 seguradora.gerarSinistro(data, endereco, veiculo, cliente);
	 System.out.println("Seu sinistro foi cadastrado com sucesso");
	}
	
	public static ClientePF criaClientePF(Seguradora seguradora, Scanner entrada) throws Exception {
		System.out.println("Qual o nome do seu cliente?");
		String nomeCliente = entrada.nextLine();
		nomeCliente = nomeCliente.replace("\n","");
		System.out.println("Qual o endereco do seu cliente?");
		String enderecoCliente = entrada.nextLine();
		enderecoCliente = enderecoCliente.replace("\n","");
		System.out.println("Qual o CPF dele?");
		String CPFCliente = entrada.nextLine();
		CPFCliente = CPFCliente.replace("\n","");
		if(!ClientePF.validarCPF(CPFCliente)) {
			System.out.println("Cliente com CPF invalido! tente cadastrar outro cliente");
			return null;
		}
		System.out.println("Qual o aniversario dele? (dd/MM/yyyy)");
		String dataNascimentoS = entrada.nextLine();
		dataNascimentoS = dataNascimentoS.replace("\n","");
		SimpleDateFormat dataformat = new SimpleDateFormat("dd/MM/yyyy");
		Date dataNascimento = dataformat.parse(dataNascimentoS);
		System.out.println("E quando ele tirou a licenca? (dd/MM/yyyy)");
		String dataLicencaS = entrada.nextLine();
		dataLicencaS = dataLicencaS.replace("\n","");
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy"); // o chat gpt falou pra criar um novo cada vez que precisasse parse just to be safe 
		Date dataLicenca = dateformat.parse(dataLicencaS);
		System.out.println("Qual o genero do seu cliente?");
		String genero = entrada.nextLine();
		genero = genero.replace("\n","");
		System.out.println("Qual o nivel de educacao do seu cliente?");
		String educacao  = entrada.nextLine();
		educacao = educacao.replace("\n","");
		System.out.println("Qual a classe economica do seu cliente?");
		String economica = entrada.nextLine();
		economica = economica.replace("\n","");
		ClientePF cliente = new ClientePF(CPFCliente, dataNascimento, nomeCliente, enderecoCliente, genero, educacao, economica, dataLicenca);
		int cadastro;
		do {
		System.out.println("Para cadastrar um novo veiculo para seu cliente, aperte '1', se estiver satisfeito, aperte '0'");
		cadastro = entrada.nextInt();
		entrada.nextLine(); //come o \n;
		if(cadastro == 1) {
			Veiculo veiculo = criaVeiculo(entrada);
			cliente.adicionaVeiculo(veiculo);
			}
		}while(cadastro != 0);
		if(seguradora.cadastrarCliente(cliente)){
			System.out.println("Seu cliente foi cadastrado na seguradora com sucesso");
			return cliente;
		}
		return cliente; // nunca vai chegar aqui nesse lab mas quem sabe no proximo
	}
	
	
	public static Seguradora startSeguradora(Scanner entrada) {
		System.out.println("Ola! seja bem vindo ao seu portal de seguradora! para criar sua seguradora, aperte '1'");
		int inicio = entrada.nextInt();
		entrada.nextLine(); //come o \n;
		while(inicio != 1) {
			System.out.println("tente novamente");
			inicio = entrada.nextInt();
			entrada.nextLine();
		}
		Seguradora seguradora = criaSeguradora(entrada);
		return seguradora;
	}
	
	public static void startClientes(Seguradora seguradora,  int proximo, Scanner entrada) throws Exception {
		Cliente cliente;
		switch(proximo) {
		case 1:
			cliente = criaClientePF(seguradora, entrada);
				break;
		case 2:
			cliente = criaClientePJ(seguradora, entrada);
				break;
		
	}
		
	}
	

	
	
	public static void startViews(Seguradora seguradora, int mais,  Scanner entrada) {
		switch(mais){
		case 1:
			System.out.println(seguradora.listarSinistros());
				break;
		case 2:
			System.out.println("Digite o cpf/cnpj do cliente cujo sinistro quer ver");
			String id = entrada.nextLine();
			id = id.replace("\n", "");
			seguradora.visualizarSinistro(id);
			break;
		case 3:
			System.out.println("Digite o tipo de cliente (PF/PJ)");
			String tipo = entrada.nextLine();
			tipo = tipo.replace("\n", "");
			System.out.println(seguradora.listarClientes(tipo));
			break;
		case 4:
			System.out.println("Digite o cpf/cnpj do cliente que quer remover");
			 id = entrada.nextLine();
			id = id.replace("\n", "");
			seguradora.removerCliente(id);
			break;
		}

	}
	
	
	public static void obrigacoesLab() throws Exception{
		Seguradora seguradora = new Seguradora("minhaSeguradora", "1111111", "oicolegaarrobadominio", "rua pitagoras");
		ClientePF.validarCPF("111.444.777-35");
		ClientePJ.validaCNPJ("01.587.123/0001-33");
		SimpleDateFormat dataformat = new SimpleDateFormat("dd/MM/yyyy");
		Date dataNascimento = dataformat.parse("13/10/2003");
		Date data= dataformat.parse("13/10/2022");
		ClientePF eu = new ClientePF("111.444.777-35", dataNascimento, "mabe" , "salinha de estudos do ifgw", "feminino", "superior incompleto", "nao recebo", data);
		Veiculo meuCarro = new Veiculo("abc123", "BMW", "mini cooper", 2023);
		eu.adicionaVeiculo(meuCarro);
		seguradora.cadastrarCliente(eu);
		Date aniversario = dataformat.parse("21/07/2009");
		Date licenca = dataformat.parse("21/04/2030");
		ClientePF rebeca = new ClientePF("115.543.157-00", aniversario, "rebeca", "no ceu", "cachorro mulher", "cachorro nao tem escola", "A?", licenca);
		Veiculo carroRebs = new Veiculo("auau1212", "chevrolet", "bolt", 2022);
		rebeca.adicionaVeiculo(carroRebs);
		seguradora.cadastrarCliente(rebeca);
		Date fundacao = dataformat.parse("10/01/1996");
		ClientePJ escritorio = new ClientePJ("01.587.123/0001-33", fundacao, "escritorio", "sp");
		Veiculo carroEscritorio = new Veiculo("aaaa1212", "toyota", "corolla", 2020);
		escritorio.adicionaVeiculo(carroEscritorio);
		seguradora.cadastrarCliente(escritorio);
		seguradora.gerarSinistro("21/04/2023", "rua do Ic", meuCarro, eu);
		seguradora.gerarSinistro("21/04/2023", "rua pitagoras", carroRebs, rebeca);
		seguradora.visualizarSinistro("111.444.777-35");
		seguradora.removerCliente("115.543.157-00");
	}
	
	public static void main(String args[]) throws Exception {
		int acao = -1;
		Scanner entrada = new Scanner(System.in);
		Seguradora seguradora = startSeguradora(entrada);
		while(acao != 0) {
		System.out.println("Digite: (1) para cadastrar clientePF\n(2) para cadastrar clientePJ\n(3) para adicionar um sinistro a seguradora");
		System.out.println("(4) para remover cliente\n(5) para listar sinistros\n(6) para listar clientes de certo tipo\n (0) para encerrar o programa");
		acao = entrada.nextInt();
		entrada.nextLine(); //come o \n;
		switch(acao) {
		case 1:
			 startClientes(seguradora, 1, entrada);
			break;
		case 2:
			 startClientes(seguradora,  2, entrada);
			break;
		case 3:
			 criaSinistro(seguradora,entrada);
			 break;
		case 4:
			 startViews(seguradora, 4, entrada);
			break;
		case 5:
			startViews(seguradora, 1,entrada);
			break;
		case 6:
			 startViews(seguradora, 3,entrada);
			break;
	}
		}
			
		System.out.println("Obrigada por utilizar o programa! Esse e o estado atual da sua seguradora:\n");
		System.out.println(seguradora);
		System.out.println("####################");
		entrada.close();
		System.out.println("Agora as impressoes referentes a outras tarefas obrigatorias do lab:");
		obrigacoesLab();// fiz o menu agora vou passar no lab (garantir que o numero certo de instancias vai ser feito pq nao posso fazer isso com o usuario)
		
} 	
}

