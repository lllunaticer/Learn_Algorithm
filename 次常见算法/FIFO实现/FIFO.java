package FIFO实现;/*FIFO就是先进先出，可以使用LinkedHashMap进行实现。
 当第三个参数传入为false或者是默认的时候，就可以实现按插入顺序排序，就可以实现FIFO缓存了。*/

/**
 * Constructs an empty <tt>LinkedHashMap</tt> instance with the
 * specified initial capacity, load factor and ordering mode.
 *
 * param initialCapacity the initial capacity
 * param loadFactor      the load factor
 * param accessOrder     the ordering mode - <tt>true</tt> for
 * access-order, <tt>false</tt> for insertion-order
 * throws IllegalArgumentException if the initial capacity is negative
 * or the load factor is nonpositive
 */
/*public LinkedHashMap(int initialCapacity,
        float loadFactor,
        boolean accessOrder){
        super(initialCapacity,loadFactor);
        this.accessOrder=accessOrder;
        }*/

/*        实现代码跟上述使用LinkedHashMap实现LRU的代码基本一致，主要就是构造函数的传值有些不同。*/

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class FIFO<K, V> {
    private final int MAX_CACHE_SIZE;
    private final float DEFAULT_LOAD_FACTORY = 0.75f;

    LinkedHashMap<K, V> map;

    public FIFO(int cacheSize) {
        MAX_CACHE_SIZE = cacheSize;
        int capacity = (int)Math.ceil(MAX_CACHE_SIZE / DEFAULT_LOAD_FACTORY) + 1;
        /*
         * 第三个参数设置为true，代表linkedlist按访问顺序排序，可作为LRU缓存
         * 第三个参数设置为false，代表按插入顺序排序，可作为FIFO缓存
         */
        map = new LinkedHashMap<K, V>(capacity, DEFAULT_LOAD_FACTORY, false) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > MAX_CACHE_SIZE;
            }
        };
    }

    public synchronized void put(K key, V value) {
        map.put(key, value);
    }

    public synchronized V get(K key) {
        return map.get(key);
    }

    public synchronized void remove(K key) {
        map.remove(key);
    }

    public synchronized Set<Map.Entry<K, V>> getAll() {
        return map.entrySet();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            stringBuilder.append(String.format("%s: %s  ", entry.getKey(), entry.getValue()));
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        FIFO<Integer, Integer> lru1 = new FIFO<>(5);
        lru1.put(1, 1);
        lru1.put(2, 2);
        lru1.put(3, 3);
        System.out.println(lru1);
        lru1.get(1);
        System.out.println(lru1);
        lru1.put(4, 4);
        lru1.put(5, 5);
        lru1.put(6, 6);
        System.out.println(lru1);
    }
}
