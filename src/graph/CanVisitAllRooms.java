/*
* 841. Keys and Rooms
Medium

360

29

Favorite

Share
There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1, and each room may have some keys to access the next room.

Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.  A key rooms[i][j] = v opens the room with number v.

Initially, all the rooms start locked (except for room 0).

You can walk back and forth between rooms freely.

Return true if and only if you can enter every room.

Example 1:

Input: [[1],[2],[3],[]]
Output: true
Explanation:
We start in room 0, and pick up key 1.
We then go to room 1, and pick up key 2.
We then go to room 2, and pick up key 3.
We then go to room 3.  Since we were able to go to every room, we return true.
Example 2:

Input: [[1,3],[3,0,1],[2],[0]]
Output: false
Explanation: We can't enter the room with number 2.
Note:

1 <= rooms.length <= 1000
0 <= rooms[i].length <= 1000
The number of keys in all rooms combined is at most 3000.
* */
package graph;

import java.util.*;

public class CanVisitAllRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int length = rooms.size();
        int roomNum = 0;
        Set<Integer> visited = new HashSet<>(rooms.get(roomNum));
        visited.add(0);
        Queue<Integer> key = new LinkedList<>(rooms.get(roomNum));
        key.add(0);
        while (!key.isEmpty()) {
            roomNum = key.poll();
            for (Integer a : rooms.get(roomNum)) {
                if(!visited.contains(a)){
                    key.add(a);
                    visited.add(a);
                }
            }
        }
        return length == visited.size();
    }
//测试
    public static void main(String[] args) {
        LinkedList<Integer> l1= new LinkedList<>();
        l1.add(1);
        l1.add(3);
        LinkedList<Integer> l2= new LinkedList<>();
        l2.add(3);
        l2.add(0);
        l2.add(1);
        LinkedList<Integer> l3= new LinkedList<>();
        l3.add(2);
        LinkedList<Integer> l4= new LinkedList<>();
        l4.add(0);
        List<List<Integer>> l = new LinkedList<>();
        l.add(l1);
        l.add(l2);
        l.add(l3);
        l.add(l4);
        CanVisitAllRooms c = new CanVisitAllRooms();
        c.canVisitAllRooms(l);
    }
}
