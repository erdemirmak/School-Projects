public class Stack {

	private int top = -1;
	private Object[] elements;
	
	public Stack(int capacity) {
		this.elements = new Object[capacity];
	}
	
	public void Push(Object data) {
		if (!isFull())
			this.elements[++this.top] = data;
	}
	public Object Pop() {
		if (isEmpty()) {
			return null;
		} else {
			Object returnObject = this.elements[this.top--];
			this.elements[this.top + 1] = null;
			return returnObject;
		}
	}
	public Object Peek() {
		if (isEmpty()) {
			return null;
		} else
			return this.elements[this.top];
	}
	public boolean isEmpty() {
		return (this.top == -1);
	}
	public boolean isFull() {
		return (this.top + 1 == this.elements.length);
	}
	public int Size() {
		return this.top + 1;
	}
}
