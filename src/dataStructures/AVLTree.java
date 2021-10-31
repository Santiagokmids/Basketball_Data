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
			root = newNodeAVLTree;
			verify = true;
		}else {
			verify = addNode(root, newNodeAVLTree);
		}

		return verify;
	}

	public void updateHeight(NodeAVLTree<K, V, F, H> nodeAVLTree) {

		if(nodeAVLTree != null && (nodeAVLTree.getLeft() != null || nodeAVLTree.getRight() != null)) {
			nodeAVLTree.setHeight(1 + nodeMax(height(nodeAVLTree.getLeft()), height(nodeAVLTree.getRight())));
		}

		if(nodeAVLTree.getDad() != null) {
			updateHeight(nodeAVLTree.getDad());
		}
	}

	public boolean addNode(NodeAVLTree<K, V, F, H> assistaNodeAVLTree, NodeAVLTree<K, V, F, H> newNodeAVLTree) {
		boolean verify = false;

		if(newNodeAVLTree.getKey().compareTo(assistaNodeAVLTree.getKey()) <= 0) { //significa que el nuevo nodo es menor al que ya estaba

			if(assistaNodeAVLTree.getLeft() == null) {
				assistaNodeAVLTree.setLeft(newNodeAVLTree);
				newNodeAVLTree.setDad(assistaNodeAVLTree);
				verify = true;
			}else {
				addNode(assistaNodeAVLTree.getLeft(), newNodeAVLTree);
			}
		}else {
			if(assistaNodeAVLTree.getRight() == null) {
				assistaNodeAVLTree.setRight(newNodeAVLTree);
				newNodeAVLTree.setDad(assistaNodeAVLTree);
				verify = true;
			}else {
				addNode(assistaNodeAVLTree.getRight(), newNodeAVLTree);//AQUI SE REPITE
			}
		}
		
		updateHeight(newNodeAVLTree.getDad());
		balanceTree(root);
		
		return verify;
	}

	@Override
	public boolean deleteNode(NodeAVLTree<K, V, F, H> node) {

		boolean verify = false;

		if (node.getLeft() == null && node.getRight() == null) {

			if (node == root) {
				root = null;
				updateHeight(node);
				verify = true;

			} else if (node == node.getDad().getLeft()) {
				updateHeight(node);
				node.getDad().setLeft(null);
				verify = true;

			} else {
				updateHeight(node);
				node.getDad().setRight(null);
				verify = true;
			}
			node.setDad(null);

		} else if (node.getLeft() == null || node.getRight() == null) {
			NodeAVLTree<K, V, F, H> onlyChild;

			if (node.getLeft() != null) {
				onlyChild = node.getLeft();
				updateHeight(node);
				node.setLeft(null);
				verify = true;

			} else {
				onlyChild = node.getRight();
				updateHeight(node);
				node.setRight(null);
				verify = true;
			}
			onlyChild.setDad(node.getDad());

			if (node == root) {
				root = onlyChild;

			} else if (node == node.getDad().getLeft()) {
				node.getDad().setLeft(onlyChild);

			} else {
				node.getDad().setRight(onlyChild);
			}
			node.setDad(null);
			updateHeight(node);

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
		System.out.println("entriiio");
		switch(balance) {
		case 2: 
			if(node.getLeft() != null && node.getLeft().getBalanced() != 1) {
				rigthTraslate(node);
				
			}else if(node.getLeft() != null && node.getLeft().getBalanced() == 1){
				doubleRightTranslate(node);
			}
			System.out.println("se cambio a "+node.getKey());
			break;

		case -2:
			if(node.getRight() != null && node.getRight().getBalanced() != -1) {
				leftTraslate(node);

			}else if(node.getRight() != null && node.getRight().getBalanced() == -1){
				doubleLeftTranslate(node);
			}
			System.out.println("se cambio a "+node.getKey());
			break;
		}

	}

	public NodeAVLTree<K, V, F, H> leftTraslate(NodeAVLTree<K, V, F, H> node) {

		NodeAVLTree<K, V, F, H> currentA = node.getRight();
		NodeAVLTree<K, V, F, H> currentB = currentA.getLeft();

		currentA.setLeft(node);
		node.setRight(currentB);

		if(node == root) {
			root = currentA;
			updateHeight(root);
		}

		updateHeight(node);
		updateHeight(currentA);

		return currentA;
	}

	public NodeAVLTree<K, V, F, H> rigthTraslate( NodeAVLTree<K, V, F, H> node) {

		NodeAVLTree<K, V, F, H> currentA = node.getLeft();
		NodeAVLTree<K, V, F, H> currentB = currentA.getRight();

		currentA.setRight(node);
		node.setLeft(currentB);

		if(node == root) {
			root = currentA;
			updateHeight(root);
		}

		updateHeight(node);
		updateHeight(currentA);

		return currentA;
	}

	public NodeAVLTree<K, V, F, H> doubleLeftTranslate(NodeAVLTree<K, V, F, H> node) {
		NodeAVLTree<K, V, F, H> current;

		node.setRight(rigthTraslate(node.getRight()));
		current = leftTraslate(node);

		return current;
	}

	public NodeAVLTree<K, V, F, H> doubleRightTranslate(NodeAVLTree<K, V, F, H> node) {
		NodeAVLTree<K, V, F, H> current;

		node.setLeft(leftTraslate(node.getLeft()));
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
		System.out.println("entramo");
		if(node != null) {
			balance = height(node.getLeft()) - height(node.getRight());
			node.setBalanced(balance);
		}
		if(balance < -1 || balance > 1) {
			System.out.println("entraas");
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

	public void preOrder(NodeAVLTree<K, V, F, H> node) {
		if (node != null) {
			System.out.print(node.getKey() + " ");
			preOrder(node.getLeft());
			preOrder(node.getRight());
		}
	}
}
