package lab04;

public enum MenuOperacoes {
	CADASTRAR(1.0),
	CADASTRAR_CLIENTE(1.1),
	CADASTRAR_VEICULO(1.2),
	CADASTRAR_SEGURADORA(1.3),
	VOLTAR_1(1.4),
	LISTAR(2.0),
	LISTAR_CLIENTE(2.1),
	LISTAR_SINISTRO_SEG(2.2),
	LISTAR_SINISTRO_CLIENTE(2.3),
	LISTAR_VEICULO_CLIENTE(2.4),
	LISTAR_VEICULO_SEGURADORA(2.5),
	VOLTAR_2(2.6),
	EXCLUIR(3.0),
	EXCLUIR_CLIENTE(3.1),
	EXCLUIR_VEICULO(3.2),
	EXCLUIR_SINISTRO(3.3),
	VOLTAR_3(3.4),
	GERAR_SINISTRO(4.0),
	TRANSFERIR_SINISTRO(5.0),
	CALCULAR_RECEITA(6.0),
	SAIR(0.0);
	
	public final double operacao;
	
	MenuOperacoes(double operacao){
		this.operacao = operacao;
	}
	
	public double getOperacao() {
		return this.operacao;
	}
}
