/*
*给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
* */

/*
* 题解
* 考虑到指数有可能为负数的情况
* */
public class 代码的完整性_数值的整数次方 {
    public double Power(double base, int exponent) {
        boolean flag = exponent >= 0? true:false;
        exponent = exponent >= 0? exponent:-exponent;
        double res = 1;
        for(int i = 0; i<exponent; i++){
            res *= base;
        }
        if(!flag)
            res = 1/res;
        return res;
    }
}
