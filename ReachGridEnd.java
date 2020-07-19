import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Rohit on 02/07/20.
 */
public class ReachGridEnd {


  public static void main(String[] args) {
    System.out.println(solve(51,41, 6, 9, new ArrayList<>(Arrays.asList(2, 2, 37, 8, 5, 36 )),new ArrayList<>(Arrays.asList( 10, 3, 31, 12, 5, 40))));
  }

  static int[] dx = {1,0,-1,0};
  static int[] dy = {0,1,0,-1};


  static int[] dxy = {1,-1,1,-1};
  static int[] dyx = {1,1,-1,-1};


  static int[] dx8 = {1,0,-1,0,1,-1,1,-1};
  static int[] dy8 = {0,1,0,-1,1,1,-1,-1};


  static int X;
  static int Y;
  public  static String solve(int A, int B, int C, int D, ArrayList<Integer> E, ArrayList<Integer> F) {


    int grid[][] = new int[A+1][B+1];
   X = A;
   Y = B;
    for(int i = 0; i< C; i++){
      //  grid[E.get(i)][F.get(i)] = true;
      markInCirle(E.get(i), F.get(i), D,grid, E.get(i), F.get(i));
      cn++;
      //
      //       for(int k  = 0; k < A+1 ;k++){
      //           for(int l  = 0; l < B+1 ;l++){
      //               System.out.print(grid[k][l] + " ");
      //           }
      //           System.out.println();
      //       }
    }
    result = false;
    if(grid[0][0] == 0){

      dfs(grid, 0, 0);}

//    for(int k  = 0; k < A+1 ;k++){
//      for(int l  = 0; l < B+1 ;l++){
//        System.out.print(grid[k][l] + " ");
//      }
//      System.out.println();
//    }

    if(result)return "YES";
    return "NO";

  }
static int cn = 1;
  static void markInCirle(int cx, int cy, int r, int[][] grid, int x, int y){
    grid[x][y] = cn;
    for(int itr = 0; itr <dx8.length; itr++){
      int xn = x + dx8[itr];
      int yn = y + dy8[itr];
      if(isValidCor(xn ,yn) && isInsideCircle(cx, cy, xn, yn , r) && grid[xn][yn] != cn)
        markInCirle(cx, cy, r, grid, xn, yn);
    }
  }

  static  boolean isInsideCircle(int cx, int cy, int x, int y, int r){
    return Math.sqrt(Math.pow(x-cx,2) + Math.pow(y-cy,2))<=r;
  }

  static  boolean isValidCor(int i ,int j){
    return i>=0 && j>=0 && i<=X && j<=Y;
  }

  static boolean result =false;
  private static void dfs(int[][] grid, int start, int end){
    //System.out.println(start + ":" + enddne;
    if(start == X && end == Y) {result = true; return;}

    grid[start][end] = 2;
    for(int i = 0; i<dx8.length; i++){
      int cx = start + dx8[i];
      int cy = end + dy8[i];

      if(isValidCor(cx,cy) && grid[cx][cy] == 0){
        dfs(grid, cx, cy);
        //if(grid[cx][cy] == 2) System.out.println(cx + ": "+ cy);
      }
    }


  }

}
