package com.test.www.utils;

public interface GlobalPrint {
    static Object lock = new Object();
    default void globalPrint(Runnable printer){
        synchronized (lock){
            printer.run();
        }
    }
}
