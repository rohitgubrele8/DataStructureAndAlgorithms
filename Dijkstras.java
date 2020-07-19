import java.util.*;

public class Dijkstras {

    public static void main(String[] args) {

        int graph[][] = new int[][] { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };

        System.out.println(Arrays.asList(dijkstrasAlgo(graph)));

    }

    public static class Pair {
        int vert;
        int cost;

        public Pair(int vert, int cost) {
            this.vert = vert;
            this.cost = cost;
        }
    }

    public static int[] dijkstrasAlgo(int[][] graph){
        Set<Integer> visited = new HashSet<>();
        int[] dist = new int[graph.length];
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        pq.add(new Pair(0, 0));

        while (visited.size() != graph.length && !pq.isEmpty()){
            Pair pair = pq.poll();
            visited.add(pair.vert);
            for(int i = 0;i < graph.length; i++){
                if(graph[pair.vert][i] != 0){
                    if(!visited.contains(i)) pq.add(new Pair(i, graph[pair.vert][i] + pair.cost));
                    if(dist[i] > graph[pair.vert][i] + pair.cost) dist[i] = graph[pair.vert][i] + pair.cost;
                }
            }
        }

        return dist;
    }




}
