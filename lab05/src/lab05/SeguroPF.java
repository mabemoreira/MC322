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

    public double calcularValor(Cliente cliente){
        ClientePF clientepf = (ClientePF) cliente;
        LocalDate dataAtual = LocalDate.now();
        LocalDate aniversario = clientepf.getDataNasc();
        ArrayList <Integer> pair = numeroSinistros();
        if(ChronoUnit.YEARS.between(aniversario, dataAtual) < 30){
            return (CalcSeguro.VALOR_BASE.getFator() * CalcSeguro.FATOR_0_30.getFator() * (1 + 1/(clientepf.getMapaVeiculos().size()))
            *(2 + pair.get(0)/10.0) * (5 + pair.get(1)/10.0));
        }
        else if (ChronoUnit.YEARS.between(aniversario, dataAtual) >= 30 && (ChronoUnit.YEARS.between(aniversario, dataAtual) <= 60)){
            return (CalcSeguro.VALOR_BASE.getFator() * CalcSeguro.FATOR_30_60.getFator() * (1 + 1/(clientepf.getMapaVeiculos().size()))
            *(2 + pair.get(0)/10.0) * (5 + pair.get(1)/10.0));
        }
        else if (ChronoUnit.YEARS.between(aniversario, dataAtual) > 60) {
            return (CalcSeguro.VALOR_BASE.getFator() * CalcSeguro.FATOR_60_90.getFator() * (1 + 1/(clientepf.getMapaVeiculos().size()))
            *(2 + pair.get(0)/10.0) * (5 + pair.get(1)/10.0));
        }
        return -1; // nunca vai chegar aqui
    }
}