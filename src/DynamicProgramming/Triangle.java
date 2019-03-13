/*
* 120. Triangle
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle

[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:

Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
* */
package DynamicProgramming;


import java.util.List;

public class Triangle {
//    以下暴力递归方法不能过OJ, 递归太深
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int total = 0;
        if (triangle.size()==0||triangle.get(0).size()==0)
            return 0;
        return maxNext(0, 0, n, total, triangle);
    }

    int maxNext(int i, int j, int n, int total, List<List<Integer>> triangle) {
        if(i+1 == n)
            return total+triangle.get(i).get(j);
        else
            return total + triangle.get(i).get(j) + Math.min(maxNext(i+1,j,n,total,triangle),maxNext(i+1,j+1,n, total,triangle));
    }
}
