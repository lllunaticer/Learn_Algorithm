package 生产者消费者模式.三种缓存队列的实现方式.缓存队列;

public interface PublicQueue<T> {
    void add(T msg);
    T remove();
}
