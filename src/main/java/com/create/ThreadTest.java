package com.create;

public class ThreadTest extends Thread{
    @Override
    public void run(){
        System.out.println("Hello World");
    }

    public static void main(String[] args) {
        Thread t = new Thread(new ThreadTest());
        t.start();
    }
}
