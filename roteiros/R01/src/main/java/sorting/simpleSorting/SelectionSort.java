package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

    @Override
    public void sort(T[] array, int leftIndex, int rightIndex) {

        if (this.canBeSorted(array, leftIndex, rightIndex)) {
            for (int i = leftIndex; i < rightIndex; i++) {
                int elementIndex = getSmallerElementIndex(array, i, rightIndex);
                Util.swap(array, i, elementIndex);
            }
        }

    }

    private int getSmallerElementIndex(T[] array, int leftIndex, int rightIndex) {
        int result = leftIndex;

        for (int i = leftIndex; i <= rightIndex; i++) {
            if (array[i].compareTo(array[result]) < 0) {
                result = i;
            }
        }

        return result;

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
