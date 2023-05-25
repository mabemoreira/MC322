package lab05;
import java.time.LocalDate;  
import java.util.HashMap; 

public class ClientePJ extends Cliente{
    private final String CNPJ;
    private LocalDate dataFund;
    private int qtdFunc;
    private HashMap<Integer, Frota> mapaFrotas;



    public ClientePJ(String CNPJ, LocalDate dataFund, int qtdFunc, String nome, String endereco, String telefone, String email){
        super(nome, telefone, endereco, email);
        this.CNPJ = CNPJ;
        this.dataFund = dataFund;
        this.qtdFunc = qtdFunc;
        mapaFrotas = new HashMap <Integer, Frota> ();
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

    public int getQtdFunc() {
        return qtdFunc;
    }

    public void setQtdFunc(int qtdFunc) {
        this.qtdFunc = qtdFunc;
    }

    public HashMap<Integer, Frota> getMapaFrotas() {
        return mapaFrotas;
    }

    public void setMapaFrotas(HashMap<Integer, Frota> mapaFrotas) {
        this.mapaFrotas = mapaFrotas;
    }
}
