import java.util.HashMap;
import java.util.Map;

public class SumOfFibonacciGraph {

    public static void main(String[] args) {
        System.out.println(fibsum(100));
    }

        static int[] fibbo = new int[45];

        private static void buildFibbo(){
            if(fibbo[0] == 0){
                fibbo[0] =1;
                fibbo[1] =1;
                for (int i = 2; i<45; i++){
                    fibbo[i] = fibbo[i-1] + fibbo[i-2];
                }
            }
        }
        static int original;
        static public int fibsum(int A) {
            buildFibbo();
            original = A;
                for (int x = 0; fibbo[x] <= A; x++) {
                    findMinNumber(A - fibbo[x], x, 1);
                }
            return mins.get(original);
        }
        static Map<Integer, Integer> mins = new HashMap<>();

        private static void findMinNumber(int N , int x ,int curr){
            int currSum = original - N;
            if(mins.containsKey(currSum) && mins.get(currSum) > curr){
                mins.put(currSum, curr);
            }else mins.put(currSum, curr);

            if(N == 0 && (!mins.containsKey(original) || mins.get(original) > curr))  {mins.put(original, curr); return;}

            if(N< 0) return;
            for(int i = x+1; fibbo[i] <= N; i++){
                findMinNumber(N - fibbo[i], i, curr + 1);
            }
        }


}
