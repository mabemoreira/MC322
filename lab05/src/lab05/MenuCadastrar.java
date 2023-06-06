package lab05;

    public enum MenuCadastrar {
        CLIENTE(1),
        VEICULO(2),
        SEGURADORA(3),
        FROTA(4),
        UMVEICULOFROTA(5),
        SEGURO(6),
        CONDUTOR(7),
        VOLTAR(8);
        
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

