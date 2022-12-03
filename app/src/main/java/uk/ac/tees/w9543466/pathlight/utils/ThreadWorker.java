package uk.ac.tees.w9543466.pathlight.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadWorker {

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public static void execute(Runnable runnable) {
        executorService.execute(runnable);
    }
}
