public class duilie {

    int volume = 5;
    int i = 4;
    int j = 4;
    int[] Q = new int[volume];
    int count = 0;

    public void put(int a) {
        if (count == volume) {
            System.out.println("The Queue is Full!");
        } else {
            count++;
            if (i == 0) {
                Q[i] = a;
                i = volume - 1;
            } else {
                Q[i--] = a;
            }
        }

    }

    public int take() {
        int res;
        if (count == 0) {
            System.out.println("Empty Queue!");
            res = -100;
        } else {
            count--;
            res = Q[j];
            if (j == 0) {
                j = volume - 1;
            } else {
                j--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        duilie q = new duilie();
        //System.out.println(q.take());//空
        q.put(2);
        System.out.println(q.take());//3
        q.put(3);
        System.out.println(q.take());//3
        q.put(1);
        q.put(2);
        q.put(3);
        q.put(4);
        q.put(5);
        System.out.println(q.take());
        System.out.println(q.take());
        System.out.println(q.take());
        System.out.println(q.take());
        System.out.println(q.take());

    }

}
