import java.util.ArrayList;

public class AllCombinationOfSizeK {

    public static void main(String[] args) {
        System.out.println(combine(3, 2));
    }

    static ArrayList<ArrayList<Integer>> result;
    static public ArrayList<ArrayList<Integer>> combine(int A, int B) {
        result = new ArrayList<ArrayList<Integer>>();
        for(int i = 1;i <= A; i++){
            generateCombination(A, B, i, new ArrayList());
        }
        return result;
    }

    private static void generateCombination(int n, int k, int i, ArrayList<Integer> comb){

        if(comb.size() == k){
            return;
        }

        comb.add(i);
        if(comb.size() == k)
            result.add(comb);

        for(int j = i+1; j <= n; j++){
            generateCombination(n,k, j,new ArrayList(comb));
        }
    }

}
