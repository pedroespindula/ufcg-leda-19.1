package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T>
        implements RBTree<T> {

  public RBTreeImpl() {
    this.root = new RBNode<T>();
  }

  protected int blackHeight() {
    return blackHeight(getRoot());
  }

  private int blackHeight(RBNode<T> node) {
    int result = 0;

    if (!node.isEmpty()) {
      if (isBlack(node)) {
        result = 1 + blackHeight(getLeft(node));
      } else {
        result = blackHeight(getLeft(node));
      }
    }

    return result;
  }

  protected boolean verifyProperties() {
    boolean resp = verifyNodesColour() && verifyNILNodeColour()
            && verifyRootColour() && verifyChildrenOfRedNodes()
            && verifyBlackHeight();

    return resp;
  }

  /**
   * The colour of each node of a RB tree is black or red. This is guaranteed
   * by the type Colour.
   */
  private boolean verifyNodesColour() {
    return true; // already implemented
  }

  /**
   * The colour of the root must be black.
   */
  private boolean verifyRootColour() {
    return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
    // implemented
  }

  /**
   * This is guaranteed by the constructor.
   */
  private boolean verifyNILNodeColour() {
    return true; // already implemented
  }

  /**
   * Verifies the property for all RED nodes: the children of a red node must
   * be BLACK.
   */
  private boolean verifyChildrenOfRedNodes() {
    return verifyChildrenOfRedNodes(getRoot());
  }

  private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
    boolean result = false;
    if (node.isEmpty()) {
      result = true;
    } else {

      if (isRed(node)) {
        result = !isRed(getLeft(node)) && !isRed(getRight(node));
        result = result && verifyChildrenOfRedNodes(getLeft(node)) && verifyChildrenOfRedNodes(getRight(node));
      } else {
        result = verifyChildrenOfRedNodes(getLeft(node)) && verifyChildrenOfRedNodes(getRight(node));
      }

    }

    return result;
  }

  /**
   * Verifies the black-height property from the root.
   */
  private boolean verifyBlackHeight() {
    return verifyBlackHeight(getRoot());
  }

  private boolean verifyBlackHeight(RBNode<T> node) {
    boolean result = true;

    if (!node.isEmpty()) {
      int leftHeight = blackHeight(getLeft(node));
      int rightHeight = blackHeight(getRight(node));

      result = leftHeight == rightHeight && verifyBlackHeight(getLeft(node)) && verifyBlackHeight(getRight(node));
    }

    return result;
  }

  @Override
  public void insert(T value) {
    if (value != null) {
      fixUpCase1(insert(value, getRoot(), new RBNode<>()));
    }
  }


  private RBNode<T> insert(T value, RBNode<T> node, RBNode<T> parent) {
    RBNode<T> result = null;
    if (node.isEmpty()) {
      createNewNode(value, node, parent);
      result = node;
    } else {
      if (value.compareTo(node.getData()) < 0) {
        result = insert(value, getLeft(node), node);
      } else if (value.compareTo(node.getData()) > 0) {
        result = insert(value, getRight(node), node);
      }
    }

    return result;
  }

  @Override
  public RBNode[] rbPreOrder() {
    RBNode<T>[] array = new RBNode[this.size()];
    rbPreOrder(array, 0, getRoot());
    return array;
  }

  private int rbPreOrder(RBNode<T>[] array, int i, RBNode<T> node) {

    if (!node.isEmpty()) {
      array[i++] = node;

      i = rbPreOrder(array, i, getLeft(node));
      i = rbPreOrder(array, i, getRight(node));
    }

    return i;
  }

  // FIXUP methods
  protected void fixUpCase1(RBNode<T> node) {
    if (isRoot(node)) {
      node.setColour(Colour.BLACK);
    } else {
      fixUpCase2(node);
    }
  }

  protected void fixUpCase2(RBNode<T> node) {
    if (isParentBlack(node)) {

    } else {
      fixUpCase3(node);
    }
  }

  protected void fixUpCase3(RBNode<T> node) {
    if (isUncleRed(node)) {
      getUncle(node).setColour(Colour.BLACK);
      getParent(node).setColour(Colour.BLACK);
      getGrandpa(node).setColour(Colour.RED);
      fixUpCase1(getGrandpa(node));
    } else {
      fixUpCase4(node);
    }
  }

  protected void fixUpCase4(RBNode<T> node) {
    if (isZigZagLeft(node)) {
      Util.leftRotation(getParent(node));
    } else if (isZigZagRight(node)) {
      Util.rightRotation(getParent(node));
    }
    fixUpCase5(node);
  }

  protected void fixUpCase5(RBNode<T> node) {
    if (isRightChildren(node)) {
      Util.leftRotation(getParent(node));
    } else {
      Util.rightRotation(getParent(node));
    }
  }

  private void createNewNode(T value, RBNode<T> newNode, RBNode<T> parent) {
    newNode.setData(value);
    newNode.setColour(Colour.RED);

    newNode.setLeft(new RBNode<>());
    newNode.setRight(new RBNode<>());
    newNode.setParent(parent);

    newNode.getLeft().setParent(newNode);
    newNode.getRight().setParent(newNode);
  }

  @Override
  public RBNode<T> getRoot() {
    return (RBNode<T>) this.root;
  }

  private RBNode<T> getLeft(RBNode<T> node) {
    return (RBNode<T>) node.getLeft();
  }

  private RBNode<T> getRight(RBNode<T> node) {
    return (RBNode<T>) node.getRight();
  }

  private RBNode<T> getParent(RBNode<T> node) {
    return (RBNode<T>) node.getParent();
  }
  private RBNode<T> getGrandpa(RBNode<T> node) {
    return getParent(getParent(node));
  }

  private RBNode<T> getUncle(RBNode<T> node) {
    RBNode<T> result = null;

    RBNode<T> parent = getParent(node);
    RBNode<T> grandpa = getGrandpa(node);

    if (isRightChildren(parent)) {
      result = getLeft(grandpa);
    } else {
      result = getRight(grandpa);
    }

    return result;
  }

  private boolean isRoot(RBNode<T> node) {
    return node.getParent().isEmpty();
  }

  private boolean isLeftChildren(RBNode<T> node) {
    return !isRightChildren(node);
  }

  private boolean isRightChildren(RBNode<T> node) {
    return node.getParent().getRight().equals(node);
  }

  private boolean isRed(RBNode<T> node) {
    return node.getColour() == Colour.RED;
  }

  private boolean isBlack(RBNode<T> node) {
    return node.getColour() == Colour.BLACK;
  }

  private boolean isParentBlack(RBNode<T> node) {
    return isBlack(getParent(node));
  }

  private boolean isUncleRed(RBNode<T> node) {
    return isRed(getUncle(node));
  }

  private boolean isZigZagLeft(RBNode<T> node) {
    return isRightChildren(node) && isLeftChildren(getParent(node));
  }

  private boolean isZigZagRight(RBNode<T> node) {
    return isLeftChildren(node) && isRightChildren(getParent(node));
  }

}
