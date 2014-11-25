import java.util.*;

public class RandomDis{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        Random jenny = new Random();
        System.out.println("How many random numbers? ");
        int nums = s.nextInt();
        System.out.println("Number of values? ");
        int values = s.nextInt();
        int[] counter = new int[values];
        for( int x = 0 ; x < nums ; x++ ){
            int generatedNum = jenny.nextInt(values);
            counter[generatedNum]++;
        }
        for( int y = 0 ; y < values ; y++ ){
            System.out.println(y + " " + counter[y]);
        }
    }
}