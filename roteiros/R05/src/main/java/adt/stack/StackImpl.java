package adt.stack;

public class StackImpl<T> implements Stack<T> {

   private T[] array;
   private int top;

   @SuppressWarnings("unchecked")
   public StackImpl(int size) {
      if (size < 0)
         return;

      array = (T[]) new Object[size];
      top = -1;
   }

   @Override
   public T top() {
      T result = null;

      if (!isEmpty()) {
         result = array[top];
      }

      return result;
   }

   @Override
   public boolean isEmpty() {
      return top < 0;
   }

   @Override
   public boolean isFull() {
      return top == array.length - 1;
   }

   @Override
   public void push(T element) throws StackOverflowException {

      if (isFull()) {
         throw new StackOverflowException();
      }
      if (element != null) {
         array[++top] = element;
      }
   }

   @Override
   public T pop() throws StackUnderflowException {

      if (isEmpty()) {
         throw new StackUnderflowException();
      }

      return array[top--];

   }

}
