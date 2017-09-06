README 
=======================================================
Connect4 Game
=======================================================
In order to play the game, there are at least 
two classes to run - one for establishing 
server connection and one for every next client.
=======================================================
COMPILING:

javac Connect4Server.java
javac Connect4.java 
=======================================================
RUNNING:

java Connect4Server 4004 - argument for port
java Connect4 localhost 4004 - arguments for hostname and port
=======================================================
RULES:

1. The game is played on a rectangular grid (6x7), which serves
as the game board.

2. There are three symbols in the game:
	1. 'Y' - yellow disk, which indicates where the player
	makes his turn.
	2. 'R' - red disk, which indicates where the computer
	makes his turn.
	3. '.' - a dot, representing an empty space on the 
	game board.

3. The player and the computer take turns place a disk
on the board.

4. On the player's turn, he/she can type either a number
from 1 to 7 to place a disk or QUIT to exit game.

5. To win, one must have a line of 4 of his own disks (could be horizontal, vertical or diagonal).

5. When one of the players have connected 4 disks, a message is displayed,
stating the player that has won.

6. If there are no more empty spaces on board, a message is displayed,
stating that the have is a draw.

=======================================================
CODE:

Connect4Server class - creates a new server socket, listens for clients and 
when one is accepted a new thread is created (PlayerThread class).
Connect4 class is tries to connect to server and starts a new thread
with HumanPlayer class, which runs the RunGame main method, where a new 
instance of the GameLogic class is created, which passes the width and
height of the game board to the constructor of GameLogic.
Then the playGame method is invoked which is a while loop and the game is played 
until the user exits, one of the player wins or there is a draw.
The way the computer is playing is by getting a random number (from 1 to 7) 
and checks if the column is free, if not a new random number is picked etc.
Program can handle incorrect input - example invalid column or non-integer.