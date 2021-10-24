package dataStructures;

public class AVLTree<K, V, F> implements IAVLTree<K, V, F>{
	
	private NodeAVLTree<K, V, F> root;
	
	public AVLTree() {
		createTree();
	}

	public NodeAVLTree<K, V, F> getRoot() {
		return root;
	}

	public void setRoot(NodeAVLTree<K, V, F> root) {
		this.root = root;
	}

	@Override
	public void createTree() {
		root = null;
	}

	@Override
	public boolean addNode(K key, V object, F balanced) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteNode(K key, V object, F balanced) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public NodeAVLTree<K, V, F> searchNode(K key, V object, F balanced) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
