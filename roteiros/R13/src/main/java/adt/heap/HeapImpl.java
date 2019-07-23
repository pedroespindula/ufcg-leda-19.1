package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o maior sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 3 > 2),
 * essa heap deixa os elementos maiores no topo. Essa comparação não é feita
 * diretamente com os elementos armazenados, mas sim usando um comparator.
 * Dessa forma, dependendo do comparator, a heap pode funcionar como uma max-heap
 * ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse
	 * comparator. Assim os metodos da heap não precisam saber se vai funcionar
	 * como max-heap ou min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma
	 * min-heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento
	 * indexado pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento
	 * indexado pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		ArrayList<T> resp = new ArrayList<T>();
		for (int i = 0; i <= this.index; i++) {
			resp.add(this.get(i));
		}
		return (T[])resp.toArray(new Comparable[0]);
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
	 * ser a raiz da heap ou de uma sub-heap. O heapify deve colocar os maiores
	 * (comparados usando o comparator) elementos na parte de cima da heap.
	 */
	private void heapify(int position) {
	  int max = this.getMax(position);

	  if (max != position) {
	  	this.swap(position, max);
	  	heapify(max);
		}
	}

	private int getMax(int position) {
		int left = this.left(position);
		int right = this.right(position);
		int max = position;

		if (left <= index && hasBiggerPriority(left, position)) {
			max = left;
		}

		if (right <= index && hasBiggerPriority(right, max)) {
			max = right;
		}

		return max;
	}

	private boolean hasBiggerPriority(int index, int other) {
		return this.getComparator().compare(this.get(index), this.get(other)) > 0;
	}


	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		// /////////////////////////////////////////////////////////////////

		set(++this.index, element);
		this.assertPriority(this.index);
	}

	private void assertPriority(int index) {
		if (this.getComparator().compare(this.get(index), this.get(this.parent(index))) > 0) {
			this.swap(index, this.parent(index));
			assertPriority(this.parent(index));
		}
	}

	private void swap(int i1, int i2) {
		T aux = get(i1);
		set(i1, this.get(i2));
		set(i2, aux);
	}

	private T get(int i1) {
		return this.heap[i1];
	}

	private void set(int i1, T t) {
		this.heap[i1] = t;
	}

	@Override
	public void buildHeap(T[] array) {
		if (array == null || array.length < 0) return;

	  this.heap = Arrays.copyOf(array, array.length);
	  this.index = array.length - 1;

	  heapifyUtilRoot(this.parent(index));
	}

	private void heapifyUtilRoot(int index) {
		if (index > -1) {
			heapify(index);
			heapifyUtilRoot(index - 1);
		}

	}

	@Override
	public T extractRootElement() {
		T result = this.rootElement();

		if (!this.isEmpty()) {
			this.swap(0, this.index);
			this.index -=1;
			heapify(0);
		}

		return result;
	}

	@Override
	public T rootElement() {
	  T result = null;

	  if (!this.isEmpty()) {
	  	result = this.get(0);
		}

	  return result;
	}

	@Override
	public T[] heapsort(T[] array) {
		if (array == null || array.length <= 0) return array;

		int indexAux = index;
		T[] heapAux = getHeap();
		Comparator<T> comparatorAux = getComparator();

		comparator = Comparator.naturalOrder();

		buildHeap(array);
		T[] result = (T[])new Comparable[array.length];

		for (int i = array.length - 1; i >= 0; i--) {
			result[i] = extractRootElement();
		}

		heap = heapAux;
		index = indexAux;
		comparator = comparatorAux;

		return result;
	}

	@Override
	public int size() {
	  return this.index + 1;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

}
