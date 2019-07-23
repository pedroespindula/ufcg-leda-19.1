package problems;

/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * <p>
 * Restricoes:
 * - Algoritmo in-place (nao pode usar memoria extra a nao ser variaveis locais)
 * - O tempo de seu algoritmo deve ser O(log n).
 *
 * @author Adalberto
 */
public class FloorCeilBinarySearch implements FloorCeil {

    @Override
    public Integer floor(Integer[] array, Integer x) {
        return floor(array, x, 0, array.length);
    }

    private Integer floor(Integer[] array, Integer x, int minIndex, int maxIndex) {
        int midIndex = (minIndex + maxIndex) / 2;
        Integer result = array[midIndex];

        if (minIndex < maxIndex - 1) {

            if (result.compareTo(x) > 0) {
                result = floor(array, x, minIndex, midIndex);
            } else if (result.compareTo(x) < 0) {
                result = floor(array, x, midIndex, maxIndex);
            }

        }

        return result;
    }

    @Override
    public Integer ceil(Integer[] array, Integer x) {
        return ceil(array, x, 0, array.length - 1);
    }

    private Integer ceil(Integer[] array, Integer x, int minIndex, int maxIndex) {
        int midIndex = (minIndex + maxIndex) / 2;
        Integer result = array[midIndex];

        if (minIndex < maxIndex) {

            if (result.compareTo(x) > 0) {
                result = ceil(array, x, minIndex, midIndex);
            } else if (result.compareTo(x) < 0) {
                result = ceil(array, x, midIndex + 1, maxIndex);
            }

        }

        return result;
    }

}
