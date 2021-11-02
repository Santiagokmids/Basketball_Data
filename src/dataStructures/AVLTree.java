package dataStructures;

import java.io.Serializable;
import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V >implements IAVLTree<K, V>, Serializable{

	private static final long serialVersionUID = 1L;

	private NodeAVLTree<K, V> root;

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

		NodeAVLTree<K, V> newNodeAVLTree = new NodeAVLTree<K, V>(key, object);

		if(root == null) {
			root = newNodeAVLTree;
			verify = true;
		}else {
			verify = addNode(root, newNodeAVLTree);
		}

		return verify;
	}

	public void updateHeight(NodeAVLTree<K, V> nodeAVLTree) {

		if(nodeAVLTree != null && (nodeAVLTree.getLeft() != null || nodeAVLTree.getRight() != null)) {
			nodeAVLTree.setHeight(1 + nodeMax(height(nodeAVLTree.getLeft()), height(nodeAVLTree.getRight())));
		}
		else if(nodeAVLTree.getLeft() == null && nodeAVLTree.getRight() == null) {
			nodeAVLTree.setHeight(1);
		}

		if(nodeAVLTree.getDad() != null) {
			updateHeight(nodeAVLTree.getDad());
		}
	}

	public boolean addNode(NodeAVLTree<K, V> assistaNodeAVLTree, NodeAVLTree<K, V> newNodeAVLTree) {
		boolean verify = false;
		if(newNodeAVLTree.getKey()!= null && assistaNodeAVLTree.getKey()!= null && newNodeAVLTree.getKey().compareTo(assistaNodeAVLTree.getKey()) <= 0) { //significa que el nuevo nodo es menor al que ya estaba

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
	public boolean deleteNode(NodeAVLTree<K, V> node) {

		boolean verify = false;

		if (node.getLeft() == null && node.getRight() == null) {

			if (node == root) {
				root = null;
				updateHeight(node);
				verify = true;

			} else if (node.getDad() != null && node == node.getDad().getLeft()) {
				updateHeight(node);
				node.getDad().setLeft(null);
				verify = true;

			} else if(node.getDad() != null){
				updateHeight(node);
				node.getDad().setRight(null);
				verify = true;
			}
			node.setDad(null);

		} else if (node.getLeft() == null || node.getRight() == null) {
			NodeAVLTree<K, V> onlyChild;

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
			NodeAVLTree<K, V> successor = successor(node.getRight());

			if(successor != null) {
				node.setObject(successor.getObject());
				node.setKey(successor.getKey());
				deleteNode(successor);

			}else {
				NodeAVLTree<K, V> predecessor = predecessor(node.getLeft());
				node.setObject(predecessor.getObject());
				node.setKey(predecessor.getKey());
				deleteNode(predecessor);
			}
		}
		balanceTree(root);
		return verify;
	}

	@Override
	public NodeAVLTree<K, V> successor(NodeAVLTree<K, V> current) {

		NodeAVLTree<K, V> newNode = new NodeAVLTree<K, V>(null, null);

		if (current.getLeft() != null) {
			newNode = successor(current.getLeft());

		} else {
			newNode = current;
		}

		return newNode;
	}

	@Override
	public NodeAVLTree<K, V> predecessor(NodeAVLTree<K, V> current) {

		NodeAVLTree<K, V> newNode = new NodeAVLTree<K, V>(null, null);

		if (current.getRight() != null) {
			newNode = predecessor(current.getLeft());

		} else {
			newNode = successor(root);
		}

		return newNode;
	}

	public void traslate(int balance, NodeAVLTree<K, V> node) {
		switch(balance) {
		case 2: 
			if(node.getLeft() != null && node.getLeft().getBalanced() != 1) {
				rigthTraslate(node);

			}else if(node.getLeft() != null && node.getLeft().getBalanced() == 1){
				doubleRightTranslate(node);
			}
			break;

		case -2:
			if(node.getRight() != null && node.getRight().getBalanced() != -1) {
				leftTraslate(node);

			}else if(node.getRight() != null && node.getRight().getBalanced() == -1){
				doubleLeftTranslate(node);
			}
			break;
		}

	}

	public NodeAVLTree<K, V> leftTraslate(NodeAVLTree<K, V> node) {

		NodeAVLTree<K, V> currentA = node.getRight();
		NodeAVLTree<K, V> currentB = currentA.getLeft();

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

	public NodeAVLTree<K, V> rigthTraslate( NodeAVLTree<K, V> node) {

		NodeAVLTree<K, V> currentA = node.getLeft();
		NodeAVLTree<K, V> currentB = currentA.getRight();

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

	public NodeAVLTree<K, V> doubleLeftTranslate(NodeAVLTree<K, V> node) {
		NodeAVLTree<K, V> current;

		node.setRight(rigthTraslate(node.getRight()));
		current = leftTraslate(node);

		return current;
	}

	public NodeAVLTree<K, V> doubleRightTranslate(NodeAVLTree<K, V> node) {
		NodeAVLTree<K, V> current;

		node.setLeft(leftTraslate(node.getLeft()));
		current = rigthTraslate(node);

		return current;
	}

	@Override
	public ArrayList<V> searchNode(K key) {
		ArrayList<V> players = new ArrayList<V>();
		return searchNode(key, root,players);
	}

	public ArrayList<V> searchNode(K key, NodeAVLTree<K, V> assistaNodeAVLTree, ArrayList<V> players) {

		if(assistaNodeAVLTree == null) {
			return players;

		}else if(assistaNodeAVLTree.getKey().equals(key)) {
			players.add(assistaNodeAVLTree.getObject());
			
			if(key.compareTo(assistaNodeAVLTree.getKey()) <= 0) {
				return searchNode(key, assistaNodeAVLTree.getLeft(),players);

			}else {
				return searchNode(key, assistaNodeAVLTree.getRight(),players);
			}
		}
		else {

			if(key.compareTo(assistaNodeAVLTree.getKey()) <= 0) {
				return searchNode(key, assistaNodeAVLTree.getLeft(),players);

			}else {
				return searchNode(key, assistaNodeAVLTree.getRight(),players);
			}
		}
	}

	public ArrayList<NodeAVLTree<K, V>> searchNodeObject(K key){
		ArrayList<NodeAVLTree<K, V>> players = new ArrayList<NodeAVLTree<K,V>>();
		return searchNodeObject(root, key, players);
	}

	public ArrayList<NodeAVLTree<K, V>> searchNodeObject(NodeAVLTree<K, V> current, K key, ArrayList<NodeAVLTree<K, V>> players) {

		if(current == null) {
			return players;

		}else if(current.getKey().equals(key)) {
			players.add(current);
			if(key.compareTo(current.getKey()) <= 0) {
				return searchNodeObject(current.getLeft(),key,players);

			}else {
				return searchNodeObject(current.getRight(),key,players);
			}

		}
		else {
			if(key.compareTo(current.getKey()) <= 0) {
				return searchNodeObject(current.getLeft(),key,players);

			}else {
				return searchNodeObject(current.getRight(),key,players);
			}
		}
	}

	@Override
	public int height(NodeAVLTree<K, V> node) {
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
	public int balanceTree(NodeAVLTree<K, V> node) {
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

	public NodeAVLTree<K, V> getRoot() {
		return root;
	}

	public void setRoot(NodeAVLTree<K, V> root) {
		this.root = root;
	}

	public String searchInOrder(NodeAVLTree<K, V> node) {
		String message = "";

		if(node != null) {
			message += searchInOrder(node.getLeft());
			message += node.getKey()+" ";
			message += searchInOrder(node.getRight());
		}
		return message;
	}

}
