package lab03;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ClientePJ extends Cliente {
	private final String CNPJ;
	private Date dataFundacao;
	
	public ClientePJ(String CNPJ, String viraDate) {
		this.CNPJ = CNPJ;
		dataFundacao = new SimpleDateFormat("dd/MM/yyyy").parse(viraDate);
		
	}
	
	
	public String getCNPJ() {
		return CNPJ;
	}

	public Date getDataFundacao() {
		return dataFundacao;
	}
}
