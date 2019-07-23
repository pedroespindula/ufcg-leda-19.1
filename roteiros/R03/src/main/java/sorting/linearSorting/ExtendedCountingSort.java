package sorting.linearSorting;

import sorting.AbstractSorting;

import java.util.Arrays;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {


    @Override
    public void sort(Integer[] array, int leftIndex, int rightIndex) {

        if (this.canBeSorted(array, leftIndex, rightIndex)) {
            this.countingSort(array, leftIndex, rightIndex);
        }

    }

    private void countingSort(Integer[] array, int leftIndex, int rightIndex) {

        Integer[] bounds = getBounds(array, leftIndex, rightIndex);

        Integer min = bounds[0];
        Integer max = bounds[1];

        int[] countArray = this.makeCountArray(array, leftIndex, rightIndex, min, max);
        this.positionElements(array, leftIndex, rightIndex, countArray, min);

    }

    private void positionElements(Integer[] array, int leftIndex, int rightIndex, int[] countArray, Integer min) {

        Integer[] auxArray = Arrays.copyOfRange(array, leftIndex, rightIndex + 1);
        for (int i = auxArray.length - 1; i >= 0; i--) {
            positionElement(array, leftIndex, countArray, min, auxArray[i]);
        }

    }

    private void positionElement(Integer[] array, int leftIndex, int[] countArray, Integer min, Integer element) {
        int countPosition = element - min;
        countArray[countPosition] -= 1;
        int finalPosition = countArray[countPosition] + leftIndex;
        array[finalPosition] = element;
    }

    private int[] makeCountArray(Integer[] array, int leftIndex, int rightIndex, Integer min, int max) {
        int arrayLength = max - min + 1;

        int[] countArray = new int[arrayLength];

        countOccurrences(array, leftIndex, rightIndex, min, countArray);
        accumulateOccurrences(countArray);

        return countArray;
    }

    private void accumulateOccurrences(int[] countArray) {
        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }
    }

    private void countOccurrences(Integer[] array, int leftIndex, int rightIndex, Integer min, int[] countArray) {
        for (int i = leftIndex; i <= rightIndex; i++) {
            countArray[array[i] - min] += 1;
        }
    }

    private Integer[] getBounds(Integer[] array, int leftIndex, int rightIndex) {

        Integer max = array[leftIndex];
        Integer min = array[leftIndex];

        for (int i = leftIndex + 1; i <= rightIndex; i++) {
            if (max.compareTo(array[i]) < 0) {
                max = array[i];
            }
            if (min.compareTo(array[i]) > 0) {
                min = array[i];
            }
        }

        return new Integer[]{min, max};
    }


    private boolean canBeSorted(Integer[] array, int leftIndex, int rightIndex) {
        boolean result = true;

        if (array == null || array.length <= 1) {
            result = false;
        } else if (leftIndex >= rightIndex || leftIndex < 0) {
            result = false;
        } else if (rightIndex > array.length) {
            result = false;
        }

        result = result && !this.hasUnsortablElements(array);

        return result;
    }

    private boolean hasUnsortablElements(Integer[] array) {
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
