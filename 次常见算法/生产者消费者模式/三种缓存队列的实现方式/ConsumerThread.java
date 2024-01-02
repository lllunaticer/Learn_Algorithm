package 生产者消费者模式.三种缓存队列的实现方式;

//import 生产者消费者模式.LinkedHashMap_Synchronized实现.PublicQueue;

import 生产者消费者模式.三种缓存队列的实现方式.缓存队列.PublicQueue;

/**
 * 消费者线程
 */
public class ConsumerThread extends Thread {

    private PublicQueue publicQueue;

    public ConsumerThread(PublicQueue publicQueue) {
        this.publicQueue = publicQueue;
    }

    @Override
    public void run() {
        for (; ; ) {
            publicQueue.remove();
        }
    }
}

