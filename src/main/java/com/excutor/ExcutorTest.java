package com.excutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExcutorTest implements Runnable {

    public void run() {
        System.out.println("Hello World");
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        ExecutorService executorService3 = Executors.newFixedThreadPool(3);
        ExecutorService executorService4 = Executors.newScheduledThreadPool(3);
        for(int i = 0; i < 3; i++){
            executorService.execute(new ExcutorTest());
        }
        executorService.shutdown();
    }
}
