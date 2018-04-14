package cooperation;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadCyclicBarrier extends Thread {
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

    public static void main(String[] args) {
        int count = 10;
        ExecutorService executor = Executors.newCachedThreadPool();
        for(int i = 0; i < count; i++){
            executor.execute(new ThreadCyclicBarrier());
        }
        executor.shutdown();
    }

    public void run(){
        System.out.println("CyclicBarrier start");
        try {
            cyclicBarrier.await(); //等待所有线程执行完
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("CyclicBarrier end");
    }
}
