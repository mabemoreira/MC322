package lab04;
import java.util.Date;

public class ClientePF extends Cliente{
	private final String CPF;
	private Date dataNascimento;
	private String genero;
	private Date dataLicenca;
	private String educacao;
	private String classeEconomica;
	
	public ClientePF(String CPF, Date dataNascimento, String nome, String endereco, String genero, String educacao, String classeEconomica, Date dataLicenca) {
		super(nome, endereco);
		this.CPF = CPF.replaceAll("[^\\d]","");
		this.setDataNascimento(dataNascimento);
		this.setGenero(genero); 
		this.setEducacao(educacao);
		this.setDataLicenca(dataLicenca);
		this.setClasseEconomica(classeEconomica);
	}

	public String getCPF() {
		return CPF;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Date getDataLicenca() {
		return dataLicenca;
	}

	public void setDataLicenca(Date dataLicenca) {
		this.dataLicenca = dataLicenca;
	}

	public String getEducacao() {
		return educacao;
	}

	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}

	public String getClasseEconomica() {
		return classeEconomica;
	}

	public void setClasseEconomica(String classeEconomica) {
		this.classeEconomica = classeEconomica;
	}
	
	@SuppressWarnings("deprecation")
	public double calculaScore() {
		if((2023 - (dataNascimento.getYear()+1900) >= 18) && (2023 - (dataNascimento.getYear()+1900)< 30))
			return(CalcSeguro.VALOR_BASE.getFator() * CalcSeguro.FATOR_18_30.getFator() * this.getMapaVeiculos().size());
		else if((2023 - (dataNascimento.getYear()+1900) >= 30) && (2023 - (dataNascimento.getYear()+1900)< 60))
			return(CalcSeguro.VALOR_BASE.getFator() * CalcSeguro.FATOR_30_60.getFator() * this.getMapaVeiculos().size());
		else
			return(CalcSeguro.VALOR_BASE.getFator() * CalcSeguro.FATOR_60_90.getFator() * this.getMapaVeiculos().size());
	}

	public String toString() {
		return "o cliente " + this.getNome() + " de genero " + genero + " nascido em " + dataNascimento + " possui o CPF " + CPF + " tirou sua habilitacao em " + dataLicenca + " tem até nivel " 
				+ educacao + " é da classe economica " + classeEconomica + " mora em " + this.getEndereco() + " possui veiculo(s)\n" + super.listarVeiculos() + "e seu seguro vale" + this.getValorSeguro();
	}

}
