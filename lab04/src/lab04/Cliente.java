package lab04;
import java.util.HashMap;

public class Cliente {
	private String nome;
	private String endereco;
	private HashMap <String, Veiculo> mapaVeiculos;
	private double valorSeguro;
	
	public Cliente(String nome, String endereco) {
		this.nome = nome;
		this.endereco = endereco;
		mapaVeiculos = new HashMap<String, Veiculo>(); 
		valorSeguro = 0; 
		}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public void setValorSeguro(double valorSeguro) {
		this.valorSeguro = valorSeguro;
	}
	
	public double getValorSeguro() {
		return valorSeguro;
	}
	
	public HashMap <String, Veiculo> getMapaVeiculos() {
		return mapaVeiculos;
	}

	public void setMapaVeiculos(HashMap <String, Veiculo> mapaVeiculos){
		this.mapaVeiculos = mapaVeiculos;
	}
	
	public String listarVeiculos() {
		StringBuilder total = new StringBuilder();
		total.append("Os veiculos do cliente sao: ");
		for(Veiculo value: mapaVeiculos.values()) {
			total.append(value.toString());
			total.append("\n");
		}
		return total.toString().equals("")? "nao ha veiculos" : total.toString();
	}
	
	public boolean adicionaVeiculo(Veiculo carro) {
		mapaVeiculos.put(carro.getPlaca(), carro);
		valorSeguro = calculaScore();
		return true;
	}
	
	public boolean removeVeiculo(String placa) { // precisa receber a placa pq é unica coisa que diferencia carros que podem ser iguais
	Veiculo teste =	mapaVeiculos.remove(placa);
	return teste == null ? false : true; 
	}
	
	public String toString() {
	return	" O cliente " + nome + " que reside em " + endereco + " possui o(s) seguinte(s) veiculo(s):\n " + this.listarVeiculos() 
	+ "e tem o seguinte valor de seguro" + valorSeguro;
	}
	
	public double calculaScore() {
		Cliente novo = null;
		if(this instanceof ClientePF) { 
			novo = (ClientePF) this;
		}
		else if(this instanceof ClientePJ) {
			novo = (ClientePJ) this;
		}
		double score = novo.calculaScore();
		return score;
}
}
