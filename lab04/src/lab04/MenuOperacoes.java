package lab04;

public enum MenuOperacoes {
	CADASTRAR(1),
	LISTAR(2),
	EXCLUIR(3),
	GERAR_SINISTRO(4),
	TRANSFERIR_SINISTRO(5),
	CALCULAR_RECEITA(6),
	SAIR(0);
	
public final int operacao;
	
	MenuOperacoes(int operacao){
		this.operacao = operacao;
	}
	
	public int getOperacao() {
		return this.operacao;
	}
	
	public static MenuOperacoes deIntpraEnum(int escolha) {
		for(MenuOperacoes op: MenuOperacoes.values()) {
			if (op.getOperacao() == escolha)
				return op;
		}
		return null;
	}
	
}
