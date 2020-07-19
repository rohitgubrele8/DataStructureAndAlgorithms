import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class AllPossibleSumComb {

    public static void main(String[] args) {
        System.out.println(combinationSum(new ArrayList<>(Arrays.asList(7 ,8, 10, 6, 11, 1, 16, 8)),28));
    }


    static ArrayList<ArrayList<Integer>> result;
    static public ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> A, int B) {
        result = new ArrayList<>();
        ArrayList<Integer> a = new ArrayList<>(new HashSet<>(A));
        Collections.sort(a);
        for(int i = 0 ; i<a.size(); i++){
            getSumCombination(a, B, new ArrayList<>(),i, 0);
        }
        return result;
    }

   static private void getSumCombination(ArrayList<Integer> A, int tsum,ArrayList<Integer> comb, int i, int sum){

        if(sum >= tsum){
            //System.out.println(sum);
            return;
        }
        comb.add(A.get(i));
        sum +=A.get(i);
        if(sum == tsum) {result.add(comb); return;}
        //System.out.println(sum);
        for(int j = i; j<A.size(); j++){
            getSumCombination(A, tsum , new ArrayList<>(comb), j, sum);
        }
    }

}
