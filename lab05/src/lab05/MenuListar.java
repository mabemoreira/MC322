package lab05;

public enum MenuListar {
    CLIENTE(1),
	SINISTRO_SEG(2),
	SINISTRO_CLIENTE(3),
	VEICULO_CLIENTE(4),
	SEGUROS_SEG(5),
    VEICULO_FROTA(6),
    SEGUROS_CLIENTE(7),
	FROTA_CLIENTE(8),
	CONDUTOR_SEG(9),
	VOLTAR(10);

public final int operacao;
	
	MenuListar(int operacao){
		this.operacao = operacao;
	}
	
	public int getOperacao() {
		return this.operacao;
	}
	
	public static MenuListar deIntpraEnum(int escolha) {
		for(MenuListar op: MenuListar.values()) {
			if (op.getOperacao() == escolha)
				return op;
		}
		return null;

}

}
