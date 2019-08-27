import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*滑动窗口算法*/
public class 腾讯提前批笔试第四题打气球 {
    //    雪菜法， 用数组记录颜色数目
    public static void main1(String[] args) {
        final int N = 1000010;
        final int M = 2010;

        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            int m = in.nextInt();

            int[] c = new int[N];
            int[] s = new int[M];

            for (int i = 1; i <= n; i++) {
                c[i] = in.nextInt();
            }
            int res = n + 1;

            int colors = 0;
            for (int i = 1, j = 0; i <= n; i++) {
                if (c[i] != 0 && s[c[i]] == 0)
                    colors++;
                s[c[i]]++;

                if (colors == m) {
                    while (c[j] == 0 || s[c[j]] > 1) {
                        s[c[j]]--;
                        j++;
                    }

                    res = Math.min(res, i - j + 1);
                }
            }
            System.out.println(res);
        }

    }

    //    改为map记录，但是会出问题，很多问题，有许多细节的地方需要考虑。包括除去0
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[] list = new int[n];
            for (int i = 0; i < n; i++) {
                list[i] = in.nextInt();
            }
//            需要一个map或者数组记录扫描区间内的各种颜色气球的种类
            Map<Integer, Integer> s = new HashMap<>();
            int res = n + 1;
            int colors = 0;
            s.put(list[0], 1);
            s.put(0, 0);
            if (list[0] != 0)
                colors++;
            for (int i = 0, j = 1; j < n; j++) {
                if (list[j] != 0 && !s.containsKey(list[j])) {
                    s.put(list[j], 1);
                    colors++;
                } else {
                    s.put(list[j], s.get(list[j]) + 1);
                }

                if (colors == m) {
                    if (list[i] == 0 || s.get(list[i]) > 1) {
                        s.put(list[i], s.get(list[i]) - 1);
                        i++;
                    }
                    res = Math.min(res, j - i + 1);
                }
            }
            System.out.println(res);
        }
    }

}
