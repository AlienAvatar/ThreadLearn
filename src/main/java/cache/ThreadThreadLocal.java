package cache;

public class ThreadThreadLocal extends Thread {
    public static void main(String[] args) {
        final ThreadLocal threadLocal = new ThreadLocal(); //把共享数据的可见范围限制在同一个线程之内
        final Thread t1 = new Thread(new ThreadThreadLocal()){
            public void run(){
                threadLocal.set(1);
                System.out.println("Thread1 " + threadLocal.get());
                threadLocal.remove();
            }
        };
        Thread t2 = new Thread(new ThreadThreadLocal()){
            public void run(){
//                threadLocal.set(2);
                System.out.println("Thread2 " + threadLocal.get());
                threadLocal.remove();
            }
        };

        t1.start();
        t2.start();
    }
}
