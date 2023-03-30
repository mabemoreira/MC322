package lab03;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class Sinistro {
	private final int ID;
	private String data;
	private String endereco;
	private Seguradora seguradora;
	private Veiculo veiculo;
	private Cliente cliente;

	public Sinistro (String data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente){
		this.ID = geraID(seguradora);
		this.setData(data);
		this.endereco = endereco;
		this.seguradora = seguradora;
		this.veiculo = veiculo;
		this.cliente = cliente;
	}
	
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
	public int getID() {
		return ID;
	}

	public int geraID(Seguradora seguradora) {
		int parcial;
		Random aleatorio = new Random();
		parcial = aleatorio.nextInt(100000);
		ArrayList <Sinistro> sinistros = seguradora.getlistaSinistros;
		
		
		
	}
}