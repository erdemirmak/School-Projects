package editor;

public class LineNode 
{
	private int id; // line numbers
	private CharacterNode right;
	private LineNode down;
	
	LineNode(int id) // constructor
	{
		this.id = id;
		right = null;
		down = null;
	}
	
	public int getId() {return id;}					//
	public CharacterNode getRight() {return right;} // getters
	public LineNode getDown() {return down;}		//
	
	public void setId(int id) {this.id = id;}						//
	public void setRight(CharacterNode right) {this.right = right;} // setters
	public void setDown(LineNode down) {this.down = down;}			//
}