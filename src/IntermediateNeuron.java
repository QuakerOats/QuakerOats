
public class IntermediateNeuron extends AbstractNeuron{
private double bias;
private double biasDiff;
IntermediateNeuron(AbstractActivationFunction activationfunction){
	super(activationfunction);
}
IntermediateNeuron(AbstractActivationFunction activationfunction, double bias){
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

 public void fire(){
	 double s = 0;
	for( int i = 0; i<= this.getInputsynapses().length-1;i++){
		s =s + this.getInputsynapses()[i].getWeight()*this.getInputsynapses()[i].getInputneuron().getOutput();
	}
	s = s - this.getBias();
	this.setOutput(this.getActivationfunction().apply(s));
	
}

}
