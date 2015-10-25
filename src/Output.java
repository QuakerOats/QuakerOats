
public class Output {
	
	public int epochsize;
	public double learningrate;
	public double trainerror;
	public double testerror;
	
	/*constructor*/
	public Output(){
		
	}
	
	public Output(int epochsize, double learningrate, double trainerror, double testerror ){
		this.epochsize = epochsize;
		this.learningrate = learningrate;
		this.trainerror = trainerror;
		this.testerror = testerror;
	}
	
}
