
public abstract class AbstractNeuron {
private double output;
private double neurondiff;
private AbstractActivationFunction activationfunction;
private Synapse[] inputsynapses;
private Synapse[] outputsynapses;


public AbstractNeuron(AbstractActivationFunction activationfunction) {
	this.activationfunction = activationfunction;
}
abstract public void fire();
public double getOutput(){
	return this.output;
}
public AbstractActivationFunction getActivationfunction() {
	return this.activationfunction;
}
public void setActivationfunction(AbstractActivationFunction activationfunction) {
	this.activationfunction = activationfunction;
}
public Synapse[] getInputsynapses() {
	return this.inputsynapses;
}
public void setInputsynapses(Synapse[] inpusynapses) {
	this.inputsynapses = inpusynapses;
}
public Synapse[] getOutputsynapses() {
	return this.outputsynapses;
}
public void setOutputsynapses(Synapse[] outputsynapses) {
	this.outputsynapses = outputsynapses;
}
public void setOutput(double output){
	this.output = output;
}
public double getNeurondiff(){
	return this.neurondiff;
}
public void setNeurondiff(double neurondiff){
	this.neurondiff = neurondiff;
}

}
