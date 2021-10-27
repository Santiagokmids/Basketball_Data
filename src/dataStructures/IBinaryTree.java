package dataStructures;

public interface IBinaryTree  <T,K>{
	public void createTree();
	public boolean addNode(T value ,K key);
	public boolean deleteNode(K k);
	public NodoBinaryTree<T,K> searchNode(K k);
}
