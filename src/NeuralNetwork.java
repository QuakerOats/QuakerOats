import java.util.ArrayList;
import java.util.List;

public abstract class NeuralNetwork {

	private int[] constructorTab;
	private ActivationFunction activationFunction;
	private InputNeuron[] inputLayer;
	private List<List<IntermediateNeuron>> hiddenLayers;
	private OutputNeuron[] outputLayer;
	
	/*If the user doesn't give an activation function*/
	public NeuralNetwork(int[] constructorTab) throws InvalidNetworkConstruction{
		if(constructorTab.length<3){
			throw new InvalidNetworkConstruction();
		}
		else{
			this.constructorTab = constructorTab;
			int length = constructorTab.length;
			
			ActivationFunction activationFunction = null;
			IntermediateNeuron intermediateNeuron = new IntermediateNeuron(activationFunction);
			InputNeuron inputNeuron = new InputNeuron(activationFunction);
			OutputNeuron outputNeuron = new OutputNeuron(activationFunction);
			
			InputNeuron[] inputLayer = new InputNeuron[constructorTab[0]];
			for(int i=1; i<=inputLayer.length-1; i++){
				inputLayer[i]=inputNeuron;
			}
			
			OutputNeuron[] outputLayer = new OutputNeuron[constructorTab[length]];
			for(int i=1; i<=inputLayer.length-1; i++){
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
	/*If the user specifies an activation function*/
	public NeuralNetwork(int[] constructorTab, ActivationFunction activationFunction) throws InvalidNetworkConstruction{
		if(constructorTab.length<3){
			throw new InvalidNetworkConstruction();
		}
		else{
			this.constructorTab = constructorTab;
			int length = constructorTab.length;
			
			IntermediateNeuron intermediateNeuron = new IntermediateNeuron(activationFunction);
			InputNeuron inputNeuron = new InputNeuron(activationFunction);
			OutputNeuron outputNeuron = new OutputNeuron(activationFunction);
			
			InputNeuron[] inputLayer = new InputNeuron[constructorTab[0]];
			for(int i=1; i<=inputLayer.length-1; i++){
				inputLayer[i]=inputNeuron;
			}
			
			OutputNeuron[] outputLayer = new OutputNeuron[constructorTab[length]];
			for(int i=1; i<=inputLayer.length-1; i++){
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
	
	abstract public void fire();
	abstract public double[] getOutputs();
	abstract public void setInputs(double[] inputs);
	abstract public void linkNetwork();
	abstract public void train(double[][] inputs, double[][] outputs);
	
	public abstract void launch(double[] inputs);

}