import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class I_Callable {
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            public void run() {
                System.out.println("Runnable Thread");      // This is an task.
            }
        };

        // 2.
            Callable<Integer> c = new Callable<Integer>() {
            public Integer call() {
                System.out.println("Callable Thread");      // This is an task.
                return 1;
            }
        };

            // if we do like this new Thread(c).start(); then it will give the error becoz Thread is not taking the Callable. it only take the Runnable.  so we used the ExecutorService.


            // create the ExecutorService
        ExecutorService es = Executors.newFixedThreadPool(1);
             // pass the Callable to the ExecutorService
            // if we do like this es.execute(c); then it will give the error becoz execute method is not taking the Callable. it only take the Runnable. becoz execute is similar to do a new thread.
        es.submit(c);       // here we can pass the Runnable also.
            // we get the Future object.
        Future<Integer> f = es.submit(c);

        try {
            Integer i = f.get();
            System.out.println(i);        // we can access the Future object.
        } catch (Exception e) {                   // we need to handle the exception becoz get() method throws the exception.
            e.printStackTrace();
        }



    }
}

/**
*      1.   we have create the Runnable.
 *              we need to do either we need to pass the Runnable r to the Thread or we need to pass the Runnable r to the ExecutorService.
 *              let say if we have one way task then we can use the Runnable.
 *
 *              let say if we want to calculate something in a separate thread and if we want the result back to this thread then Runnable is not the best choice. becoz it is void type.
*              so we need to use the Callable.
 *
 *
*      2.   we have create the Callable.
 *              Callable is of Generic type. it will return the value.
 *              it is something that can go and run on separate thread and than return the value back to the thread.
 *
 *
*      both Runnable and Callable we are running on diff threads like  either we can pass it to the Thread or we can pass it to the ExecutorService.
 *          but the main difference is that Callable can return the value back to the thread.
 *
 *          let say we create the Executor service and we pass the Callable to the ExecutorService and we get the Future object.
 *                  Future object is the object that will hold the result of the Callable.
 *
 */