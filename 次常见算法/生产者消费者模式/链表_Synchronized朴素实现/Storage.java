package 生产者消费者模式.链表_Synchronized朴素实现;

import java.util.*;

public class Storage {
    private final int MAX_SIZE = 100;//仓库最大容量
    private LinkedList list = new LinkedList();//产品存储在这里

    public void produce(int num) {//生产num个产品
        synchronized (list) {
            //一定是while，因为wait被唤醒后需要判断是不是满足生产条件
            while (list.size() + num > MAX_SIZE) {
                System.out.println("暂时不能执行生产任务");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //满足生产条件开始生产
            for (int i = 0; i < num; i++) {
                list.add(new Object());
            }
            System.out.println("已生产产品数" + num + " 仓库容量" + list.size());
            list.notifyAll();
        }
    }

    public void consume(int num) {//消费num个产品
        synchronized (list) {
            while (list.size() < num) {
                System.out.println("暂时不能执行消费任务");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //满足消费条件开始消费
            for (int i = 0; i < num; i++) {
                list.remove();
            }
            System.out.println("已消费产品数" + num + " 仓库容量" + list.size());
            list.notifyAll();
        }
    }
}
