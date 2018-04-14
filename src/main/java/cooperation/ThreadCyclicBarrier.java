package cooperation;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadCyclicBarrier extends Thread {
    public static void main(String[] args) {
        int count = 10;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
        ExecutorService executor = Executors.newCachedThreadPool();
        for(int i = 0; i < count; i++){
            executor.execute(new ThreadCyclicBarrier());
            System.out.println("CyclicBarrier start");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("CyclicBarrier end");
        }
        executor.shutdown();
    }

    public void run(){
        System.out.println("Hello");
    }
}
