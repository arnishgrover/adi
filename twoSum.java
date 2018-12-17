import java.util.*;
class solution { 
    public ArrayList<Integer> cal(final ArrayList<Integer> A, int B) {
        ArrayList<Integer> res = new ArrayList<>();
        Set<Integer> hash = new HashSet<>();
        for(int i = 0; i < A.size(); i++) {
            int brother = B - A.get(i);
            if(hash.contains(brother)) {
                int index1 = Math.min(A.indexOf(brother), i) + 1;
                int index2 = Math.max(A.indexOf(brother), i) + 1;
                
                if(res.size() == 0) {
                    res.add(index1);
                    res.add(index2);
                }
                else {
                    if(res.get(1) > index2) {
                        res.set(0, index1);
                        res.set(1, index2);
                    }
                    else if(res.get(1) == index2) {
                        if(res.get(0) > index1) {
                            res.set(0, index1);
                        }
                    }
                }
            }
            
                hash.add(A.get(i));
            
        }
        return res;   
    }

}
public class twoSum {
    public static void main(String[] args) {
        solution s = new solution();
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(2);
        arr.add(7);
        arr.add(11);
        arr.add(15);
        System.out.println(s.cal(arr, 9));
    }

}
