
import java.util.*;

public class RainWater {



    public static void main(String[] args) {
        System.out.println(trap(Arrays.asList(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)));
    }
    static public int trap(final List<Integer> A) {

        if(A.size()<3){
            return 0;
        }
        int water=0;
        PriorityQueue<Integer> left= new PriorityQueue<>((O1, O2)-> O2-O1);
        PriorityQueue<Integer> right= new PriorityQueue<>((O1, O2)-> O2-O1);
        Queue<Character> queue = new LinkedList<>();
        int lefth  = 0;
        int righth = 0;
        for(int i : A){
            right.add(i);
        }
        for(int i = 0; i< A.size() ;i++){
            right.remove(A.get(i));
            if(!left.isEmpty() && !right.isEmpty()){
                lefth  = left.peek();
                righth = right.peek();
            }
            left.add(A.get(i));
            int height = Math.min(lefth,righth);
            if(height>A.get(i)) water+=(height-A.get(i));
        }
        return water;
    }
}
