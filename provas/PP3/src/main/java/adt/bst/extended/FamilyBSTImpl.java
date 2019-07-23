package adt.bst.extended;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

public class FamilyBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements FamilyBST<T> {

  @Override
  public boolean primosPrimeiroGrau(T elem1, T elem2) {
    BSTNode<T> node1 = search(elem1);
    BSTNode<T> node2 = search(elem2);

    return primosPrimeiroGrau(node1, node2);
  }

  @Override
  public boolean primosSegundoGrau(T elem1, T elem2) {
    BSTNode<T> node1 = search(elem1);
    BSTNode<T> node2 = search(elem2);

    return primosSegundoGrau(node1, node2);
  }

  private boolean primosPrimeiroGrau(BSTNode<T> node1, BSTNode<T> node2) {
    boolean result = false;

    if (isValidNode(node1) && isValidNode(node2)) {
      BSTNode<T> parent1 = getParent(node1);
      BSTNode<T> parent2 = getParent(node2);

      if (isValidNode(parent1) && isValidNode(parent2)) {
        BSTNode<T> grandParent1 = getParent(parent1);
        BSTNode<T> grandParent2 = getParent(parent2);

        if (isValidNode(grandParent1) && isValidNode(grandParent2)) {
          result = !parent1.equals(parent2) && grandParent1.equals(grandParent2);
        }
      }
    }

    return result;
  }

  private boolean primosSegundoGrau(BSTNode<T> node1, BSTNode<T> node2) {
    boolean result = false;

    if (isValidNode(node1) && isValidNode(node2)) {
      result = primosPrimeiroGrau(getParent(node1), node2) || primosPrimeiroGrau(node1, getParent(node2));
    }

    return result;
  }

  private boolean isValidNode(BSTNode<T> node1) {
    return node1 != null && !node1.isEmpty();
  }

  private BSTNode<T> getParent(BSTNode<T> node) {
    return (BSTNode<T>) node.getParent();
  }

  /**
   * NAO ALTERAR OS METODOS ABAIXO PORQUE SERAO UTULIZADOS PELOS TESTES
   */
  @Override
  public void insert(T element) {
    insert(root, element);
  }

  protected void insert(BSTNode<T> node, T element) {
    if (node.isEmpty()) {
      node.setData(element);
      node.setLeft(new BSTNode<T>());
      node.getLeft().setParent(node);
      node.setRight(new BSTNode<T>());
      node.getRight().setParent(node);
    } else {
      if (element.compareTo(node.getData()) < 0) {
        insert((BSTNode<T>) node.getLeft(), element);
      } else if (element.compareTo(node.getData()) > 0) {
        insert((BSTNode<T>) node.getRight(), element);
      }
    }
  }

  @Override
  public BSTNode<T> search(T element) {
    return search(root, element);
  }

  protected BSTNode<T> search(BSTNode<T> node, T element) {
    BSTNode<T> result = node;
    if (element != null) {
      if (!node.isEmpty()) {
        if (element.compareTo(node.getData()) == 0) {
          result = node;
        } else if (element.compareTo(node.getData()) < 0) {
          result = search((BSTNode<T>) node.getLeft(), element);
        } else {
          result = search((BSTNode<T>) node.getRight(), element);
        }
      }
    }

    return result;
  }
}
