package dataStructures;

import java.util.ArrayList;

public interface IBinaryTree<T,K>{
	
	public void createTree();
	public boolean addNode(T value ,K key);
	public void deleteNode(NodoBinaryTree<T,K> node);
	public ArrayList<NodoBinaryTree<T,K>> searchNode(K key);
	public NodoBinaryTree<T,K> successor(NodoBinaryTree<T,K> node);
	public NodoBinaryTree<T,K> predecessor(NodoBinaryTree<T,K> node);
}
