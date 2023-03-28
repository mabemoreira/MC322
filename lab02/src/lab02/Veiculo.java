package lab02;

public class Veiculo {
	private String placa;
	private String marca;
	private String modelo;
	
	public Veiculo(String placaCarro, String marcaCarro, String modeloCarro) {
		placa = placaCarro;
		marca = marcaCarro;
		modelo = modeloCarro;
	}
	
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public String toString() {
		return "O carro eh do modelo " + modelo + " da marca " + marca + " e tem placa " + placa +".";
	}
}
