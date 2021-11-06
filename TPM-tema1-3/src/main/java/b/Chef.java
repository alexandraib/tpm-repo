package b;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;

@AllArgsConstructor
public class Chef implements Runnable {
    private int n;
    private int m;
    private PetersonLock lock;
    private Pot pot;
    private int numberPortionsPerThread;
    private int[] threadPortions;
    private int pid;

    @Override
    public void run() {
        int count = 0;
        while (m * numberPortionsPerThread / n + (m % n == 0 ? 0 : 1) - 1 > count) {
            lock.lock(pid);
            try {
                if (pot.getCapacity() == 0) {
                    System.out.println("Capacity = 0");
                    System.out.println(Arrays.toString(threadPortions));
                    pot.setCapacity(n);
                    count++;
                }
            } finally {
                lock.unlock(pid);
            }
        }
        System.out.println("Done chef");
    }
}
