package lab04;

public enum MenuCadastrar {
	CLIENTE(1),
	VEICULO(2),
	SEGURADORA(3),
	VOLTAR(4);
	
public final int operacao;
	
	MenuCadastrar(int operacao){
		this.operacao = operacao;
	}
	
	public int getOperacao() {
		return this.operacao;
	}
	
	public static MenuCadastrar deIntpraEnum(int escolha) {
		for(MenuCadastrar op: MenuCadastrar.values()) {
			if (op.getOperacao() == escolha)
				return op;
		}
		return null;
	}
}
