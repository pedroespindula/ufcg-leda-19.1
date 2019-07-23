package adt.stack;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StackTest {

    private Stack defaultStack;
    private int SIZE = 10;

    @Before
    public void init() {
        defaultStack = new StackImpl(SIZE);
    }

    @Test(expected = StackOverflowException.class)
    public void testStackOverflowExceptionThrows() throws StackOverflowException {
        fulfillStack();
        defaultStack.push(1);
    }

    private void fulfillStack() throws StackOverflowException {
        for (int i = 0; i < SIZE; i ++) {
            defaultStack.push(1);
        }
    }

    @Test(expected = StackUnderflowException.class)
    public void testStackUnderflowExceptionThrows() throws StackUnderflowException {
        defaultStack.pop();
    }

    @Test
    public void testTopEmptyStack() {
        assertNull(defaultStack.top());
    }

    @Test
    public void testTop() throws StackOverflowException {
        int element = 1;
        defaultStack.push(element);
        assertEquals(element, defaultStack.top());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(defaultStack.isEmpty());
    }

    @Test
    public void testIsEmptyPushingAndPopingElement() throws StackOverflowException, StackUnderflowException {
        testIsEmptyPushingElement();
        defaultStack.pop();
        assertTrue(defaultStack.isEmpty());
    }

    @Test
    public void testIsEmptyPushingElement() throws StackOverflowException {
        defaultStack.push(1);
        assertFalse(defaultStack.isEmpty());
    }

    @Test
    public void testIsFull() throws StackOverflowException {
        fulfillStack();
        assertTrue(defaultStack.isFull());
    }

    @Test
    public void testIsFullAfterPop() throws StackOverflowException, StackUnderflowException {
        testIsFull();
        defaultStack.pop();
        assertFalse(defaultStack.isFull());
    }

    @Test
    public void testSmallerThanZero() {
        Stack test = new StackImpl(0);

        assertTrue(test.isEmpty());
        assertTrue(test.isFull());
    }
}