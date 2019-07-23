package adt.linkedList.reverseappend;

import adt.linkedList.SingleLinkedListNode;

/**
 * @param <T>
 * @author campelo
 * @see SingleLinkedListReverseAppend
 */
public class SingleLinkedListReverseAppendImpl<T> implements SingleLinkedListReverseAppend<T> {

  /*
   * Nao remover esse atributo nem criar outros
   */
  protected SingleLinkedListNode<T> head;

  /*
   * Nao modifique o construtor
   * @param head
   */
  public SingleLinkedListReverseAppendImpl() {
    head = new SingleLinkedListNode<T>();
  }

  /* (non-Javadoc)
   * @see adt.linkedList.reverseappend.SingleLinkedListReverseAppend#doIt(java.lang.Object)
   *
   * Implemente apenas este metodo de acordo com os comentários da interface.
   *
   */
  public void doIt(T elem) {

    // Inverte
    SingleLinkedListNode<T> previous = new SingleLinkedListNode<>();
    SingleLinkedListNode<T> aux = this.head;
    SingleLinkedListNode<T> next;

    while (!aux.isNIL()) {
      next = aux.getNext();

      aux.setNext(previous);
      previous = aux;
      aux = next;
    }

    // Criação do novo nó
    SingleLinkedListNode<T> newNode = new SingleLinkedListNode<>(elem, previous);

    // Troca o head
    this.head = newNode;

  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   * NAO REMOVA NEM MODIFIQUE ESTE METODO. ELE SERA USADO NOS TESTES!
   * NAO REMOVA NEM MODIFIQUE ESTE METODO. ELE SERA USADO NOS TESTES!
   * NAO REMOVA NEM MODIFIQUE ESTE METODO. ELE SERA USADO NOS TESTES!
   */
  @Override
  public String toString() {
    String retorno = "";
    SingleLinkedListNode<T> currentNode = this.head;
    while (currentNode != null) {
      if (!retorno.equals("")) {
        retorno += " ";
      }
      retorno += currentNode;
      currentNode = currentNode.getNext();
    }
    return retorno;
  }

}
