/*
 * Connects a client to server and starts a new thread.
 */
public class Connect4 {
	public static void main(String[] args) {
		if (args.length != 2) {
            System.err.println("Usage: java Connect4 <host name> <port number>");
            System.exit(1);
        }
		// Gets the arguments from command line and converts them to a string and an integer respectively.
		String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        // Starts a new thread with the host name and port number of the client that wants to connect to the server.
        new Thread(new HumanPlayer(hostName, portNumber)).start();
	}
}
