import java.util.*;
class G {
    int v;
    List<LinkedList<Integer>> arr;
    public G(int s) {
        this.v = s;
        this.arr = new ArrayList<>();
        for(int i = 0; i <= v; i++) {
            this.arr.add(new LinkedList<>());
        }
    }
    public void addEdge(int a, int b) {
        if(a != b) {
            arr.get(a).add(b);
            arr.get(b).add(a);
        }
        else {
            arr.get(a).add(b);
        }
    }
    public void dfs(int a) {
        boolean[] marked = new boolean[arr.size()];
        dfs(marked, a);
    }
    private void dfs(boolean[] marked, int v) {
        System.out.println("Visited Node: " + v);
        marked[v] = true;
        for(int i: arr.get(v)) {
            if(!marked[i]) {
                marked[i] = true;
                dfs(marked, i);
            }
        }
    }
    public boolean hasCycle() {
        boolean[] marked = new boolean[arr.size()];
        
        boolean res = hasCycle(1, marked, 1);
        return res;
    }
    private boolean hasCycle(int v, boolean[] marked, int parent) {
        marked[v] = true;
        for(int i: arr.get(v)) {
            if(marked[i] == true && i != parent) {
                return true;
            }
            if(!marked[i]) {
                marked[i] = true;
                boolean p = hasCycle(i, marked, v);
                if(p == true) {
                    return p;
                }
            }
        }
        return false;
    }
    public void print() {
        for(int i = 0; i <= v; i++) {
            for(int p: arr.get(i)) {
                System.out.print(p + " ");
            }
            System.out.println();
        }
    }
}



public class Graphs {
    public static void main(String[] args) {
        G gr = new G(10);
        //gr.addEdge(1,7);
        gr.addEdge(1,2);        
        gr.addEdge(2,6);
        gr.addEdge(6,7);
        gr.addEdge(2,9);
        //gr.addEdge(9,10);
        gr.addEdge(10,3);
        gr.addEdge(3,1);
        gr.addEdge(1,4);
        //gr.addEdge(3,4);
        System.out.println(gr.hasCycle());    
        //gr.print();
    }
}
