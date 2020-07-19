import java.util.ArrayList;

public class greyCode {

    public static void main(String[] args) {
        System.out.println(grayCode(25));
    }

    static int[] twoPower = new int[32];
    static void generateTwoPower(){
        if(twoPower[0] == 0){
            twoPower[0]=1;
            for(int i = 1; i <32 ; i++){
                twoPower[i] = 2*twoPower[i-1];
            }
        }
    }

    static ArrayList<String> codes;
    static ArrayList<Integer> result;


    static public ArrayList<Integer> grayCode(int a) {
        generateTwoPower();
        result = new ArrayList();
        codes  = new ArrayList();
        generateGreyCode(a, 0, new StringBuilder(),0);
        generateGreyCode(a, 1, new StringBuilder(),1);

        //Collections.sort(codes);

        for(String s : codes){
            result.add(computeDecimal(s));
        }
        // System.out.println(codes);
        return result;
    }

    static private void generateGreyCode(int len, int curr, StringBuilder s, int ini){

        s.append(curr);
        if(s.length() == len){
            codes.add(s.toString());
            return;
        }



        if(ini==0){
            generateGreyCode(len, 0 , s,0);
            s.deleteCharAt(s.length()-1);
            generateGreyCode(len, 1 , s,1);
            s.deleteCharAt(s.length()-1);
        }else{
            generateGreyCode(len, 1 , s,0);
            s.deleteCharAt(s.length()-1);
            generateGreyCode(len, 0 , s,1);
            s.deleteCharAt(s.length()-1);
        }

    }




    static private int computeDecimal(String code){
        int val = 0;
        for(int i = code.length()-1; i>= 0; i--){
            val+=(Integer.valueOf(code.substring(i, i+1))*twoPower[code.length()-i-1]);
        }
        return val;
    }
}
