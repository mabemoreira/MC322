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
    public void setCode(String code) {
        this.code = code;
    }
    public HashMap<String, Veiculo> getMapaVeiculos() {
        return mapaVeiculos;
    }
    public void setMapaVeiculos(HashMap<String, Veiculo> mapaVeiculos) {
        this.mapaVeiculos = mapaVeiculos;
    }



}
