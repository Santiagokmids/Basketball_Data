package dataStructures;

public interface IAVLTree<K, V, F, H> {
	
	public void createTree();
	public boolean addNode(K key, V object);
	public boolean deleteNode(K key, V object);
	public int height(NodeAVLTree<K, V, F, H> node);
	public Integer nodeMax(H nodeHeightA, H nodeHeightB);
	public int balanceTree(NodeAVLTree<K, V, F, H> node);
	public NodeAVLTree<K, V, F, H> searchNode(K key);
}
