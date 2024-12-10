import java.util.concurrent.atomic.AtomicInteger;

public class I_ThreadLocal {
    private static final AtomicInteger nextId = new AtomicInteger(0);       // creeate a unique id for each thread.
    private static final ThreadLocal<Integer> threadId = ThreadLocal.withInitial(() -> nextId.getAndIncrement());
    public static int get() {
        return threadId.get();
    }

}
