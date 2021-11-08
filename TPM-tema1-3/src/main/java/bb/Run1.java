package bb;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;

@AllArgsConstructor
public class Run1 implements Runnable {
    private FairLock orderLock;
    private Lock potLock;
    private Pot pot;
    private int pid;
    private int[] threadPortions;

    @Override
    public void run() {
        while (threadPortions[pid] > 0) {
            orderLock.lock(pid);
            try {
                potLock.lock();
                try {
                    if (pot.getCapacity() > 0) {
                        pot.setCapacity(pot.getCapacity() - 1);
                        threadPortions[pid]--;
//                        System.out.println(Arrays.toString(threadPortions));
                    }
                } finally {
                    potLock.unlock();
                }
            } finally {
                orderLock.unlock(pid);
            }
        }
    }
}
