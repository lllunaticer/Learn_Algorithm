import java.util.*;

public class wordsLadder {

    public static void main(String[] args) {
        String begainWord = "hit";
        String endWord = "cog";
        String[] s = {"hot", "dot", "dog", "lot", "log", "cog"};
        List<String> wordList = new ArrayList<>(Arrays.asList(s));
        wordsLadder w = new wordsLadder();
        System.out.println(w.findLadders(begainWord, endWord, wordList));
    }

    //入口函数
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        wordList.add(beginWord);
        HashMap<String, ArrayList<String>> nexts = new HashMap<>(getNexts(wordList));
        HashMap<String, Integer> distances = new HashMap<>(getDistances(beginWord, nexts));
        LinkedList<String> solution = new LinkedList<>();
        List<List<String>> result = new ArrayList<>();
        getShortestPath(beginWord, endWord, nexts, distances, solution, result);
        return result;
    }

    //    生成所有单词的邻居表
    private HashMap<String, ArrayList<String>> getNexts(List<String> words) {
        HashSet<String> dict = new HashSet<>(words);
        HashMap<String, ArrayList<String>> nexts = new HashMap<>();
        for (String s : words) {
            nexts.put(s, getNext(s, dict));
        }
        return nexts;
    }

    //    获取一个单词在词典dict中的所有邻居
    private ArrayList<String> getNext(String s, Set<String> dict) {
        char[] word = s.toCharArray();
        ArrayList<String> l = new ArrayList<>();
        for (int i = 0; i < word.length; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (word[i] != c) {
                    char tmp = word[i];
                    word[i] = c;
                    if (dict.contains(String.valueOf(word)))
                        l.add(String.valueOf(word));
                    word[i] = tmp;
                }
            }
        }
        return l;
    }

    //    生成所有单词距离beginWord的距离表
    private HashMap<String, Integer> getDistances(String beginWord, HashMap<String, ArrayList<String>> nexts) {
        Queue<String> queue = new LinkedList<>();
        HashMap<String, Integer> distanceMap = new HashMap<>();
        queue.add(beginWord);
        distanceMap.put(beginWord, 0);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        while (!queue.isEmpty()) {
            String s = queue.poll();
            for (String tmp : nexts.get(s)) {
                if (!visited.contains(tmp)) {
                    visited.add(tmp);
                    queue.add(tmp);
                    distanceMap.put(tmp, distanceMap.get(s) + 1);
                }
            }
        }
        return distanceMap;
    }

    //    深度优先遍历搜索最短路径
    private void getShortestPath(String cur, String end,
                                 HashMap<String, ArrayList<String>> nexts, HashMap<String, Integer> distances,
                                 LinkedList<String> solution, List<List<String>> result) {
        solution.add(cur);
        if (end.equals(cur))
            result.add(new ArrayList<>(solution));
        else {
            for (String next : nexts.get(cur)) {
                if (distances.get(next) == distances.get(cur) + 1)
                    getShortestPath(next, end, nexts, distances, solution, result);
            }
        }
        solution.pollLast();
    }
}
