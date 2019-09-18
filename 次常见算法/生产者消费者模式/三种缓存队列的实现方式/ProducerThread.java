package 生产者消费者模式.三种缓存队列的实现方式;

import 生产者消费者模式.三种缓存队列的实现方式.缓存队列.PublicQueue;

/**
 * 生产者线程
 */
public class ProducerThread extends Thread {

    private PublicQueue publicQueue;

    public ProducerThread(PublicQueue publicQueue){
        this.publicQueue = publicQueue;
    }

    @Override
    public void run() {
        for(int i=0;i<600;i++){
            publicQueue.add(String.valueOf(i));
        }
    }
}

