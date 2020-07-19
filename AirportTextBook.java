import java.util.*;

public class AirportTextBook {

    public static void main(String[] args) {

        List<String> airports = new ArrayList<>(Arrays.asList(
                "BGI", "CDG", "DEL", "DOH", "DSM", "EWR", "EYW", "HND", "ICN", "JFK", "LGA", "LHR", "ORD",
                "SAN", "SFO", "SIN", "TLV", "BUD"));
        List<List<String>> route = new ArrayList<>();
        route.add(Arrays.asList("DSM", "ORD"));
        route.add(Arrays.asList("ORD", "BGI"));
        route.add(Arrays.asList("BGI", "LGA"));
        route.add(Arrays.asList("SIN", "CDG"));
        route.add(Arrays.asList("CDG", "SIN"));
        route.add(Arrays.asList("CDG", "BUD"));
        route.add(Arrays.asList("DEL", "DOH"));
        route.add(Arrays.asList("DEL", "CDG"));
        route.add(Arrays.asList("TLV", "DEL"));
        route.add(Arrays.asList("EWR", "HND"));
        route.add(Arrays.asList("HND", "ICN"));
        route.add(Arrays.asList("HND", "JFK"));
        route.add(Arrays.asList("ICN", "JFK"));
        route.add(Arrays.asList("JFK", "LGA"));
        route.add(Arrays.asList("EYW", "LHR"));
        route.add(Arrays.asList("LHR", "SFO"));
        route.add(Arrays.asList("SFO", "SAN"));
        route.add(Arrays.asList("SFO", "DSM"));
        route.add(Arrays.asList("SAN", "EYW"));
        System.out.println(airportConnections(airports,route,"LGA"));
    }

    private static class AirportNode implements Comparable{
        String airport;
        int score;

        public AirportNode(String airport, int score) {
            this.airport = airport;
            this.score = score;
        }

        @Override
        public int compareTo(Object o) {
            return ((AirportNode)o).score - this.score;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AirportNode that = (AirportNode) o;
            return Objects.equals(airport, that.airport);
        }

        @Override
        public int hashCode() {
            return Objects.hash(airport);
        }
    }

    public static int airportConnections(List<String> airports, List<List<String>> routes, String startingAirport) {
        Map<String, List<String>> connections = new HashMap<>();
        for(List<String> route : routes){
            if(!connections.containsKey(route.get(0))){
                connections.put(route.get(0), new ArrayList<>());
            }
            connections.get(route.get(0)).add(route.get(1));
        }
        Map<String, Integer> scoreSet = new HashMap<>();
//        scoreSet.add(new AirportNode(startingAirport, 0));
//        if(connections.get(startingAirport) != null) {
//            for (String connection : connections.get(startingAirport)) {
//                scoreSet.add(new AirportNode(connection, 0));
//            }
//        }
        visited = new HashSet<>();
        for(String airport : airports){
            if(!scoreSet.containsKey(airport)){
                scoreDFS(airport, connections, scoreSet, new HashSet<>());
            }
        }

        List<AirportNode> scores = new ArrayList<>();
        scoreSet.entrySet().forEach(entry -> {
            scores.add(new AirportNode(entry.getKey(), entry.getValue()));
        });
        Collections.sort(scores);
        DFS(startingAirport, connections);
        int newConnection = 0;
        for(AirportNode airport : scores){
            if(!visited.contains(airport.airport)){
                DFS(airport.airport, connections);
                newConnection++;
            }
        }
        return newConnection;
    }


    static Set<String> visited;

    private static int scoreDFS(String startAirport, Map<String, List<String>> connections, Map<String, Integer> scores, Set<String> visited){
        int score = 0;
        visited.add(startAirport);
        if(connections.containsKey(startAirport)) {
            for (String connection : connections.get(startAirport)) {
                if (!scores.containsKey(connection) && !visited.contains(connection)) {
                    score += scoreDFS(connection, connections, scores, visited) + 1;
                } else if(scores.containsKey(connection)) score += scores.get(connection) + 1;
            }
        }
        scores.put(startAirport, score);
        return score;
    }

    private static void DFS(String startAirport, Map<String, List<String>> connections){
        visited.add(startAirport);
        if(connections.containsKey(startAirport)) {
            for (String connection : connections.get(startAirport)) {
                if (!visited.contains(connection)) {
                    DFS(connection, connections);
                }
            }
        }
    }
}
