package b;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;

public class PetersonLock {
    private final int n;
    private final AtomicInteger[] level;
    private final AtomicInteger[] victim;

    public PetersonLock(int n) {
        this.n = n;
        level = new AtomicInteger[n];
        victim = new AtomicInteger[n];

        for (int i = 0; i < n; i++) {
            level[i] = new AtomicInteger(-1);
            victim[i] = new AtomicInteger(-1);
        }
    }

    public void lock(int i) {
        for (int L = 1; L < n; L++) {
            level[i].set(L);
            victim[L].set(i);

            while (condition(i, L) && victim[L].get() == i) {
            }
        }

        while (condition2(i)) {
        }
    }

    private boolean condition2(int i) {
        for (int k = 0; k < n; k++)
            if (k != i && level[k].get() != 0 && level[k].get() != -1 && victim[level[k].get()].get() != k)
                return true;
        return false;
    }

    private boolean condition(int i, int currentLvl) {
        for (int k = 0; k < n; k++)
            if (k != i && level[k].get() >= currentLvl)
                return true;
        return false;
    }

    public void unlock(int i) {
        level[i].set(0);
    }
}
