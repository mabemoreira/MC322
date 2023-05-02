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
		valorSeguro = 0; // no constructor, todos tem 0 carros;
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
	
	public void setValorSeguro() {
		this.valorSeguro = calculaScore();
	}
	
	public double getValorSeguro() {
		return valorSeguro;
	}
	
	public HashMap <String, Veiculo> getMapaVeiculos() {
		return mapaVeiculos;
	}
	
	public String listarVeiculos() {
		StringBuilder total = new StringBuilder();
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
	return	" O cliente " + nome + " que reside em " + endereco + " possui o(s) seguinte(s) veiculo(s): " + this.listarVeiculos();
	}
	
	public double calculaScore() {
		Cliente novo = null;
		if(this instanceof ClientePF) { // era pra ter calcula score no tipo cliente? pq a ideia do polimorfismo nao é o compilador fazer isso automaticamente?
			novo = (ClientePF) this;
		}
		else if(this instanceof ClientePJ) {
			novo = (ClientePJ) this;
		}
		double score = novo.calculaScore();
		return score;
}

}
