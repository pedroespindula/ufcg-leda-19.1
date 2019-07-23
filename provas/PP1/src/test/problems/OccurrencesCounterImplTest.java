package problems;

import org.junit.Test;

import static org.junit.Assert.*;

public class OccurrencesCounterImplTest {


    void genericTest(Integer[] array, Integer e, int exp) {
        OccurrencesCounterImpl<Integer> occurrencesCounter = new OccurrencesCounterImpl<>();
        int result = occurrencesCounter.count(array, e);
        assertEquals(exp, result);
    }

    @Test
    public void testCount1() {
        genericTest(new Integer[]{1, 1, 1, 2, 3, 4, 5, 6, 7}, 1, 3);
    }

    @Test
    public void testCount2() {
        genericTest(new Integer[]{1, 2, 2, 2, 3, 4, 5, 6, 7}, 2, 3);
    }


    @Test
    public void testCount3() {
        genericTest(new Integer[]{1, 2, 3, 3, 3, 4, 5, 6, 7}, 3, 3);
    }

    @Test
    public void testCount4() {
        genericTest(new Integer[]{1, 2, 3, 4, 4, 4, 5, 6, 7}, 4, 3);
    }

    @Test
    public void testCount5() {
        genericTest(new Integer[]{1, 2, 3, 4, 5, 5, 5, 6, 7}, 5, 3);
    }

    @Test
    public void testCount6() {
        genericTest(new Integer[]{1, 2, 3, 4, 5, 6, 6, 6, 7}, 6, 3);
    }

    @Test
    public void testCount7() {
        genericTest(new Integer[]{1, 2, 3, 4, 5, 6, 7, 7, 7}, 7, 3);
    }

    @Test
    public void testCountDouble() {
        genericTest(new Integer[]{1, 2, 3, 4, 4, 5, 6, 7}, 4, 2);
    }

    @Test
    public void testCountDoubleEnd() {
        genericTest(new Integer[]{1, 2, 3, 4, 4}, 4, 2);
    }

    @Test
    public void testASDASD() {
        genericTest(new Integer[]{1, 1, 2, 3}, 1, 2);
    }

    @Test
    public void testCountUnique() {
        genericTest(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 2, 1);
    }

    @Test
    public void testCountNotExistent() {
        genericTest(new Integer[]{1, 2, 3, 4, 5, 6, 7}, 8, 0);
    }

    @Test
    public void testCountAll() {
        genericTest(new Integer[]{1, 1, 1}, 1, 3);
    }

    @Test
    public void testEmpty() {
        genericTest(new Integer[]{}, 1, 0);
    }
}