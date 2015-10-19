public class InvalidNetworkConstruction extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidNetworkConstruction(){
		System.out.println("You tried to create a network with an array of size < 3");
	}
	
}
