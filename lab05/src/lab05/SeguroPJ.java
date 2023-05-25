package lab05;
import java.time.LocalDate;
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
}


