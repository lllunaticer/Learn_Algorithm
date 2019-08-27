/*题目描述
在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一
个整数，判断数组中是否含有该整数*/

/*首先选取数组中右上角的数字。如果该数字等于要查找的数字，则查找过程结束；
如果该数字大于要查找的数字，则剔除这个数字所在的列；如果该数字小于要查找的数字，
则剔除这个数字所在的行*/
public class 数组_二维数组中的查找 {
    public static boolean Find(int target, int[][] array) {
        if (array == null || array.length==0 ||(array.length==1&&array[0].length==0)||array[0][0] > target)
            return false;
        else {
            for (int i = 0; i < array.length; i++) {
                if (array[i][0] > target || array[i][array.length - 1] < target)
                    continue;
                else if (二分查找_判断有无给定元素.binarySearch2(target, array[i]))
                    return true;
            }
            return false;
        }
    }


    public static void main(String[] args) {
//        int[][] a = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
//        int[][] a = {{}};
//        System.out.println(Find(1, a));
        int[] a = {1, 2, 8, 9};
        System.out.println(二分查找_判断有无给定元素.binarySearch(1, a, 0, a.length-1));
    }
}
