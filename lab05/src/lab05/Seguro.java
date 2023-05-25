package lab05;
import java.time.LocalDate;  // finalmente descobri que nao estava no java.util
import java.util.HashMap; 

public abstract class Seguro {
    private static int contador = 0;
    private final int id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Seguradora seguradora;
    private HashMap <String, Sinistro> mapaSinistros;
    private HashMap <String, Condutor> mapaCondutores;
    private int valorMensal;

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
        mapaSinistros = new HashMap<String, Sinistro>();
        mapaCondutores = new HashMap<String, Condutor>();
        // valormensal = calcula valor seguro;

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
    public HashMap<String, Sinistro> getMapaSinistros() {
        return mapaSinistros;
    }
    public void setMapaSinistros(HashMap<String, Sinistro> mapaSinistros) {
        this.mapaSinistros = mapaSinistros;
    }
    public HashMap<String, Condutor> getMapaCondutores() {
        return mapaCondutores;
    }
    public void setMapaCondutores(HashMap<String, Condutor> mapaCondutores) {
        this.mapaCondutores = mapaCondutores;
    }
    public int getValorMensal() {
        return valorMensal;
    }
    public void setValorMensal(int valorMensal) {
        this.valorMensal = valorMensal;
    }

}
