import java.net.Socket;

/*
 * Attempts to create a new thread an to connect to the server.
 * Runs the main method of RunGame class, which instantiates a new game to be played.
 */

public class HumanPlayer implements Runnable {
	/*
	 * Constructor. 
	 * 
	 * @param hostName
	 * 		The name of the host.
	 * @param portNumber
	 * 		The port number of the server.
	 */
	public HumanPlayer(String hostName, int portNumber) {
		try {
			Socket socket = new Socket(hostName, portNumber);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		while(true){
			try {
				// Runs the game.
				RunGame.main();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}	
	}
}
