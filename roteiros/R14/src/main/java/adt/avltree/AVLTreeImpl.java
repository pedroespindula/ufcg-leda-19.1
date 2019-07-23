package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 *
 * Performs consistency validations within a AVL Tree instance
 *
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

  @Override
	public void insert(T element) {
    super.insert(element);
    BSTNode<T> node = search(element);
    rebalanceUp(node);
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(element);

  	if (!node.isEmpty()) {
			BSTNode<T> parent = getParent(node);
  	  super.remove(node);

  	  if (!parent.isEmpty()) {
				rebalanceUp(parent);
			} else {
  	  	rebalanceUp(this.root);
			}
		}
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
  	int result = 0;

    if (!node.isEmpty()) {
      result = height(getLeft(node)) - height(getRight(node));
		}

    return result;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
    BSTNode<T> result = node;
    boolean isRoot = node.getParent().isEmpty();

  	if (isFirstCase(node)) {
  		if (isThirdCase(node)) {
  			Util.rightRotation(getRight(node));
			}

			result = Util.leftRotation(node);
		} else if (isSecondCase(node)) {
  		if (isFourthCase(node)) {
				Util.leftRotation(getLeft(node));
			}

			result = Util.rightRotation(node);
		}

  	if (isRoot) {
  		this.root = result;
		}

	}

	private boolean isFirstCase(BSTNode<T> node) {
		return calculateBalance(node) < -1;
	}

	private boolean isSecondCase(BSTNode<T> node) {
		return calculateBalance(node) > 1;
	}

	private boolean isThirdCase(BSTNode<T> node) {
		return calculateBalance(getRight(node)) > 0;
	}

	private boolean isFourthCase(BSTNode<T> node) {
		return  calculateBalance(getLeft(node)) < 0;
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
    if (!node.isEmpty()) {
    	rebalance(node);
    	rebalanceUp(getParent(node));
		}
	}
}
