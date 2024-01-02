/*题目描述
请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。*/

import java.util.regex.Pattern;

public class 表示数值的字符串 {
    /*
    * ^ 和 美元符号框定正则表达式，它指引这个正则表达式对文本中的所有字符都进行匹配。如果省略这些标识，
    * 那么只要一个字符串中包含一个数字这个正则表达式就会进行匹配。如果仅包含 ^ ，它将匹配以一个数字开头
    * 的字符串。如果仅包含$ ，则匹配以一个数字结尾的字符串。

[-+]?
正负号后面的 ? 后缀表示这个负号是可选的,表示有0到1个负号或者正号

\\d*
\d的含义和[0-9]一样。它匹配一个数字。后缀 * 指引它可匹配零个或者多个数字。

(?:\\.\\d*)?
(?: …)?表示一个可选的非捕获型分组。* 指引这个分组会匹配后面跟随的0个或者多个数字的小数点。

(?:[eE][+\\-]?\d+)?
这是另外一个可选的非捕获型分组。它会匹配一个e(或E)、一个可选的正负号以及一个或多个数字。
    * */
    public static boolean isNumeric(char[] str) {
        String pattern = "^-?\\d*(?:\\.\\d*)?(?:[eE][+\\-]?\\d+)?$";
        String s = new String(str);
        return Pattern.matches(pattern,s);
    }

    public static void main(String[] args) {
        char[] chars = {'1','.','0','2','E','-','2'};
        System.out.println(isNumeric(chars));
    }

    /*思路2：
    表示数值的字符串遵循模式A[[B]][e|EC]或者.B[e|EC]，其中A为数值的整数部分，B紧跟着小数点为数值的小数部分，
    C紧跟着'e'或者'E'为数值的指数部分。在小数里可能没有数值的整数部分。例如，小数.123等于0.123。因此A部分不是
    必需的。如果一个数没有整数部分，那么它的小数部分不能为空。
    上述A和C都是可能以'+'或者'-'一开头的0~9的数位串；B也是0～9的数位串，但前面不能有正负号。
    以表示数值的字符串“123.45e+6"为例，“123”是它的整数部分A，“45”是它的小数部分B，“+6”是它的指数部分C。
    判断一个字符串是否符合上述模式时，首先尽可能多地扫描0~9的数位（有可能在起始处有中或者’一），也就是前面模式中
    表示数值整数的A部分。如果遇到小数点，则开始扫描表示数值小数部分的B部分。如果遇到'e或者E’，则开始扫描表示数值
    指数的C部分。
    * */

    private int index = 0;

    public boolean isNumeric2(char[] str) {
        if (str.length < 1)
            return false;

        boolean flag = scanInteger(str);

        if (index < str.length && str[index] == '.') {
            index++;
            flag = scanUnsignedInteger(str) || flag;
        }

        if (index < str.length && (str[index] == 'E' || str[index] == 'e')) {
            index++;
            flag = flag && scanInteger(str);
        }

        return flag && index == str.length;

    }

    private boolean scanInteger(char[] str) {
        if (index < str.length && (str[index] == '+' || str[index] == '-') )
            index++;
        return scanUnsignedInteger(str);

    }

    private boolean scanUnsignedInteger(char[] str) {
        int start = index;
        while (index < str.length && str[index] >= '0' && str[index] <= '9')
            index++;
        return start < index; //是否存在整数
    }

}
