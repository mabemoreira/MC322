package lab3;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;

public class ClientePJ  extends Cliente {
	private final String CNPJ;
	private Date dataFundacao;
	
	public ClientePJ(String CNPJ, Date dataFundacao, String nome, String endereco){
		super(nome,endereco);
		this.CNPJ = CNPJ.replaceAll("[^\\d]","");
		this.dataFundacao = dataFundacao;
	}
	
	public String getCNPJ() {
		return CNPJ;
	}

	public Date getDataFundacao() {
		return dataFundacao;
	}
	
	public static int calculaDigito(ArrayList <Integer> arraybase, int max, String cnpjTeste, int sub) {

		int soma = 0;

		for(int i = 0; i < max; i++) {
			soma += Character.getNumericValue(cnpjTeste.charAt(i)) * arraybase.get(arraybase.size()-sub-i);
		}

		return soma % 11 >=2 ? (11-(soma%11)) : 0;

	}
	
	public static boolean validaCNPJ(String cnpjTeste) {		
	char base = cnpjTeste.charAt(0); 		
	int cont = 1, primeiroDigito, segundoDigito, a, b;		
	cnpjTeste = cnpjTeste.replaceAll("[^\\d]","");	
	if(cnpjTeste.length() != 14) {
		System.out.println("< 14");
		return false;
	}		
	for(int i = 1; i < 14; i++) { 			
		if (cnpjTeste.charAt(i) == base)				
			cont++;		}		
	if(cont == 14)
		return false;	
	ArrayList <Integer>arraybase = new ArrayList<Integer>(Arrays.asList(2,3,4,5,6,7,8,9,2,3,4,5,6));		
	primeiroDigito = calculaDigito(arraybase, 12, cnpjTeste, 2);		
	a = Character.getNumericValue(cnpjTeste.charAt(12));		
	if(a != primeiroDigito)	
		return false;		
	segundoDigito = calculaDigito(arraybase, 13, cnpjTeste, 1);		
	b = Character.getNumericValue(cnpjTeste.charAt(13));		
		return(b== segundoDigito);
	}
	
	public String toString() {		
		return "a pessoa física de nome " + this.getNome() + " e endereco " + this.getEndereco() + " foi fundada em " + dataFundacao + " e possui CNPJ " + CNPJ
				+ " e possui o(s) seguinte(s) veiculo(s)\n" + super.listarVeiculos(); 
		}
	
	
}
