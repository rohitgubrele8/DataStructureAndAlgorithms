import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rohit on 01/07/20.
 */
public class AirportViditSol {

  public static void main(String[] args) {

    List<String> airports = new ArrayList<>(Arrays.asList("a", "b","c","d","e","f","g"));
    List<List<String>> route = new ArrayList<>();
    route.add(Arrays.asList("a","b"));
    route.add(Arrays.asList("b","c"));
    route.add(Arrays.asList("d","e"));
    route.add(Arrays.asList("f","d"));
    route.add(Arrays.asList("g","b"));

    System.out.println(airportConnections(airports, route, "a"));

  }
  static int ans  = 0;
  public static int airportConnections(List<String> airports, List<List<String>> routes,
      String startingAirport) {
    Map<String , Boolean> isStartPoint  = new HashMap<>();
    Map<String , List<String>> routesMap = new HashMap<>();

    for(List<String> route : routes){
      if(!routesMap.containsKey(route.get(0))){
        routesMap.put(route.get(0), new ArrayList<>());
      }
      routesMap.get(route.get(0)).add( route.get(1));
    }
    airports.add(0, startingAirport);
    ans = 0;
    //isStartPoint.put(startingAirport, true);
    for (int itr = 0; itr < airports.size(); itr++) {
      if(!isStartPoint.containsKey(airports.get(itr))) {
        dfs(routesMap, isStartPoint, airports.get(itr));

        if (itr == 0) {
          isStartPoint.put(airports.get(itr), false);
        } else {
          ans++;
          isStartPoint.put(airports.get(itr), true);
        }
      }
    }
    return ans;
  }



  public static void dfs(Map<String , List<String>> routesMap, Map<String , Boolean> isStartingPoint, String startNode){

    if(isStartingPoint.containsKey(startNode)){
      if(isStartingPoint.get(startNode)) {
        isStartingPoint.put(startNode, false);
        ans--;
      }
        return;
    }

    isStartingPoint.put(startNode, false);
    if(routesMap.containsKey(startNode)) {
      for (String connection : routesMap.get(startNode)) {
        dfs(routesMap, isStartingPoint, connection);
      }
    }

  }




}
