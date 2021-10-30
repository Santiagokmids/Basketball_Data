package dataStructures;

import java.util.ArrayList;

public interface IAVLTree<K, V, F, H> {
	
	public void createTree();
	public boolean addNode(K key, V object);
	public boolean deleteNode(NodeAVLTree<K, V, F, H> node);
	public int height(NodeAVLTree<K, V, F, H> node);
	public int nodeMax(int nodeHeightA, int nodeHeightB);
	public int balanceTree(NodeAVLTree<K, V, F, H> node);
	public ArrayList<NodeAVLTree<K, V, F, H>> searchNode(K key);
	public NodeAVLTree<K, V, F, H> successor(NodeAVLTree<K, V, F, H> node);
	public NodeAVLTree<K, V, F, H> predecessor(NodeAVLTree<K, V, F, H> node);
}
