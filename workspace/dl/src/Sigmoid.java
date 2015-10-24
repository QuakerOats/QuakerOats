public class Sigmoid extends ActivationFunction {
	
	public Sigmoid() {
		super();
	}
	public double applyDerivative(Double x) {
		return this.valuederivative = Math.exp(-x)/((1+Math.exp(-x))*(1+Math.exp(-x)));
	}
	public double apply(Double x){
		return this.valuefonction = 1/(1+Math.exp(-x));
	}
	
}

