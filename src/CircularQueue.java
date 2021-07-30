
public class CircularQueue {

	private int front, rear;
	private Object[] elements;

	CircularQueue(int capacity) {
		elements = new Object[capacity];
		rear = -1;
		front = 0;
	}

	void enqueue(Object data) {

		if (isFull()) {
			System.out.println("Queue overflow");
		} else {
			rear = (rear + 1) % elements.length;
			elements[rear] = data;
		}

	}

	Object dequeue() {

		if (isEmpty()) {
			System.out.println("Queue is empty");
			return null;
		} else {

			Object retData = elements[front];
			elements[front] = null;
			front = (front + 1) % elements.length;
			return retData;
		}
	}

	Object Peek() {
		Object data = elements[front];
		return data;
	}

	int size() {
		if (rear >= front)
			return rear - front + 1;
		else if (elements[front] != null)
			return elements.length - (front - rear) + 1;
		else
			return 0;

	}

	public boolean isFull() {
		if (front == (rear + 1) % elements.length && elements[front] != null && elements[rear] != null)
			return true;
		else
			return false;
	}

	boolean isEmpty() {
		if (elements[front] == null)
			return true;
		else
			return false;
	}

}
