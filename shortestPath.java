import java.util.*;
class Graph {
    ArrayList<LinkedList<Integer>> adj;
    int N;
    public Graph(int N) {
        this.N = N;
        adj = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            adj.add(new LinkedList<Integer>());
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
    public void Spath(int a, int b) {
        int[] edgeTo = new int[N];
        bfs(a, edgeTo);
    
        int p = b;
        System.out.println("Path: ");
        while(p != a) {
            System.out.print(p + "->");
            p = edgeTo[p];
        }
        System.out.println("reached");
    }
    public void bfs(int start, int[] edgeTo) {
        boolean[] marked = new boolean[N];

        marked[start] = true;

        Queue<Integer> q = new LinkedList<>();

        q.add(start);
        while(!q.isEmpty()) {
            int temp = q.poll();
            for(int w: adj.get(temp)) {
                if(!marked[w]) {
                    edgeTo[w] = temp;    
                    marked[w] = true;
                    q.add(w);
                }
            }
        }
    }
    public void Lpath(int a, int b) {
        int[] edgeTo = new int[N];
        boolean[] marked = new boolean[N];
        dfs(a, edgeTo, marked);
        int p = b;
        while(p != a) {
            System.out.print(p + "->");
            p = edgeTo[p];
        }
        System.out.println("Reached");
    
    }
    public void dfs(int start, int[] edgeTo, boolean[] marked) {
        marked[start] = true;
        for(int w: adj.get(start)) {
            if(!marked[w]) {
                marked[w] = true;
                edgeTo[w] = start;
                dfs(w, edgeTo, marked);
            }
        }
    }
            
}

public class shortestPath {
    public static void main(String[] args) {
        Graph g = new Graph(9);
        g.addEdge(0,1);
        g.addEdge(1,7);
        g.addEdge(7,6);
        g.addEdge(6,2);
        g.addEdge(2,1);
        g.addEdge(1,3);
        g.addEdge(1,4);
        g.addEdge(3,5);
        g.addEdge(5,8);
        g.addEdge(2,8);
        System.out.println("Shorter Path: ");
        g.Spath(5, 7);
        System.out.println("Longer Path: ");
        g.Lpath(5, 7);
    
    }

}
