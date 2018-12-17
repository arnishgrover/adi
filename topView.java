import java.util.*;
class Tree {
    class Node {
        int data;
        Node left, right;
        Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }
    }
    Node root;
    Tree() {
        root =  null;
    }
    public void insert(int data) {
        root = insert(root, data);
    }
    private Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        }
        if(root.data > data) {
            root.left = insert(root.left, data);
        }
        else {
            root.right = insert(root.right, data);
        }
        return root;
    }
    public void topV() {
        topV(root);
    }
    class NodewithCoord {
        Node n;
        int level;
        NodewithCoord(Node n, int level) {
            this.n = n;
            this.level = level;
        }
    }
    
    private void topV(Node root) {
        Queue<NodewithCoord> q = new LinkedList<>();
        Map<Integer, Node> map = new TreeMap<>();
        q.add(new NodewithCoord(root, 0));
        while(!q.isEmpty()) {
            NodewithCoord temp = q.poll();
            if(!map.containsKey(temp.level)) {
                map.put(temp.level, temp.n);
            }
            if(temp.n.left != null) {
                q.add(new NodewithCoord(temp.n.left, temp.level - 1));
            }
            if(temp.n.right != null) {
                q.add(new NodewithCoord(temp.n.right, temp.level + 1));
            }
        }
        for(Integer i: map.keySet()) {
            System.out.println(map.get(i).data);
        }
        
    }
    public void BottomV() {
        BottomV(root);
    }
    private void BottomV(Node root) {
        Queue<NodewithCoord> q = new LinkedList<>();
        Map<Integer, Node> map = new TreeMap<>();
        q.add(new NodewithCoord(root, 0));
        while(!q.isEmpty()) {
            NodewithCoord temp = q.poll();
            //if(!map.containsKey(temp.level)) {
                map.put(temp.level, temp.n);
            //}
            if(temp.n.left != null) {
                q.add(new NodewithCoord(temp.n.left, temp.level - 1));
            }
            if(temp.n.right != null) {
                q.add(new NodewithCoord(temp.n.right, temp.level + 1));
            }
        }
        for(Integer i: map.keySet()) {
            System.out.println(map.get(i).data);
        }

    }
    public int DiffofOddEven() {

        int sum = 0;
        if(root == null) {
            System.out.println("Null Root");
        }
        DiffofOddEven(root, sum, 0);

        return sum;

    }
    private void DiffofOddEven(Node root, int sum, int level) {
        if(root == null) {
            return;
        }
        if(level % 2 == 0) {
            sum -= root.data;
        }
        else {
            sum += root.data;
        }
        DiffofOddEven(root.left, sum, level + 1);
        DiffofOddEven(root.right, sum, level + 1);
    }
    public int getRank(int data) {
        return getRank(root, data);
    }
    private int getRank(Node root, int data) {
        int rank = 0;
        Node temp = root;
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
    private int size(Node t) {
        if(t == null) {
            return 0;
        } 
        return 1 + size(t.left) + size(t.right);
    }
}  

public class topView {
    public static void main(String[] args) {
        Tree t = new Tree();
        int[] arr = {20,10,30,11,29,28,12,27,13,26,14};
        for(int i: arr) {
            t.insert(i);
        }
        Arrays.sort(arr);
        for(int i = 0; i < arr.length; i++) {
            System.out.println(i + "  " + t.getRank(arr[i]));
        }
    }
}

