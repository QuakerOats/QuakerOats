
public class InputNeuron extends AbstractNeuron{
private double input;

InputNeuron(AbstractActivationFunction activationfunction){
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
	
public void fire(){
	this.setOutput(this.getActivationfunction().apply(input));
}
}

