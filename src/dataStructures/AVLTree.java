package dataStructures;

public class AVLTree<K, V, F, H> implements IAVLTree<K, V, F, H>{
	
	private NodeAVLTree<K, V, F, H> root;
	
	public AVLTree() {
		createTree();
	}

	public NodeAVLTree<K, V, F, H> getRoot() {
		return root;
	}

	public void setRoot(NodeAVLTree<K, V, F, H> root) {
		this.root = root;
	}
	
	@Override
	public void createTree() {
		root = null;
	}

	@Override
	public boolean addNode(K key, V object, F balanced) {
		
		return false;
	}

	@Override
	public boolean deleteNode(K key, V object, F balanced) {
		return false;
	}

	@Override
	public NodeAVLTree<K, V, F, H> searchNode(K key, V object, F balanced) {
		return null;
	}

	@Override
	public int height(NodeAVLTree<K, V, F, H> node) {
		int height = 0;
		if(node != null) {
			height = (int) node.getHeight();
		}
		return height;
	}

	@Override
	public int nodeMax(int nodeHeightA, int nodeHeightB) {
		return (nodeHeightA > nodeHeightB) ? nodeHeightA : nodeHeightB;
	}

	@Override
	public int balanceTree(NodeAVLTree<K, V, F, H> node) {
		int balance = 0;
		if(node != null) {
			balance = height(node.getLeft()) - height(node.getRight());
		}
		return balance;
	}
	
}
