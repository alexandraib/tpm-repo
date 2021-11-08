import lombok.AllArgsConstructor;

@AllArgsConstructor
public class T2 implements Runnable {
    private LockBasedQueue queue;

    @Override
    public void run() {
        queue.enq(2);
    }
}
