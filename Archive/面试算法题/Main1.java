public class Main1 {
    static int max = Integer.MIN_VALUE;
    static int[][] arr;
    static int m;
    static int n;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static int search(int[][] _arr) {
        arr = _arr;
        m = arr.length;
        n = arr[0].length;
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                int tmp = dfs(0,i,j);
                max = max>tmp?max:tmp;
            }
        }
        return max;
    }

    public static int dfs(int cur, int i, int j){
        //缺少递归出口
        if(!check(i,j)) return cur;
        int res = cur;
        for(int k = 0; k<4;k++){
            int x = i+dx[k];
            int y = j+dy[k];
            if(x<0||x>=m||y<0||y>m||arr[x][y]<arr[i][j])
                continue;
            else{
                res = dfs(++cur, x, y);
            }
        }
        return res;
    }

    public static boolean check(int i, int j){
        boolean flag = true;
        for(int k = 0; k<4; k++){
            int x = i+dx[k];
            int y = j+dy[k];
            flag = flag && (x<0||x>=m||y<0||y>m||arr[x][y]<arr[i][j]);
        }
        return flag;
    }

    public static void main(String[] args){
        int[][] arr = {{1, 2, 3, 4, 5},{1, 1, 1, 1, 2},{1, 3, 2, 1, 4}};
        System.out.println(search(arr));
    }
}