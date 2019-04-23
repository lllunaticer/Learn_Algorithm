/*
* 题目描述
输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
使得所有的奇数位于数组的前半部分，所有的偶数位于数组的
后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
* */

import java.util.ArrayList;

/*
*
* */
public class 代码的完整性_整数组顺序使奇数位于偶数前面 {

    //雪菜写的双指针算法
    public void reOrderArray(int [] array) {
        int i = 0;
        int j = array.length-1;
        while(i<=j){
            while(array[i]%2==1) i++;
            while(array[j]%2==0) j--;
            if(i<j) swap(array,i,j);
        }
    }



    //自己写的双指针移动
    public static void reOrderArray1(int [] array) {
        int i = 0;
        int j ;
        while(i<array.length && array[i]%2==1 )
            i++;
        j = i+1;
        while(j<array.length){
            while( j<array.length && array[j]%2==0 )
                j++;
            if(j<array.length){
                swap(array, i, j);
                while(i<array.length && array[i]%2==1 )
                    i++;
                j = i+1;
            }
        }
    }



    //自己写的可以保证交换后顺序不变的写法
    public void reOrderArray2(int [] array) {
        int [] help1 = new int[array.length];
        int [] help2 = new int[array.length];
        int m = 0;
        int n = 0;
        for(int i = 0; i<array.length; i++){
            if(array[i]%2==1)
                help1[m++] = array[i];
            else
                help2[n++] = array[i];
        }
        int k = 0;
        for(int i = 0; i<m;i++)
            array[k++] = help1[i];
        for(int i = 0; i<n;i++)
            array[k++] = help2[i];
    }

    static void swap(int[] array, int i, int j){
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String[] args) {
        int[] array = {1,2,3,4,5,6,7};
        int[] array1 = {1,2,4,6,5,9,7};
//        reOrderArray(array);
        for(int i:array)
            System.out.print(i+ " ");
    }
}
