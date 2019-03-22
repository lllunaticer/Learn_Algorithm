import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
/*
* 题目描述
给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，
那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}；
针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： {[2,3,4],2,6,2,5,1}，
{2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}，
 {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
* */
/*
* 时间复杂度， 每个元素只会进出窗口一次， 复杂度为O（N）*/
public class 第十二题滑动窗口的最大值 {
    static public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if (num.length == 0 || size == 0) {
//            do nothing
        } else {
//            q保存的是num中的下标，从双端队列q的左边弹出元素，从右边压入元素
//            保证存在q中的下标所对应在num里的元素是单调递减的，这样窗口中的最大值一直都是最左边的元素，也就是first元素
            Deque<Integer> q = new LinkedList<>();
            for (int i = 0; i < num.length; i++) {
//                用来控制窗口的大小始终为size, i是当前下标（窗口的右边下标）， q的first是窗口的左边下标，保证窗口左边 >= 窗口右边 - 窗口大小;
//                队列左边< i-size 的部分是过期的窗口，需要从队列中删除
                while (!q.isEmpty() && q.peekFirst() <= i - size)
                    q.pollFirst();
//                从队列的右边压入元素， 压入的元素如果大于当前窗口右边的元素，则可以将窗口右边较小的元素删除（此时需要保证这个窗口的元素从右到左是单调递减的）
//               注意只有从窗口的右边进行比较删除操作才能使得窗口保持单调递减
                while (!q.isEmpty() && num[q.peekLast()] <= num[i])
                    q.pollLast();
//                将新到的元素从队列右边压入
                q.offerLast(i);
//                当窗口的长度到达size长时开始输出元素。
                if (i >= size - 1)
                    res.add(num[q.peekFirst()]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] num = {2,3,4,2,6,2,5,1};
        System.out.println(maxInWindows(num, 3));
    }
}
