package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
        extends AbstractHashtableOpenAddress<T> {

    public HashtableOpenAddressQuadraticProbingImpl(int size,
                                                    HashFunctionClosedAddressMethod method, int c1, int c2) {
        super(size);
        hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
        this.initiateInternalTable(size);
    }

    @Override
    public void insert(T element) {
        if (element == null) return;

        this.insert(element, 0);
    }

    private void insert(T element, int probe) {
        if (probe < this.table.length) {
            int hash = this.getHash(element, probe);

            if (this.table[hash] == null || this.table[hash].equals(new DELETED())) {
                this.table[hash] = element;
                this.elements += 1;
                this.COLLISIONS += probe;

            } else if (this.table[hash].equals(element)) {

            } else {
                this.insert(element, probe + 1);

            }
        } else {
            throw new HashtableOverflowException();
        }
    }

    @Override
    public void remove(T element) {
        if (element == null) return;

        this.remove(element, 0);
    }

    private void remove(T element, int probe) {

        if (probe < this.table.length) {
            int hash = this.getHash(element, probe);

            if (this.table[hash] == null) {

            } else if (this.table[hash].equals(element)) {
                this.table[hash] = new DELETED();
                this.elements -= 1;
                this.COLLISIONS -= probe;

            } else {
                this.remove(element, probe + 1);
            }
        }
    }

    @Override
    public T search(T element) {
        if (element == null) return null;

        T result = null;

        if (indexOf(element) != -1) {
            result = element;
        }

        return result;
    }

    @Override
    public int indexOf(T element) {
        return indexOf(element, 0);
    }

    private int indexOf(T element, int probe) {
        int result = -1;

        if (probe < this.table.length) {
            int hash = this.getHash(element, probe);

            if (this.table[hash] == null) {

            } else if (this.table[hash].equals(element)) {
                result = hash;
            } else {
                result = this.indexOf(element, probe + 1);
            }
        }

        return result;
    }

    private int getHash(T element, int probe) {
        return this.getHashFunction().hash(element, probe);
    }

    @Override
    public HashFunctionQuadraticProbing getHashFunction() {
        return (HashFunctionQuadraticProbing) this.hashFunction;
    }
}
