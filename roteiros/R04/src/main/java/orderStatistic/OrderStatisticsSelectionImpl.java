package orderStatistic;

public class OrderStatisticsSelectionImpl<T extends Comparable<T>> implements OrderStatistics<T> {

    /**
     * Esta eh uma implementacao do calculo da estatistica de ordem seguindo a estrategia
     * de usar o selection sem modificar o array original. Note que seu algoritmo vai
     * apenas aplicar sucessivas vezes o selection ate encontrar a estatistica de ordem
     * desejada sem modificar o array original.
     * <p>
     * Restricoes:
     * - Preservar o array original, ou seja, nenhuma modificacao pode ser feita no
     * array original
     * - Nenhum array auxiliar deve ser criado e utilizado.
     * - Voce nao pode encontrar a k-esima estatistica de ordem por contagem de
     * elementos maiores/menores, mas sim aplicando sucessivas selecoes (selecionar um elemento
     * como o selectionsort mas sem modificar nenhuma posicao do array).
     * - Caso a estatistica de ordem nao exista no array, o algoritmo deve retornar null.
     * - Considerar que k varia de 1 a N
     * - Sugestao: o uso de recursao ajudara sua codificacao.
     */
    @Override
    public T getOrderStatistics(T[] array, int k) {
        T result = null;

        for (int i = 1; i <= k; i++) {
            result = selectOrder(array, result);
        }

        return result;
    }


    private T selectOrder(T[] array, T currentOrderElement) {
        T currentSmaller = null;

        for (T element : array) {
            if (element != null && isBiggerThanOrder(currentOrderElement, element) && isCurrentSmaller(currentSmaller, element)) {
                currentSmaller = element;
            }
        }

        return currentSmaller;
    }

    private boolean isCurrentSmaller(T currentSmaller, T element) {
        return currentSmaller == null || element.compareTo(currentSmaller) < 0;
    }

    private boolean isBiggerThanOrder(T currentOrderElement, T element) {
        return currentOrderElement == null || element.compareTo(currentOrderElement) > 0;
    }

}
