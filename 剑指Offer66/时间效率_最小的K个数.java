/*题目描述
输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，
则最小的4个数字是1,2,3,4,。*/

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;

/*
 * 解法:
 * 从一堆数字里面找到前n大的数要建最小堆，这样堆顶是当前堆里的最小数，来了一个新数，如果它比堆顶的最小数大，就把堆顶的数替换成当前数，并调整堆使得当前堆顶仍然是最小数
 * 同理从一堆数字里面找到前n小的数要建最大堆，因此下面的优先队列的比较器需要重写
 * */
public class 时间效率_最小的K个数 {
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        int length = input.length;
        if (k > length || k == 0) {
            return result;
        }

//        匿名函数
        /*PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });*/

//        lambda表达式
/*        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k,
                (Integer o1, Integer o2) -> o2.compareTo(o1)
        );*/

//       简写
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k,
                Comparator.reverseOrder()//按从大到小顺序排序
        );


        for (int i = 0; i < length; i++) {
            /*if (maxHeap.size() != k) {
                maxHeap.offer(input[i]);
            } else if (maxHeap.peek() > input[i]) {
                Integer temp = maxHeap.poll();
                temp = null;
                maxHeap.offer(input[i]);
            }*/

            //这样写也是可以的，一旦超出大小就删掉堆顶元素，不用判断（来自雪菜）
//            代码复杂度低，运行复杂度比上面的高
//          笔试面试题中建议写这种代码复杂度较低，逻辑清晰，容易调试的代码。
            maxHeap.offer(input[i]);
            if (maxHeap.size() > k)
                maxHeap.poll();
        }
        result.addAll(maxHeap);
        return result;
    }
}
