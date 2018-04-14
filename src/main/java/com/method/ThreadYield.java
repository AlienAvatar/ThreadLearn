package com.method;

public class ThreadYield implements Runnable {
    public void run(){
        System.out.println("Hello World");
        Thread.yield();
    }

    public static void main(String[] args) {
        Thread t = new Thread(new ThreadYield());
        t.start();
    }
}
