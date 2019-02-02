import java.util.LinkedList;

public class Solution13 {
    public Character[] mark1 = {'I','V','X'};
    public Character[] mark10 = {'X','L','C'};
    public Character[] mark100 = {'C','D','M'};
    public Character[] mark1000 = {'M',' ',' '};


    public int romanToInt(String s) {
        LinkedList<Character> sc = new LinkedList<>();
        for(int i = 0;i<s.length();i++)
            sc.add(s.charAt(i));
        int value = 0;
        box result = new box();



            result = parse(sc,mark1);
            value = value+result.value;
            sc = result.sc;

            result = parse(sc,mark10);
            value = value+result.value*10;
            sc = result.sc;

            result = parse(sc,mark100);
            value = value+result.value*100;
            sc = result.sc;

            result = parse(sc,mark1000);
            value = value+result.value*1000;
            sc = result.sc;

        return value;
    }

    private box parse(LinkedList s, Character[] mark){
        int v = 0;
        if(s.peekLast()==mark[0]){
            while(s.peekLast()==mark[0]){
                v=v+1;
                s.removeLast();
            }
            if(s.peekLast()==mark[1]){
                v = v+5;
                s.removeLast();
            }

        }
        else if(s.peekLast()==mark[1]){
            v = v+5;
            s.removeLast();
            if(s.peekLast()==mark[0]){
                v = v-1;
                s.removeLast();
            }
        }
        else if(s.peekLast()==mark[2]){
            s.removeLast();
            if(s.peekLast()==mark[0]){
                v = v+9;
                s.removeLast();
            }else{
                s.addLast(mark[2]);
            }
        }

        return new box(v,s);
    }

    public static void main(String[] args) {
        Solution13 a = new Solution13();
        System.out.println(a.romanToInt("MCMXCIV"));
    }

    class box{
        int value;
        LinkedList<Character> sc;

        public box(){

        }

        public box(int t, LinkedList c){
            this.value = t;
            this.sc = c;
        }
    }
}
