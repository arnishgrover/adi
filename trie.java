class Tree {
    private class Node {
        
        Node[] arr;
        boolean end;
        
        Node() {
            arr = new Node[26];
            end = false;
        }
    }
    Node root;
    public Tree() {
        root = new Node();
    }
        
    public void insert(String s) {
        insert(root, s.toLowerCase());
    }
    private void insert(Node root, String s) {
        for(int i = 0; i < s.length(); i++) {
            if(root.arr[s.charAt(i) - 'a'] == null) {
                 root.arr[s.charAt(i) - 'a'] = new Node();
            }
            root = root.arr[s.charAt(i) - 'a'];
        }
        root.end = true;
    }
    public boolean search(String s) {
        return search(root, s.toLowerCase());
    }
    private boolean search(Node root, String s) {
        for(int i = 0; i < s.length(); i++) {
            if(root.arr[s.charAt(i) - 'a'] == null) {
                return false;
            }
            root = root.arr[s.charAt(i)];
        }
        return root.end;
    }
}
    

public class trie {
    public static void main(String[] args) {
        Tree t = new Tree();
        t.insert("Arnish");
        t.insert("Grover");
        assert(t.search("Arnish") == true);
        assert(t.search("Grover") == true);
        assert(t.search("ArnishGrover") == false);
        System.out.println("All cases passed");
    }
}

