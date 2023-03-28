
package lab02;

public class Main {
	public static void main(String args[]) {
		Cliente c1 = new Cliente("Ana Maria Gilmore", "111.111.111.111", "08/10/1984" , 38, "diadema");
		Cliente c2 = new Cliente("Maria Ana Gilmore", "13.13.13.13.13.13.13.13", "13/10/2003" , 19, "ribeirao preto");
		System.out.println(c1.toString());
		System.out.println(c2.toString());// imprimi os 2 sem mudar nada
		c1.setEndereco("New Haven-CT");
		c1.setNome("A. Gilmore"); 
		c1.setCpf("111.444.777-35");// mudei só 1 objeto
		System.out.println(c1.toString());
		System.out.println(c2.toString());// imprimi os 2 para ver o que mudou 
		Veiculo v1 = new Veiculo("937-G5R", "Jeep", "Wrangler");
		System.out.println(v1.toString());
		Sinistro s1 = new Sinistro ("21/12/1961", "rua pitagoras");
		System.out.println(s1.toString());
		Seguradora sa = new Seguradora("nos Trinques", "707070", "nostrinquesarrobadominionaomepatrocinou.com", "no seu coracao");
		System.out.println(sa); 
		System.out.println(sa.toString()); // imprimi com e sem explicitar to string e no fim vi que dá na mesma
		
	}
}
