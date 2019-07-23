package adt.bt;

import adt.bst.BSTNode;

public class Util {

	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
	  BSTNode<T> parent = getParent(node);
	  BSTNode<T> right = getRight(node);
	  BSTNode<T> aux = getLeft(right);

	  right.setLeft(node);
    node.setRight(aux);

	  return commonRotation(node, parent, right, aux);
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
		BSTNode<T> parent = getParent(node);
		BSTNode<T> left = getLeft(node);
		BSTNode<T> aux = getRight(left);

		left.setRight(node);
		node.setLeft(aux);

    return commonRotation(node, parent, left, aux);
  }

  private static <T extends Comparable<T>> BSTNode<T> commonRotation(BSTNode<T> node, BSTNode<T> parent, BSTNode<T> newRoot, BSTNode<T> aux) {
    newRoot.setParent(parent);
    node.setParent(newRoot);
    aux.setParent(node);

    if (!parent.isEmpty() && getLeft(parent).equals(node)) {
      parent.setLeft(newRoot);
    } else if (!parent.isEmpty() && getRight(parent).equals(node)) {
      parent.setRight(newRoot);
    }

    return newRoot;
  }

  private static <T extends Comparable<T>> BSTNode<T> getParent(BSTNode<T> node) {
		return (BSTNode<T>) node.getParent();
	}

	private static <T extends Comparable<T>> BSTNode<T> getRight(BSTNode<T> node) {
		return (BSTNode<T>) node.getRight();
	}

	private static <T extends Comparable<T>> BSTNode<T> getLeft(BSTNode<T> node) {
		return (BSTNode<T>) node.getLeft();
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size) {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Comparable[size];
		return array;
	}
}
