package com.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class ThreadTest3 implements Callable {
    public String call() throws Exception {
        return "Hello World";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> f = new FutureTask(new ThreadTest3());
        Thread t = new Thread(f);
        t.start();
        System.out.println(f.get());
    }
}
