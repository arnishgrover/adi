import java.util.*;
class tree {
    class Node {
        int data;
        Node left, right;
        Node(int data) {
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
        if(root.data > data) {
            root.left = insert(root.left, data);
        }
        else {
            root.right = insert(root.right, data);
        }
        return root;
    }
    public void deleteNode(int data) {
        root = deleteNode(root, data);
    }
    private int findMax(Node root) {
        while(root.right != null) {
            root = root.right;
        }
        return root.data;
    }
    private int findMin(Node root) {
        while(root.left != null) {
            root = root.left;
        }
        return root.data;
    }
    private Node deleteNode(Node root, int data) {
        if(root.data == data) {
            if(root.left == null) {
                return root.right;
            }
            else if(root.right == null) {
                return root.left;
            }
            else {
                int p = findMax(root.left);
                root.data = p;
                root.left = deleteNode(root.left, p);
                return root;
            }
            
        }
        else if(root.data > data) {
            root.left = deleteNode(root.left, data);
        }
        else {
            root.right = deleteNode(root.right, data);
        }
        return root;
    }
    public void inorder() {
        inorder(root);
    }
    private void inorder(Node root) {
        if(root == null) {
            return;
        }
        inorder(root.left);
        System.out.println(root.data + " ");
        inorder(root.right);
    }   
    public boolean validTree() {
        int lb = findMin(root);
        int ub = findMax(root);
        return validTree(root, lb, ub);
    }
    private boolean validTree(Node root, int lb, int ub) {
        if(root == null) {
            return true;
        }
        if(root.data < lb || root.data > ub) {
            return false;
        }
        return validTree(root.left, lb, root.data) && validTree(root.right, root.data, ub);
    }
    public int kthHighest(int k) {
        return kthHighest(root, k);
    } 
    private int kthHighest(Node root, int k) {
        ArrayList<Integer> arr = new ArrayList<>();
        Stack<Node> st = new Stack<Node>();
        Node curr = root;
        while(curr != null || st.isEmpty() == false) {
            while(curr != null) {
                st.push(curr);
                curr = curr.left;
            }
            curr = st.pop();
            arr.add(curr.data);
            curr = curr.right;
        }
        return arr.get(arr.size() - k);
    }
    public int getRank(int data) {
        return getRank(root, data);
    }
    private int size(Node root) {
        if(root == null) {
            return 0;
        }
        return 1 + size(root.left) + size(root.right);
    }
    private int getRank(Node root, int data) {
        int rank = 1;
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
            
}
public class Tree {
    public static void main(String[] args) {
        tree t = new tree();
        int[] arr = {14,7,17,4,12,53,11,13,8};
        for(int i: arr) {
            t.insert(i);
        }
        //t.inorder();
        //t.deleteNode(12);
        //t.inorder();
        //t.invalid();
        //System.out.println(t.validTree());
        //System.out.println(t.kthHighest(8));   
        System.out.println(t.getRank(54));
    }
}
