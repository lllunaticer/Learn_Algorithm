/*
*求1+2+3+...+n，要求不能使用乘除法、
* for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
* */
//链接：https://www.nowcoder.com/questionTerminal/7a0da8fc483247ff8800059e12d7caf1
//        来源：牛客网
/*题目说了不许用循环，那就摆明了是要递归了。*/
/*链接：https://www.nowcoder.com/questionTerminal/7a0da8fc483247ff8800059e12d7caf1
来源：牛客网

1.需利用逻辑与的短路特性实现递归终止。
2.当n==0时，(n>0)&&((sum+=Sum_Solution(n-1))>0)只执行前面的判断，为false，然后直接返回0；
3.当n>0时，执行sum+=Sum_Solution(n-1)，实现递归计算Sum_Solution(n)。*/
public class 发散思维能力_求1加2加3加n {
    public int Sum_Solution(int n) {
        int sum = n;
        boolean flag = (sum>0)&&((sum+=Sum_Solution(--n))>0);//短路特性， boolean值没有意义
        return sum;
    }
}
