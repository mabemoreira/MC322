package lab02;

public class Cliente {
	private String nome;
	private String cpf;
	private String dataNascimento;
	private int idade;
	private String endereco;
	
	//testo o cpf logo no constructor
	public Cliente(String nomeCliente, String cpfCliente, String aniversarioCliente, int idadeCliente, String enderecoCliente) {
		nome = nomeCliente;
		if (validarCPF(cpfCliente))
			cpf = cpfCliente;
		else
			cpf = "cliente forneceu cpf invalido";
		dataNascimento = aniversarioCliente;
		idade = idadeCliente;
		endereco = enderecoCliente;
	}
	
	//getters e setters, gerados automaticamente pelo eclipse
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	//testo o cpf aqui também
	public void setCpf(String cpf) {
		if(validarCPF(cpf))
			this.cpf = cpf;
		else
			this.cpf = "cliente forneceu cpf invalido";
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String toString() {
		if(validarCPF(cpf))
			return "O nome do cliente eh " + nome + " e ele tem " + idade + " anos, dado que seu aniversario eh em " + dataNascimento +"."
				+ "Seu endereco e cpf sao, respectivamente: " + endereco + " e " + cpf.replaceAll("[^\\d]","")+ ".\n";
		else 
			return "O nome do cliente eh " + nome + " e ele tem " + idade + " anos, dado que seu aniversario eh em " + dataNascimento +"."
			+ "Seu endereco eh: " + endereco + ". Contudo, alerta: " + cpf;
			
	}
	
	public int calculaDigito(String cpfTeste, int aSomar, int acrescimo) {
		int soma = 0;
		for(int i = 8; i >= 0; i--) {
			soma += aSomar * (Character.getNumericValue(cpfTeste.charAt(i)));
			aSomar++;
		}
		soma+= acrescimo; 
		return soma%11 >= 2 ? (11-(soma%11)) : 0;
	}
	
	public boolean validarCPF(String cpfTeste) {
		char base = cpfTeste.charAt(0);
		int cont = 1, primeiroDigito, segundoDigito, digitoParcial, a, b;
		cpfTeste = cpfTeste.replaceAll("[^\\d]","");
		if(cpfTeste.length() != 11)
			return false;
		for(int i = 1; i < 11; i++) {
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
	
}
