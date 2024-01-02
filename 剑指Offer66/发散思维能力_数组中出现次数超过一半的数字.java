
//本题有两个，一个来自ACwing, 解法比较巧妙
//还有一个见下方，来自牛客

/*
描述：
* 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。

假设数组非空，并且一定存在满足条件的数字。

要求只能使用 O(n) 的时间和额外 O(1) 的空间，该怎么做呢？
* */
import java.util.*;

/*
 * 题解
 * 用一个cnt记录次数
 * 一个val记录值
 * 如果下一个数与保存的val相同，则cnt++;
 * 否则
 * cnt--
 * 当cnt减到0了，就把val重新赋给新值
 * 因为数组中有一个数多于一半，所以最后val中保存的一定是这个数字
 * */
public class 发散思维能力_数组中出现次数超过一半的数字 {
    public int MoreThanHalfNum_Solution(int[] array) {
        int cnt = 0;
        int val = -1;
        for (int x : array) {
            if (cnt == 0) {
                val = x;
                cnt = 1;
            } else {
                if (x == val)
                    cnt++;
                else
                    cnt--;
            }

        }
        return val;
    }

    /*
题目描述
* 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
* 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，
* 超过数组长度的一半，因此输出2。如果不存在则输出0。
* */

//    下面的解法是牛客网上题目的解法，其实牛客的这道题目意义不大，就是统计每个数字出现次数找出出现次数最大的数字这样一个过程
//    下面的解法主要训练了自己使用HashMap按照value降序排序的一个方法
    public static int m(int[] array) {
        Map<Integer, Integer> res = new HashMap<>();
        for (int x : array) {
            if (res.containsKey(x))
                res.put(x, res.get(x) + 1);
            else
                res.put(x, 1);
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(res.entrySet());
        Collections.sort(list, (Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2)->o2.getValue().compareTo(o1.getValue()));
        int max = list.get(0).getValue();
        if (max > array.length / 2)
            return list.get(0).getKey();
        else
            return 0;
    }

    public static void main(String[] args) {
        发散思维能力_数组中出现次数超过一半的数字 a = new 发散思维能力_数组中出现次数超过一半的数字();
        int [] arr = {1,2,3,2,2,2,5,4,2};
        System.out.println(a.m(arr));
    }
}
