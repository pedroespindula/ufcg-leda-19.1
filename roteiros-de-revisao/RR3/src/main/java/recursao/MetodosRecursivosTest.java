package recursao;

import static org.junit.jupiter.api.Assertions.*;

class MetodosRecursivosTest {

    private MetodosRecursivos metodosRecursivos;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        this.metodosRecursivos = new MetodosRecursivos();
    }

    @org.junit.jupiter.api.Test
    void calcularSomaArray() {
        int[] arrayPadrao = {1, 2, 3, 4};
        assertEquals(10, this.metodosRecursivos.calcularSomaArray(arrayPadrao));

        int[] arrayVazio = {};
        assertEquals(0, this.metodosRecursivos.calcularSomaArray(arrayVazio));

        int[] arrayUmElemento = {2};
        assertEquals(2, this.metodosRecursivos.calcularSomaArray(arrayUmElemento));
    }

    @org.junit.jupiter.api.Test
    void calcularFatorial() {
        assertEquals(1, this.metodosRecursivos.calcularFatorial(0));
        assertEquals(1, this.metodosRecursivos.calcularFatorial(1));
        assertEquals(2, this.metodosRecursivos.calcularFatorial(2));
        assertEquals(6, this.metodosRecursivos.calcularFatorial(3));
        assertEquals(24, this.metodosRecursivos.calcularFatorial(4));
        assertEquals(120, this.metodosRecursivos.calcularFatorial(5));
    }

    @org.junit.jupiter.api.Test
    void calcularFibonacci() {
        assertEquals(1, this.metodosRecursivos.calcularFibonacci(0));
        assertEquals(1, this.metodosRecursivos.calcularFibonacci(1));
        assertEquals(2, this.metodosRecursivos.calcularFibonacci(2));
        assertEquals(3, this.metodosRecursivos.calcularFibonacci(3));
        assertEquals(5, this.metodosRecursivos.calcularFibonacci(4));
        assertEquals(8, this.metodosRecursivos.calcularFibonacci(5));
    }

    @org.junit.jupiter.api.Test
    void countNotNull() {
        Object[] arrayPadrao = {1, 2, null, null, null};
        assertEquals(2, this.metodosRecursivos.countNotNull(arrayPadrao));

        Object[] arrayNull = {null, null};
        assertEquals(0, this.metodosRecursivos.countNotNull(arrayNull));

        Object[] arrayCheio = {1, 2, 3, 4};
        assertEquals(4, this.metodosRecursivos.countNotNull(arrayCheio));

        Object[] arrayVazio = {};
        assertEquals(0, this.metodosRecursivos.countNotNull(arrayVazio));
    }

    @org.junit.jupiter.api.Test
    void potenciaDe2() {
        assertEquals(1, this.metodosRecursivos.potenciaDe2(0));
        assertEquals(2, this.metodosRecursivos.potenciaDe2(1));
        assertEquals(4, this.metodosRecursivos.potenciaDe2(2));
        assertEquals(1024, this.metodosRecursivos.potenciaDe2(10));
    }

    @org.junit.jupiter.api.Test
    void progressaoAritmetica() {
        assertEquals(1, this.metodosRecursivos.progressaoAritmetica(1, 1, 0));
        assertEquals(1, this.metodosRecursivos.progressaoAritmetica(1, 5, 0));
        assertEquals(1, this.metodosRecursivos.progressaoAritmetica(1, 5, 1));
        assertEquals(5, this.metodosRecursivos.progressaoAritmetica(1, 1, 5));
        assertEquals(6, this.metodosRecursivos.progressaoAritmetica(1, 5, 2));
        assertEquals(15, this.metodosRecursivos.progressaoAritmetica(10, 5, 2));
    }

    @org.junit.jupiter.api.Test
    void progressaoGeometrica() {
        assertEquals(0, this.metodosRecursivos.progressaoGeometrica(0, 1, 1));
        assertEquals(1, this.metodosRecursivos.progressaoGeometrica(1, 1, 0));
        assertEquals(1, this.metodosRecursivos.progressaoGeometrica(1, 5, 0));
        assertEquals(1, this.metodosRecursivos.progressaoGeometrica(1, 5, 1));
        assertEquals(1, this.metodosRecursivos.progressaoGeometrica(1, 1, 5));
        assertEquals(5, this.metodosRecursivos.progressaoGeometrica(1, 5, 2));
        assertEquals(50, this.metodosRecursivos.progressaoGeometrica(10, 5, 2));
    }
}