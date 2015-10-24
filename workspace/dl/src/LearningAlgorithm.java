import java.util.ArrayList;
import java.util.List;

public abstract class LearningAlgorithm {
	
	public NeuralNetwork neuralNetwork;
	public double learningRate;
	public int epochSize;
	public ArrayList<Double> trainerror;
	public ArrayList<Double> testerror;
	public ToCsv csv;
	public OutputData outputdata;
	public LearningAlgorithm(NeuralNetwork neuralNetwork){
		this.neuralNetwork=neuralNetwork;
	}
	
	public LearningAlgorithm(NeuralNetwork neuralNetwork, double learningRate, int epochSize){
		this.neuralNetwork=neuralNetwork;
		this.learningRate=learningRate;
		this.epochSize=epochSize;
		this.csv = new ToCsv(this);
	}
	
	abstract public void launch(Input in);
	
	abstract public void calculateActivations(double[] input);
	
	abstract public void calculateNeuronAndWeightDiffs(double[] ouput);
	
	abstract public void train(double[][] inputs, double[][] outputs);
	abstract public void globaltraining(double[][] Inputsdata, double[][] Outputsdata);
	
	/*splits the inputs into epochs of size epochSize (attribute)*/
	public List<double[][]> splitIntoEpochs(double[][] inputs){

		
		int numberOfInputs = inputs.length;
		/*maybe we could allocate the last inputs into the previous epochs if they only constitute a half epoch or less ? see later*/
		int sizeLastEpoch = numberOfInputs % this.epochSize;
		int numberOfEpochs = (sizeLastEpoch==0) ? numberOfInputs / this.epochSize : numberOfInputs / this.epochSize + 1;
		
		List<double[][]> epochs = new ArrayList<double[][]>();
		
		for(int i=1; i<=numberOfEpochs-1; i++){
			double[][] epoch = new double[this.epochSize][inputs[0].length];
			for(int j=0; j<=this.epochSize-1; j++){
				epoch[j] = inputs[(i-1)*this.epochSize+j];
			}
			epochs.add(epoch);
		}
		
		double[][] lastEpoch = (sizeLastEpoch==0) ? new double[this.epochSize][inputs[0].length] : new double[sizeLastEpoch][inputs[0].length];
		for(int j=0; j<=lastEpoch.length-1; j++){
			lastEpoch[j] = inputs[inputs.length-lastEpoch.length+j];
		}
		epochs.add(lastEpoch);
		
		return epochs;
	};
	
	/*then splitIntoEpochs, train on each epoch, export to csv, test during the training*/
	
	
}
