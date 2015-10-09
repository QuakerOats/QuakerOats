
public class Synapse {
private double weight;
private double weightdiff;
private AbstractNeuron inputneuron;
private AbstractNeuron outputneuron;
public double getWeight() {
	return this.weight;
}
public void setWeight(double weight) {
	this.weight = weight;
}
public double getWeightdiff() {
	return this.weightdiff;
}
public void setWeightdiff(double weightdiff) {
	this.weightdiff = weightdiff;
}
public AbstractNeuron getInputneuron() {
	return this.inputneuron;
}
public void setInputneuron(AbstractNeuron inputneuron) {
	this.inputneuron = inputneuron;
}
public AbstractNeuron getOutputneuron() {
	return this.outputneuron;
}
public void setOutputneuron(AbstractNeuron outputneuron) {
	this.outputneuron = outputneuron;
}

}
