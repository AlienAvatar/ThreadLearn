package com.interrupt;

public class ThreadInterrupted implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new ThreadInterrupted());
        t.start();
        t.interrupt();
        System.out.println(Thread.interrupted());
        System.out.println(t.isInterrupted());
        System.out.println("Thread already inturupt");

    }

    public void run() {
//        while()
        System.out.println("Hello World");
    }
}
