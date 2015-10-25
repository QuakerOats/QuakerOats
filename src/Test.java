

public class Test {

	public static void main(String[] args) throws InvalidNetworkConstruction {
		
		int[] constructor = new int[]{2,1,1};
		FeedForward network = new FeedForward(constructor);
		network.linkNetwork();
		double[][] inputs = new double[][]{{0,0},{0,1},{1,0},{1,1}};
	    double[][] outputs = new double[][] {{0},{1},{1},{1}};
	    System.out.println(network.getOutputlayer()[0].getActivation());
	    network.getLearningAlgorithm().train(inputs, outputs);
	    System.out.println(network.getOutputlayer()[0].getActivation());
	    
	}
}
