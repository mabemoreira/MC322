package lab04;
import java.util.HashMap;
import java.util.Date;
import java.util.Set;

public class Seguradora {
	private String nome;
	private String telefone;
	private String email;
	private String endereco;
	private HashMap <Integer, Sinistro> mapaSinistros;
	private HashMap <String, Cliente> mapaClientes; //mapa cpf -> cliente
	
	public Seguradora(String nome, String telefone, String email, String endereco){
		this.setNome(nome.toLowerCase());
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
		if(teste == null)
			System.out.println("nao foi possivel remover esse cliente pois ele nao esta cadastrado");
		return(!(teste == null));// tentou remover um cliente que não existe 
	}

	public String listarClientes(String tipoCliente) {
		StringBuilder total = new StringBuilder();
		if(tipoCliente.equals("PJ")) {
			for(Cliente value : mapaClientes.values()) {
				if(value instanceof ClientePJ) {
					total.append(value.toString());
					total.append("\n");
				}
			}
		}
		else if(tipoCliente.equals("PF")) {
			for(Cliente value : mapaClientes.values()) {
				if(value instanceof ClientePF) {
					total.append(value.toString());
					total.append("\n");
				}
		}
	}
	return total.toString().equals("")? "nao ha clientes desse tipo" : total.toString();
}
	
	public String listarSinistros() {
		StringBuilder total = new StringBuilder();
		for(Sinistro value : mapaSinistros.values()) {
			total.append(value.toString(0));
			total.append("\n");
		}
		return total.toString().equals("")? "nao ha sinistros" : total.toString();
	}
	
	public boolean gerarSinistro(Date data, String endereco, Veiculo veiculo, Cliente cliente) {
		Sinistro sinistro = new Sinistro(data, endereco, this, veiculo, cliente);
		mapaSinistros.put(sinistro.getID(), sinistro);
		return true;
	}
	
	public boolean visualizarSinistro(String cliente) {
		boolean flag = false;
		System.out.println("Todos os sinistros cadastrados para essa pessaoa sao: ");
		for(Sinistro value: mapaSinistros.values()) {
			if(value.getCliente() instanceof ClientePF){
				ClientePF clientecast = (ClientePF) value.getCliente();
				if(clientecast.getCPF().equals(cliente.replaceAll("[^\\d]",""))) {
					System.out.println(value.toString(0));
					flag = true;
			}
			else if (value.getCliente() instanceof ClientePJ) {
				ClientePJ clientecasted = (ClientePJ) value.getCliente();
				if(clientecasted.getCNPJ().equals(cliente.replaceAll("[^\\d]",""))) {
					System.out.println(value.toString(0));
					flag = true; // fiz isso ao inves de retornar pq pode ter varios sinistros da mesma pessoa
			}
		}
	}
}
		if(flag == false)
			System.out.println("Não foram encontrados Sinistros pertencentes a essa pessoa"); /// mandou um cnpj/ cpf invalido ou nao cadastrado
	    return flag;
		
}
	

	public boolean removeSinistro(String cliente, Date data){
		Sinistro sinistro = null;
		for(Sinistro value: mapaSinistros.values()){
			Cliente intermediario = value.getCliente();
			if(intermediario instanceof ClientePF){
				ClientePF clientepf = (ClientePF) intermediario;
				if(clientepf.getCPF().equals(cliente) && (value.getData().compareTo(data) == 0)){
					sinistro = mapaSinistros.remove(value.getID());
					if(sinistro == null){
						System.out.println("Esse sinistro nao pertence a essa seguradora, tente de novo");
						return false;
					}
				}

			}
			else if(intermediario instanceof ClientePJ){
				ClientePJ clientepj = (ClientePJ) intermediario;
				if(clientepj.getCNPJ().equals(cliente) && (value.getData().compareTo(data) == 0)){
					sinistro = mapaSinistros.remove(value.getID()); // usando o remove 
					if(sinistro == null){
						System.out.println("Esse sinistro nao pertence a essa seguradora, tente de novo");
						return false;
					}
				}
			}
		}
		if(sinistro == null){
			System.out.println("Esse sinistro nao pertence a essa seguradora, tente de novo");
				return false;
		}
		this.calcularPrecoSeguroCliente(cliente);
		System.out.println("Sinistro removido com sucesso");
		return true;
	}

	public double calcularPrecoSeguroCliente(String cliente) {
		int contador = 0;
		double preco;
		Cliente ele = null;
		Cliente intermediario = null;
		for(Sinistro value : mapaSinistros.values()) {
			if(value.getCliente() instanceof ClientePF) {
				intermediario = value.getCliente(); // se fizesse o casting direto o eclipse reclamava
				ClientePF clientepf = (ClientePF) intermediario; 
				if(clientepf.getCPF().equals(cliente)) {
					ele = clientepf;
					contador++;
				}
		
			}
			else if(value.getCliente() instanceof ClientePJ) {
				intermediario = value.getCliente();
				ClientePJ clientepj = (ClientePJ) intermediario;
				if(clientepj.getCNPJ().equals(cliente)) {
					ele = clientepj;
					contador++;
				}
			}
		}
		if(ele == null)
			preco = 0; // nao ha sinistros de qq jeito
		else
			preco = ele.calculaScore() * (1 + contador); // FUNCAO DA SEGURADORA RECALCULAR SCORE
		Cliente c = Validacao.achaCliente(this, cliente); // pra nao dar problema de null pointer 
		c.setValorSeguro(preco);
		return preco;
	}
	
	public double calcularReceita() {
		double soma = 0;
		Set<String> chaves = mapaClientes.keySet();
		for(String chave : chaves) {
			soma += calcularPrecoSeguroCliente(chave);
		}
		return soma;
	}
	
	public String listarVeiculos(){
		StringBuilder total = new StringBuilder();
		for(Cliente value: mapaClientes.values()){
			total.append(value.listarVeiculos());
		}
		return total.toString().equals("")? "nao ha veiculos associados a seguradora" : total.toString();

	}

	public String toString() {
		return "A seguradora " + nome + " de telefone " + telefone + " localizada no endereco " + endereco + " com email "
				+ email + " possui os seguintes clientes pessoa física:\n " + this.listarClientes("PF") + " e os seguintes clientes do tipo pessoa juridica:\n "
				+this.listarClientes("PJ") + " entre ambos os tipos, possui os seguintes sinistros:\n" + this.listarSinistros() + " e tem uma receita de"
				+ this.calcularReceita() + " reais";
	}
	
	public String toString(int sobrecarga) {
		if(sobrecarga == 1) 
			return "A seguradora " + nome + " de telefone " + telefone + " localizada no endereco " + endereco + " com email "
					+ email + " possui os seguintes clientes pessoa física: " + this.listarClientes("PF") + " e os seguintes clientes do tipo pessoa juridica: "
					+this.listarClientes("PJ") + "e tem uma receita de " + this.calcularReceita() + " reais" ;// só para nao entrar no loop infinito de sinistro chamando seguradora
		
		else 
			return this.toString();
	}
}
