package dataStructures;

public class BinaryTree  <T> {
	private NodoBinaryTree<Integer> root;

	public void ArbolBinarioBusqueda(){}

	
	public boolean crearArbol(Integer numero) {

		boolean verify = false;

		root = new NodoBinaryTree<>();

		if (root != null) {
			root.setValue(numero);
			verify = true;
		}
		return verify;
	}

	public boolean agregarNodo(Integer numero) {

		boolean verify = false;

		NodoBinaryTree<Integer> nuevoNodo = new NodoBinaryTree<>();
		nuevoNodo.setValue(numero);

		if (root == null) {
			root = nuevoNodo;
			verify = true;

		} else {
			agregarNode(root, nuevoNodo);
		}
		return verify;
	}

	private void agregarNode(NodoBinaryTree<Integer> auxiliar, NodoBinaryTree<Integer> nuevo) {

		if (nuevo.compareTo(auxiliar) == -1 || nuevo.compareTo(auxiliar) == 0) {
			if (auxiliar.getLeft() == null) {
				auxiliar.setLeft(nuevo);
				nuevo.setParent(auxiliar);
			} else {
				agregarNode(auxiliar.getLeft(), nuevo);
			}
		} else {
			if (auxiliar.getRight() == null) {
				auxiliar.setRight(nuevo);
				nuevo.setParent(auxiliar);
			} else {
				agregarNode(auxiliar.getRight(), nuevo);
			}
		}
	}

	
	public boolean eliminarNodo(Integer numero) {

		boolean verificar = false;

		NodoBinaryTree<Integer> nodo = buscarNodo(numero);

		eliminarNode(nodo);

		if (buscarNodo(numero) == null) {
			verificar = true;
		}

		return verificar;
	}

	private void eliminarNode(NodoBinaryTree<Integer> nodo) {

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
			NodoBinaryTree<Integer> unicoHijo;

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
			NodoBinaryTree<Integer> sucesor = min(nodo.getRight());
			nodo.setValue(sucesor.getValue());
			eliminarNode(sucesor);
		}
	}

	private NodoBinaryTree<Integer> min(NodoBinaryTree<Integer> auxiliar) {

		if (auxiliar.getLeft() != null) {
			return min(auxiliar.getLeft());

		} else {
			return auxiliar;
		}
	}

	public NodoBinaryTree<Integer> buscarNodo(Integer numero) {
		return buscarNode(root, numero);
	}

	private NodoBinaryTree<Integer> buscarNode(NodoBinaryTree<Integer> auxiliar, Integer numero) {

		if (auxiliar == null || auxiliar.getValue() == numero) {
			return auxiliar;
		} else {
			if (numero <= auxiliar.getValue()) {
				return buscarNode(auxiliar.getLeft(), numero);
			} else {
				return buscarNode(auxiliar.getRight(), numero);
			}
		}
	}

	public String infoArbol(NodoBinaryTree<Integer> root) {
		String message = "";

		if (root != null) {
			message += infoArbol(root.getLeft());
			message += root.getValue() + " ";
			message += infoArbol(root.getRight());
		}
		return message;
	}

	public NodoBinaryTree<Integer> getRoot() {
		return root;
	}

	public void setRoot(NodoBinaryTree<Integer> root) {
		this.root = root;
	}
}
