package lab3;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Ola! seja bem vindo ao seu portal de seguradora! para criar sua seguradora, aperte '1'");
		int inicio = entrada.nextInt();
		do {
			System.out.println("Tente novamente");
			int inicio = entrada.nextInt();
		}while(inicio != 1);
			System.out.println("Otimo! qual o nome da sua seguradora?");
			String nomeSeguradora = entrada.nextLine();
			nomeSeguradora = nomeSeguradora.replace('\n', '');
			System.out.println("E qual o endereco dela?");
			String enderecoSeguradora = entrada.nextLine();
			System.out.println("E o telefone?");
			String telefoneSeguradora = entrada.nextLine();
			System.out.println("Por ultimo, qual o email dela?");
			String emailSeguradora = entrada.nextLine();
			Seguradora seguradora = new Seguradora(nomeSeguradora, telefoneSeguradora, emailSeguradora, enderecoSeguradora);
			entrada.close();
	}
}
