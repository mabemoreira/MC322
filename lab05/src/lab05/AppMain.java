package lab05;
import java.util.LinkedList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;


public class AppMain { // tentei reciclar o que dava do lab4
	private static void obrigacoesdolab(){
		Seguradora seguradora = new Seguradora("66.070.178/0001-17", "minha seguradora", "111111", "meuemailaaa", "rua b");
		LocalDate aniversario = LocalDate.of(2003, 10, 13);
		ClientePF eu = new ClientePF("mabe", "aaaa", "22222", "meu outro email","11111","feminino","insuficiente",aniversario);
		seguradora.cadastrarCliente(eu);
		Veiculo carro = new Veiculo("abc123", "toyota", "corolla", 2023);
		eu.cadastrarVeiculo(carro);
		LocalDate inicio = LocalDate.of(2023, 10, 13);
		LocalDate fim = LocalDate.of(2026, 10, 13);
		seguradora.gerarSeguro(inicio, fim, eu, carro);
		LocalDate fund = LocalDate.of(1953, 06, 07);
		ClientePJ aaa = new ClientePJ ("01.587.123/0001-33",fund, "mmaaa", "bbbb", "707070", "aaaaaa");
		Frota frota = new Frota("oie");
		aaa.cadastrarFrota(frota);
		LinkedList<Seguro> listaseg = seguradora.getSegurosPorCliente("11111", "PF");
		SeguroPF seguropf = (SeguroPF) listaseg.get(0);
		Condutor cond = new Condutor("055.668.83095", "aaaaa", "aaaa", "aaaaa", "aaaa", fund);
		seguropf.autorizarCondutor(cond);
		System.out.println(seguropf.listarCondutores());
		seguradora.calcularReceita();
		seguropf.gerarSinistro(fund, new Condutor("777.016.050-29", "cccc", "33333", "aaaa", "dddd", fund), "bbbb");
		seguropf.corrigeValor(); // just to be safe 
		seguradora.calcularReceita();
		System.out.println(seguradora);
		seguropf.desautorizarCondutor("055.668.83095");
		seguropf.corrigeValor();
		seguradora.calcularReceita();
		System.out.println(seguradora);
		seguradora.cadastrarCliente(aaa);
		seguradora.gerarSeguro(inicio, fim, frota, aaa);
		Veiculo novocarro = new Veiculo("bc", "aaa", "cc", 2023);
		aaa.atualizarFrota(novocarro, "oie");
		LinkedList<Seguro> listasegu = seguradora.getSegurosPorCliente("01.587.123/0001-33", "PJ");
		SeguroPJ seguropj = (SeguroPJ) listasegu.get(0);
		seguropj.gerarSinistro(fund, cond, "aaaaaaa");
		System.out.println(seguradora);

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
			System.out.println("Qual o email dela?");
			String emailSeguradora = entrada.nextLine();
			System.out.println("Por ultimo, qual o cnpj dela?");
			String cnpj = entrada.nextLine();
			while(!Validacao.validaCNPJ(cnpj)){
				System.out.println("CNPJ invalido digite outro");
				cnpj = entrada.nextLine();
			}
			Seguradora seguradora = new Seguradora(cnpj, nomeSeguradora, telefoneSeguradora, emailSeguradora, enderecoSeguradora);
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
			int anoFabricacao = 0;
			String anoFabricacaoS = entrada.nextLine();
			boolean escolhavalida = false;
			while(!escolhavalida){
			try{
				anoFabricacao = Integer.parseInt(anoFabricacaoS);
				escolhavalida = true;
			} 
			catch(NumberFormatException e){
				System.out.println("Entrada invalida, por favor digite um numero:");
				anoFabricacaoS = entrada.nextLine();
			}
			}
			Veiculo veiculo = new Veiculo(placa, marca, modelo, anoFabricacao);
			return veiculo;
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
			if(seguradora.getMapaClientes().containsKey(CNPJCliente.replaceAll("[^\\d]",""))){
				System.out.println("Esse cliente já está cadastrado nessa seguradora, tente cadastrar outro cliente ou cadastra lo em outra");
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
			DateTimeFormatter dataformat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate dataFundacao = LocalDate.parse(dataFundacaoS, dataformat);
			System.out.println("Qual o email do seu cliente?");
			String email = entrada.nextLine();
			System.out.println("Qual o telefone do seu cliente?");
			String telefone = entrada.nextLine();
			while(!Validacao.validaTelefone(telefone)){
				System.out.println("telefone invalido digite outro");
				telefone = entrada.nextLine();
			}
			ClientePJ cliente = new ClientePJ (CNPJCliente, dataFundacao, nomeCliente, enderecoCliente, telefone, email);
			boolean foi = seguradora.cadastrarCliente(cliente);
			// ver se quero deixar cadastrar frota ja aqui
			if(foi){
				System.out.println("Seu cliente foi cadastrado na seguradora com sucesso");
				return cliente;
			}
			return cliente;
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
			if(seguradora.getMapaClientes().containsKey(CPFCliente.replaceAll("[^\\d]",""))){
				System.out.println("Esse cliente já está cadastrado nessa seguradora, tente cadastrar outro cliente ou cadastra lo em outra");
				return null;
			}
			System.out.println("Qual o aniversario dele? (dd/MM/yyyy)");
			String dataNascimentoS = entrada.nextLine();
			boolean aniversariopas = Validacao.validaData(dataNascimentoS);
			while(!aniversariopas){
				System.out.println("Data invalida digite novamente");
				dataNascimentoS = entrada.nextLine();
				aniversariopas = Validacao.validaData(dataNascimentoS);
			}
			DateTimeFormatter dataformat =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate dataNascimento = LocalDate.parse(dataNascimentoS,dataformat);
			System.out.println("E qual o email dele?");
			String email = entrada.nextLine();
			System.out.println("Qual o genero do seu cliente?");
			String genero = entrada.nextLine();
			System.out.println("Qual o nivel de educacao do seu cliente?");
			String educacao  = entrada.nextLine();
			System.out.println("Qual o telefone do seu cliente?");
			String telefone = entrada.nextLine();
			while(!Validacao.validaTelefone(telefone)){
				System.out.println("telefone invalido digite outro");
				telefone = entrada.nextLine();
			}
			ClientePF cliente = new ClientePF(nomeCliente, enderecoCliente, telefone, email, CPFCliente, genero, educacao, dataNascimento);
			int cadastro;
			boolean foi = seguradora.cadastrarCliente(cliente);
			do {
			System.out.println("Para cadastrar um novo veiculo para seu cliente, aperte '1', se estiver satisfeito, aperte '0'");
			cadastro = 0;
			String cadastroS = entrada.nextLine();
			boolean escolhavalida = false;
				while(!escolhavalida){
				try{
					cadastro = Integer.parseInt(cadastroS);
					escolhavalida = true;
				} 
				catch(NumberFormatException e){
					System.out.println("Entrada invalida, por favor digite um numero:");
					cadastroS = entrada.nextLine();
				}
				}
			if(cadastro == 1) {
				Veiculo veiculo = criaVeiculo(entrada);
				cliente.cadastrarVeiculo(veiculo);
				}
			}while(cadastro != 0);
			if(foi){
				System.out.println("Seu cliente foi cadastrado na seguradora com sucesso");
				return cliente;
			}
			return cliente; // nunca vai chegar aqui nesse lab mas quem sabe no proximo
		}


		private static Condutor criaCondutor(Scanner entrada){
			System.out.println("Qual o nome do seu condutor?");
			String nome = entrada.nextLine();
			while(!Validacao.validaNome(nome)){
				System.out.println("nome invalido tente novamente");
				nome = entrada.nextLine();
			}
			System.out.println("Qual o telefone dele?");
			String telefone = entrada.nextLine();
			while(!Validacao.validaTelefone(telefone)){
				System.out.println("telefone invalido tente novamente");
				telefone = entrada.nextLine();
			}
			System.out.println("Qual o endereco do condutor?");
			String endereco = entrada.nextLine();
			System.out.println("Qual o email dele?");
			String email = entrada.nextLine();
			System.out.println("Qual o cpf dele?");
			String cpf = entrada.nextLine();
			while(!Validacao.validarCPF(cpf)){
				System.out.println("Cpf invalido digite outro");
				cpf = entrada.nextLine();
			}
			System.out.println("Digite o aniversario do condutor (dd/mm/aaaa)");
			String aniversario = entrada.nextLine();
			while(!Validacao.validaData(aniversario)){
				System.out.println("Data invalida digite outra");
				aniversario = entrada.nextLine();
			}
			DateTimeFormatter dataformat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate data = LocalDate.parse(aniversario,dataformat);
			Condutor cond = new Condutor(cpf, nome, telefone, endereco, email, data);
			return cond;

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
				System.out.println("Digite o cnpj da seguradora que na qual quer cadastrar o cliente");
				String cnpjS = entrada.nextLine();
				while(!Validacao.validaCNPJ(cnpjS)){
					System.out.println("CNPJ invalido digite outro");
					cnpjS = entrada.nextLine();
				}
				Seguradora SegS = null;
				SegS = Validacao.haSist(listaseguradoras, cnpjS, entrada); 
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
				System.out.println("Digite o CPF do cliente ao qual voce quer adicionar um veiculo");
				String id = entrada.nextLine();
				id = id.replaceAll("[^\\d]","");
				System.out.println("Digite o cnpj da seguradora em que esse cliente esta cadastrado: ");
				String cnpString = entrada.nextLine();
				while(!Validacao.validaCNPJ(cnpString)){
					System.out.println("CNPJ invalido digite outro");
					cnpString = entrada.nextLine();
				}
				Seguradora segs = null;
				segs = Validacao.haSist(listaseguradoras, cnpString, entrada);
				if(segs == null){
					return;
				}
				if(!Validacao.temPF(segs)){
					System.out.println("Essa eh uma funcao exclusiva de clientes PF crie um e volte aqui");
					return;
				}
				Cliente c = Validacao.achaCliente(segs, id);
				ClientePF cLPF = (ClientePF) c;
				Veiculo v =criaVeiculo(entrada);
				cLPF.cadastrarVeiculo(v);
				System.out.println("Veiculo adicionado com sucesso");
				break;
			case SEGURADORA: 
				Seguradora seguradora = criaSeguradora(entrada);
				listaseguradoras.add(seguradora);
				System.out.println("Seguradora cadastrada com sucesso");
				break;

			case FROTA:
				if(listaseguradoras.size() == 0){
					System.out.println("Voce nao possui seguradoras cadastre uma e volte");
					return;
				}
				System.out.println("Digite o cnpj da seguradora em que o cliente em que voce quer cadastrar a frota esta cadastrado");
				String cnpS = entrada.nextLine();
				while(!Validacao.validaCNPJ(cnpS)){
					System.out.println("cnpj invalido tente novamente");
					cnpS = entrada.nextLine();
				}
				Seguradora seg = Validacao.haSist(listaseguradoras, cnpS, entrada);
				if(seg == null){
					return;
				}
				if(!Validacao.temPJ(seg)){
					System.out.println(" essa funcao requer um cliente pj, cadastre-o e volte aqui");
					return;
				}
				System.out.println("Digite o cnpj do cliente no qual vc quer cadastrar a frota");
				String cnpcliente = entrada.nextLine();
				while(!Validacao.validaCNPJ(cnpcliente)){
					System.out.println("cnpj invalido tente novamente");
					cnpcliente = entrada.nextLine();
				}
				Cliente cl = Validacao.achaCliente(seg, cnpcliente);
				ClientePJ pj = (ClientePJ) cl;
				System.out.println("Digite o codigo da frota (case sensitive)");
				String code = entrada.nextLine();
				pj.cadastrarFrota(new Frota(code));
				break;
			case UMVEICULOFROTA:
				if(listaseguradoras.size() == 0){
					System.out.println("Voce nao possui seguradoras cadastre uma e volte");
					return;
				}
				System.out.println("Digite o cnpj da seguradora em que o cliente em que voce quer atualizar a frota esta cadastrado");
				String cnpjSt= entrada.nextLine();
				while(!Validacao.validaCNPJ(cnpjSt)){
					System.out.println("cnpj invalido tente novamente");
					cnpjSt = entrada.nextLine();
				}
				Seguradora segf = Validacao.haSist(listaseguradoras, cnpjSt, entrada);
				if(segf == null){
					return;
				}
				if(!Validacao.temPJ(segf)){
					System.out.println(" essa funcao requer um cliente pj, cadastre-o e volte aqui");
					return;
				}
				System.out.println("Digite o cnpj do cliente no qual vc quer cadastrar o veiculo na frota");
				String cnpjcliente = entrada.nextLine();
				while(!Validacao.validaCNPJ(cnpjcliente)){
					System.out.println("cnpj invalido tente novamente");
					cnpjcliente = entrada.nextLine();
				}
				Cliente cli = Validacao.achaCliente(segf, cnpjcliente);
				ClientePJ pjc = (ClientePJ) cli;
				System.out.println("Digite o codigo da frota (case sensitive)");
				String codev = entrada.nextLine();
				Veiculo vei = criaVeiculo(entrada);
				pjc.atualizarFrota(vei, codev);
				break;
			case CONDUTOR:
				if(listaseguradoras.size() == 0){
					System.out.println("Voce ainda nao tem seguradoras cadastradas cadastre uma e volte aqui");
					return;
				}
				System.out.println("Digite o cnpj da seguradora que contem o seguro no qual vc quer adicionar o condutor");
				String cnpString2 = entrada.nextLine();
				while(!Validacao.validaCNPJ(cnpString2)){
					System.out.println("cnpj invalido tente novamente");
					cnpString2 = entrada.nextLine();
				}
				Seguradora sis = Validacao.haSist(listaseguradoras, cnpString2, entrada);
				if(sis == null){
					return;
				}
				if(sis.getMapaSeguro().isEmpty()){
					System.out.println("Essa seguradora nao possui seguros cadastre um e volte");
					return;
				}
				System.out.println("Digite o tipo de seguro (pf/pj)");
				String tipoS = entrada.nextLine();
				while((!tipoS.toUpperCase().equals("PJ")) &&(! tipoS.toUpperCase().equals("PF"))){
					System.out.println("tipo invalido tente novamente: ");
					tipoS = entrada.nextLine();
				}
				if(tipoS.toUpperCase().equals("PF")){
					if(!Validacao.temPF(sis)){
						System.out.println("voce nao possui clientes pf");
						return;
					}
					System.out.println("Digite o cpf do seu cliente");
					String cpfString = entrada.nextLine();
					while(!Validacao.validarCPF(cpfString)){
						System.out.println("cpf invalido tente novamente:");
						cpfString = entrada.nextLine();
					}
					Cliente clientes = Validacao.achaCliente(sis, cpfString);
					if(clientes == null){
						System.out.println("cliente nao cadastrado nessa seguradora");
						return;
					}
					ClientePF clientesPf = (ClientePF) clientes;
					System.out.println("Digite a placa do veiculo assegurado");
					String pla = entrada.nextLine();
					if(!Validacao.validaPlaca(pla)){
						System.out.println("placa invalida tente novamente:");
						pla = entrada.nextLine();
					}
					Veiculo carroC = Validacao.encontraCarro(clientesPf, pla);
					if(carroC == null){
						System.out.println("Veiculo nao cadastrado a esse cliente");
						return;
					}
					SeguroPF segPF = Validacao.achaSeguroPF(sis, carroC);
					if(segPF == null){
						System.out.println("Esse veiculo nao eh assegurado");
						return;
					}
					Condutor cond = criaCondutor(entrada);
					segPF.autorizarCondutor(cond);
				}
				else if(tipoS.toUpperCase().equals("PJ")){
					if(!Validacao.temPJ(sis)){
						System.out.println("voce nao possui clientes pj");
						return;
					}
					System.out.println("Digite o cnpj do seu cliente");
					String cnpjString = entrada.nextLine();
					while(!Validacao.validaCNPJ(cnpjString)){
						System.out.println("cnpj invalido tente novamente:");
						cnpjString = entrada.nextLine();
					}
					Cliente clientepJ = Validacao.achaCliente(sis, cnpjString);
					if(clientepJ == null){
						System.out.println("cliente nao cadastrado nessa seguradora");
						return;
					}
					ClientePJ clientesPj = (ClientePJ) clientepJ;
					System.out.println("Digite o codigo da frota assegurada (case sensitive)");
					String co = entrada.nextLine();
					Frota fr = Validacao.achaFrotaCliente(clientesPj, co);
					if(fr == null){
						System.out.println("frota nao cadastrada");
						return;
					}
					SeguroPJ segPJ = Validacao.achaSeguroPJ(sis, fr);
					if(segPJ == null){
						System.out.println("frota nao assegurada");
						return;
					}
					Condutor condPJ = criaCondutor(entrada);
					segPJ.autorizarCondutor(condPJ);
				}
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
				System.out.println("Digite o cnpj da seguradora que voce quer listar os clientes");
				String cnpj = entrada.nextLine();
				while(!Validacao.validaCNPJ(cnpj)){
					System.out.println("CNPJ invalido digite outro");
					cnpj = entrada.nextLine();
				}
				seg = Validacao.haSist(listaseguradoras, cnpj,  entrada);
				if(seg == null)
					return;
				do{
					System.out.println("Digite o tipo de cliente que voce quer listar (PF/PJ)");
					tipo = entrada.nextLine();
				}while(!(tipo.toUpperCase().equals("PF")) && !(tipo.toUpperCase().equals("PJ")));
				System.out.println(seg.listarClientes(tipo.toUpperCase()));
				break;
			case SINISTRO_SEG:
				System.out.println("Digite o cnpj da seguradora que quer listar os sinistros");
				String cnpjs = entrada.nextLine();
				while(!Validacao.validaCNPJ(cnpjs)){
					System.out.println("CNPJ invalido digite outro");
					cnpjs = entrada.nextLine();
				}
				Seguradora segu = null;
				segu = Validacao.haSist(listaseguradoras, cnpjs, entrada);
				if(segu == null){
					return;
				}
				System.out.println("Todos os sinistros associados a essa seguradora sao:\n");
				System.out.println(segu.listarSinistros());
				break;
			case SINISTRO_CLIENTE:
				Seguradora segs = null;
				System.out.println("Digite o cnpj da seguradora a qual o cliente pertence");
				String cnpjseg = entrada.nextLine();
				while(!Validacao.validaCNPJ(cnpjseg)){
					System.out.println("CNPJ invalido digite outro");
					cnpjseg = entrada.nextLine();
				}
				segs = Validacao.haSist(listaseguradoras, cnpjseg, entrada);
				if(segs == null){
					return;
				}
				System.out.println("Digite o CPF/CNPJ da pessoa cujos sinistros quer listar");
				String id = entrada.nextLine();
				segs.listarSinistrosporCliente(id);
				break;
			case VEICULO_CLIENTE:
				Cliente cliente = null;
				System.out.println("Digite o cnpj da seguradora a qual o cliente pertence");
				String cn = entrada.nextLine();
				while(!Validacao.validaCNPJ(cn)){
					System.out.println("CNPJ invalido digite outro");
					cn = entrada.nextLine();
				}
				Seguradora seguradora = null;
				seguradora = Validacao.haSist(listaseguradoras, cn,  entrada);
				if(seguradora == null){
					return;
				}
				System.out.println("Digite o CPF do cliente");
				String iden = entrada.nextLine();
				cliente = Validacao.achaCliente(seguradora, iden);
				ClientePF clientepf = (ClientePF) cliente;
				if(cliente == null){
					System.out.println("cliente nao pertence a seguradora, tente novamente");
					return;
				}
				System.out.println("Os veiculos pertencentes ao cliente sao: ");
				System.out.println(clientepf.listarVeiculos());
				break;
			case SEGUROS_SEG:
				System.out.println("Digite o cnpj da seguradora da qual vc quer listar os seguros");
				String cnpjSeg = entrada.nextLine();
				while(!Validacao.validaCNPJ(cnpjSeg)){
					System.out.println("cnpj invalido tente novamente");
					cnpjSeg = entrada.nextLine();
				}
				Seguradora Segseg = Validacao.haSist(listaseguradoras, cnpjSeg, entrada);
				if(Segseg == null){
					return;
				}
				System.out.println("Os seguros cadastrados nessa seguradora sao:");
				System.out.println(Segseg.listarSeguros());
				break;
			case VEICULO_FROTA:
				System.out.println("Digite o cnpj da seguradora na qual o cliente dono da frota esta cadastrado");
				String cnpjSEg = entrada.nextLine();
				while(!Validacao.validaCNPJ(cnpjSEg)){
					System.out.println("cnpj invalido tente novamente");
					cnpjSEg = entrada.nextLine();
				}
				Seguradora SegF = Validacao.haSist(listaseguradoras, cnpjSEg, entrada);
				if(SegF == null){
					return;
				}
				if(!Validacao.temPJ(SegF)){
					System.out.println("sua seguradora nao possui clientes PJ");
					return;
				}
				System.out.println("Digite o cnpj do cliente");
				String cnpjCl = entrada.nextLine();
				while(!Validacao.validaCNPJ(cnpjCl)){
					System.out.println("cnpj invalido digite outro:");
					cnpjCl = entrada.nextLine();
				}
				Cliente c = Validacao.achaCliente(SegF,cnpjCl);
				ClientePJ clientePJ = (ClientePJ) c;
				System.out.println("Digite o codigo da frota que vc quer cadastrar o veiculo");
				String cd = entrada.nextLine();
				Frota fr = Validacao.achaFrotaCliente(clientePJ, cd);
				if(fr == null){
					System.out.println("frota nao cadastrada");
					return;
				}
				System.out.println("Os veiculos cadastrados nessa frota sao:");
				System.out.println(fr.listarVeiculos());
				break;
			case SEGUROS_CLIENTE:
				System.out.println("Digite o cnpj da seguradora na qual o cliente dono do seguro esta cadastrado");
				String cnpjSEG = entrada.nextLine();
				while(!Validacao.validaCNPJ(cnpjSEG)){
					System.out.println("cnpj invalido tente novamente");
					cnpjSEG = entrada.nextLine();
				}
				Seguradora SegC = Validacao.haSist(listaseguradoras, cnpjSEG, entrada);
				if(SegC == null){
					return;
				}
				if(SegC.getMapaClientes().isEmpty()){
					System.out.println("sua seguradora nao possui clientes");
					return;
				}
				System.out.println("Digite o cpf ou cnpj do cliente do qual quer listar os seguros");
				String idC = entrada.nextLine();
				if((!Validacao.validaCNPJ(idC)) && (!Validacao.validarCPF(idC))){
					System.out.println("Identificacao invalida digite novamente");
					idC = entrada.nextLine();
				}
				Cliente cliente2 = Validacao.achaCliente(SegC, idC);
				if(cliente2 == null){
					System.out.println("Cliente nao cadastrado na seguradora");
					return;
				}
				System.out.println("Digite o tipo do cliente (pf/pj)");
				String tipoS = entrada.nextLine();
				while((!tipoS.toUpperCase().equals("PF")) && (!tipoS.toUpperCase().equals("PJ"))){
					System.out.println("tipo invalido tente novamente");
					tipoS = entrada.nextLine();
				}
				System.out.println("Os seguros associados a esse cliente sao");
				System.out.println(SegC.listaSegporCliente(idC, tipoS));
				break;
			case FROTA_CLIENTE:
				System.out.println("Digite o cnpj da seguradora na qual o cliente dono da frota esta cadastrado");
				String cnpJSEG = entrada.nextLine();
				while(!Validacao.validaCNPJ(cnpJSEG)){
					System.out.println("cnpj invalido tente novamente");
					cnpJSEG = entrada.nextLine();
				}
				Seguradora SeGf = Validacao.haSist(listaseguradoras, cnpJSEG, entrada);
				if(SeGf == null){
					return;
				}
				if((!Validacao.temPJ(SeGf))){
					System.out.println("sua seguradora nao possui clientes PJ");
					return;
				}
				System.out.println("Digite o cnpj do cliente cujas frotas quer listar");
				String CnPjC = entrada.nextLine();
				while(!Validacao.validaCNPJ(CnPjC)){
					System.out.println("cnpj invalido tente novamente");
					CnPjC = entrada.nextLine();
				}
				Cliente cliente3 = Validacao.achaCliente(SeGf, CnPjC);
				if(cliente3 == null){
					System.out.println("Cliente nao cadastrado nessa seguradora");
					return;
				}
				ClientePJ clientePJ2 = (ClientePJ) cliente3;
				System.out.println("As frotas cadastradas para esse cliente sao");
				System.out.println(clientePJ2.listarFrotas()); 
				break;
			case CONDUTOR_SEG:
				System.out.println("Digite o cnpj da seguradora na qual o cliente dono da frota esta cadastrado");
				String cnPJSEG = entrada.nextLine();
				while(!Validacao.validaCNPJ(cnPJSEG)){
					System.out.println("cnpj invalido tente novamente");
					cnPJSEG = entrada.nextLine();
				}
				Seguradora SeGC = Validacao.haSist(listaseguradoras, cnPJSEG, entrada);
				if(SeGC == null){
					return;
				}
				if(SeGC.getMapaSeguro().isEmpty()){
					System.out.println("sua seguradora nao possui seguros");
					return;
				}
				System.out.println("Digite o tipo do seguro do cliente (PF/PJ)");
				String tIpo = entrada.nextLine();
				while((!tIpo.toUpperCase().equals("PF")) && (!tIpo.toUpperCase().equals("PJ"))){
					System.out.println("tipo invalido tente novamente");
					tIpo = entrada.nextLine();
				}
				if(tIpo.toUpperCase().equals("PF")){
					System.out.println("Digite o cpf do cliente dono do seguro");
					String cPf = entrada.nextLine();
					while(!Validacao.validarCPF(cPf)){
						System.out.println("cpf invalido tente novamente");
						cPf = entrada.nextLine();
					}
					Cliente clIente = Validacao.achaCliente(SeGC, cPf);
					if(clIente == null){
						System.out.println("cliente nao cadastrado na seguradora");
						return;
					}
					ClientePF clientePF2 = (ClientePF) clIente;
					System.out.println("Digite a placa do carro assegurado");
					String Placa = entrada.nextLine();
					while(!Validacao.validaPlaca(Placa)){
						System.out.println("placa invalida tente novamente");
						Placa = entrada.nextLine();
					}
					Veiculo Car = Validacao.encontraCarro(clientePF2, Placa);
					if(Car == null){
						System.out.println("Veiculo nao cadastrado no cliente");
						return;
					}
					SeguroPF segPF = Validacao.achaSeguroPF(SeGC, Car);
					if(segPF == null){
						System.out.println("carro nao eh assegurado");
						return;
					}
					System.out.println("os condutores pertencentes a esse seguro sao:");
					System.out.println(segPF.listarCondutores());
				}
				else if (tIpo.toUpperCase().equals("PJ")){
					System.out.println("Digite o cnpj do dono do seguro");
					String cnpJ = entrada.nextLine();
					while(!Validacao.validaCNPJ(cnpJ)){
						System.out.println("cnpj invalido digite outro");
						cnpJ = entrada.nextLine();
					}
					Cliente cliente4 = Validacao.achaCliente(SeGC, cnpJ);
					if(cliente4 == null){
						System.out.println("cliente nao cadastrado na seguradora");
						return;
					}
					ClientePJ CLientepj = (ClientePJ) cliente4;
					System.out.println("Digite o codigo da frota (case sensitive)");
					String codefr = entrada.nextLine();
					Frota fleet = Validacao.achaFrotaCliente(CLientepj, codefr);
					if(fleet == null){
						System.out.println("frota nao cadastrada");
						return;
					}
					SeguroPJ segPJ = Validacao.achaSeguroPJ(SeGC, fleet);
					if(segPJ == null){
						System.out.println("Frota nao assegurada");
						return;
					}
					System.out.println("os condutores pertencentes a esse seguro sao: ");
					System.out.println(segPJ.listarCondutores()) ; 
				}
				break;
			case VOLTAR:
				return;
			default:
				return;	
			}
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
				System.out.println("Digite o cnpj da seguradora a qual o cliente pertence");
				String cnpjseg = entrada.nextLine();
				while(Validacao.validaCNPJ(cnpjseg) == false){
					System.out.println("CNPJ invalido digite outro");
					cnpjseg = entrada.nextLine();
				}
				seg  = Validacao.haSist(listaSeguradora, cnpjseg,  entrada);
				if(seg == null)
					return;
				System.out.println("Digite o CPF ou CNPJ do cliente");
				String id = entrada.nextLine();
				boolean rcliente = seg.removerCliente(id);
				if(rcliente)
					System.out.println("Cliente removido com sucesso");
				break;
			case VEICULO:
				Seguradora s = null;
				System.out.println("Digite o cnpj da seguradora a qual o cliente pertence");
				String cnpjs = entrada.nextLine();
				while(!Validacao.validaCNPJ(cnpjs)){
					System.out.println("CNPJ invalido digite outro");
					cnpjs = entrada.nextLine();
				}
				s  = Validacao.haSist(listaSeguradora, cnpjs, entrada);
				if(s == null)
					return;
				System.out.println("Digite o CPF do cliente");
				String iden= entrada.nextLine();
				Cliente c = Validacao.achaCliente(s, iden);
				if(c == null){
					System.out.println("Esse cliente nao pertence a essa seguradora tente novamente");
					return;
				}
				System.out.println("Digite a placa do veiculo que quer remover:");
				String placa = entrada.nextLine();
				ClientePF C = (ClientePF) c;
				boolean removeu = C.removerVeiculo(placa.toUpperCase());
				if(removeu == false){
					System.out.println("nao foi possivel remover o veiculo pois o cliente nao o possuia");
					return;
				}
				else 
					System.out.println("Veiculo removido com sucesso");
				break;
			case FROTA:
				System.out.println("Digite o cnpj da seguradora em que o cliente dono da frota esta cadastrado");
				String cnpString = entrada.nextLine();
				while(!Validacao.validaCNPJ(cnpString)){
					System.out.println("cnpj invalido digite outro");
					cnpString = entrada.nextLine();
				}
				Seguradora S = Validacao.haSist(listaSeguradora, cnpString, entrada);
				if(S == null){
					return;
				}
				if(!Validacao.temPJ(S)){
					System.out.println("sua seguradora nao possui clientes pj");
					return;
				}
				System.out.println("Digite o cnpj do cliente dono da frota");
				String cnpStringC = entrada.nextLine();
				while(!Validacao.validaCNPJ(cnpStringC)){
					System.out.println("cnpj invalido digite outro");
					cnpStringC = entrada.nextLine();
				}
				Cliente Cl = Validacao.achaCliente(S, cnpStringC);
				if(Cl == null){
					System.out.println("cliente nao cadastrado");
					return;
				}
				ClientePJ ClPJ = (ClientePJ) Cl;
				System.out.println("Digite o codigo da frota que quer remover (case sensitive)");
				String code = entrada.nextLine();
				ClPJ.atualizarFrota(code);
				break;
			case UMVEICULOFROTA:
			System.out.println("Digite o cnpj da seguradora em que o cliente dono da frota esta cadastrado");
				String cnPString = entrada.nextLine();
				while(!Validacao.validaCNPJ(cnPString)){
					System.out.println("cnpj invalido digite outro");
					cnPString = entrada.nextLine();
				}
				Seguradora Sis = Validacao.haSist(listaSeguradora, cnPString, entrada);
				if(Sis == null){ // tem bastante codigo repetido e eu refatoraria se tivesse mais tempo
					return;
				}
				if(!Validacao.temPJ(Sis)){
					System.out.println("sua seguradora nao possui clientes pj");
					return;
				}
				System.out.println("Digite o cnpj do cliente dono da frota");
				String cnPStringC = entrada.nextLine();
				while(!Validacao.validaCNPJ(cnPStringC)){
					System.out.println("cnpj invalido digite outro");
					cnPStringC = entrada.nextLine();
				}
				Cliente CL = Validacao.achaCliente(Sis, cnPStringC);
				if(CL == null){
					System.out.println("cliente nao cadastrado");
					return;
				}
				ClientePJ CLPJ = (ClientePJ) CL;
				System.out.println("Digite o codigo da frota cujo veiculo quer remover (case sensitive)");
				String codeF = entrada.nextLine();
				Frota frota = Validacao.achaFrotaCliente(CLPJ, codeF);
				if(frota == null){
					System.out.println("Frota nao cadastrada");
					return;
				}
				System.out.println("Digite a placa do veiculo que quer remover");
				String Placa = entrada.nextLine();
				while(!Validacao.validaPlaca(Placa)){
					System.out.println("placa invalida digite outra: ");
					Placa = entrada.nextLine();
				}
				frota.removerVeiculo(Placa);
				break;
			case SEGURO:
				System.out.println("Digite o cnpj da seguradora que o seguro pertence");
				String cpj = entrada.nextLine();
				while(!Validacao.validaCNPJ(cpj)){
					System.out.println("cnpj invalido digite outro");
					cpj = entrada.nextLine();
				}
				Seguradora seguradora = Validacao.haSist(listaSeguradora, cpj, entrada);
				if(seguradora == null){
					return;
				}
				System.out.println("Digite o tipo de seguro (PF/PJ)");
				String type = entrada.nextLine();
				while((!type.toUpperCase().equals("PF")) && (!type.toUpperCase().equals("PJ"))){
					System.out.println("tipo invalido tente novamente:");
					type =entrada.nextLine();
				}
				if(type.toUpperCase().equals("PF")){
					if(!Validacao.temPF(seguradora)){
						System.out.println("vc nao possui clientes pf");
						return;
					}
					System.out.println("Digite o cpf do dono do seguro");
					String CPF = entrada.nextLine();
					while(!Validacao.validarCPF(CPF)){
						System.out.println("cpf invalido digite outro");
						CPF = entrada.nextLine();
					}
					Cliente cliente = Validacao.achaCliente(seguradora, CPF);
					if(cliente == null){
						System.out.println("cliente nao cadastrado na seguradora");
						return;
					}
					ClientePF clientePF = (ClientePF) cliente;
					System.out.println("digite a placa do veiculo que deseja desassegurar");
					String PlacA = entrada.nextLine();
					while(!Validacao.validaPlaca(PlacA)){
						System.out.println("placa invalida digite outra");
						PlacA = entrada.nextLine();
					}
					Veiculo v = Validacao.encontraCarro(clientePF,PlacA);
					if(v == null){
						System.out.println("veiculo nao pertence ao cliente");
						return;
					}
					seguradora.cancelarSeguro(v);
				}
				else if (type.toUpperCase().equals("PJ")){
					if(!Validacao.temPJ(seguradora)){
						System.out.println("voce nao possui clientes PJ");
						return;
					}
					System.out.println("Digite o cnpj do dono do seguro");
					String CNPj = entrada.nextLine();
					while(!Validacao.validaCNPJ(CNPj)){
						System.out.println("cnpj invalido tente novamente:");
						CNPj = entrada.nextLine();
					}
					Cliente CliEnte = Validacao.achaCliente(seguradora, CNPj);
					if(CliEnte == null){
						System.out.println("cliente nao cadastrado");
						return;
					}
					ClientePJ cliEntePJ = (ClientePJ) CliEnte;
					System.out.println("Digite o codigo da frota que quer desassegurar (case sensitive)");
					String codigo = entrada.nextLine();
					Frota fro = Validacao.achaFrotaCliente(cliEntePJ, codigo);
					if(fro == null){
						System.out.println("frota nao encontrada");
						return;
					}
					seguradora.cancelarSeguro(fro);
				}
				break;
			case TODOSSEGCLIENTE:
				System.out.println("Digite o cnpj da seguradora que o cliente pertence");
				String CNPJ = entrada.nextLine();
				while(!Validacao.validaCNPJ(CNPJ)){
					System.out.println("cnpj invalido tente novamente:");
					CNPJ = entrada.nextLine();
				}
				Seguradora sEg = Validacao.haSist(listaSeguradora, CNPJ, entrada);
				if(sEg == null){
					return;
				}
				System.out.println("Digite o tipo do cliente (PF/PJ)");
				String tp = entrada.nextLine();
				while((!tp.toUpperCase().equals("PF")) && (!tp.toUpperCase().equals("PJ"))){
					System.out.println("tipo invalido tente novamente");
					tp = entrada.nextLine();
				}
				String idC = null; // nunca vai dar null pointer pq eu forco a pessoa a botar
				if(tp.toUpperCase().equals("PF")){
					System.out.println("Digite o cpf do cliente");
					String cp = entrada.nextLine();
					while(!Validacao.validarCPF(cp)){
						System.out.println("cpf invalido tente novamente");
						cp = entrada.nextLine();
					}
					idC = cp;
				}
				else if(tp.toUpperCase().equals("PJ")){
					System.out.println("digite o cnpj do cliente");
					String pJ = entrada.nextLine();
					while(!Validacao.validaCNPJ(pJ)){
						System.out.println("cnpj invalido tente novamente:");
						pJ = entrada.nextLine();
					}
					idC = pJ;
				}
				sEg.cancelarSeguro(idC, tp);
				break;
			case CONDUTOR:
				System.out.println("Digite o cnpj da seguradora que o cliente pertence");
				String CNPJs = entrada.nextLine();
				while(!Validacao.validaCNPJ(CNPJs)){
					System.out.println("cnpj invalido tente novamente:");
					CNPJs = entrada.nextLine();
				}
				Seguradora sEgs = Validacao.haSist(listaSeguradora, CNPJs, entrada);
				if(sEgs == null){
					return;
				}
				System.out.println("Digite o tipo do cliente (PF/PJ)");
				String tip = entrada.nextLine();
				while((!tip.toUpperCase().equals("PF")) && (!tip.toUpperCase().equals("PJ"))){
					System.out.println("tipo invalido tente novamente");
					tp = entrada.nextLine();
				}
				if(tip.toUpperCase().equals("PF")){
					if(!Validacao.temPF(sEgs)){
						System.out.println("voce nao possui clientes pf");
						return;
					}
					System.out.println("Digite o cpf do dono do seguro");
					String cpfString = entrada.nextLine();
					while(!Validacao.validarCPF(cpfString)){
						System.out.println("cpf invalido digite outro");
						cpfString = entrada.nextLine();
					}
					Cliente Cli = Validacao.achaCliente(sEgs, cpfString);
					if(Cli == null){
						System.out.println("Cliente nao cadastrado");
						return;
					}
					ClientePF CliPf = (ClientePF) Cli;
					System.out.println("Digite a placa do veiculo assegurado");
					String pl = entrada.nextLine();
					while(!Validacao.validaPlaca(pl)){
						System.out.println("placa invalida digite outra");
						pl = entrada.nextLine();
					}
					Veiculo vei = Validacao.encontraCarro(CliPf,pl);
					if(vei== null){
						System.out.println("veiculo nao cadastrado");
						return;
					}
					SeguroPF segf = Validacao.achaSeguroPF(sEgs, vei);
					if(segf == null){
						System.out.println("veiculo nao assegurado");
						return;
					}
					System.out.println("Digite o cpf do condutor a ser removido");
					String cpF = entrada.nextLine();
					while(!Validacao.validarCPF(cpF)){
						System.out.println("cpf invalido digite outro");
						cpF = entrada.nextLine();
					}
					segf.desautorizarCondutor(cpF);
				}
				else if(tip.toUpperCase().equals("PJ")){
					if(!Validacao.temPJ(sEgs)){
						System.out.println("voce nao possui clientes pj");
						return;
					}
					System.out.println("digite o cnpj do dono do seguro");
					String Pj = entrada.nextLine();
					while(!Validacao.validaCNPJ(Pj)){
						System.out.println("cnpj invalido digite outro");
						Pj = entrada.nextLine();
					}
					Cliente cliente = Validacao.achaCliente(sEgs, Pj);
					if(cliente == null){
						System.out.println("cliente nao cadastrado");
						return;
					}
					ClientePJ cliPj = (ClientePJ) cliente;
					System.out.println("digite o codigo da frota (case sensitive)");
					String Cd = entrada.nextLine();
					Frota fleet = Validacao.achaFrotaCliente(cliPj, Cd);
					if(fleet == null){
						System.out.println("frota nao cadastrada");
						return;
					}
					SeguroPJ seguroPJ = Validacao.achaSeguroPJ(sEgs, fleet);
					if(seguroPJ == null){
						System.out.println("frota nao assegurada");
						return;
					}
					System.out.println("Digite o cpf do condutor que quer remover");
					String cpString = entrada.nextLine();
					while(!Validacao.validarCPF(cpString)){
						System.out.println("cpf invalido digite outro:");
						cpString = entrada.nextLine();
					}
					seguroPJ.desautorizarCondutor(cpString);
				}
				break;
			case VOLTAR:
				return;
			default:
				return;
			}
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
			DateTimeFormatter dataformat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate date = LocalDate.parse(data, dataformat);
			System.out.println("Onde ocorreu o sinistro?");
			String endereco = entrada.nextLine();
			System.out.println("Digite se o seguro no qual o sinistro sera cadastrado eh PF ou PJ");
				String tipo = entrada.nextLine();
				System.out.println("Digite o CPF do cliente dono do seguro");
					String cpf = entrada.nextLine();
					while(!Validacao.validarCPF(cpf)){
						System.out.println("cpf invalido digite outro");
						cpf = entrada.nextLine();
					}
					Cliente c = Validacao.achaCliente(seguradora, cpf);
					if(c == null){
						System.out.println("Cliente nao cadastrado nessa seguradora");
						return;}
				while((!tipo.toUpperCase().equals("PF")) && (!tipo.toUpperCase().equals("PJ"))){
					System.out.println("entrada invalida tente novamente");
					tipo = entrada.nextLine();
				}
				if(tipo.toUpperCase().equals("PF")){
					ClientePF clientepf = (ClientePF) c;
					System.out.println("Digite a placa do carro assegurado");
					String placa = entrada.nextLine();
					while(!Validacao.validaPlaca(placa)){
						System.out.println("placa invalida tente novamente: ");
						placa = entrada.nextLine();
					}
					Veiculo veiculo = Validacao.encontraCarro(clientepf, placa);
					if(veiculo == null){
						System.out.println("veiculo nao pertence ao cliente");
						return;
					}
					SeguroPF seguroPF = Validacao.achaSeguroPF(seguradora, veiculo);
					if(seguroPF == null){
						System.out.println("Esse veiculo nao eh assegurado");
						return;
					}
					System.out.println("Qual o cpf do condutor que cometeu o sinistro?");
					String cpfcond = entrada.nextLine();
					while(! Validacao.validarCPF(cpfcond)){
						System.out.println("cpf invalido tente de novo: ");
						cpfcond = entrada.nextLine();
					}
					Condutor cond = Validacao.encontraCondutor(seguroPF,cpfcond);
					if(cond == null){
						System.out.println("Condutor nao cadastrado");
						return;
					}
					seguroPF.gerarSinistro(date, cond, endereco);
				}
				else if(tipo.toUpperCase().equals("PJ")){
					ClientePJ clientePJ = (ClientePJ) c;
					System.out.println("Digite o codigo da frota assegurada");
					String code = entrada.nextLine();
					Frota frota = Validacao.achaFrotaCliente(clientePJ, code);
					if(frota == null){
						System.out.println("frota nao cadastrada nesse cliente");
						return;
					}
					SeguroPJ seguroPJ = Validacao.achaSeguroPJ(seguradora, frota);
					if(seguroPJ == null){
						System.out.println("essa frota nao eh assegurada");
						return;
					}
					System.out.println("Qual o cpf do condutor que cometeu o sinistro?");
					String cpfc = entrada.nextLine();
					while(! Validacao.validarCPF(cpfc)){
						System.out.println("cpf invalido tente de novo: ");
						cpfc = entrada.nextLine();
					}
					Condutor cond = Validacao.encontraCondutor(seguroPJ,cpfc);
					if(cond == null){
						System.out.println("Condutor nao cadastrado");
						return;
					}
					seguroPJ.gerarSinistro(date, cond, endereco);

				}   
		 	System.out.println("Seu sinistro foi cadastrado com sucesso");
			}






	private static int MenuPrincipal(int escolha, Scanner entrada, LinkedList<Seguradora> listaseguradoras) throws Exception{
		MenuOperacoes op = MenuOperacoes.deIntpraEnum(escolha);
			switch(op) {
			case CADASTRAR:
				System.out.println("Para cadastrar cliente aperte: 1\nPara cadastrar veiculo aperte: 2\nPara cadastrar seguradora aperte: 3");
				System.out.println("Para cadastrar frota aperte: 4\nPara cadastrar um veiculo em frota aperte: 5\nPara cadastrar seguro aperte: 6");
				System.out.println("Para cadastrar condutor aperte: 7\nPara voltar aperte: 8");
				escolha = 8;
				String escolhA = entrada.nextLine();
				boolean escolhavalida = false;
				while(!escolhavalida){
				try{
					escolha = Integer.parseInt(escolhA);
					escolhavalida = true;
				} 
				catch(NumberFormatException e){
					System.out.println("Entrada invalida, por favor digite um numero:");
					escolhA = entrada.nextLine();
				}
				}
				cadastros(escolha, entrada, listaseguradoras);
				break;
			case LISTAR:
				System.out.println("Para listar clientes aperte: 1\nPara listar sinistros por seguradora aperte: 2");
				System.out.println("Para listar sinistros por cliente aperte: 3\nPara listar veiculo por cliente aperte: 4\nPara listar seguros por seguradora aperte: 5\nPara listar veiculos por frota aperte: 6");
				System.out.println("Para listar seguros por cliente aperte: 7\nPara listar frotas por cliente aperte: 8\nPara listar condutores por seguro aperte: 9\nPara voltar aperte: 10");
				escolha = 10;
				boolean valeu = false;
				String Sescolha = entrada.nextLine();
				while(!valeu){
				try{
					escolha = Integer.parseInt(Sescolha);
					valeu = true;
				} 
				catch(NumberFormatException e){
					System.out.println("Entrada invalida, por favor digite um numero:");
					Sescolha = entrada.nextLine();
				}
				}
				
				listar(escolha, entrada, listaseguradoras);
				break;
			case EXCLUIR:  
				System.out.println("Para excluir cliente aperte: 1\nPara excluir veiculo aperte: 2\nPara excluir sinistro aperte: 3\nPara excluir frota aperte: 4");
				System.out.println("Para excluir um veiculo a uma frota aperte: 5\nPara excluir um seguro aperte: 6\nPara excluir todos os seguros de um cliente aperte: 7");
				System.out.println("Para excluir condutor aperte: 4\nPara voltar aperte: 9");
				escolha = 9;
				String escolhaS = entrada.nextLine();
				boolean deucerto = false;
				while(!deucerto){
				try{
					escolha = Integer.parseInt(escolhaS);
					deucerto= true;
				} 
				catch(NumberFormatException e){
					System.out.println("Entrada invalida, por favor digite um numero:");
					escolhaS = entrada.nextLine();
				}
				}
				excluir(escolha, entrada, listaseguradoras);
				break;
			case GERAR_SINISTRO:
				if (listaseguradoras.size() == 0){
					System.out.println("Voce ainda nao criou seguradora, crie uma e depois volte aqui");
					break;
				}
				System.out.println("Diga o cnpj da seguradora para o qual você quer gerar o sinistro: ");
				String cnpj = entrada.nextLine();
				while(!Validacao.validaCNPJ(cnpj)){
					System.out.println("CNPJ invalido digite outro");
					cnpj = entrada.nextLine();
				}
				Seguradora seguradora = null;
				seguradora = Validacao.haSist(listaseguradoras, cnpj, entrada);
				if(seguradora == null){
					break;
				}
				criaSinistro(seguradora, entrada);
				break;
			case CALCULAR_RECEITA:
				if(listaseguradoras.size() == 0){
					System.out.println("Voce ainda nao criou seguradora, crie uma e volte aqui");
					break;
				}
				System.out.println("Digite o cnpj da seguradora cuja receita quer calcular:");
				String c = entrada.nextLine();
				while(!Validacao.validaCNPJ(c)){
					System.out.println("CNPJ invalido digite outro");
					c = entrada.nextLine();
				}
				Seguradora s = null;
				s = Validacao.haSist(listaseguradoras, c, entrada);
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

	public static void main(String[] args) throws Exception {
		obrigacoesdolab();
		int escolha = 0;
		int quit = 0;
		Scanner entrada = new Scanner(System.in);
		LinkedList<Seguradora> listaSeguradoras = new LinkedList<Seguradora>();	
		do{
		System.out.println("Ola! Bem vindo ao menu de seguradoras!\nPara cadastrar aperte: 1\nPara listar aperte: 2");
		System.out.println("Para excluir aperte: 3\nPara gerar sinistro aperte: 4\nPara calcular receita aperte: 5\nPara sair aperte: 0");
		String escolhaS = entrada.nextLine();
		boolean escolhavalida = false;
		while(!escolhavalida){
		try{
			escolha = Integer.parseInt(escolhaS);
			escolhavalida = true;
		} 
		catch(NumberFormatException e){
			System.out.println("Entrada invalida, por favor digite um numero:");
			escolhaS = entrada.nextLine();
		}
		}
		quit = MenuPrincipal(escolha,entrada,listaSeguradoras);
		} while(quit != 1);
		entrada.close();
		System.out.println("Obrigada por utilizar o menu!"); 
		
	}
	

}
