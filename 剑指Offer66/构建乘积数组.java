/*题目：给定一个数组A[0，1…，n-1]，请构建一个数组B[0，1…，n-1]，其中B中的元素
B[i]=A[0]×A[1]…×A[i-1]×A[i+1]…×A[n-1]。不能使用除法。*/

/*好在还有更高效的算法。可以把B[i]=A[0]×A[1]…×A[i-1]×A[i+1]…×A[n-1]看成
A[0]×A[1]x…×A[i-1]和A[i+1]…×A[n-2]×A[n-1]两部分的乘积。因此，数组B可以用一个矩阵来创建。*/

/*不妨定义C[i]=A[0]×A[1]…×A[i-1]，D[i]=A[i+1]…×A[n-2]×A[n-1]。
C[i]可以用自上而下的顺序计算出来，即C[i]=C[i-1]×A[i-1]。类似的，D[i]
也可以用自下而上的顺序计算出来，即D[i]=D[i+1]×A[i+1]。*/
public class 构建乘积数组 {
    public int[] multiply(int[] A) {
        int[] B = new int[A.length];
        if (A.length == 0)
            return B;
        int[] C = new int[A.length];
        C[0] = 1;
        int[] D = new int[A.length];
        D[D.length - 1] = 1;

        for (int i = 1; i < A.length; i++) {
            C[i] = C[i - 1] * A[i - 1];
        }
        for (int i = A.length - 2; i >= 0; i--) {
            D[i] = D[i + 1] * A[i + 1];
        }
        for (int i = 0; i < B.length; i++)
            B[i] = C[i] * D[i];
        return B;
    }
}
