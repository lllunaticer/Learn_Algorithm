
/*题目描述
写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/
四则运算符号。*/

/*
* 解题思路：
* 计算机中的加法首先将数字转为二进制，然后使用与和非这两种操作完成的
* 例如要计算A+B,得到的结果用CD表示：
*  如果A = 1 B = 0， 则A+B = 01(CD,C=0,D=1)
*  如果A = 1 B = 1， 则A+B = 10(CD,C=1,D=0)
*  如果A = 0 B = 1， 则A+B = 01(CD,C=0,D=1)
*  如果A = 0 B = 0， 则A+B = 00(CD,C=0,D=0)
*  可以看出D = A^B(低位是不进位加法)
*        C = A&B;（高位是A和B与的结果，即只有A和B同时为1时才有进位）
*
* 在计算时，可以先计算不进位加法 A^B = num  ,
*          再用计算进位 A&B = carry (carry需要左移一位加到新数上)
*          再将两个结果加起来(仍然使用上面的方法做这个加法)，直到carry变为0
* */
public class 发散思维能力_不用加减乘除做加法 {
    public static int Add(int num1,int num2) {
        int res = num1^num2;
        int carry = (num1&num2)<<1;
        int tmp;
        while(carry!=0){
            tmp = res^carry;
            carry = (res&carry)<<1;
            res = tmp;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Add(111,899));
    }
}
