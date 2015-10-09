
public class OutputNeuron extends AbstractNeuron{
	private double bias;
	private double biasDiff;
	OutputNeuron(AbstractActivationFunction activationfunction){
		super(activationfunction);
	}
	OutputNeuron(AbstractActivationFunction activationfunction, double bias){
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

	public void fire(){
		 double s = 0;
		for( int i = 0; i<= this.getInputsynapses().length-1;i++){
			s =s + this.getInputsynapses()[i].getWeight()*this.getInputsynapses()[i].getInputneuron().getOutput();
		}
		s = s - this.getBias();
		this.setOutput(this.getActivationfunction().apply(s));
	
	}

	
}
