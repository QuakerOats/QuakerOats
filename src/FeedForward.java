
public class FeedForward extends NeuralNetwork {

	/* constructor */
	public FeedForward(int[] constructorTab) throws InvalidNetworkConstruction {
		super(constructorTab);
	}

	/*
	 * constructor where activation function and learning algorithm are
	 * specified
	 */
	public FeedForward(int[] constructorTab,
			ActivationFunction activationFunction,
			LearningAlgorithm learningAlgorithm)
			throws InvalidNetworkConstruction {
		super(constructorTab, activationFunction, learningAlgorithm);
	}

	/* links the network */
	public void linkNetwork() {
		for (int i = 0; i <= this.getConstructorTab().length - 2; i++) {
			for (int j = 0; j <= this.getConstructorTab()[i] - 1; j++) {
				for (int k = 0; k <= this.getConstructorTab()[i + 1] - 1; k++) {
					Synapse a = new Synapse(this.getLayer(i).get(j), this
							.getLayer(i + 1).get(k));
					((Neuron) this.getLayer(i).get(j)).getOutputsynapses().add(
							a);
					((Neuron) this.getLayer(i + 1).get(k)).getInputsynapses()
							.add(a);
				}
			}
		}
	}

	/* gives an input vector to the network */
	public void setInput(double[] input) {
		for (int i = 0; i <= input.length - 1; i++) {
			this.getInputlayer().getLayer().get(i).setInput(input[i]);
		}
	}

	/* propagates towards the end (activate all the neurons) */
	public void activate() {
		for (int i = 0; i <= this.getInputlayer().getLayer().size() - 1; i++) {
			this.getInputlayer().getLayer().get(i).activate();
		}
		for (int k = 0; k <= this.getHiddenlayers().size() - 1; k++) {
			for (int i = 0; i <= this.getHiddenlayers().get(k).getLayer()
					.size() - 1; i++) {
				this.getHiddenlayers().get(k).getLayer().get(i).activate();
			}
		}
		for (int i = 0; i <= this.getOutputlayer().getLayer().size() - 1; i++) {
			this.getOutputlayer().getLayer().get(i).activate();
		}
	}

	/* gives an input and propagates forward by activating all the neurons */
	public void forwardpropagation(double[] input) {
		this.setInput(input);
		this.activate();
	}

	/* gets the output given by the network */
	public double[] getOutput() {
		double[] output = new double[this.getOutputlayer().getLayer().size()];
		for (int i = 0; i <= this.getOutputlayer().getLayer().size() - 1; i++) {
			output[i] = this.getOutputlayer().getLayer().get(i).getActivation();
		}
		return output;
	}

	/* back propagates the error, neuron diff and weight diff are changed */
	public void backpropagation(double[] output) {

		// this.learningAlgorithm.calculateNeuronDiffs();

		// Calculate neurondiff and biasDiff for each Neuron of the Outputlayer
		for (int k = 0; k <= this.getOutputlayer().getLayer().size() - 1; k++) {
			double delta = this.getOutputlayer().getLayer().get(k).getActivationfunction()
					.applyDerivative(this.getOutputlayer().getLayer().get(k).getActivation())
					* (this.getOutputlayer().getLayer().get(k).getActivation() - output[k]);
			this.getOutputlayer().getLayer().get(k).setNeurondiff(delta);
			/* updates the weight */
			this.getOutputlayer().getLayer().get(k).setBiasDiff(this.getOutputlayer().getLayer().get(k)
					.getBiasDiff() + delta);
		}
		// Update weightdiff between the hidden layer and the outputlayer.
		for (int k = 0; k <= this.getHiddenlayers()
				.get(this.getHiddenlayers().size() - 1).getLayer().size() - 1; k++) {
			for (int i = 0; i <= this.getOutputlayer().getLayer().size() - 1; i++) {
				double deltaweight = this.getHiddenlayers()
						.get(this.getHiddenlayers().size() - 1).getLayer().get(k)
						.getOutputsynapses().get(i).getOutputneuron()
						.getNeurondiff()
						* this.getHiddenlayers()
								.get(this.getHiddenlayers().size() - 1).getLayer().get(k)
								.getActivation();
				double a = this.getHiddenlayers()
						.get(this.getHiddenlayers().size() - 1).getLayer().get(k)
						.getOutputsynapses().get(i).getWeightdiff();
				this.getHiddenlayers().get(this.getHiddenlayers().size() - 1)
						.getLayer().get(k).getOutputsynapses().get(i)
						.setWeightdiff(a + deltaweight);
			}
		}

		// Calculate neurondiff for each Neuron of the hiddenlayers

		/* for each layer */
		for (int k = 0; k <= this.getHiddenlayers().size() - 2; k++) {
			/* for each neuron in this layer */
			for (int i = 0; i <= this.getHiddenlayers().get(k).getLayer().size() - 1; i++) {
				double s = 0;
				/* for each output synapse of this neuron */
				for (int j = 0; j <= this.getHiddenlayers().get(k + 1).getLayer().size() - 1; j++) {
					s += this.getHiddenlayers().get(k).getLayer().get(i)
							.getOutputsynapses().get(j).getOutputneuron()
							.getNeurondiff()
							* this.getHiddenlayers().get(k).getLayer().get(i)
									.getOutputsynapses().get(j).getWeight();
				}
				double delta = this
						.getHiddenlayers()
						.get(k)
						.getLayer().get(i)
						.getActivationfunction()
						.applyDerivative(
								this.getHiddenlayers().get(k).getLayer().get(i)
										.getActivation())
						* s;
				this.getHiddenlayers().get(k).getLayer().get(i).setNeurondiff(delta);
				/* updates the weight */
				this.getHiddenlayers()
						.get(k)
						.getLayer().get(i)
						.setBiasDiff(
								this.getHiddenlayers().get(k).getLayer().get(i)
										.getBiasDiff()
										+ delta);
			}
		}
		// Update weightdiff between two neurons in hiddenlayers.
		for (int k = 0; k <= this.getHiddenlayers().size() - 2; k++) {
			for (int i = 0; i <= this.getHiddenlayers().get(k).getLayer().size() - 1; i++) {
				for (int j = 0; j <= this.getHiddenlayers().get(k + 1).getLayer().size() - 1; j++) {
					double delta = this.getHiddenlayers().get(k).getLayer().get(i)
							.getOutputsynapses().get(j).getWeightdiff()
							+ this.getHiddenlayers().get(k).getLayer().get(i)
									.getActivation()
							* this.getHiddenlayers().get(k).getLayer().get(i)
									.getOutputsynapses().get(j)
									.getOutputneuron().getNeurondiff();
					this.getHiddenlayers().get(k).getLayer().get(j).getOutputsynapses()
							.get(j).setWeightdiff(delta);
				}
			}
		}
		// Update weightdiff between the input layer and the first hiddenlayer.
		for (int k = 0; k <= this.getInputlayer().getLayer().size() - 1; k++) {
			for (int i = 0; i <= this.getHiddenlayers().get(0).getLayer().size() - 1; i++) {
				double delta = this.getInputlayer().getLayer().get(k).getOutputsynapses()
						.get(i).getOutputneuron().getNeurondiff()
						* this.getInputlayer().getLayer().get(k).getActivation()
						+ this.getInputlayer().getLayer().get(k).getOutputsynapses().get(i)
								.getWeightdiff();
				this.getInputlayer().getLayer().get(k).getOutputsynapses().get(i)
						.setWeightdiff(delta);
			}
		}

	}

	/* trains the network */
	public void train(double[][] inputs, double[][] outputs) {

		/*
		 * for each input, calculates the neurons's diff and synapases's weight
		 * diff
		 */
		for (int i = 0; i <= inputs.length - 1; i++) {
			/*
			 * this is written with methods in NeuralNetwork class, we now use
			 * the LearningAlgorithm class
			 * 
			 * this.forwardpropagation(inputs[i]);
			 * this.backpropagation(outputs[i]);
			 */
			this.getLearningAlgorithm().calculateActivations(inputs[i]);
			this.getLearningAlgorithm()
					.calculateNeuronAndWeightDiffs(inputs[i]);
		}

		/* changes the neurons's value and the synapses's weight */
		for (int i = 0; i <= this.getInputlayer().getLayer().size() - 1; i++) {
			for (int j = 0; j <= this.getHiddenlayers().get(0).getLayer().size() - 1; j++) {
				/* weight = weight + weight diff // input/hidden layers */
				this.getInputlayer().getLayer().get(i)
						.getOutputsynapses()
						.get(j)
						.setWeight(
								this.getInputlayer().getLayer().get(i).getOutputsynapses()
										.get(j).getWeight()
										+ this.getInputlayer().getLayer().get(i)
												.getOutputsynapses().get(j)
												.getWeightdiff());
			}
		}
		for (int k = 0; k <= this.getHiddenlayers().size() - 2; k++) {
			for (int i = 0; i <= this.getHiddenlayers().get(k).getLayer().size() - 1; i++) {
				for (int j = 0; j <= this.getHiddenlayers().get(k + 1).getLayer().size() - 1; j++) {
					/* weight = weight + weight diff // hidden layers */
					this.getHiddenlayers()
							.get(k)
							.getLayer().get(i)
							.getOutputsynapses()
							.get(j)
							.setWeight(
									this.getHiddenlayers().get(k).getLayer().get(i)
											.getOutputsynapses().get(j)
											.getWeight()
											+ this.getHiddenlayers().get(k)
													.getLayer().get(i).getOutputsynapses()
													.get(j).getWeightdiff());
				}
			}
		}
		for (int i = 0; i <= this.getHiddenlayers()
				.get(this.getHiddenlayers().size() - 1).getLayer().size() - 1; i++) {
			for (int j = 0; j <= this.getOutputlayer().getLayer().size() - 1; j++) {
				/*
				 * weight = weight + weight diff // hidden layers/output, can be
				 * integrated in the previous case but doesn't matter for now
				 */
				this.getHiddenlayers()
						.get(this.getHiddenlayers().size() - 1)
						.getLayer().get(i)
						.getOutputsynapses()
						.get(j)
						.setWeight(
								this.getHiddenlayers()
										.get(this.getHiddenlayers().size() - 1)
										.getLayer().get(i).getOutputsynapses().get(j)
										.getWeight()
										+ this.getHiddenlayers()
												.get(this.getHiddenlayers()
														.size() - 1).getLayer().get(i)
												.getOutputsynapses().get(j)
												.getWeightdiff());
			}
		}
	}

}
