package cooperation;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadCountDownLatch implements Runnable{
    private static CountDownLatch latch = new CountDownLatch(10);//计数器cnt = 10

    public void run() {
        System.out.println("latch start");
        latch.countDown();
        System.out.println("latch end");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        int countThread = 10;
        ExecutorService executor = Executors.newCachedThreadPool();
        for(int i = 0;  i < countThread; i++){
            executor.execute(new ThreadCountDownLatch());
        }
        executor.shutdown();
    }
}
