package editor;

import enigma.console.*;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

public class Factor
{	
	private static TextAttributes borderColor = new TextAttributes(Color.lightGray, Color.lightGray);
	private static TextAttributes defaultColor = new TextAttributes(Color.white, Color.black);
	
	public static void printBorder()
	{
		int x = 3, y = 2;
		
//		System.setProperty("encoding", "UTF-8");
//		Charset.defaultCharset();
		
//		PrintWriter consoleOut = new PrintWriter(new OutputStreamWriter(System.out,"UTF-8"));
		
//		Editor.cnt.setCursorPosition(34, 1);
//		System.out.println('▲');//△▽▲▼↑↓∆∇∧∨
//		
//		Editor.cnt.setCursorPosition(34, 26);
//		System.out.println('▼');
		
		Editor.cn.setTextAttributes(borderColor);
				
		for (int i = 0; i < 64; i++) 
		{
            Editor.cn.getTextWindow().setCursorPosition(x, y);
            System.out.print("#");
            x++;
        }
		x--;
        y++;
        for (int i = 0; i < 23; i++) 
        {
            Editor.cn.getTextWindow().setCursorPosition(x, y);
            System.out.println("#");
            y++;
        }
        x = 3;
        y = 3;
        for (int i = 0; i < 23; i++) 
        {
            Editor.cn.getTextWindow().setCursorPosition(x, y);
            System.out.println("#");
            y++;
        }
        y--;
        for (int i = 0; i < 64; i++) 
        {
            Editor.cn.getTextWindow().setCursorPosition(x, y);
            System.out.println("#");
            x++;
        }
        Editor.cn.setTextAttributes(defaultColor);
	}
	
	public static void printInfo()
	{
		Editor.cn.setTextAttributes(borderColor);
		Editor.cn.getTextWindow().setCursorPosition(69, 2);
		System.out.println("#####################");
		Editor.cn.setTextAttributes(defaultColor);
		Editor.cn.getTextWindow().setCursorPosition(72, 4);
		System.out.println("DEU CENG EDITOR");
		Editor.cn.setTextAttributes(borderColor);
		Editor.cn.getTextWindow().setCursorPosition(69, 6);
		System.out.println("#####################");
		Editor.cn.setTextAttributes(defaultColor);
		
		Editor.cn.getTextWindow().setCursorPosition(69, 8);
	    System.out.println("F1:  Selection start");
	    Editor.cn.getTextWindow().setCursorPosition(69, 9);
	    System.out.println("F2:  Selection end");
	    Editor.cn.getTextWindow().setCursorPosition(69, 10);
	    System.out.println("---------o----------");
	    Editor.cn.getTextWindow().setCursorPosition(69, 11);
	    System.out.println("F3:  Cut");
	    Editor.cn.getTextWindow().setCursorPosition(69, 12);
	    System.out.println("F4:  Copy");
	    Editor.cn.getTextWindow().setCursorPosition(69, 13);
	    System.out.println("F5:  Paste");
	    Editor.cn.getTextWindow().setCursorPosition(69, 14);
	    System.out.println("F6:  Find");
	    Editor.cn.getTextWindow().setCursorPosition(69, 15);
	    System.out.println("F7:  Replace");
	    Editor.cn.getTextWindow().setCursorPosition(69, 16);
	    System.out.println("F8:  Next");
	    Editor.cn.getTextWindow().setCursorPosition(69, 17);
	    System.out.println("F9:  Align Left");
	    Editor.cn.getTextWindow().setCursorPosition(69, 18);
	    System.out.println("F10: Justify");
	    Editor.cn.getTextWindow().setCursorPosition(69, 19);
	    System.out.println("---------o----------");
	    Editor.cn.getTextWindow().setCursorPosition(69, 20);
	    System.out.println("F11: Load");
	    Editor.cn.getTextWindow().setCursorPosition(69, 21);
	    System.out.println("F12: Save");
	    Editor.cn.getTextWindow().setCursorPosition(69, 22);
	    System.out.println("---------o----------");
	    Editor.cn.getTextWindow().setCursorPosition(69, 23);
	    System.out.println("Mode: ");
	    Editor.cn.getTextWindow().setCursorPosition(75, 23);
    	System.out.println("Insert");
	    Editor.cn.getTextWindow().setCursorPosition(69, 24);
	    System.out.println("---------o----------");
	    Editor.cn.getTextWindow().setCursorPosition(69, 25);
	    System.out.println("Alignment: ");
//	    Editor.cn.getTextWindow().setCursorPosition(80, 25);
//	    System.out.println("Justify ");
	}
	
	public static void fileReader(String fileName) throws IOException, InterruptedException
	{
		int lineNumber = 1;
		
		BufferedReader inputStream = null; // initializing buffered reader.
//		FileReader file = new FileReader(fileName);
		try 
		{
			inputStream = new BufferedReader(new FileReader(fileName));
            String line;
            
            while ((line = inputStream.readLine()) != null)
            {
            	for (int i = 0; i < line.length(); i++) 
            	{
					Editor.mll.addCharacter(lineNumber, line.charAt(i));
				}
            	Editor.mll.addLine();
            	lineNumber++;
            }
		} 
		catch (FileNotFoundException e)
		{
			Editor.cnt.setCursorPosition(3, 27);
			System.out.print("File '" + fileName + "' not found");
			Thread.sleep(2000);
			Editor.cnt.setCursorPosition(3, 27);
        	System.out.print("                                                                 ");
		}
		finally
		{
			if(inputStream != null)
			{
				inputStream.close();
			}
		}
		
//		if(Editor.hasChanged == false)
//		{
//			
//		}
//		else
//		{
//			while(true)
//			{
//				Editor.cnt.setCursorPosition(3, 27);
//	        	System.out.print("Do you want to save the current file? : ");
//	        	Scanner sc = new Scanner(System.in);
//	            String choice = sc.next();
//			}
//		}
	}
	
	public static void fileWriter(String fileName) throws IOException, InterruptedException
	{
		BufferedWriter outputStream = null;
		try 
		{
            // File file = new File("SavedFile.txt");
            // if (!file.exists()) {
            // file.createNewFile();
            // }
			outputStream = new BufferedWriter(new FileWriter(fileName));
//            FileWriter fileWritter = new FileWriter(fileName);
//            BufferedWriter bw = new BufferedWriter(fileWritter);
            String[] arr = MLL.mllToString();
            for (int i = 1; i < arr.length; i++) 
            {
                outputStream.write(arr[i]);
            }
            outputStream.close();
        } 
		catch (FileAlreadyExistsException e) 
		{
			Editor.cnt.setCursorPosition(3, 27);
        	System.out.print("The file " + fileName + " already exists!");
        	Thread.sleep(2000);
        	Editor.cnt.setCursorPosition(3, 27);
        	System.out.print("                                                                 ");
        }
	}
	
	public static void clearEditor()
	{
		int x = 4;
		int y = 3;
		
		for (int i = 0; i < 22; i++) 
		{
			for (int j = 0; j < 62; j++) 
			{
				Editor.cnt.setCursorPosition(x, y);
				System.out.print(" ");
				x++;
			}
			System.out.print("\n");
			x = 4;
			y++;
		}
	} 
}