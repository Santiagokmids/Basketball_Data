package dataStructures;

import model.Players;

public class AVLTree<K, V, F, H> implements IAVLTree<K, V, F, H>{
	
	private NodeAVLTree<Integer, Players, Integer, Integer> root;
	
	public AVLTree() {
		createTree();
	}

	@Override
	public void createTree() {
		root = null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean addNode(K key, V object) {
		
		boolean verify = false;
		
		NodeAVLTree<Integer, Players, Integer, Integer> newNodeAVLTree = new NodeAVLTree<Integer, Players, Integer, Integer>(null, null);
		newNodeAVLTree.setKey((Integer)key);
		newNodeAVLTree.setObject((Players)object);
		newNodeAVLTree.setHeight(1);
		
		if(root == null) {
			root = newNodeAVLTree;
			newNodeAVLTree.setBalanced(balanceTree((NodeAVLTree<K, V, F, H>) newNodeAVLTree));
			verify = true;
		}else {
			verify = addNode(key, root, newNodeAVLTree);
		}
		
		return verify;
	}
	
	@SuppressWarnings("unchecked")
	public void updateHeight(NodeAVLTree<Integer, Players, Integer, Integer> nodeAVLTree) {
		nodeAVLTree.setHeight(1 + nodeMax(height((NodeAVLTree<K, V, F, H>) nodeAVLTree.getLeft()), height((NodeAVLTree<K, V, F, H>) nodeAVLTree.getRight())));
	}
	
	@SuppressWarnings("unchecked")
	public boolean addNode(K key, NodeAVLTree<Integer, Players, Integer, Integer> assistaNodeAVLTree, NodeAVLTree<Integer, Players, Integer, Integer> newNodeAVLTree) {
		boolean verify = false;
		
		if(newNodeAVLTree.getKey() <= assistaNodeAVLTree.getKey()) {
			if(assistaNodeAVLTree.getLeft() == null) {
				assistaNodeAVLTree.setLeft(newNodeAVLTree);
				newNodeAVLTree.setDad(assistaNodeAVLTree);
				assistaNodeAVLTree.setBalanced(balanceTree((NodeAVLTree<K, V, F, H>) assistaNodeAVLTree));
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
	
	@SuppressWarnings("unchecked")
	public NodeAVLTree<K, V, F, H> searchNode(K key, NodeAVLTree<Integer, Players, Integer, Integer> assistaNodeAVLTree) {
		
		if(assistaNodeAVLTree == null || assistaNodeAVLTree.getKey() == key) {
			return (NodeAVLTree<K, V, F, H>) assistaNodeAVLTree;
		}else {
			if((Integer)key <= (Integer)assistaNodeAVLTree.getKey()) {
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
			height = (int) node.getHeight();
		}
		return height;
	}

	@Override
	public Integer nodeMax(H nodeHeightA, H nodeHeightB) {
		return ((Integer)nodeHeightA > (Integer)nodeHeightB) ? (Integer)nodeHeightA : (Integer)nodeHeightB;
	}

	@Override
	public int balanceTree(NodeAVLTree<K, V, F, H> node) {
		int balance = 0;
		if(node != null) {
			balance = height(node.getLeft()) - height(node.getRight());
		}
		return balance;
	}
	
	public NodeAVLTree<Integer, Players, Integer, Integer> getRoot() {
		return root;
	}

	public void setRoot(NodeAVLTree<Integer, Players, Integer, Integer> root) {
		this.root = root;
	}
}
