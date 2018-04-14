# ThreadLearn
线程知识整理
## 创建线程
1.实现Runable接口

2.继承Thread

3.实现 Callable接口

对比：
   java不支持多重继承，但可以实现多个Runnable接口
## 创建线程池 Excutor

1.newCachedThreadPool()
创建一个可缓存的线程池，如果线程池的大小超过了处理任务所需要的线程，那么就会回收部分空闲线程。
2.newFixedThreadPool(nThreads: n)
创建固定线程池个数
3.newSingleThreadExecutor()
创建一个单一线程池，相当于newFixedThreadPool(1)
4.newScheduledThreadPool()
创建一个大小无限的线程池。此线程池支持定时以及周期性执行任务的需求。

## 守护线程
守护线程是程序运行时在后台提供服务的线程
当主线程终止时，守护线程也会终止
    
      public static void main(String[] args) {
            Thread t = new Thread(new ThreadDeamon());
            t.setDaemon(true);
            t.start();
        }
      
## 睡眠
sleep()
线程处于休息状态

    public void run(){
            System.out.println("Hello World");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
## 让步
yield()
当前线程已经执行完毕，可以切换给其它线程来执行。
该方法是给线程调度器的一个建议，而且要保证相同优先级。
    
    public void run(){
            System.out.println("Hello World");
            Thread.yield();
        }

## 中断

当线程执行完毕或者发送异常时，线程会停止运行

interrupt()
提前结束线程，如果该线程处于阻塞，等待状态，就会抛出InterruptException，从而提前结束线程。
但不能中断synchronized锁和I/O锁

    public void run(){
            try {
                Thread.sleep(10000);
                System.out.println("Hello World");//并没有执行到，被中断了
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    
        public static void main(String[] args) throws InterruptedException {
            Thread t = new Thread(new ThreadInterrupt());
            t.start();
            t.interrupt();
            System.out.println("Thread already inturupt");
        }

interupted()

isInterrupted()

1.interrupted 是作用于当前线程是否是中断状态，执行后具有清除状态功能。
2.isInterrupted 是作用于调用该方法的线程对象所对应的线程，判断线程是否处于中断状态。
（线程对象对应的线程不一定是当前运行的线程。例如我们可以在A线程中去调用B线程对象的isInterrupted方法。）

在循环体中，判断线程是否处于中断状态，从而提前结束线程。


## 互斥同步块

sychronized

保证线程安全
1.同步一个（静态）方法  （静态作用于整个类）
2.同步一个类

    public class ThreadSynChronized extends Thread {
    
        public void run(){
            go();
        }
    
        private synchronized static void go(){
            for(int i = 0; i < 10; i++){
                System.out.print(i + " ");
            }
            System.out.println();
        }
    
        private static void go2(){
            synchronized (ThreadSynChronized.class) {
                for (int i = 0; i < 10; i++) {
                    System.out.print(i + " ");
                }
            }
            System.out.println();
        }
    
    
        public static void main(String[] args) {
            Thread t1 = new Thread(new ThreadSynChronized()){
                public void run(){
                    go2();
                }
            };
            Thread t2 = new Thread(new ThreadSynChronized()){
                public void run(){
                    go2();
                }
            };
            t1.start();
            t2.start();
        }
    }

重入锁

锁相当于手动同步，synchronized相当于自动同步
切记：锁同步完成后要释放锁，否则会发生死锁。

    public class ThreadLock {
        private static Lock lock = new ReentrantLock();
    
        public void run() {
            go();
        }
    
        private static void go() {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.print(i + " ");
                }
                System.out.println();
            } finally {
                lock.unlock();
            }
        }
    
        private static void go2() {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.print(i + " ");
                }
                System.out.println();
            } finally {
                lock.unlock();
            }
        }
    
    
        public static void main(String[] args) {
            Thread t1 = new Thread(new ThreadSynChronized()) {
                public void run() {
                    go2();
                }
            };
            Thread t2 = new Thread(new ThreadSynChronized()) {
                public void run() {
                    go2();
                }
            };
            t1.start();
            t2.start();
        }
    }
    
### 线程安全
线程安全：当多个线程访问某个类时，这个类始终都能表现出正确的行为，那么这个类就称为线程安全的。

（多个线程的执行结果和单线程的执行结果一致时，就可称为线程安全的）

无状态对象一定是线程安全的

### 竞态条件 
在并发编程中，这种由于不恰当的执行时序而出现不正确的结果。当某个计算的正确性取决于多个线程的交替执行时序时，就会发生竞态条件。

（正确的结果取决于运气）

### 用锁来保护状态

每个共享的和可变的变量都应该只由一个锁来保护，从而使维护人员知道哪一个锁。

### synchronized 和 Reentrantlock 比较
1.锁的实现
synchronized是JVM实现，Reentrantlock是JDK实现。
2.synchoronized能不用担心没有释放锁，JVM会保证锁的释放，优先使用synchoronized

## 线程之间的协作

join()

在一个线程中调用另一个线程的join()方法，会将当前线程挂起，而不是忙等待，直到目标线程结束。

    public class ThreadJoin {
        public class TestA extends Thread {
            public void run() {
                System.out.println("TestA");
            }
        }
    
        public class TestB extends Thread {
            TestA a = new TestA();
            public void run() {
                try {
                    a.join();//线程B挂起，让A先执行完，再执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("TestB");
            }
        }
    
        public void test() {
            Thread t1 = new Thread(new TestA());
            Thread t2 = new Thread(new TestB());
            t2.start();
            t1.start();
        }
    
        public static void main(String[] args) {
            ThreadCooperation cooperation = new ThreadCooperation();
            cooperation.test();
        }
    }

## wait() notify() notifyAll()
等待 唤醒 唤醒所有

wait会释放锁，sleep不会

## AQS

CountDownLatch
用来控制一个线程等待多个线程。
每次调用countDown()让计数器减一

CyclicBarrier
用来控制多个线程相互等待，只有当多个线程到达时，这些线程才会执行。

Sempahore

用来控制系统的信号量，可以控制互斥资源的访问量。

控制线程的并发访问量
    
    public class ThreadSemaphore extends Thread{
        Semaphore semaphore = new Semaphore(3);//每次只能3个客户访问
        public static void main(String[] args) {
    
            ExecutorService executor = Executors.newCachedThreadPool();
            for(int i = 0; i < 10; i++){
                executor.execute(new ThreadSemaphore());
            }
            executor.shutdown();
        }
    
        public void run(){
            try {
                semaphore.acquire();
                System.out.print(semaphore.availablePermits() + " ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        }
    }
    
## BlockingQueue


## 对象的共享

在缺乏足够同步的多线程程序中，要想对内存的操作的执行顺序进行判断，几乎无法得出正确的结论。

### 加锁与可见性

可见性：为了确保某个线程写入该变量的值对于其他线程来说都是可见的，访问某个共享且可变的变量时要求所有线程在同一个锁上同步。

### volatile变量

在执行访问volatile变量时，不会执行加锁操作，因此也就不会使执行线程堵塞，因此volatile变量是一种比synchronized更轻量级的同步机制。

  volatile boolean asleep;
  ...
  while(!asleep){
    ...
  }
  
加锁机制既可以确保原子性又可以确保可见性，volatile变量只能确保可见性。

volatile变量使用时机：
1.当变量的写入操作不依赖变量的当前值，或者你能确保只有单个线程更新变量的值。
2.该变量不会与其它状态变量一起纳入不变性条件。
3.在访问变量时不需要加锁。

不要在构造过程中使this引用溢出

## 线程封闭

一种避免使用同步的方式，就是不共享数据。

### ThreadLocal类

ThreadLocal对象通常用于防止对可变的单实例变量或全局变量进行共享。

使用时机：当某个频繁执行的操作需要一个临时对象，例如一个缓冲区，而同时又希望避免在每次执行时，都需要重新分配该临时对象。

## 不变性
不可变对象一定是线程安全的。

事实不可变对象：如果对象从技术上是可变的，但其状态发布后不会再改变。
例如：Date是可变的

    public Map<String,Date> lastLogin = Collections.synchronizedMap(new HashMap<String,Date>());
    
对象的发布需求取决于它的可见性
当发布一个对象时，必须明确地说明对象的访问方式，
1.线程封闭
2.只读共享
3.线程安全共享
4.保护对象

# 对象的组合

## 实例封闭

通过封闭机制来确保线程安全

    public class PersonSet{
      private fianl Set<Person> mySet = new HashSet<Person>();
      
      public synchornized void addPerson(Person p){
          mySet.add(p);
       }
   }
  
封闭机制更易于构造线程安全类，因为当封闭类的状态时，在分析类的线程安全时，就无须检查整个程序。
  
## Java监视器模式

把所有可变状态封装起来，并由对象自己的内置锁保护。

    public class PrivateLock{
      private final Object myLock = new Object();
      Wiget wiget;
      
      void someMethod(){
          synchronized(myLock){
              //modify Wiget status
         }
      }
    }

## 将同步策略文档化
# 基础构建模块

所有共享容器在迭代的地方都要加锁

通过并发容器代替同步容器可以极大地提高性能，降低风险。

ConcurrentHashMap
    加锁机制：分段锁

生产-消费者队列

闭锁 FutureTask

信号量 Semaphore

栅栏 CyclicBarrier
