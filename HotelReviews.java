import java.util.*;
class Trie {
    class Node {
        Map<Character, Node> map;
        boolean end;
        Node() {
            map = new HashMap<>();
            end = false;
        }
    }
    Node root;
    Trie() {
        root = new Node();
    }
    public void insert(String A) {
        insert(root, A);
    }
    private void insert(Node root, String s) {
        for(int i = 0; i < s.length(); i++) {
            if(root.map.containsKey(s.charAt(i)) == false) {
                root.map.put(s.charAt(i), new Node());
            }
            root = root.map.get(s.charAt(i));
        }
        root.end = true;
    }
    public boolean search(String A) {
        return search(root, A);
    }
    private boolean search(Node root, String s) {
        for(int i = 0; i < s.length(); i++) {
            if(root.map.containsKey(s.charAt(i)) == false) {
                return false;
            }
            root = root.map.get(s.charAt(i));
        }
        return root.end;
    }
}

public class HotelReviews {
    
    public static ArrayList<Integer> cal(String m, ArrayList<String> arr) {
        class IndexCount{
            int c;
            int i;
            IndexCount(int i, int c) {
                this.i = i;
                this.c = c;
            }
            public String toString() {
                return "count: " + c + " Index: " + i;
            }
        }   
        class IndexCountC implements Comparator<IndexCount> {
            public int compare(IndexCount a, IndexCount b) {
                return b.c - a.c;
            }
        }

        Trie t = new Trie();
        String[] temp = m.split("_");
        for(int i = 0; i < temp.length; i++) {
            t.insert(temp[i]);
        }
        ArrayList<IndexCount> ares = new ArrayList<>();
        for(String p: arr) {
            String[] q = p.split("_");
            int count = 0;
            for(int i = 0; i < q.length; i++) {
                if(t.search(q[i]) == true) {
                    count++;
                }
            }
            ares.add(new IndexCount(arr.indexOf(p), count));
        }
        //System.out.println(ares);
        Collections.sort(ares, new IndexCountC());
        ArrayList<Integer> res = new ArrayList<>();
        for(IndexCount z: ares) {
            res.add(z.i);
        }
        return res;

    } 
    public static void main(String [] args) {
        Trie t = new Trie();
        String m = "cool_ice_wifi";
        ArrayList<String> arr = new ArrayList<>();
        arr.add("water_is_cool");
        arr.add("cold_ice_drink");
        arr.add("cool_wifi_speed");
        
        System.out.println(HotelReviews.cal(m, arr));   
    }
}
