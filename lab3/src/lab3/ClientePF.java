package lab3;
import java.util.Date;

public class ClientePF extends Cliente{
	private final String CPF;
	private Date dataNascimento;
	private String genero;
	private Date dataLicenca;
	private String educacao;
	private String classeEconomica;
	
	public ClientePF(String CPF, Date dataNascimento, String nome, String endereco, String genero, String educacao, String classeEconomica, Date dataLicenca) {
		super(nome, endereco);
		this.CPF = CPF;
		this.setDataNascimento(dataNascimento);
		this.setGenero(genero);
		this.setEducacao(educacao);
		this.setDataLicenca(dataLicenca);
		this.setClasseEconomica(classeEconomica);
	}

	public String getCPF() {
		return CPF;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Date getDataLicenca() {
		return dataLicenca;
	}

	public void setDataLicenca(Date dataLicenca) {
		this.dataLicenca = dataLicenca;
	}

	public String getEducacao() {
		return educacao;
	}

	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}

	public String getClasseEconomica() {
		return classeEconomica;
	}

	public void setClasseEconomica(String classeEconomica) {
		this.classeEconomica = classeEconomica;
	}
	
	public static int calculaDigito(String cpfTeste, int aSomar, int acrescimo) {
		int soma = 0;
		for(int i = 8; i >= 0; i--) {
			soma += aSomar * (Character.getNumericValue(cpfTeste.charAt(i)));
			aSomar++;
		}
		soma+= acrescimo; 
		return soma%11 >= 2 ? (11-(soma%11)) : 0;
	}
	
	
	public static boolean validarCPF(String cpfTeste) {
		char base = cpfTeste.charAt(0);
		int cont = 1, primeiroDigito, segundoDigito, digitoParcial, a, b;
		cpfTeste = cpfTeste.replaceAll("[^\\d]","");
		if(cpfTeste.length() != 11)
			return false;
		for(int i = 1; i < 11; i++) { // acredito que um cpf completamente igual nao passaria no teste numerico, mas o lab pedia especificamente para testar se todos eram iguais
			if (cpfTeste.charAt(i) == base)
				cont++;
		}
		if(cont == 11)
			return false;
		primeiroDigito = calculaDigito(cpfTeste, 2, 0);
		digitoParcial = primeiroDigito * 2;
		segundoDigito = calculaDigito(cpfTeste, 3, digitoParcial);
		a = Character.getNumericValue(cpfTeste.charAt(9));
		b = Character.getNumericValue(cpfTeste.charAt(10));
		return ((primeiroDigito == a) && (segundoDigito == b));
	}

	public String toString() {
		return "o cliente " + this.getNome() + " de genero " + genero + " nascido em " + dataNascimento + " possui o CPF " + CPF + " tirou sua habilitacao em " + dataLicenca + " tem até nivel " 
				+ educacao + " é da classe economica " + classeEconomica + " mora em " + this.getEndereco() + " e possui veiculo(s) " + this.listarVeiculos(); // pq deu errado??
	}
}
