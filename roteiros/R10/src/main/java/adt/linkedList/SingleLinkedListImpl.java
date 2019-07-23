package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

    protected SingleLinkedListNode<T> head;
    protected int size;

    public SingleLinkedListImpl() {
        this.head = new SingleLinkedListNode<T>();
        this.size = 0;
    }

    @Override
    public boolean isEmpty() {
        return this.getHead().isNIL();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T search(T element) {
        T result = null;

        if (element != null) {
            SingleLinkedListNode<T> aux = this.getHead();

            while (!aux.isNIL() && result == null) {
                if (aux.getData().equals(element)) {
                    result = element;
                }
                aux = aux.getNext();
            }
        }

        return result;
    }

    @Override
    public void insert(T element) {
        if (element != null) {
            SingleLinkedListNode<T> aux = getLastEmpty();

            aux.setData(element);
            aux.setNext(new SingleLinkedListNode<>());
            this.size += 1;
        }
    }

    @Override
    public void remove(T element) {
        if (element != null) {
            SingleLinkedListNode<T> aux = this.getHead();

            while (!aux.isNIL()) {
                if (aux.getData().equals(element)) {
                    aux.setData(aux.getNext().getData());
                    aux.setNext(aux.getNext().getNext());
                    this.size -= 1;
                } else {
                    aux = aux.getNext();
                }
            }
        }
    }

    @Override
    public T[] toArray() {
        T[] result = (T[]) new Object[this.size()];

        SingleLinkedListNode<T> aux = this.getHead();

        int i = 0;
        while (!aux.isNIL()) {
            result[i++] = aux.getData();
            aux = aux.getNext();
        }

        return result;
    }

    public SingleLinkedListNode<T> getHead() {
        return head;
    }

    public void setHead(SingleLinkedListNode<T> head) {
        this.head = head;
    }

    protected SingleLinkedListNode<T> getLastEmpty() {
        SingleLinkedListNode<T> aux = this.getHead();

        while (!aux.isNIL()) {
            aux = aux.getNext();
        }
        return aux;
    }
}
