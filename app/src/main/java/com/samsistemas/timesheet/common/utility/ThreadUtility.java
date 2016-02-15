package com.samsistemas.timesheet.common.utility;

import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author jonatan.salas
 */
public final class ThreadUtility {
    private static final String LOG_TAG = ThreadUtility.class.getSimpleName();
    private static final Object lock = new Object();

    private ThreadUtility() { }

    public static void sleep(long millis) {
        try {
            synchronized (lock) {
                lock.wait(millis);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static <T> T runInBackGround(final CallBack<T> callBack) {
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        final Callable<T> callable = new Callable<T>() {
            @Override
            public T call() {
                return callBack.execute();
            }
        };

        final Future<T> future = executor.submit(callable);
        executor.shutdown();

        T object = null;

        try {
            object = future.get();
        } catch (ExecutionException | InterruptedException ex) {
            Log.d(LOG_TAG, ex.getMessage(), ex.getCause());
        }

        return object;
    }

    public interface CallBack<T> {

        T execute();
    }
}
