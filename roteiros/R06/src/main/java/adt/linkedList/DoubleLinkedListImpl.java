package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		this.head = new DoubleLinkedListNode<>();
		this.last = (DoubleLinkedListNode<T>) this.head;

		this.last.setPrevious(new DoubleLinkedListNode<>());
		this.last.setNext(new DoubleLinkedListNode<>());
	}

	@Override
    public void remove(T element) {
	    if (element == null) return;

	    DoubleLinkedListNode<T> aux = getHead();

	    while (!aux.isNIL()) {
	    	if (aux.getData() == element) {
	    		aux.getPrevious().setNext(aux.getNext());
				((DoubleLinkedListNode<T>)aux.getNext()).setPrevious(aux.getPrevious());
			}
	    	aux = (DoubleLinkedListNode<T>) aux.getNext();
		}

    }

	@Override
	public void insert(T element) {
		if (element == null) return;

		if (!isEmpty()) {
			DoubleLinkedListNode<T> aux = (DoubleLinkedListNode<T>) getLast().getNext();
			aux.setPrevious(getLast());
			setLast(aux);
		}

		getLast().setData(element);
		getLast().setNext(new DoubleLinkedListNode<>());
	}

	@Override
	public void insertFirst(T element) {
	    if (element == null) return;

		if (!getHead().isNIL()) {
			DoubleLinkedListNode<T> aux = getHead().getPrevious();
			aux.setNext(getHead());
			setHead(aux);
		}

		getHead().setData(element);
		getHead().setPrevious(new DoubleLinkedListNode<>());
	}

	@Override
	public void removeFirst() {
		if (getHead().isNIL()) return;

		setHead(getHead().getNext());
		getHead().setPrevious(new DoubleLinkedListNode<>());
	}

	@Override
	public void removeLast() {
		if (getLast().isNIL()) return;

		setLast(getLast().getPrevious());
		getLast().setNext(new DoubleLinkedListNode<>());
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

	@Override
    public DoubleLinkedListNode<T> getHead() {
	    return (DoubleLinkedListNode<T>) head;
    }

}
