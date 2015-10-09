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
    for(int i = 0; i<=this.getInputlayer().length-1;i++){
    	Synapse[] outputsynapses = new Synapse[this.getHiddenlayers()[1].length];
    	for(int j = 0; j<=this.getHiddenlayers()[1].length-1;j++){
    		outputsynapses[j].setOutputneuron(this.getHiddenlayers()[1][j]);
    	    outputsynapses[j].setInputneuron(this.getInputlayer()[i]);
    		this.getInputlayer()[i].setOutputsynapses(outputsynapses);
    		
    	}
    }
    	for(int i = 0; i<=this.getHiddenlayers()[1].length-1;i++){
        	Synapse[] inputsynapses = new Synapse[this.getInputlayer().length];
        	for(int j = 0; j<=this.getInputlayer().length-1;j++){
        		inputsynapses[j].setOutputneuron(this.getHiddenlayers()[1][i]);
        	    inputsynapses[j].setInputneuron(this.getInputlayer()[j]);
        	    inputsynapses[j].setWeight(2*(Math.random()-0.5)*2.4/this.getInputlayer().length);
        		this.getHiddenlayers()[1][i].setInputsynapses(inputsynapses);
        		
        	}
    	
    }
    for(int k =1; k<=this.getHiddenlayers().length-2;k++){
    for(int i = 0; i<=this.getHiddenlayers()[k].length-1;i++){
    	Synapse[] outputsynapses = new Synapse[this.getHiddenlayers()[k+1].length];
    	for(int j = 0; j<=this.getHiddenlayers()[k+1].length-1;j++){
    		outputsynapses[j].setOutputneuron(getHiddenlayers()[k+1][j]);
    	    outputsynapses[j].setInputneuron(this.getHiddenlayers()[k][i]);
    		this.getHiddenlayers()[k][i].setOutputsynapses(outputsynapses);
    	}
    }
    
	}
    for(int k =1; k<=this.getHiddenlayers().length-2;k++){
        for(int i = 0; i<=this.getHiddenlayers()[k+1].length-1;i++){
        	Synapse[] inputsynapses = new Synapse[this.getHiddenlayers()[k].length];
        	for(int j = 0; j<=this.getHiddenlayers()[k].length-1;j++){
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
    		outputsynapses[j].setOutputneuron(this.getOutputlayer()[j]);
    	    outputsynapses[j].setInputneuron(this.getHiddenlayers()[getHiddenlayers().length-1][i]);
    		this.getHiddenlayers()[getHiddenlayers().length-1][i].setOutputsynapses(outputsynapses);
    		
    	}
    }
    for(int i = 0; i<=this.getOutputlayer().length-1;i++){
    	Synapse[] inputsynapses = new Synapse[this.getHiddenlayers()[getHiddenlayers().length-1].length];
    	for(int j = 0; j<=this.getHiddenlayers()[getHiddenlayers().length-1].length-1;j++){
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
	for(int i = 0; i<= getHiddenlayers()[k].length;i++){
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
		this.linkNetwork();
		this.fire();
	}
}
