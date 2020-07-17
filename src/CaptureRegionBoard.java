import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Rohit on 06/07/20.
 */
public class CaptureRegionBoard {

  public static void main(String[] args) {
    ArrayList<ArrayList<Character>> input  =new ArrayList<>();
    input.add(new ArrayList<>(Arrays.asList('X', 'O','O','O','O','O','O', 'X')));
    input.add(new ArrayList<>(Arrays.asList('X','X', 'O', 'O','X','O','O','X')));
    input.add(new ArrayList<>(Arrays.asList('O', 'X','X', 'O','X', 'O','X','X')));
   // input.add(new ArrayList<>(Arrays.asList('X', 'O', 'X')));

    solve(input);
    System.out.println(input);
  }

  private static int[] dx = {1,0,-1,0};
  private static int[] dy = {0,1,0,-1};
  static public void solve(ArrayList<ArrayList<Character>> a) {
    boolean[][] visited  = new boolean[a.size()][a.get(0).size()];
    for(int i = 0; i < a.size() ; i++){
      for(int j = 0; j < a.get(0).size(); j++){
        if(a.get(i).get(j).equals('O') && !visited[i][j]){
          boolean isMark = mark(a,i,j, visited);
         // if(isMark) a.get(i).set(j, 'X');
        }
      }
    }
  }

  static private boolean mark(ArrayList<ArrayList<Character>> a, int i, int j, boolean[][] visited){
    visited[i][j] = true;
    boolean eligible = true;
    for(int itr = 0; itr <dx.length; itr++){
      int x = dx[itr]+i;
      int y = dy[itr]+j;
      if(isValid(x,y,a.size(),a.get(0).size())){
        if(!a.get(x).get(y).equals('X') && !visited[x][y]){
           eligible = mark(a, x, y, visited);
           if(!eligible) break;
        }
        if(!a.get(x).get(y).equals('X') && visited[x][y]){
          eligible = false; break;
        }
      }else if(a.get(i).get(j).equals('O')) {eligible = false; break;}
    }
    if(eligible) a.get(i).set(j,'X');
    return eligible;
  }

  private static boolean isValid(int x, int y, int X, int Y) {
    return x >= 0 && y >= 0 && x < X && y < Y;
  }
}
