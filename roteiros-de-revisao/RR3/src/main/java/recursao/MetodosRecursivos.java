package recursao;

import java.util.Arrays;

public class MetodosRecursivos {

    public int calcularSomaArray(int[] array) {
        int result = 0;

        if (array.length >= 1) {
            result = array[array.length - 1] + calcularSomaArray(Arrays.copyOf(array, array.length - 1));
        }

        return result;
    }

    public long calcularFatorial(int n) {
        long result = 1;

        if (n >= 1) {
            result = n * this.calcularFatorial(n - 1);
        }

        return result;
    }

    public int calcularFibonacci(int n) {
        int result = 1;

        if (n > 1) {
            result = calcularFibonacci(n - 1) + calcularFibonacci(n - 2);
        }

        return result;
    }

    public int countNotNull(Object[] array) {
        int result = 0;

        if (array.length > 0) {

            if (array[0] != null) {
                result += 1;
            }

            result += countNotNull(Arrays.copyOfRange(array, 1, array.length));

        }

        return result;
    }

    public long potenciaDe2(int expoente) {
        long result = 1;

        if (expoente >= 1) {
            result = 2 * potenciaDe2(expoente - 1);
        }

        return result;
    }

    public double progressaoAritmetica(double termoInicial, double razao, int n) {
        double result = termoInicial;

        if (n > 1) {
            result = razao + progressaoAritmetica(termoInicial, razao, n - 1);
        }

        return result;
    }

    public double progressaoGeometrica(double termoInicial, double razao, int n) {
        double result = termoInicial;

        if (n > 1) {
            result = razao * progressaoGeometrica(termoInicial, razao, n - 1);
        }

        return result;
    }

}
