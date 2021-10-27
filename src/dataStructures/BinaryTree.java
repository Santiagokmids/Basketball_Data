package dataStructures;
public class BinaryTree  <T,K>implements  IBinaryTree<T,K>{
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

		NodoBinaryTree<T,K> nuevoNodo = new NodoBinaryTree<>();
		nuevoNodo.setValue(value);

		if (root == null) {
			root = nuevoNodo;
			verify = true;

		} else {
			addNode(root, nuevoNodo);
		}
		return verify;
	}

	private void addNode(NodoBinaryTree<T,K> auxiliar, NodoBinaryTree<T,K> nuevo) {
		int new1 = (int)nuevo.getValue();
		int aux = (int) auxiliar.getValue();
		if (new1 <= aux) {
			if (auxiliar.getLeft() == null) {
				auxiliar.setLeft(nuevo);
				nuevo.setParent(auxiliar);
			} else {
				addNode(auxiliar.getLeft(), nuevo);
			}
		} else {
			if (auxiliar.getRight() == null) {
				auxiliar.setRight(nuevo);
				nuevo.setParent(auxiliar);
			} else {
				addNode(auxiliar.getRight(), nuevo);
			}
		}
	}



	@Override
	public boolean deleteNode(K k) {
		boolean verificar = false;

		NodoBinaryTree<T,K> nodo = searchNode(k);

		deleteNode(nodo);

		if (searchNode(k) == null) {
			verificar = true;
		}

		return verificar;
	}
	

	private void deleteNode(NodoBinaryTree<T,K> nodo) {

		if (nodo.getLeft() == null && nodo.getRight() == null) {

			if (nodo == root) {
				root = null;

			} else if (nodo == nodo.getParent().getLeft()) {
				nodo.getParent().setLeft(null);

			} else {
				nodo.getParent().setRight(null);
			}
			nodo.setParent(null);

		} else if (nodo.getLeft() == null || nodo.getRight() == null) {
			NodoBinaryTree<T,K> unicoHijo;

			if (nodo.getLeft() != null) {
				unicoHijo = nodo.getLeft();
				nodo.setLeft(null);

			} else {
				unicoHijo = nodo.getRight();
				nodo.setRight(null);
			}
			unicoHijo.setParent(nodo.getParent());

			if (nodo == root) {
				root = unicoHijo;

			} else if (nodo == nodo.getParent().getLeft()) {
				nodo.getParent().setLeft(unicoHijo);

			} else {
				nodo.getParent().setRight(unicoHijo);
			}
			nodo.setParent(null);

		} else {
			NodoBinaryTree<T,K> sucesor = min(nodo.getRight());
			nodo.setValue(sucesor.getValue());
			deleteNode(sucesor);
		}
	}


	private NodoBinaryTree<T,K> min(NodoBinaryTree<T,K> auxiliar) {

		if (auxiliar.getLeft() != null) {
			return min(auxiliar.getLeft());

		} else {
			return auxiliar;
		}
	}

	
	
	@Override
	public NodoBinaryTree<T, K> searchNode(K k) {
		return searchNode(root, k);
	}

	private NodoBinaryTree<T,K> searchNode(NodoBinaryTree<T,K> auxiliar, K k) {

		if (auxiliar == null || auxiliar.getValue() == k) {
			return auxiliar;
		} else {
			int value = (int) k ;
			int valueAux = (int) auxiliar.getValue();
			if ( value <= valueAux) {
				return searchNode(auxiliar.getLeft(), k);
			} else {
				return searchNode(auxiliar.getRight(), k);
			}
		}
	}

	public String infoArbol(NodoBinaryTree<T,K> root) {
		String message = "";

		if (root != null) {
			message += infoArbol(root.getLeft());
			message += root.getValue() + " ";
			message += infoArbol(root.getRight());
		}
		return message;
	}

	public NodoBinaryTree<T,K> getRoot() {
		return root;
	}

	public void setRoot(NodoBinaryTree<T,K> root) {
		this.root = root;
	}
	
}
