import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Rohit on 13/07/20.
 */
public class q11 {
  public static void main(String[] args) {
    solve(new ArrayList<>(Arrays.asList(10,1, 2, 4,6 ,5,8,9,10,3)));
  }

  public static void solve(List<Integer> input) {
    int maxlength = 0;
    ArrayList<Q1.Pair> pq = new ArrayList<>();
    int i = 1;
    int size = 1;
    int start = -1;
    while (i < input.size()) {
      if (input.get(i) > input.get(i - 1)) {
        if (start == -1)
          start = i-1;
        size++;
      } else if (start != -1) {
        pq.add(new Q1.Pair(start, size));
        start = -1;
        size = 1;
      }
      i++;
    }
    Collections.sort(pq);
    for (int list = 0; list < pq.size(); list++) {
      for (int next = list; next < pq.size(); next++) {
        Q1.Pair one = pq.get(list);
        Q1.Pair two = pq.get(next);
        if (!overlap(one.start, one.size, two.start, two.size) && one.size + two.size > maxlength) {
          maxlength = one.size + two.size;
        }
      }
    }

    System.out.println(maxlength);
  }


  private static boolean overlap(int x, int xSize, int y, int ySize) {
    return x < y + ySize && y < x + xSize;
  }

  static class Pair implements Comparable {
    int start;
    int size;

    public Pair(int start, int size) {
      this.start = start;
      this.size = size;
    }

    @Override
    public int compareTo(Object o) {
      return ((Pair) o).size - this.size;
    }
  }
}