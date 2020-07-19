import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by Rohit on 03/07/20.
 */
public class OneZeroNumber {

  public static void main(String[] args) {
    System.out.println(multiple(1987659));
  }

  public static String multiple(int A) {

    if(A == 1) return "1";

    Queue<Pair> queue = new LinkedList();

    queue.add(new Pair(1, null, true));

    boolean[] checked =new boolean[A];

    Pair result = null;

    //   long count  = 0;

    while(!queue.isEmpty()){

      //     count++;
      Pair curr = queue.poll();

      if(curr.one == 0){ result = curr; break;}
      int zeroMod = (curr.one*10)%A;
      int oneMod = (curr.one*10 + 1)%A;

      if(!checked[zeroMod]){
        queue.add(new Pair(zeroMod, curr,false));
        checked[zeroMod] = true;
      }
      if(!checked[oneMod]){
        queue.add(new Pair(oneMod, curr, true));
        checked[oneMod] = true;
      }
      //      System.out.println(count);
    }
    StringBuilder resultString = new StringBuilder();
    while(result != null){
      String s = result.isOne?"1":"0";
      resultString.append(s);
      result = result.parent;
    }


    return resultString.reverse().toString();

  }

  static class Pair{

    Pair parent;
    int one;
    boolean isOne;

    public Pair(int one , Pair parent, boolean isOne){
      this.one = one;
      this.parent = parent;
      this.isOne  = isOne;
    }

  }


}
