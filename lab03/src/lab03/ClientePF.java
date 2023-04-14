package lab03;
import java.util.Date;

public class ClientePF extends Cliente{
	private final String CNPJ;
	private Date dataFundacao;

	public ClientePF(String CNPJ, Date dataFundacao, String nome, String endereco) {
		super(nome, endereco);
		this.CNPJ = CNPJ;
		this.dataFundacao = dataFundacao;
	}
}
