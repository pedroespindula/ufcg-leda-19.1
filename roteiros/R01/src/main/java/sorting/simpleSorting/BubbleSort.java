package sorting.simpleSorting;

import sorting.AbstractSorting;
import util.Util;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is
 * sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

    @Override
    public void sort(T[] array, int leftIndex, int rightIndex) {

        if (this.canBeSorted(array, leftIndex, rightIndex)) {
            boolean changed = true;

            while (changed && leftIndex < rightIndex) {
                changed = this.bubbleStep(array, leftIndex, rightIndex);
                rightIndex -= 1;
            }
        }

    }

    private boolean bubbleStep(T[] array, int leftIndex, int rightIndex) {

        boolean changed = false;

        for (int i = leftIndex; i < rightIndex; i++) {

            if (array[i].compareTo(array[i + 1]) > 0) {
                Util.swap(array, i, i + 1);
                changed = true;
            }

        }

        return changed;
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
