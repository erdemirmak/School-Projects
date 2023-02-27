public class CircularQueue {
	
	private int rear, front;
	private Object[] elements;

	public CircularQueue(int capacity) {
		this.rear = -1;
		this.front = 0;
		this.elements = new Object[capacity];
	}

	public void Enqueue(Object data) {
		if (!isFull()) {
			rear = (rear + 1) % elements.length;
			elements[rear] = data;
		}
	}
	public Object Dequeue() {
		if (!isEmpty()) {
			Object data = elements[front];
			elements[front] = null;
			front = (front + 1) % elements.length;
			return data;
		} else
			return null;
	}
	public Object Peek() {
		if (!isEmpty()) {
			return elements[front];
		} else
			return null;
	}
	public boolean isEmpty() {
		return elements[front] == null;
	}
	public boolean isFull() {
		return front == (rear + 1) % elements.length && elements[front] != null && elements[rear] != null;

	}
	public int Size() {
		if (elements[front] == null) {
			return 0;
		} else {
			if (rear >= front)
				return rear - front + 1;
			else
				return elements.length - (front - rear) + 1;
		}
	}
}
