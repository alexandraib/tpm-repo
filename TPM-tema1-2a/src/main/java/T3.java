import lombok.AllArgsConstructor;

@AllArgsConstructor
public class T3 implements Runnable {
    private LockBasedQueue queue;

    @Override
    public void run() {
        System.out.println(queue.deq());
        queue.enq(3);
        queue.enq(4);
    }
}
