package adt.avltree;

import adt.bst.BSTNode;
import adt.bt.Util;

import java.util.*;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {

	}

	@Override
	protected void rebalance(BSTNode<T> node) {
		BSTNode<T> result = node;
		boolean isRoot = node.getParent().isEmpty();

		if (isFirstCase(node)) {
			if (isThirdCase(node)) {
				Util.rightRotation(getRight(node));
				result = Util.leftRotation(node);
				this.RLcounter++;
			} else {
				result = Util.leftRotation(node);
				this.RRcounter++;
			}
		} else if (isSecondCase(node)) {
			if (isFourthCase(node)) {
				Util.leftRotation(getLeft(node));
				result = Util.rightRotation(node);
				this.LRcounter++;
			} else {
				result = Util.rightRotation(node);
				this.LLcounter++;
			}
		}

		if (isRoot) {
			this.root = result;
		}

	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		T[] copy = Arrays.copyOf(array, array.length + this.size());

		fillArray(copy, array.length);
		Arrays.sort(copy);

		bfsIteration(copy);
	}

	private void bfsIteration(T[] array) {
		List<T[]> list = new ArrayList<>();
		list.add(array);
		bfsIteration(list);
	}

	private void bfsIteration(List<T[]> list) {
		T[] current = list.remove(0);

		if (current.length != 0) {

			int mid = current.length / 2;
			this.insert(current[mid]);

			list.add(Arrays.copyOfRange(current, 0, mid));
			list.add(Arrays.copyOfRange(current, mid + 1, current.length));

			bfsIteration(list);
		}
	}

	private void fillArray(T[] copy, int index) {
		if (!isEmpty()) {
			copy[index] = this.removeLeaf();
			fillArray(copy, index + 1);
		}
	}

	private T removeLeaf() {
		return removeLeaf(this.root);
	}

	private T removeLeaf(BSTNode<T> node) {
		T result = node.getData();

		if (node.isLeaf()) {
			this.remove(result);
		} else {
			if (height(getLeft(node)) > height(getRight(node))) {
				result = removeLeaf(getLeft(node));
			} else {
				result = removeLeaf(getRight(node));
			}
		}

		return result;
	}

}
