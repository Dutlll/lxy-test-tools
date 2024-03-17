package www.lxy.concurrent;

public class LogUtil {
    public static void threadStart(){
        System.out.println(Thread.currentThread()+" start...");
    }
    public static void threadEnd(){
        System.out.println(Thread.currentThread()+" end...");
    }
}
