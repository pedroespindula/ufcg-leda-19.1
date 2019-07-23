package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new RecursiveDoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (element == null) return;

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
	    return size == top.size();
	}

}
