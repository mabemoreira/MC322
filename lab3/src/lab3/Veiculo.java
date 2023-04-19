package lab3;

public class Veiculo {
	private String placa;
	private String marca;
	private String modelo;
	private int anoFabricacao;
	
	public Veiculo(String placa, String marca, String modelo, int anoFabricacao) {
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.anoFabricacao = anoFabricacao;
	}

	public String getPlaca() { // um veiculo pode mudar de placa, como quando aconteceu a transicao para as placas mercosul, mas o resto é imutavel apos sua criação
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public String getMarca() {
		return marca;
	}

	public int getAnoFabricacao() {
		return anoFabricacao;
	}
	
	
	public String toString() {
		return "o veiculo de placa " + placa + " da marca " + marca + " de modelo " + modelo + " que foi fabricado em " + anoFabricacao;
	}
	
}
