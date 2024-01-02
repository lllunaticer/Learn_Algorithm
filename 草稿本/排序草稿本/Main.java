package 排序草稿本;

import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()){
            int n = in.nextInt();
            int m = in.nextInt();
            int[] records = new int[n+1];
            for(int i = 0; i<m; i++){
                records[in.nextInt()]++;
            }
            int min = Integer.MAX_VALUE;
            for(int j = 1; j<=n; j++)
                min = min<records[j]?min:records[j];
            System.out.println(min);
        }
    }
}
