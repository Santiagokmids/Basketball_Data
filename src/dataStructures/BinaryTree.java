package dataStructures;

import java.io.Serializable;

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

	private void addNode(NodoBinaryTree<T,K> current, NodoBinaryTree<T,K> newNode) {

		if((newNode.getKey()).compareTo(current.getKey()) <= 0) {

			if(current.getLeft() == null) {
				current.setLeft(newNode);
				newNode.setParent(current);

			}else {
				addNode(current.getLeft(), newNode);
			}	
		}else {
			if(current.getRight() == null) {
				current.setRight(newNode);
				newNode.setParent(current);

			}else {
				addNode(current.getRight(), newNode);
			}
		}
	}

	@Override
	public boolean deleteNode(K key) {

		boolean verify = false;

		NodoBinaryTree<T,K> node = searchNode(key);
		if(node!= null) {
			deleteNode(node);

			if (searchNode(key) == null) {
				verify = true;
			}
		}
		return verify;
	}

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
	public NodoBinaryTree<T, K> searchNode(K key) {
		return searchNode(root, key);
	}

	private NodoBinaryTree<T,K> searchNode(NodoBinaryTree<T,K> current, K key) {

		NodoBinaryTree<T,K> newN = new NodoBinaryTree<T,K>();

		if (current == null || current.getValue() == key) {
			newN = current;

		} else {

			if (current.getLeft() != null && key.compareTo(current.getKey())<= 0) {
				
				newN = searchNode(current.getLeft(), key);

			} else if(current.getRight() != null){
				newN = searchNode(current.getRight(), key);
			}
		}

		return newN;
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
