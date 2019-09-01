package 搜索与遍历_BFS_DFS_树和图的遍历;
/*题目及评测链接:https://www.acwing.com/problem/content/description/844/*/
/*
* 给定一个整数n，将数字1~n排成一排，将会有很多种排列方法。
现在，请你按照字典序将所有的排列方法输出。
输入格式
共一行，包含一个整数n。
输出格式
按字典序输出所有排列方案，每个方案占一行。
数据范围
1≤n≤7
输入样例：
3
输出样例：
1 2 3
1 3 2
2 1 3
2 3 1
3 1 2
3 2 1
* */
/*
*
* */
import java.util.*;
public class 排列数字 {
    int N = 10;
    int n;
    int[] path = new int[N];
    boolean[] st = new boolean[N];

    void initialization(){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
    }

    void dfs(int u){
        //当u==n的时候，说明已经遍历到叶子节点，所有位置上的数都已经确定了，可以把保存在path中的路径输出出来了。
        if(u == n){
            for(int i = 0; i<n; i++)
                System.out.print(path[i]+" ");
            System.out.println();
            return;
        }

        //当u!=n时，说明位置u上的数字未被确定，我们需要从所有数字中找出当前层未被使用的数字i放到第u位上去，
        //并将这个数字i标记为已使用，这样这层往下的路径中都不能再使用这个数字i了。 并递归调用dfs(u+1)来继续往下走确定下一位置上的数字,
        //在被递归调用的下层的函数调用结束后（也就是dfs(u+1)的语句之后），这是说明u层以下的位置都已经被确定了，现在回到了本层，且本层
        //结束之后会回溯到上一层(也就是u-1那一层，是u-1使用dfs(u)才有了本层的执行)，往上层回溯的时候，本层使用过的数字i是可以被上一层
        //继续使用的，因此在退出本层之前需要将数字i的状态重新标记为未使用。

        //本层所有未使用的数字都可以放在这个位置，所以要遍历所有数字来枚举所有可能
        for(int i = 1; i <= n; i++){
            //从所有数字中寻找对本层来说未使用的数字放到路径中
            if(!st[i]){
                //找到了未被使用的数字i，加入路径中
                path[u] = i;
                //将i的使用状态标为true
                st[i] = true;
                //递归处理下一层，本层使用过的数字i不能在下一层不能再使用了，所以标记状态应该带到下一层去
                dfs(u+1);
                //经过上面的调用，u层以下的所有位置都已经被确定了。
                //在退出本层之前要恢复现场，将第i个数字的使用状态标为false。因为根据树搜索模型，本层使用的数字对本层以上的状态来说，是可以再次使用的。
                st[i] = false;
            }
            //总结，根据树的搜索模型(见Overview.md)，本层使用过的数字不能本层以下的状态再次使用，但本层使用过的数字本层以上的是可以再次使用的。
        }
    }

    public static void main(String[] args){
        排列数字 s = new 排列数字();
        s.initialization();
        s.dfs(0);
    }
}

