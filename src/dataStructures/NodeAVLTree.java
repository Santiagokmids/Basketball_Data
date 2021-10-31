package dataStructures;

public class NodeAVLTree<K, V> {
	
	private K key;
	private V object;
	private int balanced;
	private int height;
	private NodeAVLTree<K, V> left;
	private NodeAVLTree<K, V> right;
	private NodeAVLTree<K, V> dad;
	
	public NodeAVLTree(K key, V object) {
		this.key = key;
		this.object = object;
		balanced = 0;
		height = 1;
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

	public int getBalanced() {
		return balanced;
	}

	public void setBalanced(int balanced) {
		this.balanced = balanced;
	}

	public NodeAVLTree<K, V> getLeft() {
		return left;
	}

	public void setLeft(NodeAVLTree<K, V> left) {
		this.left = left;
	}

	public NodeAVLTree<K, V> getRight() {
		return right;
	}

	public void setRight(NodeAVLTree<K, V> right) {
		this.right = right;
	}

	public NodeAVLTree<K, V> getDad() {
		return dad;
	}

	public void setDad(NodeAVLTree<K, V> dad) {
		this.dad = dad;
	}

	public int getHeight() {
		
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
