
public class Synapse {
	
	private double weight;
	private double weightdiff;
	private Neuron inputneuron;
	private Neuron outputneuron;
	
	public Synapse(Neuron inputneuron,Neuron outputneuron) {
		this.inputneuron = inputneuron;
		this.outputneuron = outputneuron;
	}
	public Synapse(Object object, Object object2) {
		// TODO Auto-generated constructor stub
	}
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
	public Neuron getInputneuron() {
		return this.inputneuron;
	}
	public void setInputneuron(Neuron inputneuron) {
		this.inputneuron = inputneuron;
	}
	public Neuron getOutputneuron() {
		return this.outputneuron;
	}
	public void setOutputneuron(Neuron outputneuron) {
		this.outputneuron = outputneuron;
	}

}
