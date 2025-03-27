package org.karam.cleanjex;

import java.lang.Thread.UncaughtExceptionHandler;

public class CleanErrorHandler implements UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.err.println(CleanFormatter.format(t, e));
    }

    public static void enable() {
        Thread.setDefaultUncaughtExceptionHandler(new CleanErrorHandler());
    }
}
