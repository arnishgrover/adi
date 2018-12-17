
package com.uca.ds.trees;

import java.util.ArrayList;
import java.util.Iterator;


public class AVL<K extends Comparable<K>, V> implements Iterable<K> {
	private Node root = null;

	private class Node {
		private K key;
		private V val;
		private Node left, right;
		

		public Node(K key, V val) {
			this.key = key;
			this.val = val;
			
			left = right = null;
		}
	}
	public void insert(K key,V val) {
		System.out.println("\nInsertion of: " + key);
		root = insert(root, key, val);
		
	}
	private Node insert(Node root, K key, V val) {
		if(root == null) {
			return new Node(key, val);
		}
		int cmp = key.compareTo(root.key);
		if(cmp > 0) {
			root.right = insert(root.right, key, val);
		}
		else {
			root.left = insert(root.left, key, val);
		}
		
		int bal = height(root.left) - height(root.right);
		if(bal > 1) {
			if(height(root.left.left) - height(root.left.right) >= 0) {
				root = rightRotate(root);
			}
			else {
				root.left = leftRotate(root.left);
				root = rightRotate(root);
			}
		}
		else if(bal < -1) {
			if(height(root.right.right) - height(root.right.left) >= 0) {
				root = leftRotate(root);
			}
			else {
				root.right = rightRotate(root.right);
				root = leftRotate(root);
			}
		}
		return root;
		
	}
	private Node leftRotate(Node n) {
		System.out.println("Left Rotating on " + n.key);
		Node t = n.right;
		n.right = t.left;
		t.left = n;
		
		return t;
	}
	private Node rightRotate(Node n) {
		System.out.println("Right Rotating on " + n.key);
		Node t = n.left;
		n.left = t.right;
		t.right = n;

		return t;
	}
	private int height(Node root) {
		if(root == null) {
			return 0;
		}
		return 1 + Math.max(height(root.left), height(root.right));
	}
	public int getRank(K key) {
		return getRank(root, key);
	}
	private int getRank(Node root, K key) {
		ArrayList<K> list = new ArrayList<K>();
		inorder(root, list);
		return list.indexOf(key);
	}
	private void inorder(Node root, ArrayList<K> list) {
		if(root == null) {
			return;
		}
		inorder(root.left, list);
		list.add(root.key);
		inorder(root.right, list);
	}
	public V get(K key) {
		Node temp = get(root, key);
		return temp == null ? null : temp.val;
		
	}
	private Node get(Node root, K key) {
		if(root == null) {
			return null;
		}
		int cmp = key.compareTo(root.key);
		if(cmp == 0) {
			return root;
		}
		else if(cmp > 0) {
			return get(root.right, key);
		}
		return get(root.left, key);
	}
	public int height() {
		return height(root);
	}
	public Iterator<K> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
