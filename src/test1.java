
import java.io.IOException;
public class test1 {

    /**
     * @param args
     */
    public static void main(String[] args) throws IOException, InvalidNetworkConstruction {
 
    		MnistManager learningDataManager = new MnistManager("C:/Users/GOUNGOUNE/Desktop/Deep learning/MNIST/train-images.idx3-ubyte","C:/Users/GOUNGOUNE/Desktop/Deep learning/MNIST/train-labels.idx1-ubyte" );
    		//MnistManager testDataManager = new MnistManager("src/t10k-images.idx3-ubyte","src/t10k-labels.idx1-ubyte");
    		int[] constructorTab =new int[]{784,90,10};
    		FeedForward ff = new FeedForward(constructorTab);
    		ff.linkNetwork();
    		double[][] inputdata = new double[10000][28*28];
    		double[][] outputdata = new double[10000][10];
  		BackProp bp = new BackProp(ff);
    		ff.setLearningAlgorithm(bp);
          for(int i=0; i<10000; i++){
            	learningDataManager.setCurrent(i+1);
           	int[][] a = learningDataManager.readImage();
           	for(int j=0; j<=27; j++){           		for(int k=0; k<=27; k++){
            			inputdata[i][28*j+k] = a[j][k];
            			//System.out.println(inputdata[i][28*j+k]);
            		}
            	}
            	int b = learningDataManager.readLabel();
           		outputdata[i][b] = 1;
               	
          	}
         bp.globaltraining(inputdata, outputdata);
            ToCsv csv = new ToCsv(bp);
            csv.writeCsvFile("C:/Users/GOUNGOUNE/Desktop/Deep learning/MNIST/trainscv.csv");
		System.out.println(outputdata[886][5] + "ref");
//		System.out.println(outputdata[886][3]);
//		System.out.println(0 - outputdata[886][5]);
//		System.out.println(outputdata[886][5]);
//		System.out.println(outputdata[886][6]);
//		System.out.println(outputdata[886][7]);
//		System.out.println(outputdata[886][8]);
//		System.out.println(outputdata[886][9]);
              double[] a = outputdata[1652];
              bp.calculateActivations(a);
              System.out.println(bp.getNeuralNetwork().getOutputlayer()[0].getActivation());
              System.out.println(bp.getNeuralNetwork().getOutputlayer()[1].getActivation());
              System.out.println(bp.getNeuralNetwork().getOutputlayer()[2].getActivation());
              System.out.println(bp.getNeuralNetwork().getOutputlayer()[3].getActivation());
              System.out.println(bp.getNeuralNetwork().getOutputlayer()[4].getActivation());
              System.out.println(bp.getNeuralNetwork().getOutputlayer()[5].getActivation());
              System.out.println(bp.getNeuralNetwork().getOutputlayer()[6].getActivation());
              System.out.println(bp.getNeuralNetwork().getOutputlayer()[7].getActivation());
              System.out.println(bp.getNeuralNetwork().getOutputlayer()[8].getActivation());
              System.out.println(bp.getNeuralNetwork().getOutputlayer()[9].getActivation());
            }
    		

    	
    
}
