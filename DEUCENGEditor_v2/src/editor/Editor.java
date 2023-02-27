package editor;

import enigma.core.Enigma;
//import enigma.event.TextMouseEvent;
//import enigma.event.TextMouseListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Scanner;

import enigma.console.*;
import java.awt.Color;

public class Editor 
{
	public static MLL mll = new MLL();
	
	public static String fileName = "NewFile.txt";
	
	public static enigma.console.Console cn = Enigma.getConsole("Editor", 93, 29, 16, 1); // col,row (default: 29),fontsize,fonttype
    public static enigma.console.TextWindow cnt = cn.getTextWindow();
    
    public static int cursorx = 5, cursory = 4, lastCursorX = 0, selectionX, selectionY, endX, endY, lastCursorPosition = 0;
    public static int lineNumber = 1;
    
    static String 
    selectedString = "", 
    cutString = "", 
    copiedString = "", 
    stringToFind = "", 
    foundString = "",
    stringToReplace = "";
    
//    static boolean hasChanged = false;
    public static boolean selection;
    
    private static int startLine = 1;
    public static int mode = 0; // insert by default
    
    public static TextAttributes att0 = new TextAttributes(Color.white, Color.black); // foreground, background color
    public static TextAttributes att1 = new TextAttributes(Color.black, Color.white);
    
//    public TextMouseListener tmlis;
    public KeyListener klis;

    // ------ Standard variables for keyboard and mouse 2 --------------------------
//    public int mousepr; // mouse pressed?
//    public int mousex, mousey; // mouse text coords.
    public int keyPress; // key pressed?
    public int keyRelease; // key (for press/release)
    public int rkeymod; // key modifiers
    public int capslock = 0; // 0:off 1:on
    // -----------------------------------------------------------------------------
        
    public void run() throws InterruptedException, IOException
    {
    	Factor.printBorder();
    	Factor.printInfo();
    	
    	mll.addLine();
    	
    	klis = new KeyListener() 
    	{
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e) 
            {
                if (keyPress == 0) 
                {
                    keyPress = 1;
                    keyRelease = e.getKeyCode();
                    rkeymod = e.getModifiersEx();
                    
                    if (keyRelease == KeyEvent.VK_CAPS_LOCK) 
                    {
                        if (capslock == 0) capslock = 1;
                        else capslock = 0;
                    }
                }
            }
            public void keyReleased(KeyEvent e) {}
        };
        
        cn.getTextWindow().addKeyListener(klis);
        
        @SuppressWarnings("unused")
		int curtype;
        curtype = cnt.getCursorType(); // default:2 (invisible) 0-1:visible
        cnt.setCursorType(0);
    	cnt.setCursorPosition(cursorx, cursory);
        
        while (true) 
        {
            if (keyPress == 1) // if keyboard button pressed 
            {
                if (keyRelease == KeyEvent.VK_F1) // selection start
                {
                	selection = true;
                	selectionX = cursorx;
                    selectionY = cursory;
                }
                if (keyRelease == KeyEvent.VK_F2) // selection end
                {
                	selection = false;
                	endX = cursorx - 1;
                    endY = cursory;

                    for (int i = selectionX; i <= endX; i++) 
                    {
                        selectedString = selectedString.concat
                        (
                        	Character.toString(MLL.searchByIndex(cursory - 3, i - 5).getCharacter())
                        );
                    }
                }
                //
                if(selection) MLL.searchByIndex(cursory - 3, cursorx - 5).setSelectionMark(true);
                //
                if (keyRelease == KeyEvent.VK_F3) // cut
                {
                	for (int i = 0; i < selectedString.length(); i++) 
                	{
                        mll.delete(endY - 3, endX - 5);
                        endX--;
                        cursorx--;
                    }
                    cutString = selectedString;
                    selectedString = "";
                }
                if (keyRelease == KeyEvent.VK_F4) // copy
                {
                	copiedString = selectedString;
                }
                if (keyRelease == KeyEvent.VK_F5) // paste
                {
                	if (cutString.equals("")) 
                	{
                        // copylenmişi yapıştır
                        for (int i = 0; i < copiedString.length(); i++) 
                        {
                            mll.addCharacter_insertmode(lineNumber, cursorx - 5 , copiedString.charAt(i));
                        }
                        copiedString = "";
                    } 
                	else 
                	{
                        // cutlanmışı yapıştır
                        for (int i = 0; i < cutString.length(); i++) 
                        {
                            mll.addCharacter_insertmode(lineNumber, cursorx - 5, cutString.charAt(i));
                        }
                        cutString = "";
                    }
                }
                if (keyRelease == KeyEvent.VK_F6) // find
                {
                    MLL.find();
                }
                if (keyRelease == KeyEvent.VK_F7) // replace
                {
                	// iki kelimenin de aynı uzunlukta olması gerekiyor.
                    Scanner sc = new Scanner(System.in);
                    cnt.setCursorPosition(3, 27);
                    System.out.print("Enter string:");
                    stringToReplace = sc.nextLine();
                    cnt.setCursorPosition(3, 27);
                    System.out.print("                                                                 ");

                    for (int i = 0; i < selectedString.length(); i++) 
                    {
                        mll.delete(cursory - 3, cursorx - 5); // kelimeyi silecek sondan başlayarak
                        cursorx--;
                    }
                    for (int i = 0; i < selectedString.length(); i++) 
                    {
                        mll.addCharacter(lineNumber, stringToReplace.charAt(i));
                        cursorx++;

                    }
                    // cursorx++;
                    sc.close();
                }
                if (keyRelease == KeyEvent.VK_F8) // next
                {
                	MLL.next();
                }
                if (keyRelease == KeyEvent.VK_F9) // align left
                {

                }
                if (keyRelease == KeyEvent.VK_F10) // justify
                {

                }
                if (keyRelease == KeyEvent.VK_F11) 
                {
                	cnt.setCursorPosition(3, 27);
                	System.out.print("Enter the name of the file you want to open: ");
            		Scanner sc = new Scanner(System.in);
                    fileName = sc.next() + ".txt";
                    sc.close();
                    cnt.setCursorPosition(3, 27);
                	System.out.print("                                                                 ");
                	cnt.setCursorType(0);
                	Factor.fileReader(fileName);
                	lineNumber = mll.countLines();
                	cursory = mll.countLines() + 3;
                	cursorx = mll.countCharacters(lineNumber) + 5;
                }
                if (keyRelease == KeyEvent.VK_F12) 
                {
                	cnt.setCursorPosition(3, 27);
                	System.out.print("Save As: ");
            		Scanner sc = new Scanner(System.in);
                    fileName = sc.next() + ".txt";
                    sc.close();
                    cnt.setCursorPosition(3, 27);
                	System.out.print("                                                                 ");
                	cnt.setCursorType(0);
                	Factor.fileWriter(fileName);
                }
                if (keyRelease == KeyEvent.VK_PAGE_UP) 
                {
//                	startLine -= 20;
//                	if (startLine < 1) startLine = 1;
//                	
//                	lineNumber -= 20;
//                	if(lineNumber < 1) lineNumber = 1;
//                	
//                	cursory = 4;
//                	cursorx = mll.countCharacters(lineNumber) + 5;
                	if(startLine > 1) 
                	{
                		startLine--;
                		lineNumber--;
                		cursory++;
//                		cursory = 4;
                	}
                }

                if (keyRelease == KeyEvent.VK_PAGE_DOWN) 
                {
//                	startLine += 20;
//                	if (startLine > mll.countLines()) startLine = mll.countLines();
//                	
//                	lineNumber += 20;
//                	if(lineNumber > mll.countLines()) lineNumber = mll.countLines();
//                	
//                	cursory = 23;
//                	cursorx = mll.countCharacters(lineNumber) + 5;
                	if(startLine < mll.countLines())
                	{
                		startLine++;
                		lineNumber++;
                		cursory--;
//                		cursory = mll.countLines() + 3;
                	}

                }
                if (keyRelease == KeyEvent.VK_HOME) 
                {
                	cursorx = 5;
                }
                if (keyRelease == KeyEvent.VK_END) 
                {
                	cursorx = mll.countCharacters(lineNumber) + 5;
                }
                if (keyRelease == KeyEvent.VK_INSERT) 
                {
                	if (mode == 0) mode = 1; 	  // 1 --> overwrite mode
                	else if (mode == 1) mode = 0; // 0 --> insert mode
                }
                
                ////////////////////////////////////////////////////////////////////////
                if (mode == 0)
                {
            		cnt.setCursorPosition(75, 23);
                	System.out.print("         ");

            		cnt.setCursorPosition(75, 23);
                	System.out.print("Insert");
                }
                if(mode == 1)
                {
                	cnt.setCursorPosition(75, 23);
                	System.out.print("         ");
                	
            		cnt.setCursorPosition(75, 23);
                	System.out.print("Overwrite");                	
                }
                ////////////////////////////////////////////////////////////////////////
                
                if (keyRelease == KeyEvent.VK_BACK_SPACE) 
                {
                	if(cursorx > 5)
                	{
                    	mll.delete_bckspc(lineNumber, cursorx - 6);
                        cursorx--;
                	}
                	else if(cursorx == 5 && lineNumber > 1 && mll.countCharacters(lineNumber) == 0)
                	{
                		mll.deleteLine(lineNumber);
                		lineNumber--;
                		cursory--;
                		cursorx = mll.countCharacters(lineNumber) + 5;
                		mll.delete_bckspc(lineNumber, cursorx - 5); // deletes '\n' at the end of the previous line.
//                		cursorx = mll.countCharacters(lineNumber) + 5;
                	}
                }
                if (keyRelease == KeyEvent.VK_DELETE) 
                {
                    mll.delete_del(lineNumber, cursorx - 5);
                }
                if (keyRelease == KeyEvent.VK_SPACE) 
                {
//                	hasChanged = true;
                	if(mode == 0) // insert
                	{
                    	mll.addCharacter_insertmode(lineNumber, cursorx - 5, ' ');
                    	cursorx++;
                	}
                	else if (mode == 1) // overwrite
                	{
                		mll.addCharacter_overwritemode(lineNumber, cursorx - 5, ' ');
                    	cursorx++;
                	}
                }
                if (keyRelease == KeyEvent.VK_ENTER) 
                {
//                	hasChanged = true;
                	if(mll.countLines() >= 20)
                	{
                    	mll.addCharacter(lineNumber, '\n');
                    	mll.addLine();
                    	lineNumber++;
                    	cursorx = 5;
                		startLine++;
                	}
                	else
                	{
                    	mll.addCharacter(lineNumber, '\n');
                    	mll.addLine();
                    	lineNumber++;
                    	cursorx = 5;
                    	cursory++;
                	}
                }
                if (keyRelease == KeyEvent.VK_LEFT) //
                {
                	if (cursorx > 5) cursorx--;
                }
                if (keyRelease == KeyEvent.VK_RIGHT) //
                {
                	if(cursorx < mll.countCharacters(lineNumber) + 5) cursorx++;
                }
                if (keyRelease == KeyEvent.VK_UP) //
                {
                	if(cursory > 4 && lineNumber > 1)
                	{
                		cursory--;
                		lineNumber--;
                	}
                	else if(cursory == 4 && startLine > 1) 
                	{
                		startLine--;
                		lineNumber--;
                	}
                	
                	if(cursorx > mll.countCharacters(lineNumber) + 5) cursorx = mll.countCharacters(lineNumber) + 5;
                }
                if (keyRelease == KeyEvent.VK_DOWN) //
                {
                	if(cursory < mll.countLines() + 3) 
                	{
                		cursory++;
                		lineNumber++;
                	}
                	else if (cursory == 24 && mll.countLines() > 20) 
                	{
                		startLine++;
                		lineNumber++;
                	}
                	if(cursorx > mll.countCharacters(lineNumber) + 5) cursorx = mll.countCharacters(lineNumber) + 5;
                }
                
                char rckey=(char)keyRelease;
                
                if (rckey >= 'A' && rckey <= 'Z') 
                {
//                	hasChanged = true;
                    if (((rkeymod & KeyEvent.SHIFT_DOWN_MASK) > 0) || capslock == 1) 
                    {
                    	if(mode == 0) // insert
                    	{
                        	mll.addCharacter_insertmode(lineNumber, cursorx - 5, rckey);
                        	cursorx++;
                    	}
                    	else if (mode == 1) // overwrite
                    	{
                    		mll.addCharacter_overwritemode(lineNumber, cursorx - 5, rckey);
                        	cursorx++;
                    	}
                    } 
                    else 
                    {
                    	if(mode == 0) // insert
                    	{
                        	mll.addCharacter_insertmode(lineNumber, cursorx - 5, (char)(rckey + 32));
                        	cursorx++;
                    	}
                    	else if (mode == 1) // overwrite
                    	{
                    		mll.addCharacter_overwritemode(lineNumber, cursorx - 5, (char)(rckey + 32));
                        	cursorx++;
                    	}
                    }
                }
                if ((rkeymod & KeyEvent.SHIFT_DOWN_MASK) == 0) 
                {
                	if (rckey >= '0' && rckey <= '9') 
                    {
//                		hasChanged = true;
                		if(mode == 0) // insert
                    	{
                        	mll.addCharacter_insertmode(lineNumber, cursorx - 5, rckey);
                        	cursorx++;
                    	}
                    	else if (mode == 1) // overwrite
                    	{
                    		mll.addCharacter_overwritemode(lineNumber, cursorx - 5, rckey);
                        	cursorx++;
                    	}
                    }
                    if (rckey == '.' || rckey == ',' || rckey == '-') 
                    {
//                    	hasChanged = true;
                    	if(mode == 0) // insert
                    	{
                        	mll.addCharacter_insertmode(lineNumber, cursorx - 5, rckey);
                        	cursorx++;
                    	}
                    	else if (mode == 1) // overwrite
                    	{
                    		mll.addCharacter_overwritemode(lineNumber, cursorx - 5, rckey);
                        	cursorx++;
                    	}
                    }
                } 
                else 
                {
                	if (rckey == '8') 
                    {
//                		hasChanged = true;
                		if(mode == 0) // insert
                    	{
                        	mll.addCharacter_insertmode(lineNumber, cursorx - 5, '(');
                        	cursorx++;
                    	}
                    	else if (mode == 1) // overwrite
                    	{
                    		mll.addCharacter_overwritemode(lineNumber, cursorx - 5, '(');
                        	cursorx++;
                    	}
                    }
                	if (rckey == '9') 
                    {
//                		hasChanged = true;
                		if(mode == 0) // insert
                    	{
                        	mll.addCharacter_insertmode(lineNumber, cursorx - 5, ')');
                        	cursorx++;
                    	}
                    	else if (mode == 1) // overwrite
                    	{
                    		mll.addCharacter_overwritemode(lineNumber, cursorx - 5, ')');
                        	cursorx++;
                    	}
                    }
                    if (rckey == '.') 
                    {
//                    	hasChanged = true;
                    	if(mode == 0) // insert
                    	{
                        	mll.addCharacter_insertmode(lineNumber, cursorx - 5, ':');
                        	cursorx++;
                    	}
                    	else if (mode == 1) // overwrite
                    	{
                    		mll.addCharacter_overwritemode(lineNumber, cursorx - 5, ':');
                        	cursorx++;
                    	}
                    }
                    if (rckey == ',') 
                    {
//                    	hasChanged = true;
                    	if(mode == 0) // insert
                    	{
                        	mll.addCharacter_insertmode(lineNumber, cursorx - 5, ';');
                        	cursorx++;
                    	}
                    	else if (mode == 1) // overwrite
                    	{
                    		mll.addCharacter_overwritemode(lineNumber, cursorx - 5, ';');
                        	cursorx++;
                    	}
                    }
                }
                
                ////////////// test info //////////////////////////////////////////////////////////////////////////////
//                cnt.setCursorPosition(3, 27);
//                System.out.print("Selected string: " + selectedString + " ");
//                if(MLL.searchByIndex(lineNumber, cursorx - 5) != null)
//                {
//                	cnt.setCursorPosition(3, 28);
//                    System.out.print("has found before?: " + MLL.searchByIndex(lineNumber, cursorx - 5).getHasFound() + " ");
//                }
//                cnt.setCursorPosition(20, 27);
//                System.out.print("number of lines: " + mll.countLines() + " ");
//                cnt.setCursorPosition(20, 28);
//                System.out.print("current line: " + lineNumber + " ");
//                cnt.setCursorPosition(20, 29);
//                System.out.print("total character amount: " + mll.countAllCharacters() + " ");
//                cnt.setCursorPosition(20, 30);
//                System.out.print("number of characters in the current line: " + mll.countCharacters(lineNumber) + " ");
//                cnt.setCursorPosition(3, 27);
//                System.out.print("cursorx: " + cursorx + " ");
//                cnt.setCursorPosition(3, 28);
//                System.out.print("cursory: " + cursory + " ");
//                cnt.setCursorPosition(3, 29);
//                System.out.print("startline: " + startLine + " ");
//                cnt.setCursorPosition(3, 30);
//                System.out.print("mode: " + mode + " ");
                ////////////////////////////////////////////////////////////////////////////////////////////////////////
                
                Factor.clearEditor();
                mll.display(4, 5, startLine); // startLine stands for page up/down operations
                cnt.setCursorPosition(cursorx, cursory);
                keyPress = 0;
            }
            Thread.sleep(30);
        }
    }
}