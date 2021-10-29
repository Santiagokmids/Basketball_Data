package dataStructures;

public class NodeAVLTree<K, V, F, H> {
	
	private K key;
	private V object;
	private F balanced;
	private H height;
	private NodeAVLTree<K, V, F, H> left;
	private NodeAVLTree<K, V, F, H> right;
	private NodeAVLTree<K, V, F, H> dad;
	
	public NodeAVLTree(K key, V object, F balanced, H height) {
		this.key = key;
		this.object = object;
		this.balanced = balanced;
		this.height = height;
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

	public NodeAVLTree<K, V, F, H> getLeft() {
		return left;
	}

	public void setLeft(NodeAVLTree<K, V, F, H> left) {
		this.left = left;
	}

	public NodeAVLTree<K, V, F, H> getRight() {
		return right;
	}

	public void setRight(NodeAVLTree<K, V, F, H> right) {
		this.right = right;
	}

	public NodeAVLTree<K, V, F, H> getDad() {
		return dad;
	}

	public void setDad(NodeAVLTree<K, V, F, H> dad) {
		this.dad = dad;
	}

	public H getHeight() {
		
		return height;
	}

	public void setHeight(H height) {
		this.height = height;
	}
}
