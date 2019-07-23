package adt.queue;

public class CircularQueue<T> implements Queue<T> {

    private T[] array;
    private int tail;
    private int head;
    private int elements;

    public CircularQueue(int size) {
        if (size < 0) return;

        array = (T[]) new Object[size];
        head = -1;
        tail = -1;
        elements = 0;
    }

    @Override
    public void enqueue(T element) throws QueueOverflowException {
        if (isFull()) {
            throw new QueueOverflowException();
        }

        if (element != null) {
            if (head < 0) {
                head += 1;
            }

            tail += 1;
            elements += 1;

            setTail(element);
        }
    }

    private void setTail(T element) {
        array[modTail()] = element;
    }

    @Override
    public T dequeue() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException();
        }

        T result = head();
        head += 1;
        elements -= 1;

        return result;
    }

    @Override
    public T head() {
        T result = null;

        if (!isEmpty()) {
            result = array[modHead()];
        }

        return result;
    }

    @Override
    public boolean isEmpty() {
        return elements == 0;
    }

    @Override
    public boolean isFull() {
        return elements == array.length;
    }

    // Aux methods
    private int modHead() {
        int result;

        if (size() != 0) {
            result = head % size();
        } else {
            result = -1;
        }

        return result;
    }
    private int modTail() {
        int result;

        if (size() != 0) {
            result = tail % size();
        } else {
            result = -1;
        }

        return result;
    }

    private int size() {
        return array.length - 1;
    }
}
