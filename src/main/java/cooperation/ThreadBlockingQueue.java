package cooperation;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadBlockingQueue {
    private static BlockingQueue<String> queue = new ArrayBlockingQueue<String>(5);

    private static class Producer extends Thread{
        public void run(){
            try{
                queue.put("product");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Product");
        }
    }

    private static class Consumer extends Thread{
        public void run(){
            String str = "";
            try{
                str = queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Consumer " + str);
        }
    }

    public static void main(String[] args) {
        for(int i = 0; i < 3; i++){
            Producer producer = new Producer();
            producer.start();
        }
        for(int i = 0; i < 3; i++){
            Consumer consumer = new Consumer();
            consumer.start();
        }
    }
}
