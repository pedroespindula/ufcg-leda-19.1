package orderStatistic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderStatisticsSelectionImplTest {

    OrderStatisticsSelectionImpl<Integer> orderStatisticsSelection;

    @BeforeEach
    void init() {
        this.orderStatisticsSelection = new OrderStatisticsSelectionImpl<>();
    }


    void genericTest(Integer[] array, int k, Integer expected) {
        Integer result = this.orderStatisticsSelection.getOrderStatistics(array, k);
        assertEquals(expected, result);
    }

    void genericAllIndexesTest(Integer[] array, int max) {
        for (int i = 1; i <= max; i++) {
            genericTest(array, i, i);
        }
    }

    private void genericAllIndexesTest(Integer[] array) {
        genericAllIndexesTest(array, array.length - 1);
    }

    @Test
    void testAllIndexesOrdered() {
        genericAllIndexesTest(new Integer[]{1, 2, 3, 4, 5});
    }

    @Test
    void testAllIndexesReverseOrdered() {
        genericAllIndexesTest(new Integer[]{5, 4, 3, 2, 1});
    }

    @Test
    void testAllIndexesNotOrdered() {
        genericAllIndexesTest(new Integer[]{3, 5, 1, 2, 4});
    }

    @Test
    void testAllIndexesWithNull() {
        genericAllIndexesTest(new Integer[]{3, 5, 1, null, 4, null, 2}, 5);
    }

    @Test
    void testIndexBiggerThanLength() {
        genericTest(new Integer[]{1, 2}, 3, null);
    }

    @Test
    void testIndexSmallerThanZero() {
        genericTest(new Integer[]{1, 2}, 0, null);
    }

    @Test
    void testAllElementsNull() {
        for (int i = 1; i < 10; i++) {
            genericTest(new Integer[10], i, null);
        }
    }

}