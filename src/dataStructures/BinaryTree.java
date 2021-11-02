package dataStructures;

import java.io.Serializable;
import java.util.ArrayList;

public class BinaryTree <T,K extends Comparable <K>>implements  IBinaryTree<T,K>, Serializable{

	private static final long serialVersionUID = 1L;

	private NodoBinaryTree<T,K> root;

	public BinaryTree() {
		createTree();
	}

	@Override
	public void createTree() {
		root = null;
	}

	@Override
	public boolean addNode(T value, K key) {
		boolean verify = false;

		NodoBinaryTree<T,K> newNode = new NodoBinaryTree<>();
		newNode.setKey(key);
		newNode.setValue(value);

		if (root == null) {
			root = newNode;
			verify = true;

		} else {
			addNode(root, newNode);
		}
		return verify;
	}

	private boolean addNode(NodoBinaryTree<T,K> current, NodoBinaryTree<T,K> newNode) {

		if(current != null && newNode != null && (newNode.getKey()).compareTo(current.getKey()) <= 0) {

			if(current.getLeft() == null) {
				current.setLeft(newNode);
				newNode.setParent(current);
				return true;

			}else {
				addNode(current.getLeft(), newNode);
			}	
		}else {
			if(current.getRight() == null) {
				current.setRight(newNode);
				newNode.setParent(current);
				return true;

			}else {
				addNode(current.getRight(), newNode);
			}
		}
		return false;
	}

	@Override
	public void deleteNode(NodoBinaryTree<T,K> node) {

		if (node.getLeft() == null && node.getRight() == null) {

			if (node == root) {
				root = null;

			} else if (node.getParent()  != null  ) {
				if(node == node.getParent().getLeft()) {
					node.getParent().setLeft(null);
				}else {
					node.getParent().setRight(null);
				}

			} 
			node.setParent(null);

		} else if (node.getLeft() == null || node.getRight() == null) {
			NodoBinaryTree<T,K> onlyChild;

			if (node.getLeft() != null) {
				onlyChild = node.getLeft();
				node.setLeft(null);

			} else {
				onlyChild = node.getRight();
				node.setRight(null);
			}
			onlyChild.setParent(node.getParent());

			if (node == root) {
				root = onlyChild;

			} else if (node == node.getParent().getLeft()) {
				node.getParent().setLeft(onlyChild);

			} else {
				node.getParent().setRight(onlyChild);
			}
			node.setParent(null);

		} else {
			NodoBinaryTree<T,K> successor = successor(node.getRight());

			if(successor != null) {
				node.setValue(successor.getValue());
				node.setKey(successor.getKey());
				deleteNode(successor);

			}else {
				NodoBinaryTree<T,K> predecessor = predecessor(node.getLeft());
				node.setValue(predecessor.getValue());
				node.setKey(predecessor.getKey());
				deleteNode(predecessor);
			}

		}
	}

	@Override
	public NodoBinaryTree<T,K> successor(NodoBinaryTree<T,K> current) {

		NodoBinaryTree<T,K> newNode = new NodoBinaryTree<T,K>();

		if (current.getLeft() != null) {
			newNode = successor(current.getLeft());

		} else {
			newNode = current;
		}
		return newNode;
	}

	@Override
	public ArrayList<NodoBinaryTree<T,K>> searchNode(K key) {
		ArrayList<NodoBinaryTree<T,K>> players = new ArrayList<NodoBinaryTree<T,K>>();
		return searchNode(root, key, players);
	}

	private ArrayList<NodoBinaryTree<T,K>> searchNode(NodoBinaryTree<T,K> current, K key, ArrayList<NodoBinaryTree<T,K>> players) {
		
		if(current == null) {
			return players;

		}else if(current.getKey().equals(key)) {
			players.add(current);
			if(key.compareTo(current.getKey()) <= 0) {
				return searchNode(current.getLeft(),key,players);

			}else {
				return searchNode(current.getRight(),key,players);
			}
		}
		else {

			if(key.compareTo(current.getKey()) <= 0) {
				return searchNode(current.getLeft(),key,players);

			}else {
				return searchNode(current.getRight(),key,players);
			}
		}
	}

	@Override
	public NodoBinaryTree<T, K> predecessor(NodoBinaryTree<T, K> current) {

		NodoBinaryTree<T,K> newNode = new NodoBinaryTree<T,K>();

		if (current.getRight() != null) {
			newNode = predecessor(current.getLeft());

		} else {
			newNode = successor(root);
		}

		return newNode;
	}

	public NodoBinaryTree<T,K> getRoot() {
		return root;
	}

	public void setRoot(NodoBinaryTree<T,K> root) {
		this.root = root;
	}
	
	public ArrayList<T> searchNodeMax(K key) {
		ArrayList<T> players = new ArrayList<T>();
		return searchNodeMax(key, root, players);
	}

	public ArrayList<T> searchNodeMax(K key, NodoBinaryTree<T, K> assistaNode, ArrayList<T> players) {
		
		if(assistaNode == null) {
			return players;

		}else if(key.compareTo(assistaNode.getKey()) <= 0) {
			
			players.add(assistaNode.getValue());
			players = searchNodeMax(key, assistaNode.getRight(),players);
			players = searchNodeMax(key, assistaNode.getLeft(),players);
			return players;
		}
		else {

			if(key.compareTo(assistaNode.getKey()) <= 0) {
				return searchNodeMax(key, assistaNode.getLeft(),players);

			}else {
				return searchNodeMax(key, assistaNode.getRight(),players);
			}
		}
	}
	
	public ArrayList<T> searchNodeMin(K key) {
		ArrayList<T> players = new ArrayList<T>();
		return searchNodeMin(key, root, players);
	}

	public ArrayList<T> searchNodeMin(K key, NodoBinaryTree<T, K> assistaNode, ArrayList<T> players) {
		
		if(assistaNode == null) {
			return players;

		}else if(key.compareTo(assistaNode.getKey()) >= 0) {
			
			players.add(assistaNode.getValue());
			players = searchNodeMin(key, assistaNode.getRight(),players);
			players = searchNodeMin(key, assistaNode.getLeft(),players);
			return players;
		}
		else {

			if(key.compareTo(assistaNode.getKey()) <= 0) {
				return searchNodeMin(key, assistaNode.getLeft(),players);

			}else {
				return searchNodeMin(key, assistaNode.getRight(),players);
			}
		}
	}

	public String searchInOrder(NodoBinaryTree<T, K> node) {
		String message = "";

		if(node != null) {
			message += searchInOrder(node.getLeft());
			message += node.getKey()+" ";
			message += searchInOrder(node.getRight());
		}
		return message;
	}
	public NodoBinaryTree<T,K> searchNodeObject(K key){
		return searchNodeObject(root, key);
	}
	
	public NodoBinaryTree<T,K> searchNodeObject(NodoBinaryTree<T,K> current, K key) {
		
		if(current == null || current.getKey() == key) {
			return current;
		}else {
			if(key.compareTo(current.getKey()) <= 0) {
				return searchNodeObject(current.getLeft(), key);
			}else {
				return searchNodeObject(current.getRight(), key);
			}
		}
	}
}
