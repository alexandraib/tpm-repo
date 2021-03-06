package b;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main_b {
    public static void main(String[] args) throws InterruptedException {
        int n = 4, m = 8;
        int numberOfPortions = 10;
        int[] threadPortions = new int[m];

        for (int i = 0; i < m; i++)
            threadPortions[i] = numberOfPortions;

        Pot pot = new Pot(n);
        PetersonLock lock = new PetersonLock(m + 1);

        List<Thread> threadList = new LinkedList<>();

        Chef chef = new Chef(n, m, lock, pot, numberOfPortions, threadPortions, m);
        Thread threadChef = new Thread(chef);

        threadChef.start();

        for (int i = 0; i < m; i++) {
            threadList.add(new Thread(new Run1(lock, pot, i, threadPortions)));
            threadList.get(i).start();
        }

        threadChef.join();
        for (int i = 0; i < m; i++) {
            threadList.get(i).join();
        }
    }
}
