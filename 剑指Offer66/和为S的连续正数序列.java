/*题目描述
小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。没多久,
他就得到另一组连续正数和为100的序列:18,19,20,21,22。现在把问题交给你,你能不能也很
快的找出所有和为S的连续正数序列? Good Luck!

输出描述:
输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序*/



/*
* 题解:
* 以求和为9的所有连续序列为例，我们先把small初始化为1，big初始化为2。此时介于small和big
* 之间的序列是{1，2}，序列的和为3，小于9，所以我们下一步要让序列包含更多的数字。我们把big
* 增加1变成3，此时序列为{1，2，3}。由于序列的和是6，仍然小于9，我们接下来再增加big变成4，
* 介于small和big之间的序列也随之变成{1，2，3，4}。由于序列的和10大于9，我们要删去序列中
* 的一些数字，于是我们增加small变成2，此时得到的序列是（2，3，4}，序列的和正好是9。我们找
* 到了第一个和为9的连续序列，把它打印出来。接下来我们再增加big，重复前面的过程，可以找到第
* 二个和为9的连续序列{4，5}。
* */
import java.util.*;
public class 和为S的连续正数序列 {
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        //存放结果
        ArrayList<ArrayList<Integer> > result = new ArrayList<>();
        //两个起点，相当于动态窗口的两边，根据其窗口内的值的和来确定窗口的位置和大小
        int plow = 1,phigh = 2;
        while(phigh > plow){
            //由于是连续的，差为1的一个序列，那么求和公式是(a0+an)*n/2
            int cur = (phigh + plow) * (phigh - plow + 1) / 2;
            //相等，那么就将窗口范围的所有数添加进结果集
            if(cur == sum){
                ArrayList<Integer> list = new ArrayList<>();
                for(int i=plow;i<=phigh;i++){
                    list.add(i);
                }
                result.add(list);
                plow++;
                //如果当前窗口内的值之和小于sum，那么右边窗口右移一下
            }else if(cur < sum){
                phigh++;
            }else{
                //如果当前窗口内的值之和大于sum，那么左边窗口右移一下
                plow++;
            }
        }
        return result;
    }
}
