package 最短路径算法模板;

/*
*算法步骤:
* d[i,j]
* for(k = 1; k<=n; k++)
*   for(i = 1; i<=n; i++)
*       for(j = 1; j<=n; k++)
*           d[i,j] = Math.min(d[i,j], d[i,k] + d[k,j])*/

/*Floyd算法使用邻接矩阵保存图
* 使用三重循环，Floyd算法能够把邻接矩阵变成最短距离的矩阵*/
public class Floyd算法 {
}
