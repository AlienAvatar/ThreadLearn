package com.synchronize;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadLock {
    private static Lock lock = new ReentrantLock();

    public void run() {
        go();
    }

    private static void go() {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
        } finally {
            lock.unlock();
        }
    }

    private static void go2() {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
            System.out.println();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadSynChronized()) {
            public void run() {
                go2();
            }
        };
        Thread t2 = new Thread(new ThreadSynChronized()) {
            public void run() {
                go2();
            }
        };
        t1.start();
        t2.start();
    }
}
