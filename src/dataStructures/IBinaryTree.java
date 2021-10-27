package dataStructures;

public interface IBinaryTree  <T,K>{
	
	public void createTree();
	public boolean addNode(T value ,K key);
	public boolean deleteNode(K key);
	public NodoBinaryTree<T,K> searchNode(K key);
	public NodoBinaryTree<T,K> successor(NodoBinaryTree<T,K> node);
	public NodoBinaryTree<T,K> predecessor(NodoBinaryTree<T,K> node);
}
