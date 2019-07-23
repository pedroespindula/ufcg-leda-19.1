package adt.hashtable.closed;

import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;
import util.Util;

import java.util.LinkedList;

public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {

  /**
   * A hash table with closed address works with a hash function with closed
   * address. Such a function can follow one of these methods: DIVISION or
   * MULTIPLICATION. In the DIVISION method, it is useful to change the size
   * of the table to an integer that is prime. This can be achieved by
   * producing such a prime number that is bigger and close to the desired
   * size.
   * <p>
   * For doing that, you have auxiliary methods: Util.isPrime and
   * getPrimeAbove as documented bellow.
   * <p>
   * The length of the internal table must be the immediate prime number
   * greater than the given size (or the given size, if it is already prime).
   * For example, if size=10 then the length must
   * be 11. If size=20, the length must be 23. You must implement this idea in
   * the auxiliary method getPrimeAbove(int size) and use it.
   *
   * @param desiredSize
   * @param method
   */

  @SuppressWarnings({"rawtypes", "unchecked"})
  public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
    int realSize = desiredSize;

    if (method == HashFunctionClosedAddressMethod.DIVISION) {
      realSize = this.getPrimeAbove(desiredSize); // real size must the
      // the immediate prime
      // above
    }
    initiateInternalTable(realSize);
    HashFunctionClosedAddress<T> function = (HashFunctionClosedAddress<T>) HashFunctionFactory.createHashFunction(method, realSize);
    this.hashFunction = function;
  }

  // AUXILIARY

  /**
   * It returns the prime number that is closest (and greater) to the given
   * number.
   * If the given number is prime, it is returned.
   * You can use the method Util.isPrime to check if a number is
   * prime.
   */
  int getPrimeAbove(int number) {
    int result = number + 1;

    if (Util.isPrime(result)) {
      return result;
    }

    return getPrimeAbove(result);
  }

  @Override
  public void insert(T element) {
    if (element == null || this.indexOf(element) != -1) return;

    int hash = getHash(element);

    if (this.getLinkedList(hash) == null) {
      this.setLinkedList(hash);
    } else {
      this.COLLISIONS += 1;
    }

    this.getLinkedList(hash).add(element);
    this.elements += 1;
  }

  @Override
  public void remove(T element) {
    if (element == null || this.indexOf(element) == -1) return;

    int hash = this.getHash(element);

    this.getLinkedList(hash).remove(element);

    if (!this.getLinkedList(hash).isEmpty()) {
      this.COLLISIONS -= 1;
    }

    this.elements -= 1;

  }

  @Override
  public T search(T element) {
    if (element == null) return null;

    T result = null;

    if (this.indexOf(element) != -1) {
      result = element;
    }

    return result;
  }

  @Override
  public int indexOf(T element) {
    if (element == null) return -1;

    int result = -1;

    int hash = this.getHash(element);

    if (this.getLinkedList(hash) != null) {
      boolean contains = this.getLinkedList(hash).contains(element);

      if (contains) {
        result = hash;
      }
    }

    return result;
  }

  @Override
  public HashFunctionClosedAddress<T> getHashFunction() {
    return (HashFunctionClosedAddress<T>) this.hashFunction;
  }

  private void setLinkedList(int hash) {
    this.table[hash] = new LinkedList<>();
  }

  private LinkedList<T> getLinkedList(int hash) {
    return (LinkedList<T>) this.table[hash];
  }

  private int getHash(T element) {
    return this.getHashFunction().hash(element);
  }

}
