package lab05;
import java.util.HashMap; 

public class Frota {
    private static int contador = 0;
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
    public void setCode(int code)  {
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



