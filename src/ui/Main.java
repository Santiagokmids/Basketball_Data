package ui;

public class Main {
	/*
	//Clase Nodo
public class NodoArbolAVL {
	int dato, fe;
	NodoArbolAVL izdo, dcho;
	
	public NodoArbolAVL(int dato){
		this.dato = dato;
		fe = 0;
		izdo = null;
		dcho = null;
	}
}

//Clase arbol AVL
public class ArbolAVL {
	private NodoArbolAVL raiz;
	
	public ArbolAVL() {
		raiz = null;
	}
	
	public NodoArbolAVL buscar(int dato, NodoArbolAVL r) {
		if(r == null) return null;
		
		if(r.dato == dato) {
			return r;
		}else if(r.dato < dato) {
			return buscar(dato, r.izdo);
		}else {
			return buscar(dato, r.izdo);
		}
	}
	
	public int obtenerFE(NodoArbolAVL r) {
		if(r == null) return -1;
		else return r.fe;
	}
	
	public NodoArbolAVL rotacionIzquierda(NodoArbolAVL r) {
		NodoArbolAVL auxiliar = r.izdo;
		r.dcho = auxiliar.dcho;
		auxiliar.dcho = r;
		r.fe = Math.max(obtenerFE(r.izdo), obtenerFE(r.dcho)) + 1;
		auxiliar.fe = Math.max(obtenerFE(auxiliar.izdo), obtenerFE(auxiliar.dcho)) + 1;
		return auxiliar;
	}
	
	public NodoArbolAVL rotacionDerecha(NodoArbolAVL r) {
		NodoArbolAVL auxiliar = r.dcho;
		r.dcho = auxiliar.izdo;
		auxiliar.izdo = r;
		r.fe = Math.max(obtenerFE(r.izdo), obtenerFE(r.dcho)) + 1;
		auxiliar.fe = Math.max(obtenerFE(auxiliar.izdo), obtenerFE(auxiliar.dcho)) + 1;
		return auxiliar;
	}
	
	public NodoArbolAVL rotacionDobleIzquierda(NodoArbolAVL r) {
		NodoArbolAVL temporal;
		r.izdo =  rotacionDerecha(r.izdo);
		temporal = rotacionIzquierda(r);
		return temporal;
	}
	
	public NodoArbolAVL rotacionDobleDerecha(NodoArbolAVL r) {
		NodoArbolAVL temporal;
		r.dcho =  rotacionIzquierda(r.dcho);
		temporal = rotacionDerecha(r);
		return temporal;
	}
}
*/	
}
