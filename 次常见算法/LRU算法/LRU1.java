package LRU算法;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


/*这是LinkedHashMap的一个构造函数，传入的第三个参数accessOrder为true的时候，就按访问顺序对
LinkedHashMap排序，为false的时候就按插入顺序，默认是为false的。
当把accessOrder设置为true后，就可以将最近访问的元素置于最前面，
这样就可以满足上述的第二点。*/

/**
 * Constructs an empty <tt>LinkedHashMap</tt> instance with the
 * specified initial capacity, load factor and ordering mode.
 *
 * param  initialCapacity the initial capacity
 * param  loadFactor      the load factor
 * param  accessOrder     the ordering mode - <tt>true</tt> for
 *         access-order, <tt>false</tt> for insertion-order
 * throws IllegalArgumentException if the initial capacity is negative
 *         or the load factor is nonpositive
 */
/*public LinkedHashMap(int initialCapacity,
        float loadFactor,
        boolean accessOrder) {
        super(initialCapacity, loadFactor);
        this.accessOrder = accessOrder;
        }*/

/* 这是LinkedHashMap中另外一个方法，当返回true的时候，就会remove其中最久的元素，
   可以通过重写这个方法来控制缓存元素的删除，当缓存满了后，就可以通过返回true删除最久
   未被使用的元素，达到LRU的要求。这样就可以满足上述第三点要求。
*/

/*protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return false;
        }*/

/* 由于LinkedHashMap是为自动扩容的，当table数组中元素大于Capacity * loadFactor的时候，
   就会自动进行两倍扩容。但是为了使缓存大小固定，就需要在初始化的时候传入容量大小和负载因子。
  了使得到达设置缓存大小不会进行自动扩容，需要将初始化的大小进行计算再传入，可以将
   初始化大小设置为(缓存大小 / loadFactor) + 1，这样就可以在元素数目达到缓存大小时，
   也不会进行扩容了。这样就解决了上述第一点问题。
*/
public class LRU1<K, V> {
    private final int MAX_CACHE_SIZE;
    private final float DEFAULT_LOAD_FACTORY = 0.75f;

    LinkedHashMap<K, V> map;

    public LRU1(int cacheSize) {
        MAX_CACHE_SIZE = cacheSize;
        int capacity = (int)Math.ceil(MAX_CACHE_SIZE / DEFAULT_LOAD_FACTORY) + 1;
        /*
         * 第三个参数设置为true，代表linkedlist按访问顺序排序，可作为LRU缓存
         * 第三个参数设置为false，代表按插入顺序排序，可作为FIFO缓存
         */
        map = new LinkedHashMap<K, V>(capacity, DEFAULT_LOAD_FACTORY, true) {
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
        LRU1<Integer, Integer> lru1 = new LRU1<>(5);
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