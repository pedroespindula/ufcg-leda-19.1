package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * This algorithm applies two bubblesorts simultaneously. In a same iteration, a
 * bubblesort pushes the greatest elements to the right and another bubblesort
 * pushes the smallest elements to the left. At the end of the first iteration,
 * the smallest element is in the first position (index 0) and the greatest
 * element is the last position (index N-1). The next iteration does the same
 * from index 1 to index N-2. And so on. The execution continues until the array
 * is completely ordered.
 */
public class SimultaneousBubblesort<T extends Comparable<T>> extends
        AbstractSorting<T> {

    @Override
    public void sort(T[] array, int leftIndex, int rightIndex) {

        if (this.canBeSorted(array, leftIndex, rightIndex)) {

            boolean changed = true;

            while (changed && leftIndex < rightIndex) {
                changed = this.bubbleStep(array, leftIndex, rightIndex);
                rightIndex -= 1;

                if (!changed) {
                    break;
                }
                changed = this.bubbleStepBackwards(array, leftIndex, rightIndex);
                leftIndex += 1;

            }
        }

    }

    private boolean bubbleStepBackwards(T[] array, int leftIndex, int rightIndex) {

        boolean changed = false;

        for (int i = rightIndex; i > leftIndex; i--) {

            if (array[i].compareTo(array[i - 1]) < 0) {
                Util.swap(array, i, i - 1);
                changed = true;
            }

        }

        return changed;
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
