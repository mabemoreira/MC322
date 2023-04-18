package lab3;
import java.util.HashMap;

public class Cliente {
	private String nome;
	private String endereco;
	private HashMap <String, Veiculo> mapaVeiculos;
	
	public Cliente(String nome, String endereco) {
		this.setNome(nome);
		this.setEndereco(endereco);
		mapaVeiculos = new HashMap<String, Veiculo>();
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
	
	public HashMap <String, Veiculo> getMapaVeiculos() {
		return mapaVeiculos;
	}
	
	public String listarVeiculos() {
		String total ="";
		for(Veiculo value: mapaVeiculos.values()) {
			total.concat(this.toString());
			total.concat(" ");
		}
		return total.equals("")? "nao ha veiculos" : total;
	}
	
	public boolean adicionaVeiculo(Veiculo carro) {
		mapaVeiculos.put(carro.getPlaca(), carro);
		return true;
	}
	
	public boolean removeVeiculo(String placa) { // precisa receber a placa pq é unica coisa que diferencia carros que podem ser iguais
	Veiculo teste =	mapaVeiculos.remove(placa);
	return teste == null ? false : true; 
	}
	
	public String toString() {
		" O cliente " + nome + " que reside em " + endereco + " possui o(s) seguinte(s) veiculo(s): " + this.listarVeiculos();
	}
	
}
