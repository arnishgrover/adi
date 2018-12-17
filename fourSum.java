import java.util.*;
class Solution {
    public ArrayList<ArrayList<Integer>> cal(ArrayList<Integer> A, int B) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        Collections.sort(A);
        for(int i = 0; i < A.size(); i++) {
            for(int j = i + 1; j < A.size(); j++) {
                int sum = A.get(i) + A.get(j);
                int start = j + 1;
                int end = A.size() - 1;
                while(start < end) {
                    if(A.get(start) + A.get(end) + sum == B) {
                        int a = A.get(i);
                        int b = A.get(j);
                        int c = A.get(start);
                        int d = A.get(end);
                        
                        ArrayList<Integer> temp = new ArrayList<Integer>();
                        temp.add(a);
                        temp.add(b);
                        temp.add(c);
                        temp.add(d);
    
                        if(res.indexOf(temp) < 0) {
                            res.add(temp);
                        }
                        start++;
                        end--;
                    }
                    else if(A.get(start) + A.get(end) + sum > B) {
                        end--;
                    }
                    else {
                        start++;
                    }
                }
            }
        }
        return res;
    }
}

public class fourSum {
    public static void main(String[] args) {
        Solution s = new Solution();
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(0);
        arr.add(-1);
        arr.add(0);
        arr.add(-2);
        arr.add(2);
        
        System.out.println(s.cal(arr, 0));
    }   
}
