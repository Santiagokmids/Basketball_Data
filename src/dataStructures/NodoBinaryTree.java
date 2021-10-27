package dataStructures;


public class NodoBinaryTree <T,K> {
	private NodoBinaryTree<T,K> left;
	private NodoBinaryTree<T,K> right;
	private T value;
	private K key;
	private NodoBinaryTree<T,K> parent;

	public NodoBinaryTree() {}

	public NodoBinaryTree<T,K> getLeft() {
		return left;
	}

	public void setLeft(NodoBinaryTree<T,K> left) {
		this.left = left;
	}

	public NodoBinaryTree<T,K> getRight() {
		return right;
	}

	public void setRight(NodoBinaryTree<T,K> right) {
		this.right = right;
	}


	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public NodoBinaryTree<T,K> getParent() {
		return parent;
	}

	public void setParent(NodoBinaryTree<T,K> parent) {
		this.parent = parent;
	}

}
