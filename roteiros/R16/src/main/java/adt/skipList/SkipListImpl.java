package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int maxHeight;

	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		for (int i = 0; i < maxHeight; i++) {
			root.forward[i] = NIL;
		}
	}

	@Override
	public void insert(int key, T newValue, int height) {
	  if (newValue != null) {
      if (height > maxHeight) throw new RuntimeException("Height maior que MaxHeight");

      SkipListNode<T> search = search(key);

      if (search == null) {
        SkipListNode<T> node = new SkipListNode<>(key, height, newValue);
        insert(node, this.root, height - 1);
      } else {
        search.setValue(newValue);
      }
    }
  }

  private void insert(SkipListNode<T> node, SkipListNode<T> current, int currentHeight) {
	  if (currentHeight >= 0) {
      SkipListNode<T> next = current.getForward(currentHeight);
      if (node.getKey() < next.getKey()) {
        setFoward(node, next, currentHeight);
        setFoward(current, node, currentHeight);
        insert(node, current, currentHeight - 1);
      } else {
        insert(node, next, currentHeight);
      }
    }
  }

  private void setFoward(SkipListNode<T> node, SkipListNode<T> next, int currentHeight) {
    node.forward[currentHeight] = next;
  }

  @Override
	public void remove(int key) {
	  remove(key, this.root, maxHeight - 1);
	}

  private void remove(int key, SkipListNode<T> node, int height) {
    if (height >= 0) {
      SkipListNode<T> next = node.getForward(height);
      if (next.equals(NIL) || key < next.getKey()) {
        remove(key, node, height - 1);
      } else if (key > next.getKey()) {
        remove(key, next, height);
      } else if (key == next.getKey()) {
        setFoward(node, next.getForward(height), height);
        remove(key, node, height - 1);
      }
    }
  }

  @Override
	public int height() {
	  return height(this.root, maxHeight);
	}

  private int height(SkipListNode<T> node, int currentLevel) {
    int result = currentLevel;

    if (node.getForward(currentLevel).equals(NIL)) {
	    result = height(node, currentLevel - 1);
    }

	  return result;
  }

  @Override
	public SkipListNode<T> search(int key) {
	  return search(key, this.root, maxHeight - 1);
	}

  private SkipListNode<T> search(int key, SkipListNode<T> node, int height) {
    SkipListNode<T> result = node;

    if (height >= 0) {

      result = node.getForward(height);
      if (result.equals(NIL) || key < result.getKey()) {
        result = search(key, node, height - 1);
      } else if (key > result.getKey()){
        result = search(key, result, height);
      }
    } else if (result.getKey() != key) {
      result = null;
    }

    return result;
  }

  @Override
	public int size() {
	  return size(this.root.getForward(0));
	}

  private int size(SkipListNode<T> node) {
	  int result = 0;

	  if (!node.equals(NIL)) {
      result = 1 + size(node.getForward(0));
    }

    return result;
  }

  @Override
	public SkipListNode<T>[] toArray() {
	  SkipListNode<T>[] result = new SkipListNode[this.size() + 2];

	  toArray(result, this.root, 0);

    return result;
	}
  private void toArray(SkipListNode<T>[] result, SkipListNode<T> node, int i) {
    if (i < result.length) {
      result[i] = node;
      toArray(result, node.getForward(0), i + 1);
    }
  }

}
