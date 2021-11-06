package bb;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;

@AllArgsConstructor
public class Chef implements Runnable {
    private int n;
    private int m;
    private Lock lock;
    private Pot pot;
    private int numberPortionsPerThread;

    @Override
    public void run() {
        int count = 0;
        while (m * numberPortionsPerThread / n + (m % n == 0 ? 0 : 1) - 1 > count) {
            lock.lock();
            try {
                if (pot.getCapacity() == 0) {
                    System.out.println("Capacity = 0");
                    pot.setCapacity(n);
                    count++;
                }
            } finally {
                lock.unlock();
            }
        }
        System.out.println("Done chef");
    }
}
