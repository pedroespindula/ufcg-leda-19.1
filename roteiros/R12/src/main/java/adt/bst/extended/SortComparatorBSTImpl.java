package adt.bst.extended;

import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado.
 *
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;

	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			this.insert(element, this.root);
		}
	}

	private void insert(T element, BSTNode<T> node) {
		if (node.isEmpty()) {

			node.setData(element);
			node.setLeft(getEmptyNodeWithParent(node));
			node.setRight(getEmptyNodeWithParent(node));

		} else {
			if (comparator.compare(element, node.getData()) < 0) {
				this.insert(element, getLeft(node));
			} else {
				this.insert(element, getRight(node));
			}
		}
	}

	@Override
	public T[] sort(T[] array) {
		if (array != null) {
			for (T element : array) {
				this.insert(element);
			}

			array = this.order();
			this.root = new BSTNode<>();
		}

		return array;
	}

	@Override
	public T[] reverseOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		reverseOrder(array, 0, this.root);
		return array;
	}

	private int reverseOrder(T[] array, int i, BSTNode<T> node) {

		if (!node.isEmpty()) {
			i = reverseOrder(array, i, getRight(node));
			array[i++] = node.getData();
			i = reverseOrder(array, i, getLeft(node));
		}

		return i;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

}
