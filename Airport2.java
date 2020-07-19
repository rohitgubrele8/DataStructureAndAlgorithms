import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rohit on 02/07/20.
 */
public class Airport2 {

  static int visitedCount;

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
    System.out.println(airportConnections(airports, route, "LGA"));

  }

  static class NodeScore implements Comparable {
    int score;
    String airport;

    public NodeScore(String airport, int score) {
      this.airport = airport;
      this.score = score;
    }

    @Override
    public int compareTo(Object o) {
      if (this.score > ((NodeScore) o).score)
        return -1;
      else
        return 1;
    }
  }

  public static int airportConnections(List<String> airports, List<List<String>> routes,
      String startingAirport) {

    Map<String, List<String>> routeMap = new HashMap<>();

    for (List<String> route : routes) {
      if (!routeMap.containsKey(route.get(0))) {
        routeMap.put(route.get(0), new ArrayList<>());
      }
      routeMap.get(route.get(0)).add(route.get(1));
    }

    Map<String, NodeScore> visited = new HashMap<>();
    scoreDfs(startingAirport, routeMap, visited);
    for (String airport : airports) {
      if (!visited.containsKey(airport)) {
        scoreDfs(airport, routeMap, visited);
      }
    }
    int result = 0;
    List<NodeScore> scores = new ArrayList<>(visited.values());
    Collections.sort(scores);
    Map<String, NodeScore> connected = new HashMap<>();
    for (NodeScore nodeScore : scores) {
      if (!connected.containsKey(nodeScore.airport)) {
        scoreDfs(nodeScore.airport, routeMap, connected);
        result++;
      }
    }
    return result;
  }

  private static void scoreDfs(String startAirPort, Map<String, List<String>> routeMap,
      Map<String, NodeScore> visited) {
    if (routeMap.containsKey(startAirPort)) {
      for (String route : routeMap.get(startAirPort)) {
        if (visited.containsKey(route)) {
          if (!visited.containsKey(startAirPort))
            visited.put(startAirPort, new NodeScore(startAirPort, 0));
          visited.get(startAirPort).score += (visited.get(route).score + 1);
        } else {
          scoreDfs(route, routeMap, visited);
          if (!visited.containsKey(startAirPort))
            visited.put(startAirPort, new NodeScore(startAirPort, 0));
          visited.get(startAirPort).score += (visited.get(route).score + 1);
        }
      }
    } else {
      visited.put(startAirPort, new NodeScore(startAirPort, 0));
    }
  }

}