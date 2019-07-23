package problems;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloorCeilBinarySearchTest {

    FloorCeilBinarySearch floorCeilBinarySearch;

    @BeforeEach
    void init() {
        this.floorCeilBinarySearch = new FloorCeilBinarySearch();
    }

    void genericTest(Integer[] array, Integer x, Integer expectedFloor, Integer expectedCeil) {
        Integer resultFloor = floorCeilBinarySearch.floor(array, x);
        Integer resultCeil = floorCeilBinarySearch.ceil(array, x);
        assertEquals(expectedFloor, resultFloor);
        assertEquals(expectedCeil, resultCeil);
    }

    @Test
    void testNoExistingEqualOddStart() {
        genericTest(new Integer[]{1, 3, 5, 7, 9}, 2, 1, 3);
    }

    @Test
    void testNoExistingEqualEvenStart() {
        genericTest(new Integer[]{1, 3, 5, 7}, 2, 1, 3);
    }

    @Test
    void testNoExistingEqualOddEnd() {
        genericTest(new Integer[]{1, 3, 5, 7, 9}, 8, 7, 9);
    }

    @Test
    void testNoExistingEqualEvenEnd() {
        genericTest(new Integer[]{1, 3, 5, 7}, 6, 5, 7);
    }

    @Test
    void testNoExistingEqualOddMiddle() {
        genericTest(new Integer[]{1, 3, 5, 7, 9}, 6, 5, 7);
    }

    @Test
    void testNoExistingEqualEvenMiddle() {
        genericTest(new Integer[]{1, 3, 5, 7, 9}, 6, 5, 7);
    }

    @Test
    void testAllExistingIndexesEven() {
        for (int i = 1; i <= 5; i++) {
            genericTest(new Integer[]{1, 2, 3, 4, 5}, i, i, i);
        }
    }

    @Test
    void testAllExistingIndexesOdd() {
        for (int i = 1; i <= 6; i++) {
            genericTest(new Integer[]{1, 2, 3, 4, 5, 6}, i, i, i);
        }
    }

    @Test
    void testUniqueElement() {
        genericTest(new Integer[]{1}, 1, 1, 1);
    }

    @Test
    void testUniqueElementBigger() {
        genericTest(new Integer[]{1}, 2, 1, 1);
    }

    @Test
    void testUniqueElementSmaller() {
        genericTest(new Integer[]{1}, 0, 1, 1);
    }

    @Test
    void testNegativeElements() {
        genericTest(new Integer[]{-2, 0, 2, 4, 6}, -1, -2, 0);
    }

    @Test
    void testRandomArray() {
        genericTest(new Integer[]{21, 25, 46, 58, 68, 80, 90, 93}, 91, 90, 93);
    }
}