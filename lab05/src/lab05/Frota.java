package lab05;
import java.util.HashMap; 


public class Frota {
    private String code;
    private HashMap <String, Veiculo> mapaVeiculos;

    public Frota(String code){
        this.code = code;
        mapaVeiculos = new HashMap<String, Veiculo>();
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code)  {
        this.code = code;
    }
    public HashMap<String, Veiculo> getMapaVeiculos() {
        return mapaVeiculos;
    }
    public void setMapaVeiculos(HashMap<String, Veiculo> mapaVeiculos) {
        this.mapaVeiculos = mapaVeiculos;
    }

    public boolean addVeiculo(Veiculo veiculo){
        if(mapaVeiculos.containsValue(veiculo)){
            System.out.println("Veiculo ja adicionado");
            return false;
        }
        mapaVeiculos.put(veiculo.getPlaca(), veiculo);
        return true;
    }

    public boolean removerVeiculo(String placa){
        Veiculo veiculo = mapaVeiculos.remove(placa);
        String resultado = veiculo == null ? "Veiculo nao encontrado" : "Veiculo removido com sucesso";
        System.out.println(resultado);
        return(veiculo != null);
    }
}



