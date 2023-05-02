package lab04;
import java.util.Date;
import java.util.Random;
import java.util.HashMap;


public class Sinistro {
	private final int ID;
	private Date data;
	private String endereco;
	private Seguradora seguradora;
	private Veiculo veiculo;
	private Cliente cliente;

	public Sinistro (Date data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente){
		this.ID = geraID(seguradora);
		this.data = data;
		this.endereco = endereco;
		this.seguradora = seguradora;
		this.veiculo = veiculo;
		this.cliente = cliente;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public Seguradora getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getID() {
		return ID;
	}

	public int geraID(Seguradora seguradora) {
		int parcial;
		Random aleatorio = new Random();
		parcial = aleatorio.nextInt(100000);
		HashMap<Integer, Sinistro> mapaSinistros = seguradora.getMapaSinistros();
		while(mapaSinistros.containsKey(parcial)) {
			parcial = aleatorio.nextInt(100000);
		}
		return parcial;
	}
	
	public String toString() {
		return "O sinistro de ID " + ID + " ocorreu em " + data + " com um veiculo " + veiculo + " que pertence a " + cliente
				+ " é assegurado pela seguradora " + seguradora;
	}
// dava p/ fazer sem o segundo to string se o sinistro so fosse impresso dentro de seguradora
	
	public String toString(int sobrecarga) {
		if(sobrecarga == 0)
			return "O sinistro de ID " + ID + " ocorreu em " + data + " com um veiculo " + veiculo + " que pertence a " + cliente;
		else 
			return this.toString();
	}
}
