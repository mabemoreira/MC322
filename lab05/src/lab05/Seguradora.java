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
        this.CNPJ = CNPJ.replaceAll("[^\\d]","");
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
      return sb.toString().equals("")? "nao ha clientes cadastrados" : sb.toString();
    }


    public String listarClientes(String tipo){
        StringBuilder sb = new StringBuilder();
        for(Cliente value: mapaClientes.values()){
            if(tipo.toUpperCase().equals("PF") && value instanceof ClientePF)
                sb.append(value.toString());
            else if(tipo.toUpperCase().equals("PJ") && value instanceof ClientePJ)
                sb.append(value.toString());
                sb.append("\n");
        }
        return sb.toString().equals("") ? "nao ha clientes desse tipo cadastrados ": sb.toString();
    }

    public boolean gerarSeguro(LocalDate dataInicio, LocalDate dataFim, Frota frota, ClientePJ cliente){
        SeguroPJ seg = Validacao.achaSeguroPJ(this, frota);
        if(seg != null){
            System.out.println("Seguro ja cadastrado");
            return false;
        }
        SeguroPJ seguro = new SeguroPJ(frota, cliente, dataInicio, dataFim, this);
        mapaSeguro.put(seguro.getId(),seguro);
        seguro.corrigeValor();
        System.out.println("Seguro cadastrado com sucesso");
        return true;
    }

    public boolean gerarSeguro(LocalDate dataInicio, LocalDate dataFim, ClientePF cliente, Veiculo veiculo){
        SeguroPF seg = Validacao.achaSeguroPF(this, veiculo);
        if(seg != null){
            System.out.println("Seguro ja cadastrado");
            return false;
        }
        SeguroPF seguro = new SeguroPF(dataInicio, dataFim, this, veiculo, cliente);
        mapaSeguro.put(seguro.getId(), seguro);
        seguro.corrigeValor();
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
        if(!(mapaClientes.containsKey(cliente.replaceAll("[^0-9]", "")))){
            System.out.println("Cliente nao cadastrado");
            return false;
        }
        mapaClientes.remove(cliente);
        return true; // bem mais facil do q eu tava fazendo no lab4
    }

    public LinkedList <Seguro> getSegurosPorCliente(String cliente, String tipo){
        String novocliente = cliente.replaceAll("[^0-9]", "");
        LinkedList <Seguro> lista = new LinkedList<Seguro>();
        if(tipo.toUpperCase().equals("PF")){
            for(Seguro value: mapaSeguro.values()){
                if(value instanceof SeguroPF){
                   SeguroPF seguropf = (SeguroPF)value;
                   if(seguropf.getCliente().getCPF().equals(novocliente)){
                        lista.add(seguropf);
                   }
                }
            }
        }
        else if(tipo.toUpperCase().equals("PJ")){
            for(Seguro value: mapaSeguro.values()){
                if(value instanceof SeguroPJ){
                   SeguroPJ seguropj = (SeguroPJ)value;
                   if(seguropj.getCliente().getCNPJ().equals(novocliente)){
                        lista.add(seguropj);
                   }
                }
            }
        }
        if(lista.size() == 0){
            return null;
        }
        return lista;
    }


    public String listaSegporCliente(String cliente, String tipo){
        StringBuilder sb = new StringBuilder();
        LinkedList<Seguro> listaseg = getSegurosPorCliente(cliente, tipo);
        if(listaseg == null){
            return ("esse cliente nao possui seguros");
        }
        for(Seguro value: listaseg){
            sb.append(value.toString());
            sb.append("\n");
        }
        return sb.toString();
    }


    public String listarSinistrosporCliente(String id){
        LinkedList<Sinistro> listasin = this.getSinistrosporCliente(id);
        StringBuilder sb = new StringBuilder();
        for(Sinistro value: listasin){
            sb.append(value.toString());
            sb.append("\n");
        }
        return sb.toString().equals("") ? "Esse cliente nao possui sinistros" : sb.toString();
    }

    public String listarSinistros(){
        StringBuilder sb = new StringBuilder();
        if(mapaClientes.size() == 0){
            return(" essa seguradora nao possui clientes");
        }
        for(Cliente value: mapaClientes.values()){
            if(value instanceof ClientePF){
                ClientePF clientepf = (ClientePF) value;
                sb.append(listarSinistrosporCliente(clientepf.getCPF()));
            }
                else if(value instanceof ClientePJ){
                ClientePJ clientepf = (ClientePJ) value;
                sb.append(listarSinistrosporCliente(clientepf.getCNPJ()));
                }
                 if(sb.toString().contains("Esse cliente nao possui sinistros")){
                        int pos = sb.indexOf("Esse cliente nao possui sinistros");
                        sb.delete(pos, pos+"Esse cliente nao possui sinistros".length());
                        continue;
                    }
                sb.append("\n");
                }
                return (sb.toString().equals("")) ? "os clientes dessa seguradora nao possuem sinistros" : sb.toString();
                
            }

        
  


   public LinkedList <Sinistro> getSinistrosporCliente(String cliente){
        String novocliente = cliente.replaceAll("[^0-9]", "");
        LinkedList <Sinistro> lista = new LinkedList<Sinistro>();
        for(Seguro value: mapaSeguro.values()){
            for(Sinistro sin : value.getMapaSinistros().values()){
                if(sin.getCondutor().getCPF() == novocliente){
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

        public boolean cancelarSeguro(Veiculo v){
            boolean flag = false;
            for(Seguro values: mapaSeguro.values()){
                if(values instanceof SeguroPF){
                    SeguroPF segpf = (SeguroPF) values;
                    if(segpf.getVeiculo() == v){
                        mapaSeguro.remove(segpf.getId());
                        flag = true;
                        break;
                    }
                }
            }
            String resultado = flag ? "seguro removido com sucesso" : "seguro nao pode ser localizado";
            System.out.println(resultado);
            return(flag);
        }

        public boolean cancelarSeguro(Frota frota){
            boolean flag = false;
            for(Seguro values: mapaSeguro.values()){
                if(values instanceof SeguroPJ){
                    SeguroPJ segpJ = (SeguroPJ) values;
                    if(segpJ.getFrota() == frota){
                        mapaSeguro.remove(segpJ.getId());
                        flag = true;
                        break;
                    }
                }
            }
            String resultado = flag ? "seguro removido com sucesso" : "seguro nao pode ser localizado";
            System.out.println(resultado);
            return(flag);
        }

        public boolean cancelarSeguro(String cliente, String tipo){
            boolean flag = false;
            if(tipo.toUpperCase().equals("PF")){
                for(Seguro value: mapaSeguro.values()){
                    if(value instanceof SeguroPF){
                        SeguroPF seguropf = (SeguroPF) value;
                        if(seguropf.getCliente().getCPF() == cliente.replaceAll("[^0-9]", "")){
                            flag = true;
                            mapaSeguro.remove(seguropf.getId());
                        }
                    }
                }
            }
            else if(tipo.toUpperCase().equals("PJ")){
                for(Seguro valor: mapaSeguro.values()){
                    if(valor instanceof SeguroPJ){
                        SeguroPJ seguropj = (SeguroPJ) valor;
                        if(seguropj.getCliente().getCNPJ() == cliente.replaceAll("[^0-9]", "")){
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
          return sb.toString().equals("") ? "nao ha seguros cadastrados" : sb.toString();
        }

        public String toString(){
            return "a seguradora " + nome + " de CNPJ " + CNPJ + " localizada em " + endereco + " possui o telefone " + telefone + " e o email " + email + " possui os " 
           + " seguintes clientes : " + this.listarClientes() + " e os seguintes seguros " + this.listarSeguros() + " possuindo uma receita de " + this.calcularReceita();
        }
}
