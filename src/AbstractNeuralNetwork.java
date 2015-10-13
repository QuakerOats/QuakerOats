
public abstract class AbstractNeuralNetwork {
private InputNeuron[] inputlayer;
private IntermediateNeuron[][] hiddenlayers;
private OutputNeuron[] outputlayer;
public AbstractNeuralNetwork(InputNeuron[] inputlayer,IntermediateNeuron[][] hiddenlayers,OutputNeuron[] outputlayer) {
	this.inputlayer=inputlayer;
	this.hiddenlayers=hiddenlayers;
	this.outputlayer= outputlayer;
}
public InputNeuron[] getInputlayer() {
	return this.inputlayer;
}

public IntermediateNeuron[][] getHiddenlayers() {
	return this.hiddenlayers;
}

public OutputNeuron[] getOutputlayer() {
	return this.outputlayer;
}
abstract public void fire();
abstract public double[] getOutputs();
abstract public void setInputs(double[] inputs);
abstract public void linkNetwork();
abstract public void train(double[][] inputs, double[][] outputs);


public abstract void launch(double[] inputs);
}