
public class OutputNeuron extends Neuron{
	
	private double bias;
	private double biasDiff;
	
	OutputNeuron(ActivationFunction activationfunction){
		super(activationfunction);
	}
	OutputNeuron(ActivationFunction activationfunction, double bias){
		super(activationfunction);
		this.bias = bias;
	}
	
	public double getBias() {
		return this.bias;
	}
	public void setBias(double bias) {
		this.bias = bias;
	}
	public double getBiasDiff() {
		return this.biasDiff;
	}
	public void setBiasDiff(double biasDiff) {
		this.biasDiff = biasDiff;
	}
	public Synapse[] getOutputsynapses(){
		return null;
	}
	
	public void activate(){
		double s = 0;
		for(int i = 0; i <= this.getInputsynapses().length-1; i++){
			s += this.getInputsynapses()[i].getWeight()
				*this.getInputsynapses()[i].getInputneuron().getActivation();
		}
		s += this.getBias();
		this.setActivation(this.getActivationfunction().apply(s));
	
	}
	
}
