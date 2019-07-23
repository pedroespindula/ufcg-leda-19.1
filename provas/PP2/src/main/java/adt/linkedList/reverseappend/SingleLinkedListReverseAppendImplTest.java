package adt.linkedList.reverseappend;

import org.junit.Test;

import static org.junit.Assert.*;

public class SingleLinkedListReverseAppendImplTest {

  @Test
  public void test() {
    SingleLinkedListReverseAppendImpl<Integer> list = new SingleLinkedListReverseAppendImpl<>();

    assertEquals("NIL", list.toString());

    list.doIt(1);
    assertEquals("1 NIL", list.toString());

    list.doIt(2);
    assertEquals("2 1 NIL", list.toString());

    list.doIt(3);
    assertEquals("3 1 2 NIL", list.toString());

    list.doIt(4);
    assertEquals("4 2 1 3 NIL", list.toString());

    list.doIt(5);
    assertEquals("5 3 1 2 4 NIL", list.toString());

    list.doIt(6);
    assertEquals("6 4 2 1 3 5 NIL", list.toString());

  }

}