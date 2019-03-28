import java.util.Deque;
import java.util.LinkedList;

/*
*题目描述
地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，
每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐
标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能
够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方
格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
* */
public class 第十七题机器人的运动范围 {
    public int movingCount(int threshold, int rows, int cols) {
        int res = 0;
        if (rows == 0 || cols == 0)
            return 0;
        boolean[][] mark = new boolean[rows][cols];
        Deque<int[]> pair = new LinkedList<>();
        pair.push(new int[]{0, 0});

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        while (!pair.isEmpty()) {
            int[] t = pair.poll();
            if (get_sum(t) > threshold || mark[t[0]][t[1]])
                continue;
            res++;
            mark[t[0]][t[1]] = true;
            for (int i = 0; i < 4; i++) {
                int x = t[0] + dx[i];
                int y = t[1] + dy[i];
                if(x>=0 && x<rows && y>=0 && y<cols)
                    pair.push(new int[] {x,y});
            }
        }
        return res;
    }

    static int get_sum(int[] p) {
        return get_sum(p[0]) + get_sum(p[1]);
    }

    static int get_sum(int x) {
        int s = 0;
        while (x > 0) {
            s += x % 10;
            x /= 10;
        }
        return s;
    }
}
