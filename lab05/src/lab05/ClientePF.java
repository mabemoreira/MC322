package lab05;
import java.time.LocalDate;  
import java.util.HashMap; 

public class ClientePF extends Cliente{
    private final String CPF;
    private String genero;
    private String educacao;
    private LocalDate dataNasc;
    private HashMap <String, Veiculo> mapaVeiculos;

    public ClientePF(String nome, String endereco, String telefone, String email, String CPF, String genero, String educacao, LocalDate dataNasc){
        super(nome, telefone, endereco, email);
        this.CPF = CPF;
        this.genero = genero;
        this.educacao = educacao;
        this.dataNasc = dataNasc;
        mapaVeiculos = new HashMap <String, Veiculo> ();
    }

    public String getCPF() {
        return CPF;
    }
    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getEducacao() {
        return educacao;
    }
    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }
    public LocalDate getDataNasc() {
        return dataNasc;
    }
    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }
    public HashMap<String, Veiculo> getMapaVeiculos() {
        return mapaVeiculos;
    }
    public void setMapaVeiculos(HashMap<String, Veiculo> mapaVeiculos) {
        this.mapaVeiculos = mapaVeiculos;
    }
}
