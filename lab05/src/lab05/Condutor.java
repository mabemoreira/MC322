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
    private HashMap <Integer, Sinistro> mapaSinistros;

    public Condutor(String CPF, String nome, String telefone, String endereco, String email, LocalDate dataNasc){
        this.CPF = CPF.replaceAll("[^\\d]","");;
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
    public HashMap<Integer, Sinistro> getMapaSinistros() {
        return mapaSinistros;
    }
    public void setMapaSinistros(HashMap<Integer, Sinistro> mapaSinistros) {
        this.mapaSinistros = mapaSinistros;
    }

    public boolean adicionarSinistro(LocalDate data, String endereco, Seguro seguro){
        Sinistro sinistro = new Sinistro(data, endereco, this, seguro);
        mapaSinistros.put(sinistro.getId(),sinistro);
        seguro.corrigeValor();
        System.out.println("Sinistro cadastrado com sucesso");
        return true;
    }

    public String toString(){
        return "o condutor " + nome + " possui o cpf " + CPF + " nascido no dia " + dataNasc + " reside em " + endereco + " possui o email " + email + " e telefone "
        + telefone;
    }
}
