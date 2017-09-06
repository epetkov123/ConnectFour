import java.net.Socket;
/*
 * Each time a new client has successfully connected, a new player thread is created.
 */
public class PlayerThread extends Thread{
	private int id;
	/*
	 * Constructor.
	 * @param socket
	 * 		Socket object of a player.
	 * @param id
	 * 		ID number of thread.
	 */
	public PlayerThread(Socket socket, int id) {
		this.id = id;
		System.out.println("Player Thread : New Player Thread Created");
	}

	public void run() {
		try {
			System.out.println("Player Thread " + id + " Running");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
