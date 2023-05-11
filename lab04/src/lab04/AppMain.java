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
				break;
			default:
				break;
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
				break;
			default:
				break;	
			}
		}

		private static void cadastros(int escolha, Scanner entrada, LinkedList<Seguradora> listaseguradoras) {
			MenuCadastrar op = MenuCadastrar.deIntpraEnum(escolha);
			switch(op) {
			case CLIENTE:
				break;
			case VEICULO:
				break;
			case SEGURADORA: 
				break;
			case VOLTAR:
				break;
			default:
				break;
			}
		}
	
	
		private static void MenuPrincipal(int escolha, Scanner entrada, LinkedList<Seguradora> listaseguradoras) { // tem q ta num while na main
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
			case GERAR_SINISTRO:
				break;
			case TRANSFERIR_SINISTRO:
				break;
			case CALCULAR_RECEITA:
				break;
			case SAIR:
				break;
			default:
				break;
			}
		}
	
	
	public static void main(String args[]) throws Exception{
		obrigacoesdolab();
		int escolha;
		Scanner entrada = new Scanner(System.in);
		LinkedList<Seguradora> listaSeguradoras = new LinkedList<Seguradora>();	
		System.out.println("Ola! Bem vindo ao menu de seguradoras!\nPara cadastrar aperte: 1\nPara listar aperte: 2");
		System.out.println("Para excluir aperte: 3\nPara gerar sinistro aperte: 4\nPara transferir seguro aperte: 5\nPara sair aperte: 0");
		escolha = entrada.nextInt();
		MenuPrincipal(escolha,entrada,listaSeguradoras);
		entrada.close();
	}
}
