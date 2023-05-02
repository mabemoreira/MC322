package lab04;
import java.util.Date;

public class ClientePJ extends Cliente {
		private final String CNPJ;
		private Date dataFundacao;
		private int qtdeFuncionarios;
		
		public ClientePJ(String CNPJ, Date dataFundacao, String nome, String endereco, int qtdeFuncionarios){
			super(nome,endereco);
			this.CNPJ = CNPJ.replaceAll("[^\\d]","");
			this.dataFundacao = dataFundacao;
			this.setQtdeFuncionarios(qtdeFuncionarios);
		}
		
		public String getCNPJ() {
			return CNPJ;
		}

		public Date getDataFundacao() {
			return dataFundacao;
		}
		
		
		public int getQtdeFuncionarios() {
			return qtdeFuncionarios;
		}

		public void setQtdeFuncionarios(int qtdeFuncionarios) {
			this.qtdeFuncionarios = qtdeFuncionarios;
			this.setValorSeguro(); //isso ta certo? pq é a unica utilidade que eu vejo pro calcula score de cliente;
		}
		
		public double calculaScore(){
			return (CalcSeguro.VALOR_BASE.getFator()* (1 + (qtdeFuncionarios/100)) * this.getMapaVeiculos().size());
		}

		public String toString() {		
			return "a pessoa física de nome " + this.getNome() + " e endereco " + this.getEndereco() + " foi fundada em " + dataFundacao + " e possui CNPJ " + CNPJ
					+ " e possui o(s) seguinte(s) veiculo(s)\n" + super.listarVeiculos(); 
			}
}
