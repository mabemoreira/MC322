package lab06;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;


public class Seguradora {
    // precisa ter exclusao no menu tb?
    // a gente vai usar o teclado + os arquivos? n entendi
    private final String CNPJ;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private HashMap<String, Cliente> mapaClientes;
    private LinkedList <Seguro> listaSeguros; // notei no outro lab q pra seguro em especifico n fazia tanta diferença escolher map ou list pq ia buscar por veiculo/frota
    private ArquivoClientePF arquivoClientePF;
    private ArquivoClientePJ arquivoClientePJ;
    private ArquivoSeguro arquivoSeguro; // ele eu gero so no final???
    // private ArquivoSinistro arquivoSinistro;
// NAO VAI TER VALOR MENSAL????

    public Seguradora(String CNPJ, String nome, String telefone, String email, String endereco, ArquivoClientePF arquivopf, ArquivoClientePJ arquivopj){
        this.CNPJ = CNPJ.replaceAll("^[0-9]", "");
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        mapaClientes = new HashMap<String, Cliente>();
        listaSeguros = new LinkedList<Seguro>();
        arquivoClientePF = arquivopf;
        arquivoClientePJ = arquivopj; // eu coloco ja na inicializacao?
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

    public LinkedList<Seguro> getListaSeguros() {
        return listaSeguros;
    }

    public void setListaSeguros(LinkedList<Seguro> listaSeguros) {
        this.listaSeguros = listaSeguros;
    }

    public ArquivoClientePF getArquivoClientePF() {
        return arquivoClientePF;
    }

    public void setArquivoClientePF(ArquivoClientePF arquivoClientePF) {
        this.arquivoClientePF = arquivoClientePF;
    }

    public ArquivoClientePJ getArquivoClientePJ() {
        return arquivoClientePJ;
    }

    public void setArquivoClientePJ(ArquivoClientePJ arquivoClientePJ) {
        this.arquivoClientePJ = arquivoClientePJ;
    }

    public ArquivoSeguro getArquivoSeguro() {
        return arquivoSeguro;
    }

    public void setArquivoSeguro(ArquivoSeguro arquivoSeguro) {
        this.arquivoSeguro = arquivoSeguro;
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
        listaSeguros.add(seguro);
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
        listaSeguros.add(seguro);
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
            for(Seguro value: listaSeguros){
                if(value instanceof SeguroPF){
                   SeguroPF seguropf = (SeguroPF)value;
                   if(seguropf.getCliente().getCPF().equals(novocliente)){
                        lista.add(seguropf);
                   }
                }
            }
        }
        else if(tipo.toUpperCase().equals("PJ")){
            for(Seguro value: listaSeguros){
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
        for(Seguro value: listaSeguros){
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
            for(Seguro value: listaSeguros){
                soma+= value.getValorMensal();
            }
            return soma;
        }

        public boolean cancelarSeguro(Veiculo v){
            boolean flag = false;
            for(Seguro values: listaSeguros){
                if(values instanceof SeguroPF){
                    SeguroPF segpf = (SeguroPF) values;
                    if(segpf.getVeiculo() == v){
                        listaSeguros.remove(segpf);
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
            for(Seguro values: listaSeguros){
                if(values instanceof SeguroPJ){
                    SeguroPJ segpJ = (SeguroPJ) values;
                    if(segpJ.getFrota() == frota){
                        listaSeguros.remove(segpJ);
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
                for(Seguro value: listaSeguros){
                    if(value instanceof SeguroPF){
                        SeguroPF seguropf = (SeguroPF) value;
                        if(seguropf.getCliente().getCPF() == cliente.replaceAll("[^0-9]", "")){
                            flag = true;
                            listaSeguros.remove(seguropf);
                        }
                    }
                }
            }
            else if(tipo.toUpperCase().equals("PJ")){
                for(Seguro valor: listaSeguros){
                    if(valor instanceof SeguroPJ){
                        SeguroPJ seguropj = (SeguroPJ) valor;
                        if(seguropj.getCliente().getCNPJ() == cliente.replaceAll("[^0-9]", "")){
                            flag = true;
                            listaSeguros.remove(seguropj);
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
            for(Seguro value: listaSeguros){
                sb.append(value.toString());
                sb.append("\n");
            }
          return sb.toString().equals("") ? "nao ha seguros cadastrados" : sb.toString();
        }

        public void lerDados(){
            // AONDE??? DE QUEM?? EU JA NAO RECEBO O ARQUIVO???
        }

        public void gravarDados(){
            // eu n preciso de parametro nenhum ne???
        }

        public String toString(){
            return "a seguradora " + nome + " de CNPJ " + CNPJ + " localizada em " + endereco + " possui o telefone " + telefone + " e o email " + email + " possui os " 
           + " seguintes clientes : " + this.listarClientes() + " e os seguintes seguros " + this.listarSeguros() + " possuindo uma receita de " + this.calcularReceita();
        }
}



