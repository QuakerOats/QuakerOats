
public class FeedForward extends NeuralNetwork {
	
	/*constructor*/
	public FeedForward(int[] constructorTab) throws InvalidNetworkConstruction{
		super(constructorTab);
	}
	
	/*constructor where activation function and learning algorithm are specified*/
	public FeedForward(int[] constructorTab, ActivationFunction activationFunction, LearningAlgorithm learningAlgorithm) throws InvalidNetworkConstruction{
		super(constructorTab, activationFunction, learningAlgorithm);
	}

	
	/*Redundancy with Synapse [], would be better with ArrayList, see later*/
	/*links the network*/
	public void linkNetwork() {
		
		/*link the input neurons*/
		
		/*.get(0) gives the first hidden layer (ArrayList)
		 *.size() gives the number of neuron in this layer*/
		int size = this.getHiddenlayers().get(0).size();
    	
		/*for each input neuron*/
	    for(int i=0; i<=this.getInputlayer().length-1; i++){
	    	/*create outputsynapses tab*/
	    	Synapse[] outputsynapses = new Synapse[size];
	    	/*for each synapse in the tab*/
	    	for(int j=0; j<=size-1; j++){
	    		outputsynapses[j] = new Synapse();
	    		/*set the output neuron*/
	    		outputsynapses[j].setOutputneuron(this.getHiddenlayers().get(0).get(0));
	    	    /*set the input neuron*/
	    		outputsynapses[j].setInputneuron(this.getInputlayer()[i]);
	    	}
	    	/*transmit the outputsynapses tab to the neuron*/
    		this.getInputlayer()[i].setOutputsynapses(outputsynapses);
	    }
	    
	    
	    /*link the intermediate neurons*/
	    
	    /*first, the layer in contact with the input layer*/
	    
	    /*for each neuron from the 1st hidden layer*/
    	for(int i=0; i<=this.getHiddenlayers().get(0).size()-1; i++){
    	    /*create inputsynapses tab*/
    	    Synapse[] inputsynapses = new Synapse[this.getInputlayer().length];
        	/*for each synapse in the tab*/
        	for(int j=0; j<=this.getInputlayer().length-1; j++){
        		inputsynapses[j] = new Synapse();
        		/*set the output neuron*/
        		inputsynapses[j].setOutputneuron(this.getHiddenlayers().get(0).get(i));
        		/*set the input neuron*/
        	    inputsynapses[j].setInputneuron(this.getInputlayer()[j]);
        	    /*set the weight randomly*/
        	    inputsynapses[j].setWeight(2*(Math.random()-0.5)*2.4/this.getInputlayer().length);
        	}	
        	/*transmit the inputsynapses tab to the neuron*/
        	this.getHiddenlayers().get(0).get(i).setInputsynapses(inputsynapses);
	    }
    	
	    /*then, the layers in between*/
	    
    	/*for each layer, we'll treat the outputsynapses (redundancy) */
	    for(int k=0; k<=this.getHiddenlayers().size()-2; k++){
	    	/*for each neuron in that layer*/
	    	for(int i=0;i<=this.getHiddenlayers().get(k).size()-1;i++){
	    		/*create outputsynapses tab*/
		    	Synapse[] outputsynapses = new Synapse[this.getHiddenlayers().get(k+1).size()];
		    	/*for each synapse in the tab*/
		    	for(int j = 0; j<=this.getHiddenlayers().get(k+1).size()-1;j++){
		    		outputsynapses[j] = new Synapse();
		    		/*set the output neuron*/
		    		outputsynapses[j].setOutputneuron(this.getHiddenlayers().get(k+1).get(j));
		    		/*set the input neuron*/
		    	    outputsynapses[j].setInputneuron(this.getHiddenlayers().get(k).get(i));
		    	}
		    	/*transmit the outputsynapses to the neuron*/
	    		this.getHiddenlayers().get(k).get(i).setOutputsynapses(outputsynapses);
		    }
		}
	    
	    /*for each layer, we'll treat the inputsynapses (redundancy) */
	    for(int k=0; k<=this.getHiddenlayers().size()-2; k++){
	    	/*for each neuron in that layer*/
	        for(int i = 0; i<=this.getHiddenlayers().get(k+1).size()-1;i++){
	        	/*create inputsynapses tab*/
	        	Synapse[] inputsynapses = new Synapse[this.getHiddenlayers().get(k).size()];
	        	/*last comment, we will change to ArrayList anyway*/
	        	for(int j = 0; j<=this.getHiddenlayers().get(k).size()-1;j++){
	        		inputsynapses[j] = new Synapse();
	        		inputsynapses[j].setOutputneuron(this.getHiddenlayers().get(k+1).get(i));
	        	    inputsynapses[j].setInputneuron(this.getHiddenlayers().get(k).get(j));
	        	    inputsynapses[j].setWeight(2*(Math.random()-0.5)*2.4/this.getHiddenlayers().get(k).size());
	        	}
	        	this.getHiddenlayers().get(k+1).get(i).setInputsynapses(inputsynapses);
	        }    
	    }
	    
	    /*finally, the layer in contact with the output layer*/
	    
	    for(int i=0; i<=this.getHiddenlayers().get(this.getHiddenlayers().size()-1).size()-1; i++){
	    	Synapse[] outputsynapses = new Synapse[this.getOutputlayer().length];
	    	for(int j = 0; j<=this.getOutputlayer().length-1;j++){
	    		outputsynapses[j] = new Synapse();
	    		outputsynapses[j].setOutputneuron(this.getOutputlayer()[j]);
	    	    outputsynapses[j].setInputneuron(this.getHiddenlayers().get(this.getHiddenlayers().size()-1).get(i));
	    	}
	    	this.getHiddenlayers().get(this.getHiddenlayers().size()-1).get(i).setOutputsynapses(outputsynapses);
	    }
	    
		/*link the output neurons*/    
	    for(int i=0; i<=this.getOutputlayer().length-1; i++){
	    	Synapse[] inputsynapses = new Synapse[this.getHiddenlayers().get(this.getHiddenlayers().size()-1).size()];
	    	for(int j = 0; j<=this.getHiddenlayers().get(this.getHiddenlayers().size()-1).size()-1;j++){
	    		inputsynapses[j] = new Synapse();
	    		inputsynapses[j].setOutputneuron(this.getOutputlayer()[i]);
	    	    inputsynapses[j].setInputneuron(this.getHiddenlayers().get(this.getHiddenlayers().size()-1).get(j));
	    	    inputsynapses[j].setWeight(2*(Math.random()-0.5)*2.4/this.getHiddenlayers().get(getHiddenlayers().size()-1).size());
	    	}
	    	this.getOutputlayer()[i].setInputsynapses(inputsynapses);
	    }
   
	}	
	
}

/* These methods are useless for now */

///*gives an input vector to the network*/
//public void setInput(double[] input) {
//	for (int i=0;i<=input.length-1;i++) {
//		this.getInputlayer()[i].setInput(input[i]);
//	}
//}
//
///*propagates towards the end (activate all the neurons)*/
//public void activate() {
//	for(int i = 0; i<= this.getInputlayer().length-1;i++){
//		this.getInputlayer()[i].activate();
//	}
//	for(int k=0; k<=this.getHiddenlayers().size()-1;k++){
//		for(int i = 0; i<= this.getHiddenlayers().get(k).size()-1;i++){
//			this.getHiddenlayers().get(k).get(i).activate();
//		}
//	}
//	for(int i = 0; i<=this.getOutputlayer().length-1;i++){
//		this.getOutputlayer()[i].activate();
//	}
//}
//
///*gives an input and propagates forward by activating all the neurons*/
//public void forwardpropagation(double[] input){
//	this.setInput(input);
//	this.activate();
//}
//
///*gets the output given by the network*/
//public double[] getOutput() {
//	double[] output = new double[this.getOutputlayer().length];
//	for (int i = 0; i <= this.getOutputlayer().length - 1; i++) {
//		output[i] = this.getOutputlayer()[i].getActivation();
//	}
//	return output;
//}
//
///*back propagates the error, neuron diff and weight diff are changed*/
//public void backpropagation(double[] output){
//	
//	// this.learningAlgorithm.calculateNeuronDiffs();
//	
//	// Calculate neurondiff and biasDiff for each Neuron of the Outputlayer
//	for(int k=0; k<= this.getOutputlayer().length - 1; k++){
//		double delta = this.getOutputlayer()[k].getActivationfunction().applyDerivative(this.getOutputlayer()[k].getActivation())
//					  *(this.getOutputlayer()[k].getActivation()-output[k]);
//		this.getOutputlayer()[k].setNeurondiff(delta);
//		/*updates the weight*/
//		this.getOutputlayer()[k].setBiasDiff(this.getOutputlayer()[k].getBiasDiff() + delta);
//	}
//	//Update weightdiff between the hidden layer and the outputlayer.
//	for(int k= 0; k<=this.getHiddenlayers().get(this.getHiddenlayers().size()-1).size()-1;k++){
//		for(int i = 0; i<=this.getOutputlayer().length - 1;i++){
//	    	double deltaweight = this.getHiddenlayers().get(this.getHiddenlayers().size()-1).get(k).getOutputsynapses()[i].getOutputneuron().getNeurondiff()
//	    						*this.getHiddenlayers().get(this.getHiddenlayers().size()-1).get(k).getActivation();
//	    	double a = this.getHiddenlayers().get(this.getHiddenlayers().size()-1).get(k).getOutputsynapses()[i].getWeightdiff();
//	    	this.getHiddenlayers().get(this.getHiddenlayers().size()-1).get(k).getOutputsynapses()[i].setWeightdiff(a+deltaweight);
//		}
//	}
//	
//	// Calculate neurondiff for each Neuron of the hiddenlayers
//	
//	/*for each layer*/
//	for (int k = 0; k <= this.getHiddenlayers().size() - 2; k++) {
//		/*for each neuron in this layer*/
//		for (int i = 0; i <= this.getHiddenlayers().get(k).size() - 1; i++) {
//			double s = 0;
//			/*for each output synapse of this neuron*/
//			for (int j = 0; j<= this.getHiddenlayers().get(k+1).size() - 1;j++){
//				s += this.getHiddenlayers().get(k).get(i).getOutputsynapses()[j].getOutputneuron().getNeurondiff()
//					*this.getHiddenlayers().get(k).get(i).getOutputsynapses()[j].getWeight();
//			}
//			double delta = this.getHiddenlayers().get(k).get(i).getActivationfunction().applyDerivative(this.getHiddenlayers().get(k).get(i).getActivation())
//						  *s;
//			this.getHiddenlayers().get(k).get(i).setNeurondiff(delta);
//			/*updates the weight*/
//			this.getHiddenlayers().get(k).get(i).setBiasDiff(this.getHiddenlayers().get(k).get(i).getBiasDiff() + delta);
//		}
//	}
//	//Update weightdiff between two neurons in hiddenlayers.
//	for( int k = 0; k<= this.getHiddenlayers().size() - 2;k++){
//		for (int i = 0 ; i<=this.getHiddenlayers().get(k).size() - 1; i++){
//			for(int j = 0 ; j<=this.getHiddenlayers().get(k+1).size()-1;j++){
//			double delta = this.getHiddenlayers().get(k).get(i).getOutputsynapses()[j].getWeightdiff() 
//						  +this.getHiddenlayers().get(k).get(i).getActivation()
//						  *this.getHiddenlayers().get(k).get(i).getOutputsynapses()[j].getOutputneuron().getNeurondiff();
//			this.getHiddenlayers().get(k).get(j).getOutputsynapses()[j].setWeightdiff(delta);
//			}
//		}
//	}
//	//Update weightdiff between the input layer and the first hiddenlayer.
//	for(int k= 0; k<=this.getInputlayer().length-1;k++){
//		for(int i = 0; i<=this.getHiddenlayers().get(0).size() - 1;i++){
//			double delta = this.getInputlayer()[k].getOutputsynapses()[i].getOutputneuron().getNeurondiff()
//						  *this.getInputlayer()[k].getActivation()
//						  +this.getInputlayer()[k].getOutputsynapses()[i].getWeightdiff();
//			this.getInputlayer()[k].getOutputsynapses()[i].setWeightdiff(delta);
//		}
//	}
//
//}
//
///*trains the network*/
//public void train(double[][] inputs, double[][] outputs){
//	
//	/*for each input, calculates the neurons's diff and synapases's weight diff*/
//	for(int i = 0; i<=inputs.length-1;i++){
//		/* this is written with methods in NeuralNetwork class, we now use the LearningAlgorithm class
//		 * 
//		 * this.forwardpropagation(inputs[i]);
//		 * this.backpropagation(outputs[i]);
//		 * */
//		this.getLearningAlgorithm().calculateActivations(inputs[i]);
//		this.getLearningAlgorithm().calculateNeuronAndWeightDiffs(inputs[i]);
//	}
//	
//	/*changes the neurons's value and the synapses's weight*/
//	for(int i= 0; i<=this.getInputlayer().length - 1; i++){
//		for (int j = 0; j<=this.getHiddenlayers().get(0).size() - 1; j++){
//			/*weight = weight + weight diff // input/hidden layers*/
//			this.getInputlayer()[i].getOutputsynapses()[j].setWeight(this.getInputlayer()[i].getOutputsynapses()[j].getWeight() + this.getInputlayer()[i].getOutputsynapses()[j].getWeightdiff());
//		}
//	}
//	for( int k = 0; k<= this.getHiddenlayers().size() - 2;k++){
//		for (int i = 0 ; i<=this.getHiddenlayers().get(k).size() - 1; i++){
//			for(int j = 0 ; j<=this.getHiddenlayers().get(k+1).size()-1;j++){
//				/*weight = weight + weight diff // hidden layers*/
//				this.getHiddenlayers().get(k).get(i).getOutputsynapses()[j].setWeight(this.getHiddenlayers().get(k).get(i).getOutputsynapses()[j].getWeight() + this.getHiddenlayers().get(k).get(i).getOutputsynapses()[j].getWeightdiff());
//			}
//		}
//	}
//	for(int i= 0; i<=this.getHiddenlayers().get(this.getHiddenlayers().size()-1).size() - 1; i++){
//		for (int j = 0; j<=this.getOutputlayer().length - 1; j++){
//			/*weight = weight + weight diff // hidden layers/output, can be integrated in the previous case but doesn't matter for now*/
//			this.getHiddenlayers().get(this.getHiddenlayers().size()-1).get(i).getOutputsynapses()[j].setWeight(this.getHiddenlayers().get(this.getHiddenlayers().size()-1).get(i).getOutputsynapses()[j].getWeight() + this.getHiddenlayers().get(this.getHiddenlayers().size()-1).get(i).getOutputsynapses()[j].getWeightdiff());
//		}
//	}
//}
