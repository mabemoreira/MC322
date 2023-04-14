package lab3;
import java.util.Date;

public class ClientePJ  extends Cliente {
	private final String CNPJ;
	private Date dataFundacao;
	
	public ClientePJ(String CNPJ, Date dataFundacao, String nome, String endereco) throws Exception{
		super(nome,endereco);
		this.CNPJ = CNPJ;
		this.dataFundacao = dataFundacao;
	}
	
	public String getCNPJ() {
		return CNPJ;
	}

	public Date getDataFundacao() {
		return dataFundacao;
	}
	
	public static boolean validaCNPJ(String cnpjTeste) {
		
	}
}
