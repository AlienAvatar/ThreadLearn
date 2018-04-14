package com.method;

public class ThreadSleep implements Runnable {
    public void run(){
        System.out.println("Hello World");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread t = new Thread(new ThreadSleep());
        t.start();
    }
}
