import java.util.ArrayList;
import java.util.List;

public abstract class NeuralNetwork {

	private int[] constructorTab;
	private ActivationFunction activationFunction;
	private InputNeuron[] inputLayer;
	private List<List<IntermediateNeuron>> hiddenLayers;
	private OutputNeuron[] outputLayer;
	private LearningAlgorithm learningAlgorithm;
	
	/*If the user doesn't give an activation function*/
	public NeuralNetwork(int[] constructorTab) throws InvalidNetworkConstruction{
		/*exception if the constructor array length is too small*/
		if(constructorTab.length<3){
			throw new InvalidNetworkConstruction();
		}
		else{
			this.constructorTab = constructorTab;
			this.learningAlgorithm = new BackProp(this);
			int length = constructorTab.length;
			/*only 1 intermediate layer for now, it's ok to give the "same" random bias, see later*/
			double randomBias = 2*(Math.random()-0.5)*2.4/constructorTab[1];
			double randomBiasOutput = 2*(Math.random()-0.5)*2.4/constructorTab[length-1];
			
			ActivationFunction activationFunction = new Sigmoid();
			IntermediateNeuron intermediateNeuron = new IntermediateNeuron(activationFunction, randomBias);
			InputNeuron inputNeuron = new InputNeuron(activationFunction);
			OutputNeuron outputNeuron = new OutputNeuron(activationFunction, randomBiasOutput);
			
			InputNeuron[] inputLayer = new InputNeuron[constructorTab[0]];
			for(int i=0; i<=inputLayer.length-1; i++){
				inputLayer[i]=inputNeuron;
			}
			
			OutputNeuron[] outputLayer = new OutputNeuron[constructorTab[length-1]];
			for(int i=0; i<=outputLayer.length-1; i++){
				outputLayer[i]=outputNeuron;
			}
			
			List<List<IntermediateNeuron>> hiddenLayers = new ArrayList<List<IntermediateNeuron>>(length-2);
			for(int i=1; i<=length-2; i++){
				ArrayList<IntermediateNeuron> res = new ArrayList<IntermediateNeuron>();
				for(int k=1; k<=constructorTab[i]; k++){
					res.add(intermediateNeuron);
				}
				hiddenLayers.add(res);
			}
			
			this.inputLayer = inputLayer;
			this.hiddenLayers = hiddenLayers;
			this.outputLayer = outputLayer;
		}
	}
	/*If the user specifies an activation function and the learning algorithm*/
	public NeuralNetwork(int[] constructorTab, ActivationFunction activationFunction, LearningAlgorithm learningAlgorithm) throws InvalidNetworkConstruction{
		if(constructorTab.length<3){
			throw new InvalidNetworkConstruction();
		}
		else{
			this.setActivationFunction(activationFunction);
			this.setLearningAlgorithm(learningAlgorithm);
			this.constructorTab = constructorTab;
			int length = constructorTab.length;
			/*only 1 intermediate layer for now, it's ok to give the "same" random bias, see later*/
			double randomBias = 2*(Math.random()-0.5)*2.4/constructorTab[1];
			double randomBiasOutput = 2*(Math.random()-0.5)*2.4/constructorTab[length-1];
			
			IntermediateNeuron intermediateNeuron = new IntermediateNeuron(activationFunction, randomBias);
			InputNeuron inputNeuron = new InputNeuron(activationFunction);
			OutputNeuron outputNeuron = new OutputNeuron(activationFunction, randomBiasOutput);
			
			InputNeuron[] inputLayer = new InputNeuron[constructorTab[0]];
			for(int i=0; i<=inputLayer.length-1; i++){
				inputLayer[i]=inputNeuron;
			}
			
			OutputNeuron[] outputLayer = new OutputNeuron[constructorTab[length-1]];
			for(int i=0; i<=outputLayer.length-1; i++){
				outputLayer[i]=outputNeuron;
			}
			
			
			List<List<IntermediateNeuron>> hiddenLayers = new ArrayList<List<IntermediateNeuron>>(length-2);
			for(int i=1; i<=length-2; i++){
				ArrayList<IntermediateNeuron> res = new ArrayList<IntermediateNeuron>();
				for(int k=1; k<=constructorTab[i]; k++){
					res.add(intermediateNeuron);
				}
				hiddenLayers.add(res);
			}
			
			this.inputLayer = inputLayer;
			this.hiddenLayers = hiddenLayers;
			this.outputLayer = outputLayer;
		}
	}
		
	public ActivationFunction getActivationFunction() {
		return activationFunction;
	}
	public void setActivationFunction(ActivationFunction activationFunction) {
		this.activationFunction = activationFunction;
	}
	
	public LearningAlgorithm getLearningAlgorithm() {
		return learningAlgorithm;
	}
	public void setLearningAlgorithm(LearningAlgorithm learningAlgorithm) {
		this.learningAlgorithm = learningAlgorithm;
	}
	
	public int[] getConstructorTab() {
		return constructorTab;
	}
	
	public InputNeuron[] getInputlayer() {
		return this.inputLayer;
	}
	
	public List<List<IntermediateNeuron>> getHiddenlayers() {
		return this.hiddenLayers;
	}
	
	public OutputNeuron[] getOutputlayer() {
		return this.outputLayer;
	}

	abstract public void linkNetwork();

	abstract public void setInput(double[] input);
	abstract public void activate();
	abstract public void forwardpropagation(double[] input);
	abstract public double[] getOutput();
	
	abstract public void train(double[][] inputs, double[][] outputs);
	
	
	

}