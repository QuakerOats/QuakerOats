import java.util.ArrayList;
import java.util.List;

public abstract class Neuron {
	
	private double activation;
	private double neurondiff;
	private ActivationFunction activationfunction;
	private ArrayList<Synapse> inputsynapses;
	private ArrayList<Synapse> outputsynapses;
	
	
	public Neuron(ActivationFunction activationfunction) {
		this.activationfunction = activationfunction;
		this.inputsynapses = new ArrayList<Synapse>();
		this.outputsynapses = new ArrayList<Synapse>();
	}
	
	abstract public void activate();
	
	public ActivationFunction getActivationfunction() {
		return this.activationfunction;
	}
	public void setActivationfunction(ActivationFunction activationfunction) {
		this.activationfunction = activationfunction;
	}
	public ArrayList<Synapse> getInputsynapses() {
		return this.inputsynapses;
	}
	public void setInputsynapses(ArrayList<Synapse> inputsynapses) {
		this.inputsynapses = inputsynapses;
	}
	public ArrayList<Synapse> getOutputsynapses() {
		return this.outputsynapses;
	}
	public void setOutputsynapses(ArrayList<Synapse> outputsynapses) {
		this.outputsynapses = outputsynapses;
	}
	public double getActivation(){
		return this.activation;
	}
	public void setActivation(double activation){
		this.activation = activation;
	}
	public double getNeurondiff(){
		return this.neurondiff;
	}
	public void setNeurondiff(double neurondiff){
		this.neurondiff = neurondiff;
	}

}
