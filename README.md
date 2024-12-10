## Executor Service
``` 
Executors class:-
        Thread pool is one type of executor.
                where we have fixed size thread pool.
                it can be expensive to construct for small tasks.
                surge of traffic can make everybody slow.
                better approach is limit the number of threads in the starting.         img1.
                   In java we have ExecutorService and Executors.       img2.   

ExecutorService:-
                JB
        It allows us to submit tasks.
            submit() method returns a Future object.
                Can then return values or throw exceptions.
            Tasks can be canceled through interruption.
        
        Lifecycle Management for threads in a pool
            we can shut it down with shutdown() or shutdownNow().
                shutdown() will allow the threads to finish their current task.
                shutdownNow() will interrupt the threads.
            we can wait for it to complete shut down with awaitTermination().
            we can override beforeExecute(), terminated() and afterExecute() methods
            
            img5,  img6,    img7.
            
            JS
            
        Callable<V> more useful than Runnable.
            Callable<V> returns a value.
            Callable<V> can throw a checked exception.
            Callable<V> is a generic type.
            
            public interface Callable<V> {
                V call() throws Exception;
            }
        
        submit(Callable<V> task) returns a Future<V> object.
            public interface Future<V> {
                boolean cancel(boolean mayInterruptIfRunning);
                boolean isCancelled();
                boolean isDone();
                V get() throws InterruptedException, ExecutionException;
                V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException;
            }
            
            Img8 to img13.
            
            

        it is High level api for executing tasks.
            like creating new thread is resource intensive. means we have to do new Thread( assign runnable object).
            instead we just have to reuse the thread instance.  this we can do using thread pool.
            
            ThreadPool:-
                it has fixed number of threads that created in the beginning.
                    so when the task come in that need to be done than  a new thread instace from the pool pick the task and do it.[images](images)
                     and let say another task come in than another thread from the pool pick the task and do it.
                     so when all the threads of the threadpool are busy then other task have to wait so it will be in the queue.
                     and when thread has done with the task then it go to the threadpool and the thread will pick the task from the queue and do it.   
                
                becoz of that we don't have to keep creating new thread instance.
                also we can limit the number of threads in the pool.
                
                we can create a ArrayList or anything and keep the thread instance in that and keep reusing it.
                    but it is not good approach becoz we have to manage the thread instance. like we have to check if the thread is busy or not, also we have to check if the thread is alive or not. etc. 
                so we use ExecutorService, it allows us to manage threadpool. 
                
                what ExecutorService does ?
                    the main purpose of the ExecutorService is to say like it will take care of executing the Runnables so what we need to do is configure means what kind of  model we want to run 
                        so we just gives the Runnable to the ExecutorService and it will run it.     
                    img3,   img4.
                    
Callable:-
        JS
        go to I_Callable class.
        
Future:-
        JS
        go to I_Future class.
        

Thread Local:-
        JS
        Associate State with a thread.
                eg:-    user or Transaction ID.
        
        Each Thread has a map of its ThreadLocals
                once threads stops, all its ThreadLocals are released.
                
                
        Example:-
            go to I_ThreadLocal class.
        
        JB
            let say we want to access a variable in a multi places in a thread. 
                means we can create a thread local variable which will local to the thread.
                also we can use get() and set() method to get and set the value of the variable. 
            
            watch video again.   

```


