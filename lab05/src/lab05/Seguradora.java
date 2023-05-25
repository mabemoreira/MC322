package lab05;
import java.util.ArrayList;
import java.util.HashMap;

public class Seguradora {
    private final String CNPJ;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private HashMap <String,Cliente> mapaClientes;
    private HashMap <String,Seguro> mapaSeguro;

    public Seguradora(String CNPJ, String nome, String telefone, String email, String endereco){
        this.CNPJ = CNPJ;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        mapaClientes = new HashMap <String, Cliente> ();
        mapaSeguro = new HashMap <String, Seguro> ();
    }

    public String getCNPJ() {
        return CNPJ;
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

    public HashMap<String, Cliente> getMapaClientes() {
        return mapaClientes;
    }

    public void setMapaClientes(HashMap<String, Cliente> mapaClientes) {
        this.mapaClientes = mapaClientes;
    }

    public HashMap<String, Seguro> getMapaSeguro() {
        return mapaSeguro;
    }

    public void setMapaSeguro(HashMap<String, Seguro> mapaSeguro) {
        this.mapaSeguro = mapaSeguro;
    }
}
