package lab05;

public enum MenuExcluir {
    CLIENTE(1),
	VEICULO(2),
    FROTA(3),
    UMVEICULOFROTA(4),
	SEGURO(5),
	TODOSSEGCLIENTE(6),
	CONDUTOR(7),
	VOLTAR(8);
	

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
