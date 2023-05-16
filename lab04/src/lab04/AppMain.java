package lab04;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.HashMap;



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
			String nomeSeguradora = entrada.nextLine(); // deixei ter numero aqui pq pode ser branding 
			System.out.println("E qual o endereco dela?");
			String enderecoSeguradora = entrada.nextLine();
			System.out.println("E o telefone?");
			String telefoneSeguradora = entrada.nextLine();
			boolean telefonepas = Validacao.validaTelefone(telefoneSeguradora);
			while(!telefonepas){
				System.out.println("telefone invalido digite novamente");
				telefoneSeguradora = entrada.nextLine();
				telefonepas = Validacao.validaTelefone(telefoneSeguradora);
			}
			System.out.println("Por ultimo, qual o email dela?");
			String emailSeguradora = entrada.nextLine();
// nao validei pq nao sei se arroba ta nos caracteres proibidos 
			Seguradora seguradora = new Seguradora(nomeSeguradora, telefoneSeguradora, emailSeguradora, enderecoSeguradora);
			return seguradora;
		}

		private static Veiculo criaVeiculo(Scanner entrada) {
			System.out.println("Qual a placa do veiculo?");
			String placa = entrada.nextLine();
			boolean placapas = Validacao.validaPlaca(placa);
			while(!placapas){
				System.out.println("Placa invalida digite novamente");
				placa = entrada.nextLine();
				placapas = Validacao.validaData(placa);
			}
			System.out.println("Qual o modelo dele?");
			String modelo = entrada.nextLine();
			System.out.println("Qual a marca dele?");
			String marca = entrada.nextLine();
			System.out.println("Em que ano ele foi fabricado?");
			int anoFabricacao = entrada.nextInt();
			entrada.nextLine();// come \n
			Veiculo veiculo = new Veiculo(placa, marca, modelo, anoFabricacao);
			return veiculo;
		}
		private static ClientePF criaClientePF(Seguradora seguradora, Scanner entrada) throws Exception {
			System.out.println("Qual o nome do seu cliente?");
			String nomeCliente = entrada.nextLine();
			boolean nomepas = Validacao.validaNome(nomeCliente);
			while(!nomepas){
				System.out.println("Nome invalido digite novamente");
				nomeCliente = entrada.nextLine();
				nomepas = Validacao.validaNome(nomeCliente);
			}
			System.out.println("Qual o endereco do seu cliente?");
			String enderecoCliente = entrada.nextLine();
			System.out.println("Qual o CPF dele?");
			String CPFCliente = entrada.nextLine();
			while(!Validacao.validarCPF(CPFCliente)) {
				System.out.println("Cliente com CPF invalido! tente de novo");
			 	CPFCliente = entrada.nextLine();
			}
			System.out.println("Qual o aniversario dele? (dd/MM/yyyy)");
			String dataNascimentoS = entrada.nextLine();
			boolean aniversariopas = Validacao.validaData(dataNascimentoS);
			while(!aniversariopas){
				System.out.println("Data invalida digite novamente");
				dataNascimentoS = entrada.nextLine();
				aniversariopas = Validacao.validaData(dataNascimentoS);
			}
			SimpleDateFormat dataformat = new SimpleDateFormat("dd/MM/yyyy");
			Date dataNascimento = dataformat.parse(dataNascimentoS);
			System.out.println("E quando ele tirou a licenca? (dd/MM/yyyy)");
			String dataLicencaS = entrada.nextLine();
			boolean licencapas = Validacao.validaData(dataLicencaS);
			while(!licencapas){
				System.out.println("Data invalida digite novamente");
				dataLicencaS = entrada.nextLine();
				licencapas = Validacao.validaData(dataLicencaS);
			}
			SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy"); // o chat gpt falou pra criar um novo cada vez que precisasse parse just to be safe 
			Date dataLicenca = dateformat.parse(dataLicencaS);
			System.out.println("Qual o genero do seu cliente?");
			String genero = entrada.nextLine();
			System.out.println("Qual o nivel de educacao do seu cliente?");
			String educacao  = entrada.nextLine();
			System.out.println("Qual a classe economica do seu cliente?");
			String economica = entrada.nextLine();
			ClientePF cliente = new ClientePF(CPFCliente, dataNascimento, nomeCliente, enderecoCliente, genero, educacao, economica, dataLicenca);
			int cadastro;
			boolean foi = seguradora.cadastrarCliente(cliente);
			do {
			System.out.println("Para cadastrar um novo veiculo para seu cliente, aperte '1', se estiver satisfeito, aperte '0'");
			cadastro = entrada.nextInt();
			entrada.nextLine();
			if(cadastro == 1) {
				Veiculo veiculo = criaVeiculo(entrada);
				cliente.adicionaVeiculo(veiculo);
				seguradora.calcularPrecoSeguroCliente(CPFCliente);
				}
			}while(cadastro != 0);
			if(foi){
				System.out.println("Seu cliente foi cadastrado na seguradora com sucesso");
				return cliente;
			}
			return cliente; // nunca vai chegar aqui nesse lab mas quem sabe no proximo
		}

		private static ClientePJ criaClientePJ(Seguradora seguradora, Scanner entrada) throws Exception{
			System.out.println("Qual o nome do seu cliente?"); // tb  nao to vlidando pq marca pode ter numero no nome 
			String nomeCliente = entrada.nextLine();
			System.out.println("Qual o endereco do seu cliente?");
			String enderecoCliente = entrada.nextLine();
			System.out.println("Qual o CNPJ dele?");
			String CNPJCliente = entrada.nextLine();
			if(!Validacao.validaCNPJ(CNPJCliente)) {
				System.out.println("Cliente com CNPJ invalido! tente cadastrar outro cliente");
				return null;
			}
			System.out.println("Quando a empresa foi fundada? (dd/MM/yyyy)");
			String dataFundacaoS = entrada.nextLine();
			boolean datapas = Validacao.validaData(dataFundacaoS);
			while(!datapas){
				System.out.println("Data invalida digite novamente");
				dataFundacaoS = entrada.nextLine();
				datapas = Validacao.validaData(dataFundacaoS);
			}
			SimpleDateFormat dataformat = new SimpleDateFormat("dd/MM/yyyy");
			Date dataFundacao = dataformat.parse(dataFundacaoS);
			System.out.println("Quantos funcionarios tem seu cliente?");
			int func = entrada.nextInt();
			entrada.nextLine();
			ClientePJ cliente = new ClientePJ (CNPJCliente, dataFundacao, nomeCliente, enderecoCliente, func);
			boolean foi = seguradora.cadastrarCliente(cliente);
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
					seguradora.calcularPrecoSeguroCliente(CNPJCliente);
				}
			}
			}while(cadastro != 0);
			if(foi){
				System.out.println("Seu cliente foi cadastrado na seguradora com sucesso");
				return cliente;
			}
			return cliente;
			}

		private static Veiculo encontraCarro(Cliente cliente, String placa) {
			Veiculo veiculo = null;
			for(Veiculo valor: cliente.getMapaVeiculos().values()) {
				if(valor.getPlaca().equals(placa.toUpperCase()))
					veiculo = valor;
			}
			return veiculo;
		}


		private static void criaSinistro(Seguradora seguradora, Scanner entrada) throws Exception{ // ja tinha no lab 3
			System.out.println("Quando ocorreu o sinistro? (dd/MM/aaaa)");
			String data = entrada.nextLine();
			boolean datapas = Validacao.validaData(data);
			while(!datapas){
				System.out.println("Data invalida digite novamente");
				data= entrada.nextLine();
				datapas = Validacao.validaData(data);
			}
			SimpleDateFormat dataformat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = dataformat.parse(data);
			System.out.println("Onde ocorreu o sinistro?");
			String endereco = entrada.nextLine();
			 // turns out que seguradoras cobrirem sinistros de quando os clientes nao eram clientes deles quebraria o modelo de negocios das seguradoras
			Cliente cliente = null;
			Veiculo veiculo = null;
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
			   if(cliente == null){
				System.out.println("Esse cliente nao pertence a essa seguradora, tente adiciona-lo a ela e depois gerar o sinistro");
				return;
			   }
			   else if(veiculo == null){
				System.out.println("Esse veiculo nao pertence a esse cliente, tente adiciona-lo a ele ou transferir o seguro e depois gerar o sinistro");
				return;
			   }
			   seguradora.gerarSinistro(date, endereco, veiculo, cliente);
			   if(cliente instanceof ClientePF){
				ClientePF novocliente = (ClientePF) cliente;
				seguradora.calcularPrecoSeguroCliente(novocliente.getCPF());
			   }
			   else if(cliente instanceof ClientePJ){
				ClientePJ novocliente = (ClientePJ) cliente;
				seguradora.calcularPrecoSeguroCliente(novocliente.getCNPJ());
			   }
			   
		 	System.out.println("Seu sinistro foi cadastrado com sucesso");
			}
			
		 
	
		private static void excluir(int escolha, Scanner entrada, LinkedList<Seguradora> listaSeguradora) throws Exception{
			if(listaSeguradora.isEmpty()) {
				System.out.println("Nao ha seguradoras, crie uma com seus respectivos clientes e sinistros e volte");
				return;
			}
			MenuExcluir op = MenuExcluir.deIntpraEnum(escolha);
			switch (op) {
			case CLIENTE:
				Seguradora seg = null;
				System.out.println("Digite o nome da seguradora a qual o cliente pertence");
				String nomeseg = entrada.nextLine();
				seg  = Validacao.haSist(listaSeguradora, nomeseg,  entrada);
				if(seg == null)
					return;
				System.out.println("Digite o CPF ou CNPJ do cliente");
				String id = entrada.nextLine();
				boolean rcliente = seg.removerCliente(id);
				if(rcliente == true)
					System.out.println("Cliente removido com sucesso");
				break;
			case VEICULO:
			System.out.println("Digite o nome da seguradora a qual o cliente pertence");
				Seguradora s = null;
				String nomes = entrada.nextLine();
				s  = Validacao.haSist(listaSeguradora, nomes, entrada);
				if(s == null)
					return;
				System.out.println("Digite o CPF ou CNPJ do cliente");
				String iden= entrada.nextLine();
				Cliente c = Validacao.achaCliente(s, iden);
				if(c == null){
					System.out.println("Esse cliente nao pertence a essa seguradora tente novamente");
					return;
				}
				System.out.println("Digite a placa do veiculo que quer remover:");
				String placa = entrada.nextLine();
				boolean removeu = c.removeVeiculo(placa.toUpperCase());
				if(removeu == false){
					System.out.println("nao foi possivel remover o veiculo pois o cliente nao o possuia");
					return;
				}
				else 
					s.calcularPrecoSeguroCliente(iden); 
					System.out.println("Veiculo removido com sucesso");
				break;
			case SINISTRO:
				int continua;
				Seguradora seguradora = null;
				System.out.println("Digite o nome da seguradora a qual o cliente dono do sinistro pertence: ");
				String segu = entrada.nextLine();
				seguradora = Validacao.haSist(listaSeguradora, segu, entrada);
				if(seguradora == null){
					return;
				}
				System.out.println("Digite o CPF/CNPJ do cliente cujo(s) sinistro(s) quer remover");
				String doc = entrada.nextLine();
				do{
				seguradora.visualizarSinistro(doc);
				System.out.println("Digite 5 para remover um desses sinistros e 6 para parar");
				continua = entrada.nextInt();
				entrada.nextLine();
				if(continua == 5){
					System.out.println("Digite a data em que ocorreu o sinistro (dd/MM/aaaa)");
					SimpleDateFormat dataformat = new SimpleDateFormat("dd/MM/yyyy");
					String data = entrada.nextLine();
					boolean datapas = Validacao.validaData(data);
					while(!datapas){
					System.out.println("Data invalida digite novamente");
					data = entrada.nextLine();
					datapas = Validacao.validaData(data);
			}
					Date datasin = dataformat.parse(data);
					seguradora.removeSinistro(doc, datasin);
				}
				}while(continua != 6);
				break;
			case VOLTAR:
				return;
			default:
				return;
			}
		}
	
		private static void listar(int escolha, Scanner entrada, LinkedList<Seguradora> listaseguradoras) {
			if(listaseguradoras.size() == 0){
				System.out.println("Voce ainda nao tem seguradoras cadastradas, tente cadastralas com clientes e sinistros e volte");
				return;
			}
			MenuListar op = MenuListar.deIntpraEnum(escolha);
			switch(op) {
			case CLIENTE:
				Seguradora seg = null;
				String tipo = null;
				System.out.println("Digite o nome da seguradora que voce quer listar os clientes");
				String s = entrada.nextLine();
				seg = Validacao.haSist(listaseguradoras, s,  entrada);
				if(seg == null)
					return;
				do{
					System.out.println("Digite o tipo de cliente que voce quer listar (PF/PJ)");
					tipo = entrada.nextLine();
				}while(!(tipo.toUpperCase().equals("PF")) && !(tipo.toUpperCase().equals("PJ")));
				System.out.println(seg.listarClientes(tipo.toUpperCase()));
				break;
			case SINISTRO_SEG:
				System.out.println("Digite o nome da seguradora que quer listar os sinistros");
				String nomes = entrada.nextLine();
				Seguradora segu = null;
				segu = Validacao.haSist(listaseguradoras, nomes, entrada);
				if(segu == null){
					return;
				}
				System.out.println("Todos os sinistros associados a essa seguradora sao:\n");
				System.out.println(segu.listarSinistros());
				break;
			case SINISTRO_CLIENTE:
				Seguradora segs = null;
				System.out.println("Digite o nome da seguradora a qual o cliente pertence");
				String nomeseg = entrada.nextLine();
				segs = Validacao.haSist(listaseguradoras, nomeseg, entrada);
				if(segs == null){
					return;
				}
				System.out.println("Digite o CPF/CNPJ da pessoa cujos sinistros quer listar");
				String id = entrada.nextLine();
				segs.visualizarSinistro(id);
				break;
			case VEICULO_CLIENTE:
				Cliente cliente = null;
				System.out.println("Digite o nome da seguradora a qual o cliente pertence");
				String n = entrada.nextLine();
				Seguradora seguradora = null;
				seguradora = Validacao.haSist(listaseguradoras, n,  entrada);
				if(seguradora == null){
					return;
				}
				System.out.println("Digite o CPF/ CNPJ do cliente");
				String iden = entrada.nextLine();
				cliente = Validacao.achaCliente(seguradora, iden);
				if(cliente == null){
					System.out.println("cliente nao pertence a seguradora, tente novamente");
					return;
				}
				System.out.println("Os veiculos pertencentes ao cliente sao: ");
				System.out.println(cliente.listarVeiculos());
				break;
			case VEICULO_SEGURADORA:
				System.out.println("Digite o nome da seguradora cujos veiculos quer ver");
				String nome = entrada.nextLine();
				Seguradora sees = null;
				sees = Validacao.haSist(listaseguradoras, nome,  entrada);
				if(sees == null){
					return;
				}
				System.out.println("Os veiculos associados a essa seguradora sao: ");
				System.out.println(sees.listarVeiculos());
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
				} while(!(tipo.toUpperCase().equals("PJ")) && !(tipo.toUpperCase().equals("PF")));
				System.out.println("Digite o nome da seguradora que na qual quer cadastrar o cliente");
				String nomeS = entrada.nextLine();
				Seguradora SegS = null;
				SegS = Validacao.haSist(listaseguradoras, nomeS, entrada); 
				if(SegS == null){
					return;
				}
				if(tipo.toUpperCase().equals("PF")){
					criaClientePF(SegS, entrada);
					break;
				}
				else if(tipo.toUpperCase().equals("PJ")){
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
				segs = Validacao.haSist(listaseguradoras, seg, entrada);
				if(segs == null){
					return;
				}
				Cliente c = Validacao.achaCliente(segs, id);
				Veiculo v =criaVeiculo(entrada);
				c.adicionaVeiculo(v);
				System.out.println("Veiculo adicionado com sucesso");
				segs.calcularPrecoSeguroCliente(id);
				break;
			case SEGURADORA: 
				Seguradora seguradora = criaSeguradora(entrada);
				listaseguradoras.add(seguradora);
				System.out.println("Seguradora cadastrada com sucesso");
				break;
			case VOLTAR:
				return;
			default:
				return;
			}
		}
	
	
		private static int MenuPrincipal(int escolha, Scanner entrada, LinkedList<Seguradora> listaseguradoras) throws Exception{ // tem q ta num while na main
			MenuOperacoes op = MenuOperacoes.deIntpraEnum(escolha);
			switch(op) {
			case CADASTRAR:
				System.out.println("Para cadastrar cliente aperte: 1\nPara cadastrar veiculo aperte: 2\nPara cadastrar seguradora aperte: 3\nPara voltar aperte: 4");
				escolha = entrada.nextInt();
				entrada.nextLine();
				cadastros(escolha, entrada, listaseguradoras);
				break;
			case LISTAR:
				System.out.println("Para listar clientes aperte: 1\nPara listar sinistros por seguradora aperte: 2");
				System.out.println("Para listar sinistros por cliente aperte: 3\nPara listar veiculo por cliente aperte: 4\nPara listar veiculo por seguradora aperte: 5\nPara voltar aperte: 6");
				escolha = entrada.nextInt();
				entrada.nextLine();
				listar(escolha, entrada, listaseguradoras);
				break;
			case EXCLUIR:  
				System.out.println("Para excluir cliente aperte: 1\nPara excluir veiculo aperte: 2\nPara excluir sinistro aperte: 3\nPara voltar aperte: 4");
				escolha = entrada.nextInt();
				entrada.nextLine();
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
				seguradora = Validacao.haSist(listaseguradoras, seg, entrada);
				if(seguradora == null){
					break;
				}
				criaSinistro(seguradora, entrada);
				break;
			case TRANSFERIR_SINISTRO:
				if(listaseguradoras.size() == 0){
					System.out.println("Voce ainda nao criou seguradoras, crie e volte aqui");
					break;
				}
				Seguradora s1 = null, s2 = null;
				System.out.println("Digite o nome da seguradora do cliente que atualmente possui o seguro: ");
				String nomeS1 = entrada.nextLine();
				s1 = Validacao.haSist(listaseguradoras, nomeS1, entrada);
				if(s1 == null){
					break;
				}
				System.out.println("Digite o nome da seguradora do cliente para quem o seguro sera transferido: ");
				String nomeS2 = entrada.nextLine();
				s2 = Validacao.haSist(listaseguradoras, nomeS2, entrada);
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
					entrada.nextLine();
					if(ex == 5){
					id1 = entrada.nextLine();
					id1 = id1.replaceAll("[^\\d]","");
					c1 = Validacao.achaCliente(s1,id1);
					}
					else if(ex == 6){
						return 0;
					}
				}
				System.out.println("Digite o CNPJ ou CPF do segundo cliente:");
				id2 = entrada.nextLine();
				id2 = id2.replaceAll("[^\\d]","");
				c2 = Validacao.achaCliente(s2,id2);
				while(c2 == null){
					System.out.println("cliente nao encontrado, aperte 5 para tentar de novo e 6 para voltar");
					int ex = entrada.nextInt();
					entrada.nextLine();
					if(ex == 5){
					System.out.println("tente de novo: ");
					id2 = entrada.nextLine();
					id2 = id2.replaceAll("[^\\d]","");
					c2 = Validacao.achaCliente(s2,id2);
					}
					else if(ex == 6){
						return 0;
					}
				}
				HashMap <String, Veiculo> recebe = c1.getMapaVeiculos();
				System.out.println("os precos antigos dos seguros dos clientes sao, respectivamente: " + c1.getValorSeguro() + " e " + c2.getValorSeguro() + " reais");
				c2.getMapaVeiculos().putAll(recebe);
				s2.calcularPrecoSeguroCliente(id2);
				c1.getMapaVeiculos().clear();
				s1.calcularPrecoSeguroCliente(id1);
				System.out.println("os precos novos dos seguros dos clientes sao, respectivamente: " + c1.getValorSeguro() + " e " + c2.getValorSeguro() + " reais");
				break;
			case CALCULAR_RECEITA:
				if(listaseguradoras.size() == 0){
					System.out.println("Voce ainda nao criou seguradora, crie uma e volte aqui");
					break;
				}
				System.out.println("Digite o nome da seguradora cuja receita quer calcular:");
				String segura = entrada.nextLine();
				Seguradora s = null;
				s = Validacao.haSist(listaseguradoras, segura, entrada);
				if(s == null){
					break;
				}
				System.out.println("A receita da seguradora "  + s.getNome() + " eh " + s.calcularReceita());
				break;
			case SAIR:
				return 1;
			default:
				break;
			}
			return 0;
		}
	
	
	public static void main(String args[]) throws Exception{
		obrigacoesdolab();
		int escolha;
		Integer quit = 0;
		Scanner entrada = new Scanner(System.in);
		LinkedList<Seguradora> listaSeguradoras = new LinkedList<Seguradora>();	
		do{
		System.out.println("Ola! Bem vindo ao menu de seguradoras!\nPara cadastrar aperte: 1\nPara listar aperte: 2");
		System.out.println("Para excluir aperte: 3\nPara gerar sinistro aperte: 4\nPara transferir seguro aperte: 5\nPara sair aperte: 0");
		escolha = entrada.nextInt();
		entrada.nextLine();
		quit = MenuPrincipal(escolha,entrada,listaSeguradoras);
		} while(quit != 1);
		entrada.close();
		System.out.println("Obrigada por utilizar o menu!"); 
		
	}
}
