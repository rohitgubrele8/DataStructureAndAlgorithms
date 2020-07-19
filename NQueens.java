import java.util.ArrayList;

public class NQueens {

    public static void main(String[] args) {
        System.out.println(solveNQueens(5));
    }

   static ArrayList<ArrayList<String>> validGrid;
   static public ArrayList<ArrayList<String>> solveNQueens(int a) {

       validGrid = new ArrayList<>();
        for(int i = 0; i< a ; i++){

            ArrayList<ArrayList<String>> visited = new ArrayList<>();
            for(int k = 0 ; k < a; k++){
                visited.add(new ArrayList<>());
                for(int l = 0 ; l < a; l++){
                    visited.get(k).add("");
                }
            }
            placeNQueen(visited, a, 0, i,1, 1);
        }


    return validGrid;
    }

    private static ArrayList<String> convert(int a, ArrayList<ArrayList<String>> visited) {
        ArrayList<String> sol = new ArrayList<>();
            for (int z = 0; z < a; z++) {
                StringBuilder sb = new StringBuilder();
                ArrayList<String> row = visited.get(z);
                for (String s : row) {
                    if(s == "Q") System.out.print("+");
                    sb.append(s);
                }

                sol.add(sb.toString());
            }
        System.out.println();
        return sol;
    }
  //  static int queens;
    static private void placeNQueen(ArrayList<ArrayList<String>> visited, int n, int i,
                                    int j, int queens, int level){
        visited.get(i).set(j, "Q");
        markInvalid(i,j,visited);
        if(queens == n){
            ArrayList<String> sol = convert(n, visited);
            if(!sol.isEmpty()) validGrid.add(sol);
            return;
        }
        ArrayList<ArrayList<String>> current = new ArrayList<>();
        for(ArrayList row : visited){
            current.add(new ArrayList<>(row));
        }
        for(int ni = i+1; ni< n; ni ++ ){
            for(int nj = 0; nj < n; nj ++){
                if(current.get(ni).get(nj) == ""){
                    placeNQueen(current, n, ni, nj, queens+1, level+1);
                    current = new ArrayList<>();
                    for(ArrayList row : visited){
                        current.add(new ArrayList<>(row));
                    }
                }
            }
        }
    }

    static int[] dx = {1, 0, -1, 0, 1, -1, 1, -1};
    static int[] dy = {0, 1, 0, -1, 1, 1, -1, -1};

    static private void markInvalid(int x, int y, ArrayList<ArrayList<String>> visited) {
        for (int itr = 0; itr < dx.length; itr++) {
            int nx = x + dx[itr];
            int ny = y + dy[itr];
            while (isValidCor(nx, ny, visited.size())) {
//                if(visited.get(nx).get(ny) != "Q")
                visited.get(nx).set(ny, ".");
                nx += dx[itr];
                ny += dy[itr];
            }
        }
    }


    private static boolean isValidCor(int x, int y, int n) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }
}
