package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {
	}

	@Override
	public void insert(T element) {
	    if (element == null) return;

		if (isEmpty()) {
		    this.setData(element);
		    this.setNext(new RecursiveDoubleLinkedListImpl<>());
		    this.getNext().setPrevious(this);
		} else {
			this.getNext().insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if (element == null) return;

		if (!isEmpty()) {
			if (this.getData().equals(element)) {
				this.getNext().setPrevious(this.getPrevious());
				this.getPrevious().setNext(this.getNext());
			} else {
				this.getNext().remove(element);
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element == null) return;

		RecursiveDoubleLinkedListImpl<T> oldHead = new RecursiveDoubleLinkedListImpl<>();

        oldHead.setData(this.getData());
        oldHead.setNext(this.getNext());
        oldHead.setPrevious(this);

        this.setData(element);
        this.setNext(oldHead);
        this.getNext().setPrevious(oldHead);
	}


	@Override
	public void removeFirst() {
		this.setData(this.getNext().getData());

		if (this.getNext().getNext() == null) {
			this.setNext(new RecursiveDoubleLinkedListImpl<>());
		} else {
			this.setNext(this.getNext().getNext());
		}

		this.getNext().setPrevious(this);
	}

	@Override
	public void removeLast() {
	    if (this.getNext().isEmpty()) {
	        this.getPrevious().setNext(this.getNext());
	        this.getNext().setPrevious(this.getPrevious());
		} else {
	    	this.getNext().removeLast();
		}
	}

	@Override
	public RecursiveDoubleLinkedListImpl<T> getNext() {
		return (RecursiveDoubleLinkedListImpl<T>) this.next;
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}
}
