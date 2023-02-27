package editor;

public class MLL
{
	static LineNode head;
	int LineNumber;
	
	static int cursorPositions[] = new int[50];
	static int LineNum, positionIndex, nextIndex = 49;
	
	MLL() {LineNumber = 0;}
	
	public void addLine() // done
	{
		LineNumber++;
		LineNode newLine;
		
		if (head == null)
		{
			newLine = new LineNode(LineNumber);
			head = newLine;
		}
		else
		{
			LineNode temporaryLine = head;
			
			while(temporaryLine.getDown() != null)
			{
				temporaryLine = temporaryLine.getDown();
			}

			newLine = new LineNode(LineNumber);
			temporaryLine.setDown(newLine);
		}
	}
	
	public void addCharacter(int lineNumber, char character) // done, only adds end of the line
	{
		LineNode temporaryLine = head;
		while (temporaryLine != null)
		{
			if(lineNumber == temporaryLine.getId())
			{
				CharacterNode temporaryCharacter = temporaryLine.getRight();
				if(temporaryCharacter == null)
				{
					temporaryCharacter = new CharacterNode(character);
					temporaryLine.setRight(temporaryCharacter);
				}
				else
				{
					while(temporaryCharacter.getNext() != null) temporaryCharacter = temporaryCharacter.getNext();
					CharacterNode newCharacter = new CharacterNode(character);
					temporaryCharacter.setNext(newCharacter);
				}
			}
			temporaryLine = temporaryLine.getDown();
		}
	}
	
	public void addCharacter_overwritemode(int row, int column, char character)
	{
		LineNode temporaryLine = head;
		CharacterNode newCharacter = new CharacterNode(character);
		
//		if(head.getRight() == null) head.setRight(new CharacterNode(character));
		
		while(temporaryLine.getId() < row) temporaryLine = temporaryLine.getDown();
		
		if(temporaryLine.getRight() == null)
		{
			temporaryLine.setRight(newCharacter);
		}
		
		if (searchByIndex(row, column) == null) searchByIndex(row, column - 1).setNext(new CharacterNode(character));
		else searchByIndex(row, column).setCharacter(character);
	}
	
	public void addCharacter_insertmode(int row, int column, char character)
	{
		LineNode temporaryLine = head;
		CharacterNode previousCharacter = null;
		CharacterNode nextCharacter = null;
		CharacterNode newCharacter = new CharacterNode(character);
		
		while(temporaryLine.getId() < row) temporaryLine = temporaryLine.getDown();
		
		if(temporaryLine.getRight() == null)
		{
			temporaryLine.setRight(newCharacter);
		}
		else
		{
			if(column > 0) 
			{
				previousCharacter = searchByIndex(row, column - 1);
				nextCharacter = searchByIndex(row, column);
			}
			else if(column == 0)
			{
				CharacterNode tempChar2 = temporaryLine.getRight();
				temporaryLine.setRight(newCharacter);
				temporaryLine.getRight().setNext(tempChar2);
			}
			
			if (nextCharacter != null)
			{
				previousCharacter.setNext(newCharacter);
				newCharacter.setNext(nextCharacter);
			}
			else if (previousCharacter != null) previousCharacter.setNext(newCharacter);
		}		
	}
	
	public int countLines() // done
	{
		int counter = 0;
		LineNode temporaryLine;
		
		if (head == null) return 0;
		else
		{
			temporaryLine = head;
			
			while (temporaryLine != null)
			{
				temporaryLine = temporaryLine.getDown();
				counter++;
			}
			return counter;
		}		
	}
	
	public int countCharacters(int lineNumber) // done
	{
		int counter = 0;
		LineNode temporaryLine;
		CharacterNode temporaryChar;
		
		if (countLines() < lineNumber) return -1; // incelenmek istenen satır numarası toplam satır sayısından büyükse
		else if (head == null) return 0;
		else
		{
			temporaryLine = head;
			temporaryChar = null;
			
			while(temporaryLine.getId() < lineNumber)
			{
				temporaryLine = temporaryLine.getDown();
			}
			
			temporaryChar = temporaryLine.getRight();
			
			while(temporaryChar != null)
			{
				temporaryChar = temporaryChar.getNext();
				counter++;
				if (temporaryChar != null && temporaryChar.getCharacter() == '\n') counter--; // '\n' doesn't count
			}
			return counter;
		}
	}
	
	public int countAllCharacters() // done
	{
		int counter = 0;
		
		LineNode temporaryLine;
		CharacterNode temporaryChar;
		
		if (head == null) return 0;
		else
		{
			temporaryLine = head;
			
			while (temporaryLine != null)
			{
				temporaryChar = temporaryLine.getRight();
				
				while(temporaryChar != null)
				{
					temporaryChar = temporaryChar.getNext();
					counter++;
				}
				
				temporaryLine = temporaryLine.getDown();
			}
			return counter;
		}
	}
	
	public static CharacterNode searchByIndex(int row, int column) // done
	{
		LineNode temporaryLine;
		CharacterNode temporaryChar;
		
		temporaryLine = head;
		
		while(temporaryLine.getId() < row)
		{
			temporaryLine = temporaryLine.getDown();
		}
		
		temporaryChar = temporaryLine.getRight();
		
		for (int i = 0; i < column; i++)
		{
			if (temporaryChar != null) temporaryChar = temporaryChar.getNext();
		}
		return temporaryChar;
	}
	
	public static String[] mllToString() 
	{
		String[] arr = new String[21];
		
		for (int i = 0; i < arr.length; i++) 
		{
			arr[i] = "";
		}
		
		int index = 1;
		LineNode temp = head;
		
		while (temp != null) 
		{
			CharacterNode temp2 = temp.getRight();
			
			while (temp2 != null) 
			{
				arr[index] = arr[index].concat(Character.toString(temp2.getCharacter()));
				temp2 = temp2.getNext();
			}
			
			temp = temp.getDown();
			index++;
		}
		return arr;
	}
	
	public static void find() 
	{
		String strArray[] = mllToString();
		int index = 0;
		
		for (int i = 0; i < strArray.length; i++) 
		{
			if (strArray[i].contains(Editor.selectedString)) 
			{
				// gidip highlight etsin
				while (index != -1) 
				{
					index = strArray[i].indexOf(Editor.selectedString, index);
					cursorPositions[positionIndex++] = index + 5; // bulunan kelimelerin cursor konumunu atıyorum ki
																	// nextte kullanabileyim
																	// doğru cursor pozisyonu index+1 konumunda
					if (index != -1) 
					{
						index++;
//						Editor.cn.setTextAttributes(Editor.att1);
						for (int j = 0; j < Editor.selectedString.length(); j++) 
						{
//							Editor.cnt.setCursorPosition(index + 4 + j, Editor.cursory);
//							System.out.print(searchByIndex(Editor.cursory - 3, index + 1 - 5 + j).getCharacter());
							searchByIndex(Editor.cursory - 3, index + 1 - 2 + j).setHasFound(true);
						}
//						Editor.cn.setTextAttributes(Editor.att0);
					}
				}
				break;
			}
		}
	}
	
	public void delete(int row, int column) {
		LineNode temporaryLine;
		CharacterNode temporaryCharacter;
		CharacterNode previousCharacter;
		CharacterNode temp2;

		if (head != null) {
			temporaryLine = head;
			temporaryCharacter = null;
			previousCharacter = null;
			temp2 = temporaryLine.getRight();
			// column 0 ise demekki en baştaki karakter oluyor
			if (column == 0) { // x=2 , previousChar = satırın en sağı olcak
				// previousCharacter = temporaryLine.getRight();

				while (temp2.getNext() != null) {
					previousCharacter = temp2;
					temp2 = temp2.getNext();
				}

				// temporaryCharacter = temporaryLine.getDown().getRight();

				while (temporaryLine.getId() < row) {
					temporaryLine = temporaryLine.getDown();
				}
				temporaryLine.setRight(null);
				return;
			}

			while (temporaryLine.getId() < row) {
				temporaryLine = temporaryLine.getDown();
			}

			temporaryCharacter = temporaryLine.getRight();

			if (temporaryCharacter != null) {

				for (int i = 0; i < column; i++) {
					previousCharacter = temporaryCharacter;
					temporaryCharacter = temporaryCharacter.getNext();
				}

			}
			if (previousCharacter != null)
				previousCharacter.setNext(temporaryCharacter.getNext());
		}
	}
	
	public static void next() 
	{
		/*
		 * eğer print i yorumdan çıkarırsan çalıştığını anlıyorsun her bulduğu yere
		 * yazdırarak ilerliyor bastıkça ama printi yoruma alınca çalıştığı belli
		 * olmuyor çünkü enigmaconsolda loop un en sonundaki setcursorposition cursorı
		 * en son yazılan yere getiriyor. next e bastıkça replace edilebilir. replace
		 * edilecek stringi tutarız f7 ye , burdan var olan karakteri silip replace
		 * edilecek karakteri addChar yaparız
		 */
		// if (cursorPositions[nextIndex] != 0) { // sıfırsa oraya eleman atılmamış yani

		// EnigmaConsole.cnt.setCursorPosition(cursorPositions[nextIndex],
		// EnigmaConsole.cursory);
		// // System.out.print("x");
		// Editor.mll.delete(EnigmaConsole.cursory - 1, cursorPositions[nextIndex] - 2);
		// EnigmaConsole.cnt.setCursorPosition(cursorPositions[nextIndex],
		// EnigmaConsole.cursory);
		// System.out.print(EnigmaConsole.stringToReplace);

		// for (int i = 0; i < EnigmaConsole.stringToReplace.length(); i++) {
		// Editor.mll.addChar(EnigmaConsole.initialLineNumber,
		// EnigmaConsole.stringToReplace.charAt(i));
		// }

		// nextIndex++;
		// } else
		// return;

		int temp = 0;
		for (int i = 0; i < cursorPositions.length; i++) 
		{ // indexleri küçükten
			// büyüğe sortluyor delete'in düzgün
			// çalışması için
			for (int j = i + 1; j < cursorPositions.length; j++) 
			{
				if (cursorPositions[i] > cursorPositions[j]) 
				{
					temp = cursorPositions[i];
					cursorPositions[i] = cursorPositions[j];
					cursorPositions[j] = temp;
				}
			}
		}
		for (int i = 0; i < Editor.stringToReplace.length(); i++) 
		{

			Editor.cnt.setCursorPosition(cursorPositions[nextIndex] - i, Editor.cursory);
			Editor.mll.delete(Editor.cursory - 3, cursorPositions[nextIndex] - 5 - i);
		}

		Editor.cnt.setCursorPosition(cursorPositions[nextIndex], Editor.cursory);
		System.out.print(Editor.stringToReplace);

		for (int i = 0; i < Editor.stringToReplace.length(); i++) 
		{
			Editor.mll.addCharacter(Editor.lineNumber, Editor.stringToReplace.charAt(i));
		}
		nextIndex--;
	}
	
	public void display(int row, int column, int startLine) // page up - page down will be added
	{
		int tempVal_column = column;
		LineNode temporaryLine = head;
		while(temporaryLine != null)
		{
			if (countLines() <= 20)
			{
				CharacterNode temporaryChar = temporaryLine.getRight();
				while(temporaryChar != null)
				{
//					if (column - 4 > 60)
//					{
//						row++;
//						column = 4;
//					}
					
//					if (Editor.selection) Editor.cn.setTextAttributes(Editor.att1);
//					if (!Editor.selection) Editor.cn.setTextAttributes(Editor.att0);
					
					Editor.cnt.setCursorPosition(column, row);
					
//					if(temporaryChar.getHasFound() == true)
//					{
//						Editor.cn.setTextAttributes(Editor.att1);
//						System.out.print(temporaryChar.getCharacter());
//						Editor.cn.setTextAttributes(Editor.att0);
//						temporaryChar = temporaryChar.getNext();
//						column++;
//					}
//					else if (temporaryChar.getHasFound() == false)
//					{
//						System.out.print(temporaryChar.getCharacter());
//						temporaryChar = temporaryChar.getNext();
//						column++;
//					}
					
					if (temporaryChar.getSelectionMark() == true)
					{
						Editor.cn.setTextAttributes(Editor.att1);
						System.out.print(temporaryChar.getCharacter());
						Editor.cn.setTextAttributes(Editor.att0);
						temporaryChar = temporaryChar.getNext();
						/*if(temporaryChar != null && temporaryChar.getCharacter() != '\n')*/ column++;
					}
					else if (temporaryChar.getSelectionMark() == false)
					{
						System.out.print(temporaryChar.getCharacter());
						temporaryChar = temporaryChar.getNext();
						/*if(temporaryChar != null && temporaryChar.getCharacter() != '\n')*/ column++;
					}
				}
				if(temporaryChar == null) column = tempVal_column;
				temporaryLine = temporaryLine.getDown();
				row++;
			}
			else
			{
				while(temporaryLine.getId() < startLine)
				{
					temporaryLine = temporaryLine.getDown();
				}

				CharacterNode temporaryChar = temporaryLine.getRight(); // this line needs a care
				
				while(temporaryChar != null)
				{
//					if (column - 4 > 60)
//					{
//						row++;
//						column = 4;
//					}
					
//					if (Editor.selection) Editor.cn.setTextAttributes(Editor.att1);
//					if (!Editor.selection) Editor.cn.setTextAttributes(Editor.att0);
					
					Editor.cnt.setCursorPosition(column, row);
					
//					if(temporaryChar.getHasFound() == true)
//					{
//						Editor.cn.setTextAttributes(Editor.att1);
//						System.out.print(temporaryChar.getCharacter());
//						Editor.cn.setTextAttributes(Editor.att0);
//						temporaryChar = temporaryChar.getNext();
//						column++;
//					}
//					else if (temporaryChar.getHasFound() == false)
//					{
//						System.out.print(temporaryChar.getCharacter());
//						temporaryChar = temporaryChar.getNext();
//						column++;
//					}
					
					if (temporaryChar.getSelectionMark() == true)
					{
						Editor.cn.setTextAttributes(Editor.att1);
						System.out.print(temporaryChar.getCharacter());
						Editor.cn.setTextAttributes(Editor.att0);
						temporaryChar = temporaryChar.getNext();
						column++;
					}
					else if (temporaryChar.getSelectionMark() == false)
					{
						System.out.print(temporaryChar.getCharacter());
						temporaryChar = temporaryChar.getNext();
						column++;
					}
					
//					System.out.print(temporaryChar.getCharacter());
//					temporaryChar = temporaryChar.getNext();
//					column++;
				}
				if(temporaryChar == null) column = tempVal_column;
				temporaryLine = temporaryLine.getDown();
				row++;
			}
		}
	}
	
	public void deleteLine(int lineNum)
	{
		LineNumber--;
		LineNode previousLine;
		LineNode temporaryLine;
		
		if (head != null) 
		{
			temporaryLine = head;
			previousLine = null;
			
			while (temporaryLine.getId() < lineNum) 
			{
				previousLine = temporaryLine;
				temporaryLine = temporaryLine.getDown();
			}
			
			if (temporaryLine != null) previousLine.setDown(temporaryLine.getDown());
		}
	}
	
	public void delete_bckspc(int row, int column) // done
	{
//		LineNode previousLine;
		LineNode temporaryLine;
		CharacterNode temporaryCharacter;
		CharacterNode previousCharacter;
		
		if (head != null)
		{
//			previousLine = null;
			temporaryLine = head;
			temporaryCharacter = null;
			previousCharacter = null;
			
			while(temporaryLine.getId() < row)
			{
//				previousLine = temporaryLine;
				temporaryLine = temporaryLine.getDown();
			}
			
			temporaryCharacter = temporaryLine.getRight();
			
//			if(temporaryCharacter == null) previousLine.setDown(temporaryLine.getDown());
			
			if (temporaryCharacter != null)
			{
				for (int i = 0; i < column; i++) 
				{
					previousCharacter = temporaryCharacter;
					temporaryCharacter = temporaryCharacter.getNext();
				}
				
				if(previousCharacter != null) previousCharacter.setNext(temporaryCharacter.getNext());
				else temporaryLine.setRight(temporaryCharacter.getNext()); // should find a better solution
			}
		}
	}
	
	public void delete_del(int row, int column) // done
	{
		LineNode temporaryLine = head;
		CharacterNode temporaryCharacter;
		
		while (temporaryLine.getId() < row) temporaryLine = temporaryLine.getDown();
		
		temporaryCharacter = temporaryLine.getRight();
		
		if(column == 0 && temporaryCharacter != null) temporaryLine.setRight(temporaryCharacter.getNext());
		
		if (temporaryCharacter != null) 
		{
			for (int i = 0; i < column - 1; i++) 
			{
				temporaryCharacter = temporaryCharacter.getNext();
			}
			
			if(temporaryCharacter.getNext() != null && temporaryCharacter.getNext().getCharacter() == '\n')
			{
				addCharacter_insertmode(row + 1, 0, ' ');
				temporaryCharacter.setNext(temporaryLine.getDown().getRight());
				deleteLine(row+1);
			}
			else if(temporaryCharacter.getNext() != null)
			{
				temporaryCharacter.setNext(temporaryCharacter.getNext().getNext());
			}
//			else temporaryCharacter.setNext(temporaryCharacter.getNext()); // idk why i wrote that
		}
	}
	
	public static int countOccurrences(String str, String word) 
	{
		// split the string by spaces in a
		String a[] = str.split(" ");

		// search for pattern in a
		int count = 0;
		for (int i = 0; i < a.length; i++) 
		{
			// if match found increase count
			if (word.equals(a[i]))
				count++;
		}

		return count;
	}
}