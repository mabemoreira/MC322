package lab05;

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


}