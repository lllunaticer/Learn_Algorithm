/*
 * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？
 * 为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,
 * 但是对于后面问题他就没辙了。ACMer希望你们帮帮他,并把问题更加普遍化,
 * 可以很快的求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）。
 * */

import java.util.ArrayList;
import java.util.List;

/*
 *
 * */
public class 整数中1出现的个数 {
    public int NumberOf1Between1AndN_Solution(int n) {
        if (n == 0)
            return 0;
        List<Integer> number = new ArrayList<>();
        while (n != 0) {
            number.add(n % 10);
            n /= 10;
        }
        int res = 0;
        for (int i = number.size() - 1; i >= 0; i--) {
            int left = 0;
            int right = 0;
            int t = 1;
            for (int j = number.size() - 1; j > i; j--) {
                left = left * 10 + number.get(j);
            }
            for (int j = i - 1; j >= 0; j--) {
                right = right * 10 + number.get(j);
                t *= 10;
            }
            res += left * t;
            if (number.get(i) == 1) res += right+1;
            else if (number.get(i) > 1)
                res += t;
        }
        return res;
    }
}
