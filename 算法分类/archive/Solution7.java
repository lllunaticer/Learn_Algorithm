package archive;

import java.util.Stack;

public class Solution7 {
    public int reverse(int x) {
        int result = 0;
        int index = 1;
        int guard = x % 10;
        Stack<Integer> bucket = new Stack<>();
        boolean flag = true;
        if (x < 0) {
            x = -x;
            flag = false;
        }
        while (x > 0) {
            bucket.push(x % 10);
            x = (x - x % 10) / 10;
        }
        if (bucket.size() == 10 && guard > 2) {//这里处理溢出考虑两种情况，第一种是大型溢出，即原来的数据有十位数并且最后一位大于2，
            // 则反转之后的数会溢出边界一次以上，即溢出之后的数有可能为正也可能为负。
        } else {
            while (!bucket.empty()) {
                result = result + bucket.pop() * index;
                if (result < 0) {//这里处理第二种溢出，小型溢出情况，原始数据反转之后，溢出值不会超过int的最大值一倍以上，则此时溢出会改变符号。
                    result = 0;
                    break;
                }
                index = index * 10;
            }
            if (!flag)
                result = -result;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution7 a = new Solution7();
        System.out.println(a.reverse(146384));
    }
}
