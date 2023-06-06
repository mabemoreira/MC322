package lab05;
import java.util.LinkedList;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;

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
		seguropf.autorizarCondutor(new Condutor("055.668.83095", "aaaaa", "aaaa", "aaaaa", "aaaa", fund));
		seguropf.gerarSinistro(fund, new Condutor("777.016.050-29", "cccc", "33333", "aaaa", "dddd", fund), "bbbb");
		System.out.println(seguradora);
		seguropf.desautorizarCondutor("055.668.83095");
		System.out.println(seguradora);

	}

	public static void main(String[] args) {
		obrigacoesdolab();
		Scanner entrada = new Scanner(System.in);
		entrada.close();
	}

}
