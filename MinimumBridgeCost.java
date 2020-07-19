

import java.util.*;

public class MinimumBridgeCost {

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList(1, 2, 10)));
        input.add(new ArrayList<>(Arrays.asList(2, 3, 5)));
        input.add(new ArrayList<>(Arrays.asList(1, 3, 9)));
        input.add(new ArrayList<>(Arrays.asList(1, 4, 13)));
        input.add(new ArrayList<>(Arrays.asList(3, 5, 6)));
        input.add(new ArrayList<>(Arrays.asList(3, 7, 15)));
        input.add(new ArrayList<>(Arrays.asList(4, 7, 2)));
        input.add(new ArrayList<>(Arrays.asList(5, 6, 8)));
        System.out.println(solve(7, input));
    }

    private static int findParent(int a, int[] parents){
        if (parents[a] != a){
            parents[a]  = findParent(parents[a], parents);
        }
        return parents[a];
    }

    private static boolean union(int u, int v , int[] parent, int[] rank){
        int rootU = findParent(u, parent);
        int rootV = findParent(v, parent);

        if(rootU == rootV) return false;
        if(rank[rootU] > rank[rootV]){
            parent[rootV] = rootU;
        }else {
            parent[rootU] = rootV;
            if(rank[rootU] == rank[rootV]) rank[rootV] +=1;
        }
        return true;
    }

    static public int solve(int A, ArrayList<ArrayList<Integer>> B) {
        Collections.sort(B, Comparator.comparingInt(b -> b.get(2)));

        int [] parent = new int[A+1];
        int [] rank = new int[A+1];

        for(int i = 1; i<= A; i++){
            parent[i] = i;
            rank[i] = 1;
        }
        int sum = 0;
        for(ArrayList<Integer> b : B){
            int u = b.get(0);
            int v = b.get(1);

            if(union(u,v,parent,rank)){
            sum+= b.get(2);
            }
        }

        return sum;
    }


}
