/*
* 题目描述
输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
* */

import java.util.Arrays;


/*
 *我们想，如果比较大小的时候，可以按照拼接结果的大小来对数组进行排序，则排序结果天然就能满足要求
 *1.需要定义一个新的比较大小的规则
 * 比较a和b两个数，如果a<b <==> ab < ba (ab表示将数字a和数字b直接连接成一个新的数字ab)
 *2.利用上述定义的大小比较规则将给定的数组排序
 *3.从排好序的数组里按从小到大的顺序取出数字来拼接成最终答案
 * 就是最小数字了
 *
 * 首先，满足上述大小比较规则的结果一定能是最小的结果，这是不言而喻的（a<b <==> ab < ba 这个性质决定的）
 * 其次，如果要定义一种大小比较规则，则离散数学告诉我们，这个规则必须满足一下两个定律该大小比较规则才能成立:
 * a.反对称性:a<=b, b<=a 则 => a=b
 * b.传递性: a<=b, b<=c, 则 => a<=c
 * 可以自行证明一下上面两条规律
 * */
public class 把数组排成最小的数 {

/*    class cmp implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            return (o1+o2).compareTo(o2+o1);
        }
    }*/

    public String PrintMinNumber(int [] numbers) {
//        先转为字符串
        String [] snum = new String[numbers.length];
        int j = 0;
        for(int i: numbers)
            snum[j++] = String.valueOf(i);
//        重写大小比较规则
        Arrays.sort(snum,(String s1, String s2)->(s1+s2).compareTo(s2+s1));
        StringBuilder res = new StringBuilder();
        for(String x:snum)
            res.append(x);
        return res.toString();
    }
}
