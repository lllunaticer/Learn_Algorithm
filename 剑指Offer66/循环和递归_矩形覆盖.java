
/*
* 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
* 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
* */

/*
* 解题思路：
　　观察题目中的矩形，2*n的，是个长条形。本来脑中想象的是复杂的华容道，但是既然只是简单的长条形，
    那么依然逆向分析。既然是长条形的，那么从后向前，最后一个矩形2*2的，只有两种情况：

　　 第一种是最后是由一个2*(n-1)的矩形加上一个竖着的2*1的矩形
　　另一种是由一个2*(n-2)的矩形，加上两个横着的2*1的矩形
　　因此我们可以得出，
　　第2*n个矩形的覆盖方法等于第2*(n-1)加上第2*(n-2)的方法。使用代码可以表示为：
    for(i=3;i<71;i++){
        arr[i] = arr[i-1]+arr[i-2];
    }
　　仍然要注意数据类型，为long long型
    题解来自 https://www.cnblogs.com/xing901022/p/3753718.html
* */
public class 循环和递归_矩形覆盖 {
    public int RectCover(int target) {
        int[] res = new int[1000000];
        res[0] = 0;
        res[1] = 1;
        res[2] = 2;
        if(target<3)
            return res[target];
        else
            return cal(target, res);
    }

    public int cal(int n, int[] res){
        if(res[n]!=0)
            return res[n];
        else{
            int tmp = cal(n-1,res)+cal(n-2,res);
            res[n] = tmp;
            return res[n];
        }
    }
}
