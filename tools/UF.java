//并查集的简单实现
//使用数组储存元素
public class UF {
    //    寻找p的连通分量
    int find(int p, int[] id) {
        while (p != id[p]) {
            id[p] = id[id[p]];
            p = id[p];
        }
        return p;
    }

    boolean isConnected(int p, int q, int[] id) {
        return find(p, id) == find(q, id);
    }

    void union(int p, int q, int[] id){
        id[find(p, id)] = find(q, id);
    }
}
