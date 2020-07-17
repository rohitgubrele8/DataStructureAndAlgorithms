import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Rohit on 13/07/20.
 */
public class Q1 {
  public static void main(String args[]){
    Scanner in=new Scanner(System.in);
    int t=in.nextInt();
    int maxlength = 0;
    while(t-->0) {
      int n = in.nextInt();
      int a[] = new int[n];
      ArrayList<Pair> pq = new ArrayList<>();
      int i=1;
      int size=0;
      int start = -1;
      while(i<n){
        if(a[i]>a[i-1]) {
          if(start == -1) start = i;
          size++;
        }else if(start != -1){
          pq.add(new Pair(start, size));
          start = -1;
          size = 0;
        }
        i++;
      }
      Collections.sort(pq);
      for(int list = 0; list< pq.size(); list++){
        for(int next  = list; next<pq.size(); next ++){
          Pair one  = pq.get(list);
          Pair two  = pq.get(next);
          if(!overlap(one.start, one.size, two.start, two.size) && one.size + two.size > maxlength){
            maxlength = one.size + two.size;
          }
        }
      }
    }
    System.out.println(maxlength);
  }

  private static boolean overlap(int x, int xSize,  int y, int ySize){
    return x < y+ySize && y< x+xSize;
  }
  static class Pair implements Comparable {
    int start;
    int size;

    public Pair(int start, int size){
      this.start = start;
      this.size = size;
    }

    @Override
    public int compareTo(Object o) {
      o = (Pair)o;
      return ((Pair) o).size-this.size;
    }
  }
}
