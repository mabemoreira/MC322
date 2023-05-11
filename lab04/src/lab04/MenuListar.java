package lab04;

public enum MenuListar {
	CLIENTE(1),
	SINISTRO_SEG(2),
	SINISTRO_CLIENTE(3),
	VEICULO_CLIENTE(4),
	VEICULO_SEGURADORA(5),
	VOLTAR(6);
	
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

