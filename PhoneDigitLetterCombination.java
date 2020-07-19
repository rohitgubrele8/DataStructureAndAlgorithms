import sun.plugin2.gluegen.runtime.StructAccessor;

import java.util.ArrayList;
import java.util.Arrays;

public class PhoneDigitLetterCombination {

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }



    public static ArrayList<ArrayList<String>> map;
    public static String[] b2 = {"a","b","c"};
    public static String[] b3 = {"d","e","f"};
    public static String[] b4 = {"g","h","i"};
    public static String[] b5 = {"j","k","l"};
    public static String[] b6 = {"m","n","o"};
    public static String[] b7 = {"p","q","r","s"};
    public static String[] b8 = {"t","u","v"};
    public static String[] b9 = {"w","x","y","z"};


    static ArrayList<String> result;

    static public ArrayList<String> letterCombinations(String A) {
        map = new ArrayList<>();
        map.add(new ArrayList<>(Arrays.asList("0")));
        map.add(new ArrayList<>(Arrays.asList("1")));
        map.add(new ArrayList<>(Arrays.asList(b2)));
        map.add(new ArrayList<>(Arrays.asList(b3)));
        map.add(new ArrayList<>(Arrays.asList(b4)));
        map.add(new ArrayList<>(Arrays.asList(b5)));
        map.add(new ArrayList<>(Arrays.asList(b6)));
        map.add(new ArrayList<>(Arrays.asList(b7)));
        map.add(new ArrayList<>(Arrays.asList(b8)));
        map.add(new ArrayList<>(Arrays.asList(b9)));

        result = new ArrayList<>();
        printCombination(A.split(""), 0, new StringBuilder());
        return result;
    }

    static void printCombination(String[] input, int i, StringBuilder curr){

        if(curr.length() == input.length){ result.add(curr.toString()); return;}
        for(String s : map.get(Integer.valueOf(input[i]))){
        curr.append(s);
        printCombination(input, i+1, new StringBuilder(curr));
        curr.deleteCharAt(curr.length()-1);
        }
    }
}
