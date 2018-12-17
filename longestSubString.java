import java.util.*;
class solution {
    public int cal(String A) {
        Map<Character, Integer> hash = new HashMap<>();
        int curr = 0;
        int max = 0;
        for(int i = 0; i < A.length(); i++) {
            if(!hash.containsKey(A.charAt(i))) {
                curr++;
            }
            else {
                if(i - curr > hash.get(A.charAt(i))) {
                    curr++;
                }
                else {
                    if(curr > max) {
                        max = curr;
                    }
                    curr = i - hash.get(A.charAt(i));
                }
            }
            hash.put(A.charAt(i), i);
        }
        if(curr > max) {
            max = curr;
        }
        return max;
    }

}
public class longestSubString {
    public static void main(String[] args) {
        solution s = new solution();
        String A = "dadbc";
        System.out.println(s.cal(A));
    }
}
