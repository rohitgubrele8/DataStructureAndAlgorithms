import java.util.ArrayList;

public class KthPermutation {


    public static void main(String[] args) {
        System.out.println(getPermutation(3,4));
    }

    static StringBuilder result;
    static int[] factorial = new int[1000];
    static ArrayList<Integer> digits;
    static public String getPermutation(int A, int B) {
        result = new StringBuilder();
        digits = new ArrayList<>();
        for(int i = 1; i <= A; i++){
            digits.add(i);
        }
        getKthPermutation(A, B);
        return result.toString();
    }

    private static void getKthPermutation(int n, int k){
        if(n == 0) return;
        int index = (k/fact(n-1));
        if(k%fact(n-1) == 0) index--;
        int val = digits.get(index);
        digits.remove(index);
        result.append(val);
        getKthPermutation(n-1, k - index*fact(n-1));

    }

    static private int fact(int n){
        if(n == 0){
            factorial[n] = 1; return factorial[n];
        }
        if(factorial[n] == 0) {
            factorial[n] =  fact(n-1)*n;
        }
        return factorial[n];
    }



}
