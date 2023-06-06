package lab05;
import java.time.LocalDate;  // finalmente descobri que nao estava no java.util
import java.util.HashMap; 
import java.util.ArrayList;

public abstract class Seguro {
    private static int contador = 0;
    private final int id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Seguradora seguradora;
    private HashMap <Integer, Sinistro> mapaSinistros;
    private HashMap <String, Condutor> mapaCondutores;
    private double valorMensal;

    public Seguro(){
        id = -1;
        System.out.println("Sem argumentos"); // de novo, nunca vai chegar aqui, mas o compilador pede;
    }

    public Seguro(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora){
        id = contador;
        contador++;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
        mapaSinistros = new HashMap<Integer, Sinistro>();
        mapaCondutores = new HashMap<String, Condutor>();
        valorMensal = 0;

    }
    public int getId() {
        return id;
    }
    public LocalDate getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }
    public LocalDate getDataFim() {
        return dataFim;
    }
    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
    public Seguradora getSeguradora() {
        return seguradora;
    }
    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }
    public HashMap<Integer, Sinistro> getMapaSinistros() {
        return mapaSinistros;
    }
    public void setMapaSinistros(HashMap<Integer, Sinistro> mapaSinistros) {
        this.mapaSinistros = mapaSinistros;
    }
    public HashMap<String, Condutor> getMapaCondutores() {
        return mapaCondutores;
    }
    public void setMapaCondutores(HashMap<String, Condutor> mapaCondutores) {
        this.mapaCondutores = mapaCondutores;
    }
    public double getValorMensal() {
        return valorMensal;
    }
    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public abstract void corrigeValor();

    public boolean autorizarCondutor(Condutor condutor){
        if(mapaCondutores.containsKey(condutor.getCPF())){
            System.out.println("Esse condutor jaestá autorizado");
            return false;
        }
        mapaCondutores.put(condutor.getCPF() ,condutor);
        corrigeValor();
        System.out.println("Condutor autorizado com sucesso");
        return true;
    }

    public boolean desautorizarCondutor(String condutor){
        Condutor conductor = mapaCondutores.remove(condutor);
        if(conductor == null){
            System.out.println("Condutor nao pode ser removido");
        }
        else
        corrigeValor();
        System.out.println("Condutor removido com sucesso");
        return (conductor != null);
    }
  
    public boolean gerarSinistro(LocalDate data, Condutor condutor, String endereco){
        Sinistro sinistro = new Sinistro(data, endereco, condutor, this);
        if(mapaSinistros.containsKey(sinistro.getId())){
            System.out.println("Esse sinistro ja foi gerado"); // so por precaucao ja q os ids sao unicos nunca deve chegar aqui
            return false;
        }
        mapaSinistros.put(sinistro.getId(), sinistro);
        return true;
    }

    public ArrayList<Integer> numeroSinistros(){
        ArrayList<Integer> pair = new ArrayList<>();
        if(mapaSinistros.size() == 0){
            for( int i = 0; i < 2; i++){
                pair.add(i,0);
            }
            return pair;
        }
        pair.add(0, mapaSinistros.size());
        int condnum = 0;
        for(Condutor cond: mapaCondutores.values()){
            condnum += cond.getMapaSinistros().size();
        }
        pair.add(1, condnum);
        return pair;
    }

    public String listarCondutores(){
        StringBuilder sb = new StringBuilder();
            for(Condutor value: mapaCondutores.values()){
                sb.append(value.toString());
                sb.append("\n");
            }
            if(sb.toString() == ""){
                System.out.println("Nao ha condutores");
                return null;
            }
            else{
                return sb.toString();
            }
        }
    

    public abstract double calcularValor(Cliente cliente);

   
}
