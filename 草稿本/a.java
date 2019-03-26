public class a {
    static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix.length == 1)
            return matrix[0] == str[0];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(matrix, 0, i, j, str, rows, cols))
                    return true;
            }
        }
        return false;
    }

    static boolean dfs(char[] matrix, int u, int x, int y, char[] str, int rows, int cols) {
        if (u == str.length) return true;
        if (matrix[x * cols + y] != str[u]) return false;
        char tmp = matrix[x * cols + y];
        matrix[x * cols + y] = '*';
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        for (int i = 0; i < 4; i++) {
            int a = x + dx[i];
            int b = y + dy[i];
//
            if (a >= 0 && a < rows && b >= 0 && b < cols) {
                if (dfs(matrix, u + 1, a, b, str, rows, cols))
                    return true;
            }
        }
        matrix[x * cols + y] = tmp;
        return false;
    }

    public static void main(String[] args) {

        char[] a = "ABCE".toCharArray();
        char[] b = "BE".toCharArray();
        System.out.println(hasPath(a, 2, 2, b));
    }
}
