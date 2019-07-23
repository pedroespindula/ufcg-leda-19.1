package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (isFull()) {
			throw new StackOverflowException();
		}

	    top.insertFirst(element);
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty()) {
			throw new StackUnderflowException();
		}

	    T result = top();
		top.removeFirst();
		return result;
	}

	@Override
	public T top() {
		return isEmpty() ? null : top.toArray()[0];
	}

	@Override
	public boolean isEmpty() {
	    return top.isEmpty();
	}

	@Override
	public boolean isFull() {
	    return top.size() == size;
	}

}
