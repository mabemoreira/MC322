package lab05;

public enum CalcSeguro {
    VALOR_BASE(10.0),
	FATOR_0_30(1.25),
	FATOR_30_60(1.0),
	FATOR_60_90(1.5);
	
	public final double fator; 
	
	 CalcSeguro(double fator) {
		this.fator = fator;
	}
	 
	public double getFator() {
		return fator;
	}
}


