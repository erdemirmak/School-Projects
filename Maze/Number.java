public class Number {
	
	private int value;
	private int color; // 0 = Green, 1 = Yellow, 2 = Red
	private int[] position = new int[2];
	private boolean isMoved = false; 
	private int randomDirection = -1; // 0: right 1: left 2: up 3: down
	private Coordinate[] path;

	public Number(int value, int x, int y) {
		this.value = value;
		this.color = WhatColor();
		this.position[0] = x;
		this.position[1] = y;
	}
	//this function determines what color the number is
	public int WhatColor() {
		int color;
		if (this.value < 4) {
			color = 0;
		} else if (this.value < 7) {
			color = 1;
		} else {
			color = 2;
		}
		return color;
	}
	//this function adds the incoming coordinate to the end of the path
	public void AddCoordinateToPath(Coordinate coordinate) {
		Coordinate[] newList;
		if (this.path != null) {
			int i = this.path.length + 1;
			newList = new Coordinate[i--];
			for (int j = 0; j < i; j++) {
				newList[j] = this.path[j];
			}
			newList[i] = coordinate;
		} else {
			newList = new Coordinate[1];
			newList[0] = coordinate;
		}
		this.path = newList;
	}
	//this function adds the incoming coordinate to the beginning of the path
	public void AddCoordinateToFirstIndex(Coordinate coordinate) {
		Coordinate[] newList;
		if (this.path != null) {
			int i = this.path.length + 1;
			newList = new Coordinate[i--];
			for (int j = 0; j < i; j++) {
				newList[j+1] = this.path[j];
			}
			newList[0] = coordinate;
		} else {
			newList = new Coordinate[1];
			newList[0] = coordinate;
		}
		this.path = newList;
	}
	//this function deletes the last coordinate of the path and returns
	public Coordinate RemoveCoordinateToPath() {
		Coordinate retData = path[path.length - 1];
		if (path.length!=1) {
			Coordinate[] newList = new Coordinate[path.length - 1];
			for (int i = 0; i < newList.length; i++) {
				newList[i] = path[i];
			}path = newList;
			
		}else {
			path = null;
		}return retData;
		

	}
	//this function determines if the humanNumber is on the path, if it is on the path, it shortens the path.
	public void CheckPath() {
		if (path != null) {
			int cutPoint=-1;
			for (int i = 0; i < path.length; i++) {
				if (path[i].getX()==Game.humanNumber.getPositionx() && path[i].getY()==Game.humanNumber.getPositiony()) {
					cutPoint = i;
					break;
				}
			}if (cutPoint != -1) {
				Coordinate[] newList = new Coordinate[path.length-cutPoint-1];
				cutPoint++;
				for (int i = 0; cutPoint < path.length; cutPoint++) {
					newList[i++]= path[cutPoint];
				}path = newList;
			}
		}
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	public int getPositionx() {
		return position[0];
	}
	public int getPositiony() {
		return position[1];
	}
	public void setPositionx(int x) {
		this.position[0] = x;
	}
	public void setPositiony(int y) {
		this.position[1] = y;
	}
	public void setMoved(boolean isMoved) {
		this.isMoved = isMoved;
	}
	public boolean isMoved() {
		return isMoved;
	}
	public int getRandomDirection() {
		return randomDirection;
	}
	public void setRandomDirection(int randomDirection) {
		this.randomDirection = randomDirection;
	}
	public Coordinate[] getPath() {
		return path;
	}
	public void setPath(Coordinate[] path) {
		this.path = path;
	}
	

}
