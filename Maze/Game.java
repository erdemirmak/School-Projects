import enigma.console.TextAttributes;
import enigma.core.Enigma;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class Game {
	//  												        name,width,lenght,fontsize
	public static enigma.console.Console cn = Enigma.getConsole("NUMBER MAZE",70,24,25);
	public KeyListener klis;

	public static TextAttributes [] colors = new TextAttributes[6];
	public static boolean[][] wallArray = new boolean[23][55];
	public static Number [][] numberArray = new Number[22][54];
	//Row counter for additions to the array
	public static int rowCounter = 0;
	public static Stack leftBackpack = new Stack(8);
	public static Stack rightBackpack = new Stack(8);
	//If this variable is false the game is over
	public static boolean isGameOver = true;
	public static Number humanNumber;
	//This stacks for scoring
	public static Stack temp1 = new Stack(8);
    public static Stack temp2 = new Stack(8);
    public static int score=0;
    //This variable to prevent the movement of humanNumber when it turns into number 1
    public static long startTime2;
	// ------ Standard variables for keyboard ------
	public int keypr; // key pressed?
	public int rkey; // key (for press/release)
	// ----------------------------------------------------
	// --- Contructor
	Game() throws Exception { 
		// ------ Standard code for keyboard ------
		klis = new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
				if (keypr == 0) {
					keypr = 1;
					rkey = e.getKeyCode();
				}
			}
			public void keyReleased(KeyEvent e) {
			}
		};
		cn.getTextWindow().addKeyListener(klis);
      // ----------------------------------------------------
		colors[0] = new TextAttributes(Color.black,Color.green);
		colors[1] = new TextAttributes(Color.black,Color.yellow);
		colors[2] = new TextAttributes(Color.black,Color.red);
		colors[3] = new TextAttributes(Color.black,Color.cyan);
		colors[4] = new TextAttributes(Color.black,Color.lightGray);
		colors[5] = new TextAttributes(Color.lightGray,Color.black);
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Maze.txt")));
		String line;
		while ((line = br.readLine()) != null) {
			AddWallArrayAndPrint(line);
		}br.close();
		int[] place = RandomPlace();
		humanNumber = new Number(5, place[0], place[1]);
		humanNumber.setColor(3);
		numberArray[humanNumber.getPositiony()][humanNumber.getPositionx()] = humanNumber;
		boolean flag = true;
		boolean flag2 = true;
		long startTime = System.nanoTime();
		CircularQueue input = new CircularQueue(10);
		//Adds 25 numbers to the maze
		for (int i = 0; i < 24; i++) {
			PlaceNumber(RandomNumber());;
			//Adds input queue 10 numbers
			if (i < 10) {
				input.Enqueue(RandomNumber());
			}
		}PrintQueue(input);
		cn.getTextWindow().output(place[0], place[1], (char)(humanNumber.getValue()+'0'), colors[humanNumber.getColor()]);
		while (isGameOver) {
			humanNumber();
			long endTime = System.nanoTime();
			int tempTotalTime = (int) ((endTime - startTime) / 500000000);
			// if keyboard button pressed
			if (keypr == 1) { 
				if (rkey == KeyEvent.VK_LEFT && IsAvailable(humanNumber.getPositionx() - 1, humanNumber.getPositiony())) {
					MoveTheNumber(humanNumber, 1);
				}else if (rkey == KeyEvent.VK_RIGHT && IsAvailable(humanNumber.getPositionx() + 1, humanNumber.getPositiony())) {
					MoveTheNumber(humanNumber, 0);
				}else if (rkey == KeyEvent.VK_UP && IsAvailable(humanNumber.getPositionx(), humanNumber.getPositiony() -1)) {
					MoveTheNumber(humanNumber, 2);
				}else if (rkey == KeyEvent.VK_DOWN && IsAvailable(humanNumber.getPositionx(), humanNumber.getPositiony() +1)) {
					MoveTheNumber(humanNumber, 3);
				}else if (rkey == KeyEvent.VK_Q) {
					TransferTheNumber(false);
				}else if (rkey == KeyEvent.VK_W) {
					TransferTheNumber(true);
				}DeleteScore();
				Score();
				PrintStacks();
				CheckingPaths();
				AddMovementToPaths(rkey);
				PathFinding();
				ClearMaze();
				PrintPaths();
				// last action
				keypr = 0; 
			}
			Thread.sleep(20);
			endTime = System.nanoTime();
			int totalTime = (int) ((endTime - startTime) / 500000000);
			//if 1 unit of time passes
			if (tempTotalTime < totalTime) { 
				IsGameOver();
				RandomMovement();
				CheckingPaths();
				PathFinding();
				ClearMaze();
				PrintPaths();
				MovementOfReds();
				cn.getTextWindow().setCursorPosition(64, 22);
				cn.getTextWindow().output(Integer.toString(totalTime));
			}//if 5 seconds have passed
			if (totalTime % 5 == 0 && flag) {
				PlaceNumber((int) input.Dequeue());
				input.Enqueue(RandomNumber());
				PrintQueue(input);
				flag = false;
			} else if (totalTime % 5 != 0) {
				flag = true;
			}long endTime2 = System.nanoTime();
			int totalTime2 = (int) ((endTime2 - startTime2) / 500000000);
			//if 4 seconds have passed since the humanNumber was 1
			if (totalTime2 == 4 && flag2) {
				if (humanNumber.getValue() == 1) {
					humanNumber.setValue(humanNumber.getValue() + 1);
					cn.getTextWindow().output(humanNumber.getPositionx(), humanNumber.getPositiony(),(char) (humanNumber.getValue() + '0'), colors[humanNumber.getColor()]);
					flag2 = false;
				}
			} else if (totalTime2 % 4 != 0) {
				flag2 = true;
			}
		}cn.getTextWindow().setCursorPosition(56, 4);
		cn.getTextWindow().output("!!GAME OVER!!", colors[2]);
	}
	//this function prints the incoming line and adds it to the wall array
	public void AddWallArrayAndPrint(String line) {
		for (int i = 0; i < wallArray[0].length+13; i++) {
			if (i >= wallArray[0].length) {
				cn.getTextWindow().output(line.charAt(i));
				continue;
			}if(line.charAt(i) == '#') {
				wallArray[rowCounter][i] = false;
				cn.getTextWindow().output(line.charAt(i),colors[5]);
			}else {
				wallArray[rowCounter][i] = true;
				cn.getTextWindow().output(line.charAt(i),colors[4]);
			}
		}rowCounter++;
		System.out.println();
	}
	//this function determines whether there is a wall at the desired point
	public boolean IsAvailable(int cx, int cy) {
		return wallArray[cy][cx];
	}
	//this function adds the incoming number to the maze
	public void PlaceNumber(int number) {
		int[] place = RandomPlace();
		Number num = new Number(number, place[0], place[1]);
		numberArray[place[1]][place[0]] = num;
		cn.getTextWindow().setCursorPosition(place[0], place[1]);
		cn.getTextWindow().output((char) (num.getValue() + '0'), colors[num.getColor()]);
	}
	//this function randomly selects an empty point in the maze
	public int[] RandomPlace() {
		Random rand = new Random();
		int [] place = new int [2];
		while (true) {
			int randx = rand.nextInt(55);
			int randy = rand.nextInt(23);
			if (wallArray[randy][randx] && numberArray[randy][randx] == null) {
				place[0]=randx;
				place[1]=randy;
				return place;
			}
		}
	}
	//this function generates random numbers, taking into account the rate at which numbers occur
	public int RandomNumber() {
		Random rand = new Random();
		int probability = rand.nextInt(20);
		int number;
		if (probability == 19) {
			number = rand.nextInt(3) + 7;
		}else if (probability > 14) {
			number = rand.nextInt(3) + 4;
		}else {
			number = rand.nextInt(3) + 1;
		}return number;
	}
	//this function prints the input queue
	public void PrintQueue(CircularQueue queue) {
		cn.getTextWindow().setCursorPosition(57, 2);
		for (int i = 0; i < queue.Size(); i++) {
			System.out.print(queue.Peek());
			queue.Enqueue(queue.Dequeue());
		}
	}
	//this function prints backpacks
	public void PrintStacks() {
		int leftSize = leftBackpack.Size();
		int rightSize = rightBackpack.Size();
		Stack tempStackLeft = new Stack(leftSize);
		Stack tempStackRight = new Stack(rightSize);
		for (int i = 0; i < 8; i++) {
			cn.getTextWindow().output(59,7+i,' ');
			cn.getTextWindow().output(65,7+i,' ');
		}for (int i = 0; i < leftSize; i++) {
			cn.getTextWindow().output(59,15+i-leftSize,(char)((int)leftBackpack.Peek()+'0'));
			tempStackLeft.Push(leftBackpack.Pop());
		}for (int i = 0; i < rightSize; i++) {
			cn.getTextWindow().output(65,15+i-rightSize,(char)((int)rightBackpack.Peek()+'0'));
			tempStackRight.Push(rightBackpack.Pop());
		}while (!tempStackRight.isEmpty()) {
			rightBackpack.Push(tempStackRight.Pop());
		}while (!tempStackLeft.isEmpty()) {
			leftBackpack.Push(tempStackLeft.Pop());
		}
	}
	//this function moves the number in the given direction
	public void MoveTheNumber(Number number, int whichDirection) {//0: right  1: left  2: up  3: down
		if (humanNumber.getValue()==1) {
			return;
		}
		numberArray[number.getPositiony()][number.getPositionx()] = null;
		cn.getTextWindow().output(number.getPositionx(),number.getPositiony(),' ',colors[4]);
		switch (whichDirection) {
		case 0:
			number.setPositionx(number.getPositionx()+1);
			break;
		case 1:
			number.setPositionx(number.getPositionx()-1);
			break;
		case 2:
			number.setPositiony(number.getPositiony()-1);
			break;
		case 3:
			number.setPositiony(number.getPositiony()+1);
			break;
		}
		if (numberArray[number.getPositiony()][number.getPositionx()] == null) {
			numberArray[number.getPositiony()][number.getPositionx()] = number;
		}else {
			if (numberArray[number.getPositiony()][number.getPositionx()].getValue() > number.getValue()) {
				isGameOver = false;
			}else {
				if (!leftBackpack.isFull()) {
					leftBackpack.Push(numberArray[number.getPositiony()][number.getPositionx()].getValue());
				}else {
					leftBackpack.Pop();
					leftBackpack.Push(numberArray[number.getPositiony()][number.getPositionx()].getValue());
				}numberArray[number.getPositiony()][number.getPositionx()] = number;
				PrintStacks();
			}
		}cn.getTextWindow().output(number.getPositionx(),number.getPositiony(),(char)(number.getValue()+'0'), colors[number.getColor()]);
	}
	//this function moves the number to the given coordinate
	public void MoveTheNumber(Number number, Coordinate coordinate) {
		if (numberArray[coordinate.getY()][coordinate.getX()] == null) {
			cn.getTextWindow().output(number.getPositionx(),number.getPositiony(),' ',colors[4]);
			numberArray[number.getPositiony()][number.getPositionx()] = null;
			numberArray[coordinate.getY()][coordinate.getX()] = number;
			number.setPositionx(coordinate.getX());
			number.setPositiony(coordinate.getY());
			cn.getTextWindow().output(number.getPositionx(),number.getPositiony(),(char)(number.getValue()+'0'), colors[number.getColor()]);
		}
		
	}
	//this function allows the transfer of numbers from a backpack to other backpack
	public void TransferTheNumber(boolean flag) {// true: from left to right / false: from right to left
		if (flag && !rightBackpack.isFull() && !leftBackpack.isEmpty()) {
			rightBackpack.Push(leftBackpack.Pop());
		}else if (!flag && !leftBackpack.isFull() && !rightBackpack.isEmpty()) {
			leftBackpack.Push(rightBackpack.Pop());
		}PrintStacks();
	}
	//this function enables random movement of yellow numbers
	public void RandomMovement() {
		for (Number[] numbers : numberArray) {
			for (Number number : numbers) {
				if (number != null && number.getColor() == 1) {
					boolean [] canGo = CanGo(number);
					if (number.getRandomDirection() != -1 && canGo[number.getRandomDirection()] && !number.isMoved()) {
						number.setMoved(true);
						MoveTheNumber(number, number.getRandomDirection());
					}else if (CanMove(canGo) && !number.isMoved()) {
						number.setMoved(true);
						int randGo = RandomGo(canGo);
						number.setRandomDirection(randGo);
						MoveTheNumber(number, randGo);
					}
				}
			}
		}for (Number[] numbers : numberArray) {
			for (Number number : numbers) {
				if (number != null) {
					number.setMoved(false);
				}
			}
		}
	}
	//this function returns the directions in which the incoming number can and cannot go
	public boolean[] CanGo(Number number) {//0: right  1: left  2: up  3: down
		boolean[] canGo = new boolean[4];
		if (IsAvailable(number.getPositionx() + 1, number.getPositiony()) && numberArray[number.getPositiony()][ number.getPositionx() + 1] == null) {
			canGo[0] = true;
		}else {
			canGo[0] = false;
		}if (IsAvailable(number.getPositionx() - 1, number.getPositiony()) && numberArray[number.getPositiony()][ number.getPositionx() - 1] == null) {
			canGo[1] = true;
		}else {
			canGo[1] = false;
		}if (IsAvailable(number.getPositionx(), number.getPositiony() - 1) && numberArray[number.getPositiony() - 1][ number.getPositionx()] == null) {
			canGo[2] = true;
		}else {
			canGo[2] = false;
		}if (IsAvailable(number.getPositionx(), number.getPositiony() + 1) && numberArray[number.getPositiony() + 1][ number.getPositionx()] == null) {
			canGo[3] = true;
		}else {
			canGo[3] = false;
		}return canGo;
	}
	//this function returns whether the number can move or not by looking at the directions the number can go
	public boolean CanMove(boolean[] canGo) {
		for (boolean b : canGo) {
			if (b) {
				return true;
			}
		}return false;
	}
	//this function moves the number randomly, looking at where it can go
	public int RandomGo(boolean[] canGo) {
		Random rand = new Random();
		while (true) {
			int randomGo = rand.nextInt(canGo.length);
			if(canGo[randomGo]) {
				return randomGo;
			}
		}
	}
	//This function converts to 1 if the humanNumber is 9
	public void humanNumber() {
		if (humanNumber.getValue() > 9) {
			humanNumber.setValue(1);
			startTime2 = System.nanoTime();
		}
	}
	//this function does the scoring operations
	public void Score() {
		while(!leftBackpack.isEmpty()) {
			temp1.Push(leftBackpack.Pop());
		}while(!rightBackpack.isEmpty()) {
			temp2.Push(rightBackpack.Pop());
		}int size=0;
		if (temp1.Size() > temp2.Size()) {
			size = temp2.Size();
		} else if (temp2.Size() > temp1.Size()) {
			size = temp1.Size();
		} else {
			size = temp1.Size();
		}for (int i=0;i<size;i++) {
			if (temp1.Peek().equals(temp2.Peek())) {
				if((int)temp1.Peek()==1||(int)temp1.Peek()==2||(int)temp1.Peek()==3) {
					score=score+(int)temp1.Peek()*1;
					cn.getTextWindow().setCursorPosition(57, 18);
					System.out.println(temp1.Peek() + " * 1 = " + temp1.Peek());
				}if((int)temp1.Peek()==4||(int)temp1.Peek()==5||(int)temp1.Peek()==6) {
					score=score+(int)temp1.Peek()*5;
					cn.getTextWindow().setCursorPosition(57, 18);
					System.out.println(temp1.Peek() + " * 5 = " + (int)temp1.Peek()*5);
				}if((int)temp1.Peek()==7||(int)temp1.Peek()==8||(int)temp1.Peek()==9) {
					score=score+(int)temp1.Peek()*25;
					cn.getTextWindow().setCursorPosition(57, 18);
					System.out.println(temp1.Peek() + " * 25 = " + (int)temp1.Peek()*25);
				}temp1.Pop();
				temp2.Pop();
				humanNumber.setValue(humanNumber.getValue()+1);
			}else {
				leftBackpack.Push(temp1.Pop());
				rightBackpack.Push(temp2.Pop());
			}
		}while(!temp1.isEmpty()) {
			leftBackpack.Push(temp1.Pop());
		}while(!temp2.isEmpty()) {
			rightBackpack.Push(temp2.Pop());
		}cn.getTextWindow().setCursorPosition(65,20);
		cn.getTextWindow().output(String.valueOf(score));
		humanNumber();
		cn.getTextWindow().output(humanNumber.getPositionx(),humanNumber.getPositiony(),(char)(humanNumber.getValue()+'0'), colors[humanNumber.getColor()]);
	}
	//this function deletes score calculations from the screen
	public void DeleteScore() {
		cn.getTextWindow().setCursorPosition(57, 18);
		System.out.println("             ");
	}
	//This function creates a path for red numbers to the humanNumber.
	public void PathFinding() {
	    Number imaginaryNumber;
		for (Number[] numbers : numberArray) {
			for (Number number : numbers) {
				if (number != null && number.getColor() == 2 && number.getPath() == null) {
					Stack pathFinding = new Stack(500);
					Coordinate[][] coordinates = new Coordinate[23][55];
					coordinates[number.getPositiony()][number.getPositionx()] = new Coordinate(number.getPositionx(), number.getPositiony(), null);
					pathFinding.Push(coordinates[number.getPositiony()][number.getPositionx()]);
					while (true) {
						Coordinate coordinate = (Coordinate) pathFinding.Pop();
						if (Reach(coordinate)) {
							Coordinate previousCoordinate = coordinate.getPrevious();
							if (previousCoordinate != null) {
								number.AddCoordinateToPath(coordinate);
								while(previousCoordinate.getPrevious() != null) {
									number.AddCoordinateToPath(previousCoordinate);
									previousCoordinate = previousCoordinate.getPrevious();
								}
							}break;
						}
						imaginaryNumber = new Number(0, coordinate.getX(), coordinate.getY());
						boolean[] canGo = CanGo(imaginaryNumber);
						if (CanMove(canGo)) {
							switch (ShouldGo(imaginaryNumber)) {
							case 0:
								if (canGo[3] && coordinates[imaginaryNumber.getPositiony()+1][imaginaryNumber.getPositionx()] == null) {
									coordinates[imaginaryNumber.getPositiony()+1][imaginaryNumber.getPositionx()] = new Coordinate(imaginaryNumber.getPositionx(), imaginaryNumber.getPositiony()+1, coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()]);
									pathFinding.Push(coordinates[imaginaryNumber.getPositiony()+1][imaginaryNumber.getPositionx()]);
								}
								if (canGo[1] && coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()-1] == null) {
									coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()-1] = new Coordinate(imaginaryNumber.getPositionx()-1, imaginaryNumber.getPositiony(), coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()]);
									pathFinding.Push(coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()-1]);
								}
								if (canGo[0] && coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()+1] == null) {
									coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()+1] = new Coordinate(imaginaryNumber.getPositionx()+1, imaginaryNumber.getPositiony(), coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()]);
									pathFinding.Push(coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()+1]);
								}
								if (canGo[2] && coordinates[imaginaryNumber.getPositiony()-1][imaginaryNumber.getPositionx()] == null) {
									coordinates[imaginaryNumber.getPositiony()-1][imaginaryNumber.getPositionx()] = new Coordinate(imaginaryNumber.getPositionx(), imaginaryNumber.getPositiony()-1, coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()]);
									pathFinding.Push(coordinates[imaginaryNumber.getPositiony()-1][imaginaryNumber.getPositionx()]);
								}
								break;
							case 1:
								if (canGo[2] && coordinates[imaginaryNumber.getPositiony()-1][imaginaryNumber.getPositionx()] == null) {
									coordinates[imaginaryNumber.getPositiony()-1][imaginaryNumber.getPositionx()] = new Coordinate(imaginaryNumber.getPositionx(), imaginaryNumber.getPositiony()-1, coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()]);
									pathFinding.Push(coordinates[imaginaryNumber.getPositiony()-1][imaginaryNumber.getPositionx()]);
								}
								if (canGo[1] && coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()-1] == null) {
									coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()-1] = new Coordinate(imaginaryNumber.getPositionx()-1, imaginaryNumber.getPositiony(), coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()]);
									pathFinding.Push(coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()-1]);
								}
								if (canGo[3] && coordinates[imaginaryNumber.getPositiony()+1][imaginaryNumber.getPositionx()] == null) {
									coordinates[imaginaryNumber.getPositiony()+1][imaginaryNumber.getPositionx()] = new Coordinate(imaginaryNumber.getPositionx(), imaginaryNumber.getPositiony()+1, coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()]);
									pathFinding.Push(coordinates[imaginaryNumber.getPositiony()+1][imaginaryNumber.getPositionx()]);
								}
								if (canGo[0] && coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()+1] == null) {
									coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()+1] = new Coordinate(imaginaryNumber.getPositionx()+1, imaginaryNumber.getPositiony(), coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()]);
									pathFinding.Push(coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()+1]);
								}
								break;
							case 2:
								if (canGo[2] && coordinates[imaginaryNumber.getPositiony()-1][imaginaryNumber.getPositionx()] == null) {
									coordinates[imaginaryNumber.getPositiony()-1][imaginaryNumber.getPositionx()] = new Coordinate(imaginaryNumber.getPositionx(), imaginaryNumber.getPositiony()-1, coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()]);
									pathFinding.Push(coordinates[imaginaryNumber.getPositiony()-1][imaginaryNumber.getPositionx()]);
								}
								if (canGo[0] && coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()+1] == null) {
									coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()+1] = new Coordinate(imaginaryNumber.getPositionx()+1, imaginaryNumber.getPositiony(), coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()]);
									pathFinding.Push(coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()+1]);
								}
								if (canGo[1] && coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()-1] == null) {
									coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()-1] = new Coordinate(imaginaryNumber.getPositionx()-1, imaginaryNumber.getPositiony(), coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()]);
									pathFinding.Push(coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()-1]);
								}
								if (canGo[3] && coordinates[imaginaryNumber.getPositiony()+1][imaginaryNumber.getPositionx()] == null) {
									coordinates[imaginaryNumber.getPositiony()+1][imaginaryNumber.getPositionx()] = new Coordinate(imaginaryNumber.getPositionx(), imaginaryNumber.getPositiony()+1, coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()]);
									pathFinding.Push(coordinates[imaginaryNumber.getPositiony()+1][imaginaryNumber.getPositionx()]);
								}
								break;
							case 3:
								if (canGo[3] && coordinates[imaginaryNumber.getPositiony()+1][imaginaryNumber.getPositionx()] == null) {
									coordinates[imaginaryNumber.getPositiony()+1][imaginaryNumber.getPositionx()] = new Coordinate(imaginaryNumber.getPositionx(), imaginaryNumber.getPositiony()+1, coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()]);
									pathFinding.Push(coordinates[imaginaryNumber.getPositiony()+1][imaginaryNumber.getPositionx()]);
								}
								if (canGo[0] && coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()+1] == null) {
									coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()+1] = new Coordinate(imaginaryNumber.getPositionx()+1, imaginaryNumber.getPositiony(), coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()]);
									pathFinding.Push(coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()+1]);
								}
								if (canGo[2] && coordinates[imaginaryNumber.getPositiony()-1][imaginaryNumber.getPositionx()] == null) {
									coordinates[imaginaryNumber.getPositiony()-1][imaginaryNumber.getPositionx()] = new Coordinate(imaginaryNumber.getPositionx(), imaginaryNumber.getPositiony()-1, coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()]);
									pathFinding.Push(coordinates[imaginaryNumber.getPositiony()-1][imaginaryNumber.getPositionx()]);
								}
								if (canGo[1] && coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()-1] == null) {
									coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()-1] = new Coordinate(imaginaryNumber.getPositionx()-1, imaginaryNumber.getPositiony(), coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()]);
									pathFinding.Push(coordinates[imaginaryNumber.getPositiony()][imaginaryNumber.getPositionx()-1]);
								}
								break;
							}
						}
						if (pathFinding.Peek()==null) {
							break;
						}
					}
				}
			}
		}
	}
	//this function returns the direction in which the incoming number should go
	public int ShouldGo(Number number) {//0:NorthEast 1:SouthEast 2:SouthWest 3:NorthWest
		if (number.getPositionx() <= humanNumber.getPositionx() && number.getPositiony() > humanNumber.getPositiony()) {
			return 0;
		}else if (number.getPositionx() < humanNumber.getPositionx() && number.getPositiony() <= humanNumber.getPositiony()) {
			return 1;
		}else if (number.getPositionx() >= humanNumber.getPositionx() && number.getPositiony() < humanNumber.getPositiony()) {
			return 2;
		}else {
			return 3;
		}
	}
	//This function returns whether the blue number can be reached in one step from the entered coordinate
	public boolean Reach(Coordinate coordinate) {
		if(humanNumber.getPositionx() == coordinate.getX() && (humanNumber.getPositiony()+1 == coordinate.getY() || humanNumber.getPositiony()-1 == coordinate.getY())) {
			return true;
		}else if (humanNumber.getPositiony() == coordinate.getY() && (humanNumber.getPositionx()+1 == coordinate.getX() || humanNumber.getPositionx()-1 == coordinate.getX())) {
			return true;
		}else {
			return false;
		}
	}
	//this function prints the path of red numbers
	public void PrintPaths() {
		for (Number[] numbers : numberArray) {
			for (Number number : numbers) {
				if (number != null && number.getColor() == 2 && number.getPath() != null) {
					for (int i = 0; i < number.getPath().length; i++) {
						Coordinate coordinate = number.getPath()[i];
						cn.getTextWindow().output(coordinate.getX(), coordinate.getY(),'.',colors[4]);
					}
				}
			}
		}
	}
	//this function deletes old paths written in the maze
	public void ClearMaze() {
		for (int i = 0; i < numberArray.length; i++) {
			for (int j = 0; j < numberArray[i].length; j++) {
				if(numberArray[i][j]==null && wallArray[i][j]) {
					cn.getTextWindow().output(j,i,' ',colors[4]);
				}
			}
		}
	}
	//this function determines if the path of red numbers is still available
	public void CheckingPaths() {
		for (Number[] numbers : numberArray) {
			for (Number number : numbers) {
				if (number != null && number.getColor() == 2 && number.getPath() != null) {
					number.CheckPath();
					for (Coordinate coordinate : number.getPath()) {
						if (numberArray[coordinate.getY()][coordinate.getX()] != null) {
							number.setPath(null);
							break;
						}
					}
				}
			}
		}
	}
	//this function makes the red numbers take a step on their path
	public void MovementOfReds() {
		for (Number[] numbers : numberArray) {
			for (Number number : numbers) {
				if (number != null && number.getColor() == 2 && number.getPath() != null && !number.isMoved()) {
					number.setMoved(true);
					MoveTheNumber(number, number.RemoveCoordinateToPath());
				}
			}
		}
	}
	//this function adds the movement of the humanNumber to the path of the red number
	public void AddMovementToPaths(int direction) {// 37:left 38:up 39:right 40:down
		for (Number[] numbers : numberArray) {
			for (Number number : numbers) {
				if (number != null && number.getColor() == 2 && number.getPath() != null) {
					if (!Reach(number.getPath()[0])) {
						if (direction == 37) {
							number.AddCoordinateToFirstIndex(new Coordinate(humanNumber.getPositionx()+1, humanNumber.getPositiony(), number.getPath()[0]));
						}else if (direction == 38) {
							number.AddCoordinateToFirstIndex(new Coordinate(humanNumber.getPositionx(), humanNumber.getPositiony()+1, number.getPath()[0]));
						}else if (direction == 39) {
							number.AddCoordinateToFirstIndex(new Coordinate(humanNumber.getPositionx()-1, humanNumber.getPositiony(), number.getPath()[0]));
						}else if (direction == 40) {
							number.AddCoordinateToFirstIndex(new Coordinate(humanNumber.getPositionx(), humanNumber.getPositiony()-1, number.getPath()[0]));
						}
					}
				}
			}
		}
	}
	//this function determines if the game is over
	public void IsGameOver() {
		for (Number[] numbers : numberArray) {
			for (Number number : numbers) {
				if (number != null && number.getColor() == 2 && Reach(new Coordinate(number.getPositionx(), number.getPositiony(), null)) && number.getValue()>humanNumber.getValue()) {
					isGameOver = false;
					return;
				}
			}
		}
	}
}

