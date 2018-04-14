package cooperation;

public class ThreadJoin {
    public class TestA extends Thread {
        public void run() {
            System.out.println("TestA");
        }
    }

    public class TestB extends Thread {
        TestA a = new TestA();
        public void run() {
            try {
                a.join();//线程B挂起，让A先执行完，再执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("TestB");
        }
    }

    public void test() {
        Thread t1 = new Thread(new TestA());
        Thread t2 = new Thread(new TestB());
        t2.start();
        t1.start();
    }

    public static void main(String[] args) {
        ThreadJoin cooperation = new ThreadJoin();
        cooperation.test();
    }
}
