package cooperation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

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
