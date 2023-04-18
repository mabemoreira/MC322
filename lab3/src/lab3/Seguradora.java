package lab3;
import java.util.HashMap;



public class Seguradora {
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	private HashMap <Integer, Sinistro> mapaSinistros;
	private HashMap <String, Cliente> mapaClientes; //mapa cpf -> cliente
	
	public Seguradora(String nome, String telefone, String email, String endereco){
		this.setNome(nome);
		this.setTelefone(telefone);
		this.setEmail(email);
		this.setEndereco(endereco);
		mapaSinistros = new HashMap <Integer, Sinistro>();
		mapaClientes = new HashMap <String, Cliente>();
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

	public HashMap <Integer, Sinistro> getMapaSinistros() {
		return mapaSinistros;
	}

	public HashMap <String, Cliente> getMapaClientes() {
		return mapaClientes;
	}
	
	public boolean cadastrarCliente (Cliente cliente) {
		if(cliente instanceof ClientePF) {
			ClientePF clientecast = (ClientePF) cliente;
			mapaClientes.put(clientecast.getCPF(), clientecast);
			return true;
		}
		else if(cliente instanceof ClientePJ) { // o lab me garante que todos sao pf ou pj
			ClientePJ clientecast = (ClientePJ) cliente;
			mapaClientes.put(clientecast.getCNPJ(), clientecast);
		return true;
	}
	return false; //nunca será usado nesse lab pq tds sao pf ou pj mas está aqui
}
	
	public boolean removerCliente(String CPFouCNPJCliente) { // tem que receber o cpf, pq se nao podem ter 2 joao da silva e como saber qual remover nesse caso?
		Cliente teste = mapaClientes.remove(CPFouCNPJCliente);
		return teste == null ? false : true; // tentou remover um cliente que não existe 
	}

	public void listarClientes(String tipoCliente) {
		if(tipoCliente.equals("PJ")) {
			for(Cliente value : mapaClientes.values()) {
				if(value instanceof ClientePJ) {
					System.out.println(value);
				}
			}
		}
		else if(tipoCliente.equals("PF")) {
			for(Cliente value : mapaClientes.values()) {
				if(value instanceof ClientePF) {
					System.out.println(value);
				}
		}
	}

}
	
	public void listarSinistros() {
		for(Sinistro value : mapaSinistros.values()) {
			System.out.println(value);
		}
	}
	
	public boolean gerarSinistro(String data, String endereco, Veiculo veiculo, Cliente cliente) {
		Sinistro sinistro = new Sinistro(data, endereco, this, veiculo, cliente);
		mapaSinistros.put(sinistro.getID(), sinistro);
		return true;
	}
	
	public boolean visualizarSinistro(String cliente) {
		for(Sinistro value: mapaSinistros.values()) {
			if(value.getCliente() instanceof ClientePF){
				ClientePF clientecast = (ClientePF) value.getCliente();
				if(clientecast.getCPF().equals(cliente)) {
				System.out.println(value);
				return true;
			}
			else if (value.getCliente() instanceof ClientePJ) {
				ClientePJ clientecasted = (ClientePJ) value.getCliente();
				if(clientecasted.getCNPJ().equals(cliente)) {
				System.out.println(value);
				return true;
			}
		}
	}
}
				return false; /// mandou um cnpj/ cpf invalido ou nao cadastrado
}
	
	
}
	
	
