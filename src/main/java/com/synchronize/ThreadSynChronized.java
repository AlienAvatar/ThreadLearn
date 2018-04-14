package com.synchronize;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSynChronized extends Thread {

    public void run(){
        go();
    }

    private synchronized static void go(){
        for(int i = 0; i < 10; i++){
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static void go2(){
        synchronized (ThreadSynChronized.class) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadSynChronized()){
            public void run(){
                go2();
            }
        };
        Thread t2 = new Thread(new ThreadSynChronized()){
            public void run(){
                go2();
            }
        };
        t1.start();
        t2.start();
    }
}
