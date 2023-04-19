package lab3;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Main {
	
	public static Seguradora criaSeguradora() {
		Scanner entrada = new Scanner(System.in);
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
		entrada.close();
		return seguradora;
	}
	
	public static Veiculo criaVeiculo() {
		Scanner entrada = new Scanner(System.in);
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
		Veiculo veiculo = new Veiculo(placa, marca, modelo, anoFabricacao);
		entrada.close();
		return veiculo;
	}
	
	
	public static ClientePJ criaClientePJ(Seguradora seguradora) throws Exception{
		Scanner entrada = new Scanner(System.in);
		System.out.println("Qual o nome do seu cliente?");
		String nomeCliente = entrada.nextLine();
		nomeCliente = nomeCliente.replace("\n","");
		System.out.println("Qual o endereco do seu cliente?");
		String enderecoCliente = entrada.nextLine();
		enderecoCliente = enderecoCliente.replace("\n","");
		System.out.println("Qual o CNPJ dele?");
		String CNPJCliente = entrada.nextLine();
		if(!ClientePJ.validaCNPJ(CNPJCliente)) {
			System.out.println("Cliente com CNPJ invalido! tente cadastrar outro cliente");
			entrada.close();
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
		if(cadastro == 1) {
			Veiculo veiculo = criaVeiculo();
			cliente.adicionaVeiculo(veiculo);}
		}while(cadastro != 0);
		if(seguradora.cadastrarCliente(cliente)){
			System.out.println("Seu cliente foi cadastrado na seguradora com sucesso");
			entrada.close();
			return cliente;
		}
		entrada.close();
		return cliente;
		
	}
	
	
	
	public static ClientePF criaClientePF(Seguradora seguradora) throws Exception {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Qual o nome do seu cliente?");
		String nomeCliente = entrada.nextLine();
		nomeCliente = nomeCliente.replace("\n","");
		System.out.println("Qual o endereco do seu cliente?");
		String enderecoCliente = entrada.nextLine();
		enderecoCliente = enderecoCliente.replace("\n","");
		System.out.println("Qual o CPF dele?");
		String CPFCliente = entrada.nextLine();
		if(!ClientePF.validarCPF(CPFCliente)) {
			System.out.println("Cliente com CPF invalido! tente cadastrar outro cliente");
			entrada.close();
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
		if(cadastro == 1) {
			Veiculo veiculo = criaVeiculo();
			cliente.adicionaVeiculo(veiculo);}
		}while(cadastro != 0);
		if(seguradora.cadastrarCliente(cliente)){
			System.out.println("Seu cliente foi cadastrado na seguradora com sucesso");
			entrada.close();
			return cliente;
		}
		entrada.close();
		return cliente; // nunca vai chegar aqui nesse lab mas quem sabe no proximo
	}
	
	public static void main(String args[]) throws Exception {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Ola! seja bem vindo ao seu portal de seguradora! para criar sua seguradora, aperte '1'");
		int inicio = entrada.nextInt();
		do {
			System.out.println("Tente novamente");
			inicio = entrada.nextInt();
		}while(inicio != 1);
		Seguradora seguradora = criaSeguradora();
		System.out.println("Para adicionar um cliente pessoa fisica para sua seguradora, aperte '1', para adicionar um cliente pessoa juridica aperte '2' e para voltar aperte '3' ");
		int proximo = entrada.nextInt();
		Cliente cliente;
		switch(proximo) {
		case 1:
			cliente = criaClientePF(seguradora);
			if(cliente != null)
				break;
		case 2:
			cliente = criaClientePJ(seguradora);
			if(cliente != null)
				break;
		case 3:
			break;
		default:
			System.out.println("Tente novamente");
	}
		System.out.println("Para adicionar uma ocorrencia de Sinistro, aperte '1' e para voltar aperte '2' ");
		
		entrada.close();
}
}
