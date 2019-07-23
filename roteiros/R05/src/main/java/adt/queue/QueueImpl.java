package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		if (size < 0) return;

		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		T result = null;

		if (!isEmpty()) {
			result = array[0];
		}

		return result;
	}

	@Override
	public boolean isEmpty() {
	    return tail < 0;
	}

	@Override
	public boolean isFull() {
	    return tail == array.length - 1;
	}

	private void shiftLeft() {
		for (int i = 1; i < array.length; i++) {
			array[i - 1] = array[i];
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {

		if (isFull()) {
			throw new QueueOverflowException();
		}

		if (element != null) {
			array[++tail] = element;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {

		if (isEmpty()) {
			throw new QueueUnderflowException();
		}

		T result = head();
		tail -= 1;
		shiftLeft();

		return result;
	}

}
