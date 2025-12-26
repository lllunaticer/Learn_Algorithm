package ACwing打卡;

public class Solution74 {
    public static boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix[0] == null)
            return false;

        int row = matrix.length;
        int column = matrix[0].length;
        int l = 0;
        int r = row*column-1;
        while(l<r){
            int mid = (l+r)>>1;
            if(matrix[mid/row][mid%row]>=target)
                r = mid;
            else
                l = mid+1;
        }
        return matrix[l/column][l%row] == target;
    }

    public static void main(String[] args) {
        int[][] a = {{1,3}};
        searchMatrix(a, 2);
    }
}
