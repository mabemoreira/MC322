package lab05;

public enum MenuExcluir {
    CLIENTE(1),
	VEICULO(2),
	SINISTRO(3),
    FROTA(4),
    UMVEICULOFROTA(5),
	SEGURO(6),
	TODOSSEGCLIENTE(7),
	CONDUTOR(8),
	VOLTAR(9);
	

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
