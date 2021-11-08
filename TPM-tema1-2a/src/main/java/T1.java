import lombok.AllArgsConstructor;

@AllArgsConstructor
public class T1 implements Runnable{
    private LockBasedQueue queue;

    @Override
    public void run() {
        System.out.println(queue.deq());
        System.out.println(queue.deq());
        queue.enq(1);
    }
}
