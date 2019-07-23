package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashtableOpenAddressLinearProbingImplTest {


    protected AbstractHashtableOpenAddress<HashtableElement> table1;
    protected AbstractHashtableOpenAddress<HashtableElement> table2;

    protected final int PROPOSED_SIZE = 10;

    @Before
    public void setUp() throws Exception {
        table1 = new HashtableOpenAddressLinearProbingImpl<HashtableElement>(
                PROPOSED_SIZE, HashFunctionClosedAddressMethod.DIVISION);
        // o tamanho real utilizado vai ser PROPOSED_SIZE
        table1.insert(new HashtableElement(2)); // coloca no slot indexado com 2
        table1.insert(new HashtableElement(3)); // coloca no slot indexado com 3
        table1.insert(new HashtableElement(4)); // coloca no slot indexado com 4
        table1.insert(new HashtableElement(5)); // coloca no slot indexado com 5

        table2 = new HashtableOpenAddressLinearProbingImpl<HashtableElement>(
                PROPOSED_SIZE, HashFunctionClosedAddressMethod.DIVISION);
    }

    @Test
    public void testGeral(){
        table1.insert(new HashtableElement(2));
        table1.insert(new HashtableElement(3));
        table1.insert(new HashtableElement(4));
        table1.insert(new HashtableElement(5));
        assertEquals(0,table1.getCOLLISIONS());
        assertEquals(4,table1.size());

        table1.insert(new HashtableElement(6));
        table1.insert(new HashtableElement(7));
        table1.insert(new HashtableElement(15));
        table1.insert(new HashtableElement(84));
        table1.insert(new HashtableElement(1));
        table1.insert(new HashtableElement(27));

        assertEquals(10,table1.size());
        assertTrue(table1.isFull());
        assertEquals(table1.getCOLLISIONS(),11);
        assertNull(table1.search(new HashtableElement(25)));
        assertEquals(table1.search(new HashtableElement(27)),new HashtableElement(27));
        assertEquals(table1.indexOf(new HashtableElement(27)),0);
        assertEquals(table1.indexOf(new HashtableElement(84)),9);
        table1.remove(new HashtableElement(84));
        assertEquals(table1.indexOf(new HashtableElement(84)),-1);
        table1.insert(new HashtableElement(23));
        assertEquals(table1.indexOf(new HashtableElement(23)),9);
        table1.remove(new HashtableElement(2));
        table1.remove(new HashtableElement(3));
        table1.remove(new HashtableElement(4));
        table1.remove(new HashtableElement(5));
        table1.remove(new HashtableElement(6));
        table1.remove(new HashtableElement(7));
        table1.remove(new HashtableElement(15));
        table1.remove(new HashtableElement(84));
        table1.remove(new HashtableElement(1));
        table1.remove(new HashtableElement(27));
        table1.remove(new HashtableElement(23));
        assertEquals(table1.size(),0);
        assertNull(table1.search(new HashtableElement(23)));
        assertTrue(table1.getCOLLISIONS() == 0 || table1.getCOLLISIONS() == 17);
        assertTrue(table1.isEmpty());
    }
}