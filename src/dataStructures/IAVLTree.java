package dataStructures;

public interface IAVLTree<K, V, F, H> {
	
	public void createTree();
	public boolean addNode(K key, V object, F balanced, H height);
	public boolean deleteNode(K key, V object);
	public H height(NodeAVLTree<K, V, F, H> node);
	public H nodeMax(H nodeHeightA, H nodeHeightB);
	public H balanceTree(NodeAVLTree<K, V, F, H> node);
	public NodeAVLTree<K, V, F, H> searchNode(K key);
}
