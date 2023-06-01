package lab05;
import java.time.LocalDate;  
import java.util.HashMap;
import java.lang.StringBuilder;


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

    public boolean cadastrarVeiculo(Veiculo veiculo){
        if(mapaVeiculos.containsValue(veiculo)){
            System.out.println("veiculo ja cadastrado");
            return false;
        }
        mapaVeiculos.put(veiculo.getPlaca(), veiculo);
        return true;
    }

    public boolean removerVeiculo(String placa){
        Veiculo veiculo = mapaVeiculos.remove(placa);
        String resultado = veiculo == null ? "Veiculo nao encontrado" : "Veiculo removido com sucesso";
        System.out.println(resultado);
        return(veiculo != null);
    }

    public String listarVeiculos(){
        StringBuilder sb = new StringBuilder();
        for(Veiculo value : mapaVeiculos.values()){
            sb.append(value.toString());
        }
        if(sb.toString() == ""){
            System.out.println("nao ha veiculos");
            return null;}
        return sb.toString();
    }

   
    public String toString(){
        return "o cliente de nome " + this.getNome() + " possui o cpf " + CPF + " se identifica com o genero "+ genero + " possui nivel de educacao " + educacao + " mora em " + this.getEndereco() + 
        " nasceu em " + dataNasc + " e possui os seguintes veiculos " + this.listarVeiculos();
    }
}
