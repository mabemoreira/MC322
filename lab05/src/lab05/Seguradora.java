package lab05;
import java.util.LinkedList;
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
                sb.append("\n");
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

    public boolean gerarSeguro(LocalDate dataInicio, LocalDate dataFim, ClientePF cliente, Veiculo veiculo){
        SeguroPF seguro = new SeguroPF(dataInicio, dataFim, this, veiculo, cliente);
        if(mapaSeguro.containsValue(seguro)){
            System.out.println("Seguro ja cadastrado");
            return false;
        }
        mapaSeguro.put(seguro.getId(), seguro);
        System.out.println("Seguro cadastrado com sucesso");
        return true;
    }

    public boolean cadastrarCliente (Cliente cliente) {
        if(mapaClientes.containsValue(cliente)){
            System.out.println("Cliente ja cadastrado");
            return false;
        }
		if(cliente instanceof ClientePF) {
			ClientePF clientecast = (ClientePF) cliente;
			mapaClientes.put(clientecast.getCPF(), clientecast);
			return true;
		}
		else if(cliente instanceof ClientePJ) { // o lab me garante que todos sao pf ou pj
			ClientePJ clientecast = (ClientePJ) cliente;
			mapaClientes.put(clientecast.getCNPJ(), clientecast);
		return true;
	}
    return false ;// nunca chega aqui

}

    public boolean removerCliente (String cliente){
        if(!(mapaClientes.containsKey(cliente))){
            System.out.println("Cliente nao cadastrado");
            return false;
        }
        mapaClientes.remove(cliente);
        return true; // bem mais facil do q eu tava fazendo no lab4
    }

    public LinkedList <Seguro> getSegurosPorCliente(String cliente, String tipo){
        LinkedList <Seguro> lista = new LinkedList<Seguro>();
        if(tipo == "PF"){
            for(Seguro value: mapaSeguro.values()){
                if(value instanceof SeguroPF){
                   SeguroPF seguropf = (SeguroPF)value;
                   if(seguropf.getCliente().getCPF() == cliente){
                        lista.add(seguropf);
                   }
                }
            }
        }
        else if(tipo == "PJ"){
            for(Seguro value: mapaSeguro.values()){
                if(value instanceof SeguroPJ){
                   SeguroPJ seguropj = (SeguroPJ)value;
                   if(seguropj.getCliente().getCNPJ() == cliente){
                        lista.add(seguropj);
                   }
                }
            }
        }
        if(lista.size() == 0){
            System.out.println("esse cliente nao possui seguros");
            return null;
        }
        return lista;
    }

   public LinkedList <Sinistro> getSinistrosporCliente(String cliente){
        LinkedList <Sinistro> lista = new LinkedList<Sinistro>();
        for(Seguro value: mapaSeguro.values()){
            for(Sinistro sin : value.getMapaSinistros().values()){
                if(sin.getCondutor().getCPF() == cliente){
                    lista.add(sin);
                }
            }
        }
        if(lista.size() == 0){
            System.out.println("Esse cliente nao possui sinistros cadastrados nessa seguradora");
            return null;
        }
        return lista;
   }

        public int calcularReceita(){
            int soma = 0;
            for(Seguro value: mapaSeguro.values()){
                soma+= value.getValorMensal();
            }
            return soma;
        }


        public boolean cancelarSeguro(int a, String id, String tipo){ // int so ta ai pra diferenciar os metodos
            boolean flag = false;
            if(tipo == "PF"){
                for(Seguro value: mapaSeguro.values()){
                    if(value instanceof SeguroPF){
                        SeguroPF seguropf = (SeguroPF) value;
                        if(seguropf.getVeiculo().getPlaca() == id){
                            flag = true;
                            mapaSeguro.remove(seguropf.getId());
                        }
                    }
                }
            }
            else if(tipo == "PJ"){
                for(Seguro valor: mapaSeguro.values()){
                    if(valor instanceof SeguroPJ){
                        SeguroPJ seguropj = (SeguroPJ) valor;
                        if(seguropj.getFrota().getCode() == id){
                            flag = true;
                            mapaSeguro.remove(seguropj.getId());
                        }
                    }
                }
            }
            if(flag == false){
                System.out.println("O veiculo ou frota nao pode ser localizado");
                return false;
            }
            System.out.println("O seguro desse veiculo ou frota foi removido com sucesso");
            return true;
        }

        public boolean cancelarSeguro(String cliente, String tipo){
            boolean flag = false;
            if(tipo == "PF"){
                for(Seguro value: mapaSeguro.values()){
                    if(value instanceof SeguroPF){
                        SeguroPF seguropf = (SeguroPF) value;
                        if(seguropf.getCliente().getCPF() == cliente){
                            flag = true;
                            mapaSeguro.remove(seguropf.getId());
                        }
                    }
                }
            }
            else if(tipo == "PJ"){
                for(Seguro valor: mapaSeguro.values()){
                    if(valor instanceof SeguroPJ){
                        SeguroPJ seguropj = (SeguroPJ) valor;
                        if(seguropj.getCliente().getCNPJ() == cliente){
                            flag = true;
                            mapaSeguro.remove(seguropj.getId());
                        }
                    }
                }
            }
            if(flag == false){
                System.out.println("Esse cliente nao possui seguros");
                return false;
            }
            System.out.println("Seguros removidos com sucesso");
            return true;
        }

        public String listarSeguros(){
            StringBuilder sb = new StringBuilder();
            for(Seguro value: mapaSeguro.values()){
                sb.append(value.toString());
                sb.append("\n");
            }
            if(sb.toString() == ""){
                System.out.println("Nao ha seguros cadastrados");
                return null;
            }
            else{
                return sb.toString();
            }
        }

        public String toString(){
            return "a seguradora " + nome + " de CNPJ " + CNPJ + " localizada em " + endereco + " possui o telefone " + telefone + " e o email " + email + " possui os " 
           + " seguintes clientes : " + this.listarClientes() + " e os seguintes seguros " + this.listarSeguros();
        }
}
