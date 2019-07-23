package adt.bst;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

  protected BSTNode<T> root;

  public BSTImpl() {
    root = new BSTNode<>();
  }

  public BSTNode<T> getRoot() {
    return this.root;
  }

  @Override
  public boolean isEmpty() {
    return root.isEmpty();
  }

  @Override
  public int height() {
    return this.height(this.root);
  }

  private int height(BSTNode<T> node) {
    int result = -1;

    if (!node.isEmpty()) {
      result = 1 + Math.max(this.height(getLeft(node)), this.height(getRight(node)));
    }

    return result;
  }

  protected BSTNode<T> getRight(BSTNode<T> node) {
    return (BSTNode<T>) node.getRight();
  }

  protected BSTNode<T> getLeft(BSTNode<T> node) {
    return (BSTNode<T>) node.getLeft();
  }

  @Override
  public BSTNode<T> search(T element) {
    return this.search(element, this.root);
  }

  private BSTNode<T> search(T element, BSTNode<T> node) {
    BSTNode<T> result = new BSTNode<>();

    if (element != null && !node.isEmpty()) {
      if (element.compareTo(node.getData()) < 0) {
        result = this.search(element, getLeft(node));
      } else if (element.compareTo(node.getData()) > 0) {
        result = this.search(element, getRight(node));
      } else {
        result = node;
      }
    }

    return result;
  }

  @Override
  public void insert(T element) {
    if (element != null) {
      insert(element, this.root);
    }
  }

  private void insert(T element, BSTNode<T> node) {
    if (node.isEmpty()) {

      node.setData(element);
      node.setLeft(getEmptyNodeWithParent(node));
      node.setRight(getEmptyNodeWithParent(node));

    } else {
      if (element.compareTo(node.getData()) < 0) {
        this.insert(element, getLeft(node));
      } else {
        this.insert(element, getRight(node));
      }
    }
  }

  protected BSTNode getEmptyNodeWithParent(BSTNode<T> node) {
    return new BSTNode.Builder<T>().parent(node).build();
  }

  @Override
  public BSTNode<T> maximum() {
    BSTNode<T> result = null;

    if (!this.root.isEmpty()) {
      result = maximum(this.root);
    }

    return result;
  }

  private BSTNode<T> maximum(BSTNode<T> node) {
    BSTNode<T> result = node;

    if (!getRight(node).isEmpty()) {
      result = maximum(getRight(node));
    }

    return result;
  }

  @Override
  public BSTNode<T> minimum() {
    BSTNode<T> result = null;

    if (!this.root.isEmpty()) {
      result = minimum(this.root);
    }

    return result;
  }

  private BSTNode<T> minimum(BSTNode<T> node) {
    BSTNode<T> result = node;

    if (!getLeft(node).isEmpty()) {
      result = minimum(getLeft(node));
    }

    return result;
  }

  @Override
  public BSTNode<T> sucessor(T element) {
    BSTNode<T> result = null;
    BSTNode<T> node = search(element);

    if (getRight(node) != null && !getRight(node).isEmpty()) {
      result = this.minimum(getRight(node));
    } else {
      result = this.getParentBigger(node);
    }

    return result;
  }

  private BSTNode<T> getParentBigger(BSTNode<T> node) {
    return getParentBigger(node, node);
  }

  private BSTNode<T> getParentBigger(BSTNode<T> node, BSTNode<T> actual) {
    BTNode<T> result = null;

    if (hasParent(actual)) {
      if (getParent(actual).getData().compareTo(node.getData()) > 0) {
        result = getParent(actual);
      } else {
        result = this.getParentBigger(node, getParent(actual));
      }
    }

    return (BSTNode<T>) result;
  }

  private BSTNode<T> getParent(BSTNode<T> actual) {
    return (BSTNode<T>) actual.getParent();
  }

  private boolean hasParent(BSTNode<T> node) {
    return node.getParent() != null;
  }

  @Override
  public BSTNode<T> predecessor(T element) {
    BSTNode<T> result = null;
    BSTNode<T> node = search(element);

    if (getLeft(node) != null && !getLeft(node).isEmpty()) {
      result = this.maximum(getLeft(node));
    } else {
      result = this.getParentSmaller(node);
    }

    return result;
  }

  private BSTNode<T> getParentSmaller(BSTNode<T> node) {
    return getParentSmaller(node, node);
  }

  private BSTNode<T> getParentSmaller(BSTNode<T> node, BSTNode<T> actual) {
    BTNode<T> result = null;

    if (hasParent(actual)) {
      if (getParent(actual).getData().compareTo(node.getData()) < 0) {
        result = getParent(actual);
      } else {
        result = this.getParentSmaller(node, getParent(actual));
      }
    }

    return (BSTNode<T>) result;
  }

  @Override
  public void remove(T element) {
    BSTNode<T> node = search(element);
    remove(node);
  }

  private void remove(BSTNode<T> node) {
    if (node.isLeaf()) {
      node.setData(null);
    } else {
      BSTNode<T> aux;

      if (bothChildren(node)) {
        aux = sucessor(node.getData());

        node.setData(aux.getData());
        remove(aux);
      } else {
        if (leftChildren(node)) {
          aux = getLeft(node);
        } else {
          aux = getRight(node);
        }

        node.setData(aux.getData());
        node.setLeft(getLeft(aux));
        node.setRight(getRight(aux));
      }

    }
  }

  private boolean leftChildren(BSTNode<T> node) {
    return !getLeft(node).isEmpty() && getRight(node).isEmpty();
  }

  private boolean bothChildren(BSTNode<T> node) {
    return !getRight(node).isEmpty() && !getLeft(node).isEmpty();
  }

  @Override
  public T[] preOrder() {
    T[] array = (T[]) new Comparable[this.size()];
    preOrder(array, 0, this.root);
    return array;
  }

  private int preOrder(T[] array, int i, BSTNode<T> node) {

    if (!node.isEmpty()) {
      array[i++] = node.getData();

      i = preOrder(array, i, getLeft(node));
      i = preOrder(array, i, getRight(node));
    }

    return i;
  }

  @Override
  public T[] order() {
    T[] array = (T[]) new Comparable[this.size()];
    order(array, 0, this.root);
    return array;
  }

  private int order(T[] array, int i, BSTNode<T> node) {

    if (!node.isEmpty()) {
      i = order(array, i, getLeft(node));
      array[i++] = node.getData();
      i = order(array, i, getRight(node));
    }

    return i;
  }

  @Override
  public T[] postOrder() {
    T[] array = (T[]) new Comparable[this.size()];
    postOrder(array, 0, this.root);
    return array;
  }

  private int postOrder(T[] array, int i, BSTNode<T> node) {

    if (!node.isEmpty()) {
      i = postOrder(array, i, getLeft(node));
      i = postOrder(array, i, getRight(node));
      array[i++] = node.getData();
    }

    return i;
  }

  /**
   * This method is already implemented using recursion. You must understand
   * how it work and use similar idea with the other methods.
   */
  @Override
  public int size() {
    return size(root);
  }

  private int size(BSTNode<T> node) {
    int result = 0;
    // base case means doing nothing (return 0)
    if (!node.isEmpty()) { // indusctive case
      result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
    }
    return result;
  }

}
