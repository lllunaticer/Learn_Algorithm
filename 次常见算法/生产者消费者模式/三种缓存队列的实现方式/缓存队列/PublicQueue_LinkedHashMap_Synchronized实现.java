package 生产者消费者模式.三种缓存队列的实现方式.缓存队列;

/**
 * 公共缓存队列
 * 只做两件事：（1）生产；（2）消费
 */
import java.util.*;
public class PublicQueue_LinkedHashMap_Synchronized实现<T> implements PublicQueue<T> {

    private int putIndex = 0;//数据插入的角标
    private int maxCount = 50;//缓存区最大长度

    private LinkedHashMap<Integer, T> linkedHashMap = new LinkedHashMap<>();//缓冲区

    public synchronized void add(T msg){
        if(linkedHashMap.size() == maxCount){
            //如果缓存区达到最大数量，则阻塞生产者线程
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            notifyAll();//唤醒所有线程
        }

        linkedHashMap.put(putIndex, msg);
        System.out.println("生产一个产品，当前商品角标为："+putIndex+"===文本为："+msg+"===缓存长度为："+linkedHashMap.size());
        putIndex = (putIndex + 1 >= maxCount) ? (putIndex + 1) % maxCount : putIndex + 1;
    }

    public synchronized T remove(){

        if(linkedHashMap.size() == 0){
            //如果缓存区没有数据，则阻塞消费线程
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            notifyAll();//唤醒所有线程
        }

        Iterator it = linkedHashMap.entrySet().iterator();
        T t = null;
        if(it.hasNext()){
            Map.Entry<Integer, T> entry = (Map.Entry<Integer, T>) it.next();
            t = entry.getValue();
            int index = entry.getKey();
            linkedHashMap.remove(index);
            System.out.println("消费一个产品，当前商品角标为："+index+"===文本为："+ t +"===缓存长度为："+linkedHashMap.size());
        }
        return t;
    }
}

