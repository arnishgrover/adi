import java.util.*;
class graph {
    private final int N;
    private ArrayList<LinkedList<Integer>> adj;
    graph(int size) {
        this.N = size;
        adj = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            adj.add(new LinkedList<>());
        }
    }
    public void addEdge(int a, int b) {
        if(a == b) {
            adj.get(a).add(b);
        }
        else {
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
    }
    public void dfs(int start) {
        boolean[] marked = new boolean[N+1];
        dfs(marked, start);
    }
    private void dfs(boolean[] marked, int start) {
        marked[start] = true;
        for(int w: adj.get(start)) {
            if(!marked[w]) {
                marked[w] = true;
                dfs(marked, w);
            }
        }
    }
    public void bfs() {
        bfs(1);
    }
    public void bfs(int start) {
        int[] edgeTo = new int[N+1];
        boolean[] marked = new boolean[N+1];
        bfs(marked, start, edgeTo);
        System.out.println("Printing Edge To array: ");
        for(int i = 0; i < edgeTo.length; i++) {
            System.out.println(i + "  " + edgeTo[i]);
        }
    }
    private void bfs(boolean[] marked, int start, int[] edgeTo) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        edgeTo[start] = start;
        while(!q.isEmpty()) {
            int p = q.poll();
            marked[p] = true;
            for(int w: adj.get(p)) {
                if(!marked[w]) {
                    edgeTo[w] = p;
                    marked[w] = true;
                    q.add(w);
                }
            }
        }
    }
    
    public int numberOfClusters() {
        boolean[] marked = new boolean[N+1];
        int c = 0;
        for(int i = 0; i <= N; i++) {
            if(!marked[i]) {
                marked[i] = true;
                c++;
                dfs(marked, i);
            }
        }
        return c;
    }
    
    public boolean hasCycle() {
        boolean[] marked = new boolean[N+1];
        
        return hasCycle(1, marked, 1);
    }
    private boolean hasCycle(int v, boolean[] marked, int parent) {
        marked[v] = true;
        for(int w: adj.get(v)) {
            if(!marked[w]) {
                marked[w] = true;
                if(hasCycle(w, marked, v) == true) {
                    return true;
                }
            }
            else {
                if(w != parent) {
                    return true;
                }
            }   
        }
        return false;
    }
}
    


public class Graphs {
    public static void main(String[] args) {
        graph g = new graph(8);
        g.addEdge(1,7);
        g.addEdge(1,2);
        g.addEdge(1,3);
        g.addEdge(1,4);
        g.addEdge(7,6);
        g.addEdge(6,2);
        g.addEdge(2,8);
        g.addEdge(8,5);
        g.addEdge(5,3);
        
        g.addEdge(0,1); 
      
        System.out.println("Number of clusters: " + g.numberOfClusters());
        System.out.println("Graph has a cycle: " + g.hasCycle());
        g.bfs();
    }

}
