import java.util.*;
public class BSTpractice {
	class Node {
		int data;
		Node left, right;
		public Node(int data) {
			this.data = data;
			this.left = this.right = null;
		}
		
	}
	private Node root;
	public void insert(int data) {
		root = insert(root, data);
		
	}
	private Node insert(Node root, int data) {
		if(root == null) {
			return new Node(data);
		}
		else if(root.data == data) {
			
		}
		if(root.data > data) {
			root.left = insert(root.left, data);
			
		}
		else {
			root.right = insert(root.right, data);
		}
		return root;
	}
	public int getRank(int data) {
		return getRank(root, data);
	}
	private int getRank(Node root, int data) {
		Node temp = root;
		int rank = 1;
		while(temp != null) {
			if(temp.data > data) {
				temp = temp.left;
			}
			else if(temp.data < data) {
				rank += 1 + size(temp.left);
				temp = temp.right;
			}
			else {
				rank += size(temp.left);
				return rank;
			}
		}
		return -1;
	}
	private int size(Node n) {
		if(n == null) {
			return 0;
		}
		return 1 + size(n.left) + size(n.right);
	}
	public int diameter_t() {
		return diameter_t(root);
	}
	private int diameter_t(Node root) {
		if(root == null) {
			return 0;
		}
		int a = height(root.left);
		int b = height(root.right);
		
		int n_l = diameter_t(root.left);
		int n_r = diameter_t(root.right);
		
		return Math.max(1 + a + b, Math.max(n_l, n_r));
	}
	private int height(Node n) {
		if(n == null) {
			return 0;
		}
		return 1 + Math.max(height(n.left), height(n.right));
	}
	private class NodewithCoord{
		Node n;
		int coord;
		public NodewithCoord(Node n, int coord) {
			this.n = n;
			this.coord = coord;
		}
		
	}
	public void topView() {
		topView(root);
	}
	private void topView(Node root) {
		Set<Integer> hash = new HashSet<Integer>();
		ArrayList<NodewithCoord> arr = new ArrayList<>();
		Queue<NodewithCoord> q = new LinkedList<>();
		q.add(new NodewithCoord(root, 0));
		while(q.isEmpty() == false) {
			NodewithCoord temp = q.poll();
			if(hash.contains(temp.coord) == false) {
				//System.out.println(temp.n.data);
				arr.add(temp);
				hash.add(temp.coord);
			}
			if(temp.n.left != null) {
				q.add(new NodewithCoord(temp.n.left, temp.coord -1));
			}
			if(temp.n.right != null) {
				q.add(new NodewithCoord(temp.n.right, temp.coord + 1));
			}
		}
		Collections.sort(arr, new Sort());
		for(NodewithCoord temp: arr) {
			System.out.println(temp.n.data);
		}
		
	}
	class Sort implements Comparator<NodewithCoord>{
		public int compare(NodewithCoord a, NodewithCoord b) {
			if(a.coord > b.coord) {
				return 1;
			}
			else if(a.coord < b.coord) {
				return -1;
			}
			return 0;
		}
	}
	public void bottomView() {
		bottomView(root);
	}
	private void bottomView(Node root) {
		
		Queue<NodewithCoord> q = new LinkedList<>();
		TreeMap<Integer, Integer> hash = new TreeMap<>();
		
		q.add(new NodewithCoord(root, 0));
		
		while(q.isEmpty() == false) {
			NodewithCoord temp = q.poll();
			
			hash.put(temp.coord, temp.n.data);
			if(temp.n.left != null) {
				q.add(new NodewithCoord(temp.n.left, temp.coord - 1));
			}
			if(temp.n.right != null) {
				q.add(new NodewithCoord(temp.n.right, temp.coord + 1));
			}
		}
		for(int i: hash.keySet()) {
			System.out.println(i + "  " + hash.get(i));
		}
	}
	public void zigzag() {
		zigzag(root);
	}
	private void zigzag(Node root) {
		Stack<Node> curr = new Stack<>();
		Stack<Node> next = new Stack<>();
		boolean ltr = true;
		curr.push(root);
		while(curr.isEmpty() == false) {
			Node temp = curr.pop();
			System.out.println(temp.data);
			if(ltr == true) {
				if(temp.left != null) {
					next.push(temp.left);
				}
				if(temp.right != null) {
					next.push(temp.right);
				}
			}
			else {
				if(temp.right != null) {
					next.push(temp.right);
				}
				if(temp.left != null) {
					next.push(temp.left);
				}
			}
			if(curr.isEmpty() == true) {
				Stack<Node> t = curr;
				curr = next;
				next = t;
				ltr = !ltr;
			}
		}
	}
	public void sideView() {
		sideView(root);
	}
	class NodewithLevel {
		Node n;
		int level;
		public NodewithLevel(Node n, int level) {
			this.n = n;
			this.level = level;
		}
	}
	private void sideView(Node root) {
		TreeMap<Integer, Integer> hash = new TreeMap<>();
		
		Queue<NodewithLevel> q = new LinkedList<>();
		q.add(new NodewithLevel(root, 0));
		
		while(q.isEmpty() == false) {
			NodewithLevel temp = q.poll();
			if(hash.containsKey(temp.level) == false) {
				//System.out.println(temp.n.data);
				hash.put(temp.level, temp.n.data);
			}
			
			if(temp.n.right != null) {
				q.add(new NodewithLevel(temp.n.right, temp.level + 1));
			}
			if(temp.n.left != null) {
				q.add(new NodewithLevel(temp.n.left, temp.level+1));
			}
		}
		
		for(int p : hash.keySet()) {
			System.out.println(hash.get(p));
		}
	}
	public void circumference() {
		circumference(root);
	}
	private void printLeaf(Node root) {
		if(root == null) {
			return;
		}
		printLeaf(root.left);
		if(root.left == null && root.right == null) {
			System.out.println(root.data);
		}
		printLeaf(root.right);
		
	}
	private void printRight(Node root) {
		if(root == null) {
			return;
		}
		if(root.right != null) {
			printRight(root.right);
			System.out.println(root.data);
			
		}
		else if(root.left != null) {
			printRight(root.left);
			System.out.println(root.data);
			
		}
	}
	private void printLeft(Node root) {
		if(root == null) {
			return;
		}
		if(root.left != null) {
			System.out.println(root.data);
			printLeft(root.left);
		}
		else if(root.right != null) {
			System.out.println(root.data);
			printLeft(root.right);
		}
	}
	private void circumference(Node root) {
		System.out.println(root.data);
		printLeft(root.left);
		printLeaf(root);
		printRight(root.right);
	}
}
