package dataStructures;

public class BinaryTree <T,K>implements  IBinaryTree<T,K>{
	
	private NodoBinaryTree<T,K> root;
	
	public void ArbolBinarioBusqueda() {
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
		
		int new1 = (int) newNode.getValue();
		int aux = (int) current.getValue();
		
		if (new1 <= aux) {
			if (current.getLeft() == null) {
				current.setLeft(newNode);
				newNode.setParent(current);
				
			} else {
				addNode(current.getLeft(), newNode);
			}
			
		} else {
			if (current.getRight() == null) {
				current.setRight(newNode);
				newNode.setParent(current);
				
			} else {
				addNode(current.getRight(), newNode);
			}
		}
	}

	@Override
	public boolean deleteNode(K key) {
		
		boolean verify = false;

		NodoBinaryTree<T,K> node = searchNode(key);

		deleteNode(node);

		if (searchNode(key) == null) {
			verify = true;
		}

		return verify;
	}

	private void deleteNode(NodoBinaryTree<T,K> node) {

		if (node.getLeft() == null && node.getRight() == null) {

			if (node == root) {
				root = null;

			} else if (node == node.getParent().getLeft()) {
				node.getParent().setLeft(null);

			} else {
				node.getParent().setRight(null);
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
				deleteNode(successor);
				
			}else {
				NodoBinaryTree<T,K> predecessor = successor(node.getRight());
				node.setValue(predecessor.getValue());
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
			
			int value = (int) key;
			int valueAux = (int) current.getValue();
			
			if ( value <= valueAux) {
				newN = searchNode(current.getLeft(), key);
				
			} else {
				newN = searchNode(current.getRight(), key);
			}
		}
		
		return newN;
	}
	
	public String infoTree() {
		return infoTree(root);
	}
	
	public String infoTree(NodoBinaryTree<T,K> node) {
		String message = "";

		if (root != null) {
			message += infoTree(node.getLeft());
			message += node.getValue() + " ";
			message += infoTree(node.getRight());
		}
		return message;
	}

	public NodoBinaryTree<T,K> getRoot() {
		return root;
	}

	public void setRoot(NodoBinaryTree<T,K> root) {
		this.root = root;
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
	
}
