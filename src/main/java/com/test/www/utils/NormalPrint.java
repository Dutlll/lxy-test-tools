package com.test.www.utils;

public interface NormalPrint {
    Object lock = new Object();
    default void normalPrint(Runnable print){
        synchronized (lock){
            print.run();
        }
    }
}
