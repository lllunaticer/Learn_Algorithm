/*题目描述
把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，
因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。*/

/*思路是，所有的丑数都是由之前的数乘2，或者乘3，或者乘5得来的。
* 我们用i2, i3, i5分别表示前面序列中下一个将要乘2的位置、下一个将要乘3的位置和
* 下一个将要乘5的位置。每次用这 三个数乘以各自因子得到的结果中找出最小的那个数添加到丑数序列中，
* 并更新对这个丑数做出贡献的乘子的下标i2或i3或i5*/
import java.util.*;

public class 搜索_丑数 {
    public static int GetUglyNumber_Solution(int index) {
        List<Integer> res = new ArrayList<>();
        int i2 = 0, i3 = 0, i5 = 0;
        res.add(1);
        int r2, r3, r5, min;
        while (res.size() < index) {
            r2 = res.get(i2) * 2;
            r3 = res.get(i3) * 3;
            r5 = res.get(i5) * 5;

            min = Math.min(r2, Math.min(r3, r5));
            res.add(min);
            //不可写作：
/*           if (min == r2) i2++;
            else if (min == r3) i3++;
            else if (min == r5) i5++;*/
           //因为当有可能出现同时min == r2, min == r3, 此时要同时更新r2,r3

            if (min == r2) i2++;
            if (min == r3) i3++;
            if (min == r5) i5++;
        }
        return res.get(index - 1);
    }

    public static void main(String[] args) {
        GetUglyNumber_Solution(7);
    }
}
