package lab05;
import java.util.LinkedList;
import java.util.Scanner;
import java.time.LocalDate;


public class AppMain {
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

	public static void main(String[] args) {
		obrigacoesdolab();
		Scanner entrada = new Scanner(System.in);
		entrada.close();
	}

}
