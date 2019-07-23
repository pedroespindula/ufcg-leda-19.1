package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

   @Override
   public void sort(T[] array, int leftIndex, int rightIndex) {

      if (canBeSorted(array, leftIndex, rightIndex)) {
         quickSort(array, leftIndex, rightIndex);
      }

   }

   private void quickSort(T[] array, int leftIndex, int rightIndex) {

      if (leftIndex < rightIndex) {
         int partitionIndex = partition(array, leftIndex, rightIndex);

         quickSort(array, leftIndex, partitionIndex - 1);
         quickSort(array, partitionIndex + 1, rightIndex);
      }
   }

   private int partition(T[] array, int leftIndex, int rightIndex) {
      return partition(array, leftIndex, rightIndex, leftIndex, leftIndex + 1);
   }

   private int partition(T[] array, int leftIndex, int rightIndex, int i, int j) {

      int result = i;

      if (j <= rightIndex) {

         if (array[leftIndex].compareTo(array[j]) > 0) {
            i += 1;
            if (i != j) {
               Util.swap(array, i, j);
            }
         }
         j += 1;
         result = partition(array, leftIndex, rightIndex, i, j);
      } else {
         Util.swap(array, leftIndex, i);
      }

      return result;
   }

   private boolean canBeSorted(T[] array, int leftIndex, int rightIndex) {
      boolean result = true;

      result = !this.hasNulls(array);

      if (array == null || array.length <= 1) {
         result = false;
      } else if (leftIndex >= rightIndex || leftIndex < 0) {
         result = false;
      } else if (rightIndex > array.length) {
         result = false;
      }

      return result;
   }

   private boolean hasNulls(T[] array) {
      boolean result = false;

      int i = 0;

      while (!result && i < array.length) {
         if (array[i] == null) {
            result = true;
         }
         i++;
      }

      return result;
   }

}
