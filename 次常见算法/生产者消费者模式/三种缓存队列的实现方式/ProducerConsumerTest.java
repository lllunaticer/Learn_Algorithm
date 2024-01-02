package 生产者消费者模式.三种缓存队列的实现方式;

import 生产者消费者模式.三种缓存队列的实现方式.缓存队列.PublicQueue;
import 生产者消费者模式.三种缓存队列的实现方式.缓存队列.PublicQueue_LinkedHashMap_Lock实现;

public class ProducerConsumerTest {

    public static void main(String[] args) {
        PublicQueue publicQueue = new PublicQueue_LinkedHashMap_Lock实现();
//        可替换公共缓存队列的实现方式
//        PublicQueue publicQueue = new PublicQueue_LinkedHashMap_Synchronized实现();
//        PublicQueue publicQueue = new PublicQueue_blockingDeque实现();
        ProducerThread producerThread = new ProducerThread(publicQueue);
        ConsumerThread consumerThread = new ConsumerThread(publicQueue);
        producerThread.start();//启动生产者线程
        consumerThread.start();//启动消费者线程
    }
}

