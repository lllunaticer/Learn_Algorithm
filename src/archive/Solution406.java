package archive;

import java.util.Arrays;

/*Suppose you have a random list of people standing in a queue.
Each person is described by a pair of integers (h, k), where h
is the height of the person and k is the number of people in front
of this person who have a height greater than or equal to h. Write
an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.


Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]*/
//hints:
/*What can you say about the position of the shortest person?
If the position of the shortest person is i, how many people would be in front of the shortest person?
Once you fix the position of the shortest person, what can you say about the position of the second shortest person?*/
public class Solution406 {
//    public int[][] reconstructQueue(int[][] people) {
//
//    }

    //对二维数组按第一个元素作快排
    public static void quick_sort(int[][] s, int low, int high){
        if ( low>high )
            return;
        int i = low;
        int j = high;
        int[] index = {s[low][0],s[low][1]};

        while( i<j ){
            while( i<j && s[j][0]>=index[0])
                j--;
            if( i<j ) {
                s[i][1] = s[j][1];
                s[i++][0] = s[j][0];
            }
            while(i<j && s[i][0]<=index[0])
                i++;
            if( i<j ){
                s[j][1] = s[i][1];
                s[j--][0] = s[i][0];
            }
        }
        s[i][0] = index[0];
        s[i][1] = index[1];
        quick_sort(s, low, i-1);
        quick_sort(s, i+1,high);
    }

    public static int[][] reconstructQueue(int[][] people) {
        int queueLength = people.length;
        quick_sort(people, 0, queueLength - 1);
        int[][] restruQueue = new int[queueLength][2];
        for(int j = 0;j<queueLength;j++){
            restruQueue[j][0] = -1;
            restruQueue[j][1] = -1;
        }

        for (int i = 0; i < queueLength; i++) {
            int count = 0;
            int position = 0;
            while (count <= people[i][1]) {
                if (count == people[i][1]) {
                    if (restruQueue[position][0] == -1) {
                        restruQueue[position][0] = people[i][0];
                        restruQueue[position][1] = people[i][1];
                        break;
                    } else if (restruQueue[position][0] < people[i][0]) {
                        position++;
                    }
                }
                if (count < people[i][1]) {
                    if (restruQueue[position][0] == -1 || restruQueue[position][0] >= people[i][0]) {
                        count++;
                    }
                    position++;
                }
            }
        }
        return restruQueue;
    }

        public static void main(String[] args){
//            int a[][] = {{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
            int a[][] = {{8,2},{4,2},{4,5},{2,0},{7,2},{1,4},{9,1},{3,1},{9,0},{1,0}};
            int[][] p = reconstructQueue(a);

            for (int i = 0; i < a.length; i++)
                System.out.println(p[i][0] + "," + p[i][1]);
        }

}