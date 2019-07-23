package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

    @Override
    public void sort(T[] array, int leftIndex, int rightIndex) {

        if (this.canBeSorted(array, leftIndex, rightIndex)) {
            for (int i = leftIndex; i <= rightIndex; i++) {
                this.insertion(array, i, leftIndex);
            }
        }

    }

    private void insertion(T[] array, int index, int leftIndex) {

        while (index > leftIndex && array[index].compareTo(array[index - 1]) < 0) {
            Util.swap(array, index, index - 1);
            index -= 1;
        }

    }

    private boolean canBeSorted(T[] array, int leftIndex, int rightIndex) {
        boolean result = true;

        result = !this.hasNulls(array);

        if (array == null || array.length <= 0) {
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
