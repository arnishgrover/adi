import java.util.*;
class Graph {
    ArrayList<LinkedList<Integer>> adj;
    int N;
    Graph(int N) {
        this.N = N;
        adj = new ArrayList<>();
        for(int i = 0; i < N; i++) {
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
    public boolean isBipartite() {
        boolean[] color = new boolean[N]; // true = red;  false = black
        boolean[] marked = new boolean[N];
        //boolean isBip = true;
        color[0] = true;
        return  dfs(0, marked, color);
    }
    public boolean dfs(int start, boolean[] marked, boolean[] color) {
       marked[start] = true;
        for(int i: adj.get(start)) {
            if(!marked[i]) {
                marked[i] = true;
                color[i] = !color[start];
                if(dfs(i, marked, color) ==false) {
                    return false;
                }
            }
            else {
                if(color[i] == color[start]) {
                    return false;
                }
            }
        }
        return true;
    }
}
    
public class Bipartite {
    public static void main(String[] args) {
        Graph g = new Graph(6);
        g.addEdge(1,4);
        g.addEdge(1,5);
        g.addEdge(1,2);
        g.addEdge(2,4);
        g.addEdge(2,0);
        g.addEdge(3,5);
        System.out.println(g.isBipartite());
    
    }
}
