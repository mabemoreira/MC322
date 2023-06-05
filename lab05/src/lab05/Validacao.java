package lab05;

public class Validacao {

    public static Veiculo achaVeiculoFrota(String placa, Frota frota){
        Veiculo veiculo = null;
        for(Veiculo value: frota.getMapaVeiculos().values()){
            if(value.getPlaca() == placa)
                veiculo = value;
        }
        return veiculo;
    }

    public static Frota achaFrotaCliente(ClientePJ cliente, String code){
        Frota frota = null;
        for(Frota value: cliente.getMapaFrotas().values()){
            if(value.getCode() == code)
                frota = value;
        }
        return frota;
    }

    public static Cliente achaCliente(String id, Seguradora seguradora){
        Cliente cliente = null;
        for(Cliente value: seguradora.getMapaClientes().values()){
            if(value instanceof ClientePF){
                ClientePF clientepf = (ClientePF) value;
                if (clientepf.getCPF() == id)
                    cliente = clientepf;
            }
            if(value instanceof ClientePJ){
                ClientePJ clientepj = (ClientePJ) value;
                if (clientepj.getCNPJ() == id)
                    cliente = clientepj;
            }
        }
        return cliente;
    }

   
}
