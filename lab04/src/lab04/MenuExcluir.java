package lab04;

public enum MenuExcluir {
	CLIENTE(1),
	VEICULO(2),
	SINISTRO(3),
	VOLTAR(4);
	

public final int operacao;
	
	MenuExcluir(int operacao){
		this.operacao = operacao;
	}
	
	public int getOperacao() {
		return this.operacao;
	}
	
	public static MenuExcluir deIntpraEnum(int escolha) {
		for(MenuExcluir op: MenuExcluir.values()) {
			if (op.getOperacao() == escolha)
				return op;
		}
		return null;
	}
}
