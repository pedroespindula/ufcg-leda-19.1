package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
	    return getHead().isNIL();
	}

	@Override
	public int size() {
	    int size = 0;
	    SingleLinkedListNode<T> aux = getHead();

	    while (!aux.isNIL()) {
			size += 1;
	    	aux = aux.getNext();
		}

	    return size;
	}

	@Override
	public T search(T element) {
		T  result = null;
		SingleLinkedListNode<T> aux = getHead();

		while (!aux.isNIL() && result == null) {
			if (aux.getData().equals(element)) {
				result = aux.getData();
			}
			aux = aux.getNext();
		}

		return result;
	}

	@Override
	public void insert(T element) {
	    if (element == null) return;

	    SingleLinkedListNode<T> aux = getHead();

	    while (!aux.isNIL()) {
	    	aux = aux.getNext();
		}

	    aux.setData(element);
	    aux.setNext(new SingleLinkedListNode<>());
	}

	@Override
	public void remove(T element) {
		boolean found = false;
		SingleLinkedListNode<T> aux = getHead();
		SingleLinkedListNode<T> before = new SingleLinkedListNode<>();

		while (!aux.isNIL() && !found) {
		    if (aux.getData().equals(element)) {
		    	found = true;
				before.setNext(aux.next);
			}
			before = aux;
			aux = aux.getNext();
		}
	}

	@Override
	public T[] toArray() {
		Object[] result = new Object[size()];

		int cont = 0;
		SingleLinkedListNode<T> aux = getHead();
		while (!aux.isNIL()) {
			result[cont++] = aux.getData();
			aux = aux.getNext();
		}

		return (T[]) result;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
