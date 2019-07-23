package sorting.divideAndConquer.threeWayQuicksort;

import sorting.AbstractSorting;
import util.Util;

public class ThreeWayQuickSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * No algoritmo de quicksort, selecionamos um elemento como pivot,
	 * particionamos o array colocando os menores a esquerda do pivot 
	 * e os maiores a direita do pivot, e depois aplicamos a mesma estrategia 
	 * recursivamente na particao a esquerda do pivot e na particao a direita do pivot. 
	 * 
	 * Considerando um array com muitoe elementos repetidos, a estrategia do quicksort 
	 * pode ser otimizada para lidar de forma mais eficiente com isso. Essa melhoria 
	 * eh conhecida como quicksort tree way e consiste da seguinte ideia:
	 * - selecione o pivot e particione o array de forma que
	 *   * arr[l..i] contem elementos menores que o pivot
	 *   * arr[i+1..j-1] contem elementos iguais ao pivot.
	 *   * arr[j..r] contem elementos maiores do que o pivot. 
	 *   
	 * Obviamente, ao final do particionamento, existe necessidade apenas de ordenar
	 * as particoes contendo elementos menores e maiores do que o pivot. Isso eh feito
	 * recursivamente. 
	 **/
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {

	    if (canBeSorted(array, leftIndex, rightIndex)) {
	    	threeWayQuickSort(array, leftIndex, rightIndex);
		}
	}

	private void threeWayQuickSort(T[] array, int leftIndex, int rightIndex) {

		if (leftIndex < rightIndex) {
			int[] partitionResult = partition(array, leftIndex, rightIndex);
			int partitionLeft = partitionResult[0];
			int partitionRight = partitionResult[1];

			threeWayQuickSort(array, leftIndex, partitionLeft - 1);
			threeWayQuickSort(array, partitionRight + 1, rightIndex);
		}
	}

	private int[] partition(T[] array, int leftIndex, int rightIndex) {
		return partition(array, leftIndex, rightIndex, leftIndex);
	}

	private int[] partition(T[] array, int leftCounter, int rightCounter, int firstPivotPosition) {
		int[] result = new int[]{firstPivotPosition, rightCounter};

		if (leftCounter <= rightCounter) {
			if (array[firstPivotPosition].compareTo(array[leftCounter]) > 0) {

				Util.swap(array, firstPivotPosition, leftCounter);
				firstPivotPosition++;
				leftCounter++;
				result = partition(array, leftCounter, rightCounter, firstPivotPosition);

			} else if (array[firstPivotPosition]. compareTo(array[leftCounter]) < 0) {

				Util.swap(array, leftCounter, rightCounter);
				rightCounter--;
				result = partition(array, leftCounter, rightCounter, firstPivotPosition);

			} else if (array[firstPivotPosition].compareTo(array[leftCounter]) == 0) {

				leftCounter++;
				result = partition(array, leftCounter, rightCounter, firstPivotPosition);

			}
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
