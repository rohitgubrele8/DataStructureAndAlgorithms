import java.util.ArrayList;
import java.util.Arrays;

public class WordSearchBoard {

    public static void main(String[] args) {
        ArrayList a = new ArrayList(Arrays.asList("FEDCBECD",
                                                  "FABBGACG",
                                                  "CDEDGAEC",
                                                  "BFFEGGBA",
                                                  "FCEEAFDA",
                                                  "AGFADEAC",
                                                  "ADGDCBAA",
                                                  "EAABDDFF" ));

      //  System.out.println(exist(a,"ABCCED"));
       // System.out.println(exist(a,"SEE"));
        System.out.println(exist(a,"BCDCB"));
    }


    static int[] dx = {0,1,-1,0};
    static int[] dy = {1,0,0,-1};

    static public int exist(ArrayList<String> A, String B) {
        int result = 0;
        for (int i = 0; i < A.size(); i++) {
            for (int j = 0; j < A.get(0).length(); j++) {
                if (A.get(i).charAt(j) == B.charAt(0)) ;
                boolean[][] visited = new boolean[A.size()][A.get(0).length()];
                result = search(A, B, 0, visited, i, j);
                if (result == 1) break;
            }
            if(result ==1) break;
        }
        return result;
    }

    private static int search(ArrayList<String> A, String B, int pos, boolean[][] visited, int i, int j) {
       // visited[i][j] = true;
        if (pos == B.length()-1 && A.get(i).charAt(j) == B.charAt(pos)) return 1;
        if(A.get(i).charAt(j) != B.charAt(pos)) return 0;
        int result = 0;
        for (int itr = 0; itr < dx.length; itr++) {
            int ni = dx[itr] + i;
            int nj = dy[itr] + j;
            if (isValidCor(ni, nj, A.size(), A.get(0).length()) && !visited[ni][nj]) {
                result = search(A, B, pos + 1, visited, ni, nj);
                visited[ni][nj] = false;
            }

            if (result == 1) break;
        }
        return result;
    }

    static boolean isValidCor(int x, int y, int l, int b){
        return x >= 0 && y >= 0 && x < l && y < b;
    }



}

