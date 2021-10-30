package dataStructures;

import java.util.ArrayList;

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
			System.out.println("omefa");
			root = newNodeAVLTree;
			newNodeAVLTree.setBalanced(balanceTree( newNodeAVLTree));
			verify = true;
		}else {
			verify = addNode(key, root, newNodeAVLTree);
		}
		System.out.println(root.getHeight()+" raiz");
		if(root.getRight() != null) {
			System.out.println(root.getRight().getHeight()+" der");
		}if(root.getLeft() != null){
			System.out.println(root.getLeft().getHeight()+" iz");
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
		}else {
			if(assistaNodeAVLTree.getRight() == null) {
				assistaNodeAVLTree.setRight(newNodeAVLTree);
				newNodeAVLTree.setDad(assistaNodeAVLTree);
				assistaNodeAVLTree.setBalanced(balanceTree(assistaNodeAVLTree));
				verify = true;
			}else {
				addNode(key, assistaNodeAVLTree.getRight(), newNodeAVLTree);
			}
		}
		balanceTree(root);
		updateHeight(root);
		return verify;
	}

	@Override
	public boolean deleteNode(NodeAVLTree<K, V, F, H> node) {

		boolean verify = false;

		if (node.getLeft() == null && node.getRight() == null) {

			if (node == root) {
				root = null;
				verify = true;

			} else if (node == node.getDad().getLeft()) {
				node.getDad().setLeft(null);
				verify = true;

			} else {
				node.getDad().setRight(null);
				verify = true;
			}
			node.setDad(null);

		} else if (node.getLeft() == null || node.getRight() == null) {
			NodeAVLTree<K, V, F, H> onlyChild;

			if (node.getLeft() != null) {
				onlyChild = node.getLeft();
				node.setLeft(null);
				verify = true;

			} else {
				onlyChild = node.getRight();
				node.setRight(null);
				verify = true;
			}
			onlyChild.setDad(node.getDad());

			if (node == root) {
				root = onlyChild;

			} else if (node == node.getDad().getLeft()) {
				node.getDad().setLeft(onlyChild);
				verify = true;

			} else {
				node.getDad().setRight(onlyChild);
				verify = true;
			}
			node.setDad(null);

		} else {
			NodeAVLTree<K, V, F, H> successor = successor(node.getRight());

			if(successor != null) {
				node.setObject(successor.getObject());
				node.setKey(successor.getKey());
				deleteNode(successor);

			}else {
				NodeAVLTree<K, V, F, H> predecessor = successor(node.getRight());
				node.setObject(predecessor.getObject());
				node.setKey(predecessor.getKey());
				deleteNode(predecessor);
			}
		}
		balanceTree(root);
		updateHeight(root);
		return verify;
	}

	@Override
	public NodeAVLTree<K, V, F, H> successor(NodeAVLTree<K, V, F, H> current) {

		NodeAVLTree<K, V, F, H> newNode = new NodeAVLTree<K, V, F, H>(null, null);

		if (current.getLeft() != null) {
			newNode = successor(current.getLeft());

		} else {
			newNode = current;
		}

		return newNode;
	}

	@Override
	public NodeAVLTree<K, V, F, H> predecessor(NodeAVLTree<K, V, F, H> current) {

		NodeAVLTree<K, V, F, H> newNode = new NodeAVLTree<K, V, F, H>(null, null);

		if (current.getRight() != null) {
			newNode = predecessor(current.getLeft());

		} else {
			newNode = successor(root);
		}

		return newNode;
	}

	public void traslate(int balance, NodeAVLTree<K, V, F, H> node) {
		
		switch(balance) {
		case -2: 
			balanceTree(node.getLeft());
			if(node.getLeft().getBalanced() != 1) {
				rigthTraslate(node);
			}else {
				doubleRightTranslate(node);
			}
			break;
			
		case 2:
			balanceTree(node.getLeft());
			if(node.getLeft().getBalanced() != -1) {
				leftTraslate(node);
			}else {
				doubleLeftTranslate(node);
			}
			break;
		}
		
	}

	public NodeAVLTree<K, V, F, H> leftTraslate(NodeAVLTree<K, V, F, H> node) {

		NodeAVLTree<K, V, F, H> current = node.getLeft();

		node.getRight().setRight(current.getRight());
		current.setRight(node);
		
		updateHeight(node);
		updateHeight(current);
		balanceTree(node);
		balanceTree(current);

		return current;
	}

	public NodeAVLTree<K, V, F, H> rigthTraslate( NodeAVLTree<K, V, F, H> node) {
		NodeAVLTree<K, V, F, H> current = node.getRight();

		node.setRight(current.getLeft());
		current.setLeft(node);
		
		updateHeight(node);
		updateHeight(current);
		balanceTree(node);
		balanceTree(current);
		
		return current;
	}

	public NodeAVLTree<K, V, F, H> doubleLeftTranslate(NodeAVLTree<K, V, F, H> node) {
		NodeAVLTree<K, V, F, H> current;

		node.setLeft(rigthTraslate(node.getLeft()));
		current = leftTraslate(node);

		return current;
	}

	public NodeAVLTree<K, V, F, H> doubleRightTranslate(NodeAVLTree<K, V, F, H> node) {
		NodeAVLTree<K, V, F, H> current;

		node.setRight(leftTraslate(node.getRight()));
		current = rigthTraslate(node);

		return current;
	}

	@Override
	public ArrayList<NodeAVLTree<K, V, F, H>> searchNode(K key) {
		ArrayList<NodeAVLTree<K, V, F, H>> players = new ArrayList<NodeAVLTree<K, V, F, H>>();
		boolean stop = false;

		return searchNode(key, root,players, stop);
	}

	public ArrayList<NodeAVLTree<K, V, F, H>> searchNode(K key, NodeAVLTree<K, V, F, H> assistaNodeAVLTree, ArrayList<NodeAVLTree<K, V, F, H>> players, boolean stop) {

		if(assistaNodeAVLTree == null) {
			return players;
		}
		if(assistaNodeAVLTree.getKey() == key) {
			stop = true;
			players.add(assistaNodeAVLTree);

		}if(assistaNodeAVLTree.getKey() != key && stop) {
			return players;
		}
		else {

			if(key.compareTo(assistaNodeAVLTree.getKey()) <= 0) {
				return searchNode(key, assistaNodeAVLTree.getLeft(),players,stop);

			}else {
				return searchNode(key, assistaNodeAVLTree.getRight(),players,stop);
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
			node.setBalanced(balance);
		}
		if(balance < -1 || balance > 1) {
			traslate(balance, node);
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
