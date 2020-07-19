

import java.util.*;

public class MinimumCostOfTravelingCharMatrix {

    public static void main(String[] args) {

        ArrayList input = new ArrayList(Arrays.asList("DUDRURRLDR",
                                                      "DULRLULDRD",
                                                      "DLULUDLLDL",
                                                      "RLRRDURDLD",
                                                      "LURULLRDLL",
                                                      "RRLDURURDL",
                                                      "DULDURUDRL",
                                                      "RUUUDUDLDR",
                                                      "RRRRDLULRL",
                                                      "DLRRUDRUUU"));
        System.out.println(solve(10, 10, input));
    }
    static public class Pair{
        int x;
        int y;
        int cost;

        public Pair(int a, int b){
            this.x = a;
            this.y = b;
        }

        public Pair(int a, int b, int c){
            this.x = a;
            this.y = b;
            this.cost = c;
        }
    }

    static public int solve(int A, int B, ArrayList<String> C) {
        Map<Character, Pair> map = new HashMap<>();
        map.put('U', new Pair(-1,0, 0));
        map.put('R', new Pair(0,1, 0));
        map.put('D', new Pair(1,0, 0));
        map.put('L', new Pair(0,-1, 0));

        boolean[][] visited = new boolean[C.size()][C.get(0).length()];
        Deque<Pair> deque = new LinkedList<>();
        deque.add(new Pair(0, 0, 0));
        while (!deque.isEmpty()){
            Pair curr = deque.poll();
            if(curr.x == C.size()-1 && curr.y == C.get(curr.x).length()-1) return curr.cost;
            visited[curr.x][curr.y] = true;
            char freeChar = C.get(curr.x).charAt(curr.y);
            for(char c : map.keySet()){
                Pair dir = map.get(c);
                int x = dir.x+curr.x;
                int y = dir.y+curr.y;
                if(x >= 0 && y >= 0 && x < C.size() && y < C.get(curr.x).length() && !visited[x][y]) {
                    if (c == freeChar) {
                        deque.addFirst(new Pair(x, y, curr.cost));
                    } else {
                        deque.addLast(new Pair(x, y, curr.cost + 1));
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }


}
