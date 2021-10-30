package dataStructures;

public class AVLTree<K extends Comparable<K>, V, F, H extends Comparable<H>> implements IAVLTree<K, V, F, H>{
	
	private NodeAVLTree<K, V, F, H> root;
	
	public AVLTree() {
		createTree();
	}

	@Override
	public void createTree() {
		root = null;
	}

	@Override
	public boolean addNode(K key, V object) {
		
		boolean verify = false;
		
		NodeAVLTree<K, V, F, H> newNodeAVLTree = new NodeAVLTree<K, V, F, H>(key, object);
		
		if(root == null) {
			root = newNodeAVLTree;
			newNodeAVLTree.setBalanced(balanceTree( newNodeAVLTree));
			verify = true;
		}else {
			verify = addNode(key, root, newNodeAVLTree);
		}
		
		return verify;
	}
	
	public void updateHeight(NodeAVLTree<K, V, F, H> nodeAVLTree) {
		nodeAVLTree.setHeight(1 + nodeMax(height(nodeAVLTree.getLeft()), height(nodeAVLTree.getRight())));
	}
	
	public boolean addNode(K key, NodeAVLTree<K, V, F, H> assistaNodeAVLTree, NodeAVLTree<K, V, F, H> newNodeAVLTree) {
		boolean verify = false;
		
		if((newNodeAVLTree.getKey()).compareTo(assistaNodeAVLTree.getKey()) <= 0) {
			if(assistaNodeAVLTree.getLeft() == null) {
				assistaNodeAVLTree.setLeft(newNodeAVLTree);
				newNodeAVLTree.setDad(assistaNodeAVLTree);
				assistaNodeAVLTree.setBalanced(balanceTree(assistaNodeAVLTree));
				verify = true;
			}else {
				addNode(key, assistaNodeAVLTree.getLeft(), newNodeAVLTree);
			}
		}
		return verify;
	}

	@Override
	public boolean deleteNode(K key, V object) {
		return false;
	}

	@Override
	public NodeAVLTree<K, V, F, H> searchNode(K key) {
		return searchNode(key, root);
	}
	
	public NodeAVLTree<K, V, F, H> searchNode(K key, NodeAVLTree<K, V, F, H> assistaNodeAVLTree) {
		
		
		if(assistaNodeAVLTree == null) {
			return assistaNodeAVLTree;
		}else {
			if(key.compareTo(assistaNodeAVLTree.getKey()) <= 0) {
				return searchNode(key, assistaNodeAVLTree.getLeft());
			}else {
				return searchNode(key, assistaNodeAVLTree.getRight());
			}
		}
	}

	@Override
	public int height(NodeAVLTree<K, V, F, H> node) {
		int height = 0;
		if(node != null) {
			height = node.getHeight();
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
	
	public NodeAVLTree<K, V, F, H> getRoot() {
		return root;
	}

	public void setRoot(NodeAVLTree<K, V, F, H> root) {
		this.root = root;
	}
}
