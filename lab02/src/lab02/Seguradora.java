package lab02;

public class Seguradora {
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	
	//usei nomes longos só para não ficar igual ao exemplo do lab
	public Seguradora(String nomeSeguradora, String telefoneSeguradora, String emailSeguradora, String enderecoSeguradora) {
		nome = nomeSeguradora;
		telefone = telefoneSeguradora;
		email = emailSeguradora;
		endereco = enderecoSeguradora;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String toString() {
		return "A seguradora " + nome + " localizada em " + endereco + " pode ser contatada pelo email " + email + " ou "
				+ "pelo telefone " + telefone + ".";
	}
}
