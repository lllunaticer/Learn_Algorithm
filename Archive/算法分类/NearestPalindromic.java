import java.util.HashSet;

/*
* 564. Find the Closest Palindrome
Hard
Given an integer n, find the closest integer (not including itself), which is a palindrome.

The 'closest' is defined as absolute difference minimized between two integers.

Example 1:
Input: "123"
Output: "121"
Note:
The input n is a positive integer represented by string, whose length will not exceed 18.
If there is a tie, return the smaller one as answer.
* */
public class NearestPalindromic {

    public String nearestPalindromic(String n) {
        if(n.length()==1)
            return String.valueOf(Long.valueOf(n)-1);
        int len = n.length();
        long number = Long.parseLong(n);

        char[] tmpC1 = new char[len - 1];
        for (int i = 0; i < tmpC1.length; i++) {
            tmpC1[i] = '9';
        }
//        c1是下边界
        Long c1 = Long.valueOf(new String(tmpC1));
//        System.out.println(c1);

        char[] tmpC2 = new char[len + 1];
        for (int i = 0; i < tmpC2.length; i++) {
            tmpC2[i] = '0';
        }
        tmpC2[0] = '1';
        tmpC2[len] = '1';
//        c2是上边界
        Long c2 = Long.parseLong(new String(tmpC2));
//        System.out.println(c2);

//        中间的数+1，-1，+0反转到右边

//        +0反转复制
        String left = n.substring(0, (len & 1) == 0 ? len / 2 : len / 2 + 1);
        long numLeft = Long.valueOf(left) + 1;
        long c3 = String.valueOf(numLeft).length()==left.length()?construct(numLeft, len):c2;
        numLeft = Long.valueOf(left) - 1;
        long c4 = String.valueOf(numLeft).length()==left.length()?construct(numLeft, len):c1;

        numLeft = Long.valueOf(left);
        long c5 = this.construct(numLeft, len);

//        System.out.println(c1+" "+c2+" "+c3+" "+c4+" "+c5+" ");
        HashSet<Long> candidate = new HashSet<>();
        candidate.add(c1);
        candidate.add(c2);
        candidate.add(c3);
        candidate.add(c4);
        candidate.add(c5);
        candidate.remove(number);
        long flag = Long.MAX_VALUE;
        long small = Long.MAX_VALUE;
        for (Long i : candidate) {
            if (Math.abs(i - number) < flag || (Math.abs(i - number) == flag && i - number < 0)) {
                flag = Math.abs(i - number);
                small = i;
            }
        }
        return String.valueOf(small);
    }

    public long construct(Long num, int len) {
        String s = String.valueOf(num);
        char[] cr = new char[len];
        char[] cLeft = s.toCharArray();

        for (int i = 0; i < cLeft.length - 1; i++) {
            cr[len - 1 - i] = cr[i] = cLeft[i];
        }

        if ((len & 1) == 0)
            cr[len / 2 - 1] = cr[len / 2] = cLeft[cLeft.length - 1];
        else
            cr[len / 2] = cLeft[cLeft.length - 1];

        return Long.valueOf(new String(cr));
    }

    public static void main(String[] args) {
        NearestPalindromic n = new NearestPalindromic();
        System.out.println(n.nearestPalindromic("21474843"));

    }

}
/*
* public String nearestPalindromic(String n) {
    char[] arr = n.toCharArray();
    for (int i = 0, j = arr.length - 1; i < j; i++, j--) arr[j] = arr[i];

    String curP = String.valueOf(arr);
    String preP = nearestPalindrom(curP, false);
    String nextP = nearestPalindrom(curP, true);

    long num = Long.valueOf(n);
    long cur = Long.valueOf(curP);
    long pre = Long.valueOf(preP);
    long next = Long.valueOf(nextP);

    long d1 = Math.abs(num - pre);
    long d2 = Math.abs(num - cur);
    long d3 = Math.abs(num - next);

    if (num == cur) {
        return d1 <= d3 ? preP : nextP;
    } else if (num > cur) {
        return d2 <= d3 ? curP : nextP;
    } else {
        return d1 <= d2 ? preP : curP;
    }
}

private String nearestPalindrom(String curP, boolean dir) {
    int k = curP.length() >> 1, p = curP.length() - k;
    int l = Integer.valueOf(curP.substring(0, p));
    l += (dir ? 1 : -1);

    if (l == 0) return k == 0 ? "0" : "9";

    StringBuilder left = new StringBuilder(String.valueOf(l));
    StringBuilder right = new StringBuilder(left).reverse();
    if (k > left.length()) right.append("9");

    return left.append(right.substring(right.length() - k)).toString();
}
*
* */
