package 刷题;
//拉链法
class MyHashMap {
    Entry[] keys;

    /**
     * Initialize your data structure here.
     */
    public MyHashMap() {
        keys = new Entry[1000];
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        int hash = key % 1000;
        Entry p = keys[hash];
        if (p == null)
            keys[hash] = new Entry(key, value);
        else {
            while (p != null) {
                if (key == p.getKey()) {
                    p.setValue(value);
                    break;
                } else {
                    if (p.next == null) {
                        p.next = new Entry(key, value);
                        break;
                    } else
                        p = p.next;
                }
            }
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int hash = key % 1000;
        Entry p = keys[hash];
        while (p != null) {
            if (key == p.getKey())
                return p.getValue();
            else
                p = p.next;
        }
        return -1;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        int hash = key % 1000;
        Entry pl = keys[hash];
        Entry fakeNode = new Entry(-1, -1);
        fakeNode.next = pl;
        Entry pr = fakeNode;
        while (pl != null) {
            if (pl.getKey() == key) {
                pr.next = pl.next;
                pl.next = null;
                break;
            } else {
                pr = pr.next;
                pl = pl.next;
            }
        }
        keys[hash] = fakeNode.next;
        fakeNode.next = null;
    }

    public static void main(String[] args) {
        MyHashMap hashMap = new MyHashMap();
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        hashMap.get(1);
        hashMap.get(3);
        hashMap.put(2, 1);
        hashMap.get(2);
        hashMap.remove(2);
        hashMap.get(2);
    }
}

class Entry {
    int key;
    int value;
    Entry next;

    Entry(int k, int v) {
        key = k;
        value = v;
    }

    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Entry getNext() {
        return next;
    }

}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */

//开放定址法
