package a;

import lombok.AllArgsConstructor;

import java.util.concurrent.locks.Lock;

@AllArgsConstructor
public class Run1 implements Runnable {
    private Lock lock;
    private Pot pot;
    private int pid;

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (pot.getCapacity() > 0) {
                    System.out.println("Thread " + pid + " done.");
                    pot.setCapacity(pot.getCapacity() - 1);
                    break;
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
