package bb;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairLock {
    private Queue<QueueData> queue = new PriorityBlockingQueue<>();
    private final Lock lock = new ReentrantLock();
    private final Lock queueSizeLock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    public synchronized void lock(int pid) {
        long startTime = System.nanoTime();

        queue.add(new QueueData(startTime, pid));
        while (true) {
            queueSizeLock.lock();
            try {
                if (queue.size() == 1)
                    break;
            } finally {
                queueSizeLock.unlock();
            }

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            QueueData front = queue.peek();

            assert front != null;
            if (front.getPid() == pid) {
                break;
            }
        }
    }

    public synchronized void unlock(int pid) {
        assert queue.peek() != null;
        if (queue.peek().getPid() == pid) {
            queue.remove();
            notifyAll();
        }
    }
}
