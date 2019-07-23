package adt.bst.extended;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.*;

public class SortComparatorBSTImplTest {

  @Test
  public void testSort() {
    Integer[] array = new Integer[]{2, 4, 1, 20, 34, -1, 34};
    Integer[] result = Arrays.copyOf(array, array.length);

    SortComparatorBSTImpl tree = new SortComparatorBSTImpl<Integer>(Comparator.comparingInt(a -> a));

    Arrays.sort(result);
    assertArrayEquals(result, tree.sort(array));
  }

  @Test
  public void testReverseOrder() {
    Integer[] array = new Integer[]{2, 4, 1, 20, 34, -1, 34};
    Integer[] result = Arrays.copyOf(array, array.length);

    SortComparatorBSTImpl tree = new SortComparatorBSTImpl<Integer>(Comparator.comparingInt(a -> a));

    Arrays.sort(result);
    assertArrayEquals(result, tree.sort(array));
  }
}
