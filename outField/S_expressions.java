/*
* S-expression is a prefix notation invented for and popularized by the programming language Lisp.
* Your task is to evaluate some simple S-expressions.

In this problem, S-expression is defined as:
1. An atom.
a. A nonnegative integer. Its value is the value of the integer.
b. A boolean value, true or false. Its value is the boolean value of itself.
c. A variable, consisting of no more than 10 lower case letters, excluding reserved words "if", "let",
"true" and "false". Its value is the value bound to the variable. If the varible is not bound yet,
produce an error "Unbound Identifier". (See below for details)
2. ( f x ... )
a. one of the following 4 forms: ( + x y ) ( - x y ) ( * x y ) ( / x y ) in which x and y are both S-expressions.
To evaluate the S-expression, you need to first evaluate x then evaluate y.
If the value of x or y is not an integer, produce an error "Type Mismatch".
If both x and y are integers, its value is the their sum(x+y)/difference(x-y)/product(x*y)/quotient(x/y).
 The division is the same as the integer division ("/" operator) in C/C++/Java/C#, truncated division.
 If the value of y is zero, produce an error: "Division By Zero".
b. ( if a b c ) in which a, b and c are S-expressions.
To evaluate the S-expression, you need to evaluate a at first.
If the value of a is not a boolean value, produce an error: "Type Mismatch".
If the value of a is true, evaluate b and the S-expression's value is the value of b.
If the value of a is false, evaluate c and the S-expression's value is the value of c.
Note that b or c may not be evaluated during the calculation.
c. ( let ( x a ) b ) in which x is a variable consisting of no more than 10 lower case letters,
excluding reserved words "if", "let", "true" and "false", a and b are S-expressions.
To evaluate the S-expression, you need to first evaluate a.
Then bind the value of a to the variable x and evaluate b. Binding means if variable x appears in b,
its value is the value of a. The S-expression's value is the value of b.
Note that if x is bound to another value in b, the outer binding is ineffective. For example the
value of ( let ( x 1 ) ( let ( x 2 ) x ) ) is 2.
d. one of the following 3 forms: ( < x y ) ( > x y ) ( = x y ) in which x and y are S-expressions.
To evaluate the S-expression, you need to first evaluate x then evaluate y.
If the value of x or y is not an integer, produce an error "Type Mismatch".
If both x and y are integers, its value is a boolean value indicating whether x < y, x > y or x = y is true.
Given an S-expression, output its value. If an error occurs stop the evaluation and output the error.

输入描述:
The first line contains an integer T (1 <= T <= 100), the number of testcases.
The following T lines each contain an S-expression consisting of no more than 200 characters.
It is guaranteed input S-expressions do not have any syntax error. The variables, parentheses, integers,
true and false are seperated by at least one space.
For 100% of the data the integers in the whole evaluation will not exceed 32bit signed integers.


输出描述:
For each testcase output the value of the S-expression or one of the three errors "Division By Zero",
"Type Mismatch" and "Unbound Identifier" without quotes.

输入例子:
2
( + ( - 3 2 ) ( * 4 5 ) )
( let ( x 4 ) ( if true x y ) )

输出例子:
21
4

---------------------
作者：wdlsjdl2
来源：CSDN
原文：https://blog.csdn.net/wdlsjdl2/article/details/53980438
版权声明：本文为博主原创文章，转载请附上博文链接！

*
* 坑挺多，巨麻烦，修修补补才过，不过思路倒是不难想。

题干已经列举了S-expression的几种情况：带关键词的，包括计算操作、if、let、true、false，不带关键词的，
包括变量(用小写字母表达）、32位整数、bool值

补充一下，S-expression的第二种情况即 f (x....) 还可以出现'>'、'<'、'=' 运算符，这在题目中没有说明。

首先给一个S-expression，首尾有空格的话先去掉一层，之后判定属于哪一种情况

举个例子，比如符合关键词"if"的情况：会有if a b c的形式出现，需要特别注意的是a、b、c都是S-expression，
也就意味着实际出现的式子有可能是复合的形式例如

if ( > 3 2 ) ( + 4 ( + ( - 3 2 ) ( * 4 5 ) ) ) 8    ，a对应( > 3 2 )，b对应( + 4 ( + ( - 3 2 ) ( * 4 5 ) ) )，
c对应8，这就涉及到一个找出子S-expression即找出if关键字后面的a、b、c的过程，由于a、b、c可能也是一个复合的形式，
这个过程可以使用栈结构来做辅助，记录'('的数量，当扫描到空字符且'('的数量与')'的数量相等的时候就意味着已经取到了一个完整的子S-expression，
对子S-expression可以递归处理。

返回值使用长度为2的数组来记录状态，各个状态的含义如下

retarr[0] 的取值	代表的含义
-3	Unbound Identifier
-2	Division By Zero
-1	Type Mismatch
0	S-expression的计算结果是32位整数
1	S-expression的计算结果是bool值
2	S-expression是一个变量，变量没有绑定值
3	S-expression是一个变量，变量绑定值是bool变量
4	S-expression是一个变量，变量绑定值是32位整数


当retarr[0]的取值为0、1、2、3时，retarr[1]的值有意义，其含义是具体的值
例如式子( * 4 5 ) 的返回值数组为retarr[0]=0，retarr[1]=20



关键字为let的时候要特别注意 let ( x a ) b 的形式中本身包含了一对括号，扫描子S-expression的时候要分开扫描

其他的按照题干描述来做，四则运算、关系运算的时候需要检测 ( F x y ) 中的x、y是否是整数，if a b c 中的a是否是bool值，
let ( x a ) b 中的x是否是变量，出现非法情况都要返回对应的错误类型，出现复合let语句可能使一个变量进行多次”绑定“，
所以需要区分状态”不是变量不能赋值“、”是变量未赋值“、”是变量已经赋值“，多次绑定无需单独处理，直接按照题干描述的规则迭代即可。


---------------------
作者：wdlsjdl2
来源：CSDN
原文：https://blog.csdn.net/wdlsjdl2/article/details/53980438
版权声明：本文为博主原创文章，转载请附上博文链接！
* */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class S_expressions {


    HashMap<String, String> hashmap;

    public static void main(String[] args) {
        S_expressions t = new S_expressions();
        t.getResult();
    }


    public void getResult() {
        Scanner cin = new Scanner(System.in);
        int t = cin.nextInt();
        cin.nextLine();
        while (t > 0) {
            String s = cin.nextLine();
            hashmap = new HashMap<>();
            int[] ret = valueOfExp(s, 0, s.length() - 1);
            switch (ret[0]) {
                case 0:
                    System.out.println(ret[1]);
                    break;
                case 1:
                    System.out.println(ret[1] == 1 ? "true" : "false");
                    break;
                case -1:
                    System.out.println("Type Mismatch");
                    break;
                case -2:
                    System.out.println("Division By Zero");
                    break;
                case 2:
                case -3:
                    System.out.println("Unbound Identifier");
                    break;
                default:
                    break;
            }

            t--;
        }
    }


    public int[] valueOfExp(String s, int begin, int end) {
        if (s.charAt(begin) == '(' && s.charAt(end) == ')') {
            begin += 2;
            end -= 2;
        }

        ArrayList<Integer> divideInfo = divideStr(s, begin, end);

        switch (divideInfo.get(0)) {
            case 1: {
                int[] part1 = valueOfExp(s, divideInfo.get(1), divideInfo.get(2));
                int[] part2 = valueOfExp(s, divideInfo.get(3), divideInfo.get(4));
                int[] part3 = valueOfExp(s, divideInfo.get(5), divideInfo.get(6));
                if (!(part1[0] == 1 || part1[0] == 3))
                    return new int[]{-1, 0};
                else {
                    return part1[1] == 1 ? part2 : part3;
                }
            }
            case 2: {
                int[] part1 = valueOfExp(s, divideInfo.get(1), divideInfo.get(2));
                int[] part2 = valueOfExp(s, divideInfo.get(3), divideInfo.get(4));
                if (part1[0] < 2)
                    return new int[]{-1, 0};

                String word = s.substring(divideInfo.get(1), divideInfo.get(2) + 1);
                if (part2[0] == 1 || part2[0] == 3)
                    hashmap.put(word, part2[1] == 1 ? "true" : "false");
                else if (part2[0] == 0 || part2[0] == 4) {
                    hashmap.put(word, String.valueOf(part2[1]));
                }

                int[] part3 = valueOfExp(s, divideInfo.get(5), divideInfo.get(6));
                if (part3[0] == 2)
                    return new int[]{-3, 0};

                if (part3[0] == 3)
                    return new int[]{1, (part3[1] == 1 ? 1 : 0)};
                else if (part3[0] == 4)
                    return new int[]{0, part3[1]};
                else {
                    return part3;
                }
            }
            case 3:
                return new int[]{1, 1};

            case 4:
                return new int[]{1, 0};

            case 5: {
                int[] part1 = valueOfExp(s, divideInfo.get(1), divideInfo.get(2));
                int[] part2 = valueOfExp(s, divideInfo.get(3), divideInfo.get(4));
                if (!((part1[0] == 0 || part1[0] == 4) && (part2[0] == 0 || part2[0] == 4)))
                    return new int[]{-1, 0};
                char op = s.charAt(begin);
                int[] retarr = new int[2];
                retarr[0] = 0;
                switch (op) {
                    case '+':
                        retarr[1] = part1[1] + part2[1];
                        break;
                    case '-':
                        retarr[1] = part1[1] - part2[1];
                        break;
                    case '*':
                        retarr[1] = part1[1] * part2[1];
                        break;
                    case '/':
                        if (part2[1] == 0) {
                            retarr[0] = -2;
                            retarr[1] = 0;
                        } else {
                            retarr[1] = part1[1] / part2[1];
                        }
                        break;
                    case '>':
                        retarr[0] = 1;
                        retarr[1] = part1[1] > part2[1] ? 1 : 0;
                        break;
                    case '<':
                        retarr[0] = 1;
                        retarr[1] = part1[1] < part2[1] ? 1 : 0;
                        break;
                    case '=':
                        retarr[0] = 1;
                        retarr[1] = part1[1] == part2[1] ? 1 : 0;
                        break;
                    default:
                        break;
                }
                return retarr;
            }
            case 6: {
                String word = s.substring(begin, end + 1);
                if (hashmap.containsKey(word)) {
                    String val = hashmap.get(word);
                    if (val.compareTo("true") == 0)
                        return new int[]{3, 1};
                    else if (val.compareTo("false") == 0)
                        return new int[]{3, 0};
                    return new int[]{4, Integer.valueOf(val)};
                }

                char firstChar = word.charAt(0);
                if (firstChar >= '0' && firstChar <= '9')
                    return new int[]{0, Integer.valueOf(word)};
                return new int[]{2, 0};
            }

            default:
                return new int[]{-4, 0};
        }
    }

    public ArrayList<Integer> divideStr(String s, int begin, int end) {
        StringBuilder sb = new StringBuilder();
        int i = begin;
        for (; i <= end; i++)
            if (s.charAt(i) != ' ')
                sb.append(s.charAt(i));
            else {
                break;
            }
        String word = sb.toString();
        ArrayList<Integer> arraylist = new ArrayList<>();

        if (word.compareTo("if") == 0) {
            arraylist.add(1);
            findKExp(s, i + 1, end, 3, arraylist);
        } else if (word.compareTo("let") == 0) {
            arraylist.add(2);
            int leftcount = 1;
            int leftindex = i + 1;
            int rightindex = i + 1;
            for (; i <= end; i++)
                if (s.charAt(i) == ')')
                    if (--leftcount == 0) {
                        rightindex = i;
                        break;
                    }
            findKExp(s, leftindex + 2, rightindex - 2, 2, arraylist);
            findKExp(s, rightindex + 2, end, 1, arraylist);
        } else if (word.compareTo("true") == 0) {
            arraylist.add(3);
        } else if (word.compareTo("false") == 0) {
            arraylist.add(4);
        } else if (word.compareTo("+") == 0 || word.compareTo("-") == 0 || word.compareTo("*") == 0 || word.compareTo("/") == 0 || word.compareTo(">") == 0 || word.compareTo("<") == 0 || word.compareTo("=") == 0) {
            arraylist.add(5);
            findKExp(s, i + 1, end, 2, arraylist);
        } else {
            arraylist.add(6);
        }
        return arraylist;
    }

    public void findKExp(String s, int begin, int end, int k, ArrayList<Integer> arraylist) {
        int leftcount = 0;
        int pre = begin - 1;
        int cur = begin;
        int cnt = 0;
        while (cnt < k && cur <= end) {
            char c = s.charAt(cur);
            if (c == '(')
                leftcount++;
            else if (c == ')')
                leftcount--;
            else if (c == ' ' && leftcount == 0) {
                arraylist.add(pre + 1);
                arraylist.add(cur - 1);
                pre = cur;
                cnt++;
            }
            cur++;
        }
        arraylist.add(pre + 1);
        arraylist.add(cur - 1);
    }


}
