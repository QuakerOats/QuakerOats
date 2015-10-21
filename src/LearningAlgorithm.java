
public abstract class LearningAlgorithm {
	
	public NeuralNetwork neuralNetwork;
	public double learningRate;
	
	abstract public void launch(Input in);
	
	abstract public void calculateActivations(double[] input);
	
	abstract public void calculateNeuronAndWeightDiffs(double[] ouput);
	
	abstract public void train(double[][] inputs, double[][] outputs);
}
