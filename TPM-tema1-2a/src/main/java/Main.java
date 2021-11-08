public class Main {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 2; i++) {
            System.out.println("Executia numarul..." + i);
            LockBasedQueue queue = new LockBasedQueue();

            Thread t1 = new Thread(new T1(queue));
            Thread t2 = new Thread(new T2(queue));
            Thread t3 = new Thread(new T3(queue));

            t1.start();
            t2.start();
            t3.start();

            t1.join();
            t2.join();
            t3.join();
        }
    }
}
