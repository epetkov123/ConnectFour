/*
 * Sets the values to height and width of game board, then creates a new game.
 */
public class RunGame {
	private static int height;
	private static int width;
	
    public static void main() {
    	height = 6;
    	width = 7;
    	// Creates a new instance of the game, passing arguments height and width to constructor and playGame method is invoked.
    	System.out.println("Connect4 Game Running:");
        GameLogic game = new GameLogic(height, width);
        game.playGame();
    }
}
