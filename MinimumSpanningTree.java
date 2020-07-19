import sun.jvm.hotspot.asm.sparc.SPARCRegister;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class MinimumSpanningTree {


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
        System.out.println(minimumSpanningTreePrint( input, 7));
    }

    private static int findParent(int a, int[] parent){
        if(a != parent[a]){
            parent[a] = findParent(parent[a], parent);
        }
        return parent[a];
    }

    private static boolean union(int u, int v, int[] parents, int[] rank) {
        int rootU = findParent(u, parents);
        int rootV = findParent(v, parents);

        if(rootU == rootV) return false;
        if(rank[rootU] > rank[rootV]){
            parents[rootV] = rootU;
        }else {
            parents[rootU] = rootV;
            if(rank[rootU] == rank[rootV]) rank[rootV] +=1;
        }
        return true;
    }

    private static int minimumSpanningTreePrint(ArrayList<ArrayList<Integer>> graph, int islands){

        int[] parents = new int[islands+1];
        int[] rank = new int[islands+1];
        Collections.sort(graph, Comparator.comparingInt(a -> a.get(2)));
        for(int i = 0; i < islands+1; i++){
            parents[i] = i;
            rank[i] = 1;
        }
        int totalCost = 0;
        for (ArrayList<Integer> list : graph) {
            int u = list.get(0);
            int v = list.get(1);
            if(union(u, v, parents, rank)){
                totalCost += list.get(2);
            }
        }
        return totalCost;
    }

}
