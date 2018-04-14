package cooperation;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadCountDownLatch implements Runnable{

    public void run() {
        System.out.println("Hello World");
    }

    public static void main(String[] args) throws InterruptedException{
        int countThread = 10;
        CountDownLatch latch = new CountDownLatch(countThread);//计数器cnt = 10
        ExecutorService executor = Executors.newCachedThreadPool();
        for(int i = 0;  i < countThread; i++){
            executor.execute(new ThreadCountDownLatch());
            latch.countDown();
        }
        latch.await();
        executor.shutdown();
    }
}
