package com.create;

public class ThreadTest2 implements Runnable {
    public void run() {
        System.out.println("Hello World");
    }

    public static void main(String[] args) {
        Thread t = new Thread(new ThreadTest2());
        t.start();
    }
}
