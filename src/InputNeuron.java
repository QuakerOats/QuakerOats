public class InputNeuron extends Neuron{
	
	private double input;
	
	public InputNeuron(ActivationFunction activationfunction){
		super(activationfunction);
	}
	public double getInput(){
		return input;
	}
	public void setInput(double input){
	     this.input = input;
	}
	public Synapse[] getInputsynapses(){
		return null;
	}
		
	public void activate(){
		this.setActivation(input);
	}
	
}

