package dataStructures;

public class NodeAVLTree<K, V, F, H> {
	
	private K key;
	private V object;
	private int balanced;
	private int height;
	private NodeAVLTree<K, V, F, H> left;
	private NodeAVLTree<K, V, F, H> right;
	private NodeAVLTree<K, V, F, H> dad;
	
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

	public int getHeight() {
		
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
