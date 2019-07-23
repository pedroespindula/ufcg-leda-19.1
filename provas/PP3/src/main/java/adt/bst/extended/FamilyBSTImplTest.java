package adt.bst.extended;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FamilyBSTImplTest {

  private FamilyBST<Integer> tree;

  @Before
  public void init() {
    tree = new FamilyBSTImpl<>();

    tree.insert(8);

    tree.insert(4);
    tree.insert(12);

    tree.insert(2);
    tree.insert(6);
    tree.insert(10);
    tree.insert(14);

    tree.insert(1);
    tree.insert(3);
    tree.insert(5);
    tree.insert(7);
    tree.insert(9);
    tree.insert(11);
    tree.insert(13);
    tree.insert(15);
  }

  @Test
  public void testPrimosPrimeiroGrau() {
    assertTrue(tree.primosPrimeiroGrau(1, 5));
    assertTrue(tree.primosPrimeiroGrau(1, 7));
    assertTrue(tree.primosPrimeiroGrau(9, 13));
    assertTrue(tree.primosPrimeiroGrau(9, 15));
  }

  @Test
  public void testIrmaosNaoPrimosPrimeiroGrau() {
    assertFalse(tree.primosPrimeiroGrau(1, 3));
    assertFalse(tree.primosPrimeiroGrau(9, 11));
  }

  @Test
  public void testMesmoNoNaoPrimosPrimeiroGrau() {
    assertFalse(tree.primosPrimeiroGrau(1, 1));
    assertFalse(tree.primosPrimeiroGrau(9, 9));
  }

  @Test
  public void testSemAlturaNaoPrimosPrimeiroGrau() {
    assertFalse(tree.primosPrimeiroGrau(4, 12));
    assertFalse(tree.primosPrimeiroGrau(8, 12));
  }

  @Test
  public void testPrimosSegundoGrau() {
    assertTrue(tree.primosSegundoGrau(1, 10));
    assertTrue(tree.primosSegundoGrau(1, 14));
  }

  @Test
  public void testComutatividadePrimosSegundoGrau() {
    assertTrue(tree.primosSegundoGrau(9, 2));
    assertTrue(tree.primosSegundoGrau(9, 6));
    assertTrue(tree.primosSegundoGrau(2, 9));
    assertTrue(tree.primosSegundoGrau(6, 9));
  }

  @Test
  public void testPassandoNull() {
    assertFalse(tree.primosPrimeiroGrau(null, 1));
  }

  @Test
  public void testPassandoElementosQueNaoExistem() {
    assertFalse(tree.primosPrimeiroGrau(16, 1));
  }
}