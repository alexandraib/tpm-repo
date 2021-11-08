import java.util.concurrent.locks.ReentrantLock;

public class LockBasedQueue {
    public static final int QSIZE = 100;

    int head = 0, tail = 0;
    int items[] = new int[QSIZE];
    ReentrantLock lock = new ReentrantLock();

    public void enq(int x) {
        while (tail - head == QSIZE) {
        }
        ;
        lock.lock();
        try {
            items[tail % QSIZE] = x;
            tail++;
        } finally {
            lock.unlock();
        }
    }

    public int deq() {
        while (tail == head) {
        }
        ;
        lock.lock();
        try {
            int item = items[head % QSIZE];
            head++;
            return item;
        } finally {
            lock.unlock();
        }
    }
}