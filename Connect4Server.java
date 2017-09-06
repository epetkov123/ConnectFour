import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/*
 * Creates a new server socket and waits for clients to connect.
 */
public class Connect4Server implements Runnable {
	private ServerSocket serverSocket;
	private int counter;
	/*
	 * Constructor.
	 * @param portNumber
	 * 		Port number of server socket.
	 */
	public Connect4Server(int portNumber) throws IOException {
		serverSocket = new ServerSocket(portNumber);
		counter = 1;
	}
	
	public static void main(String[] args) {
		if (args.length != 1) {
            System.err.println("Usage: java Connect4Server <port number>");
            System.exit(1);
        }
		int portNumber = 0;
		try {
			portNumber = Integer.parseInt(args[0]);
	        new Thread(new Connect4Server(portNumber)).start();
		}
		catch(NumberFormatException e) {
			System.err.println("Usage: java Connect4Server <port number>");
		}
		catch (IOException e) {
			System.err.println("Could not listen on port " + portNumber);
			System.exit(-1);
		}
	}
	
	public void run() {  
		System.out.println("Server : Listening for Clients");
		while (true) {
			try {
				Socket clientSocket = serverSocket.accept();
			  	System.out.println("Server : Client Accepted");
				new PlayerThread(clientSocket, getNewID()).start();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private int getNewID() {
		int id = counter;
		counter++;
		return id;
	}
}
