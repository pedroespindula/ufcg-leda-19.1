package sorting.divideAndConquer;

import sorting.AbstractSorting;

import java.util.Arrays;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

		if (canBeSorted(array, leftIndex, rightIndex)) {
			mergeSort(array, leftIndex, rightIndex);
		}

	}

	private void mergeSort(T[] array, int leftIndex, int rightIndex)  {

		if (leftIndex < rightIndex) {

			int middleIndex = (leftIndex + rightIndex) / 2;

			mergeSort(array, leftIndex, middleIndex);
			mergeSort(array, middleIndex + 1, rightIndex);

			merge(array, leftIndex, rightIndex);

		}

	}

	private void merge(T[] array, int startingIndex, int endIndex) {

		int dividerIndex = (startingIndex + endIndex) / 2;
		T[] auxArray = Arrays.copyOf(array, array.length);

		merge(array, auxArray, startingIndex, startingIndex, startingIndex, endIndex, dividerIndex + 1);

	}

	private void merge(T[] array, T[] auxArray, int currentArrayIndex, int startingIndex, int startCounter, int endIndex, int endCounter) {

		int dividerIndex = (startingIndex + endIndex) / 2;

		if (startCounter <= dividerIndex && endCounter <= endIndex) {

			T smallerElement;
			if (auxArray[startCounter].compareTo(auxArray[endCounter]) < 0) {
				smallerElement = auxArray[startCounter++];

			} else {
				smallerElement = auxArray[endCounter++];

			}

			array[currentArrayIndex++] = smallerElement;
			merge(array, auxArray, currentArrayIndex, startingIndex, startCounter, endIndex, endCounter);

		} else if (startCounter > dividerIndex && endCounter <= endIndex) {

			array[currentArrayIndex++] = auxArray[endCounter++];
			merge(array, auxArray, currentArrayIndex, startingIndex, startCounter, endIndex, endCounter);

		} else if (startCounter <= dividerIndex && endCounter > endIndex) {

			array[currentArrayIndex++] = auxArray[startCounter++];
			merge(array, auxArray, currentArrayIndex, startingIndex, startCounter, endIndex, endCounter);

		}

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
