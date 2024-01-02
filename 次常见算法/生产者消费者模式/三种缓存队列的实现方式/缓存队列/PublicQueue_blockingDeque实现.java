package 生产者消费者模式.三种缓存队列的实现方式.缓存队列;

/**
 * 公共缓存队列
 * 只做两件事：（1）生产；（2）消费
 */

import java.util.concurrent.*;

public class PublicQueue_blockingDeque实现<T> implements PublicQueue<T> {

    private BlockingDeque<T> blockingDeque = new LinkedBlockingDeque<>(50);//缓冲区

    public void add(T msg) {
        try {
            blockingDeque.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("生产一个产品，当前商品角标为：" + "===文本为：" + msg);
    }

    public T remove() {
        T t = null;
        try {
            t = blockingDeque.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("消费一个产品，当前商品角标为：" + "===文本为：" + t);
        return t;
    }
}

