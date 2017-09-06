import java.util.*;
/*
 * Contains the logic for the game and plays the game 
 * by switching players and getting input from computer.
 */
public class GameLogic {
    private char[][] board;
    private static final char[] PLAYERS = {'Y', 'R'};
    private final char EMPTY_SLOT = '.';
    private final int HEIGHT;
    private final int WIDTH;
    private int moves;
    /*
     * Constructor.
	 * @param height
	 * 		The height of the game board.
	 * @param width
	 * 		The width of the game board.
     */
    public GameLogic(int height, int width) {
        this.HEIGHT = height;
        this.WIDTH = width;
        // Initializing moves and board.
        moves = HEIGHT * WIDTH;
        board = new char[HEIGHT][];
        // Filling the 2d char array, which serves as the game board, with dot symbols(empty slots).
        for(int i = 0; i < HEIGHT; i++) {
            Arrays.fill(board[i] = new char[WIDTH], EMPTY_SLOT);
        }
    }
    /*
     * Prints the game board on the screen.
     */
    private void printBoard() {
        for(int i = 0; i < HEIGHT; i++)
        {
            for(int j = 0; j < WIDTH; j++)
            {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.println("============================");
    }
    /* 
     * @param column
     * 		A column from the game board, which is going to be checked for empty slots.
     * @return 
     * 		A boolean indicating if the column passed as an argument has empty slots.
     */
    public boolean checkColumn(int column) {
        for (int i = HEIGHT - 1; i >= 0; i--)
        {
            if (board[i][column] == EMPTY_SLOT)
            {
                return true;
            }
        }
        return  false;
    }
    /*
     * Makes a move by placing the corresponding char ('Y' or 'R' - yellow or red disk) in the selected column.
     * 
     * @param column
     * 		The column in which the disk is going to be placed.
     * @param player
     * 		A boolean representing if it is the player or the computer.
     */
    public void makeMove(int column, boolean player) {
        for (int i = HEIGHT - 1; i >= 0; i--)
        {
            if (board[i][column] == EMPTY_SLOT)
            {
                if(player)
                {
                    board[i][column] = PLAYERS[0];
                    return;
                }
                else
                {
                    board[i][column] = PLAYERS[1];
                    return;
                }
            }
        }
    }
    /*
     * Checks if a player has connected 4 disks on the board.
     * @param board
     * 		A 2d char array, representing the game board
     */
    public boolean checkWin(char[][] board){
    	/*
    	 *  By having two for loops, we are going through every slot on the game board
    	 *  and check if it has 3 of the same chars next to it (horizontally, vertically and diagonally)
    	 */
        for (int i = 0; i < HEIGHT; i++)
        {
            for (int c = 0; c < WIDTH; c++)
            {
                char currentToken = board[i][c];
                // If the current token is an empty slot (a dot) skip the rest and continue with the loop.
                if (currentToken == EMPTY_SLOT){
                    continue;
                }
                if (c + 3 < WIDTH &&
                        currentToken == board[i][c+1] &&
                        currentToken == board[i][c+2] &&
                        currentToken == board[i][c+3])
                {
                    return true;
                }
                if (i + 3 < HEIGHT)
                {
                    if (currentToken == board[i+1][c] &&
                            currentToken == board[i+2][c] &&
                            currentToken == board[i+3][c])
                    {
                        return true;
                    }
                    if (c + 3 < WIDTH &&
                            currentToken == board[i+1][c+1] &&
                            currentToken == board[i+2][c+2] &&
                            currentToken == board[i+3][c+3])
                    {
                        return true;
                    }
                    if (c - 3 >= 0 &&
                            currentToken == board[i+1][c-1] &&
                            currentToken == board[i+2][c-2] &&
                            currentToken == board[i+3][c-3])
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    /*
     * The method in which the game is played.
     */
    public void playGame(){
    	Random rand = new Random();
        Scanner input = new Scanner(System.in);
        // A boolean to represent which player's turn it is.
        boolean isPlayer = true;
        int column = 0;
        // A while loop for running the whole game process.
        while(true)
        {
            if(isPlayer)
            {
                System.out.println("Enter a number from 1 to " + WIDTH + " to pick a column OR type QUIT to exit game:");
                String line = input.nextLine();
                // Checks if the string is "QUIT" which terminates the program with a message.
                if(line.equalsIgnoreCase("QUIT"))
                {
                    System.out.println("Game quit.");
                    input.close();
                    System.exit(0);
                }
                // Parsing the input to an integer.
                try{
                	column = Integer.parseInt(line) - 1;
                }
                // Guarding against incorrect input.
                catch(Exception ex){
                	System.out.println("Invalid input!");
                	continue;
                }
                // Prints a message if the integer is not within the given limit.
                if(column < 0 || column >= WIDTH)
                {
                    System.out.println("Invalid column!");
                    continue;
                }
                // Prints a message if column has no empty spaces.
                else if(!checkColumn(column))
                {
                    System.out.println("Column is full.");
                    continue;
                }
                else
                {
                	// Makes move for player and prints game board.
                    makeMove(column, isPlayer);
                    printBoard();
                    // Checks if the game is won.
                    if(checkWin(board))
                    {
                        System.out.println("Player has won.");
                        input.close();
                        System.exit(0);
                    }
                }
                // Switching boolean isPlayer to false, to switch turn.
                isPlayer = false;
            }
            else
            {
                // A while loop to get a random column number which has an empty slot.
                while(true)
                {
                	// Gets a random number within the given width limit.
                    column = rand.nextInt(WIDTH);
                    if(checkColumn(column))
                    {
                    	// Makes move for computer and prints game board.
                        makeMove(column, isPlayer);
                        printBoard();
                        // Checks if the game is won.
                        if(checkWin(board))
                        {
                            System.out.println("Computer has won.");
                            input.close();
                            System.exit(0);
                        }
                        System.out.println("Computer took turn.");
                        break;
                    }
                }
                // Switching boolean isPlayer to true, to switch turn.
                isPlayer = true;
            }
            // Decrements moves after a player has taken his turn.
            moves--;
            // Checks if there are no more moves left (empty spaces on board) and prints message to end game if so.
            if(moves == 0) 
            {
                System.out.println("Game has ended. Players have drawn.");
                input.close();
                System.exit(0);
            }
        }
    }
}
