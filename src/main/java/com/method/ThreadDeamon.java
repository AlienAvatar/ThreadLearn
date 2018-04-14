package com.method;

public class ThreadDeamon implements Runnable {
    public void run(){
        System.out.println("Hello World");
    }

    public static void main(String[] args) {
        Thread t = new Thread(new ThreadDeamon());
        t.setDaemon(true);
        t.start();
    }
}
