import java.util.*;
public class median_running_array {
    public static void main(String[] args) {
        median m = new median();
        int[] arr = {1,9,8,19,15,5};
        for(int i: arr) {
            m.insert(i);
            m.rebalance();
            System.out.println(m.getMedian());
        }
    }

}
class median {
    PriorityQueue<Integer> max = new PriorityQueue<Integer>(Collections.reverseOrder());
    PriorityQueue<Integer> min = new PriorityQueue<Integer>();
    
    
    public void insert(int data) {
        if(max.size() == 0 || max.peek() > data) {
            max.add(data);
        }
        else {
            min.add(data);
        }
    }
    public void rebalance() {
        if(max.size() - min.size() > 1) {
                min.add(max.poll());
        }
        else if(min.size() - max.size() > 1) {
                max.add(min.poll());
        }
    }
    public double getMedian() {
        double res;
        if(max.size() == min.size()) {
            res = max.peek() + min.peek();
            return res*0.5;
        }
        else if(max.size() > min.size()) {
            return max.peek();
        }
        else {
            return min.peek();
        }
    }
}

