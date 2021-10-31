package dataStructures;

import java.util.ArrayList;

public interface IAVLTree<K, V, F, H> {
	
	public void createTree();
	public boolean addNode(K key, V object);
	public boolean deleteNode(NodeAVLTree<K, V> node);
	public int height(NodeAVLTree<K, V> node);
	public int nodeMax(int nodeHeightA, int nodeHeightB);
	public int balanceTree(NodeAVLTree<K, V> node);
	public ArrayList<NodeAVLTree<K, V>> searchNode(K key);
	public NodeAVLTree<K, V> successor(NodeAVLTree<K, V> node);
	public NodeAVLTree<K, V> predecessor(NodeAVLTree<K, V> node);
}
