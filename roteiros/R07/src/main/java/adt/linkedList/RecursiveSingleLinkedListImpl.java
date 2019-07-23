package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	@Override
	public boolean isEmpty() {
	    return this.data == null;
	}

	@Override
	public int size() {
		if (isEmpty()) {
		    return 0;
		}

	    return  1 + this.next.size();
	}

	@Override
	public T search(T element) {
		if (isEmpty()) {
			return null;
		}

		if (this.data.equals(element)) {
			return element;
		}

		return this.next.search(element);
	}

	@Override
	public void insert(T element) {
	    if (isEmpty()) {
	    	this.data = element;
	    	this.next = new RecursiveSingleLinkedListImpl<>();
		} else {
			this.next.insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if (isEmpty()) {

		}

		if (this.data.equals(element)) {
			this.data = this.next.data;
			this.next = this.next.next;
		} else {
			this.next.remove(element);
		}
	}

	@Override
	public T[] toArray() {
	    return toArray((T[]) new Object[size()], 0);
	}

	private T[] toArray(T[] result, int index) {
		if (!isEmpty()) {
			result[index] = this.data;
			return this.next.toArray(result, index + 1);
		}

		return result;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
