import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class AllSubSet2 {

    public static void main(String[] args) {
        System.out.println(subsetsWithDup(new ArrayList<>(Arrays.asList(12,12,14,16,18))));
    }


    static ArrayList<ArrayList<Integer>> result;
    static ArrayList<String> temp;
    static public ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> A) {
        result = new ArrayList<>();
        temp = new ArrayList();
        result.add(new ArrayList());
        Collections.sort(A);
        for(int i = 0 ; i<A.size(); i++){
            getAllCombination(A, new StringBuilder(), i);
        }
        for(String s : temp){
            result.add(new ArrayList(Arrays.asList(s.split(","))));
        }
        return result;
    }

    static private void getAllCombination(ArrayList<Integer> A, StringBuilder comb, int i){

        comb.append(A.get(i));
        comb.append(",");
        String currComb = comb.substring(0, comb.length()-1);
        if(!temp.contains(currComb)) temp.add(currComb);

        for(int j = i+1; j<A.size(); j++){
            getAllCombination(A, comb, j);
            comb.delete(comb.lastIndexOf(A.get(j).toString()),comb.length()-1);
            comb.deleteCharAt(comb.length()-1);
        }
    }

}
