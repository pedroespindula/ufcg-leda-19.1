package adt.queue;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueueTest {

    private Queue defaultQueue;
    private int SIZE = 10;

    @Before
    public void init() {
        defaultQueue = new CircularQueue(SIZE);
    }

    @Test(expected = QueueOverflowException.class)
    public void testQueueOverflowException() throws QueueOverflowException {
        defaultQueue.enqueue(10);
    }

    @Test
    public void testIsEmpty() throws QueueOverflowException, QueueUnderflowException {
        assertTrue(defaultQueue.isEmpty());
        defaultQueue.enqueue(1);
        assertFalse(defaultQueue.isEmpty());
        defaultQueue.dequeue();
        assertTrue(defaultQueue.isEmpty());
    }
}