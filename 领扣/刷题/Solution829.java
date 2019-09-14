package 刷题;

public class Solution829 {
    static public int consecutiveNumbersSum(int N) {
        int cnt = 0;
        int sum = 0;
        int pl = 0, pr = 1;
        while(pr<=N){
            if(sum == N){
                cnt++;
                sum += pr++;
            }
            else if(sum<N){
                sum += pr++;
            }
            else{
                sum -= pl++;
            }
        }
        return cnt+1;
    }

    public static void main(String[] args) {
        System.out.println(consecutiveNumbersSum(77601076));
    }
}
