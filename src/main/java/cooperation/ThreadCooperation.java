package cooperation;

public class ThreadCooperation extends Thread{
    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadCooperation()){
            public void run(){
                System.out.println("wait start");
                try {
                    synchronized (ThreadCooperation.class) {
                        wait();
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
                System.out.println("notify start");
                synchronized (ThreadCooperation.class) {
                    notifyAll();
                }
                System.out.println("notify end");
            }
        };
        t2.start();
    }
}
