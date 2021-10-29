package dataStructures;

import java.util.ArrayList;

import model.Players;

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
	public boolean addNode(K key, V object, F balanced, H height) {
		
		boolean verify = false;
		
		NodeAVLTree<K, V, F, H> newNodeAVLTree = new NodeAVLTree<K, V, F, H>(key, object, balanced, height);
		
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
	
	@SuppressWarnings("unchecked")
	public NodeAVLTree<K, V, F, H> searchNode(K key, NodeAVLTree<K, V, F, H> assistaNodeAVLTree) {
		
		if(assistaNodeAVLTree == null) {
			return (NodeAVLTree<K, V, F, H>) assistaNodeAVLTree;
<<<<<<< HEAD
		}else {
			if(key.compareTo(assistaNodeAVLTree.getKey()) <= 0) {
=======
		}
		else {
			if((Integer)key <= (Integer)assistaNodeAVLTree.getKey()) {
>>>>>>> bf53df11ffd3674822ac4a60ce60af979ff560f6
				return searchNode(key, assistaNodeAVLTree.getLeft());
			}else {
				return searchNode(key, assistaNodeAVLTree.getRight());
			}
		}
	}

	@Override
	public H height(NodeAVLTree<K, V, F, H> node) {
		H height = null;
		if(node != null) {
			height = node.getHeight();
		}
		return height;
	}

	@Override
	public H nodeMax(H nodeHeightA, H nodeHeightB) {
		return (nodeHeightA.compareTo(nodeHeightB) < 0) ? nodeHeightA : nodeHeightB;
	}

	@Override
	public H balanceTree(NodeAVLTree<K, V, F, H> node) {
		H balance;
		if(node != null) {
			balance = height(node.getRight()).compareTo(height(node.getLeft()));
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
