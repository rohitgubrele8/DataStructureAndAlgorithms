import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Rohit on 03/07/20.
 */
public class SteppingNumbers {
  public static void main(String[] args) {
    System.out.println(stepNum(121, 124));
  }

  public static ArrayList<Integer> stepNum(int A, int B) {
    ArrayList<Integer> result = new ArrayList<>();
    Queue<Integer> queue = new LinkedList<>();
    for(int i = 1; i<=9 ;i++){
      queue.add(i);
    }
    if(A == 0) queue.add(0);
    while (!queue.isEmpty()){
      Integer curr = queue.poll();
      if(curr > B) break;

      if(curr>=A && curr <=B) result.add(curr);
      int last = curr%10;
       if(last>0) queue.add(curr*10 + last -1);
       if(last<9) queue.add(curr*10 + last + 1);

    }

    return result;
  }


}
