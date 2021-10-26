package dataStructures;

public class NodoBinaryTree <T extends Comparable<T>> implements Comparable <NodoBinaryTree<T>> {
	private NodoBinaryTree<T> left;
	private NodoBinaryTree<T> right;
	private T value;
	private NodoBinaryTree<T> parent;

	public NodoBinaryTree() {}

	public NodoBinaryTree<T> getLeft() {
		return left;
	}

	public void setLeft(NodoBinaryTree<T> left) {
		this.left = left;
	}

	public NodoBinaryTree<T> getRight() {
		return right;
	}

	public void setRight(NodoBinaryTree<T> right) {
		this.right = right;
	}

	public int compareTo(NodoBinaryTree<T> o) {
		return getValue().compareTo(o.getValue());
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public NodoBinaryTree<T> getParent() {
		return parent;
	}

	public void setParent(NodoBinaryTree<T> parent) {
		this.parent = parent;
	}

}
