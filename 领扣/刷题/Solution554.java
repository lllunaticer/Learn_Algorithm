package 刷题;

import java.util.*;

public class Solution554 {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 1;
        for (List list : wall) {
            int tmp = 0;
            for(int i = 0; i<list.size()-1; i++){
                tmp+=(Integer) list.get(i);
                if(!map.containsKey(tmp))
                    map.put(tmp,1);
                else{
                    int cnt = map.get(tmp)+1;
                    map.put(tmp, cnt);
                    max = cnt>max?cnt:max;
                }
            }
        }
        return wall.size() - max;
    }

    public static void main(String[] args) {
        Solution554 s = new Solution554();
        List<List<Integer>> a = new ArrayList<>();
        a.add(new ArrayList<>(Arrays.asList(1,2,2,1)));
        a.add(new ArrayList<>(Arrays.asList(3,1,2)));
        a.add(new ArrayList<>(Arrays.asList(1,3,2)));
        a.add(new ArrayList<>(Arrays.asList(2,4)));
        a.add(new ArrayList<>(Arrays.asList(3,1,2)));
        System.out.println(s.leastBricks(a));
    }
}
