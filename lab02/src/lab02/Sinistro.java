package lab02;
import java.util.Random;

public class Sinistro {
	private int id;
	private String data;
	private String endereco;
	
	
	public int geraAleatorios() { //poderia ter feito dentro do proprio constructor mas o lab pedia especificamente uma funcao para isso
		Random aleatorio = new Random();
		return aleatorio.nextInt(100000);
	}
	
	public Sinistro(String dataAcidente, String enderecoAcidente) {
		id = geraAleatorios();
		data = dataAcidente;
		endereco = enderecoAcidente;
	}
	
	//feitos pelo eclipse
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public String toString() {
	return "O acidente ocorreu em " + data + " no endereco " + endereco + " e possui o id " + id + ".\n";
	}
	
}
