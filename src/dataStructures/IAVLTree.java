package dataStructures;

public interface IAVLTree<K, V, F> {
	
	public void createTree();
	public boolean addNode(K key, V object, F balanced);
	public boolean deleteNode(K key, V object, F balanced);
	public NodeAVLTree<K, V, F> searchNode(K key, V object, F balanced);
}
