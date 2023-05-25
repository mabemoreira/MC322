package lab05;
import java.time.LocalDate;    
import java.util.HashMap;


public class Condutor {
    private final String CPF;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private LocalDate dataNasc;
    private HashMap <String, Sinistro> mapaSinistros;

    public Condutor(String CPF, String nome, String telefone, String endereco, String email, LocalDate dataNasc){
        this.CPF = CPF;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.dataNasc = dataNasc;
    }


    public String getCPF() {
        return CPF;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDate getDataNasc() {
        return dataNasc;
    }
    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }
    public HashMap<String, Sinistro> getMapaSinistros() {
        return mapaSinistros;
    }
    public void setMapaSinistros(HashMap<String, Sinistro> mapaSinistros) {
        this.mapaSinistros = mapaSinistros;
    }
}
