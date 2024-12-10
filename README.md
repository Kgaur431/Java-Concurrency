## Thread
``` 

Basic:-
        In the past, we had a single cpu machine and we could still run multiple programs on the same machine at the same time.  it appears like it running at the same time. 
        beocz os swap between the process very quickly. so it looks like all the program are running at the same time. 
        normally process ran in it's own memory space this feels a when we want to communicate b/w the process then it is more expensive.
            why ? 
                becoz we have to use some kind of inter-process communication mechanism like socket, pipe, file etc. 
                so that we can communicate b/w the process and it is more expensive. 
        so to overcome this problem we have a concept of thread.
        threads are like a light weight process.
        it is very qucikly for the scheduler to swap b/w the threads and all the threads share the same memory space so they can see each other's  data. 
            becoz of that they can communicate with each other very easily. but it also challange like if we are not careful then one thread might overwrite the data of another thread. 
            and it becomes more complicated when we have multi-core machines are running on diff sockets. becoz the data has to move b/w the diff caches like L1, L2, L3 etc.
                (it means when we have multiple threads running on multiple cores then we have to be very careful about the data that we are sharing b/w the threads.) 
                so keeping everything in sync is very expensive. and this is managed by the os unfortunately and by the hardware. 
                so it's important when we have multiple threads that we ensure that we have data integrity b/w the threads that we don't overwrite the data of another thread.
        img5 & img6.

Threading Models:-
        1.  Preemptive multithreading (Native Threads):-
                    it means we have native threads for each java threads. there is an native os threads.                                                                                                                                {watch video 2.2 threading models, Master threads}. 
                    the os is responsible for forcing the context switch b/w the threads. means the os can stop the thread at any time and start another thread. 
                       eg:-
                            Thread A acquires a lock on a shared resource (e.g., a file or variable). It starts working on its task while holding the lock.
                            The OS, using preemptive multithreading (The OS pauses Thread A and starts Thread B), pauses Thread A (context switch) and gives the CPU to Thread B.
                            Thread B attempts to acquire the same lock that Thread A is holding. However, since Thread A hasn’t released the lock yet, Thread B is blocked and cannot proceed.
                            At this point, the OS may allow other threads (if any) to execute or eventually resume Thread A. Once Thread A resumes and completes its task, it releases the lock.
                            After the lock is released, Thread B can acquire it and continue with its task.  img1, img2. 
                            
                                                        or
                            a thread might hold a lock can be swapped out by the os and another thread need the lock now the code may progress becoz they can't give the lock to the other thread. becoz that lock belongs to the first thread.         {watch video 2.2 threading models, Master threads}.        
                             what does it mean that lock in this case ? 
                                here lock means the thread is holding a lock on a resource and another thread is waiting for that lock but the os can swap out the first thread, Progress is delayed until the OS allows Thread A to resume and release the lock.                                                                                                     
        2.  Cooperative multithreading (Green Threads):- 
                    this way we had a single native thread calling all of the java threads. means it is like simulation of the threads is happening. 
                    
        Preemptive multithreading is the most common threading model used in modern operating systems.
            it is more safer, becoz we can't accidently not be swapped out by the os. but we also swapped out at the wrong time sometime. 
            also we have a problem of scalibility, becoz if we have single native thread __ java thread is limited by the os as how many threads we can construct. 
            and sometimes using thread we can have very convinent model like we can easy to write code by having one thread pool but it doesn't scale very well becoz we can't have millions of java threads in our program.  
            img3. 

Two diff Application of threads:- 
        1.  Parallel Computing:-
                    Parallel computing is a technique used to solve complex and computationally intensive problems more efficiently. It works by dividing a large problem into smaller, independent tasks and executing these tasks simultaneously on multiple processors or cores. 
                        This approach takes advantage of modern multi-core CPUs, GPUs, and distributed computing systems.
                        so Problems that can be easily divided into independent tasks benefit the most.
                    it is to solve the complex problem quicker called parallelism.
                        eg:- it used in bitcoin mining, weather forecasting, goverment securities, nuclear bombing simulation etc. 
                    Large problems can be divided into smaller tasks that can be solved in parallel on multiple cores.
                    it focus on solving the problem quicker.
                    communication overhead reduces speedup posibilities.  
                        The speedup is limited by factors like communication overhead and the dependency between tasks.
                    img4.
                    
        2.  Concurrent Computing:-
                    this way we don't have one to one realtionship b/w the no. of cores and the no. of threads.  but in parallel computing we have very closed relationship one to one  b/w the no. of cores and the no. of threads. this point is emphasizing  that 1000 threads may exist even if we have 8 cores.
                    but in concurrent computing there we might be a point of doing that. here we might executing parallel but not necessarily.
                    the purpose of concurrent computing is to simplify the architecture. like independent tasks simplyfying the architecture.
                    do something useful during wating time (like I/O (blocking IO is easily to do but non-blocking IO is difficult to do), Locks etc).
                    it focus on interaction b/w the tasks(memory integrity, progress etc).
                    does not always scale very well. 
                    
                    it can be use on any number fo cores. 
                    
                    img7.
                    
Java Memory Model (JSR 133):-
        Java Memory Model (JMM) is a specification that describes how threads interact through memory. It defines the behavior of threads and the memory they share.
        The JMM ensures consistency, making Java multi-threading portable and reliable.
        Without the JMM, the behavior of multi-threaded Java programs would be unpredictable across different JVMs.
        As a developer, you must follow synchronization rules (e.g., use synchronized, volatile, or Lock) when accessing shared data between threads.
        img 8.
        
        Describe shared java memory behaviour. 
        Minimum requirment of what must happen. 
        Allows JVM implementors some freedom. 
        A correctly written multi-threaded java application will be correct on every available JVM.
            A correctly running java applicaiton on one JVM could still be incorrect if it breaks JMM laws. 
            
 
 New Thread with Runnable:-
        The another way to create a lambda for runnable & pass it into thread constructor. and then thread.start() to start the thread. 
        newer way:-
            public class Main {
                public static void main(String[] args) {
                    var Thread1 = new Thread(() -> {
                        // concurrent code
                    });
                    thread.start();
                }
            }
       
       older way:- 
            public class MyThread extends Thread {
                public void run() {
                    // concurrent code
                }
            }
            
            public class Main {
                public static void main(String[] args) {
                    var thread1 = new MyThread();
                    thread1.start();
                }
            }
            
      in the newer way we are passing the lambda express so we can understand like this,
            () -> { code }  --> this is a lambda expression. 
            () this paranthesis is represent the () paranthsis of the run method.
            same with this block { code } it is represent the body of the run method.
            that means we are defining the run method as a lambda expression in the thread constructor. 
    
     In the older version we have to create a new class that extends the thread class and then we have to override the run method and write the concurrent code in the run method.
     but in the newer version we can directly pass the concurrent code in the thread constructor as a lambda expression..
 
 
ThreadGroups:-
        we are not using ThreadGroups in the modern java programming. like normally we use the ThreadPools, ExecutorService etc.
        
        Threads always belong to a group. 
        Should've used composite design pattern. 
                Groups can contain threads and other groups. 
        Used to partition threads. 

Shared Memory with Multithreading:-
        like Multiple threads working with the same object, how do we ensure that we don't have corruption of the data. 
        if several threads access a memory location at the same time, we will get the race conditions. 
        eg:- 
              BANKACCOUNT EXAMPLE:-
                    public class BankAccount {
                        private int balance;
                        public BankAccount(int balance) {
                            this.balance = balance;
                        }
                        public int getBalance() {
                            return balance;
                        }
                        public void deposit(int amount) {
                            balance += amount;
                        }
                        public void withdraw(int amount) {
                            balance -= amount;
                        }
                    } 
        
        demo of Race Condition:-
                there are two threads. t1 calls deposit (100)) and t2 calls withdraw (100). this happens at the same time. and currently the balance is 1000 (in the java heap so it is managed by the garbage collector).
                    also the threads t1 & t2 have the stack memory. so t1 call the deposite method where amount=100 on to the stack. and t2 call the withdraw method where amount=100 on to the stack.  img9. this happening at the same time on the diff cores.
                    now both read the balance from the heap memory. and both have the balance=1000. img10.
                    now they both do the operation on the balance. t1 do the balance += amount; and t2 do the balance -= amount;
                        so the balance should be 1000+100=1100 and 1000-100=900.
                   if we ask the balance from the t1 then it will return 1100 and if we ask the balance from the t2 then it will return 900. 
                  now we have race condition. becoz the result is unpredictable img 11. like t2 update the balance first so the balance is 900. but t1 override  the balance after that so the balance is 1100  img12.
                  so the balance is 1100. but as we called deposit and withdraw so the balance should be 1000.
                  
                  what could happen ?
                        anything could happen. 
                            without synchronization, the balance is 
                                1000 --> that we want.
                                1100 --> the withdrawl() result was overwritten by the deposit(). 
                                900 --> the deposit() result was overwritten by the withdrawl().
                                both 1100 and 900 --> the deposit() and withdrawl() threads see his own result. 
                                neither --> if field is a numeric 64-bit type. 
                img13, img14.
                
How do we Race Condition:-
        1.  Synchronized:-
                    java has built in mechanism called synchronized. 
                    synchronized work on any java object so we can use any java obj as a monitor.  
                    normal java objects can be used as a monitor. that means if we synchronized with the object then when we want at other time  we can use the same object.
                    only one thread at a time can acquire the monitor with synchronized. 
                    No back-off strategy to give up waiting for synchronized. means if we want to lock then we have to wait unitll we get the lock.     img15. 
        
                    eg:-
                            public class BankAccount {
                                private final Object monitor = new Object();            // here recommended to use the plain Object not like the String. 
                                private int balance;
                                public BankAccount(int balance) {
                                    this.balance = balance;
                                } 
                                public int getBalance() {
                                    return balance;
                                }
                                public void deposit(int amount) {
                                    synchronized (monitor) {    // Locking the critical section
                                        balance += amount;
                                    }
                                }
                                public void withdraw(int amount) {
                                    synchronized (monitor) {    // Locking the critical section
                                        balance -= amount;
                                    }
                                }
                            }
                            
                            img16.
                            
                            it means evey bankaccount has its own monitor so if two diff threads want to talk with two diff bankaccount that's fine.
                            so we can have two threads writing into the two diff bankaccount at the same time that will not cause any problem.
                            but if two threads are writing into the same bankaccount then they have to go through the monitor. img 18.
                                like:-  In the deposit method, we synchronize on the monitor object. This means that only one thread can execute the deposit method at a time. 
                                        The same applies to the withdraw method. 
                                        This way, we ensure that the balance is updated correctly
                                means we are locking the critical section of the code.
                        
                        working:-
                                so if t1 call the deposit method then it will acquire the lock on the monitor object and then it will update the balance. 
                                let say t2 also call the deposit method then it will try to acquire the lock on the monitor object but it can't acquire the lock becoz t1 is holding the lock. so t2 has to wait until t1 release the lock.     img17.
        
        Synchronized Methods:-
                we can also use the synchronized keyword with the entire method.
                    it same as the synchronizing on "this".
                    
                    public synchronized void deposit(int amount) {
                        balance += amount;
                    }
                    
                    When you apply the synchronized keyword to a method in Java, you're effectively locking the entire instance of the object, not just the method itself.
                            When Thread t1 calls a synchronized method (like deposit) on a BankAccount instance, it locks the entire instance of the object. This means that Thread t2 cannot call any other synchronized method (e.g., withdraw) on the same instance of BankAccount until Thread t1 finishes and releases the lock.
                            Even if t1 is not calling any other synchronized method like withdraw, but just executing the deposit method, t2 is blocked from calling any synchronized method on the same object (BankAccount), because the lock is held by t1. img19. 
                    
                    
        Synchornized Method vs "this":-                  
                what are the differences and similarities ?
                    case1:-
                            public void deposit(int amount) {
                                synchronized (this) {
                                    balance += amount;
                                }
                            }
                    
                    case2:-
                            public synchronized void deposit(int amount) {
                                balance += amount;
                            }
                    
                    these two cases provided are different approaches to synchronizing a method.  
                    In both cases, the synchronization happens on the instance of the object. Both approaches ensure that only one thread can execute the synchronized code at a time for the same object instance.
                    img20.
                            
                     not getting these differences as of know (Ask chatgpt)
                    differences:-
                         In the case1, we are synchronizing inside the method.
                         In the case2, we are synchronizing the entire method.
                    
                         we are locking on the same lock in both cases.
                         here the performance is same in both cases.
                         
                         if we make a method synchronized that synchronized modify becomes part of our method signature. somebody look that method then they can see that we can synchronizing on the object itself.
                         if somebody want to participate in the locking strategy, if we locking on this or on the object itself then they got the permission to do that.
                         what they might want to subclass      
                         they might want to another method also synchronized than they can make it synchronized and the subclass, if we make the subclass method synchronized then it's locking on the same object __ super classs.  <-- not getting   
                         it is part of the contract or promise is to say i am going to have this synchronized.
                         
                         so the case1 does not make the same promise,  in order to know what we are locking on, i have to be inside the code. so i have no longer promise that what  the locking on will be. 
                          
                          which one to choose:-
                            sometime the client should be the part of the locking strategy and if we want to do than we have to make the method synchronized.
                            but other time we don't. like there is a class called ConcurrentHashMap, it internally synchronized on something called ReantrantLock. it is an private object so the client can't participate in the locking strategy.
        
        Synchronized static methods:-
                Synchronized static method lock on class:-
                    public class Singleton {
                        private static Singleton instance;
                        private Singleton() {}
                        public static synchronized Singleton getInstance() {
                            if (instance == null) {
                                instance = new Singleton();
                            }
                            return instance;
                        }
                    }
                    
                    getInstance() could also be written like this
                        public static Singleton getInstance() {
                            synchronized (Singleton.class) {
                                if (instance == null) {
                                    instance = new Singleton();
                                }
                                return instance;
                            }
                        }
                        
                    having  a static and synchronized method is equivalent to locking on Singleton.class. img21.
                    
                   Differences:-
                        img22.
                        img23.
        
                     
                        
        Locking on this vs private lock:-
                when we synchronized a methods that actually break the encapsulation.
                    Impossible to change locking strategy later. 
                    other code could use your object as a lock. 
                    
                sometimes we want to do lock splitting where we use diff locks for diff parts of the class.
                    eg:- methods changeAddress and printAddress in a class BankAccount should be synchronized but don't need to be synchronized in respect to balance updating. 
                
                Easier to understand the concepts of locking if we are locking on a specific object instance. 
                
                ask to chatgpt:- 
                        what is author trying to say, explain it properly becoz i am unable to get any of the point which has written above. not getting any single point like why author has written has this and what is trying to acheive from it so i want understand from you based on the above point.
                       
   
                    
Thread States:-
        img 24. 
        when we create a thread (but not started) it's in a NEW state. 
        once a thread is start, it goes into runnable state. it means it is ready to run but it is not running yet when the os scheduler gives a cpu cycle to the thread then it goes into running state otherwise it will wait for the cpu cycle.
        once it exists the run method then it goes into the terminated state.
        when the thread is waiting for something like I/O operation then it goes into the waiting state. 
                means the thread is waiting for some condition to be true. like wait, join, take etc.
                let say from the waiting state we go to the blocked state.   
                means let say we call wait, in order to call wait we have to hold the lock. so when we call wait then the wait method lets go of the lock so we are not unlock and before we go to runnable state first we have to acquire the lock
                "threads can go directly from waiting to blocked state if exiting from wait()". 
                waiting is used to communicate b/w the threads normally. like one thread is waiting for another thread to finish so it goes into the waiting state.
        when the thread is waiting for another thread to finish then it goes into the blocked state. 
                means let say we are at runnable state and we are trying to get the lock on the monitor object via synchronized keyword but we can't get the lock becoz some other thread is holding the lock then we goes into the blocked state.
        when the thread is waiting for a specific time then it goes into the timed waiting state.
                like if we do t1.sleep(1000) then t1 goes into the timed waiting state.
        threads in waiting and timed-waiting states can usually be interrupted.
        img25.
         
         img26,  img27,  img28.


Inter-Thread Communication:-
        we can use the wait and notify method to communicate b/w the threads. 
        wait and notify are the methods of the object class
        
        we often want to communicate b/w the threads.   
            like we want to take something from the queue which is empty, we want to display a bank account balance. so like we are not give up, we tell the thread to wait unitl it's get an notification.
                    once the queue is not empty then the thread wakes up and then it can take the element from the queue.
           eg:- a queue which might be empty. 
                    instead of giving up we tell the thread to wait().
            when the resouce become available then we notify the waiting thread.
            
            
        wait and notify are the basic way of inter-thread communication.
                when we call lock.wait() then it relases the object lock and suspends the current thread. 
                when we call lock.notify() then it wakes up (Sending message) one of the waiting threads. 
                when we call lock.notifyAll() then it wakes up (Sending message) all the waiting threads.     
            
            we prefer to use notifyAll() instead of notify() becoz it is more safe.
                we can think like notify() is a faster becoz it is waking up only one thread but it is not safe becoz it is possible that the wrong thread is woken up.
                also notifyAll() is faster. 
                writing single notify() code is more difficult than writing notifyAll() code.
                    
                
            when we write wait() code we always have to write wait() code inside a loop. even if we think we not neede. 
                becoz  as we choose notifyAll() so we wakeup all the threads that are waiting. 
                when it come out of wait then it has to fight for the lock so when the race is on then it might lose the race like by somebuddy other thread get the lock.
                
                while(!precondition) {
                    lock.wait();
                }
                
                also caters for case where we lose the race for the lock.
                
            basic way of producer - consumer code:-
                public class ProducerConsumer {
                    private final List<Runnable> tasks = new ArrayList<>();
                    public ProducerConsumer() {
                        var thread = new Thread(() -> {
                            try{
                                while (true) {
                                take().run();
                                }
                            } catch (InterruptedException ignore) {}
                        });
                        thread.setDaemon(true);
                        thread.start();
                    }
                    
                private Runnable take() throws InterruptedException {
                    synchronized (tasks) {
                        while (tasks.isEmpty()) {
                            tasks.wait();
                        }
                        return tasks.remove(0);
                    }
                }
              
                public void submit(Runnable task) {
                    synchronized (tasks) {
                        tasks.add(task);
                        tasks.notifyAll();
                    }
                }
            }
            
            explain it what is author trying to say with this code:-
                img29,  img30,  img31.


Priorities:-
        Threads can have diff priorities.
            The priority of a thread is an integer value that determines the order in which threads are scheduled.
            Thread.MIN_PRIORITY = 1
                it means not so important.
            Thread.MAX_PRIORITY = 10
            Thread.NORM_PRIORITY = 5
        
        Thread priority cannot exceed thread group priority. 
        
        Caveat:-    Thread priority cannot be relied on
                it might be ignored by the JVM or underlying OS.
                  
        img 32, img33,  img34.

Daemon Threads:-
        It is a background thread.
        It is used to perform tasks like garbage collection, finalization, etc.
        It is automatically terminated when all the user threads are terminated.
        
        JVM runs until all the non-daemon threads exit. 
        Call setDaemon(true) before start()
            var t = new Thread(task);
            t.setDaemon(true);
            t.start();
            
       img35,   img36,  img37   img38,  img39.
                    
-------------------------------------------------------------Java Brains-------------------------------------------------------------                                   

It is single unit of sequential flow of control or unit of work within a program.

we can think it like this:-
    -   it is single sequential flow of control.
                means let say we have a bunch of statement written in the code, basically we have to execute it one after another.  --> that is a Thread. 
                thread is basically a small unit of work. 
    -   let say we have bunch program instruction that can be managed independently.    --> that is a thread.
    -   thread also allows the program to split into simultaneously running tasks.  
                means let say we want to process a 1000 number so we can have multiple threads, where each thread is processing a small portion of it like 500 number, another thread is processsing a small portion of it like 100 number and so on. all of them doing sharable work. 
                so the overall project done.    



```

### The OS scheduler is responsible for deciding which thread gets to use the CPU at any given time. This process is called context switching.4
### The state of the lock is managed independently of the OS. The OS doesn't control the lock; it only schedules threads.



