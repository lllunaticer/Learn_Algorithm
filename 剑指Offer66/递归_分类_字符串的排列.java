/*
* 题目描述
输入一个字符串,按字典序打印出该字符串中字符的所有排列。
例如输入字符串abc,则打印出由字符a,b,c所能排列出来的
所有字符串abc,acb,bac,bca,cab和cba。
输入描述:
输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
* */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
 * 题解:
 *
 * 与
 *循环和递归_二叉树中和为某一值的路径
 * 类似
 *
 * 这里我们如何保证在有相同的字符的情况下，如何保证排列的结果是不重复不遗漏的呢？
 * 根据要排列的字符串的长度，新建一个等长的字符数组path来记录排列的路径；
 * 然后把字符串分解成字符数组arr，并对其排列，使得相同的字符都在一起;
 * 我们从头开始遍历这个字符数组arr，每次把一个字符放到路径path中的一个位置，用来保证不重不漏的规则是:
 * 1.如果当前从arr中取出的字符是唯一的，则在path中从头开始寻找一个空的位置放入该字符；
 * 2. 如果当前从arr中取出的字符与上一个从arr中取出的字符相同，则在path数组中，从上一个字符放入的位置往后开始，
 * 寻找一个空的位置放入
 *当所有字符被放入，则构成一个合法的排列；
 * 并且以上规则保证排列不会重复；
 *
 * */
public class 递归_分类_字符串的排列 {
    ArrayList<String> ans = new ArrayList<>();//存贮符合要求的排列
    char[] path;//存储每个遍历的路径

    public ArrayList<String> Permutation(String str) {
        path = new char[str.length()];//path的长度等于字符串的长度
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        dfs(arr, 0, 0, 0);
        Collections.sort(ans);//对答案按字典顺序排序
        return ans;
    }

    //u是当前需要放入path的字符在arr中的位置
    //start是上一个被放入path中字符在path中的位置
    //state表示path中位置被占用的情况，用它的二进制形式来表示，如5 的二进制是101则表示
    void dfs(char[] arr, int u, int start, int state) {
        if (u == arr.length) {
            //如果u达到了字符数组的末尾，说明找到了一条符合要求的路径，将其放入结果集中
            ans.add(new String(path));
            return;
        }
        if (u == 0 || arr[u] != arr[u - 1])//如果u==0（刚开始遍历），或者当前要放入的字符和上一个已经放入path的字符相等
            //则把start置为0，意思是当前字符可以放到任意位置（也就是从头寻找一个空位置放入）
            start = 0;
        for (int i = start; i < arr.length; i++) {//开始放入
            if((state>>i & 1)==0){//用state来记录path数组中被放入的情况，state >> i & 1用来检查state的第i位是否为1，如果为1表示path的第i位已经被占据
                path[i] = arr[u];//将arr中的第u个数放入path路径中
                dfs(arr,u+1,i+1,state+(1<<i)); //state + (1<<i)表示将state的第i位置1
            }
        }
    }
}
