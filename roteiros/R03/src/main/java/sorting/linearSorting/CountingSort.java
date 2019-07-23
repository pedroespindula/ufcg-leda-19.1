package sorting.linearSorting;

import sorting.AbstractSorting;

import java.util.Arrays;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 */
public class CountingSort extends AbstractSorting<Integer> {

    @Override
    public void sort(Integer[] array, int leftIndex, int rightIndex) {

        if (this.canBeSorted(array, leftIndex, rightIndex)) {
            this.countingSort(array, leftIndex, rightIndex);
        }

    }

    private void countingSort(Integer[] array, int leftIndex, int rightIndex) {

        int[] countArray = this.makeCountArray(array, leftIndex, rightIndex);
        this.positionElements(array, leftIndex, rightIndex, countArray);

    }

    private void positionElements(Integer[] array, int leftIndex, int rightIndex, int[] countArray) {

        Integer[] auxArray = Arrays.copyOfRange(array, leftIndex, rightIndex + 1);
        for (int i = auxArray.length - 1; i >= 0; i--) {
            positionElement(array, leftIndex, countArray, auxArray[i]);
        }

    }

    private void positionElement(Integer[] array, int leftIndex, int[] countArray, Integer e) {
        countArray[e] -= 1;
        int c = countArray[e];
        array[c + leftIndex] = e;
    }

    private int[] makeCountArray(Integer[] array, int leftIndex, int rightIndex) {
        Integer max = this.getMax(array, leftIndex, rightIndex);
        int[] countArray = new int[max + 1];

        countOccurences(array, leftIndex, rightIndex, countArray);
        accumulateOccurrences(countArray);

        return countArray;
    }

    private void accumulateOccurrences(int[] countArray) {
        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }
    }

    private void countOccurences(Integer[] array, int leftIndex, int rightIndex, int[] countArray) {
        for (int i = leftIndex; i <= rightIndex; i++) {
            countArray[array[i]] += 1;
        }
    }

    private Integer getMax(Integer[] array, int leftIndex, int rightIndex) {

        Integer max = array[leftIndex];

        for (int i = leftIndex + 1; i <= rightIndex; i++) {
            if (max.compareTo(array[i]) < 0) {
                max = array[i];
            }
        }

        return max;
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

        result = result && !this.hasUnsortableElements(array);

        return result;
    }

    private boolean hasUnsortableElements(Integer[] array) {
        boolean result = false;

        int i = 0;

        while (!result && i < array.length) {
            if (array[i] == null || array[i] < 0) {
                result = true;
            }
            i++;
        }

        return result;
    }


}
