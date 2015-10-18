public abstract class ActivationFunction {
	
	public double valuefonction;
	public double valuederivative;
	public ActivationFunction() {}
	abstract public double applyDerivative(Double x);
	abstract public double apply(Double x);

}
