package 刷题;

class Solution32 {
    public static int longestValidParentheses(String s) {
        char[] chars = s.toCharArray();
        int[] marks = new int[chars.length];
        for(int i=0; i<chars.length;i++)
            marks[i] = chars[i]=='('?1:-1;

        int pr = 0;
        int pl = 0;
        int distance = 0;
        int tmp = 0;
        while(pr<marks.length){
            tmp+=marks[pr];
            if(tmp<0){
                tmp = 0;
                pl = pr+1;
            }
            else if(tmp == 0)
                distance = pr-pl+1>distance?pr-pl+1:distance;
            pr++;
        }

        pr = marks.length-1;
        pl = pr;
        tmp = 0;
        while(pl>=0){
            tmp += marks[pl] ;
            if(tmp>0) {
                tmp = 0;
                pr = pl-1;
            }
            else if(tmp == 0)
                distance = pr-pl+1>distance?pr-pl+1:distance;
            pl--;
        }
        return distance;
    }

    public static void main(String[] args) {
        String s = "()(()";
        System.out.println(longestValidParentheses(s));
    }
}
