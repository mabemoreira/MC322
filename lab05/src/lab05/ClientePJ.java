package lab05;
import java.time.LocalDate;  
import java.util.HashMap; 


public class ClientePJ extends Cliente{
    private final String CNPJ;
    private LocalDate dataFund;
    private HashMap<String, Frota> mapaFrotas;


    public ClientePJ(String CNPJ, LocalDate dataFund,  String nome, String endereco, String telefone, String email){
        super(nome, telefone, endereco, email);
        this.CNPJ = CNPJ;
        this.dataFund = dataFund;
        mapaFrotas = new HashMap <String, Frota> ();
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public LocalDate getDataFund() {
        return dataFund;
    }

    public void setDataFund(LocalDate dataFund) {
        this.dataFund = dataFund;
    }


    public HashMap<String, Frota> getMapaFrotas() {
        return mapaFrotas;
    }

    public void setMapaFrotas(HashMap<String, Frota> mapaFrotas) {
        this.mapaFrotas = mapaFrotas;
    }

    public boolean cadastrarFrota(Frota frota){
        if(mapaFrotas.containsValue(frota)){
            System.out.println("frota ja cadastrada");
            return false;
        }
        mapaFrotas.put(frota.getCode(), frota);
        System.out.println("Frota cadastrada com sucesso");
        return true;
    }

    public boolean atualizarFrota( String placa, String code){
        Frota frota = Validacao.achaFrotaCliente(this, code);
            if(frota == null){
                System.out.println("frota nao cadastrada");
                return false;
            }
        frota.removerVeiculo(placa);
        return true;
    }

    public boolean atualizarFrota (Veiculo veiculo, String code){
        Frota frota = Validacao.achaFrotaCliente(this, code);
            if(frota == null){
                System.out.println("frota nao cadastrada");
                return false;
            }
        frota.addVeiculo(veiculo);
        return true;
    }

    public boolean atualizarFrota(String code){
        Frota frota = Validacao.achaFrotaCliente(this, code);
            if(frota == null){
                System.out.println("frota nao cadastrada");
                return false;
            }
        mapaFrotas.remove(frota.getCode());
        System.out.println("frota removida com sucesso");
        return true;
    }

    public int calcularQtdVeiculos(){
        int contador = 0;
        for(Frota value: mapaFrotas.values()){
            contador += value.getMapaVeiculos().size();
        }
        return contador;
    }

    public boolean getVeiculosporFrota(String code){
        boolean flag = false;
        if(!mapaFrotas.containsKey(code)){
            System.out.println("Frota nao cadastrada tente novamente");
            return false;
        }
        System.out.println("Os veiculos cadastrados nessa frota sao: ");
        for(Frota value: mapaFrotas.values()){
            for(Veiculo v : value.getMapaVeiculos().values()){
                System.out.println(v);
                flag = true;
            }
        }
        if(!flag){
            System.out.println("Nao ha veiculos cadastrados nessa frota");
        }
        return true; // deixei true como consegui acessar a frota independentemente de ter veiculo ou nao
    }
   
}
