package b;

import lombok.AllArgsConstructor;

import java.util.concurrent.locks.Lock;

@AllArgsConstructor
public class Run1 implements Runnable {
    private PetersonLock lock;
    private Pot pot;
    private int pid;
    private int[] threadPortions;

    @Override
    public void run() {
        int count = 0;
        while (threadPortions[pid] > 0) {
            lock.lock(pid);
            try {
                if (pot.getCapacity() > 0) {
                    System.out.println("Thread " + pid);
                    pot.setCapacity(pot.getCapacity() - 1);
                    threadPortions[pid]--;
                }
            } finally {
                lock.unlock(pid);
            }
        }
    }
}
