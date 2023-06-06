package lab05;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class SeguroPJ extends Seguro{
    private Frota frota;
    private ClientePJ cliente;


    public SeguroPJ(Frota frota, ClientePJ cliente, LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora){
        super(dataInicio, dataFim, seguradora);
        this.frota = frota;
        this.cliente = cliente;
    }

    public Frota getFrota() {
        return frota;
    }

    public void setFrota(Frota frota) {
        this.frota = frota;
    }

    public ClientePJ getCliente() {
        return cliente;
    }

    public void setCliente(ClientePJ cliente) {
        this.cliente = cliente;
    }

    public double calcularValor(Cliente cliente){
        ClientePJ clientepj = (ClientePJ) cliente;
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataFund = clientepj.getDataFund();
        int qtdfunc = this.getMapaCondutores().size();
        int qtdveiculos = frota.getMapaVeiculos().size();
        ArrayList <Integer> pair = numeroSinistros();
        return (CalcSeguro.VALOR_BASE.getFator() * ( 10 + (qtdfunc)/10.0) * (1 + 1/(qtdveiculos + 2.0)) * (1 + 1 / (ChronoUnit.YEARS.between(dataFund, dataAtual) + 2.0))
        *(2 + (pair.get(0)/10.0)) * ( 5 + (pair.get(1)/10.0)) );
    }

    public void corrigeValor(){
        this.setValorMensal(calcularValor(cliente));
    }

    public String toString(){
        return " o seguro de id " + this.getId() + " com inicio em " + this.getDataInicio() + this.getDataFim() + " cobre a seguinte frota " + frota +
        " que pertence ao seguinte cliente " + cliente + " e possui os seguintes condutores " + this.listarCondutores();
    }
}


