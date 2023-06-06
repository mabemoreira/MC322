package lab05;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.util.ArrayList;

public class SeguroPF extends Seguro{
    private Veiculo veiculo;
    private ClientePF cliente;


    public SeguroPF(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, Veiculo veiculo, ClientePF cliente){
        super(dataInicio, dataFim, seguradora);
        this.veiculo = veiculo;
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    public ClientePF getCliente() {
        return cliente;
    }
    public void setCliente(ClientePF cliente) {
        this.cliente = cliente;
    }

    public int getqtdVeiculos(){
        int soma = 0;
        for(Seguro value: this.getSeguradora().getMapaSeguro().values()){
            if(value instanceof SeguroPF){
                SeguroPF seguropf = (SeguroPF) value;
                if(seguropf.getCliente() == cliente){
                    soma++;
                }
            }
        }
        return soma;
    }

    public double calcularValor(Cliente cliente){
        ClientePF clientepf = (ClientePF) cliente;
        LocalDate dataAtual = LocalDate.now();
        LocalDate aniversario = clientepf.getDataNasc();
        ArrayList <Integer> pair = numeroSinistros();
        int qtdveiculos = getqtdVeiculos();
        if(ChronoUnit.YEARS.between(aniversario, dataAtual) < 30){
            return (CalcSeguro.VALOR_BASE.getFator() * CalcSeguro.FATOR_0_30.getFator() * (1 + 1/qtdveiculos)
            *(2 + pair.get(0)/10.0) * (5 + pair.get(1)/10.0));
        }
        else if (ChronoUnit.YEARS.between(aniversario, dataAtual) >= 30 && (ChronoUnit.YEARS.between(aniversario, dataAtual) <= 60)){
            return (CalcSeguro.VALOR_BASE.getFator() * CalcSeguro.FATOR_30_60.getFator() * (1 + 1/qtdveiculos)
            *(2 + pair.get(0)/10.0) * (5 + pair.get(1)/10.0));
        }
        else if (ChronoUnit.YEARS.between(aniversario, dataAtual) > 60) {
            return (CalcSeguro.VALOR_BASE.getFator() * CalcSeguro.FATOR_60_90.getFator() * (1 + 1/qtdveiculos)
            *(2 + pair.get(0)/10.0) * (5 + pair.get(1)/10.0));
        }
        return -1; // nunca vai chegar aqui
    }
    

    public void corrigeValor(){
        this.setValorMensal(calcularValor(cliente));
    }

    public String toString(){
        return " o seguro de id " + this.getId() + " com inicio em " + this.getDataInicio() + this.getDataFim() + " cobre o seguinte veiculo " + veiculo +
        " que pertence ao seguinte cliente " + cliente + " e possui os seguintes condutores " + this.listarCondutores();
    }
}