public class SumOfFabGreedy {


    public static void main(String[] args) {
        System.out.println(fibsum(4));
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

    static public int fibsum(int A) {
        buildFibbo();
        int result = 0;
        for(int i = fibbo.length-1; A>0 && i >= 0; i--){
            if(fibbo[i]<=A) {result++; A = A - fibbo[i]; i++;}
        }
        return result;
    }
}
