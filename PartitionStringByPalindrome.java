import java.util.ArrayList;
import java.util.Collections;

public class PartitionStringByPalindrome {


    static int[] twoPower = new int[32];
    static void generateTwoPower(){
        twoPower[0]=1;
        for(int i = 1; i <32 ; i++){
            twoPower[i] = 2*twoPower[i-1];
        }
    }

    public static void main(String[] args) {
        //System.out.println(partition("aabb"));

        System.out.println(computeDecimal("01"));

    }


    static private int computeDecimal(String code){
        int val = 0;
        for(int i = code.length()-1; i>= 0; i--){
            val+=(Integer.valueOf(code.substring(i, i+1))*twoPower[code.length()-i-1]);
        }
        return val;
    }


    static ArrayList<ArrayList<String>> result;
    static public ArrayList<ArrayList<String>> partition(String a) {
        result = new ArrayList();
        partitionString(a, new ArrayList<>() , 0);
        return result;
    }

    static private void partitionString(String s, ArrayList<String> curr, int start){

        if(start >= s.length()) {result.add(curr); return;}

        for(int i = 1; i+start<=s.length(); i++){
            String sub = s.substring(start, i + start);
            if(isPalindrome(sub)){
                curr.add(sub);
                partitionString(s, new ArrayList<>(curr), i + start);
                curr.remove(curr.size()-1);
            }
        }

    }

    static private boolean isPalindrome(String s){
        for(int i = 0, j = s.length()-1; i<j ; i++, j--){
            if(s.charAt(i) != s.charAt(j)) return false;
        }
        return true;
    }
}
