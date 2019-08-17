package 刷题;

class Solution {
    public static int divide(int dividend, int divisor) {
        if(dividend == -2147483648){
            if(divisor == 1)
                return -2147483648;
            if(divisor == -1)
                return 2147483647;
        }
        int flag = 1;
        if((dividend>0&&divisor<0)||(dividend<0&&divisor>0))
            flag = -1;
        dividend = dividend>0?-dividend:dividend;
        divisor = divisor>0?-divisor:divisor;
        int res = 0;
        if(dividend>divisor)
            return 0;
        while(dividend<0){
            int k = 1;
            int divisor_tmp = divisor;
            int tmp=0;
            while(dividend<divisor_tmp){
                tmp = divisor_tmp;
                if(divisor_tmp+divisor_tmp>0||divisor_tmp+divisor_tmp==-2147483648)
                    break;
                divisor_tmp += divisor_tmp;
                k += k;
            }
            if(dividend>divisor_tmp){
                divisor_tmp -= tmp;
                k>>=1;
            }
            dividend -= divisor_tmp;
            res += k;
        }
        return res*flag;
    }

}