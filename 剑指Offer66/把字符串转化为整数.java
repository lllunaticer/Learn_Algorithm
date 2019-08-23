/*题目描述
将一个字符串转换成一个整数(实现Integer.valueOf(string)的功能，但是string不符合数字要求时返回0)，
要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0。

输入描述:
输入一个字符串,包括数字字母符号,可以为空
输出描述:
如果是合法的数值表达则返回该数字，否则返回0
    示例1
    输入
    +2147483647
        1a33

    输出
    2147483647
        0*/

/**/
public class 把字符串转化为整数 {
    public static boolean flag;
    public static int StrToInt(String str) {
        //flag的作用时判断输入是否非法
        /*非法的情况分别有：
        * 1. 字符串为null或者为空
        * 2. 字符串中有除了数字0~9以及正负号以外的其他字符
        * 3. +、-号出现在第一位以外的其他位置
        * 4. 字符串表示的数字对int型来说产生了溢出*/

        /*为了区分是因为输入零返回值为0还是因为非法输入返回0，这里添加一个flag来作为非法输入的标识*/
        flag = false;
        //判断输入是否合法，先用str.trim()去除首尾的空格
        if (str == null || str.trim().equals("")) {
            flag = true;
            return 0;
        }
        // symbol=0,说明该数为正数;symbol=1，该数为负数;start用来区分第一位是否为符号位
        int symbol = 0;
        int start = 0;
        char[] chars = str.trim().toCharArray();
        if (chars[0] == '+') {
            start = 1;
        } else if (chars[0] == '-') {
            start = 1;
            symbol = 1;
        }
        int result = 0;
        for (int i = start; i < chars.length; i++) {
            if (chars[i] > '9' || chars[i] < '0') {
                flag = true;
                return 0;
            }
            int sum = result * 10 + (chars[i] - '0');

            //判断是否溢出，举个例子,3*10+5=35,如果(35-5)/10不等于3，那么就产生了溢出
            if ((sum - (chars[i] - '0')) / 10 != result) {
                flag = true;
                return 0;
            }

            result = result * 10 + (chars[i] - '0');
            /*
             * 当输入为value=2147483648时，在计算机内部的表示应该是-2147483648
             * 显然value>Integer.MAX_VALUE是不成立的
             */
        }
        // 注意：java中-1的n次方不能用：(-1)^n .'^'异或运算
        // 注意，当value=-2147483648时，value=-value
        result = (int) Math.pow(-1, symbol) * result;
        return result;
    }
}
