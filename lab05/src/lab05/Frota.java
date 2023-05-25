package lab05;
import java.util.HashMap; 

public class Frota {
    private static int contador = 0; //obg PAD elias por essa ideia incrivel
    private int code;
    private HashMap <String, Veiculo> mapaVeiculos;

    public Frota(){
        code = contador;
        contador++;
        mapaVeiculos = new HashMap<String, Veiculo>();
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public HashMap<String, Veiculo> getMapaVeiculos() {
        return mapaVeiculos;
    }
    public void setMapaVeiculos(HashMap<String, Veiculo> mapaVeiculos) {
        this.mapaVeiculos = mapaVeiculos;
    }



}
