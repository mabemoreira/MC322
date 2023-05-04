package lab04;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppMain {
	private static void obrigacoesdolab() {
		SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
		Seguradora seguradora = new Seguradora("seguro", "1111111", "aaaa@aaaaa.com", "rua dos bobos numero 0" );
		Date dataNascimento = dateformat.parse("13/10/2003");
		Date dataLicenca = dateformat.parse("13/10/2021");
		Date dataFundacao = dateformat.parse("17/09/2003");
		ClientePF clientepf = new ClientePf("11144477735", dataNascimento, "mabe", "rua aaaa", "feminino", "superior incompleto","pobre", dataLicenca );
		ClientePJ clientepj = new ClientePJ ("42.422.967/0001-01", dataFundacao, "minha empresa", "aaaaa", 10);
		Veiculo veiculopf = new Veiculo ("abc123","bmw", "mini cooper", 2023);
		Veiculo veiculopj = new Veiculo ("abc456","volks", "new beetle", 2023);
		clientepf.adicionaveiculo(veiculopf);
		clientepj.adicionaveiculo(veiculopj);
		seguradora.cadastrarcliente(clientepf);
		seguradora.cadastrarcliente(clientepj);
		Date dataSinistroPF = dateformat.parse("21/12/2023");
		Date dataSinistroPJ = dateformat.parse("16/05/2023");
		Sinistro sinistropf = new Sinistro(dataSinistroPF, "rua aaaa", seguradora, veiculopf, clientepf);
		Sinistro sinistropj = new Sinistro(dataSinistroPJ, "rua aaaa", seguradora, veiculopj, clientepj);
		clientepf.setValorSeguro(seguradora.calcularPrecoSeguroCliente(clientepf.getCPF()));
		clientepj.setValorSeguro(seguradora.calcularPrecoSeguroCliente(clientepj.getCNPJ()));
		System.out.println(seguradora.listarClientes());
		seguradora.visualizarSinistro(clientepf.getCPF());
		System.out.println(seguradora.listarSinistros());
		System.out.println(seguradora.calcularReceita());
		}

	public static void main(String args[]) {
		
	}
}
