package 生产者消费者模式.链表_Synchronized朴素实现;

public class Producer extends Thread{
    private int num;//生产的数量
    public Storage storage;//仓库

    public Producer(Storage storage) {
        this.storage = storage;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void run() {
        storage.produce(num);
    }
}
