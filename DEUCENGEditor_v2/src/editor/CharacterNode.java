package editor;

public class CharacterNode 
{
	private char character;
	private CharacterNode next;
	private boolean selectionMark;
	private boolean hasFound;
	
	CharacterNode(char character) // constructor
	{
		this.character = character; 
		next = null;
		selectionMark = false;
		hasFound = false;
	} 
	
	public char getCharacter() {return character;} 				//
	public CharacterNode getNext() {return next;}  				// getters
	public boolean getSelectionMark() {return selectionMark;}	//
	public boolean getHasFound() {return hasFound;}				//
	
	public void setCharacter(char character) {this.character = character;} //
	public void setNext(CharacterNode next) {this.next = next;}			   // setters
	public void setSelectionMark(boolean data) {selectionMark = data;}	   //
	public void setHasFound(boolean data) {hasFound = data;}			   //
}