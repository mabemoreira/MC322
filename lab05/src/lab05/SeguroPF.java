package lab05;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;

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




    public int calcularValor(Cliente cliente){
        ClientePF clientepf = (ClientePF) cliente;
        LocalDate dataAtual = LocalDate.now();
        LocalDate aniversario = clientepf.getDataNasc();
        if(ChronoUnit.YEARS.between(aniversario, dataAtual) < 30)
            return CalcSeguro.VALOR_BASE * CalcSeguro.FATOR_0_30  * this.getSeguradora().getSinistrosPorCliente().size() // confirmar se o get sinistros condutor é o sinsitro de cada um dos condutores
    }
}