
public abstract class Neuron {
	
	private double activation;
	private double neurondiff;
	private ActivationFunction activationfunction;
	private Synapse[] inputsynapses;
	private Synapse[] outputsynapses;
	
	
	public Neuron(ActivationFunction activationfunction) {
		this.activationfunction = activationfunction;
	}
	
	abstract public void activate();
	
	public ActivationFunction getActivationfunction() {
		return this.activationfunction;
	}
	public void setActivationfunction(ActivationFunction activationfunction) {
		this.activationfunction = activationfunction;
	}
	public Synapse[] getInputsynapses() {
		return this.inputsynapses;
	}
	public void setInputsynapses(Synapse[] inputsynapses) {
		this.inputsynapses = inputsynapses;
	}
	public Synapse[] getOutputsynapses() {
		return this.outputsynapses;
	}
	public void setOutputsynapses(Synapse[] outputsynapses) {
		this.outputsynapses = outputsynapses;
	}
	public double getActivation(){
		return this.activation;
	}
	public void setActivation(double activation){
		this.activation = activation;
	}
	public double getNeurondiff(){
		return this.neurondiff;
	}
	public void setNeurondiff(double neurondiff){
		this.neurondiff = neurondiff;
	}

}
