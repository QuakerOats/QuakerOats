public class FeedForward extends NeuralNetwork {
	
	public FeedForward(int[] constructorTab) throws InvalidNetworkConstruction{
		super(constructorTab);
	}
	
	public FeedForward(int[] constructorTab, ActivationFunction activationFunction, LearningAlgorithm learningAlgorithm) throws InvalidNetworkConstruction{
		super(constructorTab, activationFunction, learningAlgorithm);
	}

	
	/*Redundancy with Synapse [], would be better with ArrayList, see later*/
	
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

	public void setInputs(double[] inputs) {
		for (int i=0;i<=inputs.length-1;i++) {
			this.getInputlayer()[i].setInput(inputs[i]);
		}
	}

	public void activate() {
		for(int i = 0; i<= this.getInputlayer().length-1;i++){
			this.getInputlayer()[i].activate();
		}
		for(int k=0; k<=this.getHiddenlayers().size()-1;k++){
			for(int i = 0; i<= getHiddenlayers().get(k).size()-1;i++){
				this.getHiddenlayers().get(k).get(i).activate();
			}
		}
		for(int i = 0; i<=this.getOutputlayer().length-1;i++){
			this.getOutputlayer()[i].activate();
		}
	}
	
	public void launch(double[] inputs){
		/*for(int i = 0; i<=this.getInputlayer().length-1;i++){
			this.getInputlayer()[i].setInput(inputs[i]);
		}*/
		this.setInputs(inputs);
		this.activate();
	}
	
	public double[] getOutputs() {
		double[] outputs = new double[this.getOutputlayer().length];
		for (int i = 0; i <= this.getOutputlayer().length - 1; i++) {
			outputs[i] = this.getOutputlayer()[i].getActivation();
		}
		return outputs;
	}
	
	public void backpropagation(double[] inputs, double[] outputs){
		// Calculate neurondiff for each Neuron of the Outputlayer
		for(int k=0; k<= this.getOutputlayer().length - 1; k++){
			double delta = this.getOutputlayer()[k].getActivationfunction().applyDerivative(this.getOutputlayer()[k].getActivation())*(this.getOutputlayer()[k].getActivation()-outputs[k]);
			this.getOutputlayer()[k].setNeurondiff(delta);
		}
		//Update weightdiff between the hidden layer and the outputlayer.
		for(int k= 0; k<=this.getHiddenlayers().get(this.getHiddenlayers().size()-1).size()-1;k++){
			for(int i = 0; i<=this.getOutputlayer().length - 1;i++){
	    	double deltaweight = this.getHiddenlayers().get(this.getHiddenlayers().size()-1).get(k).getOutputsynapses()[i].getOutputneuron().getNeurondiff()
	    						*this.getHiddenlayers().get(this.getHiddenlayers().size()-1).get(k).getActivation();
	    	double a = this.getHiddenlayers().get(this.getHiddenlayers().size()-1).get(k).getOutputsynapses()[i].getWeightdiff();
	    	this.getHiddenlayers().get(this.getHiddenlayers().size()-1).get(k).getOutputsynapses()[i].setWeightdiff(a+deltaweight);
	    }
		}
		// Calculate neurondiff for each Neuron of the hiddenlayers
		for (int k = 0; k <= this.getHiddenlayers().size() - 2; k++) {
			for (int i = 0; i <= this.getHiddenlayers().get(k).size() - 1; i++) {
				double s = 0;
				for (int j = 0; j<= this.getHiddenlayers().get(k+1).size() - 1;j++){
				s += this.getHiddenlayers().get(k).get(i).getOutputsynapses()[j].getOutputneuron().getNeurondiff()
					*this.getHiddenlayers().get(k).get(i).getOutputsynapses()[j].getWeight();
			}
			double delta = this.getHiddenlayers().get(k).get(i).getActivation()
						  *(1-this.getHiddenlayers().get(k).get(i).getActivation())*s;
			this.getHiddenlayers().get(k).get(i).setNeurondiff(delta);
		}
		}
		//Update weightdiff between two neurons in hiddenlayers.
		for( int k = 0; k<= this.getHiddenlayers().size() - 2;k++){
			for (int i = 0 ; i<=this.getHiddenlayers().get(k).size() - 1; i++){
				for(int j = 0 ; j<=this.getHiddenlayers().get(k+1).size()-1;j++){
				double delta = this.getHiddenlayers().get(k).get(i).getOutputsynapses()[j].getWeightdiff() 
							  +this.getHiddenlayers().get(k).get(i).getActivation()
							  *this.getHiddenlayers().get(k).get(i).getOutputsynapses()[j].getOutputneuron().getNeurondiff();
				this.getHiddenlayers().get(k).get(j).getOutputsynapses()[j].setWeightdiff(delta);
			}
			}
		}
		//Update weightdiff between the input layer and the first hiddenlayer.
				for(int k= 0; k<=this.getInputlayer().length-1;k++){
					for(int i = 0; i<=this.getHiddenlayers().get(0).size() - 1;i++){
			    	double delta = this.getInputlayer()[k].getOutputsynapses()[i].getOutputneuron().getNeurondiff()*this.getInputlayer()[k].getActivation()+ this.getInputlayer()[k].getOutputsynapses()[i].getWeightdiff();
			    	this.getHiddenlayers().get(this.getHiddenlayers().size()-1).get(k).getOutputsynapses()[i].setWeightdiff(delta);
			    }
				}

	}
	
	public void train(double[][] inputs, double[][] outputs){
		for(int i = 0; i<=inputs.length-1;i++){
			this.launch(inputs[i]);
			this.backpropagation(inputs[i], outputs[i]);
		}
		for(int i= 0; i<=this.getInputlayer().length - 1; i++){
			for (int j = 0; j<=this.getHiddenlayers().get(0).size() - 1; j++){
			this.getInputlayer()[i].getOutputsynapses()[j].setWeight(this.getInputlayer()[i].getOutputsynapses()[j].getWeight() + this.getInputlayer()[i].getOutputsynapses()[j].getWeightdiff());
			}
		}
		for( int k = 0; k<= this.getHiddenlayers().size() - 2;k++){
			for (int i = 0 ; i<=this.getHiddenlayers().get(k).size() - 1; i++){
				for(int j = 0 ; j<=this.getHiddenlayers().get(k+1).size()-1;j++){
				this.getHiddenlayers().get(k).get(i).getOutputsynapses()[j].setWeight(this.getHiddenlayers().get(k).get(i).getOutputsynapses()[j].getWeight() + this.getHiddenlayers().get(k).get(i).getOutputsynapses()[j].getWeightdiff());
			}
			}
		}
		for(int i= 0; i<=this.getHiddenlayers().get(this.getHiddenlayers().size()-1).size() - 1; i++){
			for (int j = 0; j<=this.getOutputlayer().length - 1; j++){
				this.getHiddenlayers().get(this.getHiddenlayers().size()-1).get(i).getOutputsynapses()[j].setWeight(this.getHiddenlayers().get(this.getHiddenlayers().size()-1).get(i).getOutputsynapses()[j].getWeight() + this.getHiddenlayers().get(this.getHiddenlayers().size()-1).get(i).getOutputsynapses()[j].getWeightdiff());
			}
		}
	}
	
}
