package lab05;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.StringBuilder;
import java.time.LocalDate;

public class Seguradora {
    private final String CNPJ;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private HashMap <String,Cliente> mapaClientes;
    private HashMap <Integer,Seguro> mapaSeguro;

    public Seguradora(String CNPJ, String nome, String telefone, String email, String endereco){
        this.CNPJ = CNPJ;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        mapaClientes = new HashMap <String, Cliente> ();
        mapaSeguro = new HashMap <Integer, Seguro> ();
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

    public HashMap<Integer, Seguro> getMapaSeguro() {
        return mapaSeguro;
    }

    public void setMapaSeguro(HashMap<Integer, Seguro> mapaSeguro) {
        this.mapaSeguro = mapaSeguro;
    }

    public String listarClientes(){
        StringBuilder sb = new StringBuilder();
        for(Cliente value: mapaClientes.values()){
            sb.append(value.toString());
        }
        if(sb.toString() == ""){
            System.out.println("Nao ha clientes cadastrados");
            return null;
        }
        else{
            return sb.toString();
        }
    }


    public String listarClientes(String tipo){
        StringBuilder sb = new StringBuilder();
        for(Cliente value: mapaClientes.values()){
            if(tipo.toUpperCase() == "PF" && value instanceof ClientePF)
                sb.append(value.toString());
            else if(tipo.toUpperCase() == "PJ" && value instanceof ClientePJ)
                sb.append(value.toString());
        }
        if(sb.toString() == ""){
            System.out.println("Nao ha clientes desse tipo cadastrados");
            return null;
        }
        else{
            return sb.toString();
        }
    }

    public boolean gerarSeguro(LocalDate dataInicio, LocalDate dataFim, Frota frota, ClientePJ cliente){
        SeguroPJ seguro = new SeguroPJ(frota, cliente, dataInicio, dataFim, this);
        if(mapaSeguro.containsValue(seguro)){
            System.out.println("Seguro ja cadastrado");
            return false;
        }
        mapaSeguro.put(seguro.getId(),seguro);
        System.out.println("Seguro cadastrado com sucesso");
        return true;
    }



}
