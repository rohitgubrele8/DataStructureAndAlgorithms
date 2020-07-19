import com.sun.org.apache.bcel.internal.generic.FSUB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class AllSubSets {

    public static void main(String[] args) {
        System.out.println(subsets(new ArrayList<>(Arrays.asList(12, 13))));
    }

    static ArrayList<ArrayList<Integer>> result  = new ArrayList();
    static public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> A) {
        result.add(new ArrayList<>());
        for(int  i = 0; i<A.size(); i++){
            generateSubSets(A, i, new ArrayList<>());
        }
        return result;
    }


    static private void generateSubSets(ArrayList<Integer> A, int start, ArrayList<Integer> currentSet){
        if(start == A.size()){
            return;
        }
        currentSet.add(A.get(start));
        result.add(currentSet);
        for(int i  = start+1;i<A.size(); i++)
        generateSubSets(A, i,new ArrayList<>(currentSet));
    }


    static public ArrayList<ArrayList<Integer>> subsets1(ArrayList<Integer> A) {
        if(A==null)
            return null;
        Collections.sort(A);
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for(int i=0;i<A.size();i++)
            subsets(A, result, new ArrayList(),i);
        return result;
    }
    static public  void subsets(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> temp, int i){
        temp.add(A.get(i));
        result.add(temp);
        if(i==A.size())
            return;
        for(int j=i+1;j<A.size();j++){
            subsets(A, result, new ArrayList<>(temp), j);
        }
    }

}
