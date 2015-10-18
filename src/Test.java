
public class Test {

	public static void main(String[] args) {
	   Sigmoid a = new Sigmoid();
	   InputNeuron i1 = new InputNeuron(a);
	   InputNeuron i2 = new InputNeuron(a);
	   IntermediateNeuron h11 = new IntermediateNeuron(a);
	   IntermediateNeuron h12 = new IntermediateNeuron(a);
	   IntermediateNeuron h21 = new IntermediateNeuron(a);
	   IntermediateNeuron h22 = new IntermediateNeuron(a);
	   OutputNeuron o1 = new OutputNeuron(a);
	   OutputNeuron o2 = new OutputNeuron(a);
	   InputNeuron[] inputlayer = new InputNeuron[]{i1,i2};
	   IntermediateNeuron[][] hiddenlayers = new IntermediateNeuron[][] {{h11,h12},{h21,h22}};
	   OutputNeuron[] outputlayer = new OutputNeuron[] {o1,o2};
       FeedForward premiertest = new FeedForward(inputlayer,hiddenlayers,outputlayer);
       double[][] inputs = new double[][]{{0,0},{0,1},{1,0},{1,1}};
       double[][] outputs = new double[][] {{0,0},{1,0},{1,0},{1,0}};
       Synapse[] outputsynapses = new Synapse[3];
       Synapse d = null;
       d = new Synapse();
       d.getWeight();
       System.out.println(premiertest.getHiddenlayers().length);
       premiertest.linkNetwork();
       premiertest.train(inputs, outputs);
       double[] you = new double[]{1,0};
       premiertest.launch(you);
       System.out.println(o1.getActivation());
       System.out.println(o2.getActivation());
}
}
