package com.lxy.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

//common util
public class CU {
    public static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    private static final Object functionUtilLock = new Object();
    public static final String EMPTY_STR = "";
    public static void preExecute(Runnable runnable) {
        //doNothing
    }

    public static void jucTestFrame(Runnable runnable) {
        preExecute(runnable);
        runnable.run();
        afterExecute(runnable);
    }

    public static void afterExecute(Runnable runnable) {
        System.out.println();
        System.out.println("-----afterExecute------");
        System.out.println("sleepSecond for 10 s");
        sleepSecond(10);
    }

    public static final <T> T functionUtil(Object args, T ans) {
        synchronized (functionUtilLock) {
            System.out.println("---------------------");
            System.out.println("args::" + args);
            System.out.println("ans::" + ans);
        }
        return ans;
    }
    public synchronized static boolean printIsInterrupt(){
        final boolean interrupted = Thread.currentThread().isInterrupted();
        System.out.println(Thread.currentThread()+":is interrupt ?:"+ interrupted);
        return interrupted;
    }
    public synchronized static boolean printIsInterruptAndReI(){
        final boolean interrupted = Thread.currentThread().isInterrupted();
        System.out.println(Thread.currentThread()+":is interrupt ?:"+interrupted);
        if (interrupted){
            Thread.currentThread().interrupt();
        }
        return interrupted;
    }
    public static void sleep(Supplier<Throwable> runnable) {
        try {
            final Throwable throwable = runnable.get();
            if (throwable != null){
                throw throwable;
            }
        } catch (Throwable e) {
            System.out.println();
            System.out.println("------------------------------------");
            System.out.println("sleepSecond error may be interrupted");
            if (e instanceof InterruptedException) {
                System.out.println("interrupt is eat so need to interrupt to ..");
                Thread.currentThread().interrupt();
            }
            System.out.println("------------------------------------");
            System.out.println();
        }
    }
    public static void sleepMSecond(int second) {
        sleep(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(second);
            }catch (Exception e){
                return e.getCause();
            }
            return null;
        });
    }
    public static void sleepSecond(int second) {
        sleep(() -> {
            try {
                TimeUnit.SECONDS.sleep(second);
            }catch (Exception e){
                return e.getCause();
            }
            return null;
        });
    }
}
