package adt.linkedList.set;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SetLinkedListImplTest {

    @Test
    public void testIntersection() {
        testNormalInsert();
        testDuplicateInsertion();

        SetLinkedListImpl set = getDuplicateInsertion();
        SetLinkedListImpl other = getNormalInsert();

        assertArrayEquals(new String[]{"a", "c"}, other.intersection(set).toArray());
    }

    @Test
    public void testUnion() {
        testNormalInsert();
        testDuplicateInsertion();

        SetLinkedListImpl set = getDuplicateInsertion();
        SetLinkedListImpl other = getNormalInsert();

       assertArrayEquals(new String[]{"a", "d", "e", "c", "b"}, set.union(other).toArray());
    }

    @Test
    public void testConcatenate() {
        testNormalInsert();
        testDuplicateInsertion();

        SetLinkedListImpl set = getDuplicateInsertion();
        SetLinkedListImpl other = getNormalInsert();

        set.concatenate(other);
        assertArrayEquals(new String[]{"a", "b", "c", "d", "e"}, set.toArray());
    }

    @Test
    public void testNormalInsert() {

        SetLinkedListImpl set = getNormalInsert();
        assertArrayEquals(new String[]{"a", "d", "e", "c"}, set.toArray());
    }

    @Test
    public void testDuplicateInsertion() {
        SetLinkedListImpl<String> set = getDuplicateInsertion();
        assertArrayEquals(new String[]{"a", "b", "c"}, set.toArray());
    }

    @Test
    public void testInsertNull() {
        SetLinkedList<String> set = new SetLinkedListImpl<>();
        set.insert(null);
        assertEquals(0, set.size());
    }

    @Test
    public void testToArrayEmpty() {
        SetLinkedList set = new SetLinkedListImpl();
        assertArrayEquals(new String[]{}, set.toArray());
    }

    @Test
    public void testUnionEmpty() {
        SetLinkedList set = new SetLinkedListImpl();
        SetLinkedList set2 = new SetLinkedListImpl();
        assertArrayEquals(new String[]{}, set.union(set2).toArray());
    }

    @Test
    public void testUnionItself() {
        SetLinkedList set = new SetLinkedListImpl();
        assertArrayEquals(set.toArray(), set.union(set).toArray());
    }

    @Test
    public void testIntersectionItself() {
        SetLinkedList set = new SetLinkedListImpl();
        assertArrayEquals(set.toArray(), set.intersection(set).toArray());
    }

    private SetLinkedListImpl<String> getNormalInsert() {
        SetLinkedListImpl<String> set = new SetLinkedListImpl<>();
        set.insert("a");
        set.insert("d");
        set.insert("e");
        set.insert("c");
        return set;
    }

    private SetLinkedListImpl<String> getDuplicateInsertion() {
        SetLinkedListImpl<String> set = new SetLinkedListImpl<>();

        set.insert("a");
        set.insert("a");
        set.insert("a");
        set.insert("a");
        set.insert("a");
        set.insert("a");
        set.insert("b");
        set.insert("c");
        return set;
    }
}
