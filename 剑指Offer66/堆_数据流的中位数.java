import java.util.Comparator;
import java.util.PriorityQueue;

/*题目描述
如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。
如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。*/

/*
* 解题思路（来自雪菜）：
* 维护两个堆（图见 堆_数据流的中位数.png）
* 维护一个大根堆和一个小根堆，如图:
* 小根堆在上，存的是较大的数（小根堆的堆顶是堆里最小的数，小根堆里其他数都比它大）；
* 大根堆在下，存的是较小的数（大根堆的堆顶是堆里较大的数，大根堆里其他数都比它小）；
* 我们的目的是维护这两个堆，使得小根堆里面存的数都比大根堆里面存的数大
* 每次插入数据都从下面的小根堆插入，插入后，比较大根堆和小根堆堆顶数的大小，如果下面的大根堆的堆顶比上面的小根堆的堆顶要大，
* 则交换两个堆的堆顶；
* 然后再进行一步判断:如果下面的大根堆的数据的个数比上面小根堆的数据个数多2个，则直接取出下面大根堆堆顶元素插入上面的小根堆中；
*
* 最后判断:
* 如果两个堆中元素的总个数为奇数，则中位数是下面大根堆的堆顶；
* 如果两个堆中元素的总个数为偶数，则中位数是两个堆堆顶元素的平均数
* */
public class 堆_数据流的中位数 {

//    优先队列默认是按自然顺序排序，也就是从小到大排序的
    PriorityQueue<Integer> min_heap = new PriorityQueue<>();
    PriorityQueue<Integer> max_heap = new PriorityQueue<>(Comparator.reverseOrder());
    public void Insert(Integer num) {
        max_heap.offer(num);
// 每次插入数据都从下面的小根堆插入，插入后，比较大根堆和小根堆堆顶数的大小，如果下面的大根堆的堆顶比上面的小根堆的堆顶要大，
// 则交换两个堆的堆顶；
        if(!min_heap.isEmpty() && !max_heap.isEmpty() && min_heap.peek()<max_heap.peek()){
            int tmp_big = max_heap.poll();
            int tmp_small = min_heap.poll();
            min_heap.offer(tmp_big);
            max_heap.offer(tmp_small);
        }
        //如果下面的大根堆的数据的个数比上面小根堆的数据个数多2个，则直接取出下面大根堆堆顶元素插入上面的小根堆中；
        if(max_heap.size() - min_heap.size() > 1){
            min_heap.offer(max_heap.poll());
        }
    }

//    如果两个堆中元素的总个数为奇数，则中位数是下面大根堆的堆顶；
//    如果两个堆中元素的总个数为偶数，则中位数是两个堆堆顶元素的平均数
    public Double GetMedian() {
        if ((max_heap.size() + min_heap.size())%2 != 0)
            return new Double(max_heap.peek());
        else
            return (max_heap.peek().doubleValue()+min_heap.peek().doubleValue())/2;

    }
}
