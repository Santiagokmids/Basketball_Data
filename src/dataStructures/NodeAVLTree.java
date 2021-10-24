package dataStructures;

public class NodeAVLTree<K, V, F> {
	
	private K key;
	private V object;
	private F balanced;
	private NodeAVLTree<K, V, F> left;
	private NodeAVLTree<K, V, F> right;
	private NodeAVLTree<K, V, F> dad;
	
	public NodeAVLTree(K key, V object, F balanced) {
		this.key = key;
		this.object = object;
		this.balanced = balanced;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getObject() {
		return object;
	}

	public void setObject(V object) {
		this.object = object;
	}

	public F getBalanced() {
		return balanced;
	}

	public void setBalanced(F balanced) {
		this.balanced = balanced;
	}

	public NodeAVLTree<K, V, F> getLeft() {
		return left;
	}

	public void setLeft(NodeAVLTree<K, V, F> left) {
		this.left = left;
	}

	public NodeAVLTree<K, V, F> getRight() {
		return right;
	}

	public void setRight(NodeAVLTree<K, V, F> right) {
		this.right = right;
	}

	public NodeAVLTree<K, V, F> getDad() {
		return dad;
	}

	public void setDad(NodeAVLTree<K, V, F> dad) {
		this.dad = dad;
	}
	
}
