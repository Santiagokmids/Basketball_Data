package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application{
	
	private BasketballDataGUI basketData;

	public Main() {
		basketData = new BasketballDataGUI(); 
	}
	public static void main(String [] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-pane.fxml"));
		
		fxmlLoader.setController(basketData);
		
		Parent root = fxmlLoader.load();
		Image icon = new Image("/images/icon.png");
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/images/logo.png");
		primaryStage.setScene(scene);
		primaryStage.getIcons().add(icon);
		primaryStage.setTitle("Basketball Data");
		primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.show();
		basketData.loadApp();
	}

/*Clase arbol AVL
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
