import java.util.Arrays;
//https://blog.csdn.net/jianyuerensheng/article/details/51258374
public class QuickSort {
    public static void quick_sort(int s[], int low, int high){
        int i, j , index;
        if(low>high)
            return;
        i = low;
        j = high;
        index = s[i];//用子表的第一个记录做基准
        while( i<j ){//从表的两端向中间扫描
            while( i<j && s[j]>=index)
                j--;
            if(i<j)
                s[i++] = s[j];//用比基准小的记录替换低位记录,注意是i++,先赋值再自增
            while( i<j && s[i]<index)
                i++;
            if( i<j )//用比基准大的记录替换高位记录
                s[j--] = s[i];
        }
        s[i] = index;//将基准数值替换回s[i]
        quick_sort(s, low, i-1);//对低子表进行递归排序
        quick_sort(s,i+1,high);//对高子表进行递归排序
    }

    public static void main(String[] args) {
        int a[] = {49,38,65,97,76,13,27,49};
        quick_sort(a, 0,a.length-1);
        System.out.println(Arrays.toString(a));
    }
}
