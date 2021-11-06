package a;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int n = 5, m = 16;

        Pot pot = new Pot(n);
        Lock lock = new ReentrantLock();

        List<Thread> threadList = new LinkedList<>();

        Chef chef = new Chef(n, m, lock, pot);
        Thread threadChef = new Thread(chef);

        threadChef.start();

        for (int i = 0; i < m; i++) {
            threadList.add(new Thread(new Run1(lock, pot,i)));
            threadList.get(i).start();
        }

        threadChef.join();
        for (int i = 0; i < m; i++) {
            threadList.get(i).join();
        }
    }
}
