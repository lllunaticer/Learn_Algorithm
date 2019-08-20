package 刷题;

//开放定址法
class MyHashMap2 {

    Entry[] keys;

    /**
     * Initialize your data structure here.
     */
    public MyHashMap2() {
        keys = new Entry[10];
    }

    public int find(int key){
        int hash = key%10;
        Entry p = keys[hash];
        while(p!=null){
            if(key == p.key)
                return hash;
            else
                p = keys[++hash%10];
        }
        return hash;
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        int p = find(key);
        if(keys[p] == null)
            keys[p] = new Entry(key,value);
        else{
            keys[p].key = key;
            keys[p].value = value;
        }

    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int p = find(key);
        return keys[p] == null? -1:keys[p].value;
    }


    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        int p = find(key);
        if(keys[p]!=null)
            keys[p].key = -1;
    }

    class Entry {
        int key, value;

        Entry(int k, int v) {
            key = k;
            value = v;
        }

    }
    public static void main(String[] args) {
        MyHashMap2 hashMap = new MyHashMap2();
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        hashMap.put(13, 13);
        hashMap.put(4, 4);
        hashMap.put(15, 15);
        hashMap.put(6, 6);
        hashMap.put(7, 7);
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
