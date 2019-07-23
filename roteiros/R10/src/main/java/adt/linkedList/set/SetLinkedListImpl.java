package adt.linkedList.set;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T> {

   @Override
   public void insert(T element) {
      super.insert(element);
      removeDuplicates();
   }

   @Override
   public void removeDuplicates() {
      SingleLinkedListNode<T> aux = this.getHead();

      while (!aux.isNIL()) {
         SingleLinkedListNode<T> next = aux.getNext();

         while (!next.isNIL()) {
            if (aux.getData().equals(next.getData())) {
               next.setData(next.getNext().getData());
               next.setNext(next.getNext().getNext());
               this.size -= 1;
            } else {
               next = next.getNext();
            }
         }

         aux = aux.getNext();
      }
   }

   @Override
   public SetLinkedList<T> union(SetLinkedList<T> otherSet) {
      SetLinkedList<T> result = new SetLinkedListImpl<>();

      result.concatenate(this);
      result.concatenate(otherSet);

      result.removeDuplicates();

      return result;
   }

   @Override
   public SetLinkedList<T> intersection(SetLinkedList<T> otherSet) {
      SingleLinkedListNode<T> aux = this.getHead();
      SetLinkedList<T> result = new SetLinkedListImpl<>();

      while (!aux.isNIL()) {
         for (T e : otherSet.toArray()) {
            if (aux.getData().equals(e)) {
               result.insert(e);
            }
         }
         aux = aux.getNext();
      }
      result.removeDuplicates();

      return result;
   }

   @Override
   public void concatenate(SetLinkedList<T> otherSet) {
      T[] array = otherSet.toArray();

      for (T e : array) {
         this.insert(e);
      }

      removeDuplicates();
   }

}
