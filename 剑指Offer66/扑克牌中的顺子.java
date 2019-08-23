/*
*题目：从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌
|是不是连续的。2~10为数字本身，A为1，J为11，Q为12，K为13，而大、小王可以看成任意数字。
* */

/*
*我们需要把扑克牌的背景抽象成计算机语言。不难想象，我们可以把5张牌看成由5个数字组成的数组。
* 大、小王是特殊的数字，我们不妨把它们都定义为0，这样就能和其他扑克牌区分开来了。
接下来我们分析怎样判断5个数字是不是连续的，最直观的方法是把数组排序。值得注意的是，由于0
可以当成任意数字，我们可以用0去补满数组中的空缺。如果排序之后的数组不是连续的，即相邻的两
个数字相隔若干个数字，那么只要我们有足够的0可以补满这两个数字的空缺，这个数组实际上还是连续的。
举个例子，数组排序之后为0，1，3，4，5}，在1和3之间空缺了一个2，刚好我们有一个0，也就是我们可
以把它当成2去填补这个空缺。
于是我们需要做3件事情：首先把数组排序；其次统计数组中0的个数；最后统计排序之后的数组中相邻数字
之间的空缺总数。如果空缺的总数小于或者等于0的个数，那么这个数组就是连续的；反之则不连续。最后
我们还需要注意一点：如果数组中的非0数字重复出现，则该数组不是连续的。换成扑克牌的描述方式就是：
如果一副牌里含有对子，则不可能是顺子。
* */

import java.util.Arrays;

//为了方便起见,你可以认为大小王是0。
public class 扑克牌中的顺子 {
    public static boolean isContinuous(int[] numbers) {
        if (numbers == null || numbers.length == 0)
            return false;
        Arrays.sort(numbers);
        int zeros = 0;
        int index = 0;
        while (numbers[index] == 0) {
            zeros++;
            index++;
        }

        for (int i = index + 1; i < numbers.length; i++) {
            int diff = numbers[i] - numbers[i - 1];
            if (diff == 0) {
                return false;
            } else if (diff == 1) {
                continue;
            } else if (diff > 1) {
                while (diff != 1) {
                    diff--;
                    zeros--;
                    if (zeros < 0)
                        return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] a = {0, 3, 2, 6, 4};
        isContinuous(a);
    }
}
