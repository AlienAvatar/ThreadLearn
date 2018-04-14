package cooperation;

public class ThreadCooperation extends Thread{
    private static ThreadCooperation cooperation = new ThreadCooperation();
    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadCooperation()){
            public void run(){
                System.out.println("wait start");
                try {
                    synchronized (cooperation) {
                        cooperation.wait();
                    }
                    System.out.println("wait end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.start();

        Thread t2 = new Thread(new ThreadCooperation()){
            public void run(){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("notify start");
                synchronized (cooperation) {
                    cooperation.notifyAll();
                }
                System.out.println("notify end");
            }
        };
        t2.start();
    }
}
