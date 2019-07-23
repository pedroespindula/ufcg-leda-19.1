package problems;

/**
 * Dado um array ordenado de elementos comparaveis e um outro elemento comparavel,
 * implemente o metodo que conte as ocorrências do elemento no array.
 * <p>
 * Restricoes:
 * - a complexidade esperada é O (log.n). Soluções com tempo O(n) ou superiores serão desconsideradas.
 * - voce nao pode usar memoria extra
 * - voce nao pode usar metodos prontos da bilbioteca de arrays (exceto o metodo length)
 * - Dica: tente pensar numa forma eficiente (em log n) de descobrir a posicao de um
 * elemento no array e use essa ideia para contar as ocorrencias desse elemento no array
 *
 * @param <T>
 * @author campelo
 */
public class OccurrencesCounterImpl<T extends Comparable<T>> {

    public int count(T[] array, T elem) {
        int leftIndex = findExtremeLeft(array, elem);
        int rightIndex = findExtremeRight(array, elem);

        return rightIndex - leftIndex + 1;
    }

    private int findExtremeRight(T[] array, T elem) {
        return findExtremeRight(array, elem, 0, array.length - 1);
    }

    private int findExtremeRight(T[] array, T elem, int start, int end) {
        int midIndex = (start + end) / 2;

        int result = -1;

        if (midIndex < end) {
            if (midIndex == end) {
                if (elem.compareTo(array[midIndex]) == 0) {
                    result = midIndex;
                } else {
                    result = midIndex - 1;
                }
            } else {
                if (elem.compareTo(array[midIndex]) < 0) {
                    result = findExtremeRight(array, elem, start, midIndex);
                } else if (elem.compareTo(array[midIndex]) > 0) {
                    result = findExtremeRight(array, elem, midIndex + 1, end);
                } else if (elem.compareTo(array[midIndex]) == 0) {
                    result = findExtremeRight(array, elem, midIndex + 1, end);
                }
            }
        }


        return result;

    }

    private int findExtremeLeft(T[] array, T elem) {
        return findExtremeLeft(array, elem, 0, array.length);
    }

    private int findExtremeLeft(T[] array, T elem, int start, int end) {
        int midIndex = (start + end) / 2;

        int result = -1;

        if (midIndex < end) {
            if (midIndex == start) {
                if (elem.compareTo(array[midIndex]) == 0) {
                    result = midIndex;
                } else {
                    result = midIndex + 1;
                }
            } else {
                if (elem.compareTo(array[midIndex]) < 0) {
                    result = findExtremeLeft(array, elem, start, midIndex);
                } else if (elem.compareTo(array[midIndex]) > 0) {
                    result = findExtremeLeft(array, elem, midIndex, end);
                } else if (elem.compareTo(array[midIndex]) == 0) {
                    result = findExtremeLeft(array, elem, start, midIndex);
                }
            }
        }

        return result;

    }

}
