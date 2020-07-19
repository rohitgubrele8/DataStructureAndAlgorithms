import java.util.LinkedList;
import java.util.Queue;

public class MoveTheKnight {
    public static void main(String[] args) {
        System.out.println(knight(4,7,2,6,2,4));
    }


     static public int knight(int A, int B, int C, int D, int E, int F) {
        visited = new boolean[A+1][B+1];
        int currSteps = 0;
        minSteps = 10000;
         if(C == E && D == F) return 0;
         Queue<Pair> queue = new LinkedList<>();
         queue.add(new Pair(C,D));
         int currLevel = 1;
         int nextLevel = 0;
         while (!queue.isEmpty()){
             Pair curr = queue.poll();
             visited[curr.x][curr.y] = true;
             if(curr.x == E && curr.y == F) {
                 minSteps = currSteps;
                 break;
             }
             currLevel--;
             for (int itr = 0; itr < dx.length; itr++) {
                 int nx = curr.x + dx[itr];
                 int ny = curr.y + dy[itr];
                 if (isValidCor(nx, ny, A, B) && !visited[nx][ny]) {
                     queue.add(new Pair(nx,ny));
                     nextLevel++;
                 }
             }

             if(currLevel == 0) {currLevel = nextLevel; nextLevel = 0; currSteps ++;}
         }

        if(minSteps == 10000) minSteps = -1;
        return minSteps;
    }

    static int minSteps;

    static int[] dx = {2,1,1,-1,2,-2,-2,-1};
    static int[] dy = {1,2,-2,2,-1,1,-1,-2};
    static boolean[][] visited;

    static boolean isValidCor(int x, int y, int l, int b){
        return x > 0 && y > 0 && x <= l && y <= b;
    }

    static public class Pair{
        int x;
        int y;

        public Pair(int x,int y){
            this.x = x;
            this.y = y;
        }
    }




}
