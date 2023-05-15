package lab04;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;


public class AppMain {
	private static void obrigacoesdolab() throws Exception {
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Seguradora seguradora = new Seguradora("seguro", "1111111", "aaaa@aaaaa.com", "rua dos bobos numero 0" );
		Date dataNascimento = dateformat.parse("13/10/2003");
		Date dataLicenca = dateformat.parse("13/10/2021");
		Date dataFundacao = dateformat.parse("17/09/2003");
		ClientePF clientepf = new ClientePF("11144477735", dataNascimento, "mabe", "rua aaaa", "feminino", "superior incompleto","pobre", dataLicenca );
		ClientePJ clientepj = new ClientePJ ("42.422.967/0001-01", dataFundacao, "minha empresa", "aaaaa", 10);
		Veiculo veiculopf = new Veiculo ("abc123","bmw", "mini cooper", 2023);
		Veiculo veiculopj = new Veiculo ("abc456","volks", "new beetle", 2023);
		clientepf.adicionaVeiculo(veiculopf);
		clientepj.adicionaVeiculo(veiculopj);
		seguradora.cadastrarCliente(clientepf);
		seguradora.cadastrarCliente(clientepj);
		Date dataSinistroPF = dateformat.parse("21/12/2023");
		Date dataSinistroPJ = dateformat.parse("16/05/2023");
		seguradora.gerarSinistro(dataSinistroPF, "rua aaaa", veiculopf, clientepf);
		seguradora.gerarSinistro(dataSinistroPJ, "rua bbbb", veiculopj, clientepj);
		clientepf.setValorSeguro(seguradora.calcularPrecoSeguroCliente(clientepf.getCPF())); // funcao da seguradora recalcular
		clientepj.setValorSeguro(seguradora.calcularPrecoSeguroCliente(clientepj.getCNPJ()));
		System.out.println(seguradora.listarClientes("PF"));
		seguradora.visualizarSinistro(clientepf.getCPF());
		System.out.println(seguradora.listarSinistros());
		System.out.println(seguradora.calcularReceita());
		}

		private static Seguradora criaSeguradora(Scanner entrada) {
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

		private static Veiculo criaVeiculo(Scanner entrada) {
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
		private static ClientePF criaClientePF(Seguradora seguradora, Scanner entrada) throws Exception {
			System.out.println("Qual o nome do seu cliente?");
			String nomeCliente = entrada.nextLine();
			nomeCliente = nomeCliente.replace("\n","");
			System.out.println("Qual o endereco do seu cliente?");
			String enderecoCliente = entrada.nextLine();
			enderecoCliente = enderecoCliente.replace("\n","");
			System.out.println("Qual o CPF dele?");
			String CPFCliente = entrada.nextLine();
			CPFCliente = CPFCliente.replace("\n","");
			while(!Validacao.validarCPF(CPFCliente)) {
				System.out.println("Cliente com CPF invalido! tente de novo");
			 	CPFCliente = entrada.nextLine();
				CPFCliente = CPFCliente.replace("\n","");
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

		private static ClientePJ criaClientePJ(Seguradora seguradora, Scanner entrada) throws Exception{
			System.out.println("Qual o nome do seu cliente?");
			String nomeCliente = entrada.nextLine();
			nomeCliente = nomeCliente.replace("\n","");
			System.out.println("Qual o endereco do seu cliente?");
			String enderecoCliente = entrada.nextLine();
			enderecoCliente = enderecoCliente.replace("\n","");
			System.out.println("Qual o CNPJ dele?");
			String CNPJCliente = entrada.nextLine();
			CNPJCliente = CNPJCliente.replace("\n","");
			if(!Validacao.validaCNPJ(CNPJCliente)) {
				System.out.println("Cliente com CNPJ invalido! tente cadastrar outro cliente");
				return null;
			}
			System.out.println("Quando a empresa foi fundada? (dd/MM/yyyy)");
			String dataFundacaoS = entrada.nextLine();
			dataFundacaoS = dataFundacaoS.replace("\n","");
			SimpleDateFormat dataformat = new SimpleDateFormat("dd/MM/yyyy");
			Date dataFundacao = dataformat.parse(dataFundacaoS);
			System.out.println("Quantos funcionarios tem seu cliente?");
			int func = entrada.nextInt();
			ClientePJ cliente = new ClientePJ (CNPJCliente, dataFundacao, nomeCliente, enderecoCliente, func);
			int cadastro;
			func = entrada.nextInt();
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

		private static Veiculo encontraCarro(Cliente cliente, String placa) {
			Veiculo veiculo = null;
			for(Veiculo valor: cliente.getMapaVeiculos().values()) {
				if(valor.getPlaca().equals(placa))
					veiculo = valor;
			}
			return veiculo;
		}


		private static void criaSinistro(Seguradora seguradora, Scanner entrada) throws Exception{ // ja tinha no lab 3
			boolean flag = false;
			System.out.println("Quando ocorreu o sinistro? (dd/MM/aaaa)");
			String data = entrada.nextLine();
			SimpleDateFormat dataformat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = dataformat.parse(data);
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
		 seguradora.gerarSinistro(date, endereco, veiculo, cliente);
		 System.out.println("Seu sinistro foi cadastrado com sucesso");
		}
	
		private static void excluir(int escolha, Scanner entrada, LinkedList<Seguradora> Seguradora) {
			MenuExcluir op = MenuExcluir.deIntpraEnum(escolha);
			switch (op) {
			case CLIENTE:
				break;
			case VEICULO:
				break;
			case SINISTRO:
				break;
			case VOLTAR:
				return;
			default:
				return;
			}
		}
	
		private static void listar(int escolha, Scanner entrada, LinkedList<Seguradora> listaseguradoras) {
			MenuListar op = MenuListar.deIntpraEnum(escolha);
			switch(op) {
			case CLIENTE:
				break;
			case SINISTRO_SEG:
				break;
			case SINISTRO_CLIENTE:
				break;
			case VEICULO_CLIENTE:
				break;
			case VEICULO_SEGURADORA:
				break;
			case VOLTAR:
				return;
			default:
				return;	
			}
		}

		private static void cadastros(int escolha, Scanner entrada, LinkedList<Seguradora> listaseguradoras) throws Exception{
			MenuCadastrar op = MenuCadastrar.deIntpraEnum(escolha);
			switch(op) {
			case CLIENTE:
				String tipo = null;
				if(listaseguradoras.size() == 0){
					System.out.println("Voce nao tem seguradoras, crie uma e volte aqui");
					break;
				}
				do{
				System.out.println("Qual tipo de cliente quer cadastrar(PF/PJ)?");
				tipo = entrada.nextLine();
				} while((!tipo.equals("PJ")) || (!tipo.equals("PF")));
				System.out.println("Digite o nome da seguradora que na qual quer cadastrar o cliente");
				String nomeS = entrada.nextLine();
				Seguradora SegS = null;
				SegS = Validacao.haSist(listaseguradoras, nomeS, SegS, entrada);
				if(SegS == null){
					return;
				}
				if(tipo.equals("PF")){
					criaClientePF(SegS, entrada);
					break;
				}
				else if(tipo.equals("PJ")){
					criaClientePJ(SegS, entrada);
					break;
				}
			case VEICULO:
				if(listaseguradoras.size() == 0){
					System.out.println("Voce nao tem seguradoras, crie uma e volte aqui");
					break;
				}
				System.out.println("Digite o CPF/CNPJ do cliente ao qual voce quer adicionar um veiculo");
				String id = entrada.nextLine();
				id = id.replaceAll("[^\\d]","");
				System.out.println("Digite o nome da seguradora em que esse cliente esta cadastrado: ");
				String seg = entrada.nextLine();
				Seguradora segs = null;
				segs = Validacao.haSist(listaseguradoras, seg, segs, entrada);
				if(segs == null){
					return;
				}
				Cliente c = Validacao.achaCliente(segs, id);
				Veiculo v =criaVeiculo(entrada);
				c.adicionaVeiculo(v);
				System.out.println("Veiculo adicionado com sucesso");
				segs.calcularPrecoSeguroCliente(id);
			case SEGURADORA: 
				Seguradora seguradora = criaSeguradora(entrada);
				listaseguradoras.add(seguradora);
				System.out.println("Seguradora cadastrada com sucesso");
			case VOLTAR:
				return;
			default:
				return;
			}
		}
	
	
		private static void MenuPrincipal(int escolha, Scanner entrada, LinkedList<Seguradora> listaseguradoras, int quit) throws Exception{ // tem q ta num while na main
			MenuOperacoes op = MenuOperacoes.deIntpraEnum(escolha);
			switch(op) {
			case CADASTRAR:
				System.out.println("Para cadastrar cliente aperte: 1\nPara cadastrar veiculo aperte: 2\nPara cadastrar seguradora aperte: 3\nPara voltar aperte: 4");
				escolha = entrada.nextInt();
				cadastros(escolha, entrada, listaseguradoras);
				break;
			case LISTAR:
				System.out.println("Para listar clientes aperte: 1\nPara listar sinistros por seguradora aperte: 2");
				System.out.println("Para listar sinistros por cliente aperte: 3\nPara listar veiculo por cliente aperte: 4\nPara listar veiculo por seguradora aperte: 5\nPara voltar aperte: 6");
				escolha = entrada.nextInt();
				listar(escolha, entrada, listaseguradoras);
				break;
			case EXCLUIR:  
				System.out.println("Para excluir cliente aperte: 1\nPara excluir veiculo aperte: 2\nPara excluir sinistro aperte: 3\nPara voltar aperte: 4");
				escolha = entrada.nextInt();
				excluir(escolha, entrada, listaseguradoras);
				break;
			case GERAR_SINISTRO:
				if (listaseguradoras.size() == 0){
					System.out.println("Voce ainda nao criou seguradora, crie uma e depois volte aqui");
					break;
				}
				System.out.println("Diga o nome da seguradora para o qual você quer gerar o sinistro: ");
				String seg = entrada.nextLine();
				Seguradora seguradora = null;
				seguradora = Validacao.haSist(listaseguradoras, seg, seguradora, entrada);
				if(seguradora == null){
					break;
				}
				criaSinistro(seguradora, entrada);
			case TRANSFERIR_SINISTRO:
				if(listaseguradoras.size() == 0){
					System.out.println("Voce ainda nao criou seguradoras, crie e volte aqui");
					break;
				}
				Seguradora s1 = null, s2 = null;
				System.out.println("Digite o nome da seguradora do cliente que atualmente possui o seguro: ");
				String nomeS1 = entrada.nextLine();
				s1 = Validacao.haSist(listaseguradoras, nomeS1, s1, entrada);
				if(s1 == null){
					break;
				}
				System.out.println("Digite o nome da seguradora do cliente para quem o seguro sera transferido: ");
				String nomeS2 = entrada.nextLine();
				s2 = Validacao.haSist(listaseguradoras, nomeS2, s2, entrada);
				if(s2 == null){
					break;
				}
				Cliente c1, c2;
				String id1, id2;
				System.out.println("Digite o CNPJ ou CPF do primeiro cliente:");
				id1 = entrada.nextLine();
				id1 = id1.replaceAll("[^\\d]","");
				c1 = Validacao.achaCliente(s1,id1);
				while(c1 == null){
					System.out.println("cliente nao encontrado, aperte 5 para tentar de novo e 6 para voltar");
					int ex = entrada.nextInt();
					if(ex == 5){
					id1 = entrada.nextLine();
					id1 = id1.replaceAll("[^\\d]","");
					c1 = Validacao.achaCliente(s1,id1);
					}
					else if(ex == 6){
						break;
					}
				}
				System.out.println("Digite o CNPJ ou CPF do segundo cliente:");
				id2 = entrada.nextLine();
				id2 = id2.replaceAll("[^\\d]","");
				c2 = Validacao.achaCliente(s2,id2);
				while(c2 == null){
					System.out.println("cliente nao encontrado, aperte 5 para tentar de novo e 6 para voltar");
					int ex = entrada.nextInt();
					if(ex == 5){
					id2 = entrada.nextLine();
					id2 = id2.replaceAll("[^\\d]","");
					c2 = Validacao.achaCliente(s2,id2);
					}
					else if(ex == 6){
						break;
					}
				}
				System.out.println("os precos antigos dos seguros dos clientes sao, respectivamente: " + c1.getValorSeguro() + " e " + c2.getValorSeguro() + " reais");
				c2.setMapaVeiculos(c1.getMapaVeiculos());
				s2.calcularPrecoSeguroCliente(id2);
				c1.getMapaVeiculos().clear();
				s1.calcularPrecoSeguroCliente(id1);
				System.out.println("os precos novos dos seguros dos clientes sao, respectivamente: " + c1.getValorSeguro() + " e " + c2.getValorSeguro() + " reais");

			case CALCULAR_RECEITA:
				if(listaseguradoras.size() == 0){
					System.out.println("Voce ainda nao criou seguradora, crie uma e volte aqui");
					break;
				}
				System.out.println("Digite o nome da seguradora cuja receita quer calcular:");
				String segura = entrada.nextLine();
				Seguradora s = null;
				s = Validacao.haSist(listaseguradoras, segura, s, entrada);
				if(s == null){
					break;
				}
				System.out.println("A receita da seguradora" + s.getNome() + "eh" + s.calcularReceita());
			case SAIR:
				quit = 1;
				return;
			default:
				break;
			}
		}
	
	
	public static void main(String args[]) throws Exception{
		obrigacoesdolab();
		int escolha, quit = 0;
		Scanner entrada = new Scanner(System.in);
		LinkedList<Seguradora> listaSeguradoras = new LinkedList<Seguradora>();	
		do{
		System.out.println("Ola! Bem vindo ao menu de seguradoras!\nPara cadastrar aperte: 1\nPara listar aperte: 2");
		System.out.println("Para excluir aperte: 3\nPara gerar sinistro aperte: 4\nPara transferir seguro aperte: 5\nPara sair aperte: 0");
		escolha = entrada.nextInt();
		MenuPrincipal(escolha,entrada,listaSeguradoras, quit);
		} while(quit != 1);
		entrada.close();
	}
}
