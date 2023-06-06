package lab05;

public enum MenuListar {
    CLIENTE(1),
	SINISTRO_SEG(2),
	SINISTRO_CLIENTE(3),
	VEICULO_CLIENTE(4),
	SEGUROS_SEG(5),
    VEICULO_FROTA(7),
    SEGUROS_CLIENTE(8),
	FROTA_CLIENTE(9),
	CONDUTOR_SEG(10),
	VOLTAR(11);

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
