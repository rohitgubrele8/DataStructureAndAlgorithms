import java.util.ArrayList;
import java.util.Arrays;

public class CaptureRegion {

    public static void main(String[] args) {
        ArrayList<ArrayList<Character>> input = new ArrayList<>();
        input.add(toCharacterArray("XOXXXXOOXX"));
        input.add(toCharacterArray("XOOOOXOOXX"));
        input.add(toCharacterArray("OXXOOXXXOO"));
        input.add(toCharacterArray("OXOXOOOXXO"));
        input.add(toCharacterArray("OXOOXXOOXX"));
        input.add(toCharacterArray("OXXXOXXOXO"));
        input.add(toCharacterArray("OOXXXXOXOO"));
        solve(input);
        for(int i = 0 ; i<input.size(); i++) {
            System.out.println(input.get(i));
        }
    }

    static private ArrayList<Character> toCharacterArray(String s) {
        ArrayList<Character> result = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            result.add(s.charAt(i));
        }
        return result;
    }


    private static int[] dx = {1, 0, -1, 0,1,-1,1,-1};
    private static int[] dy = {0, 1, 0, -1,1,1,-1,-1};

    static public void solve(ArrayList<ArrayList<Character>> a) {
        boolean[][] visited  = new boolean[a.size()][a.get(0).size()];

            for(int j = 0; j < a.get(0).size()-1; j++){
                if(a.get(0).get(j).equals('O') && !visited[0][j]){
                    mark(a, visited, 0, j);
                }
            }
            for(int j = 0; j < a.get(0).size()-1; j++){
                if(a.get(a.size()-1).get(j).equals('O') && !visited[a.size()-1][j]){
                    mark(a, visited, a.size()-1, j);
                }
            }
            for(int i = 0; i < a.size()-1; i++){
                if(a.get(i).get(0).equals('O') && !visited[i][0]){
                    mark(a, visited, i, 0);
                }
            }
            for(int i = 0; i < a.size()-1; i++){
                if(a.get(i).get(a.get(0).size()-1).equals('O') && !visited[i][a.get(0).size()-1]){
                    mark(a, visited, i, a.get(0).size()-1);
                }
            }


            for(int i = 1; i < a.size() ;i++){
                for(int j = 1; j<a.get(0).size(); j++){
                    if(!visited[i][j]) a.get(i).set(j, 'X');
                }
            }
    }

    static void mark(ArrayList<ArrayList<Character>> a, boolean[][] visited, int i, int j){
        visited[i][j] = true;
        for (int itr = 0; itr < dx.length; itr++) {
            int ni = i + dx[itr];
            int nj = j + dy[itr];
            if(isValid(ni, nj, a.size(), a.get(0).size()) && a.get(ni).get(nj).equals('O') && !visited[ni][nj])
                mark(a, visited, ni, nj);
        }
    }


    static boolean isValid(int x, int y, int l, int b) {
        return x >= 0 && y >= 0 && x < l && y < b;
    }

    static boolean isBorder(int x, int y, int l, int b) {
        return x == 0 || y == 0 || x == l-1 || y == b-1;
    }
}
