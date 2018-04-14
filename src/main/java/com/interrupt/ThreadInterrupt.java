package com.interrupt;


public class ThreadInterrupt implements Runnable {
    public void run(){
        try {
            Thread.sleep(10000);
            System.out.println("Hello World");//并没有执行到，被中断了
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new ThreadInterrupt());
        t.start();
        t.interrupt();
        System.out.println("Thread already inturupt");
    }
}
