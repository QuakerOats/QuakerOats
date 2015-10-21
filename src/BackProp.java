
public class BackProp extends LearningAlgorithm{

	public void launch(Input in) {
		
		
	}
	
	/*forward propagates the input, neuron's activation are changed*/
	public void calculateActivations(double[] input){
		
		/*present an input to the network*/
		for (int i=0;i<=input.length-1;i++) {
			this.neuralNetwork.getInputlayer()[i].setInput(input[i]);
		}
		
		/*forward propagates the input by activating each neuron*/
		for(int i = 0; i<= this.neuralNetwork.getInputlayer().length-1;i++){
			this.neuralNetwork.getInputlayer()[i].activate();
		}
		for(int k=0; k<=this.neuralNetwork.getHiddenlayers().size()-1;k++){
			for(int i = 0; i<= this.neuralNetwork.getHiddenlayers().get(k).size()-1;i++){
				this.neuralNetwork.getHiddenlayers().get(k).get(i).activate();
			}
		}
		for(int i = 0; i<=this.neuralNetwork.getOutputlayer().length-1;i++){
			this.neuralNetwork.getOutputlayer()[i].activate();
		}
		
		
	}
	
	/*back propagates the error, neuron's diff and synapses's weight diff are changed*/
	public void calculateNeuronAndWeightDiffs(double[] ouput){
		
		// Calculate neuron diff for the output layer
		for(int k=0; k<= this.neuralNetwork.getOutputlayer().length - 1; k++){
			double delta = this.neuralNetwork.getOutputlayer()[k].getActivationfunction().applyDerivative(this.neuralNetwork.getOutputlayer()[k].getActivation())
						  *(this.neuralNetwork.getOutputlayer()[k].getActivation()-ouput[k]);
			this.neuralNetwork.getOutputlayer()[k].setNeurondiff(delta);
		}
		
		//Update weight diff between the last hidden layer and the output layer
		for(int k= 0; k<=this.neuralNetwork.getHiddenlayers().get(this.neuralNetwork.getHiddenlayers().size()-1).size()-1;k++){
			for(int i = 0; i<=this.neuralNetwork.getOutputlayer().length - 1;i++){
		    	double deltaweight = this.neuralNetwork.getHiddenlayers().get(this.neuralNetwork.getHiddenlayers().size()-1).get(k).getOutputsynapses()[i].getOutputneuron().getNeurondiff()
		    						*this.neuralNetwork.getHiddenlayers().get(this.neuralNetwork.getHiddenlayers().size()-1).get(k).getActivation();
		    	double a = this.neuralNetwork.getHiddenlayers().get(this.neuralNetwork.getHiddenlayers().size()-1).get(k).getOutputsynapses()[i].getWeightdiff();
		    	this.neuralNetwork.getHiddenlayers().get(this.neuralNetwork.getHiddenlayers().size()-1).get(k).getOutputsynapses()[i].setWeightdiff(a+deltaweight);
			}
		}
		// Calculate neurondiff for each Neuron of the hiddenlayers
		
		/*for each layer*/
		for (int k = 0; k <= this.neuralNetwork.getHiddenlayers().size() - 2; k++) {
			/*for each neuron in this layer*/
			for (int i = 0; i <= this.neuralNetwork.getHiddenlayers().get(k).size() - 1; i++) {
				double s = 0;
				/*for each output synapse of this neuron*/
				for (int j = 0; j<= this.neuralNetwork.getHiddenlayers().get(k+1).size() - 1;j++){
					s += this.neuralNetwork.getHiddenlayers().get(k).get(i).getOutputsynapses()[j].getOutputneuron().getNeurondiff()
						*this.neuralNetwork.getHiddenlayers().get(k).get(i).getOutputsynapses()[j].getWeight();
				}
				double delta = this.neuralNetwork.getHiddenlayers().get(k).get(i).getActivationfunction().applyDerivative(this.neuralNetwork.getHiddenlayers().get(k).get(i).getActivation())
							  *s;
				this.neuralNetwork.getHiddenlayers().get(k).get(i).setNeurondiff(delta);
			}
		}
		//Update weightdiff between two neurons in hiddenlayers.
		for( int k = 0; k<= this.neuralNetwork.getHiddenlayers().size() - 2;k++){
			for (int i = 0 ; i<=this.neuralNetwork.getHiddenlayers().get(k).size() - 1; i++){
				for(int j = 0 ; j<=this.neuralNetwork.getHiddenlayers().get(k+1).size()-1;j++){
				double delta = this.neuralNetwork.getHiddenlayers().get(k).get(i).getOutputsynapses()[j].getWeightdiff() 
							  +this.neuralNetwork.getHiddenlayers().get(k).get(i).getActivation()
							  *this.neuralNetwork.getHiddenlayers().get(k).get(i).getOutputsynapses()[j].getOutputneuron().getNeurondiff();
				this.neuralNetwork.getHiddenlayers().get(k).get(j).getOutputsynapses()[j].setWeightdiff(delta);
				}
			}
		}
		//Update weightdiff between the input layer and the first hiddenlayer.
		for(int k= 0; k<=this.neuralNetwork.getInputlayer().length-1;k++){
			for(int i = 0; i<=this.neuralNetwork.getHiddenlayers().get(0).size() - 1;i++){
				double delta = this.neuralNetwork.getInputlayer()[k].getOutputsynapses()[i].getOutputneuron().getNeurondiff()
							  *this.neuralNetwork.getInputlayer()[k].getActivation()
							  +this.neuralNetwork.getInputlayer()[k].getOutputsynapses()[i].getWeightdiff();
				this.neuralNetwork.getInputlayer()[k].getOutputsynapses()[i].setWeightdiff(delta);
			}
		}

		
	}

	/*launch the training*/
	public void train(double[][] inputs, double[][] outputs){
		
		/*for each input, calculates the neurons's diff and synapases's weight diff*/
		for(int i = 0; i<=inputs.length-1;i++){
			this.calculateActivations(inputs[i]);
			this.calculateNeuronAndWeightDiffs(inputs[i]);
		}
		
		/*changes the neurons's value and the synapses's weight*/
		for(int i= 0; i<=this.neuralNetwork.getInputlayer().length - 1; i++){
			for (int j = 0; j<=this.neuralNetwork.getHiddenlayers().get(0).size() - 1; j++){
				/*weight = weight + weight diff // input/hidden layers*/
				this.neuralNetwork.getInputlayer()[i].getOutputsynapses()[j].setWeight(this.neuralNetwork.getInputlayer()[i].getOutputsynapses()[j].getWeight() + this.neuralNetwork.getInputlayer()[i].getOutputsynapses()[j].getWeightdiff());
			}
		}
		for( int k = 0; k<= this.neuralNetwork.getHiddenlayers().size() - 2;k++){
			for (int i = 0 ; i<=this.neuralNetwork.getHiddenlayers().get(k).size() - 1; i++){
				for(int j = 0 ; j<=this.neuralNetwork.getHiddenlayers().get(k+1).size()-1;j++){
					/*weight = weight + weight diff // hidden layers*/
					this.neuralNetwork.getHiddenlayers().get(k).get(i).getOutputsynapses()[j].setWeight(this.neuralNetwork.getHiddenlayers().get(k).get(i).getOutputsynapses()[j].getWeight() + this.neuralNetwork.getHiddenlayers().get(k).get(i).getOutputsynapses()[j].getWeightdiff());
				}
			}
		}
		for(int i= 0; i<=this.neuralNetwork.getHiddenlayers().get(this.neuralNetwork.getHiddenlayers().size()-1).size() - 1; i++){
			for (int j = 0; j<=this.neuralNetwork.getOutputlayer().length - 1; j++){
				/*weight = weight + weight diff // hidden layers/output, can be integrated in the previous case but doesn't matter for now*/
				this.neuralNetwork.getHiddenlayers().get(this.neuralNetwork.getHiddenlayers().size()-1).get(i).getOutputsynapses()[j].setWeight(this.neuralNetwork.getHiddenlayers().get(this.neuralNetwork.getHiddenlayers().size()-1).get(i).getOutputsynapses()[j].getWeight() + this.neuralNetwork.getHiddenlayers().get(this.neuralNetwork.getHiddenlayers().size()-1).get(i).getOutputsynapses()[j].getWeightdiff());
			}
		}
	}

	
	
}
