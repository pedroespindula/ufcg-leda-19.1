package adt.queue;

/**
 * The interface of a generic queue. The queue is able to store any kind of
 * data.
 *
 */
public interface Queue<T> {

	/**
	 * Inserts a new element in the queue or returns an exception if the queue
	 * is full. Null elements are not allowed (the queue remains unchanged).
	 * 
	 * @param element
	 * @throws QueueOverflowException
	 */
	void enqueue(T element) throws QueueOverflowException;

	/**
	 * If the queue has elements, it removes the oldest of the queue and returns
	 * it; otherwise, it throws an exception.
	 * 
	 * @return
	 * @throws QueueUnderflowException
	 */
	T dequeue() throws QueueUnderflowException;

	/**
	 * Returns (without removing) the oldest element of the queue; or 
	 * null if the queue is empty.
	 * 
	 * @return
	 */
	T head();

	/**
	 * Returns true if the queue is empty; or false, otherwise.
	 * 
	 * @return
	 */
	boolean isEmpty();

	/**
	 * Returns true if the queue is full; or false, otherwise.
	 * 
	 * @return
	 */
	boolean isFull();

}
