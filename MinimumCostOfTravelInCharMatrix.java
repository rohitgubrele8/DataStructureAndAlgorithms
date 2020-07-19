import java.util.*;

public class MinimumCostOfTravelInCharMatrix {

    public static void main(String[] args) {
        ArrayList input = new ArrayList(Arrays.asList("DUDRURRLDR", "DULRLULDRD", "DLULUDLLDL", "RLRRDURDLD", "LURULLRDLL",
                "RRLDURURDL", "DULDURUDRL", "RUUUDUDLDR", "RRRRDLULRL", "DLRRUDRUUU"));
        System.out.println(solve(10, 10, input));
    }

    static class Pair {
        int x, y;
        Pair(int a, int b) {
            x = a;
            y = b;
        }
    }

    static public int solve(int A, int B, ArrayList<String> C) {
        char arr[][] = new char[A][B];
        if (A == 1 && B == 1) {
            return 0;
        }
        for (int i = 0; i < A; i++) {
            String temp = C.get(i);
            for (int j = 0; j < B; j++) {
                arr[i][j] = temp.charAt(j);
            }
        }
        return minCost(arr);
    }

    static HashMap<Character, Pair> map = new HashMap<>();
    static int INF = (int) 1e9;

    static public int minCost(char[][] grid) {
        int m = grid.length, n = grid[0].length, cost = 0;
        int[][] dp = new int[m][n];
        map.put('R', new Pair(0, 1));
        map.put('L', new Pair(0, -1));
        map.put('D', new Pair(1, 0));
        map.put('U', new Pair(-1, 0));

        for (int i = 0; i < m; i++)
            Arrays.fill(dp[i], INF);

        Queue<Pair> bfs = new LinkedList<>();
        dfs(grid, 0, 0, dp, cost, bfs);

        while (!bfs.isEmpty()) {
            cost++;
            for (int size = bfs.size(); size > 0; size--) {
                Pair top = bfs.poll();
                int r = top.x, c = top.y;
                for (char k : map.keySet()) {
                    Pair temp = map.get(k);
                    dfs(grid, r + temp.x, c + temp.y, dp, cost, bfs);
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    static void dfs(char[][] grid, int r, int c, int[][] dp, int cost, Queue<Pair> bfs) {
        int m = grid.length;
        int n = grid[0].length;
        if (r < 0 || r >= m || c < 0 || c >= n || dp[r][c] != INF)
            return;

        dp[r][c] = cost;
        bfs.offer(new Pair(r, c));
        Pair temp = map.get(grid[r][c]);
        dfs(grid, r + temp.x, c + temp.y, dp, cost, bfs);
    }
}
