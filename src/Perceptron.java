public class Perceptron extends AbstractNeuralNetwork {
	public Perceptron(InputNeuron[] inputlayer,
			IntermediateNeuron[][] hiddenlayers, OutputNeuron[] outputlayer) {
		super(inputlayer, hiddenlayers, outputlayer);
	}

	public double[] getOutputs() {
		double[] outputs = new double[this.getOutputlayer().length];
		for (int i = 0; i <= this.getOutputlayer().length - 1; i++) {
			outputs[i] = this.getOutputlayer()[i].getOutput();
		}
		return outputs;
	}

	public void setInputs(double[] inputs) {
		for (int i = 0; i <= inputs.length - 1; i++) {
			this.getInputlayer()[i].setInput(inputs[i]);
		}
	}

	public void linkNetwork() {
    for(int i = 0; i<=(this.getInputlayer().length-1);i++){
    	Synapse[] outputsynapses = new Synapse[this.getHiddenlayers()[0].length];
    	for(int j = 0; j<=(this.getHiddenlayers()[0].length-1);j++){
    		outputsynapses[j] = new Synapse();
    		outputsynapses[j].setOutputneuron(this.getHiddenlayers()[0][j]);
    	    outputsynapses[j].setInputneuron(this.getInputlayer()[0]);
    		this.getInputlayer()[i].setOutputsynapses(outputsynapses);
    		
    	}
    }
    	for(int i = 0; i<=this.getHiddenlayers()[0].length-1;i++){
        	Synapse[] inputsynapses = new Synapse[this.getInputlayer().length];
        	for(int j = 0; j<=this.getInputlayer().length-1;j++){
        		inputsynapses[j] = new Synapse();
        		inputsynapses[j].setOutputneuron(this.getHiddenlayers()[0][i]);
        	    inputsynapses[j].setInputneuron(this.getInputlayer()[j]);
        	    inputsynapses[j].setWeight(2*(Math.random()-0.5)*2.4/this.getInputlayer().length);
        		this.getHiddenlayers()[0][i].setInputsynapses(inputsynapses);
        		
        	}
    	
    }
    for(int k =0; k<=this.getHiddenlayers().length-2;k++){
    for(int i = 0; i<=this.getHiddenlayers()[k].length-1;i++){
    	Synapse[] outputsynapses = new Synapse[this.getHiddenlayers()[k+1].length];
    	for(int j = 0; j<=this.getHiddenlayers()[k+1].length-1;j++){
    		outputsynapses[j] = new Synapse();
    		outputsynapses[j].setOutputneuron(getHiddenlayers()[k+1][j]);
    	    outputsynapses[j].setInputneuron(this.getHiddenlayers()[k][i]);
    		this.getHiddenlayers()[k][i].setOutputsynapses(outputsynapses);
    	}
    }
    
	}
    for(int k =0; k<=this.getHiddenlayers().length-2;k++){
        for(int i = 0; i<=this.getHiddenlayers()[k+1].length-1;i++){
        	Synapse[] inputsynapses = new Synapse[this.getHiddenlayers()[k].length];
        	for(int j = 0; j<=this.getHiddenlayers()[k].length-1;j++){
        		inputsynapses[j] = new Synapse();
        		inputsynapses[j].setOutputneuron(getHiddenlayers()[k+1][i]);
        	    inputsynapses[j].setInputneuron(this.getHiddenlayers()[k][j]);
        	    inputsynapses[j].setWeight(2*(Math.random()-0.5)*2.4/this.getHiddenlayers()[k].length);
        		this.getHiddenlayers()[k+1][i].setInputsynapses(inputsynapses);
        	}
        }
        
    	}
    for(int i = 0; i<=this.getHiddenlayers()[getHiddenlayers().length-1].length-1;i++){
    	Synapse[] outputsynapses = new Synapse[this.getOutputlayer().length];
    	for(int j = 0; j<=this.getOutputlayer().length-1;j++){
    		outputsynapses[j] = new Synapse();
    		outputsynapses[j].setOutputneuron(this.getOutputlayer()[j]);
    	    outputsynapses[j].setInputneuron(this.getHiddenlayers()[getHiddenlayers().length-1][i]);
    		this.getHiddenlayers()[getHiddenlayers().length-1][i].setOutputsynapses(outputsynapses);
    		
    	}
    }
    for(int i = 0; i<=this.getOutputlayer().length-1;i++){
    	Synapse[] inputsynapses = new Synapse[this.getHiddenlayers()[getHiddenlayers().length-1].length];
    	for(int j = 0; j<=this.getHiddenlayers()[getHiddenlayers().length-1].length-1;j++){
    		inputsynapses[j] = new Synapse();
    		inputsynapses[j].setOutputneuron(this.getOutputlayer()[i]);
    	    inputsynapses[j].setInputneuron(this.getHiddenlayers()[getHiddenlayers().length-1][j]);
    	    inputsynapses[j].setWeight(2*(Math.random()-0.5)*2.4/this.getHiddenlayers()[getHiddenlayers().length-1].length);
    		this.getOutputlayer()[i].setInputsynapses(inputsynapses);
    		
    	}
	
}
   
	}

	public void fire() {
for(int i = 0; i<= this.getInputlayer().length-1;i++){
	this.getInputlayer()[i].fire();
}
for(int k=0; k<=this.getHiddenlayers().length-1;k++){
	for(int i = 0; i<= getHiddenlayers()[k].length-1;i++){
		this.getHiddenlayers()[k][i].fire();
	}
}
for(int i = 0; i<=this.getOutputlayer().length-1;i++){
	this.getOutputlayer()[i].fire();
}
	}
	public void launch(double[] inputs){
		for(int i = 0; i<=this.getInputlayer().length-1;i++){
			this.getInputlayer()[i].setInput(inputs[i]);
		}
		this.fire();
	}
	public void backpropagation(double[] inputs, double[] outputs){
		// Calculate neurondiff for each Neuron of the Outputlayer
		for(int k=0; k<= this.getOutputlayer().length - 1; k++){
			double delta = this.getOutputlayer()[k].getActivationfunction().applyDerivative(this.getOutputlayer()[k].getOutput())*(this.getOutputlayer()[k].getOutput()-outputs[k]);
			this.getOutputlayer()[k].setNeurondiff(delta);
		}
		//Update weightdiff between the hidden layer and the outputlayer.
		for(int k= 0; k<=this.getHiddenlayers()[getHiddenlayers().length-1].length-1;k++){
			for(int i = 0; i<=this.getOutputlayer().length - 1;i++){
	    	double deltaweight = this.getHiddenlayers()[getHiddenlayers().length-1][k].getOutputsynapses()[i].getOutputneuron().getNeurondiff()*this.getHiddenlayers()[getHiddenlayers().length-1][k].getOutput();
	    	double a = this.getHiddenlayers()[getHiddenlayers().length-1][k].getOutputsynapses()[i].getWeightdiff();
	    	this.getHiddenlayers()[getHiddenlayers().length-1][k].getOutputsynapses()[i].setWeightdiff(a+deltaweight);
	    }
		}
		// Calculate neurondiff for each Neuron of the hiddenlayers
		for (int k = 0; k <= this.getHiddenlayers().length - 2; k++) {
			for (int i = 0; i <= this.getHiddenlayers()[k].length - 1; i++) {
				double s = 0;
				for (int j = 0; j<= this.getHiddenlayers()[k+1].length - 1;j++){
				s = s + this.getHiddenlayers()[k][i].getOutputsynapses()[j].getOutputneuron().getNeurondiff()*this.getHiddenlayers()[k][i].getOutputsynapses()[j].getWeight();
			}
			double delta = this.getHiddenlayers()[k][i].getOutput()*(1-this.getHiddenlayers()[k][i].getOutput())*s;
			this.getHiddenlayers()[k][i].setNeurondiff(delta);
		}
		}
		//Update weightdiff between two neurons in hiddenlayers.
		for( int k = 0; k<= this.getHiddenlayers().length - 2;k++){
			for (int i = 0 ; i<=this.getHiddenlayers()[k].length - 1; i++){
				for(int j = 0 ; j<=this.getHiddenlayers()[k+1].length-1;j++){
				double delta = this.getHiddenlayers()[k][i].getOutputsynapses()[j].getWeightdiff() + this.getHiddenlayers()[k][i].getOutput()*this.getHiddenlayers()[k][i].getOutputsynapses()[j].getOutputneuron().getNeurondiff();
				this.getHiddenlayers()[k][j].getOutputsynapses()[j].setWeightdiff(delta);
			}
			}
		}
		//Update weightdiff between the input layer and the first hiddenlayer.
				for(int k= 0; k<=this.getInputlayer().length-1;k++){
					for(int i = 0; i<=this.getHiddenlayers()[0].length - 1;i++){
			    	double delta = this.getInputlayer()[k].getOutputsynapses()[i].getOutputneuron().getNeurondiff()*this.getInputlayer()[k].getOutput()+ this.getInputlayer()[k].getOutputsynapses()[i].getWeightdiff();
			    	this.getHiddenlayers()[getHiddenlayers().length-1][k].getOutputsynapses()[i].setWeightdiff(delta);
			    }
				}

	}
	public void train(double[][] inputs, double[][] outputs){
		for(int i = 0; i<=inputs.length-1;i++){
			this.launch(inputs[i]);
			this.backpropagation(inputs[i], outputs[i]);
		}
		for(int i= 0; i<=this.getInputlayer().length - 1; i++){
			for (int j = 0; j<=this.getHiddenlayers()[0].length - 1; j++){
			this.getInputlayer()[i].getOutputsynapses()[j].setWeight(this.getInputlayer()[i].getOutputsynapses()[j].getWeight() + this.getInputlayer()[i].getOutputsynapses()[j].getWeightdiff());
			}
		}
		for( int k = 0; k<= this.getHiddenlayers().length - 2;k++){
			for (int i = 0 ; i<=this.getHiddenlayers()[k].length - 1; i++){
				for(int j = 0 ; j<=this.getHiddenlayers()[k+1].length-1;j++){
				this.getHiddenlayers()[k][i].getOutputsynapses()[j].setWeight(this.getHiddenlayers()[k][i].getOutputsynapses()[j].getWeight() + this.getHiddenlayers()[k][i].getOutputsynapses()[j].getWeightdiff());
			}
			}
		}
		for(int i= 0; i<=this.getHiddenlayers()[this.getHiddenlayers().length-1].length - 1; i++){
			for (int j = 0; j<=this.getOutputlayer().length - 1; j++){
				this.getHiddenlayers()[this.getHiddenlayers().length-1][i].getOutputsynapses()[j].setWeight(this.getHiddenlayers()[this.getHiddenlayers().length-1][i].getOutputsynapses()[j].getWeight() + this.getHiddenlayers()[this.getHiddenlayers().length-1][i].getOutputsynapses()[j].getWeightdiff());
			}
		}
	}
}
