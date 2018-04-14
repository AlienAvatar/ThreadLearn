# ThreadLearn
线程知识整理
## 创建线程
1.实现Runable接口
2.继承Thread
3.
### 线程安全
线程安全：当多个线程访问某个类时，这个类始终都能表现出正确的行为，那么这个类就称为线程安全的。

（多个线程的执行结果和单线程的执行结果一致时，就可称为线程安全的）

无状态对象一定是线程安全的

### 竞态条件 
在并发编程中，这种由于不恰当的执行时序而出现不正确的结果。当某个计算的正确性取决于多个线程的交替执行时序时，就会发生竞态条件。

（正确的结果取决于运气）

### 内置锁

    synchronized(lock){
      //do something();
    }
    
### 重入

### 用锁来保护状态

每个共享的和可变的变量都应该只由一个锁来保护，从而使维护人员知道哪一个锁。

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

## 使用线程

1.实现Runnable接口
2.继承Thread类
3.实现Callable接口



所有共享容器在迭代的地方都要加锁

通过并发容器代替同步容器可以极大地提高性能，降低风险。

ConcurrentHashMap
    加锁机制：分段锁

生产-消费者队列

闭锁 FutureTask

信号量 Semaphore

栅栏 CyclicBarrier
