package lab05;
import java.time.LocalDate;

public class Sinistro {
    private static int contador = 0; // agora que o PAD me disse usarei sempre que puder
    private final int id;
    private LocalDate data;
    private String endereco;
    private Condutor condutor;
    private Seguro seguro;

    public Sinistro(LocalDate data, String endereco, Condutor condutor, Seguro seguro){
        id = contador;
        contador++;
        this.data = data;
        this.endereco = endereco;
        this.condutor = condutor;
        this.seguro = seguro;
    }

    public static int getContador() {
        return contador;
    }
    public static void setContador(int contador) {
        Sinistro.contador = contador;
    }
    public int getId() {
        return id;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public Condutor getCondutor() {
        return condutor;
    }
    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }
    public Seguro getSeguro() {
        return seguro;
    }
    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }


}
